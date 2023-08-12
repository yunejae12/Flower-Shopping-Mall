package shopping.flowershop.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import shopping.flowershop.security.JwtService;
import shopping.flowershop.service.CookieService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CookieService cookieService;
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/member/login")){
            String authorizationHeader = request.getHeader("access_token");
            filterChain.doFilter(request,response);
        }else{
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
                try{
                    String token = authorizationHeader.substring("Bearer ".length());
                    String username = jwtService.extractUsername(token);
                    ArrayList<String> roles = (ArrayList<String>) jwtService.extractAllClaims(token).get("roles");
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    roles.forEach(role->
                        authorities.add(new SimpleGrantedAuthority(role)));
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username,null,authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);
                }catch (ExpiredJwtException exception){
                    String refreshToken = cookieService.getCookie(request,"refresh_token").getValue();
                    log.info("Access Token has been expired: {}",exception.getMessage());
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                    if(jwtService.isTokenValid(refreshToken,userDetails)){
                        Map<String,Object> extraClaims = new HashMap<>();
                        extraClaims.put("roles",userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
                        String accessToken = Jwts.builder()
                                .setSubject(userDetails.getUsername())
                                .setIssuer(request.getRequestURL().toString())
                                .setIssuedAt(new Date(System.currentTimeMillis()))
                                .setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
                                .setClaims(extraClaims)
                                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)), SignatureAlgorithm.HS256)
                                .compact();

                    }else{
                        log.warn("Refresh Token is not valid. automatically log out.");
                    }

                }
                catch (JwtException exception){
                    log.info("Error Authorization: {}",exception.getMessage());
                    response.setHeader("error",exception.getMessage());
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    Map<String,String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(),error);
                }
            }else{
                filterChain.doFilter(request,response);
            }
        }

    }
}

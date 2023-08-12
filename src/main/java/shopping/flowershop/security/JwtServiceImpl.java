package shopping.flowershop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Jwt에서 사용하는 토큰관련 Service
 *
 */
@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Override
    public String extractUsername(String token) {
        return extreactClaim(token,Claims::getSubject);
    }



    @Override
    public String generateToken(UserDetails userDetails, Long periodTime) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        HashMap<String,Object> claims = new HashMap<String,Object>();
        authorities.forEach(grantedAuthority -> {claims.put("authorities",grantedAuthority);});
        return generateToken(claims,userDetails,periodTime);
    }

    /**
     * 사용자 정보를 기반으로 토큰을 생성하여 반환하는 Method
     * @param extraClaims 추가할 Claims
     * @param userDetails 사용자 정보
     * @return String 토큰
     */
    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, Long periodTime) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+periodTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token){
        return extractTokenExpired(token).before(new Date());
    }

    private Date extractTokenExpired(String token) {
        return extreactClaim(token,Claims::getExpiration);
    }

    /**
     * 클레임 추출
     * @param token 토큰
     * @param claimsResolver 클레임타입을 T로 타입변경
     * @param <T>
     * @return
     */
    public <T> T extreactClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 모든 Claim을 추출
     * @param token 토큰
     * @return
     */
    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

package shopping.flowershop.security;


import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String extractUsername(String jwt);
    Claims extractAllClaims(String token);

    String generateToken(UserDetails userDetails, Long periodTime);

    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, Long periodTime);

    boolean isTokenValid(String token, UserDetails userDetails);
}

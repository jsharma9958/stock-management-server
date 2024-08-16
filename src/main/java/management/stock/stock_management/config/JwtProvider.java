package management.stock.stock_management.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.Secret_key.getBytes());

    public static String generateToken(Authentication auth, int uid) {
        return Jwts.builder()
                .setIssuer("jatinsharma")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // 24 hours expiration
                .claim("username", auth.getName())
                .claim("uid", uid)
                .signWith(key)
                .compact();
    }

    public static String getUsernameFromToken(String jwt) {
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return (String) claims.get("username");
    }

    public static int getUidFromToken(String jwt) {
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return (Integer) claims.get("uid"); // Cast to Integer
    }
}

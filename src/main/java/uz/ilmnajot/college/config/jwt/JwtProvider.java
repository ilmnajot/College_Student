package uz.ilmnajot.college.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.ilmnajot.college.Entity.User;
import java.util.Date;

@Component
public class JwtProvider {

    private static final Long expireTime = 10 * 24 * 3600 * 1000L;
    private static final String secretWord = "secret_word";

    public String generateToken(User user) {

        return Jwts
                .builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expireTime))
                .signWith(SignatureAlgorithm.HS512, secretWord)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretWord)
                    .setSigningKey(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        return Jwts
                .parser()
                .setSigningKey(secretWord)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

package warungmakanbahariapi.wmbahari.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import warungmakanbahariapi.wmbahari.entity.Customer;
import warungmakanbahariapi.wmbahari.entity.User;
import warungmakanbahariapi.wmbahari.model.response.JwtClaim;

import javax.management.relation.Role;
import java.time.Instant;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
@Slf4j
public class JwtUtils {

    @Value("${app.wm-bahari.jwt-secret}")
    private String secretKey;
    @Value("${app.wm-bahari.jwt-expirationInSecond}")
    private Long expirationInSecond;
    @Value("${app.wm-bahari.app-name}")
    private String appName;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            List<String> roles = user.getRole()
                    .stream()
                    .map(Role::getRoleName).toList();

            return JWT.create()
                    .withIssuer(appName)
                    .withSubject(user.getId())
                    .withExpiresAt(Instant.now().plusSeconds(expirationInSecond))
                    .withClaim("roles",roles)
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException(e);
        }
    }


    public boolean verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getIssuer().equals(appName);
        }catch (JWTVerificationException e){
            log.error("Invalid verification JWT : {}",e.getMessage());
            return false;
        }
    }

    public JwtClaim getUserInfoByToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            List<String> roles = decodedJWT.getClaim("role").asList(String.class);

            return JwtClaim.builder()
                    .userId(decodedJWT.getSubject())
                    .roles(roles)
                    .build();

        }catch (JWTVerificationException e){
            log.error("Invalid verification JWT : {}",e.getMessage());
            return null;
        }
    }
}

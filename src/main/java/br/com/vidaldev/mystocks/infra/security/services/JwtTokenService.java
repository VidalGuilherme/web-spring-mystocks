package br.com.vidaldev.mystocks.infra.security.services;

import br.com.vidaldev.mystocks.infra.security.entities.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    @Value(value = "${api.security.token.secret}")
    private String SECRET;

    @Value(value = "${api.security.token.issuer}")
    private String ISSUER;

    public String getToken(UserDetailsImpl userDetails){
        try{
            var algorithm = Algorithm.HMAC256(SECRET);

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(userDetails.getUsername())
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expireDate())
                    .sign(algorithm);

        }catch (JWTCreationException e){
            throw new RuntimeException("error generate token", e);
        }
    }

    public String getSubject(String token){
        try{
            var algorithm = Algorithm.HMAC256(SECRET);

            return JWT.require(algorithm)
                    .withIssuer("API mystocks")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTCreationException e){
            throw new RuntimeException("Invalid token.", e);
        }
    }

    private Instant creationDate() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant expireDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}

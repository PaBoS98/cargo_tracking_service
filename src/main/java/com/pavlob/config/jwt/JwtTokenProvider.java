package com.pavlob.config.jwt;

import com.pavlob.model.ClientToken;
import com.pavlob.model.TokenLevel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

    private static final String TOKEN_LEVEL_CLAIM_KEY = "tokenLevel";
    private static final String CLIENT_ID_CLAIM_KEY = "clientId";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;

    @Value("${jwt.algorithms}")
    private String jwtAlgorithm;

    public boolean isJwtTokenValid(final String jwt) {

        return !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public ClientToken decryptJwtToken(final String token) {
        final Claims claims = this.extractClaims(token);

        final ClientToken clientToken = new ClientToken();
        clientToken.setJwt(token);
        clientToken.setTokenLevel(TokenLevel.valueOf(claims.get(TOKEN_LEVEL_CLAIM_KEY).toString()));
        clientToken.setClientId(claims.get(CLIENT_ID_CLAIM_KEY).toString());
        clientToken.setExpired(claims.getExpiration());

        return clientToken;
    }

    private Claims extractClaims(final String token) {
        return Jwts.parser()
                .verifyWith(new SecretKeySpec(jwtSecret.getBytes(), jwtAlgorithm))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public TokenLevel getTokenLevel(final String jwt) {
        final Claims claims = extractClaims(jwt);

        return getTokenLevel(claims);
    }

    private TokenLevel getTokenLevel(final Claims claims) {
        final String tokenLevel = extractClaimByKey(claims, TOKEN_LEVEL_CLAIM_KEY, String.class);

        return TokenLevel.valueOf(tokenLevel);
    }

    public  <T> T extractClaimByKey(final String jwt, final String key, final Class<T> clazz) {
        final Claims claims = extractClaims(jwt);
        return extractClaim(claims, (k) -> claims.get(key, clazz));
    }

    private <T> T extractClaimByKey(final Claims claims, final String key, final Class<T> clazz) {
        return extractClaim(claims, (k) -> claims.get(key, clazz));
    }

    private <T> T extractClaim(final String jwt, final Function<Claims, T> claimsResolvers) {
        final Claims claims = extractClaims(jwt);
        return claimsResolvers.apply(claims);
    }

    private <T> T extractClaim(final Claims claims, final Function<Claims, T> claimsResolvers) {
        return claimsResolvers.apply(claims);
    }
}

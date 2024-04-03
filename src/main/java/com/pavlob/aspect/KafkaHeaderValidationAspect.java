package com.pavlob.aspect;

import com.pavlob.config.jwt.JwtTokenProvider;
import com.pavlob.model.ClientToken;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import static com.pavlob.Constant.BEARER_PREFIX;

@Aspect
@Component
@Slf4j
public class KafkaHeaderValidationAspect {

    // TODO move it to config service with encryption
    private static final String TRUSTED_CLIENT_ID = "trustedClientId";

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Before(value = "@annotation(ValidateKafkaHeader) && execution(* listenCargoReportTopic(..)) && args(token, message)", argNames = "token,message")
    public void validateTokenHeader(final String token, final String message) {

        final String jwt = removeBearerPrefix(token);

        if (tokenProvider.isJwtTokenValid(jwt)) {
            final ClientToken clientToken = tokenProvider.decryptJwtToken(jwt);
            final String clientId = clientToken.getClientId();
            if (clientId.equals(TRUSTED_CLIENT_ID)) {
                return;
            }
        }

        // TODO create exception handler and move there for avoid duplication
        log.error("token {} is invalid", token);
        throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Invalid token");
    }

    private String removeBearerPrefix(final String token) {
        if (token.startsWith(BEARER_PREFIX)) {
            return token.substring(BEARER_PREFIX.length());
        }
        return token;
    }
}

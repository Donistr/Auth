package org.example.auth.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "authlib")
@Setter
@Getter
public class AuthLibProperties {

    private String secretKey;

    private long refreshTokenExpirationTimeMillis;

    private long accessTokenExpirationTimeMillis;

}

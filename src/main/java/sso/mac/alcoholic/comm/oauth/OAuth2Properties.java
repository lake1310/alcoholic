package sso.mac.alcoholic.comm.oauth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("oauth2")
public class OAuth2Properties {

    private int expiredPeriod;
    private String resourceId;
    private String publicKey;
}

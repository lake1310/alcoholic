package sso.mac.alcoholic.comm.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@RequiredArgsConstructor
//@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final OAuth2Properties oAuth2Properties;

    @Bean("resourceTokenStore")
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessJwtTokenConverter());
    }

    @Bean("resourceAccessJwtTokenConverter")
    public JwtAccessTokenConverter accessJwtTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(oAuth2Properties.getPublicKey());
        return converter;
    }

    @Bean("resourceTokenServices")
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setTokenStore(tokenStore());
        services.setSupportRefreshToken(true);

        return services;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(oAuth2Properties.getResourceId());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/index").permitAll()
                .mvcMatchers("/admin").access("#oauth2.hasScope('write')")
                .anyRequest().authenticated();
    }
}

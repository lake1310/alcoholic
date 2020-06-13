package sso.mac.alcoholic.comm.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    정적인 컨텐츠 처리 ( html,css,js...)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/docs/**");
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /*
    동적인 컨텐츠 처리 ( controller 호출...)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .mvcMatchers("/accounts/index","/accounts/resister/**").permitAll()
                .mvcMatchers("/accounts/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                    .and()
                .csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("!/h2-console/**"))
                    .and()
                .headers().addHeaderWriter(
                        new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'")).frameOptions().disable()
                    .and()
                .formLogin()
                    .and()
                .httpBasic();
    }
}

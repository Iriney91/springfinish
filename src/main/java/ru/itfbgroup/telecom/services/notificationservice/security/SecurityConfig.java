package ru.itfbgroup.telecom.services.notificationservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.web.http.HttpSessionIdResolver;

import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.session.web.http.HeaderHttpSessionIdResolver.xAuthToken;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService clientDetailService;

    @Bean
    public HttpSessionIdResolver sessionStrategy() {
        return xAuthToken();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public MapSessionRepository sessionRepository() {
        return new MapSessionRepository(new ConcurrentHashMap<>());
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clientDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//вот это добавил
                .authorizeRequests()
                .antMatchers(
                        "/v2/api-docs",
                        "/auth/ok",
                        "/swagger-resources/**",
                        "/swagger-ui.html"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
        ;
    }
}

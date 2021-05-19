package com.eshop.backend.auth.security;

import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.auth.JWT.JWTAuthenticationFilter;
import com.eshop.backend.auth.JWT.JWTAuthorizationFilter;
import com.eshop.backend.auth.JWT.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthorizedUserDao authorizedUserDao;

    public WebSecurity(UserDetailsServiceImpl userService, BCryptPasswordEncoder bCryptPasswordEncoder, AuthorizedUserDao authorizedUserDao) {
        this.userDetailsService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authorizedUserDao = authorizedUserDao;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/login/**").permitAll()
                .antMatchers("/user/registration/**").permitAll()
                .antMatchers("/user/confirm-account/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), authorizedUserDao));

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

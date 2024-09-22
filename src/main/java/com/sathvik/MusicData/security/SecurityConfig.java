package com.sathvik.MusicData.security;

import com.sathvik.MusicData.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private ApiSecretFilter apiSecretFilter;

    @Autowired
    private AppConfig appConfig;

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final String artistLinksAPIRequestMatcher = "/artist-links/**";
        final String albumLinksAPIRequestMatcher = "/album-links/**";

        return http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(artistLinksAPIRequestMatcher)
                        .permitAll();
                    req.requestMatchers(albumLinksAPIRequestMatcher)
                        .permitAll();
                    req.anyRequest()
                        .authenticated();
                })
                .addFilterAt(apiSecretFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic().disable()
                .formLogin().disable()
                .build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
                Arrays.stream(
                        appConfig
                                .getAppCoonfigMap()
                                .getOrDefault("ALLOWED_ORIGINS", "")
                                .split(",")
                )
                .map(String::trim)
                .collect(Collectors.toList())
        );
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
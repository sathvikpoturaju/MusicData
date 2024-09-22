package com.sathvik.MusicData.security;

import com.sathvik.MusicData.config.AppConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

import static com.sathvik.MusicData.constants.ResponseMessages.API_SECRET_INVALID;

@Component
@AllArgsConstructor
public class ApiSecretFilter extends OncePerRequestFilter {
    private AppConfig appConfig;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String secret = request.getHeader("api-secret");

        if (Objects.isNull(secret) || !appConfig.getAppCoonfigMap().getOrDefault("API_SECRET_KEY", "").equals(secret)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(API_SECRET_INVALID);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
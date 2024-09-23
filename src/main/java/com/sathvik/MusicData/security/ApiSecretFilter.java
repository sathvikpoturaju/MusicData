package com.sathvik.MusicData.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

import static com.sathvik.MusicData.constants.ResponseMessages.API_SECRET_INVALID;
import static com.sathvik.MusicData.constants.AppConstants.API_SECRET_KEY;

@Component
public class ApiSecretFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String secret = request.getHeader("api-secret");

        if (Objects.isNull(secret) || !API_SECRET_KEY.equals(secret)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(API_SECRET_INVALID);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
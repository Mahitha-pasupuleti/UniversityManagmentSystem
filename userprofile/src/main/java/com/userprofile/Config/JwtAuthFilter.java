package com.userprofile.Config;

import com.userprofile.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null, username = null;

        Cookie[] cookies = request.getCookies();
        if ( cookies != null && authHeader == null ) {
            for (Cookie cookie : cookies) {
                if ( "accessToken".equals( cookie.getName() ) ) {
                    authHeader = "Bearer " + cookie.getValue();
                    break;
                }
            }
        }

        if ( authHeader != null && authHeader.startsWith("Bearer ") ) {
            token = authHeader.substring(7);
            try {
                username = jwtService.extractUsername(token);
                if ( !jwtService.isTokenExpired(token) ) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, List.of());
                    SecurityContextHolder.getContext().setAuthentication( authToken );
                }
            } catch(Exception ex) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

        filterChain.doFilter(request, response);
    }

}

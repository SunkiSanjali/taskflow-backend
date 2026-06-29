package com.taskflow.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil,
                                   CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        System.out.println("======================================");
        System.out.println("Incoming Request : " + request.getMethod() + " " + request.getRequestURI());
        System.out.println("Authorization Header : " + authHeader);

        // If no Authorization header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("No Bearer token found.");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        System.out.println("JWT Token : " + token);

        String username;

        try {
            username = jwtUtil.extractUsername(token);
            System.out.println("Username extracted from token : " + username);
        } catch (Exception e) {
            System.out.println("Failed to extract username from token.");
            e.printStackTrace();
            filterChain.doFilter(request, response);
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {

            try {

                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(username);

                System.out.println("User found in database : "
                        + userDetails.getUsername());

                System.out.println("Authorities : "
                        + userDetails.getAuthorities());

                if (jwtUtil.validateToken(token)) {

                    System.out.println("JWT Token is VALID");

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    SecurityContextHolder.getContext()
                            .setAuthentication(authToken);

                    System.out.println("Authentication SUCCESSFUL");

                } else {

                    System.out.println("JWT Token is INVALID");
                }

            } catch (Exception e) {

                System.out.println("Authentication FAILED");
                e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }
}
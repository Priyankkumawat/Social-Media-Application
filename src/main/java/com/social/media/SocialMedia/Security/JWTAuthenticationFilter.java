package com.social.media.SocialMedia.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTTokenHelper jwttOkenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Get token
        String requestToken = request.getHeader("Authorization");
        //Bearer 25252glgl
        System.out.println(requestToken);
        String username = null;
        String token = null;
        if(requestToken != null && requestToken.startsWith("Bearer")){
            token = requestToken.substring(7);
            try{
                username = jwttOkenHelper.getUsernameFromToken(token);
            }
            catch (IllegalArgumentException | ExpiredJwtException | MalformedJwtException e){
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("Jwt token not start with Bearer");
        }

        //validate token
        if(token != null && SecurityContextHolder.getContext().getAuthentication() != null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwttOkenHelper.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else{
                System.out.println("invalid jwt token");
            }
        }
        else{
            System.out.println("username is null or context is not null");
        }
        filterChain.doFilter(request, response);
    }
}

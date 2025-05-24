package com.moeb.proposal_manager.auth.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.moeb.proposal_manager.auth.model.CustomUserDetails;
import com.moeb.proposal_manager.auth.utils.JwtUtils;
import com.moeb.proposal_manager.user.model.User;
import com.moeb.proposal_manager.user.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;
  private final UserRepository userRepository;

  public JwtAuthFilter(JwtUtils jwtUtils, UserRepository userRepository) {
    this.jwtUtils = jwtUtils;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws IOException, jakarta.servlet.ServletException {

    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = authHeader.substring(7);
    String email = jwtUtils.extractUsername(token);

    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      User user = userRepository.findByEmail(email).orElse(null);
      UserDetails userDetails = new CustomUserDetails(user);

      if (user != null) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            userDetails, null,
            userDetails.getAuthorities());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }

    filterChain.doFilter(request, response);
  }
}
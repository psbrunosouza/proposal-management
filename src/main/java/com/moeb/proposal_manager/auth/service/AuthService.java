package com.moeb.proposal_manager.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import com.moeb.proposal_manager.auth.dto.LoginDTO;
import com.moeb.proposal_manager.auth.utils.JwtUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class AuthService {
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;

  public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
  }

  public String authenticate(LoginDTO loginRequest) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequest.getEmail(),
              loginRequest.getPassword()));

      UserDetails userDetails = (UserDetails) authentication.getPrincipal();

      return jwtUtils.generateToken(userDetails.getUsername());

    } catch (AuthenticationException e) {
      throw new RuntimeException("Authentication fail user or password incorrect", e);
    }
  }
}

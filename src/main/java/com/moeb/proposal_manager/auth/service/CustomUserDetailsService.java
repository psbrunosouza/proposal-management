package com.moeb.proposal_manager.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.moeb.proposal_manager.auth.model.CustomUserDetails;
import com.moeb.proposal_manager.user.model.User;
import com.moeb.proposal_manager.user.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserService userService;

  public CustomUserDetailsService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    User user = userService.findUsername(email)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

    return new CustomUserDetails(user);
  }
}
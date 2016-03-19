package com.brahalla.Cerberus.service.impl;

import com.brahalla.Cerberus.domain.User;
import com.brahalla.Cerberus.model.security.CerberusUser;
import com.brahalla.Cerberus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    } else {
      return new CerberusUser(
              user.getId(),
              user.getUsername(),
              user.getPassword(),
              user.getEmail(),
              user.getLastPasswordReset(),
              AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
      );
    }
  }

}

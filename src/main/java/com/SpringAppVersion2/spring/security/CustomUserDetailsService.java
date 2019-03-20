package com.SpringAppVersion2.spring.security;

import com.SpringAppVersion2.model.bean.User;
import com.SpringAppVersion2.spring.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String usernameOrEmail)
      throws UsernameNotFoundException {
    // Let people login with either username or email
    User user = userRepository.findByUserNameOrUserEmail(usernameOrEmail, usernameOrEmail)
        .orElseThrow(() ->
            new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
        );

    return UserPrincipal.create(user);
  }

  @Transactional
  public UserDetails loadUserById(Long id) throws Exception{
    User user = userRepository.findById(id).orElseThrow(
        () -> new Exception("User Detail Not found")
    );

    return UserPrincipal.create(user);
  }
}
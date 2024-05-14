package com.dev.service.impl;

import com.dev.dao.UserDao;
import com.dev.model.UserModel;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> optionalUser = userDao.getUserByUsername(username);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + user.getRole().toString());
            return new User(user.getUsername(), user.getPassword(), Set.of(grantedAuthority));
        } else {
            throw new UsernameNotFoundException("user was not found");
        }
    }
}

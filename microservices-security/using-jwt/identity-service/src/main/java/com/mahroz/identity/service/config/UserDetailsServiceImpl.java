package com.mahroz.identity.service.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Using BCrypt encoded password original value is "abcdef"
     * You can use db repo as well here
     */
    private final List<CustomUserDetails> userList = List.of(new CustomUserDetails("Alex","$2a$10$cZg4tVooPvf1C3kNjU./X.e.TUAE6km0G2oP4xI7xQfGjvB47wwFi"));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userList
                .stream()
                .filter(u->u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }
}

package com.example.main_service.security;

import com.example.data.model.basic.User;
import com.example.data.repository.basic.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CookUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CookUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.
                findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователя с логином " + login + " не существует"));
        return CookUserDetails.build(user);
    }
}

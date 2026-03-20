package com.example.weddingapp.service;

import com.example.weddingapp.entity.User;
import com.example.weddingapp.enums.Role;
import com.example.weddingapp.repository.WebAppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebbAppUserService implements UserDetailsService {

    private final WebAppUserRepository repo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(("User details not found!"))
        );
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().equals(Role.ADMIN) ? "admin" : "read"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public User saveUser(User user) {
        String hashPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPwd);
        return repo.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return repo.findById(id);
    }

    public Optional<User> getByUsername(String username) {
        return repo.findByUsername(username);
    }

    public User login(String username, String password) {
        return repo.findByUsernameAndPassword(username, password)
                .orElse(null);
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}

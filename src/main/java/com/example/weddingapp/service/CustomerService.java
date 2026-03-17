package com.example.weddingapp.service;

import com.example.weddingapp.entity.Customer;
import com.example.weddingapp.enums.Role;
import com.example.weddingapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
public class CustomerService implements UserDetailsService {

    private final CustomerRepository repo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = repo.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(("User details not found!"))
        );
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole().equals(Role.ADMIN) ? "admin" : "read"));
        return new User(customer.getEmail(), customer.getPassword(), authorities);
    }

    public Customer saveUser(Customer customer) {
        String hashPwd = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashPwd);
        return repo.save(customer);
    }

    public Optional<Customer> getUserById(Long id) {
        return repo.findById(id);
    }

    public Optional<Customer> getByUsername(String username) {
        return repo.findByUsername(username);
    }

    public Customer login(String username, String password) {
        return repo.findByUsernameAndPassword(username, password)
                .orElse(null);
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}

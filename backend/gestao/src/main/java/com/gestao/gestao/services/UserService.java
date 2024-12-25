package com.gestao.gestao.services;

import com.gestao.gestao.models.User;
import com.gestao.gestao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }
    
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }
    
        if (!user.getRole().equalsIgnoreCase("professor") && !user.getRole().equalsIgnoreCase("aluno")) {
            return "Invalid role. Allowed roles: 'professor', 'aluno'";
        }
    
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    
        userRepository.save(user);
    
        return "User registered successfully as " + user.getRole();
    }

    public Optional<User> authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                return user;
            }
        }

        return Optional.empty();
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

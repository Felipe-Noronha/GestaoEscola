package com.gestao.gestao.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.gestao.dto.LoginRequestDTO;
import com.gestao.gestao.dto.RegisterRequestDTO;
import com.gestao.gestao.dto.ResponseDTO;
import com.gestao.gestao.models.User;
import com.gestao.gestao.repositories.UserRepository;
import com.gestao.gestao.security.TokenService;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        User user = this.repository.findByEmail(body.username()).orElseThrow(() -> new RuntimeException("User not found"));
        
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            ResponseDTO response = new ResponseDTO(user.getUsername(), token);
            return ResponseEntity.ok(response);
        }
        
        return ResponseEntity.badRequest().build();
    }

    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = this.repository.findByEmail(body.getEmail());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.getPassword()));
            newUser.setEmail(body.getEmail());
            newUser.setUsername(body.getUsername()); 
            newUser.setRole(body.getRole());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            ResponseDTO response = new ResponseDTO(newUser.getUsername(), token);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.badRequest().build();
    }
}

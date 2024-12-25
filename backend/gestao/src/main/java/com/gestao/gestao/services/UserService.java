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

    // Método para registrar um novo usuário
    public String registerUser(User user) {
        // Verificar se o username ou email já existe
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }
    
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }
    
        // Validar o papel (role)
        if (!user.getRole().equalsIgnoreCase("professor") && !user.getRole().equalsIgnoreCase("aluno")) {
            return "Invalid role. Allowed roles: 'professor', 'aluno'";
        }
    
        // Codificar a senha
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    
        // Salvar o usuário
        userRepository.save(user);
    
        return "User registered successfully as " + user.getRole();
    }

    // Método para autenticar o usuário no login
    public Optional<User> authenticateUser(String username, String password) {
        // Busca o usuário pelo username
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            // Verifica se a senha é válida
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                return user; // Login bem-sucedido
            }
        }

        return Optional.empty(); // Credenciais inválidas
    }

    // Método para buscar um usuário por username
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Método para buscar um usuário por email
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

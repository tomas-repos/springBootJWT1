package com.auth.authentication.controller;
import com.auth.authentication.entity.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.auth.authentication.security.JwtUtil;
import com.auth.authentication.repository.UserRepository;
import com.auth.authentication.entity.User;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        System.out.println("In AuthController");
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Logger log = LoggerFactory.getLogger(JwtUtil.class);
        log.info("Entró al login con usuario: {}", loginRequest.getUsername()+" y password"+loginRequest.getPassword());
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        userRepository.save(user);

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.info("login request pass no match"+loginRequest.getPassword());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
        String token = jwtUtil.generateToken(user.getUsername(), "ROLE_" + user.getRole());
        log.info("login request sucess token {}", token);

        return ResponseEntity.ok(Map.of("token", token));
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newToken = jwtUtil.refreshToken(token);
        return ResponseEntity.ok(Map.of("token", newToken));
    }
}

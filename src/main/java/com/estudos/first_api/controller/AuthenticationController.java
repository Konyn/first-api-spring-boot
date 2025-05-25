package com.estudos.first_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.first_api.domain.user.AuthenticationDTO;
import com.estudos.first_api.domain.user.RegisterDTO;
import com.estudos.first_api.domain.user.User;
import com.estudos.first_api.domain.user.UserRepository;
import com.estudos.first_api.infra.TokenService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager manager;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.manager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Validated RegisterDTO data) {
        if (this.repository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(
                encryptedPassword,
                data.name(),
                data.email(),
                data.role());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}

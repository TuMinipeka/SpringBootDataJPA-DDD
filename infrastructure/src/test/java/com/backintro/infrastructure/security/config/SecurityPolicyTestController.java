package com.backintro.infrastructure.security.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SecurityPolicyTestController {

    @PostMapping("/api/auth/login")
    ResponseEntity<Void> login() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/countries")
    ResponseEntity<Void> countries() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/security/users")
    ResponseEntity<Void> users() {
        return ResponseEntity.ok().build();
    }
}

package com.baseProject.cafofo.auth;

import com.baseProject.cafofo.exceptions.UsernameAlreadyInUseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register-owner")
    public ResponseEntity<?> registerOwner(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.registerOwner(request));
        } catch (UsernameAlreadyInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use");
        }
    }
    @PostMapping("/register-customer")
    public ResponseEntity<?> registerCustomer(
            @RequestBody RegisterRequest request){

        try {
            return ResponseEntity.ok(authenticationService.registerCustomer(request));
        } catch (UsernameAlreadyInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(
            @RequestBody RegisterRequest request) {

        try {
            return ResponseEntity.ok(authenticationService.registerAdmin(request));
        } catch (UsernameAlreadyInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use");
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request){

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
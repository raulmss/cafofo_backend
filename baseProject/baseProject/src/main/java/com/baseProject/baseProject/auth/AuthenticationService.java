package com.baseProject.baseProject.auth;

import com.baseProject.baseProject.config.JwtService;
import com.baseProject.baseProject.exceptions.CustomAuthenticationException;
import com.baseProject.baseProject.exceptions.UserNotFoundException;
import com.baseProject.baseProject.user.Role;
import com.baseProject.baseProject.user.User;
import com.baseProject.baseProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        //Build the new user
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getFirstName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        //Save the new user
        userRepository.save(user);

        //Generate the user's Token
        var jwtToken = jwtService.generateToken(user);

        //Return the authentication response containing the JWT Token.
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try{
            //This member will do all the authentication work for me. If email or password are wrong, then an Exception will be thrown.
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }catch (AuthenticationException e){
            throw new CustomAuthenticationException("Authentication failed for user with email: " + request.getEmail(), e);
        }

        //if it gets to this point, user is authenticated, credentials are alright.
        //Then we fetch the User object.
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("No user found with the email: " + request.getEmail()));

        //Generate the user's Token
        var jwtToken = jwtService.generateToken(user);

        //Return the authentication response containing the JWT Token.
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

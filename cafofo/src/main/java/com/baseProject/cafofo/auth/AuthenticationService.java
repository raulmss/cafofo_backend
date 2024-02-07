package com.baseProject.cafofo.auth;

import com.baseProject.cafofo.config.JwtService;
import com.baseProject.cafofo.entity.Customer;
import com.baseProject.cafofo.entity.Owner;
import com.baseProject.cafofo.exceptions.CustomAuthenticationException;
import com.baseProject.cafofo.exceptions.UserNotFoundException;
import com.baseProject.cafofo.exceptions.UsernameAlreadyInUseException;
import com.baseProject.cafofo.repositoy.CustomerRepository;
import com.baseProject.cafofo.repositoy.OwnerRepository;
import com.baseProject.cafofo.user.Role;
import com.baseProject.cafofo.user.User;
import com.baseProject.cafofo.user.UserRepository;
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
    private final CustomerRepository customerRepository;
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerOwner(RegisterRequest request) throws UsernameAlreadyInUseException {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new UsernameAlreadyInUseException("The email: " + request.getEmail() + " is already in use.");
        }
        //Build the new user
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getFirstName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .secretAnswer(passwordEncoder.encode(request.getSecretAnswer()))
                .active(true)
                .role(Role.OWNER)
                .build();

        var owner = Owner.builder()
                .user(user)
                .build();
        //Save the new user
        ownerRepository.save(owner);

        //Generate the user's Token
        var jwtToken = jwtService.generateToken(user);

        //Return the authentication response containing the JWT Token.
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse registerCustomer(RegisterRequest request) throws UsernameAlreadyInUseException {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new UsernameAlreadyInUseException("The email: " + request.getEmail() + " is already in use.");
        }

        //Build the new user
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .secretAnswer(passwordEncoder.encode(request.getSecretAnswer()))
                .active(true)
                .role(Role.CUSTOMER)
                .build();

        var customer = Customer.builder()
                .user(user)
                .build();
        //Save the new user
        customerRepository.save(customer);

        //Generate the user's Token
        var jwtToken = jwtService.generateToken(customer.getUser());

        //Return the authentication response containing the JWT Token.
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse registerAdmin(RegisterRequest request) throws UsernameAlreadyInUseException {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new UsernameAlreadyInUseException("The email: " + request.getEmail() + " is already in use.");
        }
        //Build the new user
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getFirstName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .secretAnswer(passwordEncoder.encode(request.getSecretAnswer()))
                .active(true)
                .role(Role.ADMIN)
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

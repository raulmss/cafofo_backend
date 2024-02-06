package com.baseProject.cafofo.service;

import com.baseProject.cafofo.exceptions.UserNotFoundException;
import com.baseProject.cafofo.user.User;
import com.baseProject.cafofo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public String resetUserPassword(long userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return "Password changed successfully";
    }

    public String userChangeUserPassword(String email, String answer, String newPassword){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (passwordEncoder.encode(user.getSecretAnswer()).equals(answer)){
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }
        return "Password changed successfully";
    }


}

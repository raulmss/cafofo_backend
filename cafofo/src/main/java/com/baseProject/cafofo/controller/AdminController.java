package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/reset-password")
    public ResponseEntity<String> changeUserPassword(long userId, String newPassword) {
        return ResponseEntity.ok(adminService.resetUserPassword(userId, newPassword));
    }
}

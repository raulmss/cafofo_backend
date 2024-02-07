package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.entity.DTO.ChangePassWordDTO;
import com.baseProject.cafofo.service.AdminService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommonController {

    private final AdminService adminService;

    @PreAuthorize("permitAll()")
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ChangePassWordDTO changePassWordDTO){
        return ResponseEntity.ok(adminService
                .userChangeUserPassword(changePassWordDTO.getEmail(),
                changePassWordDTO.getSecretAnswer(),
                changePassWordDTO.getPassword()));
    }
}

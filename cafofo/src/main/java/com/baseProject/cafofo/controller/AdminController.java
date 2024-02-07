package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.entity.DTO.AdminChangesPasswordDTO;
import com.baseProject.cafofo.service.AdminService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/reset-password")
    public ResponseEntity<String> changeUserPassword(@RequestBody AdminChangesPasswordDTO acp) {
        return ResponseEntity.ok(adminService.resetUserPassword(acp.getUserId(), acp.getNewPassword()));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/change-activation-user/{id}")
    public ResponseEntity<String> deactivateUser(@PathVariable long id) {
        return ResponseEntity.ok(adminService.changeActiveStatus(id));
    }
}

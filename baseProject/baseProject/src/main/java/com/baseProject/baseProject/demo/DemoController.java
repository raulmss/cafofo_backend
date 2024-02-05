package com.baseProject.baseProject.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    //Methods to demonstrate security working
    //Open to all authenticate
    @GetMapping("/all")
    public ResponseEntity<String> demo(){
        return ResponseEntity.ok("SECURED ENTITY: All Authenticated.");
    }

    //Open to user only
    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> demoUser(){
        return ResponseEntity.ok("SECURED ENTITY: Users only.");
    }


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> demoAdmin(){
        return ResponseEntity.ok("SECURED ENTITY: Admin Only.");
    }

    //Open to admin only

}

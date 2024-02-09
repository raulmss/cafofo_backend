package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.entity.DTO.AdminChangesPasswordDTO;
import com.baseProject.cafofo.service.AdminService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.baseProject.cafofo.config.URLConstants;
import com.baseProject.cafofo.dto.UserDto;
import org.springframework.http.HttpStatus;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/properties-to-be-approved")
    public ResponseEntity<?> findPropertiesTOBeApproved(){
        return ResponseEntity.ok(adminService.findPropertiesTOBeApproved());
    }
  
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public Collection<UserDto> findAllUsers(){
        System.out.println("inside findAllUsers method controller");
        return adminService.findAllUsers();
    }


    @PreAuthorize("hasAuthority('ADMIN')")
   @GetMapping("/customers")
    public Collection<UserDto> findAllCustomers(){
        System.out.println("inside findAllCustomers method controller");
        return adminService.findAllCustomers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/owners")
    public Collection<UserDto> findAllOwners(){
        System.out.println("inside findAllOwners method controller");
        return adminService.findAllOwners();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    //change customer inactive
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/customers/{id}")
    public void updateCustomer(@PathVariable("id") Long customerId){
        System.out.println("inside updateCustomer method controller");
        adminService.updateCustomer(customerId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    //change owner inactive
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/owners/{id}")
    public void updateOwner(@PathVariable("id") Long ownerId){
        System.out.println("inside updateOwner method controller");
        adminService.updateOwner(ownerId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("approve-owner-property/{id}")
    public void approveProperty(@PathVariable("id") Long propertyId){
        System.out.println("inside approveProperty method controller");
        adminService.approveProperty(propertyId);
    }
}

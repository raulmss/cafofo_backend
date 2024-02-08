package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.dto.FavouriteDto;
import com.baseProject.cafofo.dto.OfferRequestDto;
import com.baseProject.cafofo.entity.Offer;
import com.baseProject.cafofo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService customerService;

    @PreAuthorize("hasAuthority('CUSTOMER')")
   @PostMapping("/customers/{id}/offers")
    @ResponseStatus(HttpStatus.CREATED)
    void save(@PathVariable("id") Long customerId, @RequestBody OfferRequestDto offerRequest){
        System.out.println("inside save method controller");
        customerService.save(customerId, offerRequest);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("customers/{userId}/favorite-lists")
    public ResponseEntity<String> addToFavorites(@RequestParam Long propertyId, @PathVariable Long userId) {
        String result = customerService.addToFavorites(userId, propertyId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("customers/{userId}/favorite-lists")
    // @GetMapping("customers/{customerId}/favorite-lists")
    public ResponseEntity<List<FavouriteDto>> getFavorites(@PathVariable Long userId) {
        List<FavouriteDto> favorites = customerService.getFavorites(userId);
        return favorites != null ?
                ResponseEntity.ok(favorites) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @DeleteMapping("customers/{userId}/favorite-lists")
    // @DeleteMapping("customers/{customerId}/favorite-lists")
    public ResponseEntity<String> removeFromFavorites(@RequestParam Long propertyId, @PathVariable Long userId) {
        String result = customerService.removeFromFavorites(userId, propertyId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("customers/{userId}/offers")
    public ResponseEntity<List<Offer>> getOffersByUser(@PathVariable Long userId) {
        List<Offer> offers = customerService.getOffersByUser(userId);
        return offers!=null ?
                ResponseEntity.ok(offers):
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @DeleteMapping("customers/{userId}/offers")
    public ResponseEntity<String> cancelOffer(@PathVariable Long userId, @RequestParam Long offerId) {
        String result = customerService.cancelOffer(offerId, userId);
        return result!=null ?
                ResponseEntity.ok(result):
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PatchMapping("customers/{userId}/offers/{offerId}")
    public ResponseEntity<String> maintainOfferByPrice(@PathVariable Long userId,@PathVariable Long offerId, @RequestParam double price) {
        String result = customerService.maintainOfferByPrice(offerId, price, userId);
        return result!=null ?
                ResponseEntity.ok(result):
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}

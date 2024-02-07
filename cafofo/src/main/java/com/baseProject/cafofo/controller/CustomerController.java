package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.DTO.FavouriteDto;
import com.baseProject.cafofo.entity.Offer;
import com.baseProject.cafofo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping("customers/{userId}/favorite-lists")
    // @PostMapping("customers/{customerId}/favorite-lists")
    public ResponseEntity<String> addToFavorites(@RequestParam Long propertyId, @PathVariable Long userId) {
        String result = customerService.addToFavorites(userId, propertyId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("customers/{userId}/favorite-lists")
    // @GetMapping("customers/{customerId}/favorite-lists")
    public ResponseEntity<List<FavouriteDto>> getFavorites(@PathVariable Long userId) {
        List<FavouriteDto> favorites = customerService.getFavorites(userId);
        return favorites != null ?
                ResponseEntity.ok(favorites) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("customers/{userId}/favorite-lists")
    // @DeleteMapping("customers/{customerId}/favorite-lists")
    public ResponseEntity<String> removeFromFavorites(@RequestParam Long propertyId, @PathVariable Long userId) {
        String result = customerService.removeFromFavorites(userId, propertyId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("customers/{userId}/offers")
    // @GetMapping("customers/{customerId}/offers")
    public ResponseEntity<List<Offer>> getOffersByUser(@PathVariable Long userId) {
        List<Offer> offers = customerService.getOffersByUser(userId);
        return offers!=null ?
                ResponseEntity.ok(offers):
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("customers/{userId}/offers")
    //@PutMapping("customers/{customerId}/offers")
    public ResponseEntity<String> cancelOffer(@PathVariable Long userId, @RequestParam Long offerId) {
        String result = customerService.cancelOffer(offerId, userId);
        return result!=null ?
                ResponseEntity.ok(result):
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("customers/{userId}/offers/{offerId}")
    public ResponseEntity<String> maintainOfferByPrice(@PathVariable Long userId,@PathVariable Long offerId, @RequestParam double price) {
        String result = customerService.maintainOfferByPrice(offerId, price, userId);
        return result!=null ?
                ResponseEntity.ok(result):
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

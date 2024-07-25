package Lab14Part1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class Shop {

    @GetMapping("/shop")
    public ResponseEntity<?> getShop(){
        return new ResponseEntity<String>("Shop method", HttpStatus.OK);
    }

    @GetMapping("/orders")
    @PreAuthorize("hasRole('sales') or hasRole('finance')")
    public ResponseEntity<?> getOrders(){
        return new ResponseEntity<String>("Orders method", HttpStatus.OK);
    }

    @GetMapping("/payments")
    @PreAuthorize("hasRole('finance')")
    public ResponseEntity<?> getPayments(){
        return new ResponseEntity<String>("Payments method", HttpStatus.OK);
    }
    
}

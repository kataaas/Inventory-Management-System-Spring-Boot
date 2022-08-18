package ru.kataaas.ims.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kataaas.ims.dto.RegisterVendorDTO;
import ru.kataaas.ims.service.VendorService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/vendor")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createVendor(@Valid @RequestBody RegisterVendorDTO registerDTO) {
        if (vendorService.checkIfPhoneNumberAlreadyUsed(registerDTO.getName())) {
            return ResponseEntity.badRequest().body("The name is already in use");
        }
        if (vendorService.checkIfEmailAlreadyUsed(registerDTO.getEmail())) {
            return ResponseEntity.badRequest().body("The email is already in use");
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(vendorService.create(registerDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(e.getMessage());
        }
    }

}

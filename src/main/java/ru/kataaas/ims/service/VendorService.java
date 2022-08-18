package ru.kataaas.ims.service;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.repository.VendorRepository;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

}

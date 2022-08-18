package ru.kataaas.ims.service;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.RegisterVendorDTO;
import ru.kataaas.ims.dto.VendorDTO;
import ru.kataaas.ims.entity.VendorEntity;
import ru.kataaas.ims.repository.VendorRepository;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public VendorDTO create(RegisterVendorDTO registerVendorDTO) {
        VendorEntity vendor = new VendorEntity();
        vendor.setName(registerVendorDTO.getName());
        vendor.setEmail(registerVendorDTO.getEmail());
        vendor.setPassword(registerVendorDTO.getPassword());

        VendorEntity savedVendor = vendorRepository.save(vendor);
        return new VendorDTO(savedVendor.getId(), savedVendor.getName(),
                savedVendor.getEmail(), savedVendor.getCreatedAt(), null);
    }

    public boolean checkIfPhoneNumberAlreadyUsed(String phoneNumber) {
        return vendorRepository.existsByName(phoneNumber);
    }

    public boolean checkIfEmailAlreadyUsed(String email) {
        return vendorRepository.existsByEmail(email);
    }

}

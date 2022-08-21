package ru.kataaas.ims.service;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.RegisterVendorDTO;
import ru.kataaas.ims.dto.VendorDTO;
import ru.kataaas.ims.entity.VendorEntity;
import ru.kataaas.ims.mapper.VendorMapper;
import ru.kataaas.ims.repository.VendorRepository;

import java.util.Optional;

@Service
public class VendorService {

    private final VendorMapper vendorMapper;

    private final VendorRepository vendorRepository;

    public VendorService(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    public VendorEntity findById(Long id) {
        return vendorRepository.findById(id).orElse(null);
    }

    public VendorDTO findByName(String name) {
        Optional<VendorEntity> vendor = vendorRepository.findByName(name);
        return vendor.map(vendorMapper::toVendorDTO).orElse(null);
    }

    public VendorDTO create(RegisterVendorDTO registerVendorDTO) {
        VendorEntity vendor = new VendorEntity();
        vendor.setName(registerVendorDTO.getName());
        vendor.setEmail(registerVendorDTO.getEmail());
        vendor.setPassword(registerVendorDTO.getPassword());

        VendorEntity savedVendor = vendorRepository.save(vendor);
        return vendorMapper.toVendorDTO(savedVendor);
    }

    public boolean checkIfPhoneNumberAlreadyUsed(String phoneNumber) {
        return vendorRepository.existsByName(phoneNumber);
    }

    public boolean checkIfEmailAlreadyUsed(String email) {
        return vendorRepository.existsByEmail(email);
    }

}

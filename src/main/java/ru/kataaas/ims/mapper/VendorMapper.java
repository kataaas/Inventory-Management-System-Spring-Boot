package ru.kataaas.ims.mapper;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.AuthUserResponse;
import ru.kataaas.ims.dto.VendorDTO;
import ru.kataaas.ims.entity.UserEntity;
import ru.kataaas.ims.entity.VendorEntity;

@Service
public class VendorMapper {

    public VendorDTO toVendorDTO(VendorEntity vendor) {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(vendor.getId());
        vendorDTO.setName(vendor.getName());
        vendorDTO.setEmail(vendor.getEmail());
        vendorDTO.setCreatedAt(vendor.getCreatedAt());
        vendorDTO.setProducts(vendor.getProducts().size());
        return vendorDTO;
    }

    public AuthUserResponse toAuthResponse(VendorEntity vendor, String token) {
        AuthUserResponse authUserResponse = new AuthUserResponse();
        authUserResponse.setId(vendor.getId());
        authUserResponse.setLogin(vendor.getEmail());
        authUserResponse.setAccessToken(token);
        return authUserResponse;
    }

}

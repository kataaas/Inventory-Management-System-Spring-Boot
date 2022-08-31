package ru.kataaas.ims.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kataaas.ims.entity.UserEntity;
import ru.kataaas.ims.entity.VendorEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomPersonDetailsService implements UserDetailsService {

    private final UserService userService;

    private final VendorService vendorService;

    public CustomPersonDetailsService(UserService userService, VendorService vendorService) {
        this.userService = userService;
        this.vendorService = vendorService;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumberAndRole) throws UsernameNotFoundException {
        String[] arr = phoneNumberAndRole.split(":");
        String phoneNumber = arr[0];
        String role = arr[1];
        System.out.println(phoneNumber + " " + role);
        if (role.equalsIgnoreCase("USER")) {
            UserEntity user = userService.findByPhoneNumber(phoneNumber);
            if (user == null) throw new UsernameNotFoundException(phoneNumber);
            return new User(phoneNumberAndRole, user.getPassword(), mapRolesToAuthorities("ROLE_USER"));
        }
        if (role.equalsIgnoreCase("VENDOR")) {
            VendorEntity vendor = vendorService.findByPhoneNumber(phoneNumber);
            if (vendor == null) throw new UsernameNotFoundException(phoneNumber);
            return new User(phoneNumberAndRole, vendor.getPassword(), mapRolesToAuthorities("ROLE_VENDOR"));
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(role));
        return list;
    }
}

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
    public UserDetails loadUserByUsername(String phoneNumberOrEmail) throws UsernameNotFoundException {
        if (phoneNumberOrEmail.matches("\\+7-\\d{3}-\\d{3}-\\d{2}-\\d{2}$")) {
            UserEntity user = userService.findByPhoneNumber(phoneNumberOrEmail);
            if (user == null) throw new UsernameNotFoundException(phoneNumberOrEmail);
            return new User(user.getPhoneNumber(), user.getPassword(), mapRolesToAuthorities("ROLE_USER"));
        }
        if (phoneNumberOrEmail.matches("^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,6}$")) {
            VendorEntity vendor = vendorService.findByEmail(phoneNumberOrEmail);
            if (vendor == null) throw new UsernameNotFoundException(phoneNumberOrEmail);
            return new User(vendor.getEmail(), vendor.getPassword(), mapRolesToAuthorities("ROLE_VENDOR"));
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(role));
        return list;
    }
}

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
import ru.kataaas.ims.utils.ERole;

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
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity user = userService.findByPhoneNumber(login);
        if (user != null) {
            return new User(login, user.getPassword(), mapRolesToAuthorities("ROLE_USER"));
        }
        VendorEntity vendor = vendorService.findByEmail(login);
        if (vendor != null) {
            return new User(login, vendor.getPassword(), mapRolesToAuthorities("ROLE_VENDOR"));
        }
        throw new UsernameNotFoundException(login);
    }

    public UserDetails loadByPhoneNumberAndRole(String phoneNumber, ERole role) throws UsernameNotFoundException {
        if (role == ERole.ROLE_USER) {
            UserEntity user = userService.findByPhoneNumber(phoneNumber);
            if (user == null) throw new UsernameNotFoundException(phoneNumber);
            return new User(phoneNumber, user.getPassword(), mapRolesToAuthorities(role.name()));
        }
        if (role == ERole.ROLE_VENDOR) {
            VendorEntity vendor = vendorService.findByPhoneNumber(phoneNumber);
            if (vendor == null) throw new UsernameNotFoundException(phoneNumber);
            return new User(phoneNumber, vendor.getPassword(), mapRolesToAuthorities(role.name()));
        }
        throw new UsernameNotFoundException(phoneNumber);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(role));
        return list;
    }
}

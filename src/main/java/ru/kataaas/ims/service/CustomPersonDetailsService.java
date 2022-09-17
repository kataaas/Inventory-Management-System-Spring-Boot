package ru.kataaas.ims.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kataaas.ims.entity.EmployeeEntity;
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

    private final EmployeeService employeeService;

    public CustomPersonDetailsService(UserService userService, VendorService vendorService, EmployeeService employeeService) {
        this.userService = userService;
        this.vendorService = vendorService;
        this.employeeService = employeeService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity user = userService.findByPhoneNumber(login);
        if (user != null) {
            return new User(login, user.getPassword(), mapRolesToAuthorities(ERole.ROLE_USER.name()));
        }
        VendorEntity vendor = vendorService.findByPhoneNumber(login);
        if (vendor != null) {
            return new User(login, vendor.getPassword(), mapRolesToAuthorities(ERole.ROLE_VENDOR.name()));
        }
        throw new UsernameNotFoundException(login);
    }

    public UserDetails loadByLoginAndRole(String login, ERole role) throws UsernameNotFoundException {
        if (role == ERole.ROLE_USER) {
            UserEntity user = userService.findByPhoneNumber(login);
            if (user == null) throw new UsernameNotFoundException(login);
            return new User(login, user.getPassword(), mapRolesToAuthorities(role.name()));
        }
        if (role == ERole.ROLE_VENDOR) {
            VendorEntity vendor = vendorService.findByPhoneNumber(login);
            if (vendor == null) throw new UsernameNotFoundException(login);
            return new User(login, vendor.getPassword(), mapRolesToAuthorities(role.name()));
        }
        if (role == ERole.ROLE_EMPLOYEE) {
            EmployeeEntity employee = employeeService.findByLogin(login);
            if (employee == null) throw new UsernameNotFoundException(login);
            return new User(login, employee.getPassword(), mapRolesToAuthorities(role.name()));
        }
        throw new UsernameNotFoundException(login);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(role));
        return list;
    }
}

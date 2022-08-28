package ru.kataaas.ims.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kataaas.ims.entity.RoleEntity;
import ru.kataaas.ims.entity.UserEntity;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumberOrEmail) throws UsernameNotFoundException {
        UserEntity user = userService.findByPhoneNumberOrEmail(phoneNumberOrEmail, phoneNumberOrEmail);
        if (user == null) throw new UsernameNotFoundException(phoneNumberOrEmail);
        return new User(user.getPhoneNumber(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<RoleEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}

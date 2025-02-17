package com.cusbservice.authorization.model.custom;

import com.cusbservice.authorization.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails extends User implements UserDetails {

    private final UserDTO userDTO;

    public CustomUserDetails(UserDTO userDTO) {
        super(userDTO.getUsername(), userDTO.getPassword(), userDTO.getRoles().stream()
                .map(rule -> new SimpleGrantedAuthority(rule.getRoleName()))
                .collect(Collectors.toList()));
        this.userDTO = userDTO;
    }

    @Override
    public String getUsername() {
        return userDTO.getUsername();
    }

    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDTO getUserModel() {
        return userDTO;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return userDTO.getRoles().stream()
                .map(rule -> new SimpleGrantedAuthority(rule.getRoleName()))
                .collect(Collectors.toSet());
    }
}

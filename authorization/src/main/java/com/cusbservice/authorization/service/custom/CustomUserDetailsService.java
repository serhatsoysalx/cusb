package com.cusbservice.authorization.service.custom;

import com.cusbservice.authorization.dto.UserDTO;
import com.cusbservice.authorization.entity.User;
import com.cusbservice.authorization.general.GeneralService;
import com.cusbservice.authorization.model.custom.CustomUserDetails;
import com.cusbservice.authorization.repository.IUserRepository;
import com.cusbservice.authorization.util.constants.LoginErrorMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService extends GeneralService<IUserRepository> implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> userOpt = repository.findByUsername(username);

        if (userOpt.isPresent()) {
            return new CustomUserDetails(modelMapper.map(userOpt.get(), UserDTO.class));
        } else {
            throw new UsernameNotFoundException(LoginErrorMessage.USER_NOT_FOUND + " : " + username);
        }
    }

    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}

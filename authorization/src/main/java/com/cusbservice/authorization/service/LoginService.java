package com.cusbservice.authorization.service;

import com.cusbservice.authorization.dto.LoginRequestDTO;
import com.cusbservice.authorization.dto.RoleDTO;
import com.cusbservice.authorization.dto.UserDTO;
import com.cusbservice.authorization.entity.User;
import com.cusbservice.authorization.general.GeneralService;
import com.cusbservice.authorization.repository.IUserRepository;
import com.cusbservice.authorization.service.custom.CustomUserDetailsService;
import com.cusbservice.authorization.util.constants.LoginErrorMessage;
import com.cusbservice.authorization.util.expections.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoginService extends GeneralService<IUserRepository> {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public UserDTO login(LoginRequestDTO loginRequestDTO) {
        final UserDetails customUser = customUserDetailsService.loadUserByUsername(loginRequestDTO.getUsername());

        if (Objects.nonNull(customUser) && passwordEncoder.matches(loginRequestDTO.getPassword(), customUser.getPassword())) {
            Optional<User> user = repository.findByUsername(customUser.getUsername());

            if (user.isPresent()) {
                User userEntity = user.get();
                UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
                Set<RoleDTO> rolesDto = userEntity.getRoles().stream()
                        .map(userRoleEntity -> modelMapper.map(userRoleEntity, RoleDTO.class))
                        .collect(Collectors.toSet());
                userDTO.setRoles(rolesDto);

                return userDTO;
            }
        }

        throw new InvalidCredentialsException(LoginErrorMessage.INVALID_CREDENTIALS);
    }
}

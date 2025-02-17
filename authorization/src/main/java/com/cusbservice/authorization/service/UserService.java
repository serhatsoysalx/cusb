package com.cusbservice.authorization.service;

import com.cusbservice.authorization.dto.UserDTO;
import com.cusbservice.authorization.entity.User;
import com.cusbservice.authorization.general.GeneralService;
import com.cusbservice.authorization.repository.IUserRepository;
import com.cusbservice.authorization.util.constants.LoginErrorMessage;
import com.cusbservice.authorization.util.expections.UserAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GeneralService<IUserRepository> {

    @Autowired
    private RoleService roleService;

    @Transactional
    public UserDTO registerUser(UserDTO userDTO) {
        if (userExists(userDTO)) {
            throw new UserAlreadyExistsException(LoginErrorMessage.USER_ALREADY_EXISTS);
        }

        if (!roleService.existsRole(userDTO.getRoles())) {
            throw new UserAlreadyExistsException(LoginErrorMessage.ROLE_ID_NOT_FOUND);
        }
        userDTO.setPassword(encodePassword(userDTO.getPassword()));
        userDTO.setRoles(roleService.getUserRoleFromEntity(userDTO.getRoles()));
        User userEntity = modelMapper.map(userDTO, User.class);
        final User savedUser = repository.save(userEntity);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    private boolean userExists(UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty() ||
                userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            return true;
        }
        final boolean username = repository.existsByUsername(userDTO.getUsername());
        final boolean email = repository.existsByEmail(userDTO.getEmail());
        return username || email;
    }

}

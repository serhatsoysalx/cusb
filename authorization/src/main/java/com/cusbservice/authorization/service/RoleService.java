package com.cusbservice.authorization.service;

import com.cusbservice.authorization.dto.RoleDTO;
import com.cusbservice.authorization.entity.Role;
import com.cusbservice.authorization.general.GeneralService;
import com.cusbservice.authorization.repository.IRoleRepository;
import com.cusbservice.authorization.util.constants.LoginErrorMessage;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService extends GeneralService<IRoleRepository> {

    @Autowired
    private EntityManager entityManager;

    public Set<RoleDTO> getListOfRoles() {
        final List<Role> roles = repository.findAll();
        if (!roles.isEmpty()) {
            return roles.stream().map(role ->
                            modelMapper.map(role, RoleDTO.class))
                    .collect(Collectors.toCollection(HashSet::new));
        }
        return null;
    }

    private Set<Role> getRoleEntites(Set<RoleDTO> roleDTOs) {
        return roleDTOs.stream().map(roleDTO -> {
            Role role = repository.findById(roleDTO.getId())
                    .orElseThrow(() -> new IllegalArgumentException(LoginErrorMessage.ROLE_NOT_FOUND));
            return entityManager.merge(role);
        }).collect(Collectors.toSet());
    }

    public Set<RoleDTO> getUserRoleFromEntity(Set<RoleDTO> roleDTO) {
        Set<RoleDTO> roles = new HashSet<>();
        getRoleEntites(roleDTO).forEach(role -> {
            roles.add(modelMapper.map(role, RoleDTO.class));
        });
        return roles;
    }

    public boolean existsRole(Set<RoleDTO> roles) {
        if (roles == null || roles.isEmpty()) {
            return false;
        }

        for (RoleDTO role : roles) {
            if (!repository.existsById(role.getId())) {
                return false;
            }
        }
        return true;
    }

}

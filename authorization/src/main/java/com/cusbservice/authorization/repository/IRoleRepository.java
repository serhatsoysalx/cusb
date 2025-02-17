package com.cusbservice.authorization.repository;

import com.cusbservice.authorization.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    boolean existsByRoleName(String roleName);
}

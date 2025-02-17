package com.cusbservice.authorization.dto;

import java.io.Serializable;

public class RoleDTO implements Serializable {

    private Long id;
    private String roleName;
    private Boolean status;

    public RoleDTO() {
    }

    public RoleDTO(Long id,
                   String roleName,
                   Boolean status) {
        this.id = id;
        this.roleName = roleName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

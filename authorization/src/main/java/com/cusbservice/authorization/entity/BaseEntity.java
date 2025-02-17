package com.cusbservice.authorization.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "status")
    private Boolean status;

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = Boolean.TRUE;
        }
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}


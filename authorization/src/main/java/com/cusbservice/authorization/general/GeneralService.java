package com.cusbservice.authorization.general;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class GeneralService<R> {

    protected static final Logger logger = LoggerFactory.getLogger(GeneralService.class);

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected R repository;

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}

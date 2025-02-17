package com.cusbservice.authorization.aop;

import com.cusbservice.authorization.dto.UserDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UsersControllerAOP {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.cusbservice.authorization.controller.UserController.registerUser(..)) && args(userDTO)")
    public void logBeforeSignup(JoinPoint joinPoint, UserDTO userDTO) {
        String username = userDTO.getUsername();
        logger.info("Signup request received for username: {}", username);
    }

    @After("execution(* com.cusbservice.authorization.controller.UserController.registerUser(..))")
    public void logAfterSignup(JoinPoint joinPoint) {
        logger.info("Signup process completed successfully.");
    }

    @AfterThrowing(pointcut = "execution(* com.cusbservice.authorization.controller.UserController.registerUser(..))", throwing = "exception")
    public void logAfterThrowingSignup(JoinPoint joinPoint, Throwable exception) {
        String username = ((UserDTO) joinPoint.getArgs()[0]).getUsername();
        logger.error("Signup failed for username: {}. Error: {}", username, exception.getMessage());
    }

}

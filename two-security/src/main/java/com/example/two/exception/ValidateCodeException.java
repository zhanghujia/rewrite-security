package com.example.two.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author 10696
 * @since 2020/11/30 15:29
 */

public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -5766425614461223095L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}

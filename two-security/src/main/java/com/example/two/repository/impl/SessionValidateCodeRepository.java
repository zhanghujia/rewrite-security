package com.example.two.repository.impl;

import com.example.two.validate.ValidateCode;
import com.example.two.validate.ValidateCodeRepository;
import com.example.two.validate.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author 10696
 * @since 2020/11/25 17:06
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {


    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 操作Session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(request, getSessionKey(request, validateCodeType), code);
    }

    private String getSessionKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return ((ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(request, validateCodeType)));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(request, validateCodeType));
    }


}

package com.example.two.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 10696
 * @since 2020/11/24 17:20
 */
@Component
@ConfigurationProperties(prefix = "two.security")
public class SecurityProperties {

    private ValidateCodeProperties code = new ValidateCodeProperties();


    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }


}

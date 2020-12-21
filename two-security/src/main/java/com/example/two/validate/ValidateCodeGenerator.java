package com.example.two.validate;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author 10696
 * @since 2020/11/24 17:13
 */

public interface ValidateCodeGenerator {

    /**
     * code生成器
     *
     * @param request 请求
     * @return 实例对象
     */
    ValidateCode generate(ServletWebRequest request);
}

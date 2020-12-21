package com.example.two.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author 10696
 * @since 2020/11/24 17:46
 */

public interface ValidateCodeProcessor {

    /**
     * 创建校验码
     *
     * @param request 请求
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验校验码
     *
     * @param request 请求
     */
    void validate(ServletWebRequest request);

}

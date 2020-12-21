package com.example.two.repository.impl;

import com.example.two.validate.ValidateCode;
import com.example.two.validate.ValidateCodeRepository;
import com.example.two.validate.ValidateCodeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @author 10696
 * @since 2020/11/25 17:05
 */

public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        redisTemplate.opsForValue().set(buildKey(request, validateCodeType), code, 30, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        Object o = redisTemplate.opsForValue().get(buildKey(request, validateCodeType));
        if (o == null) {
            return null;
        }
        return ((ValidateCode) o);
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        redisTemplate.delete(buildKey(request, validateCodeType));
    }


    private String buildKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String deviceKey = request.getHeader("deviceKey");
        if (StringUtils.isBlank(deviceKey)) {
            throw new RuntimeException("请在请求头中携带deviceKey参数");
        }
        return "code:" + validateCodeType.toString().toLowerCase() + ":" + deviceKey;
    }

}

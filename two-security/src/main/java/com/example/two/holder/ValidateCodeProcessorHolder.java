package com.example.two.holder;

import com.example.two.validate.ValidateCodeProcessor;
import com.example.two.validate.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 10696
 * @since 2020/11/30 10:35
 */
@Component("validateCodeProcessorHolder")
public class ValidateCodeProcessorHolder {

    /**
     * 依赖搜索
     * <p>
     * Spring启动时，会查找容器中所有的ValidateCodeProcessor接口的实现
     * 并把Bean的名字作为key，放到Map中
     */
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;


    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new RuntimeException("验证码处理器" + name + "不存在");
        }

        return processor;
    }


}

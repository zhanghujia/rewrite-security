package com.example.two.validate;

import com.example.two.constants.SecurityConstants;

/**
 * @author 10696
 * @since 2020/11/25 16:31
 */

public enum ValidateCodeType {


    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },

    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };


    /**
     * 校验时从请求中获取参数的名字
     *
     * @return
     */
    public abstract String getParamNameOnValidate();


}

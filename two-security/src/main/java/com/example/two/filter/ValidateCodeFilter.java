package com.example.two.filter;

import com.example.two.constants.SecurityConstants;
import com.example.two.exception.ValidateCodeException;
import com.example.two.holder.ValidateCodeProcessorHolder;
import com.example.two.properties.SecurityProperties;
import com.example.two.validate.ValidateCodeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 10696
 * @since 2020/11/30 10:29
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 存放所有需要验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrls(), ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);

    }

    private void addUrlToMap(String urls, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urls)) {
            String[] urlStr = StringUtils.splitByWholeSeparatorPreserveAllTokens(urls, ",");
            for (String s : urlStr) {
                urlMap.put(s, type);
            }
        }

    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        ValidateCodeType type = getValidateCodeType(httpServletRequest);
        if (type != null) {
            logger.info("校验请求(" + httpServletRequest.getRequestURL() + ")中的验证码类型 " + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type).validate(
                        new ServletWebRequest(httpServletRequest, httpServletResponse));
                logger.info("验证码校验通过");
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    /**
     * 获取验证码的类型 如果请求不需要校验则返回null
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> strings = urlMap.keySet();
            for (String string : strings) {
                if (pathMatcher.match(string, request.getRequestURI())) {
                    result = urlMap.get(string);
                }
            }
        }
        return result;

    }

}

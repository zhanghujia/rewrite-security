package com.example.two.validate.image;

import com.example.two.validate.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author 10696
 * @since 2020/11/30 10:12
 */

@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * 发送图形验证码，将其写到响应中
     *
     * @param request 请求
     * @param validateCode 验证码
     * @throws Exception 异常
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
        assert request.getResponse() != null;
        ImageIO.write(validateCode.getBufferedImage(), "JPEG", request.getResponse().getOutputStream());
    }

}

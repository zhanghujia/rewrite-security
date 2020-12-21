package com.example.two.validate.image;

import com.example.two.validate.ValidateCode;

import java.awt.image.BufferedImage;

/**
 * @author 10696
 * @since 2020/11/24 17:04
 */

public class ImageCode extends ValidateCode {

    private BufferedImage bufferedImage;

    public ImageCode(BufferedImage bufferedImage, String code, int expireIn) {
        super(code, expireIn);
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

}

package com.example.two.properties;

/**
 * @author 10696
 * @since 2020/11/24 17:27
 */

public class ImageCodeProperties extends CodeProperties {

    private int width = 67;

    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

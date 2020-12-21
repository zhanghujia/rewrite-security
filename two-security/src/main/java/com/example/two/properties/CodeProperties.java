package com.example.two.properties;

/**
 * @author 10696
 * @since 2020/11/24 17:23
 */

public class CodeProperties {

    private int length = 6;

    private int expiredIn = 60;

    private String urls;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(int expiredIn) {
        this.expiredIn = expiredIn;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
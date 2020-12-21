package com.example.one.properties;

import com.example.one.enums.LoginType;
import org.springframework.stereotype.Component;

import static com.example.one.enums.LoginType.JSON;

/**
 * @author 10696
 * @since 2020/11/23 17:43
 */

@Component
public class BrowserProperties {

    private String loginPage = "/one-signIn.html";

    private LoginType loginType = JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }


}

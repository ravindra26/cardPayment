package com.service1.pojo;

/**
 *
 * @author Ravindra
 */
public class LoginPacket {

    private String username;
    private String password;
    private long loginTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }
}

package com.gupta.udapi.json;

/**
 * @author amitkumargupta
 */
public class LoginDetailsJson {

    private String userName;
    private String password;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginDetailsJson{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


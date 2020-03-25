package com.gupta.udapi.json;

/**
 * @author amitkumargupta
 */
public class SignUpDetailsJson {

    private String userName;
    private String password;
    private Integer type;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SignUpDetailsJson{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

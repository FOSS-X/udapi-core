package com.gupta.udapi.dtos;

/**
 * @author amitkumargupta
 */
public class UdapiUserDto {

    private String userName;
    private String passwordHash;
    private Integer type;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UdapiUserDto{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", passwordHash='").append(passwordHash).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    public UdapiUserDto(String userName,
                        String passwordHash,
                        Integer type) {

        this.userName = userName;
        this.passwordHash = passwordHash;
        this.type = type;
    }

    public static class Builder {

        private String userName;
        private String passwordHash;
        private Integer type;

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public Builder setType(Integer type) {
            this.type = type;
            return this;
        }

        public UdapiUserDto build() {
            return new UdapiUserDto(
                    userName,
                    passwordHash,
                    type
            );
        }
    }

    public void setPasswordHashToEmpty() {
        this.passwordHash = " ";
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Integer getType() {
        return type;
    }
}

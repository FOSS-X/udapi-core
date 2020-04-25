package com.gupta.udapi.dtos;

/**
 * @author amitkumargupta
 */
public class DbConfigDto {

    private String dbName;
    private String userName;
    private String password;
    private Integer port;
    private String ip;
    private Byte type;

    public DbConfigDto(String dbName, String userName, String password, Integer port, String ip, Byte type) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
        this.port = port;
        this.ip = ip;
        this.type = type;
    }

    public static class Builder {
        private String dbName;
        private String userName;
        private String password;
        private Integer port;
        private String ip;
        private Byte type;

        public Builder setType(Byte type) {
            this.type = type;
            return this;
        }

        public Builder setDbName(String dbName) {
            this.dbName = dbName;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setPort(Integer port) {
            this.port = port;
            return this;
        }

        public Builder setIp(String ip) {
            this.ip = ip;
            return this;
        }

        public DbConfigDto build() {
            return new DbConfigDto(
                    dbName,
                    userName,
                    password,
                    port,
                    ip,
                    type
            );
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DbConfigDto{");
        sb.append("dbName='").append(dbName).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", port=").append(port);
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    public Byte getType() {
        return type;
    }
}

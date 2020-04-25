package com.gupta.udapi.entities;

import javax.persistence.*;

/**
 * @author Amit
 */
@Entity
@Table(name = "config")
public class UdapiDatabaseMetadataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String dbName;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private Byte type;

    @Column(name = "port")
    private Integer port;

    @Column(name = "ip")
    private String ip;

    public UdapiDatabaseMetadataEntity(String dbName,String userName, String password, Byte type, Integer port, String ip) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
        this.type = type;
        this.port = port;
        this.ip = ip;
    }

    public UdapiDatabaseMetadataEntity(){}

    public static class Builder {

        private String dbName;
        private String userName;
        private String password;
        private Integer port;
        private String ip;
        private Byte type;

        public UdapiDatabaseMetadataEntity build() {
            return new UdapiDatabaseMetadataEntity(
                    dbName,
                    userName,
                    password,
                    type,
                    port,
                    ip
            );
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setDbName(String dbName) {
            this.dbName = dbName;
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

        public Builder setType(Byte type) {
            this.type = type;
            return this;
        }
    }


    public String getUserName() {
        return userName;
    }

    public Integer getId() {
        return id;
    }

    public String getDbName() {
        return dbName;
    }

    public String getPassword() {
        return password;
    }

    public Byte getType() {
        return type;
    }

    public Integer getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UdapiDatabaseMetadataEntity{");
        sb.append("id=").append(id);
        sb.append(", dbName='").append(dbName).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", type=").append(type);
        sb.append(", port=").append(port);
        sb.append(", ip='").append(ip).append('\'');
        sb.append('}');
        return sb.toString();
    }
}


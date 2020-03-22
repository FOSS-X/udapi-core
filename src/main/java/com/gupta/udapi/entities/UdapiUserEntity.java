package com.gupta.udapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Amit
 */
@Entity
@Table(name = "user")
public class UdapiUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String passwordHash;

    @Column(name = "type")
    private Integer type;

    @Column(name = "status")
    private Boolean status;


    public UdapiUserEntity(String name, String passwordHash, Integer type, Boolean status) {
        this.name = name;
        this.passwordHash = passwordHash;
        this.type = type;
        this.status = status;
    }

    public UdapiUserEntity(){}

    public static class Builder {

        private Integer id;
        private String name;
        private String passwordHash;
        private Integer type;
        private Boolean status;

        public UdapiUserEntity build() {
            return new UdapiUserEntity(
                    name,
                    passwordHash,
                    type,
                    status
            );
        }

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
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

        public Builder setStatus(Boolean status) {
            this.status = status;
            return this;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Integer getType() {
        return type;
    }

    public Boolean getStatus() {
        return status;
    }

}

package com.gupta.udapi.repositories.impl;

import com.gupta.udapi.entities.UdapiUserEntity;
import com.gupta.udapi.exception.DatabaseException;
import com.gupta.udapi.exception.UserNotFoundException;
import com.gupta.udapi.repositories.UdapiUserRepository;
import com.gupta.udapi.repositories.jpa.UdapiUserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Amit
 */
@Repository
public class UdapiUserRepositoryImpl implements UdapiUserRepository {

    @Autowired
    private UdapiUserJpa<UdapiUserEntity, Integer> userJpa;

    @Override
    public UdapiUserEntity getUserName(String userName) {
        UdapiUserEntity userEntity = null;

        try {
            userEntity = userJpa.getUserName(userName);
        }catch (Exception e) {
            throw new DatabaseException("A database exception has occoured fetching user details");
        }

        if (userEntity == null) {
            throw new UserNotFoundException("The username or password is incorrect.");
        }
        return userEntity;
    }

    @Override
    public Boolean checkIfUdapiUserExists(String userName) {

        UdapiUserEntity udapiUserEntityName = null;

        try {
            udapiUserEntityName = userJpa.getUserName(userName);

        }catch (Exception e) {
            throw new DatabaseException("A database exception has occoured fetching user details");
        }

        return !(udapiUserEntityName == null);
    }

    @Override
    public UdapiUserEntity addNewUser(UdapiUserEntity userEntity) {

        UdapiUserEntity responseEntity;
        try {
            responseEntity = userJpa.save(userEntity);
        }catch (Exception e) {
            throw new DatabaseException("A database error occoured creating the new brand.");
        }

        return responseEntity;
    }
}

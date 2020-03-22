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
        return null;
    }

    @Override
    public UdapiUserEntity addNewUser(UdapiUserEntity userEntity) {
        return null;
    }

//    @Override
//    public UdapiUserEntity getBrandUserName(String userName) {
//
//        UdapiUserEntity brandUserEntity = null;
//
//        try {
//            brandUserEntity = userJpa.getBrandUserName(userName);
//        }catch (Exception e) {
//            throw new DatabaseException("A database exception has occoured fetching user details");
//        }
//
//        if (brandUserEntity == null) {
//            throw new UserNotFoundException("The username or password is incorrect.");
//        }
//        return brandUserEntity;
//    }
//
//    @Override
//    public Boolean checkIfBrandUserExists(String brandName, Integer brandId) {
//
//        BrandUserEntity brandUserEntityName = null;
//        BrandUserEntity brandUserEntityId = null;
//        try {
//            brandUserEntityName = userJpa.getBrandUserName(brandName);
//            brandUserEntityId = userJpa.getBrandId(brandId);
//
//        }catch (Exception e) {
//            throw new DatabaseException("A database exception has occoured fetching user details");
//        }
//
//        return !(brandUserEntityName == null && brandUserEntityId == null);
//    }
//
//    @Override
//    public BrandUserEntity addNewBrandUser(BrandUserEntity brandUserEntity) {
//
//        BrandUserEntity responseEntity;
//
//        try {
//            responseEntity = userJpa.save(brandUserEntity);
//        }catch (Exception e) {
//            throw new DatabaseException("A database error occoured creating the new brand.");
//        }
//
//        return responseEntity;
//    }
}

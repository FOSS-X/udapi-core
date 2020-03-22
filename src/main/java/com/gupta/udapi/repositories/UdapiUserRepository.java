package com.gupta.udapi.repositories;

import com.gupta.udapi.entities.UdapiUserEntity;

/**
 * @author Amit
 */
public interface UdapiUserRepository {

    UdapiUserEntity getUserName(String userName);

    Boolean checkIfUdapiUserExists(String userName);

    UdapiUserEntity addNewUser(UdapiUserEntity userEntity);
}

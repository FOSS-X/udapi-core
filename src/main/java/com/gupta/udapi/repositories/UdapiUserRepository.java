package com.gupta.udapi.repositories;

import com.gupta.udapi.entities.UdapiUserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Amit
 */

@Repository
public interface UdapiUserRepository {

    UdapiUserEntity getUserName(String userName);

    Boolean checkIfUdapiUserExists(String userName);

    UdapiUserEntity addNewUser(UdapiUserEntity userEntity);
}

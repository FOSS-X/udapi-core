package com.gupta.udapi.repositories.jpa;

import com.gupta.udapi.entities.UdapiUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Amit
 */
@Repository
public interface UdapiUserJpa<T extends UdapiUserEntity, ID extends Integer> extends JpaRepository<T, ID> {

    @Query("select u from UdapiUserEntity u where u.name = :userName and u.status = true")
    UdapiUserEntity getUserName(@Param(value = "userName") String userName);

}

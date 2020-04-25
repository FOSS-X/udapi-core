package com.gupta.udapi.repositories.jpa;

import com.gupta.udapi.entities.UdapiDatabaseMetadataEntity;
import com.gupta.udapi.entities.UdapiUserEntity;
import com.gupta.udapi.enums.DbTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author Amit
 */
@Repository
public interface UdapiDatabaseMetadataJpa<T extends UdapiDatabaseMetadataEntity, ID extends Integer> extends JpaRepository<T, ID> {

    @Query("select u from UdapiDatabaseMetadataEntity u where u.type = :dbType")
    T getDatabaseMetadataFromByte(@Param(value = "dbType") Byte dbType);

    @Query("select u from UdapiDatabaseMetadataEntity u")
    List<T> getAllDatabaseMetadataFromByte();

    @Query(value = "select CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END from UdapiDatabaseMetadataEntity e where e.dbName = :dbName and e.type = :dbType")
    Boolean checkIfCommunityExistsByName(String dbName, Byte dbType);

    @Transactional
    @Modifying
    @Query("delete from UdapiDatabaseMetadataEntity u where u.type = :dbType")
    void deleteDatabaseConfig(@Param(value = "dbType") Byte dbType);

//    @Query("select u from UdapiDatabaseMetadataEntity u where u.name = :userName and u.status = true")
//    UdapiUserEntity getUserName(@Param(value = "userName") String userName);

}


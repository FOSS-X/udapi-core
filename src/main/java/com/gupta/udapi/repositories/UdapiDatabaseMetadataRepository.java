package com.gupta.udapi.repositories;

import com.gupta.udapi.entities.UdapiDatabaseMetadataEntity;
import com.gupta.udapi.enums.DbTypeEnum;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UdapiDatabaseMetadataRepository {

    UdapiDatabaseMetadataEntity addNewDatabaseConfig(UdapiDatabaseMetadataEntity databaseMetadataEntity);
    UdapiDatabaseMetadataEntity getDatabaseConfig(DbTypeEnum dbType);
    List<UdapiDatabaseMetadataEntity> getAllDatabaseConfig();
}

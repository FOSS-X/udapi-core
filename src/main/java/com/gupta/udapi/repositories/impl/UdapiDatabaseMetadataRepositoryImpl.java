package com.gupta.udapi.repositories.impl;

import com.gupta.udapi.entities.UdapiDatabaseMetadataEntity;
import com.gupta.udapi.enums.DbTypeEnum;
import com.gupta.udapi.exception.DatabaseException;
import com.gupta.udapi.repositories.UdapiDatabaseMetadataRepository;
import com.gupta.udapi.repositories.jpa.UdapiDatabaseMetadataJpa;
import com.gupta.udapi.services.UdapiDatabaseService;
import com.gupta.udapi.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UdapiDatabaseMetadataRepositoryImpl implements UdapiDatabaseMetadataRepository {

    @Autowired
    private UdapiDatabaseMetadataJpa<UdapiDatabaseMetadataEntity, Integer> udapiDatabaseMetadataJpa;

    @Override
    public UdapiDatabaseMetadataEntity addNewDatabaseConfig(UdapiDatabaseMetadataEntity databaseMetadataEntity) {

        //TODO: Logic for duplicate databases

        try {
            databaseMetadataEntity = udapiDatabaseMetadataJpa.save(databaseMetadataEntity);
        }catch (Exception e) {
            throw new DatabaseException("There was a database error storing the db config: \n"
                    + databaseMetadataEntity);
        }

        return databaseMetadataEntity;
    }

    @Override
    public UdapiDatabaseMetadataEntity getDatabaseConfig(DbTypeEnum dbType) {

        UdapiDatabaseMetadataEntity databaseMetadataEntity = null;
        try {
            databaseMetadataEntity = udapiDatabaseMetadataJpa.getDatabaseMetadataFromByte(dbType.getId());
        }catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("There was a database error fetching the db config: \n"
                    + databaseMetadataEntity);
        }
        return databaseMetadataEntity;
    }
}

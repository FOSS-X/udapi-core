package com.gupta.udapi.repositories.impl;

import com.gupta.udapi.entities.UdapiDatabaseMetadataEntity;
import com.gupta.udapi.enums.DbTypeEnum;
import com.gupta.udapi.exception.DatabaseConfigAlreadyExistsException;
import com.gupta.udapi.exception.DatabaseException;
import com.gupta.udapi.repositories.UdapiDatabaseMetadataRepository;
import com.gupta.udapi.repositories.jpa.UdapiDatabaseMetadataJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UdapiDatabaseMetadataRepositoryImpl implements UdapiDatabaseMetadataRepository {

    @Autowired
    private UdapiDatabaseMetadataJpa<UdapiDatabaseMetadataEntity, Integer> udapiDatabaseMetadataJpa;

    @Override
    public UdapiDatabaseMetadataEntity addNewDatabaseConfig(UdapiDatabaseMetadataEntity databaseMetadataEntity) {

        //Checking if a database config with the same name for that database already exists
        try {
            Boolean exists = udapiDatabaseMetadataJpa.checkIfCommunityExistsByName(databaseMetadataEntity.getDbName(),
                    databaseMetadataEntity.getType());

            if (exists)
                throw new DatabaseConfigAlreadyExistsException();

        }catch (DatabaseConfigAlreadyExistsException e) {
            throw new DatabaseConfigAlreadyExistsException("The database config already exists: " + databaseMetadataEntity.toString());
        }
        catch (Exception e) {
            throw new DatabaseException("There was a database error storing the db config: \n"
                    + databaseMetadataEntity);
        }

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
                    + dbType);
        }
        return databaseMetadataEntity;
    }

    @Override
    public List<UdapiDatabaseMetadataEntity> getAllDatabaseConfig() {

        List<UdapiDatabaseMetadataEntity> metadataEntities = null;
        try {
            metadataEntities = udapiDatabaseMetadataJpa.getAllDatabaseMetadataFromByte();
        }catch (Exception e) {
            throw new DatabaseException("There was a database error fetching the db config: \n");
        }
        return metadataEntities;
    }
}

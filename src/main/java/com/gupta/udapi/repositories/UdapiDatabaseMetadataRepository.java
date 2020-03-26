package com.gupta.udapi.repositories;

import com.gupta.udapi.entities.UdapiDatabaseMetadataEntity;

public interface UdapiDatabaseMetadataRepository {

    UdapiDatabaseMetadataEntity addNewDatabaseConfig(UdapiDatabaseMetadataEntity databaseMetadataEntity);
}

package com.gupta.udapi.services;

import com.gupta.udapi.dtos.DbConfigDto;
import com.gupta.udapi.entities.UdapiDatabaseMetadataEntity;
import com.gupta.udapi.enums.DbTypeEnum;
import org.springframework.stereotype.Service;

@Service
public interface UdapiDatabaseMetadataService {

    DbConfigDto addDbConfigToDatabase(DbConfigDto dbConfigDto);

    UdapiDatabaseMetadataEntity getDatabaseConfig(DbTypeEnum mysql);
}

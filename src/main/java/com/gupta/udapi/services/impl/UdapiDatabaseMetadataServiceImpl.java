package com.gupta.udapi.services.impl;

import com.gupta.udapi.dtos.DbConfigDto;
import com.gupta.udapi.entities.UdapiDatabaseMetadataEntity;
import com.gupta.udapi.enums.DbTypeEnum;
import com.gupta.udapi.mapper.UdapiDatabaseMetadataMapper;
import com.gupta.udapi.mapper.UdapiUserMapper;
import com.gupta.udapi.repositories.UdapiDatabaseMetadataRepository;
import com.gupta.udapi.services.UdapiDatabaseMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UdapiDatabaseMetadataServiceImpl implements UdapiDatabaseMetadataService {

    @Autowired
    UdapiDatabaseMetadataRepository databaseMetadataRepository;

    @Override
    public DbConfigDto addDbConfigToDatabase(DbConfigDto dbConfigDto) {

        UdapiDatabaseMetadataEntity databaseMetadataEntity = UdapiDatabaseMetadataMapper.getEntityFromDto(dbConfigDto);
        databaseMetadataEntity = databaseMetadataRepository.addNewDatabaseConfig(databaseMetadataEntity);
        return UdapiDatabaseMetadataMapper.getDtoFromEntity(databaseMetadataEntity);
    }

    @Override
    public UdapiDatabaseMetadataEntity getDatabaseConfig(DbTypeEnum typeEnum) {
        UdapiDatabaseMetadataEntity metadataEntity = databaseMetadataRepository.getDatabaseConfig(typeEnum);
        return metadataEntity;
    }

    @Override
    public List<UdapiDatabaseMetadataEntity> getAllDatabaseConfig() {
        List<UdapiDatabaseMetadataEntity> metadataEntities = databaseMetadataRepository.getAllDatabaseConfig();
        return metadataEntities;
    }
}

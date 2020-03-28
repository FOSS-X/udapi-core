package com.gupta.udapi.mapper;

import com.gupta.udapi.dtos.DbConfigDto;
import com.gupta.udapi.dtos.UdapiUserDto;
import com.gupta.udapi.entities.UdapiDatabaseMetadataEntity;
import com.gupta.udapi.entities.UdapiUserEntity;
import com.gupta.udapi.json.SignUpDetailsJson;
import org.springframework.stereotype.Service;

/**
 * @author amitkumargupta
 */
@Service
public class UdapiDatabaseMetadataMapper {

    public static DbConfigDto getDtoFromEntity(UdapiDatabaseMetadataEntity metadataEntity) {

        return new DbConfigDto.Builder()
                .setDbName(metadataEntity.getDbName())
                .setUserName(metadataEntity.getUserName())
                .setIp(metadataEntity.getIp())
                .setPassword(metadataEntity.getPassword())
                .setPort(metadataEntity.getPort())
                .setType(metadataEntity.getType())
                .build();
    }

    public static UdapiDatabaseMetadataEntity getEntityFromDto(DbConfigDto configDto) {

        return new UdapiDatabaseMetadataEntity.Builder()
                .setDbName(configDto.getDbName())
                .setUserName(configDto.getUserName())
                .setIp(configDto.getIp())
                .setPassword(configDto.getPassword())
                .setPort(configDto.getPort())
                .setType(configDto.getType())
                .build();
    }
}

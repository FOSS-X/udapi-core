package com.gupta.udapi.controllers;

import com.gupta.udapi.constants.ConstantStrings;
import com.gupta.udapi.dtos.DbConfigDto;
import com.gupta.udapi.entities.UdapiDatabaseMetadataEntity;
import com.gupta.udapi.enums.DbTypeEnum;
import com.gupta.udapi.exception.DbTypeNotFoundException;
import com.gupta.udapi.services.UdapiDatabaseMetadataService;
import com.gupta.udapi.services.UdapiDatabaseService;
import com.gupta.udapi.services.factories.ApplicationContextFactory;
import com.gupta.udapi.services.factories.DatabaseServiceFactory;
import com.gupta.udapi.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author amitkumargupta
 */
@RestController
@RequestMapping("/v1/metadata/")
public class MetadataController {

    @Autowired
    UdapiDatabaseMetadataService databaseMetadataService;

    @RequestMapping(value = "/env",method = RequestMethod.GET)
    public ResponseEntity<String> getEnvMetaData() {
        return new ResponseEntity<>("{meta: 'data'}", HttpStatus.OK);
    }

    /**
     * Adding a new database configuration
     * @param dbType One of mysql, mongodb, riakdb, couchdb, etc
     * @param dbConfigDto The json POST request body
     * @return HTTP status
     */
    @RequestMapping(value = "/db/{dbType}",method = RequestMethod.POST)
    public ResponseEntity<DbConfigDto> addDatabase(
            @PathVariable(value = "dbType") String dbType,
            @RequestBody DbConfigDto dbConfigDto) throws SQLException {

        Byte dbTypeByte = DbTypeEnum.getEnumByteFromString(dbType);
        if (dbTypeByte == null) {
            throw new DbTypeNotFoundException("The given dbType " + dbType + " was not found.\n" + dbConfigDto);
        }
        dbConfigDto.setType(dbTypeByte);

        UdapiDatabaseService databaseService = null;
        try {
            databaseService = DatabaseServiceFactory.getDatabaseService(
                    ApplicationContextFactory.getApplicationContext(), dbTypeByte);
            System.out.println(databaseService);
            if (databaseService == null) {
                throw new Exception("Did not find database driver");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        databaseService.testConnection(dbConfigDto);

        // If connection is successful, add to config db
        dbConfigDto = databaseMetadataService.addDbConfigToDatabase(dbConfigDto);
        return new ResponseEntity<>(dbConfigDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/db/{value}",method = RequestMethod.GET)
    public ResponseEntity<List<UdapiDatabaseMetadataEntity>> getAllDatabaseConfigs(
            @PathVariable(value = "value") String inputValue) {

        List<UdapiDatabaseMetadataEntity> metadataEntities;
        if (! inputValue.equals(ConstantStrings.ALL_DB_CONFIG_TYPE)) {
            metadataEntities = new ArrayList<>();
            Byte typeByte = DbTypeEnum.getEnumByteFromString(inputValue);
            metadataEntities.add(databaseMetadataService.getDatabaseConfig(DbTypeEnum.getEnumByValueByte(typeByte)));
        }
        else {
            metadataEntities = databaseMetadataService.getAllDatabaseConfig();
        }
        return new ResponseEntity<>(metadataEntities, HttpStatus.OK);
    }

    @RequestMapping(value = "/db/{dbType}",method = RequestMethod.PUT)
    public ResponseEntity<DbConfigDto> updateDatabaseConfig(
            @PathVariable(value = "dbType") String dbType,
            @RequestBody DbConfigDto dbConfigDto) {

        Byte dbTypeByte = DbTypeEnum.getEnumByteFromString(dbType);
        if (dbTypeByte == null) {
            throw new DbTypeNotFoundException("The given dbType " + dbType + " was not found.\n" + dbConfigDto);
        }
        dbConfigDto.setType(dbTypeByte);
        dbConfigDto = databaseMetadataService.updateDatabaseConfig(dbConfigDto);
        return new ResponseEntity<>(dbConfigDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/db/{dbType}",method = RequestMethod.DELETE)
    public ResponseEntity deleteDatabaseConfig(
            @PathVariable(value = "dbType") String dbType) {
        databaseMetadataService.deleteDatabaseConfig(DbTypeEnum.getEnumByDesc(dbType));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
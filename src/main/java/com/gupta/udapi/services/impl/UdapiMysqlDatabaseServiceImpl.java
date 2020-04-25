package com.gupta.udapi.services.impl;

import com.gupta.udapi.constants.UdapiDatabaseCodes;
import com.gupta.udapi.dtos.DbConfigDto;
import com.gupta.udapi.entities.UdapiDatabaseMetadataEntity;
import com.gupta.udapi.enums.DbTypeEnum;
import com.gupta.udapi.exception.CannotConnectToDatabaseException;
import com.gupta.udapi.exception.DatabaseException;
import com.gupta.udapi.repositories.UdapiDatabaseMetadataRepository;
import com.gupta.udapi.services.UdapiDatabaseService;
import com.gupta.udapi.services.factories.DatabaseServiceFactory;
import com.gupta.udapi.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class UdapiMysqlDatabaseServiceImpl implements UdapiDatabaseService {

    @Autowired
    UdapiDatabaseMetadataRepository metadataRepository;

    static {
        DatabaseServiceFactory.registerDatabaseService(UdapiDatabaseCodes.MYSQL, UdapiMysqlDatabaseServiceImpl.class);
    }

    @Override
    public void testConnection(DbConfigDto dbConfigDto) throws SQLException {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + dbConfigDto.getIp() +
                            ":" +
                            dbConfigDto.getPort() +
                            "/" +
                            dbConfigDto.getDbName(),

                    dbConfigDto.getUserName(),
                    dbConfigDto.getPassword());
        } catch (SQLException e) {
            throw new CannotConnectToDatabaseException("Could not test connection to the database with config: \n" +
                    dbConfigDto);
        }
        finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public String addEntitySet(String esName) {

        return esName;
    }

    @Override
    public String addEntity(String entityName, String entityId) {
        return null;
    }

//    @Override
//    public void addEntity() {
//
//    }

    @Override
    public String getEntitySet(String esName) {

        UdapiDatabaseMetadataEntity databaseMetadataEntity = metadataRepository.getDatabaseConfig(DbTypeEnum.MYSQL);

        if (databaseMetadataEntity == null) {
            throw new DatabaseException("The mysql database configuration might not exist. Create one.");
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + databaseMetadataEntity.getIp() +
                            ":" +
                            databaseMetadataEntity.getPort() +
                            "/" +
                            databaseMetadataEntity.getDbName(),

                    databaseMetadataEntity.getUserName(),
                    databaseMetadataEntity.getPassword());

        } catch (SQLException e) {
            throw new CannotConnectToDatabaseException("Could not test connection to the database with config: \n" +
                    databaseMetadataEntity);
        }

        String result = "";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM " + esName)
        ) {
            result = Utils.convertEntityResultSetToJSON(rs).toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("The entity set probably does not exist " + esName);
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public String getAllEntitySets() {

        UdapiDatabaseMetadataEntity databaseMetadataEntity = metadataRepository.getDatabaseConfig(DbTypeEnum.MYSQL);

        if (databaseMetadataEntity == null) {
            throw new DatabaseException("The mysql database configuration might not exist. Create one.");
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + databaseMetadataEntity.getIp() +
                            ":" +
                            databaseMetadataEntity.getPort() +
                            "/" +
                            databaseMetadataEntity.getDbName(),

                    databaseMetadataEntity.getUserName(),
                    databaseMetadataEntity.getPassword());

        } catch (SQLException e) {
            throw new CannotConnectToDatabaseException("Could not test connection to the database with config: \n" +
                    databaseMetadataEntity);
        }

        String result = "";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = ((Statement) stmt).executeQuery("SHOW TABLES")
        ) {
            result = Utils.convertAllEntityResultSetToJSON(rs).toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("Error fetching all tables");
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}

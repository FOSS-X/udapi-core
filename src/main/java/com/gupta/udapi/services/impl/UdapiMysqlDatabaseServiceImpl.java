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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    public String getEntity(String entitySetName, String entityId) {
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
        try (Statement stmt = conn.createStatement()) {
            String pkName = Utils.getPrimaryKeyFromEntitySet(entitySetName, conn);
            String retreiveQuery = new StringBuilder().append("SELECT * FROM ")
                    .append(entitySetName)
                    .append(" where ")
                    .append(pkName)
                    .append("=\"")
                    .append(entityId)
                    .append("\"").toString();
            System.out.println("retreiveQuery = " + retreiveQuery);
            ResultSet rs = ((Statement) stmt).executeQuery(retreiveQuery);
            result = Utils.convertEntityResultSetToJSON(rs).toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("The entity set probably does not exist " + entityId);
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
    public String addEntity(String entitySetName, String entityId, JSONObject jsonEntity) {

        UdapiDatabaseMetadataEntity databaseMetadataEntity = metadataRepository.getDatabaseConfig(DbTypeEnum.MYSQL);
        if (databaseMetadataEntity == null) {
            throw new DatabaseException("The mysql database configuration might not exist. Create one.");
        }

        Connection conn = null;
        String result = "";
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + databaseMetadataEntity.getIp() +
                            ":" +
                            databaseMetadataEntity.getPort() +
                            "/" +
                            databaseMetadataEntity.getDbName(),

                    databaseMetadataEntity.getUserName(),
                    databaseMetadataEntity.getPassword());

            List<String> columnsSet = Utils.getColumnNamesFromEntitySet(entitySetName, conn);
            int numCols = columnsSet.size();

            // First column is the primary key, so ignored.
            StringBuilder insertQuery = new StringBuilder("INSERT INTO " + entitySetName + " VALUES (\"" +
                    entityId +
                    "\",");
            for (int i = 1; i < numCols; i++) {
                String value = jsonEntity.get(columnsSet.get(i)).toString();
                insertQuery.append("\"").append(value).append("\",");
            }
            insertQuery = new StringBuilder(insertQuery.substring(0, insertQuery.length() - 1) + ")");
            System.out.println(insertQuery);

            try (Statement stmt = conn.createStatement();
            ) {
                int res = ((Statement) stmt).executeUpdate(insertQuery.toString());
                result = Integer.toString(res);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DatabaseException(e.getMessage());
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            throw new CannotConnectToDatabaseException( e.getMessage() +
                    databaseMetadataEntity);
        }catch (JSONException jsonException) {
            throw new DatabaseException("Missing fields required" + jsonException.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException(e.getMessage());
        }

        return result;
    }

    @Override
    public String updateEntity(String entityName, String entityId, JSONObject jsonEntity) {
        return null;
    }

    @Override
    public String deleteEntity(String entitySetName, String entityId) {

        UdapiDatabaseMetadataEntity databaseMetadataEntity = metadataRepository.getDatabaseConfig(DbTypeEnum.MYSQL);

        if (databaseMetadataEntity == null) {
            throw new DatabaseException("The mysql database configuration might not exist. Create one.");
        }

        Connection conn = null;
        String result = "";
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + databaseMetadataEntity.getIp() +
                            ":" +
                            databaseMetadataEntity.getPort() +
                            "/" +
                            databaseMetadataEntity.getDbName(),

                    databaseMetadataEntity.getUserName(),
                    databaseMetadataEntity.getPassword());


            Statement stmt = conn.createStatement();
            String deleteQuery = "DELETE FROM " + entitySetName + " WHERE id =\""
                    + entityId + "\"";
            result = Integer.toString(((Statement) stmt).executeUpdate(deleteQuery));

        } catch (SQLException e) {
            throw new CannotConnectToDatabaseException("Could not test connection to the database with config: \n" +
                    databaseMetadataEntity);
        }


        return result;
    }

    @Override
    @Deprecated
    public String addEntitySet(String esName) {
        return esName;
    }

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

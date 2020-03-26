package com.gupta.udapi.services.impl;

import com.gupta.udapi.dtos.DbConfigDto;
import com.gupta.udapi.exception.CannotConnectToDatabaseException;
import com.gupta.udapi.services.UdapiDatabaseService;

import java.sql.*;

public class MysqlDatabaseServiceImpl implements UdapiDatabaseService {

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
    public void addEntitySet() {

    }

    @Override
    public void addEntity() {

    }

    @Override
    public void getEntitySet() {

    }
}

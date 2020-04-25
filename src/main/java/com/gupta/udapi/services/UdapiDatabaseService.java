package com.gupta.udapi.services;

import com.gupta.udapi.dtos.DbConfigDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author amitkumargupta
 */
public interface UdapiDatabaseService {

    void testConnection (DbConfigDto dbConfigDto) throws SQLException;
    String addEntitySet(String esName);
    String addEntity(String entityName, String entityId);
    String getEntitySet(String esName);
    String getAllEntitySets();
}

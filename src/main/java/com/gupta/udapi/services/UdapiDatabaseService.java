package com.gupta.udapi.services;

import com.gupta.udapi.dtos.DbConfigDto;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author amitkumargupta
 */
public interface UdapiDatabaseService {

    void testConnection (DbConfigDto dbConfigDto) throws SQLException;
    String getEntity(String entitySetName, String entityId);
    String addEntity(String entitySetName, String entityId, JSONObject jsonEntity);
    String updateEntity(String entityName, String entityId, JSONObject jsonEntity);
    String getEntitySet(String esName);
    String getAllEntitySets();

    @Deprecated
    String addEntitySet(String esName);
}

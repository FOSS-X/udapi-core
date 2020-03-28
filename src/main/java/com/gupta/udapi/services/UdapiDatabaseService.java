package com.gupta.udapi.services;

import com.gupta.udapi.dtos.DbConfigDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author amitkumargupta
 */
@Service
public interface UdapiDatabaseService {

    void testConnection (DbConfigDto dbConfigDto) throws SQLException;
    void addEntitySet();
    void addEntity();
    String getEntitySet(String esName);

    String getAllEntitySets();
}

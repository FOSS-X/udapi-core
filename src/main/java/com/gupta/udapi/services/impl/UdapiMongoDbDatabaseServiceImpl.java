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
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class UdapiMongoDbDatabaseServiceImpl implements UdapiDatabaseService {

    @Autowired
    UdapiDatabaseMetadataRepository metadataRepository;

    static {
        DatabaseServiceFactory.registerDatabaseService(UdapiDatabaseCodes.MONGODB, UdapiMongoDbDatabaseServiceImpl.class);
    }

    @Override
    public void testConnection(DbConfigDto dbConfigDto) {
        //MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(new MongoClientURI(
                    "mongodb://" +
                            dbConfigDto.getIp() +
                            ":" +
                            dbConfigDto.getPort()));

            // Checking if the database exists.
            MongoDatabase database = mongoClient.getDatabase(dbConfigDto.getDbName());

        } catch (Exception e) {
            throw new CannotConnectToDatabaseException("Could not test connection to the database with config: \n" +
                    dbConfigDto);
        }
    }

    @Override
    public String addEntitySet(String esName) {
        return null;
    }

    @Override
    public void addEntity() {

    }

    @Override
    public String getEntitySet(String esName) {

        UdapiDatabaseMetadataEntity databaseMetadataEntity = metadataRepository.getDatabaseConfig(DbTypeEnum.MONGODB);

        if (databaseMetadataEntity == null) {
            throw new DatabaseException("The mysql database configuration might not exist. Create one.");
        }

        MongoClient mongoClient = null;
        DB database = null;
        try {
            mongoClient = new MongoClient(new MongoClientURI(
                    "mongodb://" +
                            databaseMetadataEntity.getIp() +
                            ":" +
                            databaseMetadataEntity.getPort()));

            database = mongoClient.getDB(databaseMetadataEntity.getDbName());
            if (database == null)
                throw new Exception();

        } catch (Exception e) {
            throw new CannotConnectToDatabaseException("Could not test connection to the database with config: \n" +
                    databaseMetadataEntity);
        }

        DBCollection collection = database.getCollection(esName);

        JSONArray array = new JSONArray();
        DBCursor cursor = collection.find();

        try {
            while (cursor.hasNext()) {
                array.put(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return array.toString();
    }

    @Override
    public String getAllEntitySets() {

        UdapiDatabaseMetadataEntity databaseMetadataEntity = metadataRepository.getDatabaseConfig(DbTypeEnum.MONGODB);

        if (databaseMetadataEntity == null) {
            throw new DatabaseException("The mysql database configuration might not exist. Create one.");
        }

        MongoClient mongoClient = null;
        DB database = null;
        try {
            mongoClient = new MongoClient(new MongoClientURI(
                    "mongodb://" +
                            databaseMetadataEntity.getIp() +
                            ":" +
                            databaseMetadataEntity.getPort()));

            database = mongoClient.getDB(databaseMetadataEntity.getDbName());
            if (database == null)
                throw new Exception();

        } catch (Exception e) {
            throw new CannotConnectToDatabaseException("Could not test connection to the database with config: \n" +
                    databaseMetadataEntity);
        }

        Set<String> esNames = database.getCollectionNames();
        mongoClient.close();

        JSONArray array = new JSONArray();
        for (String s : esNames) {
            array.put(s);
        }

        return array.toString();
    }
}

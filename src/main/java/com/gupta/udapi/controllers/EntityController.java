package com.gupta.udapi.controllers;

import com.gupta.udapi.enums.DbTypeEnum;
import com.gupta.udapi.exception.InvalidEntityJsonException;
import com.gupta.udapi.services.UdapiDatabaseService;
import com.gupta.udapi.services.factories.ApplicationContextFactory;
import com.gupta.udapi.services.factories.DatabaseServiceFactory;
import com.gupta.udapi.utility.Utils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author amitkumargupta
 */
@RestController
@RequestMapping("/v1/entity")
public class EntityController {

    @RequestMapping(value = "/{entitySetName}/{entityId}",method = RequestMethod.GET)
    public ResponseEntity<String> getEntityById(
            final @PathVariable String entitySetName,
            final @PathVariable String entityId,
            @RequestAttribute(name = "dbType") String dbType
            ) {

        Byte dbTypeByte = DbTypeEnum.getEnumByteFromString(dbType);
        UdapiDatabaseService databaseService = DatabaseServiceFactory.getDatabaseService(
                ApplicationContextFactory.getApplicationContext(), dbTypeByte);
        String jsonResult = databaseService.getEntity(entitySetName, entityId);
        return Utils.buildJsonResponseEntityFromString(jsonResult);
    }

    @RequestMapping(value = "/{entitySetName}/{entityId}",method = RequestMethod.PUT)
    public ResponseEntity<String> updateEntity(
            final @PathVariable String entitySetName,
            final @PathVariable String entityId
    ) {
        return new ResponseEntity<>(entitySetName + "/"  + entityId, HttpStatus.OK);
    }

    @RequestMapping(value = "/{entitySetName}/{entityId}",method = RequestMethod.POST)
    public ResponseEntity<String> createEntity(
            final @PathVariable String entitySetName,
            final @PathVariable String entityId,
            final @RequestBody String entityDto,
            @RequestAttribute(name = "dbType") String dbType
    ) {

        // Parsing the input entity dto
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(entityDto);
        }catch (JSONException err){
            throw new InvalidEntityJsonException("The entity json could not be parsed.\n" +
                    err.getMessage());
        }

        Byte dbTypeByte = DbTypeEnum.getEnumByteFromString(dbType);
        UdapiDatabaseService databaseService = DatabaseServiceFactory.getDatabaseService(
                ApplicationContextFactory.getApplicationContext(), dbTypeByte);
        String jsonResult = databaseService.addEntity(entitySetName, entityId, jsonObject);
        return Utils.buildJsonResponseEntityFromString(jsonResult);
        //return new ResponseEntity<>(entitySetName + "/"  + entityId, HttpStatus.OK);
    }

    @RequestMapping(value = "/{entitySetName}/{entityId}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEntity(
            final @PathVariable String entitySetName,
            final @PathVariable String entityId,
            @RequestAttribute(name = "dbType") String dbType
    ) {
        Byte dbTypeByte = DbTypeEnum.getEnumByteFromString(dbType);
        UdapiDatabaseService databaseService = DatabaseServiceFactory.getDatabaseService(
                ApplicationContextFactory.getApplicationContext(), dbTypeByte);
        String jsonResult = databaseService.deleteEntity(entitySetName, entityId);
        return Utils.buildJsonResponseEntityFromString(jsonResult);
    }
}

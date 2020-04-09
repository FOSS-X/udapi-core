package com.gupta.udapi.controllers;

import com.gupta.udapi.enums.DbTypeEnum;
import com.gupta.udapi.exception.CreatingEntitySetNotSupportedException;
import com.gupta.udapi.services.UdapiDatabaseService;
import com.gupta.udapi.services.factories.ApplicationContextFactory;
import com.gupta.udapi.services.factories.DatabaseServiceFactory;
import com.gupta.udapi.utility.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author amitkumargupta
 */
@RestController()
@RequestMapping("/v1/entitySet/")
public class EntitySetController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> getAllEntitySets(
            @RequestAttribute(name = "dbType") String dbType
    ) {

        Byte dbTypeByte = DbTypeEnum.getEnumByteFromString(dbType);
        UdapiDatabaseService databaseService = DatabaseServiceFactory.getDatabaseService(ApplicationContextFactory.getApplicationContext(), dbTypeByte);
        String res = databaseService.getAllEntitySets();
        return Utils.buildJsonResponseEntityFromString(res);
    }

    @RequestMapping(value = "/{entitySetName}", method = RequestMethod.GET)
    public ResponseEntity<String> getEntitySetByName(
            final @PathVariable String entitySetName,
            @RequestAttribute(name = "dbType") String dbType
    ) {
        Byte dbTypeByte = DbTypeEnum.getEnumByteFromString(dbType);
        UdapiDatabaseService databaseService = DatabaseServiceFactory.getDatabaseService(
                ApplicationContextFactory.getApplicationContext(), dbTypeByte);
        String jsonResult = databaseService.getEntitySet(entitySetName);
        return Utils.buildJsonResponseEntityFromString(jsonResult);
    }

    @RequestMapping(value = "/{entitySetName}", method = RequestMethod.POST)
    public ResponseEntity<String> createEntitySet(
            final @PathVariable String entitySetName,
            @RequestAttribute(name = "dbType") String dbType
    ) {
        Byte dbTypeByte = DbTypeEnum.getEnumByteFromString(dbType);
        UdapiDatabaseService databaseService = DatabaseServiceFactory.getDatabaseService(
                ApplicationContextFactory.getApplicationContext(), dbTypeByte);

        throw new CreatingEntitySetNotSupportedException("Managing entity sets through udapi is not supported.");

        //TODO
//        JSONObject parsedEntitySetJson = new JSONObject(parsedEntitySetDto);
//        System.out.println(parsedEntitySetDto.toString());
//
//        String jsonResult = databaseService.addEntitySet(entitySetName);
//        return new ResponseEntity<>(entitySetName, HttpStatus.OK);
    }

    @RequestMapping(value = "/{entitySetName}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEntitySet(
            final @PathVariable String entitySetName,
            @RequestAttribute(name = "dbType") String dbType
    ) {
        Byte dbTypeByte = DbTypeEnum.getEnumByteFromString(dbType);
        UdapiDatabaseService databaseService = DatabaseServiceFactory.getDatabaseService(
                ApplicationContextFactory.getApplicationContext(), dbTypeByte);

        throw new CreatingEntitySetNotSupportedException("Managing entity sets through udapi is not supported.");

        //TODO
        //return new ResponseEntity<>(entitySetName, HttpStatus.OK);
    }

    //TODO
    @RequestMapping(value = "/{entitySetName}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateEntitySet(
            final @PathVariable String entitySetName
    ) {
        throw new CreatingEntitySetNotSupportedException("Managing entity sets through udapi is not supported.");
    }
}

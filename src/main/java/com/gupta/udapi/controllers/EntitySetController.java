package com.gupta.udapi.controllers;

import com.gupta.udapi.services.UdapiDatabaseService;
import com.gupta.udapi.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

/**
 * @author amitkumargupta
 */
@RestController()
@RequestMapping("/v1/entitySet/")
public class EntitySetController {

    @Autowired
    Utils utils;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> getAllEntitySets(
            @RequestAttribute(name = "dbType") String dbType
    ) {

        UdapiDatabaseService databaseService = utils.resolveServiceTypeInstanceFromDbType(dbType);
        String res = databaseService.getAllEntitySets();
        return utils.buildJsonResponseEntityFromString(res);
    }

    @RequestMapping(value = "/{entitySetName}", method = RequestMethod.GET)
    public ResponseEntity<String> getEntitySetByName(
            final @PathVariable String entitySetName,
            @RequestAttribute(name = "dbType") String dbType
    ) {
        UdapiDatabaseService databaseService = utils.resolveServiceTypeInstanceFromDbType(dbType);
        String jsonResult = databaseService.getEntitySet(entitySetName);
        return utils.buildJsonResponseEntityFromString(jsonResult);
    }

    @RequestMapping(value = "/{entitySetName}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateEntitySet(
            final @PathVariable String entitySetName
    ) {
        return new ResponseEntity<>(entitySetName, HttpStatus.OK);
    }

    @RequestMapping(value = "/{entitySetName}", method = RequestMethod.POST)
    public ResponseEntity<String> createEntitySet(
            final @PathVariable String entitySetName
    ) {
        return new ResponseEntity<>(entitySetName, HttpStatus.OK);
    }

    @RequestMapping(value = "/{entitySetName}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEntitySet(
            final @PathVariable String entitySetName
    ) {
        return new ResponseEntity<>(entitySetName, HttpStatus.OK);
    }
}

package com.gupta.udapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author amitkumargupta
 */
@RestController
@RequestMapping("/v1/entity")
public class EntityController {

    @RequestMapping(value = "/{entitySetName}/{entityId}",method = RequestMethod.GET)
    public ResponseEntity<String> getEntityById(
            final @PathVariable String entitySetName,
            final @PathVariable String entityId
            ) {
        return new ResponseEntity<>(entitySetName + "/"  + entityId, HttpStatus.OK);
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
            final @PathVariable String entityId
    ) {
        return new ResponseEntity<>(entitySetName + "/"  + entityId, HttpStatus.OK);
    }

    @RequestMapping(value = "/{entitySetName}/{entityId}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEntity(
            final @PathVariable String entitySetName,
            final @PathVariable String entityId
    ) {
        return new ResponseEntity<>(entitySetName + "/"  + entityId, HttpStatus.OK);
    }
}

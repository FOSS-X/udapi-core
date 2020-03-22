package com.gupta.udapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author amitkumargupta
 */
@RestController
@RequestMapping("/v1/metadata/")
public class MetadataController {

    @RequestMapping(value = "/env",method = RequestMethod.GET)
    public ResponseEntity<String> getEnvMetaData() {
        return new ResponseEntity<>("{meta: 'data'}", HttpStatus.OK);
    }

    @RequestMapping(value = "/db/{dbName}",method = RequestMethod.GET)
    public ResponseEntity<String> getDbMetaData(
            final @NotNull @PathVariable String dbName) {
        return new ResponseEntity<>(dbName, HttpStatus.OK);
    }

    @RequestMapping(value = "/entitySet/{entitySetName}",method = RequestMethod.GET)
    public ResponseEntity<String> getEntitySetMetaData(
            final @NotNull @PathVariable String entitySetName) {
        return new ResponseEntity<>(entitySetName, HttpStatus.OK);
    }
}
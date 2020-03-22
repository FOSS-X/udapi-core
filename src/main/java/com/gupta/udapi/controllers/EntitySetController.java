package com.gupta.udapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author amitkumargupta
 */
@RestController()
@RequestMapping("/v1/entitySet/")
public class EntitySetController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> getAllEntitySets() {
        return new ResponseEntity<>("{All entity sets}", HttpStatus.OK);
    }

    @RequestMapping(value = "/{entitySetName}", method = RequestMethod.GET)
    public ResponseEntity<String> getEntitySetByName(
            final @PathVariable String entitySetName
    ) {
        return new ResponseEntity<>(entitySetName, HttpStatus.OK);
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

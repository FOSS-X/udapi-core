package com.gupta.udapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author amitkumargupta
 */
@RestController
@RequestMapping("/")
public class StatusController {

    @GetMapping("/status")
    public ResponseEntity<String> health() {
        return new ResponseEntity<String>("Ready", HttpStatus.OK);
    }
}


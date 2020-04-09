package com.gupta.udapi.controllers;

import com.gupta.udapi.dtos.DbConfigDto;
import com.gupta.udapi.services.factories.ApplicationContextFactory;
import com.gupta.udapi.services.factories.DatabaseServiceFactory;
import com.gupta.udapi.services.impl.UdapiMongoDbDatabaseServiceImpl;
import com.gupta.udapi.services.impl.UdapiMysqlDatabaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public ResponseEntity<String> test(
            @RequestBody DbConfigDto dbConfigDto
    ) {

        return new ResponseEntity<String>("Res", HttpStatus.OK);
    }
}

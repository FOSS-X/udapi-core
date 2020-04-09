package com.gupta.udapi.services.factories;

import com.gupta.udapi.exception.DbTypeNotFoundException;
import com.gupta.udapi.services.UdapiDatabaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DatabaseServiceFactory {

    private static final Map<Byte, Class<? extends UdapiDatabaseService>> registerMap = new HashMap<>();

    public static void registerDatabaseService(Byte typeByte, Class<? extends UdapiDatabaseService> implClass) {
        System.out.println("Registered: " + typeByte + " " + implClass);
        registerMap.put(typeByte, implClass);
    }

    public static UdapiDatabaseService getDatabaseService(ApplicationContext applicationContext, Byte dbType) {

        if (registerMap.containsKey(dbType)) {
            return applicationContext.getBean(registerMap.get(dbType));
        }

        throw new DbTypeNotFoundException("The database type header: " + dbType + " was not found.");
    }
}

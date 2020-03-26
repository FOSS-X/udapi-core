package com.gupta.udapi.services;

import com.gupta.udapi.dtos.DbConfigDto;

public interface UdapiDatabaseMetadataService {

    DbConfigDto addDbConfigToDatabase(DbConfigDto dbConfigDto);
}

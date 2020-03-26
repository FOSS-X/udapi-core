package com.gupta.udapi.controllers;

import com.gupta.udapi.dtos.DbConfigDto;
import com.gupta.udapi.enums.DbTypeEnum;
import com.gupta.udapi.exception.DbTypeNotFoundException;
import com.gupta.udapi.services.UdapiDatabaseMetadataService;
import com.gupta.udapi.services.UdapiDatabaseService;
import com.gupta.udapi.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.*;

/**
 * @author amitkumargupta
 */
@RestController
@RequestMapping("/v1/metadata/")
public class MetadataController {

    @Autowired
    UdapiDatabaseMetadataService databaseMetadataService;

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

    /**
     * Adding a new database configuration
     * @param dbType One of mysql, mongodb, riakdb, couchdb, etc
     * @param dbConfigDto The json POST request body
     * @return HTTP status
     */
    @RequestMapping(value = "/db",method = RequestMethod.POST)
    public ResponseEntity<DbConfigDto> addDatabase(
            final @RequestAttribute(name = "dbType") String dbType,
            @RequestBody DbConfigDto dbConfigDto) throws IllegalAccessException, InstantiationException, SQLException {

        //TODO: validate Confifg DTO

        Byte dbTypeByte = DbTypeEnum.getEnumByteFromString(dbType);

        if (dbTypeByte == null) {
            throw new DbTypeNotFoundException("The given dbType " + dbType + " was not found.\n" + dbConfigDto);
        }
        dbConfigDto.setType(Byte.toUnsignedInt(dbTypeByte));

        UdapiDatabaseService databaseService = null;
        try {
            databaseService = Utils.udapiDatabaseServiceResolver(dbTypeByte).newInstance();
            if (databaseService == null) {
                throw new Exception("Did not find database driver");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        databaseService.testConnection(dbConfigDto);

        // If connection is successful, add to config db
        dbConfigDto = databaseMetadataService.addDbConfigToDatabase(dbConfigDto);

        //-------------

//        // Testing connection.
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/udapi",
//                    "root",
//                    "password");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try (Statement stmt = conn.createStatement();
//             ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM user")
//        ) {
//            while (rs.next()) {
//                int numColumns = rs.getMetaData().getColumnCount();
//                for (int i = 1; i <= numColumns; i++) {
//                    // Column numbers start at 1.
//                    // Also there are many methods on the result set to return
//                    //  the column as a particular type. Refer to the Sun documentation
//                    //  for the list of valid conversions.
//                    System.out.println( "COLUMN " + i + " = " + rs.getObject(i));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return new ResponseEntity<DbConfigDto>(dbConfigDto, HttpStatus.OK);
    }
}
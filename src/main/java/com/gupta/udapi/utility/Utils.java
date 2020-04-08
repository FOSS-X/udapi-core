package com.gupta.udapi.utility;

import com.gupta.udapi.entities.UdapiDatabaseMetadataEntity;
import com.gupta.udapi.enums.DbTypeEnum;
import com.gupta.udapi.repositories.UdapiDatabaseMetadataRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.ResultSet;

/**
 * @author amitkumargupta
 */

@Service
public class Utils {

    @Autowired
    UdapiDatabaseMetadataRepository metadataRepository;

    /**
     * Takes an exception stack trace and returns it in a String format.
     * @param ex
     * @return
     */
    public String getStackTraceInStringFmt(Exception ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        return writer.toString();
    }

    public ResponseEntity<String>  buildJsonResponseEntityFromString(String jsonBody) {
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(jsonBody);
    }

    public JSONArray convertToJSON(ResultSet resultSet) throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            System.out.println(total_rows);
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                System.out.println(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase());
                System.out.println(resultSet.getObject(i + 1));
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
            }
            jsonArray.put(obj);
        }
        return jsonArray;
    }

    public UdapiDatabaseMetadataEntity getMetadataConnection(DbTypeEnum dbTypeEnum) {

        return metadataRepository.getDatabaseConfig(dbTypeEnum);
    }
}

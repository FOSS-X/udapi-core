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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author amitkumargupta
 */

@Service
public class Utils {

    /**
     * Takes an exception stack trace and returns it in a String format.
     * @param ex
     * @return
     */
    public static String getStackTraceInStringFmt(Exception ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        return writer.toString();
    }

    public static ResponseEntity<String>  buildJsonResponseEntityFromString(String jsonBody) {
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(jsonBody);
    }

    public static JSONArray convertEntityResultSetToJSON(ResultSet resultSet) throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
            }
            jsonArray.put(obj);
        }
        return jsonArray;
    }

    public static JSONArray convertAllEntityResultSetToJSON(ResultSet resultSet) throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < total_rows; i++) {
                jsonArray.put(resultSet.getObject(i+1));
            }
        }
        return jsonArray;
    }

    public static List<String> getColumnNamesFromEntitySet(String entitySetName, Connection conn) throws Exception {

        ResultSet resultSet = conn.createStatement().executeQuery("DESC " + entitySetName);
        List<String> columnSet = new ArrayList<>();
        while (resultSet.next()) {
            columnSet.add(resultSet.getString(1));
        }
        return columnSet;
    }

    public static String getPrimaryKeyFromEntitySet(String entitySetName, Connection conn) throws SQLException {

        ResultSet rs = conn.getMetaData().getPrimaryKeys(null, null, entitySetName);
        if (!rs.next())
            return null;

        // Gets the first primary key if more than one is present.
        return rs.getString("COLUMN_NAME");
    }
}

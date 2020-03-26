package com.gupta.udapi.utility;

import com.gupta.udapi.services.UdapiDatabaseService;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author amitkumargupta
 */
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

    public static  Class<UdapiDatabaseService> udapiDatabaseServiceResolver(Byte dbType) throws ClassNotFoundException {

        switch (dbType) {
            case 0: {
                return (Class<UdapiDatabaseService>) Class.forName("com.gupta.udapi.services.impl.MysqlDatabaseServiceImpl");
            }
            case 1:
        }
        return null;
    }
}

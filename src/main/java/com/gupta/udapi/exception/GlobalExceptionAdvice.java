package com.gupta.udapi.exception;

import com.gupta.udapi.utility.Utils;
import com.gupta.udapi.json.ErrorDetailJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author amitkumargupta
 */
@ControllerAdvice
public class GlobalExceptionAdvice {

    @Autowired
    private Utils utils;
    private static Logger strategyLogger = LogManager.getLogger();

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDetailJson> generalServerException(Exception e) {

        strategyLogger.fatal("Internal server error.");
        strategyLogger.fatal(utils.getStackTraceInStringFmt(e));
        return  new ResponseEntity<ErrorDetailJson>(new ErrorDetailJson("UD_100-01",e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DatabaseException.class)
    public ResponseEntity<ErrorDetailJson> databaseException(Exception e) {

        strategyLogger.fatal("Internal server error.");
        strategyLogger.fatal(utils.getStackTraceInStringFmt(e));
        return  new ResponseEntity<ErrorDetailJson>(new ErrorDetailJson("UD_100-01",e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //TODO: Add all other exceptions
}

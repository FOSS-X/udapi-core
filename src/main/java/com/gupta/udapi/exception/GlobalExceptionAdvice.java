package com.gupta.udapi.exception;

import com.gupta.udapi.Utils;
import com.gupta.udapi.json.ErrorDetailJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author amitkumargupta
 */
@ControllerAdvice
public class GlobalExceptionAdvice {

    private static Logger strategyLogger = LogManager.getLogger();

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDetailJson> generalServerException(Exception e) {
        strategyLogger.fatal("Internal server error.");
        strategyLogger.fatal(Utils.getStackTraceInStringFmt(e));
        return  new ResponseEntity<ErrorDetailJson>(new ErrorDetailJson("SV_100-01",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

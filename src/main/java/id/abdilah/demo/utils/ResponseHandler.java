package id.abdilah.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponseWithData(Boolean isError, String message, Object responseObj, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("error", isError);
        map.put("message", message);
        map.put("data", responseObj);

        return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<Object> generateResponseOnlyMessage(Boolean isError, String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("error", isError);
        map.put("message", message);

        return new ResponseEntity<Object>(map,status);
    }
}

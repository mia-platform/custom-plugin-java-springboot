package eu.miaplatform.customplugin.springboot.lib;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CPStatus {

    public static ResponseEntity statusOk(CPStatusBody cpStatusBody) {
        cpStatusBody.setStatus(CPStatusBody.OK);
        return new ResponseEntity<>(cpStatusBody, HttpStatus.OK);
    }


    public static ResponseEntity statusKo(CPStatusBody cpStatusBody) {
        cpStatusBody.setStatus(CPStatusBody.KO);
        return new ResponseEntity<>(cpStatusBody, HttpStatus.SERVICE_UNAVAILABLE);
    }
}

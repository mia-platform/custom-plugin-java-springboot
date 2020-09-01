package eu.miaplatform.customplugin.springboot;

import eu.miaplatform.decorators.DecoratorResponse;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;

public class DecoratorUtils {
    public static ResponseEntity<Serializable> getResponseEntityFromDecoratorResponse(DecoratorResponse decoratorResponse) {
        HttpStatus httpStatusCode = HttpStatus.resolve(decoratorResponse.getStatusCode());
        MultiValueMap<String, String> headersMultiValueMap = new LinkedMultiValueMap<>();
        decoratorResponse.getHeaders().forEach(headersMultiValueMap::add);
        HttpHeaders httpHeaders = new HttpHeaders(headersMultiValueMap);

        return ResponseEntity
                .status(httpStatusCode != null ? httpStatusCode : HttpStatus.OK)
                .headers(httpHeaders)
                .body(decoratorResponse.getBody());
    }
}

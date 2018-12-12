package eu.miaplatform.customplugin.springboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

@RestController
public interface CustomPluginController {

    String handle();
}

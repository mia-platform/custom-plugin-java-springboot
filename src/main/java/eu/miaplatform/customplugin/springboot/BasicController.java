package eu.miaplatform.customplugin.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}

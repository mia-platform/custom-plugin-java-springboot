package eu.miaplatform.customplugin.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class Application {

    //Default server port is 3000
    //Run mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8000 to change server port
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }
}

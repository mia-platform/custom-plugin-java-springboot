package eu.miaplatform.customplugin.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class CustomPluginApplication {

    private static String serverPort = "8080";

    public static void run(String[] args) {

        SpringApplication app = new SpringApplication(CustomPluginApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", serverPort));
        app.run(args);
    }

    public static void setServerPort(String serverPort) {
        CustomPluginApplication.serverPort = serverPort;
    }
}

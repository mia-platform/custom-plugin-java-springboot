package eu.miaplatform.customplugin.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class CustomPlugin {

    private static String serverPort = "8080";
    private static List<Route> routes = new ArrayList<>();

    static void run(String[] args) {

        SpringApplication app = new SpringApplication(CustomPlugin.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", serverPort));
        app.run(args);

    }

    static void setServerPort(String serverPort) {
        CustomPlugin.serverPort = serverPort;
    }

    static void addRoute(Route route) {
        routes.add(route);
    }

    @Bean
    public EndpointService endpointService() {
        EndpointService endpointService = new EndpointService();
        endpointService.setRoutes(routes);
        return endpointService;
    }
}

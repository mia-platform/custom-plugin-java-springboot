package eu.miaplatform.customplugin.springboot.example;

import eu.miaplatform.customplugin.springboot.CustomPluginApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class MyApplication extends CustomPluginApplication {

    public static void main(String[] args) {
        setServerPort("8081");
        run(args);
    }
}

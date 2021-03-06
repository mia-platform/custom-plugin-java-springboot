package eu.miaplatform.customplugin.springboot.example;

import eu.miaplatform.customplugin.springboot.CPSwaggerConfig;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends CPSwaggerConfig {

    @Override
    public String getTitle() {
        return "Custom Plugin Spring Boot REST API";
    }

    @Override
    public String getDescription() {
        return "An example of a Custom Plugin Spring Boot REST API";
    }

    @Override
    public String getVersion() {
        return "0.0.1-SNAPSHOT";
    }

    @Override
    public String getLicense() {
        return "Apache License Version 2.0";
    }

    @Override
    public String getLicenseUrl() {
        return "https://www.apache.org/licenses/LICENSE-2.0";
    }

    @Override
    public String getContactName() {
        return "Mia Platform";
    }

    @Override
    public String getContactUrl() {
        return "https://www.mia-platform.eu";
    }

    @Override
    public String getContactEmail() {
        return "info@mia-platform.eu";
    }


}

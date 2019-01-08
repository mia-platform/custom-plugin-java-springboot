package eu.miaplatform.customplugin.springboot.lib;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public abstract class CPSwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("eu.miaplatform.customplugin.springboot"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title(getTitle())
                .description(getDescription())
                .version(getVersion())
                .license(getLicense())
                .licenseUrl(getLicenseUrl())
                .contact(new Contact(getContactName(), getContactUrl(), getContactEmail()))
                .build();
    }

    public abstract String getTitle();

    public abstract String getDescription();

    public abstract String getVersion();

    public abstract String getLicense();

    public abstract String getLicenseUrl();

    public abstract String getContactName();

    public abstract String getContactUrl();

    public abstract String getContactEmail();


}

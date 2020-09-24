package eu.miaplatform.customplugin.springboot;

import eu.miaplatform.service.environment.EnvConfiguration;
import eu.miaplatform.service.environment.InvalidEnvConfigurationException;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import static eu.miaplatform.customplugin.springboot.CPConstants.CP_REQUEST;

@RestController
public abstract class CPController {

    protected final Logger logger = LoggerFactory.getLogger(CPController.class);

    @Autowired
    protected CPService customPluginService;

    @PostConstruct
    public void init() {
        try {
            EnvConfiguration.parseEnvironment();
        } catch (InvalidEnvConfigurationException ex) {
            ex.printStackTrace();
            ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Application.class)
                    .web(WebApplicationType.NONE).run();
            SpringApplication.exit(ctx, () -> 1);
        }
    }

    @ModelAttribute(CP_REQUEST)
    public CPRequest populate(HttpServletRequest request) {
        return new CPRequest(request, new Options());
    }
}

package eu.miaplatform.customplugin.springboot;

import eu.miaplatform.service.EnvConfiguration;
import eu.miaplatform.service.EnvConfigurationException;
import io.swagger.annotations.ApiOperation;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@RestController
public abstract class CPController {

    protected final String CP_REQUEST = "CP_REQUEST";
    protected final Logger logger = LoggerFactory.getLogger(CPController.class);

    @Autowired
    protected CPService customPluginService;

    @PostConstruct
    public void init() {
        EnvConfiguration envConfiguration = new EnvConfiguration();
        try {
            envConfiguration.parseEnvironment();
        } catch (EnvConfigurationException ex) {
            ex.printStackTrace();
            ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Application.class)
                    .web(WebApplicationType.NONE).run();
            SpringApplication.exit(ctx, () -> 0);
        }
    }

    @ModelAttribute(CP_REQUEST)
    public CPRequest populate(HttpServletRequest request) {
        return new CPRequest(request, new Options());
    }

/*    @ModelAttribute(POST_DECORATOR_REQUEST)
    public PostDecoratorRequest makePostDecoratorRequest(HttpServletRequest request) {
        String body = DecoratorUtils.getBody(request);
        return PostDecoratorRequest.builder()
                .request(DecoratorRequest.builder()
                        .method(request.getMethod())
                        .path(request.getRequestURI())
                        .headers(DecoratorUtils.getHeaders(request))
                        .query(request.getQueryString())
                        .body(body)
                        .build())
                .build();
    }*/

/*in
/*    @ModelAttribute(POST_DECORATOR_REQUEST)
    public PostDecoratorRequest makePostDecoratorRequest(HttpServletRequest request) {
        String body = DecoratorUtils.getBody(request);
        return PostDecoratorRequest.builder()
                .request(DecoratorRequest.builder()
                        .method(request.getMethod())
                        .path(request.getRequestURI())
                        .headers(DecoratorUtils.getHeaders(request))
                        .query(request.getQueryString())
                        .body(body)
                        .build())
                .build();
    }*/

/*
    @ModelAttribute(PRE_DECORATOR_REQUEST)
    public PreDecoratorRequest makePreDecoratorRequest(HttpServletRequest request) {
        String body = DecoratorUtils.getBody(request);
        return PreDecoratorRequest.builder()
                .request(DecoratorRequest.builder()
                        .method(request.getMethod())
                        .path(request.getRequestURI())
                        .headers(DecoratorUtils.getHeaders(request))
                        .query(request.getQueryString())
                        .body(body)
                        .build()).build();
    }
*/

    @GetMapping("/-/healthz")
    @ApiOperation(value = "Healthz")
    @ResponseBody
    public ResponseEntity healthz(@ApiIgnore @ModelAttribute(CP_REQUEST) CPRequest cpRequest) {
        return healthinessHandler(cpRequest);
    }

    @GetMapping("/-/ready")
    @ApiOperation(value = "Ready")
    public ResponseEntity ready(@ApiIgnore @ModelAttribute(CP_REQUEST) CPRequest cpRequest) {
        return readinessHandler(cpRequest);
    }

    public ResponseEntity healthinessHandler(CPRequest cpRequest) {
        return CPStatus.statusOk(new CPStatusBody());
    }

    public ResponseEntity readinessHandler(CPRequest cpRequest) {
        return CPStatus.statusKo(new CPStatusBody());
    }
}

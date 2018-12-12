package eu.miaplatform.customplugin.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EndpointService {

    private List<Route> routes;

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @PostConstruct
    public void init() {

        for (Route route: routes) {

            RequestMethod requestMethod = null;
            switch (route.getMethod()) {
                case GET:
                    requestMethod = RequestMethod.GET;
                    break;
                case POST:
                    requestMethod = RequestMethod.POST;
                    break;
            }

            RequestMappingInfo requestMappingInfo = RequestMappingInfo
                    .paths(route.getPath())
                    .methods(requestMethod)
                    .build();

            try {
                requestMappingHandlerMapping.registerMapping(requestMappingInfo, route.getController(), CustomPluginController.class.getDeclaredMethod("handle"));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}

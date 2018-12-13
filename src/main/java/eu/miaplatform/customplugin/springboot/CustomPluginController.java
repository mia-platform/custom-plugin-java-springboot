package eu.miaplatform.customplugin.springboot;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public abstract class CustomPluginController {

    public final String CP_REQUEST = "CP_REQUEST";

    @ModelAttribute(CP_REQUEST)
    public CustomPluginRequest populate (HttpServletRequest request) {
        return new CustomPluginRequest(request, new CustomPluginOptions());
    }
}

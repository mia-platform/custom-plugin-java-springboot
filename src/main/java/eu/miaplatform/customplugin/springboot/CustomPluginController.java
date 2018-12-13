package eu.miaplatform.customplugin.springboot;

import eu.miaplatform.customplugin.springboot.CustomPluginOptions;
import eu.miaplatform.customplugin.springboot.CustomPluginRequest;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public abstract class CustomPluginController {

    public final String cpRequest = "CPREQUEST";

    @ModelAttribute(cpRequest)
    public CustomPluginRequest populate (HttpServletRequest request) {
        return new CustomPluginRequest(request, new CustomPluginOptions());
    }
}

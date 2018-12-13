package eu.miaplatform.customplugin.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public abstract class CustomPluginController {

    protected final String CP_REQUEST = "CP_REQUEST";
    protected final Logger logger = LoggerFactory.getLogger(CustomPluginController.class);

    @Autowired
    protected CustomPluginService customPluginService;

    @ModelAttribute(CP_REQUEST)
    public CustomPluginRequest populate (HttpServletRequest request) {
        return new CustomPluginRequest(request, new CustomPluginOptions());
    }
}

package eu.miaplatform.customplugin.springboot.example;

import eu.miaplatform.customplugin.springboot.CustomPluginController;
import eu.miaplatform.customplugin.springboot.CustomPluginRequest;
import eu.miaplatform.customplugin.springboot.example.model.PersonWithNews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController extends CustomPluginController {

    private final Logger logger = LoggerFactory.getLogger(CustomPluginController.class);

    @Autowired
    private MyService myService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Custom Plugin Java!";
    }

    @GetMapping("/personwithnews")
    @ResponseBody
    public PersonWithNews getPersonWithNews(@ModelAttribute(cpRequest) CustomPluginRequest customPluginRequest, @RequestParam(value="name") String name) {

        customPluginRequest.getHeadersPropagator().getHeaders().forEach(header ->
                logger.info("headerName: " + header.getName() + " - headerValue: " + header.getValue())
        );

        return myService.createPersonWithNews(customPluginRequest, name);
    }
}
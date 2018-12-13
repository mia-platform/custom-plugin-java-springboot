package eu.miaplatform.customplugin.springboot.example;

import eu.miaplatform.customplugin.ServiceClient;
import eu.miaplatform.customplugin.ServiceClientFactory;
import eu.miaplatform.customplugin.springboot.CustomPluginController;
import eu.miaplatform.customplugin.springboot.CustomPluginRequest;
import eu.miaplatform.customplugin.springboot.example.model.Author;
import eu.miaplatform.customplugin.springboot.example.model.News;
import eu.miaplatform.customplugin.springboot.example.model.PersonWithNews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class MyController extends CustomPluginController {

    private final Logger logger = LoggerFactory.getLogger(CustomPluginController.class);

    private final String CRUDPATH = "http://localhost:8080";

    @Autowired
    private MyService myService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Custom Plugin Java!";
    }

    @GetMapping("/personwithnews")
    @ResponseBody
    public PersonWithNews getPersonWithNews(@ModelAttribute(CP_REQUEST) CustomPluginRequest customPluginRequest, @RequestParam(value="name") String name) {

        customPluginRequest.getHeadersPropagator().getHeaders().forEach(header ->
                logger.info("headerName: " + header.getName() + " - headerValue: " + header.getValue())
        );

        return myService.getPersonWithNews(customPluginRequest, name);
    }

    @PostMapping("/personwithnews")
    @ResponseBody
    public void addPersonWithNews(@ModelAttribute(CP_REQUEST) CustomPluginRequest customPluginRequest, @RequestBody PersonWithNews personWithNews) {

        customPluginRequest.getHeadersPropagator().getHeaders().forEach(header ->
                logger.info("headerName: " + header.getName() + " - headerValue: " + header.getValue())
        );

        myService.createPersonAndNews(customPluginRequest, personWithNews);
    }
}
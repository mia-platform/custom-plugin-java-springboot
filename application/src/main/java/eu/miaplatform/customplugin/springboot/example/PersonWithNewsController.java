package eu.miaplatform.customplugin.springboot.example;

import eu.miaplatform.customplugin.springboot.example.model.PersonWithNews;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(value = "personwithnewscontroller", description = "Sample operations on person and news")
public class PersonWithNewsController extends CPController {

    private final String CRUDPATH = "http://localhost:8080";

    @GetMapping("/")
    @ApiOperation(value = "Sample base path")
    @ResponseBody
    public PersonWithNews index(
            @ApiIgnore @ModelAttribute(CP_REQUEST) CPRequest cpRequest) {

        cpRequest.getHeadersPropagator().getHeaders().forEach(header ->
                logger.info("headerName: " + header.getName() + " - headerValue: " + header.getValue())
        );

        return new PersonWithNews("Greetings", "From CP Java");
    }

    @GetMapping("/personwithnews")
    @ApiOperation(value = "Search a person and its news", response = PersonWithNews.class)
    @ResponseBody
    public PersonWithNews getPersonWithNews(
            @ApiIgnore @ModelAttribute(CP_REQUEST) CPRequest cpRequest,
            @RequestParam(value = "name") String name) {

        return customPluginService.addHandler(cpRequest, PersonWithNewsHandler.getPersonWithNewsHandler(name));
    }

    @GetMapping("/personwithnewsfake")
    @ApiOperation(value = "Search a person and its news", response = PersonWithNews.class)
    @ResponseBody
    public PersonWithNews getPersonWithNewsCopy(
            @ApiIgnore @ModelAttribute(CP_REQUEST) CPRequest cpRequest,
            @RequestParam(value = "name") String name) {

        return customPluginService.addHandler(cpRequest, PersonWithNewsHandler.getPersonWithNewsHandler(name));
    }

    @PostMapping("/personwithnews")
    @ApiOperation(value = "Add a person and a news")
    public void addPersonWithNews(
            @ApiIgnore @ModelAttribute(CP_REQUEST) CPRequest cpRequest,
            @RequestBody PersonWithNews personWithNews) {

        customPluginService.addHandler(cpRequest, PersonWithNewsHandler.addPersonWithNewsHandler(personWithNews));
    }
}
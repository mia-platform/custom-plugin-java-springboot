package eu.miaplatform.customplugin.springboot;

import eu.miaplatform.customplugin.CRUDServiceClient;
import eu.miaplatform.customplugin.ServiceClientFactory;
import eu.miaplatform.customplugin.springboot.lib.CPController;
import eu.miaplatform.customplugin.springboot.lib.CPRequest;
import eu.miaplatform.customplugin.springboot.model.Author;
import eu.miaplatform.customplugin.springboot.model.News;
import eu.miaplatform.customplugin.springboot.model.PersonWithNews;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@Api(value = "personwithnewscontroller", description = "Sample operations on person and news")
public class PersonWithNewsController extends CPController {

    private final String CRUDPATH = "http://localhost:8080";

    @GetMapping("/")
    @ApiOperation(value = "Sample base path")
    public String index() {
        return "Greetings from Custom Plugin Java!";
    }

    @GetMapping("/personwithnews")
    @ApiOperation(value = "Search a person and its news", response = PersonWithNews.class)
    @ResponseBody
    public PersonWithNews getPersonWithNews(
            @ApiIgnore @ModelAttribute(CP_REQUEST) CPRequest cpRequest,
            @RequestParam(value = "name") String name) {

        return customPluginService.addHandler(cpRequest, getPersonWithNewsHandler(name));
    }

    @PostMapping("/personwithnews")
    @ApiOperation(value = "Add a person and a news")
    public void addPersonWithNews(
            @ApiIgnore @ModelAttribute(CP_REQUEST) CPRequest cpRequest,
            @RequestBody PersonWithNews personWithNews) {

        customPluginService.addHandler(cpRequest, addPersonWithNewsHandler(personWithNews));
    }

    private Function<CPRequest, PersonWithNews> getPersonWithNewsHandler(String name) {

        return cpRequest -> {

            cpRequest.getHeadersPropagator().getHeaders().forEach(header ->
                    logger.info("headerName: " + header.getName() + " - headerValue: " + header.getValue())
            );

            CRUDServiceClient serviceClient = ServiceClientFactory.getCRUDServiceClient(CRUDPATH, 2, cpRequest.getHeadersPropagator());

            List<Author> authors = serviceClient.retrieveByAttribute("name", name, Author.class).collect(Collectors.toList());
            List<News> news = serviceClient.retrieveByAttribute("author", name, News.class).collect(Collectors.toList());

            if (authors.size() > 0 && news.size() > 0) {
                return new PersonWithNews(authors.get(0).getName(), news.get(0).getTitle());
            }
            return null;
        };
    }

    private Consumer<CPRequest> addPersonWithNewsHandler(PersonWithNews personWithNews) {

        return cpRequest -> {

            cpRequest.getHeadersPropagator().getHeaders().forEach(header ->
                    logger.info("headerName: " + header.getName() + " - headerValue: " + header.getValue())
            );

            Author author = new Author(personWithNews.getPersonName(), "Surname", 99);
            News news = new News(personWithNews.getNewsTitle(), "body", personWithNews.getPersonName(), "2018-12-12T16:06:59.872Z", 99);

            CRUDServiceClient serviceClient = ServiceClientFactory.getCRUDServiceClient(CRUDPATH, 2, cpRequest.getHeadersPropagator());
            String savedAuthor = serviceClient.storeSingle(author, Author.class);
            String savedNews = serviceClient.storeSingle(news, News.class);

            logger.info(savedAuthor);
            logger.info(savedNews);
        };
    }
}
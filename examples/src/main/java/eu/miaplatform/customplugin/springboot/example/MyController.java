package eu.miaplatform.customplugin.springboot.example;

import eu.miaplatform.customplugin.CRUDServiceClient;
import eu.miaplatform.customplugin.ServiceClientFactory;
import eu.miaplatform.customplugin.springboot.CustomPluginController;
import eu.miaplatform.customplugin.springboot.CustomPluginRequest;
import eu.miaplatform.customplugin.springboot.example.model.Author;
import eu.miaplatform.customplugin.springboot.example.model.News;
import eu.miaplatform.customplugin.springboot.example.model.PersonWithNews;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MyController extends CustomPluginController {

    private final String CRUDPATH = "http://localhost:8080";

    @RequestMapping("/")
    public String index() {
        return "Greetings from Custom Plugin Java!";
    }

    @GetMapping("/personwithnews")
    @ResponseBody
    public PersonWithNews getPersonWithNews(@ModelAttribute(CP_REQUEST) CustomPluginRequest customPluginRequest, @RequestParam(value = "name") String name) {

        return customPluginService.addHandler(customPluginRequest, (request -> {

            request.getHeadersPropagator().getHeaders().forEach(header ->
                    logger.info("headerName: " + header.getName() + " - headerValue: " + header.getValue())
            );

            CRUDServiceClient serviceClient = ServiceClientFactory.getCRUDServiceClient(CRUDPATH, null, request.getHeadersPropagator());

            List<Author> authors = serviceClient.retrieveByAttribute("name", name, Author.class).collect(Collectors.toList());
            List<News> news = serviceClient.retrieveByAttribute("author", name, News.class).collect(Collectors.toList());


            if (authors.size() > 0 && news.size() > 0) {
                return new PersonWithNews(authors.get(0).getName(), news.get(0).getTitle());
            }
            return null;
        }));
    }

    @PostMapping("/personwithnews")
    public void addPersonWithNews(@ModelAttribute(CP_REQUEST) CustomPluginRequest customPluginRequest, @RequestBody PersonWithNews personWithNews) {

        customPluginService.addHandler(customPluginRequest, (request -> {

            request.getHeadersPropagator().getHeaders().forEach(header ->
                    logger.info("headerName: " + header.getName() + " - headerValue: " + header.getValue())
            );

            Author author = new Author(personWithNews.getPersonName(), "Surname", 99);
            News news = new News(personWithNews.getNewsTitle(), "body", personWithNews.getPersonName(), "2018-12-12T16:06:59.872Z", 99);

            CRUDServiceClient serviceClient = ServiceClientFactory.getCRUDServiceClient(CRUDPATH, null, request.getHeadersPropagator());
            String savedAuthor = serviceClient.storeSingle(author, Author.class);
            String savedNews = serviceClient.storeSingle(news, News.class);

            logger.info(savedAuthor);
            logger.info(savedNews);
        }));
    }
}
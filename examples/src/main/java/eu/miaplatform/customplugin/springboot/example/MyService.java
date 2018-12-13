package eu.miaplatform.customplugin.springboot.example;

import eu.miaplatform.customplugin.springboot.example.model.Author;
import eu.miaplatform.customplugin.springboot.example.model.News;
import eu.miaplatform.customplugin.springboot.example.model.PersonWithNews;
import eu.miaplatform.customplugin.springboot.CustomPluginRequest;
import eu.miaplatform.customplugin.ServiceClient;
import eu.miaplatform.customplugin.ServiceClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MyService {

    private final String CRUDPATH = "http://localhost:8080";
    private final Logger logger = LoggerFactory.getLogger(MyService.class);

    public PersonWithNews getPersonWithNews(CustomPluginRequest customPluginRequest, String personName) {

        ServiceClient serviceClient = ServiceClientFactory.getCRUDServiceClient(CRUDPATH, null, customPluginRequest.getHeadersPropagator());

        List<Author> authors = serviceClient.retrieveByAttribute("name", personName, Author.class).collect(Collectors.toList());
        List<News> news = serviceClient.retrieveByAttribute("author", personName, News.class).collect(Collectors.toList());


        if (authors.size() > 0 && news.size() > 0) {
            return new PersonWithNews(authors.get(0).getName(), news.get(0).getTitle());
        }
        return null;
    }

    public void createPersonAndNews(CustomPluginRequest customPluginRequest, PersonWithNews personWithNews) {

        Author author = new Author(personWithNews.getPersonName(), "Surname", 99);
        News news = new News(personWithNews.getNewsTitle(), "body", personWithNews.getPersonName(), "2018-12-12T16:06:59.872Z", 99);

        ServiceClient serviceClient = ServiceClientFactory.getCRUDServiceClient(CRUDPATH, null, customPluginRequest.getHeadersPropagator());
        String savedAuthor = serviceClient.storeSingle(author, Author.class);
        String savedNews = serviceClient.storeSingle(news, News.class);

        logger.info(savedAuthor);
        logger.info(savedNews);
    }

/*    public PersonWithNews getPersonWithNews(CustomPluginRequest request, Function<CustomPluginRequest, PersonWithNews> handler) {
        return handler.apply(request);
    }*/
}

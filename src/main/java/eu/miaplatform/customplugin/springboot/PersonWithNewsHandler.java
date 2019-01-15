package eu.miaplatform.customplugin.springboot;

import eu.miaplatform.customplugin.CRUDServiceClient;
import eu.miaplatform.customplugin.ServiceClientFactory;
import eu.miaplatform.customplugin.springboot.lib.CPRequest;
import eu.miaplatform.customplugin.springboot.model.Author;
import eu.miaplatform.customplugin.springboot.model.News;
import eu.miaplatform.customplugin.springboot.model.PersonWithNews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

class PersonWithNewsHandler {

    private static final String CRUDPATH = "http://localhost:8080";
    private static final Logger logger = LoggerFactory.getLogger(PersonWithNewsHandler.class);

    static Function<CPRequest, PersonWithNews> getPersonWithNewsHandler(String name) {

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

    static Consumer<CPRequest> addPersonWithNewsHandler(PersonWithNews personWithNews) {

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

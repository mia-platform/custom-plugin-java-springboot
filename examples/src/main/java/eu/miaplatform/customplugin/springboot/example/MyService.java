package eu.miaplatform.customplugin.springboot.example;

import eu.miaplatform.customplugin.springboot.example.model.Author;
import eu.miaplatform.customplugin.springboot.example.model.News;
import eu.miaplatform.customplugin.springboot.example.model.PersonWithNews;
import eu.miaplatform.customplugin.springboot.CustomPluginRequest;
import eu.miaplatform.customplugin.ServiceClient;
import eu.miaplatform.customplugin.ServiceClientFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyService {

    public PersonWithNews createPersonWithNews(CustomPluginRequest customPluginRequest, String personName) {

        ServiceClient serviceClient = ServiceClientFactory.getCRUDServiceClient("http://localhost:8080", null, customPluginRequest.getHeadersPropagator());

        List<Author> authors = serviceClient.retrieveByAttribute("name", personName, Author.class).collect(Collectors.toList());
        List<News> news = serviceClient.retrieveByAttribute("author", personName, News.class).collect(Collectors.toList());


        if (authors.size() > 0 && news.size() > 0) {
            return new PersonWithNews(authors.get(0).getName(), news.get(0).getTitle());
        }
        return null;
    }
}

package eu.miaplatform.customplugin.springboot;

import com.google.gson.Gson;
import eu.miaplatform.customplugin.springboot.lib.CPDecorator;
import eu.miaplatform.customplugin.springboot.model.PersonWithNews;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Configuration
public class PersonWithNewsDecorator extends CPDecorator {


    @Bean
    public FilterRegistrationBean personWithNews() {

        return addPrePostDecorator("/personwithnews",
                request -> {
                    // PATH
                    String path = request.getPath();
                    logger.info("Path = " + path);

                    // METHOD
                    String method = request.getMethod();
                    logger.info("Method = " + method);

                    // HEADERS
                    request.addHeader("custom_request_header_name", "custom_request_header_value");

                    // BODY
                    String body;
                    try {
                        body = request.getBody();
                        logger.info("Body = " + body);
                    } catch (IOException e) {
                        logger.error("Body (error) = " + e.getMessage());
                    }

                    if (method.equals("POST")) {
                        PersonWithNews personWithNews = new PersonWithNews("fakeName", "fakeNews");
                        request.setBody(new Gson().toJson(personWithNews));
                    }

                    // QUERY PARAMETERS
                    String queryString = request.getQueryString();
                    logger.info("QueryString = " + queryString);

                    if (method.equals("GET")) {
                        Map<String, String[]> queryStringParams = request.getQueryParameters();
                        if (queryStringParams.containsKey("name")) {
                            queryStringParams.replace("name", new String[]{"fakeName"});
                        }
                    }

                    return request;
                },
                response -> {

                    //BODY
                    String body = response.getBody();
                    try {
                        response.setBody(body);
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }

                    //HEADERS
                    response.addHeader("custom_response_header_name", "custom_response_header_value");

                    //STATUS
                    logger.info("ResponseStatus = " + response.getStatus());

                    response.setStatus(401);
                });
    }

    @Bean
    public FilterRegistrationBean personWithNewsCopy() {

        return addPrePostDecorator("/personwithnewsfake",
                request -> {
                    request.addHeader("custom_request_header_name", "custom_request_header_value");
                    return request;
                },
                response -> {
                    response.addHeader("custom_response_header_name", "custom_response_header_value");

                    PersonWithNews personWithNews = new PersonWithNews("fakeName", "fakeNews");
                    try {
                        response.setBody(new Gson().toJson(personWithNews));
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                });
    }

    @Bean
    public FilterRegistrationBean index() {

        return addPreDecorator("/",
                request -> {
                    request.addHeader("custom_request_header_name", "custom_request_header_value");
                    return request;
                });
    }
}

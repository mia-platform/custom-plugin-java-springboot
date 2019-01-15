package eu.miaplatform.customplugin.springboot;

import com.google.gson.Gson;
import eu.miaplatform.customplugin.springboot.lib.CPDecorator;
import eu.miaplatform.customplugin.springboot.model.PersonWithNews;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class PersonWithNewsDecorator extends CPDecorator {


    @Bean
    public FilterRegistrationBean personWithNewsCopy() {

        return addPrePostDecorator("/personwithnewsfake",
                request -> {
                    request.addHeader("custom_request_header_name", "custom_request_header_value");
                    return request;
                },
                response -> {
                    ((HttpServletResponse) response.getResponse()).addHeader("custom_response_header_name", "custom_response_header_value");

                    PersonWithNews personWithNews = new PersonWithNews("fakeName", "fakeNews");
                    byte[] responseToSend = new Gson().toJson(personWithNews).getBytes();
                    try {
                        response.getResponse().getOutputStream().write(responseToSend);
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                });
    }

    @Bean
    public FilterRegistrationBean personWithNews() {

        return addPrePostDecorator("/personwithnews",
                request -> {
                    request.addHeader("custom_request_header_name", "custom_request_header_value");
                    return request;
                },
                response -> {
                    String responseContent = new String(response.getDataStream());
                    byte[] responseToSend = responseContent.getBytes();
                    try {
                        response.getResponse().getOutputStream().write(responseToSend);
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                    ((HttpServletResponse) response.getResponse()).addHeader("custom_response_header_name", "custom_response_header_value");
                });
    }

    /*@Bean
    public FilterRegistrationBean index() {

        return addPreDecorator("/",
                request -> {
                    request.addHeader("custom_request_header_name", "custom_request_header_value");
                    return request;
                });
    }*/
}

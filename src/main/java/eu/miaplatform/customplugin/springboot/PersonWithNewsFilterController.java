package eu.miaplatform.customplugin.springboot;

import eu.miaplatform.customplugin.springboot.lib.CPFilterController;
import eu.miaplatform.customplugin.springboot.lib.CustomRequestWrapper;
import eu.miaplatform.customplugin.springboot.lib.CustomResponseWrapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class PersonWithNewsFilterController extends CPFilterController {

    @Bean
    public FilterRegistrationBean personWithNews() {

        return addFilter("/personwithnews", (request, response, chain) -> {

            CustomRequestWrapper requestWrapper = new CustomRequestWrapper((HttpServletRequest) request);
            requestWrapper.addHeader("custom_header", "custom plugin header");

            CustomResponseWrapper customResponseWrapper = new CustomResponseWrapper((HttpServletResponse) response);

            chain.doFilter(requestWrapper, customResponseWrapper);

            String responseContent = new String(customResponseWrapper.getDataStream());
            byte[] responseToSend = responseContent.getBytes();
            response.getOutputStream().write(responseToSend);

            ((HttpServletResponse) response).addHeader("response_header", "response header");
        });
    }
}

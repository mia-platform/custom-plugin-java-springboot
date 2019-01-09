package eu.miaplatform.customplugin.springboot.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

public class CPFilter {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String filter = "filter";
    private int number = 1;

    protected FilterRegistrationBean addPrePostDecorator(String path,
                                                         Function<CustomRequestWrapper, CustomRequestWrapper> preHandler,
                                                         Consumer<CustomResponseWrapper> postHandler) {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setFilter((servletRequest, servletResponse, filterChain) -> {
            CustomRequestWrapper requestWrapper = preHandler.apply(new CustomRequestWrapper((HttpServletRequest) servletRequest));
            CustomResponseWrapper responseWrapper = new CustomResponseWrapper((HttpServletResponse) servletResponse);
            filterChain.doFilter(requestWrapper, responseWrapper);
            postHandler.accept(responseWrapper);
        });
        registration.setName(filter + number);
        number++;
        registration.addUrlPatterns(path);
        return registration;
    }


    protected FilterRegistrationBean addPreDecorator(String path, Function<CustomRequestWrapper, CustomRequestWrapper> preHandler) {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setFilter((servletRequest, servletResponse, filterChain) -> {
            CustomRequestWrapper requestWrapper = preHandler.apply(new CustomRequestWrapper((HttpServletRequest) servletRequest));
            filterChain.doFilter(requestWrapper, servletResponse);
        });
        registration.setName(filter + number);
        number++;
        registration.addUrlPatterns(path);
        return registration;
    }

    protected FilterRegistrationBean addPostDecorator(String path, Consumer<CustomResponseWrapper> postHandler) {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setFilter((servletRequest, servletResponse, filterChain) -> {
            CustomResponseWrapper responseWrapper = new CustomResponseWrapper((HttpServletResponse) servletResponse);
            filterChain.doFilter(servletRequest, responseWrapper);
            postHandler.accept(responseWrapper);
        });
        registration.setName(filter + number);
        number++;
        registration.addUrlPatterns(path);
        return registration;
    }
}

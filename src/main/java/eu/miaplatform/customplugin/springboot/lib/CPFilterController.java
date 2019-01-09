package eu.miaplatform.customplugin.springboot.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import javax.servlet.Filter;

public class CPFilterController {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    public FilterRegistrationBean addFilter(String path, Filter filter) {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.addUrlPatterns(path);
        registration.setOrder(1);

        return registration;
    }
}

package com.queryholic.spring.vanila.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author : queryholic
 * @since : 2018. 9. 2.
 */
@ComponentScan(
    basePackages = "com.queryholic.spring.vanila",
    useDefaultFilters = false,
    includeFilters = {@ComponentScan.Filter(Controller.class)})
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setViewClass(JstlView.class);
        resolver.setPrefix(VIEW_RESOLVER_PREFIX);
        resolver.setSuffix(VIEW_RESOLVER_SUFFIX);
        resolver.setOrder(Ordered.LOWEST_PRECEDENCE);

        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/");
    }
}

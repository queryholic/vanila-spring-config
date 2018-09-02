package com.queryholic.spring.vanila.config.app;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : queryholic
 * @since : 2018. 9. 2.
 */
@Configuration
@EnableTransactionManagement
@Import({DatabaseConfig.class})
@ComponentScan(
        basePackages = {"com.queryholic.spring.vanila"}, excludeFilters = {
        @ComponentScan.Filter(Controller.class),
        @ComponentScan.Filter(Configuration.class)})
@PropertySource(value = "classpath:vanila.properties")
@EnableAspectJAutoProxy
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("/WEB-INF/classes/common/messages/locale/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(60); // 1분마다 메시지파일 변경여부 검사
        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor() {
        return new MessageSourceAccessor(messageSource());
    }

}

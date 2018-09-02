package com.queryholic.spring.vanila.config.app;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author : queryholic
 * @since : 2018. 9. 2.
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = {"com.queryholic.spring.vanila.**.mapper"})
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = createDataSource();
        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
        return dataSource;
    }

    private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("/common/sql/db-schema.sql"));
        return databasePopulator;
    }

    private SimpleDriverDataSource createDataSource() {
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass(org.h2.Driver.class);
        simpleDriverDataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MVCC=TRUE");
        simpleDriverDataSource.setUsername("");
        simpleDriverDataSource.setPassword("");
        return simpleDriverDataSource;
    }


    /**
     * TransactionManager 설정
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(final DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * SqlSessionFactory 설정
     *
     * @param dataSource
     * @param applicationContext
     * @return
     * @throws IOException
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource,
                                                       ApplicationContext applicationContext) throws IOException {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:common/mybatis/config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath*:common/mybatis/mappers/**/*.xml"));

        return factoryBean;
    }

    /**
     * SqlSessionTemplate 설정
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

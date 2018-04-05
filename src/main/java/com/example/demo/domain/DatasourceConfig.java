package com.example.demo.domain;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.example.demo.domain.repository"})
@EntityScan(basePackages = {"com.example.demo.domain.entity"})
public class DatasourceConfig {

/*
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource datasource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource datasource) {
        return builder.dataSource(datasource)
                .persistenceUnit("default")
                .packages("com.example.demo.domain.entity")
                //.properties(jpaProperties())
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(factory);
        tm.setDefaultTimeout(1000);
        tm.afterPropertiesSet();
        return tm;
    }

    private Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.show_sql", true);
        props.put("hibernate.format_sql", true);
        props.put("hibernate.use_sql_comments", true);
        props.put("hibernate.generate_statistics", true);
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        return props;
    }
*/

/*
    //@Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }
*/

/*
    @Bean
    public HibernatePropertiesCustomizer customizer() {
        return (props -> {
            props.put("hibernate.show_sql", false);
            props.put("hibernate.format_sql", false);
            props.put("hibernate.use_sql_comments", false);
            props.put("hibernate.generate_statistics", false);
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        });
    }
*/

}

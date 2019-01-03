package com.dubbo.demo.springbootdubboprovider.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.dubbo.deml.springbootdubbocommon.utils.AesUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DruidDatabaseConfiguration {


    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.druid.initialSize}")
    private int initialSize;
    @Value("${spring.druid.minIdle}")
    private int minIdle;
    @Value("${spring.druid.maxActive}")
    private int maxActive;
    @Value("${spring.druid.maxWait}")
    private int maxWait;
    @Value("${spring.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.druid.validationQuery}")
    private String validationQuery;
    @Value("${spring.druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.druid.filters}")
    private String filters;
    @Value("${spring.druid.connectionProperties}")
    private String connectionProperties;

    @Bean
    @Primary
    public DruidDataSource dataSource() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(AesUtils.decrypt(username));
        datasource.setPassword(AesUtils.decrypt(password));
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setFilters(filters);
        return datasource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean
    public ServletRegistrationBean druidStatViewServle() {
        //注册服务
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 白名单(为空表示,所有的都可以访问,多个IP的时候用逗号隔开)
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow)
        servletRegistrationBean.addInitParameter("deny", "127.0.0.2");
        // 设置登录的用户名和密码
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions",
                "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        System.out.println("druid初始化成功!");
        return filterRegistrationBean;

    }

}

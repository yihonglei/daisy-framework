package com.jpeony.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import com.jpeony.common.config.properties.DruidProperties;
import com.jpeony.common.datasource.MultipleDataSource;
import com.jpeony.common.enums.DataSourceType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.servlet.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * druid 配置多数据源
 *
 * @author yihonglei
 */
@Configuration
public class DruidConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.druid.jpeony-master")
    public DataSource jpeonyMasterDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.jpeony-slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.jpeony-slave", name = "enabled", havingValue = "true")
    public DataSource jpeonySlaveDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.user-master")
    public DataSource userMasterDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.user-slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.user-slave", name = "enabled", havingValue = "true")
    public DataSource userSlaveDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean(name = "multipleDataSource")
    @Primary
    public MultipleDataSource dataSource(DataSource jpeonyMasterDataSource, DataSource jpeonySlaveDataSource,
                                         DataSource userMasterDataSource, DataSource userSlaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(16);
        targetDataSources.put(DataSourceType.JPEONY_MASTER, jpeonyMasterDataSource);
        targetDataSources.put(DataSourceType.JPEONY_SLAVE, jpeonySlaveDataSource);
        targetDataSources.put(DataSourceType.USER_MASTER, userMasterDataSource);
        targetDataSources.put(DataSourceType.USER_SLAVE, userSlaveDataSource);

        // 路由数据源
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        multipleDataSource.setDefaultTargetDataSource(jpeonyMasterDataSource);
        multipleDataSource.setTargetDataSources(targetDataSources);
        return multipleDataSource;
    }

    /**
     * 去除监控页面底部的广告
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    @ConditionalOnProperty(name = "spring.datasource.druid.statViewServlet.enabled", havingValue = "true")
    public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties) {
        // 获取web监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取common.js的配置路径
        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
        final String filePath = "support/http/resources/js/common.js";
        // 创建filter进行过滤
        Filter filter = new Filter() {
            @Override
            public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                chain.doFilter(request, response);
                // 重置缓冲区，响应头不会被重置
                response.resetBuffer();
                // 获取common.js
                String text = Utils.readFromResource(filePath);
                // 正则替换banner, 除去底部的广告信息
                text = text.replaceAll("<a.*?banner\"></a><br/>", "");
                text = text.replaceAll("powered.*?shrek.wang</a>", "");
                response.getWriter().write(text);
            }

            @Override
            public void destroy() {
            }
        };
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(commonJsPattern);
        return registrationBean;
    }
}

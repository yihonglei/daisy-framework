package com.jpeony.user.server;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import com.jpeony.user.server.interceptor.TraceLogInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static com.jpeony.commons.core.constant.InterceptorConstant.NGINX;

/**
 * @author yihonglei
 */
//@SpringBootApplication(scanBasePackages = {"com.jpeony.*"},
//        exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class,
//                RedisRepositoriesAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.jpeony.*", exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.jpeony.*")
@EnableDiscoveryClient
@MapperScan("com.jpeony.user.server.mapper")
public class UserServerApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceLogInterceptor()).addPathPatterns("/**").excludePathPatterns(NGINX);
    }

    /**
     * 不使用默认 jackson 框架解析 json，重写 configureMessageConverters，使用 fastjson 框架解析 json
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);

        List<MediaType> fastMediaTypes = Lists.newArrayList();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastMediaTypes.add(new MediaType("application", "*+json"));

        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);
    }
}

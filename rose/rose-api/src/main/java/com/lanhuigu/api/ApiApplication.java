package com.lanhuigu.api;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import com.lanhuigu.common.spring.TraceLogInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static com.lanhuigu.common.constant.InterceptorConstant.NGINX;

/**
 * 启动类
 *
 * @author yihonglei
 * @date 2019/11/16 10:57 AM
 */
@SpringBootApplication(scanBasePackages = "com.lanhuigu")
@MapperScan("com.lanhuigu.core.mapper")
public class ApiApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    /**
     * 添加日志追踪拦截器
     *
     * @author yihonglei
     * @date 2019/11/16 2:51 PM
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceLogInterceptor()).addPathPatterns("/**").excludePathPatterns(NGINX);
    }

    /**
     * 不使用默认jackson框架解析json，重写configureMessageConverters，使用fastjson框架解析json
     *
     * @author yihonglei
     * @date 2019/11/16 2:51 PM
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

package com.jpeony.boot.common.config;

import com.jpeony.boot.common.constant.CommonConstant;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Swagger2的接口配置
 *
 * @author yihonglei
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger", value = {"isShow"}, havingValue = "true")
public class SwaggerConfig {
    /**
     * 作者
     */
    @Value("${swagger.author:yhl}")
    private String author;
    /**
     * 版本
     */
    @Value("${swagger.version:V1.0}")
    private String version;
    /**
     * 文档title
     */
    @Value("${swagger.title:docTitle}")
    private String title;
    /**
     * 文档描述
     */
    @Value("${swagger.description:docDescription}")
    private String description;
    /**
     * 联系地址
     */
    @Value("${swagger.url:contractUrl}")
    private String url;
    /**
     * 联系邮箱
     */
    @Value("${swagger.email:contractEmail}")
    private String email;
    /**
     * token
     */
    @Value("${token.header:111111}")
    private String header;

    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi() {
        Parameter parameter = new ParameterBuilder()
                .name(header)
                .description("认证token")
                .modelRef(new ModelRef("string"))
                .defaultValue(CommonConstant.TOKEN_PREFIX)
                .parameterType("header")
                .required(false)
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
//                .pathMapping("/dev-api")
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                .globalOperationParameters(Arrays.asList(parameter))
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                //.apis(RequestHandlerSelectors.basePackage("com.hq.api.tool.swagger"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                /* 设置安全模式，swagger可以设置访问token */
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList<ApiKey>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    /**
     * 默认的安全上引用
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title(title)
                // 描述
                .description(description)
                // 作者信息
                .contact(new Contact(author, url, email))
                // 版本
                .version(version)
                .build();
    }
}

package com.child.box;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@EnableSwagger2
@Configuration
@ComponentScan(basePackages = {"com.child.box"})
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${swagger.show}")
    private boolean swaggerShow;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (this.swaggerShow) {
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com/child/box/api"))
                .paths(PathSelectors.any())
                .build()
                .groupName("com/child/box/api");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("前端接口文档")
                .description("此文档定义了客户端请求后台接口请求方式，请求url和返回对象，请严格按照文档格式发送请求")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket admin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .apiInfo(adminInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com/child/box/system"))
                .paths(PathSelectors.any())
                .build()
                .groupName("sys");
    }

    private ApiInfo adminInfo() {
        return new ApiInfoBuilder()
                .title("后台接口文档")
                .description("此文档定义了客户端请求后台接口请求方式，请求url和返回对象，请严格按照文档格式发送请求")
                .version("1.0")
                .build();
    }

}

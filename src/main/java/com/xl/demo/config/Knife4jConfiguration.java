package com.xl.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author XL
 * 创建Swagger配置依赖
 * @date 2022/1/12 17:04
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Value("${rick.name}")
    private String name;

    @Value("${rick.version}")
    private String version;

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(name+"API接口文档")
                        .description("# sa-token APIs")
                        .termsOfServiceUrl("https://blog.csdn.net/Ice_Rick")
                        .contact("Rick")
                        .version(version)
                        .build())
                //分组名称
                .groupName(version+"版本")
                .select()
                //这里指定Controller扫描包路径
                //.apis(RequestHandlerSelectors.basePackage("com.xl.demo"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}

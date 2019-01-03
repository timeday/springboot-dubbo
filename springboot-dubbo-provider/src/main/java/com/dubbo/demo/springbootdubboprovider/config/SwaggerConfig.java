package com.dubbo.demo.springbootdubboprovider.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@Configuration
//@EnableSwagger2
public class SwaggerConfig {
   // @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.maobc.maobcuniversaldocking"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Demo中使用Swagger2构建RESTful APIs")
                .version("1.0.0")
                .build();
    }



/*@Configuration
@EnableSwagger2
//@Profile({"dev"})
public class SwaggerConfig {

    @Bean
    Docket createRestApi() {
     *//*   ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("dicAccessToken").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        tokenPar.name("version").description("版本").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());*//*
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.maobc.maobcuniversaldocking"))
                .paths(PathSelectors.any()).build();
                *//*.build().globalOperationParameters(pars);*//*
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("白吃猫清分提现接口")
                .description("用户微服务接口")
                .version("1.0")
                .build();
    }*/

}

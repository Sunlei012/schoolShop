package com.sunlei.schoolshop.Config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfig {
    @Bean
    public Docket userDocket(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("用户相关接口文档").select().paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.sunlei.schoolshop.Controller.UserController")).build();
    }

    @Bean
    public Docket goodsDocket(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("商品相关接口文档").select().paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.sunlei.schoolshop.Controller.GoodsController")).build();
    }

    @Bean
    public Docket dealDocket(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("订单相关接口文档").select().paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.sunlei.schoolshop.Controller.DealController")).build();
    }
}

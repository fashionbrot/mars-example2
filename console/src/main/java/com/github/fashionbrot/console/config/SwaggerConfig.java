
package com.github.fashionbrot.console.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
* swagger2 配置
*
* @author fashionbrot
* @email fashionbrot@163.com
* @date 2020-08-25
*/

@Configuration
@EnableSwagger2
@Profile({"dev","test"}) //@Profile({"dev","{pro}"}) 这样写就是两个环境都显示-----设置要显示的工作环境（控制那个环境显示接口文档）
public class SwaggerConfig {


    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        // 设置swagger-ui.html页面上的一些元素信息。
        .apiInfo(metaData())
        .select()
        // 扫描的包路径
        .apis(RequestHandlerSelectors.withClassAnnotation(Controller.class))
        // 定义要生成文档的Api的url路径规则
        .paths(PathSelectors.any())
        .build();
    }


    private ApiInfo metaData() {
        return new ApiInfoBuilder()
        //接口标题
        .title("RESTful APIs")
        //副标题
        .description("RESTful APIs")
        //接口路径
        .termsOfServiceUrl("Http://localhost:8888")
        //创建人
        .contact("fashionbrot")
        //版本
        .version("0.0.1")
        .build();
    }

}

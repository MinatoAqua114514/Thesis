package com.thesis.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SwaggerConfig 类用于配置 Swagger 文档生成器，以便为毕业论文管理系统 API 自动生成详细的接口文档。
 * 该类通过 Spring 的 {@link Configuration} 注解标记为配置类，并提供了一个 {@link Bean} 定义来创建 {@link OpenAPI} 实例。
 * {@link #springShopOpenAPI()} 方法初始化 {@link OpenAPI} 对象并设置基本信息，如标题、描述、作者、版本和许可证等。
 *
 * <p>此外，{@link #getApiInfo()} 方法封装了 {@link Info} 对象的构建过程，详细定义了 API 文档的元数据。
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                // 配置接口文档基本信息
                .info(this.getApiInfo());
    }

    private Info getApiInfo() {
        return new Info()
                // 接口文档标题
                .title("毕业论文管理系统 API")
                // 接口文档描述
                .description("毕业论文 API 示例文档")
                // 接口作者信息
                .contact(new Contact().name("陈佳林").email("2314445395@qq.com"))
                // 概述信息
                .summary("毕业论文管理系统 API")
                // 接口版本
                .version("1.0.0")
                // 接口文档许可证信息
                .license(new License().name("Apache 2.0").url("https://springdoc.org"));
    }
}

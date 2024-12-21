package com.thesis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CrossConfig 类用于配置跨域资源共享（CORS）策略。
 * 通过实现 WebMvcConfigurer 接口，此配置类自定义了所有HTTP请求的CORS规则，
 * 允许来自任何源的请求访问，支持GET、POST、PUT和DELETE方法，并允许携带任何头部信息。
 * 同时，它启用了凭据（cookies）的传递，并设置了预检请求的有效时间为3600秒。
 */
// https://blog.csdn.net/weixin_44985880/article/details/120620207
@Configuration
public class CrossConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}

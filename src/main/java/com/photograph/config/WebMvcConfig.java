package com.photograph.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by Eminem on 2018/1/12.
 */
@EnableWebMvc
@Configuration
@ComponentScan
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Value("${cbs.imagesPath}")
    private String imagesPath;

    /**
     * 处理静态文件
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        if (imagesPath.equals("") || imagesPath.equals("${cbs.imagesPath}")){
            String imgPath = WebMvcConfig.class.getClassLoader().getResource("").getPath();
            imgPath = imgPath.substring(0,imgPath.lastIndexOf("/"))+"/uploadImage/";
            imagesPath = imgPath;
        }
        registry.addResourceHandler("/static/**","/uploadImage/**").addResourceLocations("classpath:/static/",imagesPath);
    }

    /**
     * cors全局跨域
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("PUT","DELETE","GET","POST")
                        .allowedHeaders("*")
                        .exposedHeaders("access-control-allow-headers",
                                "access-control-allow-methods",
                                "access-control-allow-origin",
                                "access-control-max-age",
                                "X-Frame-Options")
                        .allowCredentials(false).maxAge(3600);
            }
        };
    }
}
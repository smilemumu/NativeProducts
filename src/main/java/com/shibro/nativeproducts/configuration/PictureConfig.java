package com.shibro.nativeproducts.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class PictureConfig extends WebMvcConfigurerAdapter {


    @Value("${file.path.local.url}")
    private String  localPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/picture/**").addResourceLocations("file:"+localPath);
        super.addResourceHandlers(registry);
    }
}

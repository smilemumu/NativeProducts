package com.shibro.nativeproducts.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = "classpath:/config/spring/root-spring.xml")
public class SpringConfig {

}

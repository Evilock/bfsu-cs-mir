package com.shiye.mir;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * 启动类
 * @author fangshaozu_sx
 */
@MapperScan("com.shiye.mir.dao.mybatis")
@SpringBootApplication
public class MirApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(MirApplication.class, args);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }
}

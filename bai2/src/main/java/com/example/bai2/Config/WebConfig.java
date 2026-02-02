package com.example.bai2.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Đường dẫn đến thư mục chứa ảnh (nơi ProductService lưu vào)
        Path uploadDir = Paths.get("static/images");
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        // Map URL /images/** vào thư mục vật lý static/images/
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/" + uploadPath + "/");
    }
}
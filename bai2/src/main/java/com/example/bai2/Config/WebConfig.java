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
        // Lấy đường dẫn tuyệt đối của thư mục "uploads"
        Path uploadDir = Paths.get("uploads");
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        // Cấu hình: Nếu URL là /images/** thì tìm trong thư mục vật lý uploads/
        // Lưu ý dấu "/" ở cuối rất quan trọng
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + uploadPath + "/");

        // Nếu chạy Windows mà vẫn không được, hãy thử dùng:
        // .addResourceLocations("file:///" + uploadPath + "/");
    }
}
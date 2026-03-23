package com.example.__LEVANDINH.config;

import com.example.__LEVANDINH.entity.Category;
import com.example.__LEVANDINH.entity.Product;
import com.example.__LEVANDINH.repository.CategoryRepository;
import com.example.__LEVANDINH.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;
    @Override
    public void run(String... args) {
        if (categoryRepo.count() == 0) { // Chỉ nạp nếu DB trống
            Category c1 = categoryRepo.save(new Category(null, "Điện thoại", false, null));
            Category c2 = categoryRepo.save(new Category(null, "Laptop", false, null));
            productRepo.save(new Product(null, "iPhone 15 Pro", "https://hoanghamobile.com/tin-tuc/wp-content/uploads/2025/12/hinh-nen-iphone-dep-thumbnail.jpg\", 20000.0, false, c1", 25000000.0, false, c1));
            productRepo.save(new Product(null, "Samsung S24", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPGIP5sA3aR9klgbcqjqnRMY2_PCLrt9xtBA&s", 22000000.0, false, c1));
            productRepo.save(new Product(null, "iPhone 15s Pro", "https://hoanghamobile.com/tin-tuc/wp-content/uploads/2025/12/hinh-nen-iphone-dep-thumbnail.jpg\", 20000.0, false, c", 25000000.0, false, c1));
            productRepo.save(new Product(null, "Samsung S24s", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPGIP5sA3aR9klgbcqjqnRMY2_PCLrt9xtBA&s", 22000000.0, false, c1));
            productRepo.save(new Product(null, "iPhone 15e Pro", "https://hoanghamobile.com/tin-tuc/wp-content/uploads/2025/12/hinh-nen-iphone-dep-thumbnail.jpg\", 20000.0, false, c1", 25000000.0, false, c1));
            productRepo.save(new Product(null, "Samsung S24e    ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPGIP5sA3aR9klgbcqjqnRMY2_PCLrt9xtBA&s", 22000000.0, false, c1));
            System.out.println(">> Đã nạp dữ liệu vào MySQL thành công!");
        }
    }
//    @Override
//    public void run(String... args) {
//        if (categoryRepo.count() == 0) {
//            Category c1 = categoryRepo.save(new Category(null, "Điện thoại", false, null));
//            Category c2 = categoryRepo.save(new Category(null, "Laptop", false, null));
//
//            productRepo.save(new Product(null, "iPhone 15", "https://hoanghamobile.com/tin-tuc/wp-content/uploads/2025/12/hinh-nen-iphone-dep-thumbnail.jpg", 20000.0, false, c1));
//            productRepo.save(new Product(null, "Samsung S23", "https://www.freepik.com/free-psd/google-pixel-7-pro-mockup_230254934.htm#fromView=keyword&page=1&position=0&uuid=5ee59efb-1cbd-4ae9-8b82-4dd37c1f1400&query=Samsung+s23", 18000.0, false, c1));
//            productRepo.save(new Product(null, "MacBook M2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPGIP5sA3aR9klgbcqjqnRMY2_PCLrt9xtBA&s", 30000.0, false, c2));
//            System.out.println(">> Đã nạp dữ liệu mẫu thành công!");
//        }
//    }
}

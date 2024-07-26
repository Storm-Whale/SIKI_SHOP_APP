package com.whale.siki_shop_app.repository;

import com.whale.siki_shop_app.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

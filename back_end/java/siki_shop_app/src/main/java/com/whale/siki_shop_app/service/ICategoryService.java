package com.whale.siki_shop_app.service;

import com.whale.siki_shop_app.entity.Category;

import java.util.List;

public interface ICategoryService {

    Category storeCategory(Category category);

    Category getCategoryById(int categoryId);

    List<Category> getAllCategories();

    Category updateCategory(int categoryId, Category category);

    void deleteCategory(int categoryId);
}
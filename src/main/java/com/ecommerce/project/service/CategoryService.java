package com.ecommerce.project.service;

import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


public interface CategoryService {
    CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(@PathVariable long categoryId);
    CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable long categoryId);
}

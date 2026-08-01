package com.ecommerce.project.service;

import com.ecommerce.project.Exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(Long categoryId, ProductDTO productDTO) {
        // First check if the category is actually exists
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category Id not found", "categoryId", "categoryId"));

        double discountAmount = (productDTO.getDiscount() / 100) * productDTO.getPrice();
        double specialPrice   = productDTO.getPrice() - discountAmount;

        productDTO.setSpecialPrice(specialPrice);
        productDTO.setId(null);

        productDTO.setImage("default.png"); // we'll get back for it later

        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        savedProduct.setCategory(category);

        return modelMapper.map(savedProduct, ProductDTO.class);

    }
}

package com.educative.ecommerce.service;

import com.educative.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void getProduct(){

    }
}

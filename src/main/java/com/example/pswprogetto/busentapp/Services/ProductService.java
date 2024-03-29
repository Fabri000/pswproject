package com.example.pswprogetto.busentapp.Services;

import com.example.pswprogetto.busentapp.Entities.Product;
import com.example.pswprogetto.busentapp.Exceptions.ProductAlreadyExistException;
import com.example.pswprogetto.busentapp.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Transactional
    public void addProduct(Product p) throws ProductAlreadyExistException {
        if(productRepository.existsByCode(p.getCode()))
            throw new ProductAlreadyExistException();
        productRepository.save(p);
    }
    @Transactional(readOnly = true)
    public List<Product> getAll(){
        return productRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<Product> getProductThatContains(String name){ return  productRepository.findByNameContaining(name);}
}

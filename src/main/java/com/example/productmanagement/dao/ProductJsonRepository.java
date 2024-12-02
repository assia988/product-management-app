package com.example.productmanagement.dao;

import com.example.productmanagement.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductJsonRepository {

    private final File jsonFile = new File("products.json");
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Product> findAll() {
        try {
            if (jsonFile.exists()) {
                return objectMapper.readValue(jsonFile, new TypeReference<List<Product>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Optional<Product> findById(Long id) {
        return findAll().stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    public Product save(Product product) {
        List<Product> products = findAll();
        if (product.getId() == null) {
            product.setId((long) (products.size() + 1));
        }
        products.removeIf(p -> p.getId().equals(product.getId()));
        products.add(product);
        writeToFile(products);
        return product;
    }

    public void deleteById(Long id) {
        List<Product> products = findAll();
        products.removeIf(product -> product.getId().equals(id));
        writeToFile(products);
    }

    private void writeToFile(List<Product> products) {
        try {
            objectMapper.writeValue(jsonFile, products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

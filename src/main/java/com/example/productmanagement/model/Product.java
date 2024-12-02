package com.example.productmanagement.model;

import com.example.productmanagement.enums.InventoryStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Product {
    private Long id; // Changed to Long for compatibility with databases
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private String internalReference;
    private Long shellId; // Changed to Long for compatibility
    private InventoryStatus inventoryStatus;
    private int rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

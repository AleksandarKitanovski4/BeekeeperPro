package com.example.beekeeperpro.product.model;

import com.example.beekeeperpro.product.enums.ProductType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductFinance {

    private int id;
    private int beehiveId;
    private ProductType productType;
    private BigDecimal extractedKg;
    private BigDecimal soldKg;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductFinance(int id, int beehiveId, ProductType productType, BigDecimal extractedKg, BigDecimal soldKg,
                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.beehiveId = beehiveId;
        this.productType = productType;
        this.extractedKg = extractedKg;
        this.soldKg = soldKg;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBeehiveId() {
        return beehiveId;
    }

    public void setBeehiveId(int beehiveId) {
        this.beehiveId = beehiveId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public BigDecimal getExtractedKg() {
        return extractedKg;
    }

    public void setExtractedKg(BigDecimal extractedKg) {
        this.extractedKg = extractedKg;
    }

    public BigDecimal getSoldKg() {
        return soldKg;
    }

    public void setSoldKg(BigDecimal soldKg) {
        this.soldKg = soldKg;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getRemainingKg() {
        return extractedKg.subtract(soldKg);
    }
}

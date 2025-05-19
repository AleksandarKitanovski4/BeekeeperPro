package com.example.beekeeperpro.product.model;

import com.example.beekeeperpro.product.enums.ProductType;

import java.math.BigDecimal;

public class ProductStock {

    private int id;
    private ProductType productType;
    private BigDecimal extractedKg;
    private BigDecimal soldKg;

    public ProductStock(int id, ProductType productType, BigDecimal extractedKg, BigDecimal soldKg) {
        this.id = id;
        this.productType = productType;
        this.extractedKg = extractedKg;
        this.soldKg = soldKg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public BigDecimal getRemainingKg() {
        return extractedKg.subtract(soldKg);
    }
}

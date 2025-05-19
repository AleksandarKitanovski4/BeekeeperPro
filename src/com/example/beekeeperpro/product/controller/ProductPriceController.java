package com.example.beekeeperpro.product.controller;

import com.example.beekeeperpro.product.enums.Currency;
import com.example.beekeeperpro.product.enums.ProductType;
import com.example.beekeeperpro.product.model.PriceInfo;
import com.example.beekeeperpro.product.service.ProductPriceService;

import java.math.BigDecimal;
import java.util.Map;

public class ProductPriceController {

    private final ProductPriceService productPriceService;

    public ProductPriceController(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    public void showPrice(ProductType productType) {
        PriceInfo info = productPriceService.getPriceInfo(productType);
        System.out.println("Price for " + productType + ": " + info.price() + " " + info.currency());
    }

    public void showAllPrices() {
        Map<ProductType, PriceInfo> map = productPriceService.getAllPrices();
        System.out.println("All product prices: ");
        map.forEach((type, info) -> System.out.println(type + ": " + info.price() + " " + info.currency()));
    }

    public void updatePrice(ProductType productType, BigDecimal newPrice, Currency currency) {
        productPriceService.updatePrice(productType, newPrice, currency);
        System.out.println("Price updated for " + productType + ": " + newPrice + " " + currency);
    }

    public void convertAndShow(ProductType productType, Currency toCurrency) {
        BigDecimal converted = productPriceService.getPriceIn(productType, toCurrency);
        System.out.println(productType + " price in " + toCurrency + ": " + converted);
    }

    public void updateExchangeRate(BigDecimal newRate) {
        productPriceService.updateExchangeRate(newRate);
        System.out.println("Exchange rate updated to " + newRate);
    }
}

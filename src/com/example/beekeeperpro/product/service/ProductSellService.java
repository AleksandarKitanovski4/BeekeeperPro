package com.example.beekeeperpro.product.service;

import com.example.beekeeperpro.product.enums.Currency;
import com.example.beekeeperpro.product.enums.ProductType;

import java.math.BigDecimal;

public class ProductSellService {

    private final ProductFinanceService financeService;
    private final ProductStockService stockService;
    private final ProductPriceService priceService;

    public ProductSellService(ProductFinanceService financeService, ProductStockService stockService, ProductPriceService priceService) {
        this.financeService = financeService;
        this.stockService = stockService;
        this.priceService = priceService;
    }

    public void sellProduct(int beehiveNumber, ProductType productType, BigDecimal quantitySold) {
        BigDecimal[] stock = stockService.getStockByProduct(productType);
        BigDecimal extracted = stock[0];
        BigDecimal alreadySold = stock[1];
        BigDecimal remaining = extracted.subtract(alreadySold);

        if (remaining.compareTo(quantitySold) < 0) {
            throw new IllegalArgumentException("Not enough stock. Available: " + remaining + " " + productType.getUnitOfMeasure());
        }

        stockService.reduceStock(productType, quantitySold);

        financeService.addFinanceRecordByBeehiveNumber(beehiveNumber, productType, BigDecimal.ZERO, quantitySold);

        BigDecimal priceMkd = priceService.getPriceIn(productType, Currency.MKD).multiply(quantitySold);
        System.out.println("Sold: " + quantitySold + " " + productType + " for " + priceMkd + " MKD.");
    }
}

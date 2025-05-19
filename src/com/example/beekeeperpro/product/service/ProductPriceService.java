package com.example.beekeeperpro.product.service;

import com.example.beekeeperpro.product.enums.Currency;
import com.example.beekeeperpro.product.enums.ProductType;
import com.example.beekeeperpro.product.model.PriceInfo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;

public class ProductPriceService {

    private final Map<ProductType, PriceInfo> productPrices = new EnumMap<>(ProductType.class);

    private BigDecimal exchangeRateEurToMkd = BigDecimal.valueOf(61.5);

    public ProductPriceService() {
        productPrices.put(ProductType.HONEY,new PriceInfo(BigDecimal.valueOf(600), Currency.MKD));
        productPrices.put(ProductType.WAX, new PriceInfo(BigDecimal.valueOf(1000), Currency.MKD));
        productPrices.put(ProductType.POLLEN, new PriceInfo(BigDecimal.valueOf(20), Currency.MKD));
        productPrices.put(ProductType.BEE_QUEEN, new PriceInfo(BigDecimal.valueOf(1000), Currency.MKD));
        productPrices.put(ProductType.PROPOLIS, new PriceInfo(BigDecimal.valueOf(40), Currency.MKD));
        productPrices.put(ProductType.ROYAL_JELLY, new PriceInfo(BigDecimal.valueOf(150), Currency.MKD));
        productPrices.put(ProductType.BEE_NUCLEUS, new PriceInfo(BigDecimal.valueOf(4500), Currency.MKD));
    }

    public PriceInfo getPriceInfo(ProductType productType) {
        return productPrices.get(productType);
    }

    public void updatePrice(ProductType productType, BigDecimal newPrice, Currency newCurrency) {
        productPrices.put(productType, new PriceInfo(newPrice, newCurrency));
    }

    public Map<ProductType, PriceInfo> getAllPrices() {
        return productPrices;
    }

    public BigDecimal convertPrice(BigDecimal amount, Currency from, Currency to) {
        if (from == to) {
            return amount;
        } else if (from == Currency.MKD && to == Currency.EUR) {
            return amount.divide(exchangeRateEurToMkd, 2, RoundingMode.HALF_UP);
        } else if (from == Currency.EUR && to == Currency.MKD) {
            return amount.multiply(exchangeRateEurToMkd);
        } else {
            throw new IllegalArgumentException("Unsupported currency conversion");
        }
    }

    public BigDecimal getPriceIn(ProductType productType, Currency desiredCurrency) {
        PriceInfo info = getPriceInfo(productType);
        return convertPrice(info.price(), info.currency(), desiredCurrency);
    }

    public void updateExchangeRate(BigDecimal newRate) {
        this.exchangeRateEurToMkd = newRate;
    }
}

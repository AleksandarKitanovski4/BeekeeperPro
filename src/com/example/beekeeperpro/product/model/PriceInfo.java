package com.example.beekeeperpro.product.model;

import com.example.beekeeperpro.product.enums.Currency;

import java.math.BigDecimal;

public record PriceInfo(BigDecimal price, Currency currency) {}

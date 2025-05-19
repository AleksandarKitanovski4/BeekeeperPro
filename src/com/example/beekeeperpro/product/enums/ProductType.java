package com.example.beekeeperpro.product.enums;

public enum ProductType {
    HONEY(UnitOfMeasure.KG),
    WAX(UnitOfMeasure.KG),
    POLLEN(UnitOfMeasure.GRAM),
    BEE_QUEEN(UnitOfMeasure.PIECE),
    PROPOLIS(UnitOfMeasure.GRAM),
    ROYAL_JELLY(UnitOfMeasure.GRAM),
    BEE_NUCLEUS(UnitOfMeasure.PIECE);

    private final UnitOfMeasure unitOfMeasure;

    ProductType(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }
}

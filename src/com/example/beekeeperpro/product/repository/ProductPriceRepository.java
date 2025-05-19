package com.example.beekeeperpro.product.repository;

import com.example.beekeeperpro.product.enums.Currency;
import com.example.beekeeperpro.product.enums.ProductType;
import com.example.beekeeperpro.product.model.PriceInfo;
import com.example.beekeeperpro.repository.DbConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.Map;

public class ProductPriceRepository {

    public void insertPrice(ProductType productType, BigDecimal price, Currency currency) {

        String sql = "INSERT INTO product_price (product_type, price_per_unit, currency, last_updated) " +
                "VALUES (?,?,?, now())";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productType.name());
            stmt.setBigDecimal(2, price);
            stmt.setString(3, currency.name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePrice(ProductType productType, BigDecimal price, Currency currency) {

        String sql = "UPDATE product_price SET price_per_unit = ?, currency = ?, last_updated = now() " +
                "WHERE product_type = ?";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, price);
            stmt.setString(2, currency.name());
            stmt.setString(3, productType.name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PriceInfo getPriceByProductType(ProductType productType) {

        String sql = "SELECT price_per_unit, currency FROM product_price " +
                "WHERE product_type = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productType.name());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PriceInfo(rs.getBigDecimal("price_per_unit"),
                        Currency.valueOf(rs.getString("currency")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<ProductType, PriceInfo> getAllPrices() {
        Map<ProductType, PriceInfo> prices = new EnumMap<>(ProductType.class);
        String sql = "SELECT product_type, price_per_unit, currency FROM product_price";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                prices.put(ProductType.valueOf(rs.getString("product_type")),
                        new PriceInfo(rs.getBigDecimal("price_per_unit"),
                                Currency.valueOf(rs.getString("currency"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prices;
    }
}

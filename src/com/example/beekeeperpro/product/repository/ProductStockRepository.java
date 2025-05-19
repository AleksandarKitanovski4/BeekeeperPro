package com.example.beekeeperpro.product.repository;

import com.example.beekeeperpro.product.enums.ProductType;
import com.example.beekeeperpro.repository.DbConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.EnumMap;
import java.util.Map;

public class ProductStockRepository {

    public void insertStock(ProductType productType, BigDecimal totalExtracted, BigDecimal totalSold) {
        String sql = "INSERT INTO product_stock (product_type, total_extracted, total_sold, created_at) " +
                "VALUES (?, ?, ?, now())";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productType.name());
            stmt.setBigDecimal(2, totalExtracted);
            stmt.setBigDecimal(3, totalSold);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStock(ProductType productType, BigDecimal newTotalExtracted, BigDecimal newTotalSold) {
        String sql = "UPDATE product_stock SET total_extracted = ?, total_sold = ?, updated_at = now() " +
                "WHERE product_type = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, newTotalExtracted);
            stmt.setBigDecimal(2, newTotalSold);
            stmt.setString(3, productType.name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reduceStock(ProductType productType, BigDecimal quantity) {
        String sql = "UPDATE product_stock SET total_sold = total_sold + ?, updated_at = now() " +
                "WHERE product_type = ?";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, quantity);
            stmt.setString(2, productType.name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BigDecimal[] getStockByProductType(ProductType productType) {
        String sql = "SELECT total_extracted, total_sold FROM product_stock WHERE product_type = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productType.name());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new BigDecimal[]{
                        rs.getBigDecimal("total_extracted"),
                        rs.getBigDecimal("total_sold")
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO};
    }

    public Map<ProductType, BigDecimal[]> getAllStock() {
        Map<ProductType, BigDecimal[]> stockMap = new EnumMap<>(ProductType.class);
        String sql = "SELECT product_type, total_extracted, total_sold FROM product_stock";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                stockMap.put(ProductType.valueOf(rs.getString("product_type")),
                        new BigDecimal[]{
                                rs.getBigDecimal("total_extracted"),
                                rs.getBigDecimal("total_sold")
                        });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockMap;
    }
}

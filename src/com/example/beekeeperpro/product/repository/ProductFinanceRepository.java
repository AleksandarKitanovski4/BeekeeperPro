package com.example.beekeeperpro.product.repository;

import com.example.beekeeperpro.product.enums.ProductType;
import com.example.beekeeperpro.repository.DbConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductFinanceRepository {

    public void insertFinance(int beehiveId, ProductType productType, BigDecimal extractedKg, BigDecimal soldKg) {

        String sql = "INSERT INTO product_finance (beehive_id, product_type, extracted_kg, sold_kg, created_at) " +
                "VALUES (?,?,?,?, now())";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, beehiveId);
            stmt.setString(2, productType.name());
            stmt.setBigDecimal(3, extractedKg);
            stmt.setBigDecimal(4, soldKg);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertFinanceByBeehiveNumber(int beehiveNumber, ProductType productType,BigDecimal extractedKg, BigDecimal soldKg) {

        String sql = "SELECT id FROM beehive WHERE number = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, beehiveNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int beehiveId = rs.getInt("id");
                insertFinance(beehiveId, productType, extractedKg, soldKg);
            } else {
                throw new IllegalArgumentException("Beehive with number " + beehiveNumber + " not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSoldKg(int financeId, BigDecimal soldKg) {

        String sql = "UPDATE product_finance SET sold_kg = ?, updated_at = now() " +
                "WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, soldKg);
            stmt.setInt(2, financeId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> getFinanceByBeehiveNumber(int beehiveNumber) {
        List<Object[]> list = new ArrayList<>();

        String sql = "SELECT pf.id, pf.product_type, pf.extracted_kg, pf.sold_kg, pf.created_at FROM product_finance pf " +
                "JOIN beehive b ON pf.beehive_id = b.id WHERE b.number = ?";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, beehiveNumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Object[]{
                        rs.getInt("id"),
                        rs.getString("product_type"),
                        rs.getBigDecimal("extracted_kg"),
                        rs.getBigDecimal("sold_kg"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Object[]> getFinanceByApiaryName(String apiaryName) {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT pf.id, pf.product_type, pf.extracted_kg, pf.sold_kg, pf.created_at FROM product_finance pf " +
                "JOIN beehive b ON pf.beehive_id = b.id " +
                "JOIN apiary a ON b.apiary_id = a.id " +
                "WHERE b.name = ?";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, apiaryName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Object[]{
                        rs.getInt("id"),
                        rs.getString("product_type"),
                        rs.getBigDecimal("extracted_kg"),
                        rs.getBigDecimal("sold_kg"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

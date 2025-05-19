package com.example.beekeeperpro.repository;

import com.example.beekeeperpro.model.Inspection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InspectionRepository {

    public boolean existsByDateAndBeehiveNumber(LocalDate date, int beehiveNumber) {
        String sql = "SELECT COUNT(*) FROM inspection i " +
                "JOIN beehive b ON i.hive_id = b.id " +
                "WHERE i.inspection_date = ? AND b.number = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(date));
            stmt.setInt(2, beehiveNumber);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insert(Inspection inspection) {
        String sql = "INSERT INTO inspection (hive_id, user_id, inspection_date, has_queen, notes, issues, intervention) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, inspection.getHiveId());
            stmt.setInt(2, inspection.getUserId());
            stmt.setDate(3, Date.valueOf(inspection.getInspectionDate()));
            stmt.setBoolean(4, inspection.hasQueen());
            stmt.setString(5, inspection.getNotes());
            stmt.setString(6, inspection.getIssues());
            stmt.setString(7, inspection.getIntervention());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Inspection> findByBeehiveNumber(int beehiveNumber) {
        List<Inspection> inspections = new ArrayList<>();
        String sql = "SELECT i.* FROM inspection i " +
                "JOIN beehive b ON i.hive_id = b.id " +
                "WHERE b.number = ? " +
                "ORDER BY i.inspection_date DESC ";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, beehiveNumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                inspections.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inspections;
    }

    public List<Inspection> findByInspectionDate(LocalDate date) {
        List<Inspection> inspections = new ArrayList<>();
        String sql = "SELECT * FROM inspection " +
                "WHERE inspection_date = ? " +
                "ORDER BY hive_id ";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                inspections.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inspections;
    }

    public List<Inspection> findAll() {
        List<Inspection> inspections = new ArrayList<>();
        String sql = "SELECT * FROM inspection " +
                "ORDER BY inspection_date DESC ";

        try (Connection conn = DbConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                inspections.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inspections;
    }

    public void deleteByInspectionDate(LocalDate date) {
        String sql = "DELETE FROM inspection " +
                "WHERE inspection_date = ? ";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(date));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Inspection mapRow(ResultSet rs) throws SQLException {
        return new Inspection(
                rs.getInt("id"),
                rs.getInt("hive_id"),
                rs.getInt("user_id"),
                rs.getDate("inspection_date").toLocalDate(),
                rs.getBoolean("has_queen"),
                rs.getString("notes"),
                rs.getString("issues"),
                rs.getString("intervention")
        );
    }
}
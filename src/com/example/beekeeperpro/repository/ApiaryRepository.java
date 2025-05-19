package com.example.beekeeperpro.repository;

import com.example.beekeeperpro.model.Apiary;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApiaryRepository {

    public boolean existByName(String name) {
        String sql = "SELECT COUNT(*) FROM apiary WHERE name = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Apiary> getAllApiaries() {
        List<Apiary> apiaries = new ArrayList<>();

        String sql = "SELECT id, user_id, name, location, start_date FROM apiary";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                LocalDate startDate = rs.getDate("start_date").toLocalDate();

                apiaries.add(new Apiary(id, userId, name, location, startDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apiaries;
    }

    public void insertApiary(Apiary apiary) {
        String sql = "INSERT INTO apiary (user_id, name, location, start_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, apiary.getUserId());
            stmt.setString(2, apiary.getName());
            stmt.setString(3, apiary.getLocation());
            stmt.setDate(4, Date.valueOf(apiary.getStartDate()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Apiary> searchApiariesByName(String keyword) {
        List<Apiary> result = new ArrayList<>();

        String sql = "SELECT * FROM apiary WHERE LOWER(name) LIKE LOWER(?)";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new Apiary(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getDate("start_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateApiary(Apiary apiary) {
        String sql = "UPDATE apiary SET user_id = ?, location = ?, start_date = ? WHERE name = ?";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, apiary.getUserId());
            stmt.setString(2, apiary.getLocation());
            stmt.setDate(3, Date.valueOf(apiary.getStartDate()));
            stmt.setString(4, apiary.getName());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteApiary(String name) {
        String sql = "DELETE FROM apiary WHERE name = ?";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllByUserId(int userId) {
        String sql = "DELETE FROM apiary WHERE user_id = ?";

        try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.example.beekeeperpro.repository;

import com.example.beekeeperpro.model.Beehive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeehiveRepository {

    public boolean existByNumber(int number) {
        String sql = "SELECT COUNT(*) FROM beehive WHERE number = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, number);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Beehive findBeehiveByNumber(int number) {
        String sql = "SELECT * FROM beehive WHERE number = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, number);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Beehive(
                        rs.getInt("id"),
                        rs.getInt("number"),
                        rs.getString("type"),
                        rs.getString("status"),
                        rs.getInt("apiary_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Beehive> getBeehivesByApiaryId(int apiaryId) {
        List<Beehive> list = new ArrayList<>();

        String sql = "SELECT * FROM beehive WHERE apiary_id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, apiaryId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Beehive(
                        rs.getInt("id"),
                        rs.getInt("number"),
                        rs.getString("type"),
                        rs.getString("status"),
                        rs.getInt("apiary_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertBeehive(Beehive beehive) {
        String sql = "INSERT INTO beehive (number, type, status, apiary_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, beehive.getNumber());
            stmt.setString(2, beehive.getType());
            stmt.setString(3, beehive.getStatus());
            stmt.setInt(4, beehive.getApiaryId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBeehive(Beehive beehive) {
        String sql = "UPDATE beehive SET type = ?, status = ?, apiary_id = ? WHERE number = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, beehive.getType());
            stmt.setString(2, beehive.getStatus());
            stmt.setInt(3, beehive.getApiaryId());
            stmt.setInt(4, beehive.getNumber());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBeehive(int number) {
        String sql = "DELETE FROM beehive WHERE number = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, number);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package org.iss2024.bug_detection.Repository;

import org.iss2024.bug_detection.Domain.Programmer;
import org.iss2024.bug_detection.Utility.DB_Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepoProgrammer implements Repo_Programmer_Interface {


    private final DB_Utils DB_connection;

    public RepoProgrammer(DB_Utils db_utils) {
        this.DB_connection = db_utils;
    }

    /**
     * @param entity
     * @return
     */
    @Override
    public Optional<Programmer> save(Programmer entity) {

        String SQL_INSERT = "INSERT INTO programmers (username, password) VALUES (?, ?) ";
        try (Connection conn = DB_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, entity.getUsername());
            pstmt.setString(2, entity.getPassword());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return Optional.of(entity);
                    }
                }
            }
        } catch (SQLException e) {
            ///System.out.println(e.getMessage());
            System.out.println("Error in RepoProgrammer:save");
        }
        return Optional.empty();
    }

    /**
     * @param entity
     * @return
     */
    @Override
    public Optional<Programmer> delete(Programmer entity) {
        String SQL_DELETE = "DELETE FROM programmers WHERE  username=?";
        try (Connection conn = DB_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE)) {

            pstmt.setString(1, entity.getUsername());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return Optional.of(entity);
            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("Error in RepoProgrammer:delete");
        }
        return Optional.empty();
    }

    /**
     * @param entity
     * @return
     */
    @Override
    public Optional<Programmer> update(Programmer entity) {
        String SQL_UPDATE = "UPDATE programmers SET password=? WHERE username=?";
        try (Connection conn = DB_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {

            pstmt.setString(1, entity.getPassword());
            pstmt.setString(2, entity.getUsername());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return Optional.of(entity);
            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("Error in RepoProgrammer:update");
        }
        return Optional.empty();
    }

    /**
     * @param
     * @return
     */
    @Override
    public Optional<Programmer> findById(String Id) {
        String SQL_FIND = "SELECT * FROM programmers WHERE username=?";
        try (Connection conn = DB_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_FIND)) {

            pstmt.setString(1, Id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Programmer(rs.getString("username"), rs.getString("password")));
                }
            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("Error in RepoProgrammer:findById");
        }
        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public Iterable<Programmer> findAll() {

        List<Programmer> programmers = new ArrayList<>();
        String SQL_FIND_ALL = "SELECT * FROM programmers";
        try (Connection conn = DB_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_FIND_ALL)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    programmers.add(new Programmer(rs.getString("username"), rs.getString("password")));
                }
                return programmers;
            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("Error in RepoProgrammer:findAll");
        }
        return null;
    }

    /**
     * @return
     */
    @Override
    public Long size() {
        String SQL_SIZE = "SELECT COUNT(*) FROM programmers";
        try (Connection conn = DB_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SIZE)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("Error in RepoProgrammer:size");
        }
        return -1L;
    }
}


package org.iss2024.bug_detection.Repository;

import org.iss2024.bug_detection.Domain.Validator;
import org.iss2024.bug_detection.Utility.DB_Utils;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class RepoValidator implements Repo_Validator_Interface{


    private final DB_Utils DB_connection;

    public RepoValidator(DB_Utils db_utils) {
        this.DB_connection = db_utils;
    }

    /**
     * @param entity 
     * @return
     */
    @Override
    public Optional<Validator> save(Validator entity) {

        String SQL_INSERT = "INSERT INTO validators (username, password) VALUES (?, ?) ";
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
            //System.out.println(e.getMessage());
            System.out.println("Error in RepoValidator:save");
        }
        return Optional.empty();
    }

    /**
     * @param entity 
     * @return
     */
    @Override
    public Optional<Validator> delete(Validator entity) {

        String SQL_DELETE = "DELETE FROM validators WHERE  username=?";
        try (Connection conn = DB_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE)) {

            pstmt.setString(1, entity.getUsername());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return Optional.of(entity);
            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("Error in RepoValidator:delete");
        }
        return Optional.empty();
    }

    /**
     * @param entity 
     * @return
     */
    @Override
    public Optional<Validator> update(Validator entity) {

        String SQL_UPDATE = "UPDATE validators SET password=? WHERE username=?";
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
            System.out.println("Error in RepoValidator:update");
        }
        return Optional.empty();
    }

    /**
     * @param
     * @return
     */
    @Override
    public Optional<Validator> findById(String Id) {

            String SQL_FIND = "SELECT v.*" +
                    "FROM validators v " +
                    "WHERE v.username=? ";

            try (Connection conn = DB_connection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL_FIND)) {

                pstmt.setString(1, Id);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(new Validator(rs.getString("username"), rs.getString("password")));
                    }
                }
            } catch (SQLException e) {
               // System.out.println(e.getMessage());
                System.out.println("Error in RepoValidator:findById");
            }
            return Optional.empty();
    }

    /**
     * @return 
     */
    @Override
    public Iterable<Validator> findAll() {

        List<Validator> validators = null;
        String SQL_FIND_ALL = "SELECT * FROM validators";
        try (Connection conn = DB_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_FIND_ALL)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    validators.add(new Validator(rs.getString("username"), rs.getString("password")));
                }
                return validators;
            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("Error in RepoValidator:findAll");
        }
        return null;
    }

    /**
     * @return 
     */
    @Override
    public Long size() {

        String SQL_SIZE = "SELECT COUNT(*) FROM validators";
        try (Connection conn = DB_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SIZE)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("Error in RepoValidator:size");
        }
        return 0L;
    }

}

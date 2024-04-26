package org.iss2024.bug_detection.Repository;

import org.iss2024.bug_detection.Domain.Admin;
import org.iss2024.bug_detection.Domain.Programmer;
import org.iss2024.bug_detection.Utility.DB_Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RepoAdmin implements Repo_Admin_Interface {

    private DB_Utils DB_connection;

    public RepoAdmin(DB_Utils db_utils) {
        this.DB_connection = db_utils;
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public Optional<Admin> findById(String id) {
        String SQL_FIND = "SELECT * FROM admins WHERE username=?";
        try (Connection conn = DB_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_FIND)) {

            pstmt.setString(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Admin(rs.getString("username"), rs.getString("password")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

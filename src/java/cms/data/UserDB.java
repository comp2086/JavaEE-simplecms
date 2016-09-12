package cms.data;

import cms.business.User;
import cms.data.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// Handles DB ops
public class UserDB {

    public static void insert(User newUser) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement ps = null;

        try {
            String preparedSQL = "INSERT INTO users(firstName) VALUES ( ? )";
            ps = conn.prepareStatement(preparedSQL);
            ps.setString(1, newUser.getFirstName());
            ps.executeUpdate();
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(conn);
    }

    public static void delete(int userId) {
        // Delete routine
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement ps = null;

        try {
            String preparedSQL = "DELETE FROM users WHERE id = ?";
            ps = conn.prepareStatement(preparedSQL);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }

        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(conn);
    }

    public static void update(User updatedUser) {
        // Update routine
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement ps = null;
        
        try {
            String preparedSQL = "UPDATE users SET firstName = ? WHERE id = ?";
            ps = conn.prepareStatement(preparedSQL);
            ps.setString(1, updatedUser.getFirstName());
            ps.setInt(2, updatedUser.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
        
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(conn);
    }

    public static User getUser(int userId) {
        // Get a specified user routine
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        
        try {
            String preparedSQL = "SELECT * FROM users WHERE id = ?";
            ps = conn.prepareStatement(preparedSQL);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            
            if (rs.first()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
            }
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
        
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(conn);
        return user;
    }

    public static ArrayList<User> list() {
        // List all users routine
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<User> users = null;

        try {
            String sql = "SELECT * FROM users";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            users = new ArrayList<>();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setFirstName(rs.getString("firstName"));
                users.add(u);
            }

        } catch (SQLException e) {
            System.err.println(e);
        }

        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(stmt);
        pool.freeConnection(conn);

        return users;
    }
}

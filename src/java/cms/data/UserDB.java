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
            String preparedSQL = "INSERT INTO Users(firstName) VALUES ( ? )";
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

    public static void delete(User deletedUser) {
        // Delete routine
    }

    public static void update(User updatedUser) {
        // Update routine
    }

    public static User getUser(int id) {
        // Get a specified user routine

        return new User();
    }

    public static ArrayList<User> list() {
        // List all users routine
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<User> users = null;
        
        try {
            String sql = "SELECT * FROM Users";
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

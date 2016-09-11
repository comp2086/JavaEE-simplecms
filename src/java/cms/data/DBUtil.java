package cms.data;

import java.sql.*;

public class DBUtil {
    
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }
    
    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }
    
    public static void closePreparedStatement(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }
}

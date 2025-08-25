package dao;

import db.DBConnection;
import model.BugType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BugTypeDAO {

    public void addBugType(BugType bugType) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO BugType VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bugType.getBugCode());
            stmt.setString(2, bugType.getBugCategory());
            stmt.setString(3, bugType.getBugSeverity());
            stmt.executeUpdate();
            System.out.println("✅ Bug Type added successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<BugType> getAllBugTypes() {
        List<BugType> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM BugType";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BugType bugType = new BugType();
                bugType.setBugCode(rs.getInt("bugCode"));
                bugType.setBugCategory(rs.getString("bugCategory"));
                bugType.setBugSeverity(rs.getString("bugSeverity"));
                list.add(bugType);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public BugType getBugTypeByCode(int bugCode) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM BugType WHERE bugCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bugCode);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                BugType bugType = new BugType();
                bugType.setBugCode(rs.getInt("bugCode"));
                bugType.setBugCategory(rs.getString("bugCategory"));
                bugType.setBugSeverity(rs.getString("bugSeverity"));
                return bugType;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void updateBugType(BugType bugType) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE BugType SET bugCategory=?, bugSeverity=? WHERE bugCode=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, bugType.getBugCategory());
            stmt.setString(2, bugType.getBugSeverity());
            stmt.setInt(3, bugType.getBugCode());
            
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Bug Type updated successfully.");
            } else {
                System.out.println("❌ Bug Type not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteBugType(int bugCode) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM BugType WHERE bugCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bugCode);
            
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Bug Type deleted successfully.");
            } else {
                System.out.println("❌ Bug Type not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
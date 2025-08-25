package dao;

import db.DBConnection;
import model.BugReport;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BugDAO {

    public void addBug(BugReport bug) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO BugReport VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bug.getBugNo());
            stmt.setInt(2, bug.getBugCode());
            stmt.setInt(3, bug.getProjectID());
            stmt.setInt(4, bug.getTesterCode());
            stmt.setInt(5, bug.getEmployeeCode());
            stmt.setString(6, bug.getStatus());
            stmt.setString(7, bug.getBugDescription());
            stmt.executeUpdate();
            System.out.println("✅ Bug reported successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<BugReport> getAllBugs() {
        List<BugReport> bugs = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM BugReport";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                BugReport bug = new BugReport();
                bug.setBugNo(rs.getInt("bugNo"));
                bug.setBugCode(rs.getInt("bugCode"));
                bug.setProjectID(rs.getInt("projectID"));
                bug.setTesterCode(rs.getInt("TCode"));
                bug.setEmployeeCode(rs.getInt("ECode"));
                bug.setStatus(rs.getString("status"));
                bug.setBugDescription(rs.getString("bugDes"));
                bugs.add(bug);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bugs;
    }
    
    public BugReport getBugById(int bugNo) {
        BugReport bug = null;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM BugReport WHERE bugNo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bugNo);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                bug = new BugReport();
                bug.setBugNo(rs.getInt("bugNo"));
                bug.setBugCode(rs.getInt("bugCode"));
                bug.setProjectID(rs.getInt("projectID"));
                bug.setTesterCode(rs.getInt("TCode"));
                bug.setEmployeeCode(rs.getInt("ECode"));
                bug.setStatus(rs.getString("status"));
                bug.setBugDescription(rs.getString("bugDes"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bug;
    }
    
    public List<BugReport> getBugsByEmployee(int empCode) {
        List<BugReport> bugs = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM BugReport WHERE ECode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, empCode);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                BugReport bug = new BugReport();
                bug.setBugNo(rs.getInt("bugNo"));
                bug.setBugCode(rs.getInt("bugCode"));
                bug.setProjectID(rs.getInt("projectID"));
                bug.setTesterCode(rs.getInt("TCode"));
                bug.setEmployeeCode(rs.getInt("ECode"));
                bug.setStatus(rs.getString("status"));
                bug.setBugDescription(rs.getString("bugDes"));
                bugs.add(bug);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bugs;
    }
    
    public void updateBug(BugReport bug) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE BugReport SET bugCode = ?, projectID = ?, TCode = ?, ECode = ?, status = ?, bugDes = ? WHERE bugNo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bug.getBugCode());
            stmt.setInt(2, bug.getProjectID());
            stmt.setInt(3, bug.getTesterCode());
            stmt.setInt(4, bug.getEmployeeCode());
            stmt.setString(5, bug.getStatus());
            stmt.setString(6, bug.getBugDescription());
            stmt.setInt(7, bug.getBugNo());
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("❌ Bug not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteBug(int bugNo) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM BugReport WHERE bugNo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bugNo);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Bug deleted successfully.");
            } else {
                System.out.println("❌ Bug not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

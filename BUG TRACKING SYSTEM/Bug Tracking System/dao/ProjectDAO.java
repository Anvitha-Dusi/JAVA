package dao;

import db.DBConnection;
import model.Project;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    public void addProject(Project p) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Project VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, p.getProjectID());
            stmt.setString(2, p.getProjectName());
            stmt.setString(3, p.getStartDate());
            stmt.setString(4, p.getEndDate());
            stmt.setString(5, p.getProjectDesc());
            stmt.executeUpdate();
            System.out.println("✅ Project added successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Project> getAllProjects() {
        List<Project> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Project";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Project p = new Project();
                p.setProjectID(rs.getInt("projectID"));
                p.setProjectName(rs.getString("projectName"));
                p.setStartDate(rs.getString("SDate"));
                p.setEndDate(rs.getString("EDate"));
                p.setProjectDesc(rs.getString("projectDec"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void deleteProject(int projectID) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Project WHERE projectID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, projectID);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Project deleted successfully.");
            } else {
                System.out.println("❌ Project not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateProject(Project p) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Project SET projectName=?, SDate=?, EDate=?, projectDec=? WHERE projectID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getProjectName());
            stmt.setString(2, p.getStartDate());
            stmt.setString(3, p.getEndDate());
            stmt.setString(4, p.getProjectDesc());
            stmt.setInt(5, p.getProjectID());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Project updated successfully.");
            } else {
                System.out.println("❌ Project not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

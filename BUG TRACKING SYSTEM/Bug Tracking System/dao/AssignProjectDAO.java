package dao;

import db.DBConnection;
import model.AssignProject;
import model.Employee;
import model.Project;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignProjectDAO {

    public void assignProject(AssignProject assignment) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO AssignProject (projectID, empCode) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, assignment.getProjectID());
            stmt.setInt(2, assignment.getEmpCode());
            stmt.executeUpdate();
            System.out.println("✅ Project assigned successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<AssignProject> getAllAssignments() {
        List<AssignProject> assignments = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM AssignProject";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                AssignProject assignment = new AssignProject();
                assignment.setAssignID(rs.getInt("assignID"));
                assignment.setProjectID(rs.getInt("projectID"));
                assignment.setEmpCode(rs.getInt("empCode"));
                assignments.add(assignment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return assignments;
    }
    
    public List<Project> getProjectsByEmployee(int empCode) {
        List<Project> projects = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT p.* FROM Project p JOIN AssignProject a ON p.projectID = a.projectID WHERE a.empCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, empCode);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("projectID"));
                project.setProjectName(rs.getString("projectName"));
                project.setStartDate(rs.getString("SDate"));
                project.setEndDate(rs.getString("EDate"));
                project.setProjectDesc(rs.getString("projectDec"));
                projects.add(project);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return projects;
    }
    
    public List<Employee> getEmployeesByProject(int projectID) {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT e.* FROM Employee e JOIN AssignProject a ON e.empCode = a.empCode WHERE a.projectID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, projectID);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmpCode(rs.getInt("empCode"));
                employee.setEmpName(rs.getString("empName"));
                employee.setEmpEmail(rs.getString("empEmail"));
                employee.setEmpPassword(rs.getString("empPassword"));
                employee.setGender(rs.getString("gender"));
                employee.setDob(rs.getString("DOB"));
                employee.setMobileNo(rs.getLong("mobileNo"));
                employee.setRole(rs.getString("Role"));
                employees.add(employee);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
    }
    
    public void removeAssignment(int assignID) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM AssignProject WHERE assignID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, assignID);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Project assignment removed successfully.");
            } else {
                System.out.println("❌ Assignment not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Employee;
import db.DBConnection;

public class EmployeeDAO {

    public void addEmployee(Employee e) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Employee VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, e.getEmpCode());
            stmt.setString(2, e.getEmpName());
            stmt.setString(3, e.getEmpEmail());
            stmt.setString(4, e.getEmpPassword());
            stmt.setString(5, e.getGender());
            stmt.setString(6, e.getDob());
            stmt.setLong(7, e.getMobileNo());
            stmt.setString(8, e.getRole());
            stmt.executeUpdate();
            System.out.println("✅ Employee added successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Employee";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Employee e = new Employee();
                e.setEmpCode(rs.getInt("empCode"));
                e.setEmpName(rs.getString("empName"));
                e.setEmpEmail(rs.getString("empEmail"));
                e.setEmpPassword(rs.getString("empPassword"));
                e.setGender(rs.getString("gender"));
                e.setDob(rs.getString("DOB"));
                e.setMobileNo(rs.getLong("mobileNo"));
                e.setRole(rs.getString("Role"));
                list.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public Employee getEmployeeById(int empCode) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Employee WHERE empCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, empCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Employee e = new Employee();
                e.setEmpCode(rs.getInt("empCode"));
                e.setEmpName(rs.getString("empName"));
                e.setEmpEmail(rs.getString("empEmail"));
                e.setEmpPassword(rs.getString("empPassword"));
                e.setGender(rs.getString("gender"));
                e.setDob(rs.getString("DOB"));
                e.setMobileNo(rs.getLong("mobileNo"));
                e.setRole(rs.getString("Role"));
                return e;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteEmployee(int empCode) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Employee WHERE empCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, empCode);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee deleted successfully.");
            } else {
                System.out.println("❌ Employee not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateEmployee(Employee e) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Employee SET empName=?, empEmail=?, empPassword=?, gender=?, DOB=?, mobileNo=?, Role=? WHERE empCode=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, e.getEmpName());
            stmt.setString(2, e.getEmpEmail());
            stmt.setString(3, e.getEmpPassword());
            stmt.setString(4, e.getGender());
            stmt.setString(5, e.getDob());
            stmt.setLong(6, e.getMobileNo());
            stmt.setString(7, e.getRole());
            stmt.setInt(8, e.getEmpCode());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee updated successfully.");
            } else {
                System.out.println("❌ Employee not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
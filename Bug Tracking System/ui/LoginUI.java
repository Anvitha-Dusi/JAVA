package ui;

import dao.EmployeeDAO;
import model.Employee;
import java.util.Scanner;

public class LoginUI {
    private Scanner sc = new Scanner(System.in);
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    
    public boolean authenticate(String role) {
        System.out.println("\n=== " + role + " Login ===");
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        
        // For admin, use a hardcoded credential for simplicity
        if (role.equalsIgnoreCase("Admin")) {
            return email.equals("admin@bugtracker.com") && password.equals("admin123");
        }
        
        // For manager and employee, check against database
        for (Employee emp : employeeDAO.getAllEmployees()) {
            if (emp.getEmpEmail().equals(email) && 
                emp.getEmpPassword().equals(password) && 
                emp.getRole().equalsIgnoreCase(role)) {
                return true;
            }
        }
        
        return false;
    }
}
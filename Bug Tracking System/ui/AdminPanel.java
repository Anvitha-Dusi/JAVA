package ui;

import java.util.Scanner;
import java.util.List;
import dao.EmployeeDAO;
import model.Employee;

public class AdminPanel {
    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        EmployeeDAO empDao = new EmployeeDAO();

        while (true) {
            System.out.println("\n=== Admin Panel ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Delete Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addEmployee(sc, empDao);
                case 2 -> viewEmployees(empDao);
                case 3 -> deleteEmployee(sc, empDao);
                case 4 -> updateEmployee(sc, empDao);
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addEmployee(Scanner sc, EmployeeDAO empDao) {
        Employee e = new Employee();
        System.out.print("Enter Employee Code: ");
        e.setEmpCode(sc.nextInt());
        sc.nextLine();
        
        System.out.print("Enter Employee Name: ");
        e.setEmpName(sc.nextLine());
        
        System.out.print("Enter Employee Email: ");
        e.setEmpEmail(sc.nextLine());
        
        System.out.print("Enter Employee Password: ");
        e.setEmpPassword(sc.nextLine());
        
        System.out.print("Enter Gender (Male/Female): ");
        e.setGender(sc.nextLine());
        
        System.out.print("Enter Date of Birth (DD/MM/YYYY): ");
        e.setDob(sc.nextLine());
        
        System.out.print("Enter Mobile Number: ");
        e.setMobileNo(sc.nextLong());
        sc.nextLine();
        
        System.out.print("Enter Role (Admin/Manager/Developer/Tester): ");
        e.setRole(sc.nextLine());
        
        empDao.addEmployee(e);
    }

    private void viewEmployees(EmployeeDAO empDao) {
        List<Employee> employees = empDao.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        
        System.out.println("\n=== Employee List ===");
        System.out.printf("%-10s %-20s %-30s %-15s %-15s\n", "Emp Code", "Name", "Email", "Mobile", "Role");
        for (Employee e : employees) {
            System.out.printf("%-10d %-20s %-30s %-15d %-15s\n", 
                e.getEmpCode(), e.getEmpName(), e.getEmpEmail(), e.getMobileNo(), e.getRole());
        }
    }

    private void deleteEmployee(Scanner sc, EmployeeDAO empDao) {
        System.out.print("Enter Employee Code to delete: ");
        int empCode = sc.nextInt();
        sc.nextLine();
        
        empDao.deleteEmployee(empCode);
    }

    private void updateEmployee(Scanner sc, EmployeeDAO empDao) {
        System.out.print("Enter Employee Code to update: ");
        int empCode = sc.nextInt();
        sc.nextLine();
        
        Employee e = empDao.getEmployeeById(empCode);
        if (e == null) {
            System.out.println("Employee not found.");
            return;
        }
        
        System.out.println("\n=== Update Employee Details ===");
        System.out.println("1. Update Name");
        System.out.println("2. Update Email");
        System.out.println("3. Update Password");
        System.out.println("4. Update Mobile Number");
        System.out.println("5. Update Role");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1 -> {
                System.out.print("Enter new Name: ");
                e.setEmpName(sc.nextLine());
            }
            case 2 -> {
                System.out.print("Enter new Email: ");
                e.setEmpEmail(sc.nextLine());
            }
            case 3 -> {
                System.out.print("Enter new Password: ");
                e.setEmpPassword(sc.nextLine());
            }
            case 4 -> {
                System.out.print("Enter new Mobile Number: ");
                e.setMobileNo(sc.nextLong());
                sc.nextLine();
            }
            case 5 -> {
                System.out.print("Enter new Role: ");
                e.setRole(sc.nextLine());
            }
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }
        
        empDao.updateEmployee(e);
    }
}

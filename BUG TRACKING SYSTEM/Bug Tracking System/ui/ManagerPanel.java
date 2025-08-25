package ui;

import dao.ProjectDAO;
import dao.BugDAO;
import dao.BugTypeDAO;
import dao.AssignProjectDAO;
import dao.EmployeeDAO;
import model.Project;
import model.BugReport;
import model.BugType;
import model.AssignProject;
import model.Employee;

import java.util.Scanner;
import java.util.List;

public class ManagerPanel {

    Scanner sc = new Scanner(System.in);
    ProjectDAO projectDAO = new ProjectDAO();
    BugDAO bugDAO = new BugDAO();
    BugTypeDAO bugTypeDAO = new BugTypeDAO();
    AssignProjectDAO assignProjectDAO = new AssignProjectDAO();
    EmployeeDAO employeeDAO = new EmployeeDAO();

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Manager Panel ===");
            System.out.println("1. Add Project");
            System.out.println("2. View All Projects");
            System.out.println("3. Add Bug Report");
            System.out.println("4. View All Bug Reports");
            System.out.println("5. Add Bug Type");
            System.out.println("6. View All Bug Types");
            System.out.println("7. Assign Project to Employee");
            System.out.println("8. View Project Assignments");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addProject();
                case 2 -> viewProjects();
                case 3 -> addBug();
                case 4 -> viewBugs();
                case 5 -> addBugType();
                case 6 -> viewBugTypes();
                case 7 -> assignProject();
                case 8 -> viewAssignments();
                case 9 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addProject() {
        Project p = new Project();
        System.out.print("Enter Project ID: ");
        p.setProjectID(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter Project Name: ");
        p.setProjectName(sc.nextLine());
        System.out.print("Enter Start Date: ");
        p.setStartDate(sc.nextLine());
        System.out.print("Enter End Date: ");
        p.setEndDate(sc.nextLine());
        System.out.print("Enter Description: ");
        p.setProjectDesc(sc.nextLine());

        projectDAO.addProject(p);
    }

    private void viewProjects() {
        projectDAO.getAllProjects().forEach(p -> {
            System.out.println("Project ID: " + p.getProjectID());
            System.out.println("Name: " + p.getProjectName());
            System.out.println("Start: " + p.getStartDate());
            System.out.println("End: " + p.getEndDate());
            System.out.println("Desc: " + p.getProjectDesc());
            System.out.println("------------------------------");
        });
    }

    private void addBug() {
        BugReport bug = new BugReport();
        System.out.print("Enter Bug No: ");
        bug.setBugNo(sc.nextInt());
        System.out.print("Enter Bug Code: ");
        bug.setBugCode(sc.nextInt());
        System.out.print("Enter Project ID: ");
        bug.setProjectID(sc.nextInt());
        System.out.print("Enter Tester Code: ");
        bug.setTesterCode(sc.nextInt());
        System.out.print("Enter Assigned Employee Code: ");
        bug.setEmployeeCode(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter Bug Status: ");
        bug.setStatus(sc.nextLine());
        System.out.print("Enter Description: ");
        bug.setBugDescription(sc.nextLine());

        bugDAO.addBug(bug);
    }

    private void viewBugs() {
        bugDAO.getAllBugs().forEach(bug -> {
            System.out.println("Bug No: " + bug.getBugNo());
            System.out.println("Bug Code: " + bug.getBugCode());
            System.out.println("Project ID: " + bug.getProjectID());
            System.out.println("Tester Code: " + bug.getTesterCode());
            System.out.println("Employee Code: " + bug.getEmployeeCode());
            System.out.println("Status: " + bug.getStatus());
            System.out.println("Description: " + bug.getBugDescription());
            System.out.println("------------------------------");
        });
    }

    private void addBugType() {
        BugType bugType = new BugType();
        System.out.print("Enter Bug Code: ");
        bugType.setBugCode(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter Bug Category: ");
        bugType.setBugCategory(sc.nextLine());
        System.out.print("Enter Bug Severity (Low/Medium/High/Critical): ");
        bugType.setBugSeverity(sc.nextLine());

        bugTypeDAO.addBugType(bugType);
    }

    private void viewBugTypes() {
        bugTypeDAO.getAllBugTypes().forEach(bt -> {
            System.out.println("Bug Code: " + bt.getBugCode());
            System.out.println("Category: " + bt.getBugCategory());
            System.out.println("Severity: " + bt.getBugSeverity());
            System.out.println("------------------------------");
        });
    }
    
    private void assignProject() {
        // Display available projects
        System.out.println("\nAvailable Projects:");
        List<Project> projects = projectDAO.getAllProjects();
        if (projects.isEmpty()) {
            System.out.println("No projects available. Please add a project first.");
            return;
        }
        
        projects.forEach(p -> {
            System.out.println(p.getProjectID() + ": " + p.getProjectName());
        });
        
        // Display available employees
        System.out.println("\nAvailable Employees:");
        List<Employee> employees = employeeDAO.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees available. Please add employees first.");
            return;
        }
        
        employees.forEach(e -> {
            if (e.getRole().equals("Developer") || e.getRole().equals("Tester")) {
                System.out.println(e.getEmpCode() + ": " + e.getEmpName() + " (" + e.getRole() + ")");
            }
        });
        
        // Get assignment details
        AssignProject assignment = new AssignProject();
        System.out.print("\nEnter Project ID to assign: ");
        assignment.setProjectID(sc.nextInt());
        System.out.print("Enter Employee Code to assign to: ");
        assignment.setEmpCode(sc.nextInt());
        sc.nextLine();
        
        // Validate project ID
        boolean projectExists = projects.stream()
                .anyMatch(p -> p.getProjectID() == assignment.getProjectID());
        if (!projectExists) {
            System.out.println("❌ Invalid Project ID.");
            return;
        }
        
        // Validate employee code
        boolean employeeExists = employees.stream()
                .anyMatch(e -> e.getEmpCode() == assignment.getEmpCode() && 
                        (e.getRole().equals("Developer") || e.getRole().equals("Tester")));
        if (!employeeExists) {
            System.out.println("❌ Invalid Employee Code or employee is not a Developer/Tester.");
            return;
        }
        
        assignProjectDAO.assignProject(assignment);
    }
    
    private void viewAssignments() {
        System.out.println("\n=== Project Assignments ===");
        System.out.println("1. View by Project");
        System.out.println("2. View by Employee");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1 -> {
                System.out.print("Enter Project ID: ");
                int projectID = sc.nextInt();
                sc.nextLine();
                
                System.out.println("\nEmployees assigned to Project ID " + projectID + ":");
                List<Employee> assignedEmployees = assignProjectDAO.getEmployeesByProject(projectID);
                
                if (assignedEmployees.isEmpty()) {
                    System.out.println("No employees assigned to this project.");
                } else {
                    assignedEmployees.forEach(e -> {
                        System.out.println("Employee Code: " + e.getEmpCode());
                        System.out.println("Name: " + e.getEmpName());
                        System.out.println("Role: " + e.getRole());
                        System.out.println("------------------------------");
                    });
                }
            }
            case 2 -> {
                System.out.print("Enter Employee Code: ");
                int empCode = sc.nextInt();
                sc.nextLine();
                
                System.out.println("\nProjects assigned to Employee Code " + empCode + ":");
                List<Project> assignedProjects = assignProjectDAO.getProjectsByEmployee(empCode);
                
                if (assignedProjects.isEmpty()) {
                    System.out.println("No projects assigned to this employee.");
                } else {
                    assignedProjects.forEach(p -> {
                        System.out.println("Project ID: " + p.getProjectID());
                        System.out.println("Name: " + p.getProjectName());
                        System.out.println("Start: " + p.getStartDate());
                        System.out.println("End: " + p.getEndDate());
                        System.out.println("------------------------------");
                    });
                }
            }
            default -> System.out.println("Invalid choice.");
        }
    }
}
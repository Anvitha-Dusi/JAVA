package ui;

import dao.BugDAO;
import dao.AssignProjectDAO;
import model.BugReport;
import model.Project;
import java.util.Scanner;
import java.util.List;

public class EmployeePanel {

    Scanner sc = new Scanner(System.in);
    BugDAO bugDAO = new BugDAO();
    AssignProjectDAO assignProjectDAO = new AssignProjectDAO();
    private int currentEmployeeCode;

    public void showMenu() {
        // Get employee code for the current session
        System.out.print("Enter your Employee Code: ");
        currentEmployeeCode = sc.nextInt();
        sc.nextLine();
        
        while (true) {
            System.out.println("\n=== Employee Panel ===");
            System.out.println("1. View My Assigned Projects");
            System.out.println("2. View All Bug Reports");
            System.out.println("3. Update Bug Status");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> viewAssignedProjects();
                case 2 -> viewBugs();
                case 3 -> updateBugStatus();
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    private void viewAssignedProjects() {
        List<Project> projects = assignProjectDAO.getProjectsByEmployee(currentEmployeeCode);
        
        if (projects.isEmpty()) {
            System.out.println("You have no assigned projects.");
            return;
        }
        
        System.out.println("\n=== My Assigned Projects ===");
        projects.forEach(p -> {
            System.out.println("Project ID: " + p.getProjectID());
            System.out.println("Name: " + p.getProjectName());
            System.out.println("Start Date: " + p.getStartDate());
            System.out.println("End Date: " + p.getEndDate());
            System.out.println("Description: " + p.getProjectDesc());
            System.out.println("------------------------------");
        });
    }

    private void viewBugs() {
        System.out.println("\n=== Bug Reports ===");
        System.out.println("1. View All Bugs");
        System.out.println("2. View Bugs Assigned to Me");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        List<BugReport> bugs;
        
        switch (choice) {
            case 1 -> {
                bugs = bugDAO.getAllBugs();
                if (bugs.isEmpty()) {
                    System.out.println("No bug reports found.");
                    return;
                }
                System.out.println("\n=== All Bug Reports ===");
            }
            case 2 -> {
                bugs = bugDAO.getBugsByEmployee(currentEmployeeCode);
                if (bugs.isEmpty()) {
                    System.out.println("No bug reports assigned to you.");
                    return;
                }
                System.out.println("\n=== Bugs Assigned to Me ===");
            }
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }
        
        bugs.forEach(bug -> {
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

    private void updateBugStatus() {
        // Show only bugs assigned to the current employee
        List<BugReport> assignedBugs = bugDAO.getBugsByEmployee(currentEmployeeCode);
        
        if (assignedBugs.isEmpty()) {
            System.out.println("You have no bugs assigned to you.");
            return;
        }
        
        System.out.println("\n=== Bugs Assigned to You ===");
        assignedBugs.forEach(bug -> {
            System.out.println(bug.getBugNo() + ": " + bug.getBugDescription() + " (" + bug.getStatus() + ")");
        });
        
        System.out.print("\nEnter Bug No to update: ");
        int bugNo = sc.nextInt();
        sc.nextLine();
        
        // Verify the bug exists and is assigned to this employee
        boolean bugFound = assignedBugs.stream()
                .anyMatch(bug -> bug.getBugNo() == bugNo);
                
        if (!bugFound) {
            System.out.println("❌ Invalid Bug No or bug not assigned to you.");
            return;
        }
        
        BugReport bug = bugDAO.getBugById(bugNo);
        
        System.out.println("Current Status: " + bug.getStatus());
        System.out.println("1. Mark as Resolved");
        System.out.println("2. Mark as Pending");
        System.out.println("3. Mark as Rejected");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        String newStatus;
        switch (choice) {
            case 1 -> newStatus = "resolved";
            case 2 -> newStatus = "pending";
            case 3 -> newStatus = "rejected";
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }
        
        bug.setStatus(newStatus);
        bugDAO.updateBug(bug);
        System.out.println("✅ Bug status updated successfully.");
    }
}

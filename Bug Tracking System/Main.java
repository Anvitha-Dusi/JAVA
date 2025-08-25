package com.bugtracker;

import ui.ManagerPanel;
import ui.EmployeePanel;
import ui.AdminPanel;
import ui.LoginUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoginUI loginUI = new LoginUI();

        while (true) {
            System.out.println("\n=== Bug Tracking System ===");
            System.out.println("1. Admin Login");
            System.out.println("2. Manager Login");
            System.out.println("3. Employee Login");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    if (loginUI.authenticate("Admin")) {
                        System.out.println("\nWelcome, Admin!");
                        AdminPanel adminPanel = new AdminPanel();
                        adminPanel.showMenu();
                    } else {
                        System.out.println("❌ Invalid credentials. Access denied.");
                    }
                }
                case 2 -> {
                    if (loginUI.authenticate("Manager")) {
                        System.out.println("\nWelcome, Manager!");
                        ManagerPanel managerPanel = new ManagerPanel();
                        managerPanel.showMenu();
                    } else {
                        System.out.println("❌ Invalid credentials. Access denied.");
                    }
                }
                case 3 -> {
                    if (loginUI.authenticate("Developer") || loginUI.authenticate("Tester")) {
                        System.out.println("\nWelcome, Employee!");
                        EmployeePanel employeePanel = new EmployeePanel();
                        employeePanel.showMenu();
                    } else {
                        System.out.println("❌ Invalid credentials. Access denied.");
                    }
                }
                case 4 -> {
                    System.out.println("Exiting application...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

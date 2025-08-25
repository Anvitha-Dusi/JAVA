import ui.ManagerPanel;
import ui.EmployeePanel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Bug Tracking System ===");
            System.out.println("1. Manager Login");
            System.out.println("2. Employee Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("\nWelcome, Manager!");
                    ManagerPanel managerPanel = new ManagerPanel();
                    managerPanel.showMenu();
                }
                case 2 -> {
                    System.out.println("\nWelcome, Employee!");
                    EmployeePanel employeePanel = new EmployeePanel();
                    employeePanel.showMenu();
                }
                case 3 -> {
                    System.out.println("Exiting application...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

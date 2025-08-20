package Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class SecurePasswordVaultApp {
    private static Scanner scanner = new Scanner(System.in);
    private static List<User> users = new ArrayList<>(); 
    private static User currentUser = null;

    private static void createAccount() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already taken. Please try another.");
                return;
            }
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Subscription plans:");
        System.out.println("1. Free");
        System.out.println("2. Premium");
        System.out.println("3. Ultra");
        System.out.print("Choose an option: ");

        int planChoice = scanner.nextInt();
        scanner.nextLine(); 

        SubscriptionPlan plan = switch (planChoice) {
            case 1 -> new FreePlan();
            case 2 -> new PremiumPlan();
            case 3 -> new PremiumPlan();
            default -> new FreePlan();
        };

        User newUser = new User(username, password, plan);
        users.add(newUser);

        System.out.println("Account created successfully!");
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = null; 

        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }

        if (user != null) {
            System.out.println("Login successful!");
            currentUser = user;
            openVault(); 
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }


    private static void openVault() {
        PasswordVault vault = new PasswordVault(currentUser.getVault().getVaultId(), currentUser.getSubscriptionPlan().getEncryptionEngine());

        while (true) {
            System.out.println("\n \tVault Menu\t");
            System.out.println("1. Add Password");
            System.out.println("2. View Passwords");
            System.out.println("3. Generate Password");
            System.out.println("4. Remove Password");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
    
            switch (choice) {
                case 1 -> addPassword(vault);
                case 2 -> viewPasswords(vault);
                case 3 -> generatePassword(vault);
                case 4 -> removePassword(vault);
                case 5 -> {
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    

    private static void addPassword(PasswordVault vault) {
        System.out.print("Enter website name: ");
        String site = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        vault.addEntry(new PasswordEntry(UUID.randomUUID().toString(), site, password));
        System.out.println("Password added successfully.");
    }

    private static void viewPasswords(PasswordVault vault) {
        vault.viewEntries();
    }

    private static void generatePassword(PasswordVault vault) {
        String newPassword = vault.generateNewPassword();
        System.out.println("Generated password: " + newPassword);
    }

    private static void removePassword(PasswordVault vault) {
        System.out.print("Enter the ID of the password you want to remove: ");
        String id = scanner.nextLine();
        vault.removeEntry(id);
    }
    

    public static void main(String[] args) {
        System.out.println("Welcome to Secure Password Vault");

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Thank you for using Secure Password Vault!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

package com.mycompany.messenger_app;

import java.util.ArrayList;
import java.util.Scanner;

public class Messenger_App {

    public static void main(String[] args) {
        
        
        // ADD COMMENTS!!!!!!!!
        
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Login> users = new ArrayList<>();
        boolean running = true;
        
        System.out.println("============ CHAT APP ============");
    
        while(running){
            System.out.println("\n1. Register User");
            System.out.println("2. Log in");
            System.out.println("3. Exit");

            System.out.print("\nSelect an option (1, 2, 3): ");
            int selection = scanner.nextInt();
            scanner.nextLine();

            switch(selection){
                case 1 -> {
                    System.out.print("\nEnter a username: ");
                    String username = scanner.nextLine();
                    
                    if(Login.checkUsername(username)){
                        if(users.stream().anyMatch(u -> u.getUsername().equals(username))){
                            System.out.println("Username already exists. Please choose a different username.");
                            break;
                        }
                        
                        System.out.println("Username successfully captured.");
                    }else{
                        System.out.println(Login.registerUser("InvalidUsername"));
                        break;
                    }                                       
                         
                    
                    System.out.print("\nEnter a password: ");
                    String password = scanner.nextLine();
                    
                    if(Login.checkPasswordComplexity(password)){
                        System.out.println("Password successfully captured.");
                    }else{
                        System.out.println(Login.registerUser("InvalidPassword"));
                        break;
                    }
                       
                    
                    System.out.print("\nEnter your cell phone number (+27XXXXXXXXX): ");
                    String phoneNumber = scanner.nextLine();
                                        
                    if(Login.checkCellPhoneNumber(phoneNumber)){
                        System.out.println("Cell phone number successfully added.");
                    }else{
                        System.out.println("Cell phone number incorrectly formatted " +
                                           "or does not contain international code.");
                        break;
                    }
                    
                    users.add(new Login(username, password, phoneNumber));
                    System.out.println(Login.registerUser("RegisterSuccess"));
                    
                    System.out.println("\nWelcome, " + username + "! You can now log in with your username and password.");
                    System.out.println("Redirecting to login page...\n");
                    promptLogin(scanner, users);
                    break;
                }
                case 2 -> {
                    if(users.isEmpty()){
                        System.out.println("No users have registered yet! Please register first.");
                        break;
                    }
                    
                    promptLogin(scanner, users);
                    break;
                }
                case 3 -> {
                    System.out.println("Exiting... Come back soon!");
                    scanner.close();
                    running = false;
                }
                default -> 
                    System.out.println("Please enter a valid number (1, 2, 3)");
            }
        }
    }
    
    private static void promptLogin(Scanner scanner, ArrayList<Login> users){
        System.out.print("Enter username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();
        
        Login user = users.stream()
                  .filter(u -> u.getUsername().equals(enteredUsername))
                  .findFirst()
                  .orElse(null);
        
        boolean loginStatus = user != null && user.loginUser(enteredPassword);
        System.out.println(user != null ? user.returnLoginStatus(loginStatus) : "\nUser not found.");
    }
    
   public static class Login {
        public String username;
        public String password;
        public String phoneNumber;
        
        public Login(String username, String password, String phoneNumber){
            this.username = username;
            this.password = password;
            this.phoneNumber = phoneNumber;
        }
        
        public String getUsername(){
            return username;
        }
        
        public static boolean checkUsername(String username){
            return username.length() <= 5 && username.contains("_");
        }
        
        public static boolean checkPasswordComplexity(String password){
            return password.length() >= 8 &&
                   password.matches(".*[A-Z].*") &&
                   password.matches(".*\\d.*") &&
                   password.matches(".*[!@#$%^&*,.?\":()|<>].*");
        }
        
        public static boolean checkCellPhoneNumber(String phoneNumber){
            return phoneNumber.matches("\\+27[6-8]\\d{8}");
        }
        
        public static String registerUser(String registerCase){
            return switch(registerCase){
                case "InvalidUsername" -> "Username is not correctly formatted; please ensure that " +
                                          "your username contains an underscore and is no more than " +
                                          "five characters in length.";
                case "InvalidPassword" -> "Password is not correctly formatted; please ensure that the " +
                                          "password contains at least eight characters, a capital letter, " +
                                          "a number, and a special character.";
                case "RegisterSuccess" -> "\nRegistered successfully!";
                default -> "Invalid case.";
            };
        }
        
        public boolean loginUser(String enteredPassword){
            return this.password.equals(enteredPassword);
        }
        
        public String returnLoginStatus(boolean status){
            return status ? "\nLogin successful!" : "\nInvalid username or password.";
        }
    }
}

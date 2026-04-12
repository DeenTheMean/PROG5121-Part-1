package com.mycompany.messenger_app;

import java.util.ArrayList;
import java.util.Scanner;

public class Messenger_App {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> user = new ArrayList<>();
        boolean running = true;
        
        System.out.println("============ CHAT APP ============");
    
        while(running){
            System.out.println("\n1. Sign up");
            System.out.println("2. Log in");
            System.out.println("3. Exit");

            System.out.print("\nSelect an option (1, 2, 3): ");
            int selection = scanner.nextInt();
            scanner.nextLine();

            switch(selection){
                case 1 -> {
                    System.out.println("Enter your username: ");
                    String username = scanner.nextLine();
                    
                    if(username.length() <= 5 && username.contains("_")){
                        System.out.println("Username successfully captured.");
                    }else{
                        System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
                    }
                         
                   System.out.println(username);         
                }
                case 2 -> {
                    System.out.println("Logged in");
                }
                case 3 -> {
                    running = false;
                }
                default -> 
                    System.out.println("Please enter a valid number (1, 2, 3)");
            }
        }
    }
    
    class User {
        public String username;
        public String password;
        public String phoneNumber;
    }
}

package org.example;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static String getFirstName() {
        System.out.println("Enter first name: ");
        return scanner.nextLine();
    }

    public static String getLastName() {
        System.out.println("Enter last name: ");
        return scanner.nextLine();
    }

    public static int getId() {
        System.out.println("Enter id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public static int getInt() {
        System.out.println("Enter int: ");
        int integer = scanner.nextInt();
        scanner.nextLine();
        return integer;
    }
}

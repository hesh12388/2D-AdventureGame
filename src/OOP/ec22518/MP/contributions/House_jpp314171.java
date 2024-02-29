package OOP.ec22518.MP.contributions;

import java.util.Scanner;

class House_jpp314171 implements Visitable {
    private Scanner scanner;

    public House_jpp314171() {
        scanner = new Scanner(System.in);
    }

    // This method is to get integer inputs from the user
    public int inputInt(String message) {
        System.out.println(message);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consumes the newline character after the integer input
        return choice;
    }

    // This method displays the room menu to the user
    public void displayMenu() {
        System.out.println("This is my big house!");
        System.out.println("Enter the number corresponding to your choice:");
        System.out.println("(1) Room A");
        System.out.println("(2) Room B");
        System.out.println("(3) Room C");
        System.out.println("(4) Exit");
    }

    public Direction visit(Visitor v, Direction d) {
        Room[] rooms = {
            new Room_ec221002(),
            new Room_ec221003(),
            new Room_ec221004()
        };

        displayMenu();
        int userChoice = inputInt("");

        while (userChoice != 4) {
            if (userChoice >= 1 && userChoice <= rooms.length) {
                Room selectedRoom = rooms[userChoice - 1];
                selectedRoom.visit(v, d);
                v.tell("This is Room " + (userChoice) + ", please close the door when you enter.");
            } else {
                v.tell("Invalid choice. Please try again.");
            }

            displayMenu();
            userChoice = inputInt("");
        }

        System.out.println("That is my House, thanks for coming!");
        System.out.println("Bye");

        return d;
    }
}

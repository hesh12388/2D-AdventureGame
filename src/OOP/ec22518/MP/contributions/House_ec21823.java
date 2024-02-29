package OOP.ec22518.MP.contributions;

import java.util.Scanner;

class House_ec21823 implements Visitable {
    private Scanner scanner;

    public House_ec21823() {
        scanner = new Scanner(System.in);
    }

    // This method is to get string inputs from the user
    public String inputString(String message) {
        String choice;

        System.out.println(message);
        choice = scanner.nextLine();

        return choice;
    }

    public Direction visit(Visitor v, Direction d) {
        Room_ec22836 roomA = new Room_ec22836();
        Room_ec22711 roomB = new Room_ec22711();
        Room_ec22919 roomC = new Room_ec22919();

        Direction newDirection;

        System.out.println("This is my big house!");
        String userChoice = inputString("(A)Room A  (B)Room B  (C)Room C  (E)Exit");

        while (!userChoice.equals("E")) {
            if (userChoice.equals("A")) {
                newDirection = roomA.visit(v, d);
                v.tell("This is Room A, please close the door when you enter.");
            } if (userChoice.equals("B")) {
                newDirection = roomB.visit(v, d);
                v.tell("This is Room B, please close the door when you enter.");

            } else if (userChoice.equals("C")) {
                newDirection = roomC.visit(v, d);
                v.tell("This is Room C, please close the door when you enter.");

            }

            userChoice = inputString("(A) Room A  (B) Room B  (C) Room C  (E) Exit");
        }

        System.out.println("That is my House, thanks for coming!");
        System.out.println("Bye");

        return d;
    }
}
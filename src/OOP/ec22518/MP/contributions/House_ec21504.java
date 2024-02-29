package OOP.ec22518.MP.contributions;

import java.util.Scanner;

public class House_ec21504 implements Visitable {

    private Room[][] houseLayout;
    private final int numRows = 2;
    private final int numCols = 3;

    public House_ec21504() {
        houseLayout = new Room[numRows][numCols];

        houseLayout[0][0] = new Room_ec21504();
        houseLayout[0][1] = new Room_ec22476();
        houseLayout[0][2] = new Room_ec22562();
        houseLayout[1][0] = new Room_ec22562();
        houseLayout[1][1] = new Room_ec21582();
        houseLayout[1][2] = new Room_ec21504();
    }

    public Direction visit(Visitor visitor, Direction d) {
        int currentRow = 0;
        int currentCol = 0;

        while (true) {
            System.out.println("You are currently in " + houseLayout[currentRow][currentCol].getClass().getSimpleName() + ".");
            System.out.println("Available rooms to visit:");
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    System.out.println("[" + row + "," + col + "]: " + houseLayout[row][col].getClass().getSimpleName());
                }
            }

            System.out.println("Enter the row and column of the room you would like to visit, separated by a space (e.g., 0 1):");

            Scanner sc = new Scanner(System.in);
            int newRow = sc.nextInt();
            int newCol = sc.nextInt();

            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols) {
                currentRow = newRow;
                currentCol = newCol;
                Direction exitDirection = houseLayout[currentRow][currentCol].visit(visitor, Direction.UNDEFINED);
            } else {
                System.out.println("Invalid room coordinates! Please try again.");
            }
        }
    }
}

package OOP.ec22518.MP.contributions;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.InputMismatchException;


class House_ec22842 implements Visitable {

    // Method to print a value with a new line.
    static < T > void pr(T a) {
        System.out.println(a);
    }

    // Method to read a double value from the user with a message prompt.
    static Double inputDouble(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        double n = scanner.nextDouble();

        return n;
    }
    
    // Method to read a string value from the user with a message prompt.
    static String inputString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String m = scanner.nextLine();
        return m;
    }

    // Basement class which extends Room and implements Visitable interface.
    static class Basement extends Room implements Visitable {
        // Declare variables and objects for the class.
        Room Bank, Club, Cave, room;
        boolean valid = false;
        boolean leave = false;
        char[] arr1 = {
            'a',
            'b',
            'c'
        };
        char[] arrH = {
            'a',
            'b',
            'c'
        };
        char choice = 'a';
        char choice2 = 'a';

        Scanner scanner = new Scanner(System.in);
        private ArrayList < Room > rooms = new ArrayList < > ();
        private double squareFootage = 250.0;
        private double value;
        boolean lock = false;

        // Method to add a room to the basement.
        private boolean addRoom(Room room) {
            // Check if the room is valid.
            if (room == null) {
                System.out.println("Error: Invalid room input. Please enter an actual room.");
                return false;
            }

            // Print the reference of the added room.
            System.out.println("Here is the reference of your room : " + room.hashCode());

            // Prompt the user to input the dimension of the room.
            double roomDimension = inputDouble("Input the dimension of the room:");
            while (roomDimension <= 0) {
                roomDimension = inputDouble("Error: Invalid room dimension. Please input a value greater than 0.");
            }

            // Check if there is enough square footage to add the room.
            if (roomDimension > squareFootage) {
                System.out.println("Error: Not enough square footage to add this room.");
                return false;
            }

            // Subtract the room dimension from the square footage and add the room to the list.
            this.squareFootage -= roomDimension;
            this.rooms.add(room);
            return true;
        }

        // Method to remove a room by its hashcode.
        private void removeRoomByHashcode() {
            Iterator <Room> iter = rooms.iterator();

            // Prompt the user for the reference number (hashcode) of the room to remove.
            System.out.println("Reference Number? ");

            int hashCode;
            try {
                hashCode = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid reference number.");
                scanner.next(); // Clear the invalid input
                return;
            }

            // Try to find the room with the given hashcode and remove it.
            boolean roomFound = false;
            while (iter.hasNext()) {
                Room room = iter.next();
                if (room.hashCode() == hashCode) {
                    iter.remove();
                    System.out.println("Room removed successfully.");
                    roomFound = true;
                    break;
                }
            }

            // If the room is not found, print a message.
            if (!roomFound) {
                System.out.println("Room not found.");
            }
        }

        // Method to get the remaining square footage in the basement.
        private double getSquareFootage() {
            return squareFootage;
        }

        // Default constructor for Basement class.
        
        Basement() {
        }

        // Method to visit the basement, allowing the visitor to interact with the floor.
        public Direction visit(Visitor v, Direction d) {
            int counter = 0;
            // Welcome message for the visitor.
            v.tell("Welcome to the Doll Floor. Here, you have the opportunity to design and customize your own floor by selecting the rooms we have to offer.");

            // While there is remaining square footage, allow the visitor to add rooms.
            while (this.squareFootage > 0 && choice != 'd') {
                choice = v.getChoice("You can use up to " + squareFootage + " square foot to customize your own floor. Please select the type of room you want to add " + counter + ":\na)Add Masjid b) Leave", arr1);
                Room newRoom = null;
                if (choice == 'a') {
                    // Get the room from the username input.
                    if (getSquareFootage() > this.squareFootage) {
                        // If there is not enough square footage, inform the visitor.
                        v.tell("Sorry, not enough square footage to add this room.");
                    } else {
                        // Add the room to the basement.
                        addRoom(newRoom);
                    }
                } else if (choice == 'd') {
                    // Do nothing.
                } else {
                    // Invalid input, show an error message.
                    v.tell("\n---------Error: Invalid Input:" + choice + "--------");
                    v.tell("\n--------Please input a valid character--------");
                }
            }

            boolean leave = false;
            // While the visitor does not choose to leave.
            while (!leave) {
                choice2 = v.getChoice("You have the option to either visit your custom house or leave the secret floor. Please select one of the following options:\na) Visit your custom house\nb) Leave the secret floor and erase your history\nc) Modify your house", arrH);

                if (choice2 == 'a') {
                    // Visit each room in the custom house.
                    for (Room room: rooms) {
                        room.visit(v, d);
                    }
                } else if (choice2 == 'b') {
                    // Leave the custom floor and return to the original position.
                    v.tell("\nYou are now leaving the custom floor and returning to your original position.");
                    leave = true;
                } else if (choice2 == 'c') {
                    boolean exitEditor = false;
                    // Enter the editor mode to modify the house.
                    while (!exitEditor) {
                        char c1 = v.getChoice("\nWelcome to the editor, I told you my House wasn't like others..\na) Add room by username\nb) Remove room inside the floor\nc) Leave editor mode", arr1);
                        switch (c1) {
                            case 'a':
                                // Add a room by username.
                                if (this.squareFootage <= 250.0) {
                                    v.tell("You cannot add anymore room, delete to add more");
                                } else {
                                    Room room = Contributions.newRoomByUsername(inputString("Please input a valid username."));
                                    if (room == null) {
                                        room = new Room_ec22842();
                                    }
                                    addRoom(room);
                                }
                                break;
                            case 'b':
                                removeRoomByHashcode();
                                break;
                            case 'c':
                                exitEditor = true;
                                break;
                        }
                    }
                }
            }
            return d;
        }
    }

    // Declaration of variables for room instances and a Basement object.
    Room room1, room2, room3, room4, room5, room6, room7, room8, room9;
    Basement basement = new Basement();
    private boolean locked = false;
    Room[][] rooms;
    char[] arr = {'a'};
    char[] arr1 = {'a','b','c','d','e'};
    private int k = 0;

    // Array of the pre-built rooms, in case the user inputs a wrong username.
    Room[] arrayOfRoom = new Room[5];

    House_ec22842() {

        Room[] tempRoom = new Room[5];
        // Initializing the pre-built rooms.
        tempRoom[0] = new Room_ec22830();
        tempRoom[1] = new Room_ec22562();
        tempRoom[2] = new Room_ec221148();
        tempRoom[3] = new Room_ec22504();
        tempRoom[4] = new Room_ec22840();

        // Initializing additional pre-built rooms.
        room1 = new Room_ec22562();
        room2 = new Room_ec22761();
        room3 = new Room_ec22784();
        room4 = new Room_ex21423();
        room5 = new Room_ec22772();
        room6 = new Room_ec22840();
        room7 = new Room_ec22664();
        room8 = new Room_ec22742();
        room9 = new Room_ec22579();
        basement = new Basement();

        // Add transitions between rooms.

        System.out.println("This house is not like another house, it is more than a house.");
        // While the house is not locked.
        while (!locked) {

            for (int j = 0; j < 5; j++) {
                // Get rooms by username input.
                arrayOfRoom[j] = Contributions.newRoomByUsername(inputString("You have the possibility to add up to 4 rooms of you want, please enter the id of the Room you want to use ex : ec22842. If the username is not known by the system, it will place some pre-built rooms instead."));
                if (arrayOfRoom[j] == null) {
                    // If the user inputs a wrong username, use a pre-built room instead.
                    System.out.println("You input the wrong username, the room the system is gonna place a pre-built room instead");
                    arrayOfRoom[j] = tempRoom[j];
                }
            }
            locked = true;
        }

        // Initialize the room matrix with the created rooms.
        rooms = new Room[][] {
            {
                room1,
                room2,
                room3,
                room4,
                room5
            }, {
                arrayOfRoom[0],
                arrayOfRoom[1],
                arrayOfRoom[2],
                arrayOfRoom[3],
                arrayOfRoom[4]
            }, {
                room6,
                room7,
                room8,
                room9,
                room8
            }, {
                basement,
                basement,
                basement,
                basement,
                basement
            }
        };
    }



    // user can choose the next room he wants to go, if username not found then goes to preset room.
    // basement is now in a position inside the room.

    public Direction visit(Visitor visitor, Direction direction) {
        boolean leave = false;
        boolean inTheGarden = false;
        boolean validD = false;
        boolean basementLocked = true; // when in the garden, the lock of the basement is down, so a new part of the house is unlocked
        int col = 0;
        int row = 0;
        char choice12 = 'a';
        int numOfRows = rooms.length;
        int numOfCols = rooms[0].length;

        // Continue visiting the rooms until the user chooses to leave.
        while (!leave) {

            validD = false;
            Direction d = rooms[row][col].visit(visitor, direction);

            // If the user is in a hallway room (rooms with even row and column indices).
            if (row % 2 == 0 && col % 2 == 0) {

                // Keep asking the user for a direction until a valid direction is provided.
                while (!validD) {

                    visitor.tell("\nYou are currently in the hallway.\n");
                    choice12 = visitor.getChoice("The hallway allows you to move to different positions in the house. You have the following options:\n\na)Move forward.\nb)Move backward.\nc)Move upstairs.\nd)Move downstairs.\ne)Exit the house", arr1);

                    // Handle the user's choice of direction.
                    if (choice12 == 'c') {
                        if (row + 1 >= 4) {
                            visitor.tell("--------Error: Cannot move to position (" + (row + 1) + ", " + col + ")--------\n");
                            visitor.tell("--------Please try again in another direction--------");
                        } else {
                            visitor.tell("You are heading upstairs");
                            row = (row + 1) % 4;
                            validD = true;
                        }
                    } else if (choice12 == 'a') {
                        if (col + 1 >= 5) {
                            visitor.tell("--------Error: Cannot move to position (" + row + ", " + (col + 1) + ")--------\n");
                            visitor.tell("--------Please try again in another direction--------");
                        } else {
                            visitor.tell("You are heading forward");
                            col = (col + 1) % 5;
                            validD = true;
                        }
                    } else if (choice12 == 'd') {
                        if (row - 1 < 0) {
                            visitor.tell("--------Error: Cannot move to position (" + (row - 1) + ", " + col + ")--------\n");
                            visitor.tell("--------Please try again in another direction--------");
                        } else {
                            visitor.tell("You are heading downstairs");
                            row = (row - 1) % 4;
                            validD = true;
                        }
                    } else if (choice12 == 'b') {
                        if (col - 1 < 0) {
                            visitor.tell("--------Error: Cannot move to position (" + row + ", " + (col - 1) + ")--------\n");
                            visitor.tell("--------Please try again in another direction--------");
                        } else {
                            visitor.tell("You are heading to the opposite direction");
                            col = (col - 1) % 5;
                            validD = true;
                        }
                    } else if (choice12 == 'e') {
                        visitor.tell("Bye");
                        leave = true;
                        validD = true;

                    } else {
                        visitor.tell("--------Error: Invalid direction provided: " + d + "--------");
                        visitor.tell("--------Please try again in another direction--------");
                    }
                }

                System.out.println("\nCurrent position: (" + row + ", " + col + ")");
            } else {
                visitor.tell("Heading forward..");

                if (col + 1 >= numOfCols) {
                    visitor.tell("Oops, it seems like there's no more available rooms in this floor. Let's move on to the next floor.");
                    if (row + 1 >= numOfRows) {
                        inTheGarden = false;

                        while (!inTheGarden) {

                            Scanner scanner = new Scanner(System.in);
                            visitor.tell("You are now in the garden at position [3,3].");
                            visitor.tell("Please enter the coordinates of the room you would like to visit within the house. The maximum coordinate values are [3,3] and the minimum is [0,0].");
                            visitor.tell("\nEnter the row number: ");
                            int newRow = scanner.nextInt();
                            visitor.tell("\nEnter the col number:");
                            int newCol = scanner.nextInt();
                            if (newRow >= 0 && newRow <= 3 && newCol >= 0 && newCol <= 4) {
                                if (rooms[newRow][newCol] != null) {
                                    row = newRow;
                                    col = newCol;
                                    visitor.tell("You have now entered the room located at position [" + newRow + ", " + newCol + "].");
                                } else {
                                    visitor.tell("Invalid input. Please try again.");
                                }
                            }
                            char choice = visitor.getChoice("\nYou've unlocked the basement. Do you want to check it out?\n\na) Yes, take me to the basement.\nb) No, I'm good for now.", arr1);
                            basementLocked = false;

                            if (choice == 'a') basement.visit(visitor, direction);
                            if (choice == 'b') visitor.tell("You've left the house.");

                            inTheGarden = true;

                        }

                    } else {
                        row = row + 1;
                        col = 0;
                    }
                } else {
                    col = col + 1;
                    System.out.println("\nCurrent position: (" + row + ", " + col + ")");
                }
            }

        }
        return direction.opposite(direction);
    }
}

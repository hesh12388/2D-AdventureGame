package OOP.ec22518.MP.contributions;

import java.util.Arrays; // Used to compare arrays later in the program

public class House_ec22524 implements Visitable {
    // initialising room variables
    Room r1;
    Room r2;
    Room r3;
    Room r4;

    House_ec22524() {
        // the 4 room instances
        r1 = new Room_ec22524(); // my room
        r2 = new Room_ec22462();
        r3 = new Room_ec21504();
        r4 = new Room_ec22763();
    }

    // the visit method
    public Direction visit(Visitor v, Direction d) {
        int[] coords = {1,1}; // array with 2 integers to act as coordinates

        char userOption;
        boolean exit = false; // used for exiting the while loop.

        v.tell("\nYou walk in to the house and you find yourself in a junction of 2 hallways. You decide to explore the house.\n");

        // the main while loop
        while (!exit) {

            // get the user choice
            userOption = v.getChoice("a. Go North\nb. Go West\nc. Go South\nd. Go East\ne. Where Am I?\nf. Explanation of House Layout", new char[]{'a', 'b', 'c', 'd', 'e', 'f'});

            // ---- if statements to choose based on user option ----
            if (userOption == 'a') { // go north
                d = Direction.FROM_SOUTH;
                coords = goNorth(coords, v); // calls the method to go North
                currentCorridorPosition(coords, v); // prints the current position of the user.
            }
            else if (userOption == 'b') { // go west
                d = Direction.FROM_EAST;
                coords = goWest(coords, v);
                currentCorridorPosition(coords, v);
            }
            else if (userOption == 'c' && Arrays.equals(coords, new int[]{1, 0})) { // else if statement to exit the house if the user walks south from the south corridor.
                d = Direction.FROM_NORTH;
                coords[0]=1;
                coords[1]=-1; // coordinate outside the house
            }
            else if (userOption == 'c') { // go south
                d = Direction.FROM_NORTH;
                coords = goSouth(coords, v);
                currentCorridorPosition(coords, v);
            }
            else if (userOption == 'd') { // go east
                d = Direction.FROM_WEST;
                coords = goEast(coords, v);
                currentCorridorPosition(coords, v);
            }
            else if (userOption=='e') { // just prints the user's position
                currentCorridorPosition(coords, v);
            }
            else if (userOption == 'f') { // explains the house layout
                houseExplanation(v);
            }
            else {
                v.tell("Something went wrong.");
            }
            // ---- -------------------------- ----


            // ---- goes into rooms (or exit) based on the coordinates ----
            if (coords[0] == 0 && coords[1] == 0) { // enters room 1 if coordinates are (0,0)
                v.tell("You are entering Room 1, located south-west.\n");
                d = r1.visit(v, d);
                coords = positionFixer(d, coords, v);
                currentCorridorPosition(coords, v);
            }
            else if (coords[0] == 2 && coords[1] == 0) { // enters room 2 if coordinates are (2,0)
                v.tell("You are entering Room 2, located south-east.\n");
                d = r2.visit(v, d);
                coords = positionFixer(d, coords, v);
                currentCorridorPosition(coords, v);
            }
            else if (coords[0] == 2 && coords[1] == 2) { // enters room 3 if coordinates are (2,2)
                v.tell("You are entering Room 3, located north-east.\n");
                d = r3.visit(v, d);
                coords = positionFixer(d, coords, v);
                currentCorridorPosition(coords, v);
            }
            else if (coords[0] == 0 && coords[1] == 2) { // enters room 4 if coordinates are (0,2)
                v.tell("You are entering Room 4, located north-west.\n");
                d = r4.visit(v, d);
                coords = positionFixer(d, coords, v);
                currentCorridorPosition(coords, v);
            }
            else if (coords[0]==1 && coords[1]==-1) { // exits the house if the coordinates are "outside the house"
                v.tell("You have left the house (1,-1).");
                exit=true;
            }
            // ---- ----------------------- ----
        }

        return d;
    }

    // ---- methods to go to a particular direction, takes the coordinate array as an argument ----
    int[] goNorth (int[] c, Visitor v) {
        if (c[1] < 2) {// makes sure that the coordinate doesn't go above the 3x3 limit of the house
            c[1]++; // increments the y variable to go north
        } else { // if it is higher than the limit prints this message
            v.tell("You have hit a wall.");
        }
        return c; // return the coordinate array
    }

    int[] goSouth (int[] c, Visitor v) {
        if (c[1] > 0) {
            c[1]--; // decrements the y variable to go south
        } else {
            v.tell("You have hit a wall.");
        }
        return c;
    }
    int[] goEast (int[] c, Visitor v) {
        if (c[0]<2) {
            c[0]++; // increments the x variable to go east
        }
        else {
            v.tell("You have hit a wall.");
        }
        return c;
    }
    int[] goWest (int[] c, Visitor v) {
        if (c[0]>0) {
            c[0]--; // decrements the y variable to go west
        }
        else {
            v.tell("You have hit a wall.");
        }
        return c;
    }
    // ----  ----


    // this method fixes the position coordinates of the visitor after visiting a room
    int[] positionFixer (Direction d, int[] c, Visitor v) {
        if (d== Direction.TO_SOUTH) {
            c = goSouth(c, v);
        }
        else if (d==Direction.TO_WEST) {
            c = goWest(c, v);
        }
        else if (d==Direction.TO_NORTH) {
            c = goNorth(c, v);
        }
        else if (d==Direction.TO_EAST) {
            c = goEast(c, v);
        }
        else {
            System.out.println("Something went wrong.");
        }
        return c; // returns the coordinate array
    }

    // prints the user's position in the hallways using the coordinates
    void currentCorridorPosition (int[] coords, Visitor v) {
        if (coords[0] == 1 && coords[1] == 0) { // checking the coordinates for the south corridor
            v.tell("You are in the south corridor. Position: (1,0). If you walk further south, YOU WILL EXIT the house.");
        }
        else if (coords[0] == 1 && coords[1] == 1) { // for the central junction
            v.tell("You are in the corridor junction in the center. Position: (1,1).");
        }
        else if (coords[0] == 1 && coords[1] == 2) { // for the north corridor
            v.tell("You are in the north corridor. Position: (1,2).");
        }
        else if (coords[0] == 0 && coords[1] == 1) { // for the west corridor
            v.tell("You are in the west corridor. Position: (0,1).");
        }
        else if (coords[0] == 2 && coords[1] == 1) { // for the east corridor
            v.tell("You are in the east corridor. Position: (2,1).");
        }
    }

    // this method simply prints out the explanation of the layout of the room
    void houseExplanation (Visitor v) {
        v.tell("----Explanation----\nThe house is a simple 3x3 matrix with each corner of the matrix being a room.\nThe house has a cross shaped hallways that can be navigated by selecting directions.\nThe exit is located on the south corridor. To leave, just navigate to the south corridor and walk south further.");
        v.tell("The corner edges of the house are rooms. After being done with a room, it returns you to the position you first entered it.");
        v.tell("Hope you have a fun time here!\n-------------------");
    }

}

package OOP.ec22518.MP.contributions;

import java.util.Random;

public class House_ec22675 implements Visitable {

    private Room[][] rooms;
    private int currentFloor = 0;
    private int currentRoomAtFloor = 0;

    public House_ec22675() {
        this.rooms = new Room[][]{
            {new Room_ec19389(), new Room_ec22675(), new Room_cb21793()}, // Ground floor
            {new Room_ec211044(), new Room_ec221003(), new Room_ec221002()}, // Middle floor
            {new Room_ec211045(), new Room_bt21057(), new Room_ec221004()}, // Top floor
        };
            // room -> room -> room
                    // up/down
            // room -> room -> room
                    // up/down
            // room -> room -> room
    }

    // @Override
    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        Direction directionEnteringRoomFrom = directionVistorArrivesFrom;
        System.out.println("Welcome to my house");
        System.out.println("to exit you must have a key, or visit 5 rooms in the house!");
        handleVisitor(visitor, directionEnteringRoomFrom);
        System.out.println("You walk to the exit of the house but see a sign that says you shall not leave");
        System.out.println("And you see a coin to toss on floor, you must toss it if you want to leave.");
        doCoinTosses(); 
        System.out.println("Exiting house...");
        
        return Direction.TO_WEST;
    }

    public void handleVisitor(Visitor visitor, Direction d) {
        Direction dirToMove = d;
        boolean exit = false;
        System.out.println("You are currently at floor " + this.currentFloor + " select an option");
        // ask user if they want to enter current room, or change room/floor
        while (!exit) {
            char option = visitor.getChoice("a)enter room, b)change floor, c)move to next room, d) exit", new char[]{'a', 'b', 'c', 'd'});
            if (option == 'a') {
                // enter the current room, use the direction of previous room to enter.
                dirToMove = rooms[this.currentFloor][this.currentRoomAtFloor].visit(visitor, dirToMove);
            } else if (option == 'b') {
                moveFloor(visitor);
            } else if (option == 'c') {
                moveRoom();
            } else if (option == 'd') {
                exit = true;
            }
        }
        
    }

    private void moveRoom() {
        if (currentRoomAtFloor < 2) {
            currentRoomAtFloor++;
        } else {
            currentRoomAtFloor = 0;
        }
        System.out.println("You are at room " + this.currentRoomAtFloor + " at floor " + this.currentFloor) ;
    }

    private void moveFloor(Visitor visitor) {
        char c = visitor.getChoice("a) up, b) down", new char[]{'a', 'b'});
        if (c == 'a'){
            if (this.currentFloor > 1) {
                System.out.println("Already at top floor");
            } else {
                this.currentFloor++;
            }
        } else if (c == 'b') {
            if (this.currentFloor <= 0) {
                System.out.println("Already at bottom floor");
            } else {
                this.currentFloor--;
            } 
        }
        System.out.println("You are currently at floor " + this.currentFloor);
    }

    public void doCoinTosses() {
        Random rand = new Random();
        int toss= 0;
        while (toss == 0) {
            System.out.println("Tossing coin...");
            toss = rand.nextInt(2);
            System.out.println("Trying again!");
        }
    }
    
}

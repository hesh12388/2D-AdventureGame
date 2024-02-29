package OOP.ec22518.MP.contributions;

class House_ec22697 implements Visitable {

    Room roomOne;
    Room roomTwo;
    Room roomThree;
    int currentRoomNum;
    char[] THREE_OPTIONS = {'a', 'b', 'c'};
    char[] TWO_OPTIONS = {'a', 'b'};


    House_ec22697() {
        roomOne = new Room_ec22697();
        roomTwo = new Room_ec22716();
        roomThree = new Room_ec22476();
        currentRoomNum = 0;
    }

    // Returns direction the visitor leaves towards.
    public Direction visit(Visitor visitor, Direction dir) {

        // room one then two, then three

        visitor.tell("You find yourself in a dimly lit house");
        visitor.tell("In front of you is a large green door");
        visitor.tell("To you left and right are seemingly endless wooden staircases");

        char choice = Character.toLowerCase(visitor.getChoice("Do you want to\n\ta) Go up the left staircase\n\tb) Go into the room straight ahead\n\tc) Go up the right staircase", THREE_OPTIONS));

        // ask which one they go to 
        // go clock wise
        // so if they start with rightmost room, then their only option is to leave the house.

        // DOES THIS DIR HAVE TO CHANGE?

        switch (choice) {
            case 'a':
                visitor.tell("You walk up the stairs and open the door...");
                roomOne.visit(visitor, dir);
                currentRoomNum = 1;

                // ask if they want to leave and go to the next room

                // do you a) go to the next room b) exit the house
                // if a, go to the next room - in this case visit 

                // if they want to leave, do nothing

                afterRoomVisit(visitor, dir);

                break;
            
            case 'b':
                visitor.tell("You quietly make you way to the door and start to open it...");
                roomTwo.visit(visitor, dir);
                currentRoomNum = 2;

                afterRoomVisit(visitor, dir);
                break;
            
            case 'c':
                visitor.tell("You sprint up the stairs and begin to open a polished wooden door...");
                roomThree.visit(visitor, dir);
                currentRoomNum = 3;

                afterRoomVisit(visitor, dir);
                break;
        
            // default: // is this needed?
            //     visitor.tell("Suddenly, the lights in the house start to flicker");
            //     visitor.tell("You look around, only to see a pair of glowing eye staring at you");
            //     visitor.tell("You rush out of the house as fast as you can...");
            //     break;
        }

        visitor.tell("Suddenly, the lights in the house start to flicker...");
        visitor.tell("You look around, only to see a pair of glowing eyes staring at you!");
        visitor.tell("You rush out of the house as fast as you can...");
        

        return Direction.opposite(dir);
    }

    // Returns the next room in the house given the current room
    private void afterRoomVisit(Visitor visitor, Direction visitorDir) {
        char choice = Character.toLowerCase(visitor.getChoice("Do you want to\n\ta) Go to the next room\n\tb) Exit the house", TWO_OPTIONS));

        switch (choice) {
            case 'a':
                if (currentRoomNum != 3) { // only if they're not in the last room
                    Room nextRoom = getNextRoom();
                    nextRoom.visit(visitor, visitorDir);
                }

                // otherwise leave!
                break;
        
            case 'b':
                break;
        }

        return;
    }

    //Returns the next room in the house given the current room
    private Room getNextRoom() {
        if (currentRoomNum == 1) {
            return roomTwo;
        }
        else if (currentRoomNum == 2) {
            return roomThree;
        }
        else {
            return roomOne; // Is there another way of doing this w/o this else statement?
        }

    }

}
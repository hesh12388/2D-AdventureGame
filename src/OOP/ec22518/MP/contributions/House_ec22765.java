package OOP.ec22518.MP.contributions;

public class House_ec22765 implements Visitable {

    // instance variables for holding rooms and other variables
    Room_ec22765 mainRoom;
    Room_ec21578 amongUs;
    Room_ec22696 phoneRoom;
    Room_ex20181 spookyRoom;
    Room[] rooms;
    int roomIndex;
    int goldAmount;
    boolean inHallway;

    // constructor to create and connect rooms
    public House_ec22765() {
        // create instances of various Room classes and assign them to room variables
        Room_ec22765 mainRoom = new Room_ec22765();
        Room_ec21578 amongUs = new Room_ec21578();
        Room_ec22696 phoneRoom = new Room_ec22696();
        Room_ex20181 spookyRoom = new Room_ex20181();

        rooms = new Room[] {mainRoom,amongUs,phoneRoom,spookyRoom};
        roomIndex = 0;
        goldAmount = 3;
        inHallway = false;
    }

    // implement the visit method from the Visitable interface
    public Direction visit(Visitor visitor, Direction fromDirection) {
        // determine which room to pass the visitor to next based on the direction they came from
        visitor.tell("You wake up in Room_ec22765, your objective should be to exit the house but feel free to explore the house");
        Direction nextDirection = rooms[roomIndex].visit(visitor, fromDirection);

        if (nextDirection == Direction.FROM_NORTH) {
            roomIndex = 1;
        }
        else if (nextDirection == Direction.FROM_EAST) {
            if (roomIndex == 3) {
                roomIndex = 0;
            }
            else {
                roomIndex++;
            }
        }
        else if(nextDirection == Direction.FROM_SOUTH) {
            roomIndex = 3;
        }
        else if (nextDirection == Direction.FROM_WEST) {
            if (roomIndex == 0) {
                roomIndex = 3;
            }
            else {
                roomIndex--;
            }
        }
        else {
            visitor.tell("You have managed to glitch out of the house.Hidden ending");
            return Direction.UNDEFINED;
        }
        if (roomIndex == 2 && nextDirection == Direction.FROM_SOUTH) {
            inHallway = true;
        }
        if (inHallway) {
            visitor.tell("You have reached a hallway and you approach 3 doors. One of them has to be the exit");
            char[] choices = { 'a', 'b', 'c' };
            int choiceIndex = visitor.getChoice("What do you want to do?", choices);
            char choice = choices[choiceIndex];

            if (choice == 'a') {
                visitor.tell("You managed to go outside of the house, however now you are in the garden, the walls are spiky so you can't climb out");
                if (goldAmount > 2) {
                    visitor.tell("A magical goblin appeared and gave you the keys which you used to exit the garden and house altogether you made it out");
                }
                else {
                    visitor.tell("You found a window which you climbed up and entered the phone room, if you remember your way around you can come back to the hall");
                    inHallway = false;
                    roomIndex = 2;
                }
            }
            if (choice == 'b') {
                visitor.tell("You managed to escape the house the proper way. Congratulations!");
                return Direction.UNDEFINED;
            }
            if (choice == 'c') {
                visitor.tell("You entered a dark room, and the next second you find yourself falling and losing consciousness. You wake up back where you started.Things might be different this time");
                inHallway = false;
                roomIndex = 0;
            }
        }


        return nextDirection;
    }

}



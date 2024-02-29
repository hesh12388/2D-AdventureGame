package OOP.ec22518.MP.contributions;

class House_ec22658 implements Visitable {

    private Room firstRoom; 
    private Room secondRoom; 
    private Room thirdRoom; 
    private boolean openBox;



    public House_ec22658() {
        firstRoom = new Room_ec22466();
        secondRoom = new Room_ec21582();
        thirdRoom = new Room_ec22430();
    }

    public Direction visit(Visitor visitor, Direction dir) {

        Room[] rooms = { firstRoom, secondRoom, thirdRoom };
        openBox = false;

        visitor.tell("Welcome to the house");
        visitor.tell("You walk down the hall and see three doors. On the right, left and infront.");
        visitor.tell("You also see a key. Could be for the doors");
        char[] options = { '1', '2', '3', '4' };
        char choice = visitor.getChoice("Do you want to either enter the 1) first room, 2) second room, 3) third room, or 4) exit", options);

        while (choice != '4') {

             if (choice == '1') {
                visitor.tell("You enter the room on the left.");
                dir = Direction.TO_EAST; 
                dir = rooms[0].visit(visitor, dir);
                visitor.tell("You are back in the hallway.");
            }
            if (choice == '2') {
                visitor.tell("You enter the door infront of you");
                dir = Direction.TO_SOUTH; 
                dir = rooms[1].visit(visitor, dir);
                visitor.tell("You come back into the hall");
            }
            if (choice == '3') {
                visitor.tell("You enter the right room");
                dir = Direction.TO_WEST; 
                dir = rooms[2].visit(visitor, dir);
                visitor.tell("You find another key in which you use that to open a box where there is coins inside.");
                 openBox = true;
                visitor.giveGold(3);
            }

            choice = visitor.getChoice("Do you want to either enter the 1) first room, 2) second room, 3) third room, or 4) exit", options);
        }

        visitor.tell("You have left");
        return dir;
    }
}

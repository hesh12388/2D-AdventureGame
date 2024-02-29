package OOP.ec22518.MP.contributions;

class House_ec22430 implements Visitable {

    private Room firstRoom; // west
    private Room secondRoom; // north
    private Room thirdRoom; // east
    private boolean boxOpened;



    public House_ec22430() {
        firstRoom = new Room_ec22723();
        secondRoom = new Room_ec22742();
        thirdRoom = new Room_ec22902();
    }

    public Direction visit(Visitor visitor, Direction dir) {

        Room[] rooms = { firstRoom, secondRoom, thirdRoom };
        boxOpened = false;

        visitor.tell("Welcome to this house. ");
        visitor.tell("You walk down the hallway and see a door to your left, a door in front and a door to the right.");
        visitor.tell("You also spot a small box with a lock by the entrance to the first door.");
        char[] options = { '1', '2', '3', '4' };
        char choice = visitor.getChoice("Do you wish to enter the 1) first, 2) second, 3) third room, or 4) leave?", options);

        while (choice != '4') {

            if (choice == '1') {
                visitor.tell("You enter the room on the left.");
                dir = Direction.TO_EAST; // Coming from the east
                dir = rooms[0].visit(visitor, dir);
                visitor.tell("You are back in the hallway.");
            }
            if (choice == '2') {
                visitor.tell("You enter the room straight ahead.");
                dir = Direction.TO_SOUTH; // Coming from the south
                dir = rooms[1].visit(visitor, dir);
                visitor.tell("You are back in the hallway.");
            }
            if (choice == '3') {
                visitor.tell("You enter the room on the right.");
                dir = Direction.TO_WEST; // Coming from the west
                dir = rooms[2].visit(visitor, dir);
                visitor.tell("You spot a key on your way out which you use to open the box in the hallway. You find gold coins inside.");
                boxOpened = true;
                visitor.giveGold(3);
            }

            choice = visitor.getChoice("Do you wish to enter the 1) first, 2) second, 3) third room, or 4) leave?", options);
        }

        visitor.tell("You are leaving the house. Hope you enjoyed your stay.");
        return dir;
    }
}

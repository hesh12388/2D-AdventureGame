package OOP.ec22518.MP.contributions;

class House_ec22882 implements Visitable {

    private Room room1;
    private Room room2;
    private Room room3;

    public House_ec22882() {
        room1 = new Room_ec22867();
        room2 = new Room_ec22992();
        room3 = new Room_ec22882();
    }

    public Direction visit(Visitor visitor, Direction directionOfArrival) {
        visitor.tell("Welcome to the House!");
        visitor.tell("You enter the hall and are faced with 3 doors ");

        char choice = visitor.getChoice("Which would you like to enter? 1)Room 1, 2)Room 2, 3)Room 3, or 4)Exit",
                new char[] { '1', '2', '3', '4' });

        while (choice != '4') {

            if (choice == '1') {
                visitor.tell("You entered the first room on the left");
                directionOfArrival = Direction.TO_EAST;
                directionOfArrival = room1.visit(visitor, directionOfArrival);
                visitor.tell("You go back in the hallway.");
            }

            if (choice == '2') {
                visitor.tell("You entered the second room in front of you");
                directionOfArrival = Direction.TO_SOUTH;
                directionOfArrival = room2.visit(visitor, directionOfArrival);
                visitor.tell("You go back in the hallway.");
            }

            if (choice == '3') {
                visitor.tell("You entered the last room on the right");
                directionOfArrival = Direction.TO_WEST;
                directionOfArrival = room3.visit(visitor, directionOfArrival);
                visitor.tell("You go back in the hallway.");
            }

            choice = visitor.getChoice("Which would you like to enter next? 1) Room 1, 2) Room 2, 3) Room 3, or 4)Exit",
                    new char[] { '1', '2', '3', '4' });
        }

        visitor.tell("You have left");
        return directionOfArrival;
    }
}
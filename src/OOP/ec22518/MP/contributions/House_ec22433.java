package OOP.ec22518.MP.contributions;

public class House_ec22433 implements Visitable {
    private final int rIndex = 3;
    private Room[] rooms;


    public House_ec22433() {
        rooms = new Room[rIndex];
        rooms[0] = new Room_ec22433();
        rooms[1] = new Room_ec20258();
        rooms[2] = new Room_ec221022();
    }

    public Direction visit(Visitor vPerson, Direction dIntoHouse) {
        Direction d = Direction.TO_NORTH;
        vPerson.tell("Welcome to the house of Pedigru Academia!");
        String routes = ("Would you like to enter [a-d] - a) Room 1 heading North, b) Room 2 heading East, 3) Room 3 heading West, 4) Leave the House");
        char[] options = {'a', 'b', 'c', 'd'};
        String choice = String.valueOf(vPerson.getChoice(routes, options));
        Room currentRoom = rooms[rIndex];
        boolean leaveHouse = false;
        while (!leaveHouse) {
            if (choice == "a") {
                d = Direction.TO_NORTH; // d = TO_SOUTH (origin)
            } else if (choice == "b") {
                d = Direction.TO_EAST; // d = TO_WEST (origin)
            } else if (choice == "c") {
                d = Direction.TO_WEST; // d = TO_EAST (origin)
            } else if (choice == "d") {
                d = Direction.opposite(dIntoHouse); // Leave the way you came
                leaveHouse = true;
            } else {
                vPerson.tell("Enter the correct number for each option!");
            }

            d = currentRoom.visit(vPerson, d);

        }
        d = currentRoom.visit(vPerson, d);
        return d;
    }
}

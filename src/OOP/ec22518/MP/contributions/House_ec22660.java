package OOP.ec22518.MP.contributions;

public class House_ec22660 implements Visitable {
    private Room[] rooms;
    private Room currentR;

    public House_ec22660() {
        rooms = new Room[4];
        //all the rooms in the house
        rooms[0] = new Room_ec22741(); // Alicia
        rooms[1] = new Room_ec22860(); // Mariam
        rooms[2] = new Room_ec22660(); // Me
        rooms[3] = new Room_ec221017(); // Konrad
    }

    public Direction visit(Visitor visitor, Direction d) {

        boolean leaveHouse = false;
        // loop to know when visitor leaves house
        while (leaveHouse == false) {
            if (d == Direction.FROM_SOUTH || d == Direction.TO_NORTH) {
                currentR = rooms[0];
                d = currentR.visit(visitor, d);
            }
            else if (d == Direction.FROM_NORTH || d == Direction.TO_SOUTH) {
                currentR = rooms[3];
                d = currentR.visit(visitor, d);
            }
            else if (d == Direction.FROM_WEST || d == Direction.TO_EAST) {
                currentR = rooms[1];
                d = currentR.visit(visitor, d);
            }
            else if (d == Direction.FROM_EAST || d == Direction.TO_WEST) {
                currentR = rooms[2];
                d = currentR.visit(visitor, d);
            }

            if (currentR == rooms[0] && (d == Direction.TO_SOUTH || d == Direction.FROM_NORTH)) {
                leaveHouse = true;
            }
        }
        return d;
    }
}

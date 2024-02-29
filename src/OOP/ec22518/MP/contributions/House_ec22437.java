package OOP.ec22518.MP.contributions;

class House_ec22437 implements Visitable {
    Room room0 = new Room_ec22466();
    Room room1 = new Room_ec22569();
    Room room2 = new Room_ec22616();
    Room room3 = new Room_ec22623();

    public Direction visit(Visitor v, Direction d) {

        if (room0.visit(v, Direction.FROM_SOUTH).toString() == "heading North") {
            d = Direction.TO_NORTH;
            return d;
        } else if (room0.visit(v, Direction.FROM_SOUTH).toString() == "heading East") {
            d = Direction.TO_EAST;
            return d;
        } else if (room0.visit(v, Direction.FROM_SOUTH).toString() == "heading South") {
            d = Direction.TO_SOUTH;
            return d;
        } else if (room0.visit(v, Direction.FROM_SOUTH).toString() == "heading West") {
            d = Direction.TO_WEST;
            return d;
        }
        return d;
    }
}
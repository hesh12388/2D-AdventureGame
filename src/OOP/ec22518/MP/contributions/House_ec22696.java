package OOP.ec22518.MP.contributions;

class House_ec22696 implements Visitable {
    public Direction visit(Visitor visitor, Direction incomingDirection) {
        Direction[] outgoing = getDirections(incomingDirection);
        visitor.tell("You are in an abandoned hallway. There's an old, creaking door in front of you.");
        char[] options = new char[2];
        options[0] = 'A';
        options[1] = 'B';
        char choice = visitor.getChoice("Would you like to a) continue on, or b) retreat?", options);
        while (choice=='A') {
            visitor.tell("You gingerly open the door, and creep into the next room.");
            Room_ec22696 room1 = new Room_ec22696();
            Room_ec19389 room2 = new Room_ec19389();
            Room_ec20258 room3 = new Room_ec20258();
            Room_ec20315 room4 = new Room_ec20315();
            Direction comingOut = room1.visit(visitor, incomingDirection);
            if (comingOut.equals(outgoing[0])) {
                Direction backout = room2.visit(visitor, comingOut);
            } else if (comingOut.equals(outgoing[1])) {
                Direction backout = room3.visit(visitor, comingOut);
            } else if (comingOut.equals(outgoing[2])) {
                Direction backout = room4.visit(visitor, comingOut);
            }
            choice = visitor.getChoice("Would you like to a) continue on, or b) retreat?", options);
        }
        return Direction.opposite(incomingDirection);
    }
    
    Direction[] getDirections(Direction incoming) {
        Direction[] outgoings = new Direction[3];
        if (incoming.equals(Direction.FROM_SOUTH)){
            outgoings[0] = Direction.TO_WEST;
            outgoings[1] = Direction.TO_NORTH;
            outgoings[2] = Direction.TO_EAST;
        } else if (incoming.equals(Direction.FROM_WEST)){
            outgoings[0] = Direction.TO_NORTH;
            outgoings[1] = Direction.TO_EAST;
            outgoings[2] = Direction.TO_SOUTH;
        } else if (incoming.equals(Direction.FROM_NORTH)){
            outgoings[0] = Direction.TO_EAST;
            outgoings[1] = Direction.TO_SOUTH;
            outgoings[2] = Direction.TO_WEST;
        } else if (incoming.equals(Direction.FROM_EAST)){
            outgoings[0] = Direction.TO_SOUTH;
            outgoings[1] = Direction.TO_WEST;
            outgoings[2] = Direction.TO_NORTH;
        }
        return outgoings;
    }
}

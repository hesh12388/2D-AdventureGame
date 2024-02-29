package OOP.ec22518.MP.contributions;

class House_ec22680 implements Visitable {
    private Room[] rooms;
    private final int RoomIndex = 3;

    House_ec22680() {
        rooms = new Room[RoomIndex];
        rooms[0] = new Room_ec22680();
        rooms[1] = new Room_ec22702();
        rooms[2] = new Room_ec22507();
    }

    public Direction visit(Visitor visitor, Direction direction) {
        char[] RoomChoice = {'N', 'W', 'E', 'S'};
        visitor.tell("You are now in the House of ec22680");
        visitor.tell("I will now present you with multiple choices, make your choice and you will be transported to your destination");
        String options = ("In this house there are only 4 options chose wisely");
        Room current = rooms[RoomIndex];
        String Room = String.valueOf(visitor.getChoice(options, RoomChoice));
        boolean leave = false;

        while (!leave) {
            if (Room == "N") {
                direction = Direction.TO_NORTH;
                visitor.tell("You have entered the room of ec22680");
            } else if (Room == "S") {
                direction = Direction.TO_SOUTH;
                visitor.tell("You left the house");
                leave = true;
            } else if (Room == "E") {
                direction = Direction.TO_EAST;
                visitor.tell("You have entered the room of ec22702");
            } else if (Room == "W") {
                direction = Direction.TO_WEST;
                visitor.tell("You have now entered the room of ec22507");
            } else {
                visitor.tell("Please pick a correct Option");
            }

            direction = current.visit(visitor, direction);
        }
        direction = current.visit(visitor, direction);
        return direction;
    }
    
}

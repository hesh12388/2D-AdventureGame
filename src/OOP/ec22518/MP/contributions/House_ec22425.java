package OOP.ec22518.MP.contributions;

class House_ec22425 implements Visitable {

    private Room[] room;

    House_ec22425() {
        room = new Room[3];
        room[0] = new Room_ec22425();
        room[1] = new Room_ec22446();
        room[2] = new Room_ec22419();
    }


    public Direction visit(Visitor v, Direction d) {

        Room currentRoom = room[0];
        Direction direction = currentRoom.visit(v, d);
        boolean leave = false;

        while (!leave) {

            if (currentRoom==room[0]) {
                v.tell("You entered the room of ec22425. Choose which direction to proceed.");
                if (direction == Direction.TO_NORTH) {
                    v.tell("locked");
                }
                else if (direction == Direction.TO_EAST) {
                    v.tell("locked");
                } else if (direction == Direction.TO_SOUTH) {
                    currentRoom = room[1];
                    v.tell("You entered the room of ec22445");
                } else if (direction == Direction.TO_WEST) {
                    currentRoom = room[2];
                    v.tell("You entered the room of ec22419");
                }
            }

            else if (currentRoom==room[1]) {
                v.tell("You entered the room of ec22446. Choose which direction to proceed. East to exit, north to go back.");
                if (direction == Direction.TO_NORTH) {
                    currentRoom = room[0];
                    v.tell("You have re-entered the room of ec22425.");
                }
                else if (direction == Direction.TO_EAST) {
                    leave = true;
                    v.tell("You have left.");
                } else if (direction == Direction.TO_SOUTH) {
                    v.tell("locked");
                } else if (direction == Direction.TO_WEST) {
                    v.tell("locked");
                }
            }
            else if (currentRoom==room[2]) {
                v.tell("You entered the room of ec22419. Choose which direction to proceed. west to exit, east to go back.");
                if (direction == Direction.TO_NORTH) {
                    v.tell("locked");
                }
                else if (direction == Direction.TO_EAST) {
                    currentRoom = room[0];
                    v.tell("You have re-entered the room of ec22425.");
                } else if (direction == Direction.TO_SOUTH) {
                    v.tell("locked");
                } else if (direction == Direction.TO_WEST) {
                    leave = true;
                    v.tell("You have left.");
                }
            }

            direction = currentRoom.visit(v, direction);
        }

        return Direction.opposite(direction);

    }
}

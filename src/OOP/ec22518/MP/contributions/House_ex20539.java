package OOP.ec22518.MP.contributions;

import java.util.ArrayList;

class House_ex20539 implements Visitable {
    private ArrayList<Room> rooms;

    House_ex20539(Visitor v, Direction d) {
        rooms = new ArrayList<Room>();
        rooms.add(new Room_ex21423());
        rooms.add(new Room_ex21299());
        rooms.add(new Room_ex21213());
        rooms.add(new Room_ex21405());
    }

    public Direction visit(Visitor v, Direction d) {
        int Index = 1;
        Room currentRoom = rooms.get(Index);
        Direction directionLeaving = currentRoom.visit(v, Direction.TO_EAST);
        boolean path = false;
        while (!path) {
            if (directionLeaving == Direction.TO_NORTH) {
                if (currentRoom == rooms.get(1)) {
                    v.tell("No room North.");
                    v.tell("Back to current room.");

                }
                else {
                    v.tell("No exit North.");
                    v.tell("Back to first room.");

                }
            }
            else if (directionLeaving == Direction.TO_EAST) {
                if (currentRoom == rooms.get(2)) {
                    v.tell("No exit East.");
                    v.tell("Back to orginial room.");

                }
                else {
                    Index++;
                    currentRoom = rooms.get(Index);
                }
            }
            else if (directionLeaving == Direction.TO_SOUTH) {
                if (currentRoom == rooms.get(3)) {
                    Index = 1;
                    currentRoom = rooms.get(Index);
                }
                else {
                    v.tell("House you left was South.");
                    path = true;
                }
            }
            else if (directionLeaving == Direction.TO_WEST) {
                if (currentRoom == rooms.get(0)) {
                    v.tell("No exit West.");
                    v.tell("Restarting current room.");

                }
                else {
                    Index--;
                    currentRoom = rooms.get(Index);
                }
            }


            directionLeaving = currentRoom.visit(v, directionLeaving);
        }


        return Direction.opposite(d);
    }
}

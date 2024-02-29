package OOP.ec22518.MP.contributions;

class House_ec22911 implements Visitable {

    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    public Room[] rooms;

    public House_ec22911() {
        room1 = new Room_ec22586();
        room2 = new Room_ec22585();
        room3 = new Room_ec22866();
        room4 = new Room_ec22908();
        rooms = new Room[4];

        rooms[0] = room1;
        rooms[1] = room2;
        rooms[2] = room3;
        rooms[3] = room4;
    };

    public Direction visit(Visitor v, Direction d) {

        int roomNumber = 0;
        v.tell("There is a massive house infront of you.");
        char choice = v.getChoice("Would you like to a) enter the house or b) leave the house?", new char[] { 'a', 'b' });
        if (choice == 'a') {
            while (true) {
                d = rooms[roomNumber].visit(v, d);
                if (d == Direction.TO_SOUTH) {
                    roomNumber++;
                    if (roomNumber == rooms.length) {
                        v.tell("You leave the house!");
                        return d;
                    } else {
                        v.tell("You are in the next room.");
                    }
                } else if (d == Direction.TO_NORTH) {
                    roomNumber--;
                    v.tell("You are in the previous room.");
                } else {
                    v.tell("You still stay in the same room.");
                }
            }


        } 
        else {
            d = Direction.opposite(d);
        }
        return d;
    }
}
package OOP.ec22518.MP.contributions;

class House_ec22634 implements Visitable {
    private Room[] rooms;
    private final int NUMBER_OF_ROOMS = 3;

    House_ec22634(Visitor V, Direction D) {
        rooms = new Room[NUMBER_OF_ROOMS];
        rooms[0] = new Room_ec22634();
        rooms[1] = new Room_ec22419();
        rooms[2] = new Room_ec22748();
    }// end method House_ec22634

    public Direction visit(Visitor V, Direction D) {
        int currentNum = 0;
        V.tell("Welcome to the house!");
        Room currentRoom = rooms[currentNum];
        Direction whichWay = currentRoom.visit(V, Direction.TO_EAST);
        boolean exit = false;

        while (!exit) {
            if (whichWay == Direction.TO_NORTH) {
                V.tell("This door is fake...you have now left the house");     //go north
               exit = true;
            } else if (whichWay == Direction.TO_WEST) {
                if (currentRoom == rooms[0]) {
                    V.tell("There is no door on this side");
                } else {                                              //go west
                    currentNum = currentNum - 1;
                    V.tell("You are now entering Room " + currentNum);
                    currentRoom = rooms[currentNum];
                }

            } else if (whichWay == Direction.TO_EAST) {
                if(currentRoom == rooms[2]){
                    V.tell("There is no door on this side");
                } else {
                    currentNum = currentNum + 1;                     //go east
                    currentRoom = rooms[currentNum];
                    V.tell("You are now entering Room " + currentNum);
                }

            } else if (whichWay == Direction.TO_SOUTH) {
                if (currentRoom == rooms[1]) {
                    V.tell("This door is stuck, sorry!");
                } else {                                            //go south
                    V.tell ("You have discovered a secret door to room 1.");
                    currentRoom = rooms[1];
                }
            }
            whichWay = currentRoom.visit(V,whichWay);
        } // end while
        return whichWay.opposite(D);
    } // end direction
}// end class

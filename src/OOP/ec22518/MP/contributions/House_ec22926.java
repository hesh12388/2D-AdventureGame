package OOP.ec22518.MP.contributions;

class House_ec22926 implements Visitable {
    // set attributes
    private Room northRoom;
    private Room eastRoom;
    private Room westRoom;

    // creating rooms
    House_ec22926() {
        northRoom = new Room_ec22798();
        eastRoom = new Room_ec22943();
        westRoom = new Room_ec22943();
    }

    public Direction visit(Visitor visitor, Direction direction) {

        // if alive or dead, set as alive naturally
        boolean inHouse = true;
        visitor.tell("Welcome!!!!");

        while (inHouse) {

            visitor.tell(
                    "You are in the hallway. There are three doors to the north, east and west. You also see a frame with a item attatched to the wall.");
            char[] directionChoices = { 'N', 'E', 'W', 'H', 'L' };

            char hallway = visitor.getChoice(
                    "Would you like to enter the (N)orth, (E)ast or (W)est room, go to the (H)int or (L)eave",
                    directionChoices);

            Boolean hintUsed = false;
            Boolean eastVisited = false;

            // OPtions

            //Hint for user
            if (hallway == 'H') {
                visitor.tell(
                        "You read that you must follow the way of light.");
                hintUsed = true;
            }

            if (hallway == 'E') {
                eastVisited = true;
                direction = eastRoom.visit(visitor, direction);
            }

            else if (hallway == 'W') {
                if (eastVisited) {
                    direction = westRoom.visit(visitor, direction);
                }

                else if (!eastVisited) {
                    visitor.tell(
                            "You opened the door but you got burned alive....");
                    inHouse = false;
                }

                else {
                    visitor.tell("Weird... something happend");
                }
            }

            else if (hallway == 'N') {
                direction = northRoom.visit(visitor, direction);
            }
            
          // LEAVE THE HOUSE

            else if (hallway == 'L') {
                visitor.tell("You played the safe game and left.");
                inHouse = false;
            }

            else {
                visitor.tell("Try Again");
            }
        }
        return direction;
    }
}

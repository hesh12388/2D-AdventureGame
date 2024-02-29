package OOP.ec22518.MP.contributions;

class House_ec22628 implements Visitable {
    final int NUMBER_OF_ROOMS = 5;
    Room[] rooms = new Room[NUMBER_OF_ROOMS];
    House_ec22628() {
        rooms[0] = new Room_ec22422();
        rooms[1] = new Room_ec22628();
        rooms[2] = new Room_ec22627();
        rooms[3] = new Room_ec221247();
        rooms[4] = new Room_ec22471();
        rooms[5] = new Room_ec22945();
    }

    /*maze layout (each number corresponds to the room element number)
      room 3713 is the garden
       {--3713--}
        {--0--} {--1--}
        {--5--} {--2--}
        {--4--} {--3--}
        
    */
    public Direction visit(Visitor v, Direction d) {
        v.tell("Is this a line of rooms? Is this a maze? You feel something strange going on. Can you get to the end?");
        int current_room = 3713;

        while (current_room >= 0) {
            
            if ((current_room == 1 && d == Direction.FROM_EAST)||(current_room == 5 && d == Direction.FROM_SOUTH)) {
                current_room = 0;
                d = rooms[current_room].visit(v, d);
            }
            
            else if ((current_room == 0 && d == Direction.FROM_WEST)||(current_room == 2 && d == Direction.FROM_SOUTH)) {
                current_room = 1;
                d = rooms[current_room].visit(v, d);
            }

            else if ((current_room == 5 && d == Direction.FROM_WEST)||(current_room == 3 && d == Direction.FROM_SOUTH)||(current_room == 1 && d == Direction.FROM_NORTH)) {
                current_room = 2;
                d = rooms[current_room].visit(v, d);
            }
            
            else if ((current_room == 4 && d == Direction.FROM_WEST)||(current_room == 2 && d == Direction.FROM_NORTH)) {
                current_room = 3;
                d = rooms[current_room].visit(v, d);
            }

            else if ((current_room == 3 && d == Direction.FROM_EAST)||(current_room == 5 && d == Direction.FROM_NORTH)) {
                current_room = 4;
                d = rooms[current_room].visit(v, d);
            }

            else if ((current_room == 2 && d == Direction.FROM_EAST)||(current_room == 0 && d == Direction.FROM_NORTH)||(current_room == 4 && d == Direction.FROM_SOUTH)) {
                current_room = 5;
                d = rooms[current_room].visit(v, d);
            }

            else {
                current_room = 3713;
                v.tell("You are currently at the front of the house");
                String string = "a) Enter the House\nb) Leave the House";
                char[] choice = {'a','b'};

                char option = v.getChoice(string, choice);
                if (option == 'a') {
                    current_room = 0;
                }

                else {
                    current_room = -1;
                    v.tell("Farewell.");
                }
            }

        }
        return d;
    }
}
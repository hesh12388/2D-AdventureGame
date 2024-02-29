package OOP.ec22518.MP.contributions;

/************************************
 * Winchester Mystery House: House_ec221247
 * By Bala Siva
 * Student ID: 220349336
 * Version 3: 24 March 2023
 * visitor is allowed to a room depending on their direction and location
 * ***********************************/
class House_ec221247 implements Visitable {

    final int NO_OF_ROOMS = 5;
    Room [] room = new Room[NO_OF_ROOMS];
    House_ec221247(){
        room[0] = new Room_ec22414();
        room[1] = new Room_ec221150(); // Cengiz
        room[2] = new Room_ec221247(); // me
        room[3] = new Room_ec22789(); // Newton
        room[4] = new Room_ec22413();
    }



    public Direction visit(Visitor v, Direction d) {
        v.tell("Welcome to Mystery House");

        // room array size is used to indicate visitor is in the garden
        // negative number means exit the house
       int roomNoIn = NO_OF_ROOMS;

        // house layout
        //                  414(0)
        //      1150(1)    1247(2)   789(3)
        //                  413(4)
        // wonder around the house till room number is negative.
        while(roomNoIn >= 0){
            if((roomNoIn == NO_OF_ROOMS && d == Direction.FROM_NORTH) || (roomNoIn == 2 && d == Direction.FROM_SOUTH)){
                roomNoIn = 0;
                d = room[roomNoIn].visit(v, d);
            }
            else if ((roomNoIn == NO_OF_ROOMS && d == Direction.FROM_WEST) || (roomNoIn == 2 && d == Direction.FROM_EAST)){
                roomNoIn = 1;
                d = room[roomNoIn].visit(v, d);
            }
            else if ((roomNoIn == NO_OF_ROOMS && d == Direction.FROM_EAST) || (roomNoIn == 2 && d == Direction.FROM_WEST)){
                roomNoIn = 3;
                d = room[roomNoIn].visit(v,d);
            }
            else if ((roomNoIn == NO_OF_ROOMS && d == Direction.FROM_SOUTH) || (roomNoIn == 2 && d == Direction.FROM_NORTH)){
                roomNoIn = 4;
                d = room[roomNoIn].visit(v, d);
            }
            else if((roomNoIn == 0 && d == Direction.FROM_NORTH) || (roomNoIn == 1 && d == Direction.FROM_WEST)
                        || (roomNoIn == 3 && d == Direction.FROM_EAST) || (roomNoIn == 4 && d == Direction.FROM_SOUTH)){
                roomNoIn = 2;
                d = room[roomNoIn].visit(v,d);
            }
            // visitor is in the garden
            else{
                roomNoIn = NO_OF_ROOMS;
                v.tell("You are in the garden.");
                char [] choice = {'N', 'S', 'E', 'W', 'O'};

                // select direction to go back to the house or leave the house
                String s = "If you like to go in the house choose the direction you want to go in or type O to exit the house \n" +
                            " N: North \t S: South \t E: East \t S: South \t O: Out of the house";
                char option = v.getChoice(s, choice);
                switch (option){
                    case 'N': d = Direction.FROM_NORTH; break;
                    case 'S': d = Direction.FROM_SOUTH; break;
                    case 'E': d = Direction.FROM_EAST; break;
                    case 'W': d = Direction.FROM_WEST; break;
                    default:
                        roomNoIn = -1;
                        v.tell("Thank you for visiting the House ");
                }
            }
        }
        return d;
    }

}


package OOP.ec22518.MP.contributions;

import java.util.Random;
class House_ec22431 implements Visitable {
    public Room[] rooms;
    public House_ec22431(){
        rooms = new Room[]{new Room_ec22431(),new Room_ec22923(),new Room_ec22578(),new Room_ec221022()};
    }
    public Direction visit(Visitor v, Direction d) {
        char[] options = {'1', '2'};
        int choice = 1;
        d = story(v);
        while (choice != 2) { //just in case someone's room doesn't lead to other rooms
            if (d == Direction.FROM_SOUTH) {
                d = rooms[0].visit(v, d);
                choice = v.getChoice("1. Enter Room\n2. Exit", options);
            } else if (d == Direction.FROM_WEST) {
                d = rooms[1].visit(v, d);
                choice = v.getChoice("1. Enter Room\n2. Exit", options);
            } else if (d == Direction.FROM_NORTH) {
                d = rooms[2].visit(v, d);
                choice = v.getChoice("1. Enter Room\n2. Exit", options);
            } else if (d == Direction.FROM_EAST) {
                d = rooms[3].visit(v, d);
                choice = v.getChoice("1. Enter Room\n2. Exit", options);
            }
        }
        return d;
    }
    public Direction randomRoom(Visitor v){
        int randomRoom;
        Random rand = new Random();
        randomRoom = rand.nextInt(4)+1;
        v.tell("All of a sudden you lose a sense of direction, you don't know where you came from and all sense of direction is lost");
        Direction d;
        if(randomRoom ==1){
            d = Direction.FROM_NORTH;
        }
        else if (randomRoom ==2){
            d = Direction.FROM_WEST;
        }
        else if(randomRoom ==3){
            d = Direction.FROM_EAST;
        }
        else{
            d = Direction.FROM_SOUTH;
        }
        return d;
    }
    public Direction story(Visitor v){
        Direction d = Direction.FROM_SOUTH;
        v.tell("You enter the house through the ancient door." +
                "\nYou are greeted with a glowing portal" +
                "\nAn empty fireplace with what looks to be a trap door inside" +
                "\nA spiraling, broken staircase leading down to what you presume to be the basement" +
                "\nA grand staircase leading upstairs" +
                "\nAnd lastly a door, solid and undamaged with age, seemingly out of place");
        char[] options = {'1', '2','3','4','5'};
        int choice = v.getChoice("Do you\n1. Walk through the portal\n2. Enter the trap door through the fireplace\n3. Go down the stairs\n4. Go up the stairs\n5. Walk through the door", options);
        switch (choice){
            case '1':
                d = randomRoom(v);
                break;
            case '2':
                break; 
            case '3':
                d = Direction.FROM_WEST;
                break;
            case '4':
                d = Direction.FROM_NORTH;
                break;
            case '5':
                d = Direction.FROM_EAST;
                break;
        }
        return d;
    }
}

//each class returns direction

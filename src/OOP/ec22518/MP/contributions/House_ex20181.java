package OOP.ec22518.MP.contributions;

class House_ex20181 implements Visitable {
    private boolean lobbyVisited = false;
    private boolean exit = false;
    
    private static Room room1 = new Room_ex21423();
    private static Room room2 = new Room_ex20181();
    private static Room room3 = new Room_ec22923();
    
    public Direction lobby(Visitor v)
    {
        char[] choices = {'a','b'};
        if (lobbyVisited) v.tell("You are back in the lobby"); 
        else 
        {
            v.tell("Welcome to the lobby. As a new visitor you get 5 gold");
            v.giveGold(5);
        }
        char choice = v.getChoice("Take the door to a) north, b) east or c) west", choices);
        if ( choice == 'a' ) return Direction.TO_NORTH;
        else if ( choice == 'b')  return Direction.TO_EAST;
        else return Direction.TO_WEST;
    }
    
    public Direction visit(Visitor v,Direction directionVistorArrivesFrom)
    {
        Direction going;
        Direction from = Direction.FROM_SOUTH;
        char[] yOrN = { 'y', 'n'};
        char choice;
        int location = 0;
        v.tell("The elevator finally reaches its destination and you enter the lobby. The elevator seals itself therefore you need to find another exit. Welcome to floor 409");
        while ( !exit ) 
        {
            switch ( location )
            {
                case 0:
                    going = lobby( v );
                    if (going == Direction.TO_NORTH) {location = 1; from = Direction.FROM_SOUTH;}
                    else if (going == Direction.TO_EAST) {location = 2; from = Direction.FROM_SOUTH;}
                    else {location = 4;from = Direction.FROM_SOUTH;}
                    lobbyVisited = true;
                    break;
                case 1:
                    going = room1.visit(v, from);
                    if (going == Direction.TO_NORTH) {location = 6; from = Direction.FROM_SOUTH;}
                    else if (going == Direction.TO_EAST) {location = 2; from = Direction.FROM_WEST;}
                    else if (going == Direction.TO_WEST){location = 4;from = Direction.FROM_EAST;}
                    else {location = 0; from= Direction.FROM_NORTH ;}
                    break;
                case 2:
                    going = room2.visit(v, from);
                    if (going == Direction.TO_NORTH) {location = 3; from = Direction.FROM_SOUTH;}
                    else if (going == Direction.TO_EAST) {location = 4; from = Direction.FROM_EAST;}
                    else if (going == Direction.TO_WEST){location = 1;from = Direction.FROM_EAST;}
                    else {location = 3; from= Direction.FROM_NORTH;}
                    break;
            }
            break;
        }
        return Direction.TO_NORTH;
    }
    
}

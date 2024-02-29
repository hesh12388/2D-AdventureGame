package OOP.ec22518.MP.contributions;

class House_ec22452 implements Visitable {
    public static Room room1;
    public static Room room2;
    public static Room room3;
        
    public Direction visit(Visitor v, Direction d_entered) {
        Room[] rooms = {};
        room1 = new Room_ec22452();
        room2 = new Room_ec22443();
        room3 = new Room_ec22422();
        
        rooms[0] = room1;
        rooms[1] = room2;
        rooms[2] = room3;
        
        boolean exit = false;
        while (!exit) {
            if (d_entered == Direction.FROM_NORTH) {
                d_entered = rooms[0].visit(v, d_entered);
                if (d_entered == Direction.TO_NORTH) {
                    v.tell("You have left the house");
                    exit = true;
                } else if (d_entered == Direction.TO_EAST) {
                    d_entered = Direction.FROM_EAST;
                    d_entered = rooms[1].visit(v, d_entered);
                } else if (d_entered == Direction.TO_WEST) {
                    v.tell("You have left the house");
                    exit = true;
                } else {
                    v.tell("You have left the house");
                    exit = true;
                }
            } else if (d_entered == Direction.FROM_EAST) {
                d_entered = rooms[1].visit(v, d_entered);
                if (d_entered == Direction.TO_NORTH) {
                    v.tell("You have left the house");
                    exit = true;
                } else if (d_entered == Direction.TO_EAST) {
                    d_entered = Direction.FROM_EAST;
                    d_entered = rooms[2].visit(v, d_entered);
                } else if (d_entered == Direction.TO_WEST)  {
                    d_entered = Direction.FROM_WEST;
                    d_entered = rooms[0].visit(v, d_entered);
                } else {
                    v.tell("You have left the house");
                    exit = true;
                }
                
            } else if (d_entered == Direction.FROM_WEST) {
                d_entered = rooms[2].visit(v, d_entered);
                if (d_entered == Direction.TO_NORTH) {
                    v.tell("You have left the house");
                    exit = true;
                } else if (d_entered == Direction.TO_EAST) {
                    v.tell("You have left the house");
                    exit = true;
                } else if (d_entered == Direction.TO_WEST) {
                    d_entered = Direction.FROM_EAST;
                    d_entered = rooms[1].visit(v, d_entered);
                } else {
                    v.tell("You have left the house");
                    exit = true;
                }
                
            } else {
                v.tell("You have left the house");
                exit = true;
            }
            
            
            
        }
        return d_entered;
    }
    

}
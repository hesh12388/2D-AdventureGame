package OOP.ec22518.MP.contributions;

class House_ec22754 implements Visitable {
    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    
    
    House_ec22754() {
    room1 = new Room_ec22754(); 
    room2 = new Room_ec221021(); 
    room3 = new Room_ec221006(); 
    room4 = new Room_ec22638();
    }
    
    public Direction visit(Visitor v, Direction d) {
        for(int i = 0; i<4; i++){
            if(d == Direction.FROM_SOUTH)
            {
                v.tell("Entering room one");
                room1.visit(v,d);
                d = Direction.TO_EAST;
            }  
            else if (d == Direction.FROM_NORTH)
            {
                v.tell("Entering room two");
                room2.visit(v,d);
                d = Direction.TO_WEST;
            }
            else if (d == Direction.FROM_EAST)
            {
                v.tell("Entering room three");
                room3.visit(v,d);
                d = Direction.TO_SOUTH;
            }
            else if (d == Direction.FROM_WEST)
            {
                v.tell("Entering room four");
                room4.visit(v,d);
                d = Direction.TO_WEST;
            }
        } //NEW
        return Direction.opposite(d);
    }
}

package OOP.ec22518.MP.contributions;

class House_ec22587 implements Visitable {
    private Room rooma;
    private Room roomb;
    private Room roomc;
    private Room roomd;
    
    
    House_ec22587() {
    rooma = new Room_ec22754(); 
    roomb = new Room_ec221021(); 
    roomc = new Room_ec221006(); 
    roomd = new Room_ec22638();
    }
    
    public Direction visit(Visitor v, Direction d) {
        for(int i = 0; i<4; i++){
            if(d == Direction.FROM_SOUTH)
            {
                v.tell("Entering room one");
                rooma.visit(v,d);
                d = Direction.TO_SOUTH;
            }  
            else if (d == Direction.FROM_NORTH)
            {
                v.tell("Entering room two");
                roomb.visit(v,d);
                d = Direction.TO_WEST;
            }
            else if (d == Direction.FROM_EAST)
            {
                v.tell("Entering room three");
                roomc.visit(v,d);
                d = Direction.TO_EAST;
            }
            else if (d == Direction.FROM_WEST)
            {
                v.tell("Entering room four");
                roomd.visit(v,d);
                d = Direction.TO_WEST;
            }
        } //NEW
        return Direction.opposite(d);//new
    }
}

package OOP.ec22518.MP.contributions;

class House_ec22975 implements Visitable {
    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;


    House_ec22975() {
        room1 = new Room_ec22804();
        room2 = new Room_ec221021();
        room3 = new Room_ec221006();
        room4 = new Room_ec22638();
    }

    public Direction visit(Visitor visitor, Direction direction) {
    for(int i = 0; i<4; i++){
     if(direction == Direction.FROM_NORTH)
            {
                visitor.tell("Entering room one");
                room1.visit(visitor,direction);
                direction = Direction.TO_EAST;
            }
       else if (direction == Direction.FROM_SOUTH)
            {
                visitor.tell("Entering room two");
                room2.visit(visitor,direction);
                direction = Direction.TO_WEST;
            }
       else if (direction == Direction.FROM_EAST)
            {
                visitor.tell("Entering room three");
                room3.visit(visitor,direction);
                direction = Direction.TO_NORTH;
            }
       else if (direction == Direction.FROM_WEST)
            {
                visitor.tell("Entering room four");
                room4.visit(visitor,direction);
                direction = Direction.TO_SOUTH;
            }
       }
       
        return Direction.opposite(direction);
    }
}

package OOP.ec22518.MP.contributions;

class House_ac12345 implements Visitable {
    Room [] ROOMS = {new Room_ec221028(), new Room_bt21057(), new Room_ec211030(), new Room_ec221025()};

    public Direction visit(Visitor v, Direction d) {
        boolean leave = false;
        while(!leave){
            if (d == Direction.FROM_SOUTH){
                Direction newDir = ROOMS[0].visit(v,d);
                v.tell("You have entered the south room");
            }else if (d == Direction.FROM_WEST) {
                Direction newDir = ROOMS[1].visit(v,d);
                v.tell("You have entered the west room");
            }else if (d == Direction.FROM_NORTH){
                Direction newDir = ROOMS[2].visit(v,d);
                v.tell("You have left the house");
                leave = true;
            }else if (d == Direction.FROM_EAST) {
                Direction newDir = ROOMS[3].visit(v,d);
                v.tell("You have entered the east room");
            }
        }
        return d;
    }
}

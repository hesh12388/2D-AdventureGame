package OOP.ec22518.MP.contributions;

/* Assigment 5 - House_ec22895.java */
class House_ec22895 implements Visitable {
    Room livingRoom;
    Room bedRoom;
    Room cinemaRoom;
    int gold;

    public House_ec22895() {
        this.livingRoom = new Room_ec22895();
        this.bedRoom = new Room_ec20258();
        this.cinemaRoom = new Room_ec211044();
        this.gold = 0;
    }
    
    public Direction visit(Visitor visitor, Direction origin) {
        boolean exitLoop = false;
        Direction currentDirection = livingRoom.visit(visitor, origin);
        while(!exitLoop) {
            visitor.tell("You started at the living room, now let's see where your journey takes you.");
            if(currentDirection == Direction.TO_EAST) {
                visitor.tell("You are now heading to the cinema room!");
                currentDirection = cinemaRoom.visit(visitor, currentDirection);
            }
            else if(currentDirection == Direction.TO_NORTH) {
                visitor.tell("Congrats! You managed to get to the bed room.");
                currentDirection = bedRoom.visit(visitor, currentDirection);
            }
            else if(currentDirection == Direction.TO_SOUTH) {
                visitor.tell("You have exited the house.");
                exitLoop = false;
            }
            else {
                currentDirection = livingRoom.visit(visitor, Direction.opposite(origin));
            }
        }
        return Direction.opposite(origin);
    }
}

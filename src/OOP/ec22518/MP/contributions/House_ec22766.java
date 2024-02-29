package OOP.ec22518.MP.contributions;

class House_ec22766 implements Visitable {
    static Room myRoom = new Room_ec22766();
    static Room friendRoom1 = new Room_ec22937();
    static Room friendRoom2 = new Room_ec22406();
    static Room[] allRooms = {myRoom, friendRoom1, friendRoom2};
    //Direction returningDirection=Direction.TO_NORTH;
    int allRoomsIndex=0;
    
    public static void main(String[] args)
    {
    }
    
    public Direction visit(Visitor newVisitor, Direction visitorDirection)
    {
        while(!(visitorDirection.equals(Direction.FROM_SOUTH))){
            if (visitorDirection.equals(Direction.FROM_NORTH))
            {
                visitorDirection=visitRoom(newVisitor, allRooms[0], visitorDirection);
            }
            else if (visitorDirection.equals(Direction.FROM_EAST))
            {
                visitorDirection=visitRoom(newVisitor, allRooms[1], visitorDirection);
            }
            else if (visitorDirection.equals(Direction.FROM_WEST))
            {
                visitorDirection=visitRoom(newVisitor, allRooms[2], visitorDirection);
            }
            
            //returningDirection=visitRoom(newVisitor, allRooms[allRoomsIndex], returningDirection);
            //allRoomsIndex=allRoomsIndex+1;
        }
        newVisitor.tell("You are now exiting my house, thank you for visiting.");
        return Direction.FROM_SOUTH;
    }
    
    public Direction visitRoom(Visitor visitor, Room room, Direction currentDirection)
    {
        visitor.tell("You are entering a new Room "+room.toString());
        return room.visit(visitor, currentDirection);
    }
}

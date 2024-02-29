package OOP.ec22518.MP.contributions;

class House_ec22630 implements Visitable {

    Room myRoom;
    Room binula;
    Room darien;

    public House_ec22630() 
    {
        myRoom = new Room_ec22630();
        binula = new Room_ec21645();
        darien = new Room_ec22814();
    }
    public Direction visit(Visitor v, Direction d) 
    {
        v.tell("You in da house ");
        Direction a = myRoom.visit(v, d);

        while(!a.equals(Direction.TO_NORTH))
        {
            if(a.equals(Direction.TO_EAST))
            {
                a = darien.visit(v, Direction.FROM_WEST);
            }

            else if(a.equals(Direction.TO_WEST))
            {
                a = binula.visit(v, Direction.FROM_EAST);
            }

            else if(a.equals(Direction.TO_SOUTH))
            {
                a = myRoom.visit(v, Direction.FROM_NORTH);
            } 
        }
        return Direction.TO_NORTH;
    }
}

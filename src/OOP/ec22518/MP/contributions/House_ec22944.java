package OOP.ec22518.MP.contributions;

class House_ec22944 implements Visitable {
    Room tmansRoom;
    Room zubairsRoom;
    Room mosRoom;


    House_ec22944() {
        tmansRoom = new Room_ec22944();
        zubairsRoom = new Room_ec22480();
        mosRoom = new Room_ec22479();
    }

    public Direction visit(Visitor v, Direction d)
    {
        Direction firstDirection = tmansRoom.visit(v, d);
        while(!firstDirection.equals(Direction.TO_SOUTH))
        {
            if(firstDirection.equals(Direction.TO_WEST))
            {
                firstDirection = zubairsRoom.visit(v, Direction.FROM_EAST);
            }

            if(firstDirection.equals(Direction.TO_WEST))
            {
                firstDirection = mosRoom.visit(v, Direction.FROM_WEST);
            }

            if(firstDirection.equals(Direction.TO_NORTH))
            {
                firstDirection = tmansRoom.visit(v, Direction.FROM_SOUTH);
            }
        }

        return Direction.TO_SOUTH;
    }
}
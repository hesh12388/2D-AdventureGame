package OOP.ec22518.MP.contributions;

class House_ec22914 implements Visitable {
    Room [][]rooms = new Room[3][3];

    public House_ec22914()
    {
        rooms[0][0] = Contributions.newRoomByUsername("ec22960");
        rooms[0][1] = Contributions.newRoomByUsername("ec221099");
        rooms[0][2] = Contributions.newRoomByUsername("ec22914");
        rooms[1][0] = Contributions.newRoomByUsername("ec22960");
        rooms[1][1] = Contributions.newRoomByUsername("ec221099");
        rooms[1][2] = Contributions.newRoomByUsername("ec22914");
        rooms[2][0] = Contributions.newRoomByUsername("ec22960");
        rooms[2][1] = Contributions.newRoomByUsername("ec221099");
        rooms[2][2] = Contributions.newRoomByUsername("ec22914");
    }

    public Direction visit(Visitor visitors, Direction directionVistorArrivesFrom)
    {
        boolean play = true;
        Direction direction = rooms[0][0].visit(visitors, directionVistorArrivesFrom);
        int [] index = {0,0};
        while(play == true)
        {
            index = index_direction(direction, index);
            if(index[0]<0 || index[0]>2 || index[1]<0 || index[1]>2)
            {
                play = false;
            }
            else
            {
                direction = rooms[index[0]][index[1]].visit(visitors, directionVistorArrivesFrom);
            }
        }
        return direction;
    }

    public int[] index_direction(Direction direction, int [] index)
    {
        if(direction.equals(Direction.TO_NORTH))
        {
            index[0]--;
        }
        else if(direction.equals(Direction.TO_SOUTH))
        {
            index[0]++;
        }
        else if(direction.equals(Direction.TO_EAST))
        {
            index[1]++;
        }
        else if(direction.equals(Direction.TO_WEST))
        {
            index[1]--;
        }

        return index;
    }
}
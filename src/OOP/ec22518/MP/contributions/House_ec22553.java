package OOP.ec22518.MP.contributions;

class House_ec22553 implements Visitable {
    private Room[][] all_rooms;
    House_ec22553(Visitor v, Direction d)
    {
        all_rooms[0][0] = new Room_ec22859(); //sep
        all_rooms[1][0] = new Room_ec22771(); //bas
        all_rooms[0][1] = new Room_ec22551(); //rk
        all_rooms[1][1] = new Room_ec22553(); //me
    }
    public Direction visit(Visitor v, Direction d)
    {
        int cord_x = 0;
        int cord_y = 0;
        Room current_room = all_rooms[cord_x][cord_y];

        Direction direction = current_room.visit(v,Direction.TO_NORTH);
        Boolean left = false;

        while(!left)
        {
            if(direction == Direction.TO_NORTH)
            {
                if(cord_y == 1 && cord_x == 1)
                {
                    v.tell("You left the house");
                    left = true;
                }
                else
                {
                    cord_y ++;
                }
            }

            else if(direction == Direction.TO_EAST)
            {
                if(cord_x == 1)
                {
                    v.tell("No room to east");
                }
                else
                {
                    cord_x ++;
                }
            }

            else if(direction == Direction.TO_SOUTH)
            {
                if(cord_y == 0)
                {
                    v.tell("No room to south");
                }
                else
                {
                    cord_y --;
                }
            }

            else if(direction == Direction.TO_WEST)
            {
                if(cord_x == 0)
                {
                    v.tell("No room to west");
                }
                else
                {
                    cord_x --;
                }
            }


            direction = all_rooms[cord_x][cord_y].visit(v,direction);
        }

        return Direction.opposite(direction);
    }
}

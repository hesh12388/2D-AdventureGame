package OOP.ec22518.MP.contributions;

class House_ec22838 implements Visitable {
    Room R1, R2, R3, R4;
    
    //    (N) (3)
    // (4) 1   2 (3)
    // (2) 3   4 (1)
    //    (2) (1)
    
    House_ec22838()
    {
        R1 = new Room_ec22838(); R2 = new Room_ec211030(); R3 = new Room_ec22838(); R4 = new Room_ec211044();
    }
    
    Room next_room(Room current_room, Visitor visitor, Direction direction_out)
    {
        if (current_room == R1)
        {
            if (direction_out == Direction.TO_NORTH)
            {
                visitor.tell("You have left the house.");
                return null;
            }
            else if (direction_out == Direction.TO_EAST) {return R2;}
            else if (direction_out == Direction.TO_SOUTH) {return R3;}
            else {return R4;}
        }
        else if (current_room == R2)
        {
            if (direction_out == Direction.TO_NORTH) {return R3;}
            else if (direction_out == Direction.TO_EAST) {return R3;}
            else if (direction_out == Direction.TO_SOUTH) {return R4;}
            else {return R1;}
        }
        else if (current_room == R3)
        {
            if (direction_out == Direction.TO_NORTH) {return R1;}
            else if (direction_out == Direction.TO_EAST) {return R4;}
            else if (direction_out == Direction.TO_SOUTH) {return R2;}
            else {return R2;}
        }
        else
        {
            if (direction_out == Direction.TO_NORTH) {return R2;}
            else if (direction_out == Direction.TO_EAST) {return R1;}
            else if (direction_out == Direction.TO_SOUTH) {return R1;}
            else {return R3;}
        }
    }
    
    public Direction visit(Visitor visitor, Direction direction)
    {
        Room current_room = R1;
        Direction current_direction = direction;
        
        while (current_room != null)
        {
            current_direction = current_room.visit(visitor, current_direction);
            current_room = next_room(current_room, visitor, current_direction);
        }
        
        return Direction.TO_NORTH;
    }
}

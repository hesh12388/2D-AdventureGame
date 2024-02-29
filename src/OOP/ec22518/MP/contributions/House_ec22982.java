package OOP.ec22518.MP.contributions;

class House_ec22982 implements Visitable {
    private Room [] rooms;
    private final int NUMBER_OF_ROOMS = 3;
    
    House_ec22982(Visitor visitor, Direction direction){
        rooms = new Room[NUMBER_OF_ROOMS];
        rooms[0] = new Room_ec22885();
        rooms[1] = new Room_ec22426();
        rooms[2] = new Room_ec22547();
    }
    
    public Direction visit(Visitor visitor, Direction direction){
        int index = 0;
        Room current = rooms[index];
        Direction d = current.visit(visitor, Direction.TO_EAST);
        boolean leave = false;
        
        while(!leave){
        if (direction == Direction.TO_NORTH){
            visitor.tell("You have left the house.");
        }
        
        else if (direction == Direction.TO_SOUTH){
            visitor.tell("There is no room in this direction.");
            leave = true;
        }
        
        else if (direction == Direction.TO_WEST){
            if (current == rooms[index]){
                visitor.tell("Unfortunately you cannot go in this direction.");
            }
            else{
                index = index-1;
                current = rooms[index];
            }
        }
        
        else if(direction == Direction.TO_EAST){
            if (current == rooms[2]){
                visitor.tell("You cannot go in this direction.");
            }
            else{
                index = index + 1;
                current = rooms[index];
            }
        }
        
        direction = current.visit(visitor, direction);
    }
        
         return Direction.opposite(direction);
        
    }
    
}

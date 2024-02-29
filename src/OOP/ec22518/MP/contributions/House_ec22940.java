package OOP.ec22518.MP.contributions;

public class House_ec22940 implements Visitable {
    Room room1;
    Room room2;
    Room room3;
    Room room4;
    
    House_ec22940(){
        room1 = new Room_ec22940();
        room2 = new Room_ec221002();
        room3 = new Room_ec22941();
        room4 = new Room_ec22943();

        
    }
    
    public Direction visit(Visitor visitor, Direction direction){
        
        boolean stop = false;
        
        Direction newDirection = room1.visit(visitor, direction);
        
        while(!stop){
            //if north leave the house
            if(direction == Direction.TO_NORTH){
                visitor.tell("You have entered the room of Light");
                visitor.tell("You are now going to leave the house. Goodbye!!");
                stop = true;
            }
            else if(direction == Direction.TO_SOUTH){
                visitor.tell("You have entered the room of Darkeness");
                newDirection = room2.visit(visitor, Direction.FROM_SOUTH);
            }
            else if(direction == Direction.TO_EAST){
                visitor.tell("You have entered the room of Shadows");
                newDirection = room3.visit(visitor, Direction.FROM_SOUTH);
            }
            else if(newDirection == Direction.TO_WEST){
                visitor.tell("You have entered the room of Hell");
                newDirection = room4.visit(visitor, Direction.FROM_SOUTH);
            }
        }
        
        
        
        return newDirection;
    }
}

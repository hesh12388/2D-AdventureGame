package OOP.ec22518.MP.contributions;

class House_ec22879 implements Visitable {

    Room livingroom;
    Room kitchen;
    Room bedroom;
    
    //Constructor.
    
    House_ec22879 (){
    
    livingroom = new Room_ec22748();
    kitchen = new Room_ec22825();
    bedroom = new Room_ec22879();
    
    }
    
    public Direction visit(Visitor visitor, Direction d) {
    
        boolean ex = false; 
        Direction direction = d;
        direction = livingroom.visit(visitor,d);
        Room current = livingroom;
        
        // while ex is false the code is running
        while (ex==false){
            
            if (current == livingroom){
            
             visitor.tell ("Welcome! Now you are in the living room");
                
                if (direction == Direction.TO_SOUTH) {
 					direction = bedroom.visit(visitor, d);
 					current = bedroom; // Now the current room is the bedroom
                    visitor.tell ("Let's go to the bedroom!");
 				}
 				else if (direction == Direction.TO_EAST) {
 					direction = kitchen.visit(visitor, d);
 					current = kitchen;
                    visitor.tell ("Let's go to the kitchen!");
 				}
 				else if (direction == Direction.TO_NORTH) {
 					visitor.tell ("You are leaving!");
 					ex = true;
 					return Direction.TO_NORTH;
 				}
            }
            
            else if (current == bedroom){
        
                if (direction == Direction.TO_NORTH) {
 				    current = livingroom;
                    visitor.tell ("Let's go to the living room!");
                    direction = livingroom.visit(visitor, d);
 				}
 				else if (direction == Direction.TO_EAST) {
 					direction = kitchen.visit(visitor, d);
 					current = kitchen;
                    visitor.tell ("Let's go to the kitchen!");
 				}
 				else if (direction == Direction.TO_SOUTH) {
 					visitor.tell ("There is a wall.");
 					ex = true;
 					return Direction.TO_SOUTH;
 				}
            }
             else if (current == kitchen){
        
                if (direction == Direction.TO_WEST){
 					current = livingroom;
                    visitor.tell ("Let's go to the livingroom!");
                    direction = livingroom.visit(visitor, d);
 				}
 				else if (direction == Direction.TO_SOUTH) {
 					direction = bedroom.visit(visitor, d);
 					current = bedroom;
                    visitor.tell ("Let's go to the bedroom!");
 				}
 				else if (direction == Direction.TO_EAST) {
 					 visitor.tell ("There is the garden, you can't access.");
 					ex = true;
 					return Direction.TO_EAST;
 				}
        }
        }
        
        return d; //hh
    }
}

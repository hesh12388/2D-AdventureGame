package OOP.ec22518.MP.contributions;

class House_ec22795 implements Visitable {

    Room room1;
    Room room2;
    Room room3;
    Room room4;
  
    public House_ec22795() {
        room1 = new Room_ec22466();
        room2 = new Room_ec221003();
        room3 = new Room_ec19389();
        room4 = new Room_ec211045();
    }

    public Direction visit(Visitor v, Direction d) {
        
        Direction finalDirection = null;
        
        v.tell("You have entered the house, which has 4 rooms.");
        v.tell("Now you are at the entrance of the House. Go towards the North to enter the first room.");
        v.tell("You must choose the correct direction to progress. If you choose the wrong choice, you will be stuck in the room.");
        
        if (d == Direction.TO_NORTH) {
            finalDirection = room1.visit(v, d);
            
            if (finalDirection == Direction.TO_SOUTH) {
                v.tell("You are back at the entrance.");
            } else if (finalDirection == Direction.TO_WEST) {
                v.tell("You are in Room 3.");
                finalDirection = room3.visit(v, Direction.TO_EAST);
                
                if (finalDirection == Direction.TO_WEST) {
                    v.tell("You are back in Room 1.");
                    finalDirection = room1.visit(v, Direction.TO_NORTH);
                }
            } else if (finalDirection == Direction.TO_EAST) {
                v.tell("You are in Room 2.");
                finalDirection = room2.visit(v, Direction.TO_WEST);
                
                if (finalDirection == Direction.TO_EAST) {
                    v.tell("You are back in Room 1.");
                    finalDirection = room1.visit(v, Direction.TO_NORTH);
                }
            }
        } else if (d == Direction.TO_SOUTH) {
            finalDirection = Direction.TO_SOUTH;
            v.tell("You left the house.");
        }
        
        return d;
    }
    
}

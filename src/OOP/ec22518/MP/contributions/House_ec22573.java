package OOP.ec22518.MP.contributions;

class House_ec22573 implements Visitable {
    
    //array of rooms within the house 
    Room[] rooms = {new Room_ec22573(), new Room_ec22583(), new Room_ec22579(), new Room_ec22760()};
    
    
    public Direction visit(Visitor v, Direction d) {
        char[] options = {'a', 'b'};
        int choice = v.getChoice("1. Enter Room\n2. Exit", options);
        
        //check if the rooms have been visited
        boolean visitedRoom1 = false;
        boolean visitedRoom2 = false;
        boolean visitedRoom3 = false;
        boolean visitedRoom4 = false;
        
        //until the user chooses to exit
        while (choice != 'b') {
            if (d == Direction.FROM_SOUTH) {
                d = rooms[0].visit(v, d);
                v.tell("You have to crawl through a tunnel to get to this room. Do you still wish to enter");
                choice = v.getChoice("1. Enter Room\n2. Exit", options);
                
                if(choice == 'a'){
                    visitedRoom1 = true;
                }
                
            } else if (d == Direction.FROM_WEST) {
                
                d = rooms[1].visit(v, d);
                v.tell("You find a hole in the ceiling and a ladder leading up towards it. Do you want to climb it?");
                choice = v.getChoice("1. Enter Room\n2. Exit", options);
                
                if(choice == 'a'){
                    visitedRoom2 = true;
                }
                
            } else if (d == Direction.FROM_NORTH) {
                
                d = rooms[2].visit(v, d);
                v.tell("The door has no door handle. Do you wish to enter - you will have to kick the door down? ");
                choice = v.getChoice("1. Enter Room\n2. Exit", options);
                
                if(choice == 'a'){
                    visitedRoom3 = true;
                }
                
            } else if (d == Direction.FROM_EAST) {
                
                d = rooms[3].visit(v, d);
                v.tell("You hear a strange noise coming from this room, it's intriguing but also quite eerie.Do you wish to enter?");
                choice = v.getChoice("1. Enter Room\n2. Exit", options);
                
                if(choice == 'a'){
                    visitedRoom4 = true;
                }
            }
        }
        v.tell("Thanks for visiting my house.");
        
        return d;
    }
}


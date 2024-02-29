package OOP.ec22518.MP.contributions;

class House_ec22962 implements Visitable {

    // Declare rooms
    Room_ec22962 egg = new Room_ec22962();
    Room_ec22990 max = new Room_ec22990();
    Room_ec22948 sasha = new Room_ec22948();
    Room_ec22883 arsen = new Room_ec22883();
    Room_ec221204 boh = new Room_ec221204();

    public static void main(String[] args) {
        House_ec22962 h = new House_ec22962();
        Visitor v = new IOVisitor(System.out,System.in);
        
        Direction d = h.visit(v, Direction.FROM_SOUTH);
    }

    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        boolean stay = true;
        while(stay) {
            // Visit Max's room and get the direction
            Direction visitorComesFrom = max.visit(visitor, directionVistorArrivesFrom);
            stay = transition(visitor, visitorComesFrom);
            if(!stay) { break; }
            
            visitorComesFrom = egg.visit(visitor, visitorComesFrom);
            stay = transition(visitor, visitorComesFrom);
            if(!stay) { break; }
            
            visitorComesFrom = sasha.visit(visitor, visitorComesFrom);
            stay = transition(visitor, visitorComesFrom);
            if(!stay) { break; }
    
            if(visitorComesFrom == Direction.FROM_SOUTH) {
                visitorComesFrom = arsen.visit(visitor, visitorComesFrom);
                stay = transition(visitor, visitorComesFrom);
                if(!stay) { break; }
                visitorComesFrom = sasha.visit(visitor, visitorComesFrom);
                stay = transition(visitor, visitorComesFrom);
                if(!stay) { break; }
            } else if(visitorComesFrom == Direction.FROM_NORTH) {
                visitorComesFrom = boh.visit(visitor, visitorComesFrom);
                stay = transition(visitor, visitorComesFrom);
                if(!stay) { break; }
                visitorComesFrom = sasha.visit(visitor, visitorComesFrom);
                stay = transition(visitor, visitorComesFrom);
                if(!stay) { break; }
            } else if(visitorComesFrom == Direction.FROM_EAST) {
                visitorComesFrom = egg.visit(visitor, visitorComesFrom);
                stay = transition(visitor, visitorComesFrom);
                if(!stay) { break; }
                visitorComesFrom = sasha.visit(visitor, visitorComesFrom);
                stay = transition(visitor, visitorComesFrom);
                if(!stay) { break; }
            } else if(visitorComesFrom == Direction.FROM_WEST) {
                return Direction.TO_EAST;
            }

        }

        return Direction.TO_EAST;
    }

    public static boolean transition(Visitor visitor, Direction directionUserIsGoingTo) {
        char[] userOptions = {'y', 'n'};
        
        if(directionUserIsGoingTo == Direction.TO_NORTH) {
            visitor.tell("\n\u001B[34mYou are in a hallway walking north.\u001B[0m\n");
        } else if(directionUserIsGoingTo == Direction.TO_SOUTH) {
            visitor.tell("\n\u001B[34mYou are in a hallway walking south.\u001B[0m\n");
        } else if(directionUserIsGoingTo == Direction.TO_EAST) {
            visitor.tell("\n\u001B[34mYou are in a hallway walking east.\u001B[0m\n");
        } else if(directionUserIsGoingTo == Direction.TO_WEST) {
            visitor.tell("\n\u001B[34mYou are in a hallway walking west.\u001B[0m\n");
        }
        
        char userResponse = visitor.getChoice("\n\u001B[33mWould you like to leave the house? [y/n]\u001B[0m", userOptions);
        if(userResponse == 'y') { 
            visitor.tell("\n\u001B[34mThank you for visiting my humble home. Goodbye :)\u001B[0m");
            return false; 
        }

        return true;
    }
}

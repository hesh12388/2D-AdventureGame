package OOP.ec22518.MP.contributions;

class House_ec22652 implements Visitable {
    
    Room roomOne, roomTwo, roomThree;
    
    House_ec22652() {
        this.roomOne = new Room_ec22652();
        this.roomTwo = new Room_ec22946();
        this.roomThree = new Room_ec22932();
    }
    
    // | roomTwo | roomThree |
    // | roomOne |
    
    public Direction visit(Visitor visitor, Direction directionFrom) {
        Direction exitDirection = roomOne.visit(visitor, directionFrom);
        
        if (exitDirection == Direction.TO_NORTH) {
            exitDirection = roomTwo.visit(visitor, exitDirection);
            if (exitDirection == Direction.TO_SOUTH) {
                visitor.tell("The door is jammed.");
                char option = visitor.getChoice("Pick a direction, N, E or W.", new char[]{'N', 'E', 'W'});
                exitDirection = pickDirection(option);
            }
        }
        else if (exitDirection == Direction.TO_WEST) {
            exitDirection = roomThree.visit(visitor, exitDirection);
            if (exitDirection == Direction.TO_EAST) {
                visitor.tell("The door is jammed.");
                char option = visitor.getChoice("Pick a direction, N, S, E or W.", new char[]{'N', 'S', 'E', 'W'});
                exitDirection = pickDirection(option);
            }
        }
        
        return exitDirection;
    }
    
    Direction pickDirection(char option) {
        Direction directionPicked = Direction.TO_NORTH;
        
        if (option == 'N') {
            directionPicked = Direction.TO_NORTH;
        }
        else if (option == 'S') {
            directionPicked = Direction.TO_SOUTH;
        }
        else if (option == 'E') {
            directionPicked = Direction.TO_EAST;
        }
        else if (option == 'W') {
            directionPicked = Direction.TO_WEST;
        }
        
        return directionPicked;
    }
}

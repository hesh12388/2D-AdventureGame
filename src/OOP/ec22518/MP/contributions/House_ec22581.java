package OOP.ec22518.MP.contributions;

class House_ec22581 implements Visitable {
    // Make an array for room object and one for the equivalent directions
    Direction[] compassArray = new Direction[4];
    Room[] roomArray = new Room[4];
    
    // Populate roomArray and compassArray
    public House_ec22581()
    {
        roomArray[0] = new Room_ec221006();
        compassArray[0] = Direction.TO_SOUTH;
        roomArray[1] = new Room_ec22581();
        compassArray[1] = Direction.TO_NORTH;
        roomArray[2] = new Room_ec22548();
        compassArray[2] = Direction.TO_EAST;
        roomArray[3] = new Room_ec22422();
        compassArray[3] = Direction.TO_WEST;
    }
    
    public Direction visit(Visitor visitor, Direction from) // Visit method
    {
        // Initialise and declare room-flow variables
        boolean exiting = false;
        int roomInd = 0;
        Direction nextDir;
        
        // Loop room visitation code while the goal isn't to exit
        while (!exiting)
        {
            nextDir = roomArray[roomInd].visit(visitor, from); // Run visit method of next room
            
            if (roomInd == 0) // Can only go to other rooms from SOUTH room
            {
                // Loop through directions and find the corresponding room
                for (int i = 0; i < 4; i++)
                {
                    if (nextDir == compassArray[i]) // If statement to check if desired direction is current place in array
                    {
                        if (nextDir == Direction.TO_SOUTH) // Detect if trying to go SOUTH from south room and exit if so
                        {
                            exiting = true;
                        }
                        roomInd = i;
                        break;
                    }
                }
            }
            else // Return to SOUTH from all other rooms
            {
                visitor.tell("Returned to the main room. Go South to exit or explore!!");
                roomInd = 0; // Reset returns form all rooms to main SOUTH room
            }
        }
        
        // Tell message on exit then return opposite direction
        visitor.tell("You exited the house. Bye!!!");
        return Direction.opposite(from);
    }
}
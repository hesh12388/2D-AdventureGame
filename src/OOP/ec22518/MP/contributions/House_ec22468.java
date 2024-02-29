package OOP.ec22518.MP.contributions;/* Thanks to the following people for their rooms:
 * ec22696
 * ec22678
 * ec22764 */

/* HOUSE PLAN (^North)
                                 
   &##############################  ^ #######################################( 
   &                         *      N       *                                (  
   &                         *              *                                (  
   &                         *              *                                (  
   &        1                <W              E>                  2           (  
   &                         *              *                                (  
   &                         *       S      *                                (  
   @(((((((((((((((((((((((((%       v      *********************************(  
   &                         /              /                                (   
   &                         /              /                                (  
   &                         /              /                                (  
   &                         /              /                                (  
   &                         /              /                                (  
   &                         /              /                                (  
   &          3                                                              (  
   &                         <--W         --> E                              (  
   &                         /              /                       4        (  
   &                         /              /                                (  
   &                         /              /                                (  
   &                         /      S       /                                (  
   &                         /      |       /                                (  
   &                         /      |       /                                (  
   &                         /      v       /                                (  
   &#############################ENTRANCE####################################( 


 */

class House_ec22468 implements Visitable {
    // Instance variables
    Room[] rooms;

    /* Indicates the user's position in the house.
    * -1 is entrance
     * 0-3 are rooms
     4 is garden
     5 is outside the house, i.e. exited through entrance.*/
    int location;

    // Constructor
    public House_ec22468() {
        rooms = new Room[] {new Room_ec22468(), new Room_ec22764(), new Room_ec22678(), new Room_ec22468()};
        location = -1;
    }

    // Methods
    public Direction visit(Visitor v, Direction d) {
        
        v.tell("You have entered ec22468's house.\nYou find yourself in the hallway.\n");
        
        char c; // Stores user input.
        char[] options = new char[] {'n', 's','e','w'};
        String optionMessage = "Would you like to go:\n"
        + "n: North\n"
        + "s: South\n"
        + "e: East\n"
        + "w: West\n";

        // Main loop
        while (location!=5){
            v.tell(optionMessage);
            c = v.getChoice("", null);

            switch (location) {
                case -1: // Entrance
                    switch (c) {
                        
                        case 'w': // Go west to room 3
                            location = 2;
                            rooms[location].visit(v, Direction.FROM_WEST);
                            break;
                        
                        case 'e': // Go east to room 4
                            location = 4;
                            rooms[location].visit(v, Direction.FROM_EAST);
                            break;
                        
                        case 's': // Leave house
                            location = 5;
                            v.tell("You took one step inside the house and ran away.");
                            break;
    
                        default: // Invalid
                            v.tell("You walked into a wall.");
                            break;
                    }
                    break;
                
                case 0: // Room 1

                    switch (c) {
                        case 'n': // Go north to room 2
                            location = 1;
                            rooms[location].visit(v, Direction.FROM_SOUTH);
                            break;
                        
                        case 'e': // Go south to room 3
                            location = 3;
                            rooms[location].visit(v, Direction.FROM_WEST);
                            break;

                        default: // Invalid
                            v.tell("You walked into a wall.");
                            break;
                    }
                    break;
                
                
                case 1: // Room 2
                        switch (c) {
                            case 'n': // Go north to room 1
                                location = 1;
                                rooms[location].visit(v, Direction.FROM_SOUTH);
                                break;
                            
                            case 'w': // Go east to room 4
                                location = 4;
                                rooms[location].visit(v, Direction.FROM_EAST);
                                break;
    
                            default: // Invalid
                                v.tell("You walked into a wall.");
                                break;
                        }
                        break;

                case 2: // Room 3
                        switch (c) {
                            case 'n': // Go north to room 4
                                location = 4;
                                rooms[location].visit(v, Direction.FROM_SOUTH);
                                break;
                            
                            case 'w': // Go west to room 1
                                location = 1;
                                rooms[location].visit(v, Direction.FROM_WEST);
                                break;
                            
                            case 'e': // Go east to exit
                                location = 5;
                                v.tell("You have exited the house.");
                                break;
    
                            default: // Invalid
                                v.tell("You walked into a wall.");
                                break;
                        }
                        break;

                case 3: // Room 4
                        switch (c) {
                            case 'n': // Go north to room 3
                                location = 3;
                                rooms[location].visit(v, Direction.FROM_SOUTH);
                                break;
                            
                            case 'w': // Go west to exit
                                location = 5;
                                v.tell("You have exited the house.");
                                break;
    
                            default: // Invalid
                                v.tell("You walked into a wall.");
                                break;
                        }
                        break;

                default: // Invalid
                    v.tell(optionMessage);
                    break;
            }
        }
        
        return d;
    }
}

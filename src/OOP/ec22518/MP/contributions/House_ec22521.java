package OOP.ec22518.MP.contributions;

public class House_ec22521 implements Visitable {
    private Room[] rooms;
    private int currentRoom;
    
    // Constructor for creating House_ec22521 objects
    public House_ec22521() {
        // Create instances of Room objects and assign them to the room variables
        rooms = new Room[3];
        rooms[0] = new Room_ec22881(); // Create a Room_ec22720 object
        rooms[1] = new Room_ec22897(); // Create a Room_ec22897 object
        rooms[2] = new Room_ec22563(); // Create a Room_ec221022 object
        currentRoom = 0; // Start in the first room
    }
    
    // The visit method for a House_ec22521 object
    public Direction visit(Visitor visitor, Direction direction) {
        // Greet the visitor
        visitor.tell("You are now in Arafats house, welcome. ");
        
        // Ask if the visitor wants to leave the house
        char[] choices = {'y', 'n'};
        char input = visitor.getChoice("do you want to leave the house? y,n", choices);
        if (input==('y')){
            // If the visitor chooses to leave, say goodbye and end the visit
            visitor.tell("goodbye");
            return direction;
        }
        
        // Start by visiting the first room
        Direction nextDirection = rooms[currentRoom].visit(visitor, direction);
        
        // Keep looping until the visitor decides to leave
        while (nextDirection != null){
            // Tell the visitor they're entering a new room
            visitor.tell("youre entering a new room");
            
            // Move to the next room based on the direction chosen by the visitor
            if (currentRoom == 0 && nextDirection == Direction.TO_NORTH){
                currentRoom = 1;
            }
            else if (currentRoom == 1 && nextDirection == Direction.TO_NORTH){
                currentRoom = 2;
            }
            else if (currentRoom == 2 && nextDirection == Direction.TO_SOUTH){
                currentRoom = 1;
            }
            else if (currentRoom == 1 && nextDirection == Direction.TO_SOUTH){
                currentRoom = 0;
            }
            else {
                // If the visitor chooses an invalid direction, say they're lost and keep them in the same room
                visitor.tell("you're lost");
                nextDirection = rooms[currentRoom].visit(visitor, direction); 
                continue;
            }
            
            // Tell the visitor they've entered a new room
            visitor.tell("you've entered a new room");
            
            // Ask if the visitor wants to leave the house
            input = visitor.getChoice("do you want to leave the house? y,n", choices);
            
            if (input==('y')){
                // If the visitor chooses to leave, say goodbye and end the visit
                visitor.tell("goodbye");
                return direction;
            }
            
            // Visit the next room based on the direction chosen by the visitor
            nextDirection = rooms[currentRoom].visit(visitor, nextDirection);
        }
        
        // Say goodbye when the visitor has finished visiting all the rooms
        visitor.tell("bye bye");
        return direction;
    }
}

package OOP.ec22518.MP.contributions;

class House_ec22740 {
    //array which will contain the rooms
    private Room rooms[];
    
    //constructor which adds the rooms to the array
    public House_ec22740(Visitor visitor, Direction currentDirection){
        rooms = new Room[4];
        rooms[0]= new Room_ec22740();
        rooms[1]= new Room_ec22426();
        rooms[2]= new Room_ec22982();
        rooms[3]= new Room_ec22885();
    }
    
    //when the visitor enters the house
    public Direction visit(Visitor visitor, Direction currentDirection){
        visitor.tell("You are standing in front of a house, the door is open.");
        char[] option = {'a','b'};
        char choice = visitor.getChoice("Do you want to a) go inside or b) walk away from the house",option);
        
        if (choice == 'b'){
            visitor.tell("You are walking away from the hosue.");
            return Direction.opposite(currentDirection);
        }   
        
        int index = 0;
        Room currentRoom = rooms[index];
        currentDirection = currentRoom.visit(visitor, Direction.TO_WEST);
        visitor.tell("You have entered the house.");
        
        boolean leave = false;
        while(!leave){
            if (currentDirection == Direction.TO_NORTH){
                visitor.tell("You have left the house.");
            }

            else if (currentDirection == Direction.TO_SOUTH){
                visitor.tell("You have awakened the ghost living in this room.");
                visitor.tell("You have been taken golds by the ghost.");
                visitor.takeGold(5);
                leave = true;
            }

            else if (currentDirection == Direction.TO_EAST){
                if (currentRoom == rooms[index]){
                    visitor.tell("There's no room in this direction.");
                }
                else{
                    index = index-1;
                    currentRoom = rooms[index];
                }
            }

            else if(currentDirection == Direction.TO_WEST){
                if (currentRoom == rooms[0]){
                    visitor.tell("You cannot go in this direction.");
                }
                else{
                    index = index + 1;
                    currentRoom = rooms[index];
                }

            currentDirection = currentRoom.visit(visitor, currentDirection);
            }
        }
        
        return Direction.opposite(currentDirection);
    }
}

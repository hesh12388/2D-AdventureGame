package OOP.ec22518.MP.contributions;

//The class which is a subclass of house
class House_ec22426 implements Visitable {
    //sees if the chest is open
    Boolean chestOpened = false;
    //array which will contain the rooms
    private Room rooms[];
    
    //constructor which adds the rooms to the array
    public House_ec22426(Visitor newVisitor, Direction currentDirection){
        rooms = new Room[4];
        rooms[0]= new Room_ec22426();
        rooms[1]= new Room_ec22547();
        rooms[2]= new Room_ec22982();
        rooms[3]= new Room_ec22885();
    }
    
    //when the visitor enters the house
    public Direction visit(Visitor newVisitor, Direction currentDirection){
        
        chestOpened = false;
        
        //user can decide if they want to enter the house
        newVisitor.tell("You are standing outside a large house with an open door...");
        char[] option = {'a','b'};
        char choice = newVisitor.getChoice("Do you want to a) go inside or b) walk away from the house",option);
        
        if (choice == 'b'){
            return Direction.opposite(currentDirection);
        }
        
        //User can decide to leave house, turn on lights or enter rooms
        newVisitor.tell("You enter a dark hallway and you can see a room at the end.");
        choice = newVisitor.getChoice("Do you want to turn on the lights a)yes or b)no",option);
        
        if (choice == 'a'){
            newVisitor.tell("You turn on the light and see some gold on the floor.");    
            newVisitor.giveGold(5);
        }
        
        int index = 0;
        Room currentRoom = rooms[index];
        currentDirection = currentRoom.visit(newVisitor, Direction.TO_EAST);
        boolean finished = false;

        newVisitor.tell("You have entered a house.");
        //keeps repeating until finished is done
        while(!finished){
        if (currentDirection == Direction.TO_NORTH){
            //allows the user to keep exploring the rooms
            newVisitor.tell("You have left the house.");
            choice = newVisitor.getChoice("Do you want to a) leave or b) go back inside the house",option);
        
            if (choice == 'a'){
                finished = true;
            }
        }

        else if (currentDirection == Direction.TO_SOUTH){
            //allows the user to interact with a chest
            newVisitor.tell("You walk into a deadend.");
            newVisitor.tell("You turn around and see a chest on the floor.");
            if (chestOpened == false){
                choice = newVisitor.getChoice("Do you want to a) leave it or b) open the chest",option);

                if (choice == 'b'){
                    chestOpened = true;
                    newVisitor.tell("You open the chest inside is a book.");                
                }
                else{
                    newVisitor.tell("You walk past the chest.");
                }
            }
            else{
                newVisitor.tell("The chest is already open.");
            }
        
        }

        else if (currentDirection == Direction.TO_WEST){
            if (currentRoom == rooms[index]){
                newVisitor.tell("You find 5 gold.");
                newVisitor.giveGold(5);
            }
            else{
                index = index-1;
                currentRoom = rooms[index];
            }
        }

        else if(currentDirection == Direction.TO_EAST){
            if (currentRoom == rooms[3]){
                //user makes choice
                newVisitor.tell("You are not allowed to go in this direction.");
                choice = newVisitor.getChoice("Do you want to a) continue or b) turn around",option);

                if (choice == 'a'){
                    newVisitor.tell("You continue walking and you are attacked by a dog."); 
                    newVisitor.tell("Gold is take from you.");
                    int  take = newVisitor.takeGold(9);
                }
                else{
                    newVisitor.tell("You turn around.");
                }
                
            }
            else{
                index = index + 1;
                currentRoom = rooms[index];
            }
        }
     //while is ended
    currentDirection = currentRoom.visit(newVisitor, currentDirection);
    }

      return Direction.opposite(currentDirection);
    }
}

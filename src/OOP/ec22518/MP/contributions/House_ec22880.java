package OOP.ec22518.MP.contributions;

class House_ec22880 implements Visitable {
    private Room room1;
    private Room room2;
    private Room room3;

    House_ec22880() {
        room1 = new Room_ec22880(); 
        room2 = new Room_ec22413();   
        room3 = new Room_ec221014();  
    }

    public Direction visit(Visitor v, Direction d) {
        char [] options = {'N', 'S', 'E', 'W'};
        char [] A_OR_B = {'A','B'};
        
        v.tell("You are standing in front of a dark, eerie mansion. What would you like to do?");
         
        char WhereToGo = v.getChoice("A)Enter the mansion B) roam around outside", A_OR_B );
        
        if (WhereToGo == 'A'){
            d = optionA(v,d,options);
        }
        if (WhereToGo == 'B'){
            d = optionB(v,d,options,A_OR_B);
        }
        return d;
    }
    
    public Direction optionB(Visitor v, Direction d, char [] options, char [] A_OR_B){
        v.tell("Where would you like to go?");
        
        char choice = v.getChoice("North (N), South (S), East (E) or West (W)",options);
        
        if (choice == 'N'){
            v.tell("Well, looks like you're inside the house...");
            v.tell("Good luck with that.");
            d = optionA(v,d,options);
        }
        
        if (choice == 'S'){
            v.tell("You have entered a forest. Would you like to enter?");
            char ForestChoice = v.getChoice("A) walk around B) Go back to the enterence.",A_OR_B);
            
            if (ForestChoice == 'A'){
                d = Direction.TO_SOUTH;
                v.tell("Well you seem to be lost...");
                v.tell("You have no idea where you are going.");
                v.tell("You should trace your steps back");
                d = Direction.TO_NORTH;
            }
            
            if (ForestChoice == 'B'){
                v.tell("You're still in the same place...");
            }
        }
        
        if(choice == 'E'){
            d = Direction.TO_EAST;
            v.tell("You've entered the garden.");
            v.tell("There are many bushes and a pool");
            v.tell("What would you like to do?");
            char G_Choice = v.getChoice("A) take a closer look at the bushes B) jump in the pool",A_OR_B);
            
            if (G_Choice == 'A'){
                v.tell("You found a dead body...");
                char G2_Choice = v.getChoice("Do you want to? A)Continue to the pool or B) leave",A_OR_B);
                d = PoolChoice(v,d,G2_Choice,A_OR_B);
            }
        }
        if (choice == 'W'){
            v.tell("You have entered a forest. Would you like to enter?");
            char ForestChoice = v.getChoice("A) walk around B) Go back to the enterence.",A_OR_B);
            
            if (ForestChoice == 'A'){
                d = Direction.TO_SOUTH;
                v.tell("Well you seem to be lost...");
                v.tell("You have no idea where you are going.");
                v.tell("You should trace your steps back");
                d = Direction.TO_NORTH;
            }
            
            if (ForestChoice == 'B'){
                v.tell("You're still in the same place...");
            }
        }
        return d;
    }
    
    public Direction PoolChoice (Visitor v, Direction d, char G2_choice, char [] A_OR_B){
        d = Direction.TO_EAST;
        v.tell("You're at the pool. What would you like to do ?");
        char Pool_Choice = v.getChoice("A) jump in the pool B) leave",A_OR_B);
        if (Pool_Choice == 'A'){
            v.tell("You're at all wet. You should get out and leave. Go home.");
            d = Direction.TO_WEST;
        }
        if (Pool_Choice == 'B'){
            d = Direction.TO_WEST;
        }
        return d;
    }
    
    
    public Direction optionA(Visitor v, Direction d, char [] options){
        v.tell("You are standing in a dark, narrow corridor."); 
        v.tell("There appears to be a room in front of you.");  
        v.tell("There is also a room to your right and to left."); 
        v.tell("What would you like to do?"); 
        v.tell("A) Go to the room in front(N).");
        v.tell("B) Go to the room on the left(W).");
        v.tell("C) Go to the room on the right(E).");
        v.tell("D) Leave(S)");
        
         
        char choice = v.getChoice("So... Which one would it be?", options);

        while (choice != 'S') {
            if (choice == 'N') {
                d = room1.visit(v, d);
            }
            if (choice == 'W') {
                d = room2.visit(v, d);
            }
            if (choice == 'E') {
                d = room3.visit(v, d);
            }
            else {
                choice = v.getChoice("Try again", options);
            }
            choice = v.getChoice("Which room will be next...", options);
        }
        return d;
    }
        
}
        
      

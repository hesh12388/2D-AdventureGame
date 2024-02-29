package OOP.ec22518.MP.contributions;

class House_ec22965 implements Visitable {
    Room RWest;
    Room REast;
    Room RNorth;
    Room RSouth;
    
    House_ec22965(){
        RWest = new Room_ec22965();
        REast = new Room_ec22614();
        RNorth = new Room_ec22917();
        RSouth = new Room_ec22909();
        
    }
    
    public Direction visit(Visitor visitor, Direction direction) {
        boolean enter = true;
        visitor.tell("welcome to my house, enter the hallway");
        while (enter){
            visitor.tell("you're in the hallway, would you like to go north, south, east, west or exit.");
            char[] options = {'N', 'E','S','W','X'};
            char option = visitor.getChoice("enter N, S, E, W, or X (exit)", options);
            
            
            if (option == 'N'){
                direction = RNorth.visit(visitor,direction);
                
            }
            
            else if (option == 'W'){
                direction = RWest.visit(visitor,direction);
            }
            
            else if (option == 'S'){
                direction = RSouth.visit(visitor,direction);
            }
            else if (option == 'E'){
                direction = REast.visit(visitor,direction);
            }
            
            else if (option == 'X'){
                visitor.tell("bye bye");
                enter = false;
            }
        }
        return direction;
    }
}

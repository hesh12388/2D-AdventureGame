package OOP.ec22518.MP.contributions;

class House_ec22828 implements Visitable {
    public Direction visit(Visitor v, Direction d) {
        
    boolean holdsT = false;
    boolean choiceBeenMade = false;
    boolean exit = false;
    
        
    Room_ec22860 A = new Room_ec22860();
    Room_ec22660 B = new Room_ec22660();
    Room_ec22828 C = new Room_ec22828();
        
    Item lighter = new Item("lighter");
    boolean[] entered = new boolean[3];
    entered[0] = false;
    entered[1] = false;
    entered[2] = false;
        
        
        
        //-------------------------------------------------
        
    v.tell("You enter House, there is a lighter which may or may not be useful, do you wish to take it?"); 
    holdsT = v.giveItem(lighter);
                
        //--------------------------------------------------
        
        v.tell("You see 3 doors marked A, B and C, the last is marked with a red slash of paint by the handle."); 
        
        char[] options = {'y', 'n'};
        char[] options2 = {'A', 'B', 'C', 'D'};
        char choice2 = v.getChoice("you must make a choice, To leave choose D.", options2);
        char choice;
        
        
        while (!exit){
        while (!choiceBeenMade){
            
            if(choice2 == 'A' | choice2 == 'B' | choice2 =='C' | choice2 == 'D'){
            choiceBeenMade = true;}
            else {
            choice2 = v.getChoice("Make a choice coward...", options2);
            }
        }
            
            if (choice2 == 'A' & entered[0] | choice2 == 'B' & entered[1] | choice2 == 'C' & entered[2]){
            choice = v.getChoice("You have already entered this room, Do you wish to enter again? (y/n)", options);
            if (choice == 'n'){choice2 = 'z';}
            }
           

            
            
        if (choice2 == 'A' ){
        entered[0] = true; 
        d = A.visit(v, Direction.FROM_EAST);
        d = Direction.FROM_WEST;
        }    
        else if (choice2 == 'B'){
        entered[1] = true;
        d = B.visit(v, Direction.FROM_NORTH);
        d = Direction.FROM_SOUTH;
        }   
        else if (choice2 == 'C'){
        entered[2] = true;
        d = C.visit(v, Direction.FROM_WEST);
        }
        else if (choice2 == 'D'){
        d = Direction.FROM_NORTH;
        exit = true;
        }
            
            choiceBeenMade = false;
              
            if (choice2 == 'z'){ choice2 = v.getChoice("Try picking a new room this time; A, B, C or D?", options2);}
            else{
                if(!exit){
            choice2 = v.getChoice("you have arrived back to lobby, Choose A, B, C or D?", options2);
                }
            }
        
    }
        //---------------------------------------------------     
        
        
        
        
        
        return d;
    }
}

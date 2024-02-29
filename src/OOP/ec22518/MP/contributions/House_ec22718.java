package OOP.ec22518.MP.contributions;

import java.util.*;

// building a house :)
//
class House_ec22718 implements Visitable {
    Room r1;
    Room r2;
    Room r3;
    Room r4;
    
    // for adding new rooms to the house
    //
    House_ec22718(){
        r1 = new Room_ec22718();
        r2 = new Room_ec22409();
        r3 = new Room_ec221247();
        r4 = new Room_ec22546();
    }

    
    // for inputs
    //
    public static String input(Visitor v,String text){
        Scanner sc = new Scanner(System.in);
        v.tell(text);
        return sc.nextLine();
    }
    
    // validates input (also returns if the user wants to leave or stay)
    //
    public String checkToLeave(Visitor v){
        String check = input(v,"Would you like to (V) visit room 1 again or (L) leave the house?");

        if (check.equals("V") || check.equals("L")){
                return check;
            }else{
                checkToLeave(v);
                return "error";
            }
    }

    // for exiting the house
    //
    public boolean leave(Room r, Visitor v, Direction d){
        if (d== Direction.TO_EAST){
            v.tell("You walk down the EAST corridor");
            v.tell("The floor is a bit slipery so be careful");
            
        }else{
            v.tell("You walk down the SOUTH corridor");
        }
        
        String input = checkToLeave(v);
        
        if (input.equals("V")){
                return false;
        }else{
            v.tell("Thank you for coming to my house :)");
            v.tell("Please visit again soon!");
            return true;
        }
    }
    

    // geography of the house 
    // where you go if you come from a certain direction form a specific room
    //
    public Direction fromDirection(Visitor v, Room r,Direction d){
        Direction newD = r.visit(v,d);
        
        // room 1
        if (r == r1){
            if (newD == Direction.TO_NORTH){
                v.tell("Leaving NORTH");
                v.tell("You walk past the fountain and go to Room 4");
                fromDirection(v,r4, newD);
            } else if (newD == Direction.TO_EAST){
                v.tell("Leaving EAST");
                v.tell("You walk to Room 3");
                fromDirection(v,r3, newD);
            }else if (newD == Direction.TO_SOUTH){
                v.tell("Leaving SOUTH");
                boolean exit =leave(r,v,newD);
                
                if (exit){
                    return Direction.TO_SOUTH;
                }else{
                    v.tell("You walk back to the first room");
                   fromDirection(v,r1, newD); 
                }
            }else {
                v.tell("Leaving WEST");
                fromDirection(v,r1, newD);
            }
        }
        
        // room 2
        else if (r == r2){
            
            if (newD == Direction.TO_NORTH){
                v.tell("Leaving NORTH");
                v.tell("You walk to Room 4");
                fromDirection(v,r4, newD);
                
            } else if (newD == Direction.TO_EAST){
                v.tell("Leaving EAST");
                v.tell("You walk past the fountain and go to Room 3");
                v.tell("Don't get too close or it will spray on you!");
                fromDirection(v,r3, newD);
                
            }else if (newD == Direction.TO_SOUTH){
                v.tell("Leaving SOUTH");
                v.tell("You walk to Room 1");
                fromDirection(v,r1, newD);

            }else {
                v.tell("Leaving WEST");
                boolean exit =leave(r,v,newD);
                
                if (exit){
                    return Direction.TO_SOUTH;
                }else{
                   v.tell("You walk back to the first room");
                   fromDirection(v,r1, newD); 
                }
            }

        // room 3        
        }else if(r == r3){
            if (newD == Direction.TO_NORTH){
                v.tell("Leaving NORTH");
                v.tell("You walk to Room 4");
                fromDirection(v,r4, newD);
                
            } else if (newD == Direction.TO_EAST){
                v.tell("Leaving EAST");
                
                boolean exit =leave(r,v,newD);
                
                if (exit){
                    return Direction.TO_SOUTH;
                }else{
                   v.tell("You walk back to the first room");
                   fromDirection(v,r1, newD); 
                }
                  
            }else if (newD == Direction.TO_SOUTH){
                v.tell("Leaving SOUTH");
                v.tell("You walk to Room 1");
                fromDirection(v,r1, newD);

            }else {
                v.tell("Leaving WEST");
                v.tell("You walk past the fountain and go to Room 2");
                v.tell("You watch the fountain glisten from the skylight");
                fromDirection(v,r2, newD);
            }

        // room 4
        }else{
            if (newD == Direction.TO_NORTH){
                v.tell("Leaving NORTH");
                v.tell("You walk back into Room 4");
                fromDirection(v,r4, newD);
                
            } else if (newD == Direction.TO_EAST){
                v.tell("Leaving EAST");
                v.tell("You walk to Room 3");

            }else if (newD == Direction.TO_SOUTH){
                v.tell("Leaving SOUTH");
                v.tell("You walk to Room 1");
                fromDirection(v,r1, newD);

            }else {
                v.tell("Leaving WEST");
                v.tell("You walk to Room 2");
                fromDirection(v,r2, newD); 
            }
        }
        
        return d;
    }

    // returns the direction the visitor leaves 
    // (the same as the direction they leave the house from)
    // 
    public Direction visit(Visitor v, Direction d) {
        
        v.tell("Welcome to my house");
        v.tell("You are now enetering room 1, I hope you enjoy :)");
        Direction leaving =fromDirection(v,r1,d);

        return leaving;
    }
}

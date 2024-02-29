package OOP.ec22518.MP.contributions;

import java.util.Scanner;
class House_ec22616 implements Visitable {

    private Room room_1;
    private Room room_2;
    private Room room_3;
    
    
    House_ec22616(){
        room_1 = new Room_ec22549();
        room_2 = new Room_ec22852();
        room_3 = new Room_ec22617();
        
    }
    
    
    public Direction visit(Visitor v, Direction d) {
      
        v.tell("You are in room 1 right now.");
        Room current_room = room_1;
        

        if(d == Direction.FROM_EAST){
            if(current_room == room_1){
                current_room = room_3;
                room_3.visit(v,d);
            }else{
                v.tell("You cannot go east from here");
                }

        } else if(d == Direction.FROM_WEST){
            if(current_room == room_3){
                current_room = room_2;
                room_2.visit(v,d);
            } else{
                v.tell("You cannot go west from here");
            }
        }  else if(d == Direction.FROM_SOUTH){
            if(current_room == room_2){
                current_room = room_1;
            } else{
                v.tell("You cannot go west from here");
            }

            
        }

        
        v.tell("You are in a booby trapped room and all the other doors have closed and a new door has appeared.");
        v.tell("However there is a monster blocking it and it appears to have malicious intent.");
        v.tell("What do you decide to do?");
        v.tell("(a) Fight the monster");
        v.tell("(b) Wait it out because you're scared to fight.");
        
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        
        if(option.equals("a")){
            v.tell("Since you suddenly decide to fight this powerful monster you got the power to create items out of thin air");
            v.tell("What would you like to create to defeat this beast?");
            
            String weapon;
            weapon = scanner.nextLine();
            
            v.tell("Wow a " + weapon + " is a great choice to");
            v.tell("Now fight until you win!");
            v.tell("Well done you beat the monster and have escaped");
            return d;
        
        } else if(option.equals("b")){
            v.tell("You are a coward and you will die in this room from starvation.");
            return d;
        } else{
            return d;
        }
        
        
    }
}

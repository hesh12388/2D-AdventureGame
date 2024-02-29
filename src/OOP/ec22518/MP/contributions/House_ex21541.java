package OOP.ec22518.MP.contributions;

import java.util.*;
class House_ex21541 implements Visitable {
     
     Room[] rooms = {new Room_ec22923(),new Room_ex21541(),new Room_ec22891(),new Room_ex20181()}; 
    
    
    public boolean askExit(){
    
        Scanner scanner = new Scanner(System.in);
        boolean t = false;
        System.out.println("Would you like to keep playing (1) or exit (2)?");
        char choice3 = scanner.next().charAt(0);
        if(choice3 == '1'){t = false;}
        else if(choice3 == '2'){t = true;}
        
        return t;
    
    
    }
    
    
    
     public Direction visit(Visitor v, Direction d) {
         
         boolean tAccept;
         boolean exit = false;
         Direction currentD = d;
         Direction from = d;
         int loc = 0;
         char choice1;
         char choice2;
         
         Item crossbow = new Item("Crossbow");
         v.tell("You have entered the house and discovered a crossbow.");
         tAccept = v.giveItem(crossbow);
         if(tAccept){v.tell("There are no bolts however... Maybe you'll find some ahead");}
         char select1[] = {'1','2'};
         
         
         choice1 = v.getChoice("There are 4 doors infront of you. Would you like to explore (1) or leave the house (2)?",select1);
         if(choice1 == '1'){
             char select2[] = {'A','B','C','D'};
             v.tell("Room A is to the north, Room B is to the east, Room C is to the west and Room D is to the south.");
             choice2 = v.getChoice("Which door would you like to enter? - A,B,C,D",select2);
             if(choice2 == 'A'){loc = 0; from = Direction.FROM_SOUTH;}
             else if(choice2 == 'B'){loc = 1; from = Direction.FROM_WEST;}
             else if(choice2 == 'C'){loc = 2; from = Direction.FROM_EAST;}
             else if(choice2 == 'D'){loc = 3; from = Direction.FROM_NORTH;}
             
             while(!exit)
             switch(loc){
             
                 case 0:
                    currentD = rooms[0].visit(v,from);
                     if(currentD == Direction.TO_EAST || currentD == Direction.TO_WEST){loc = 1;}
                     else if(currentD == Direction.TO_NORTH || currentD == Direction.TO_SOUTH){loc = 2;}
                     if(askExit()){return currentD;}
                     break;
             
                 case 1:
                    currentD = rooms[1].visit(v,from);
                     if(currentD == Direction.TO_EAST || currentD == Direction.TO_WEST){loc = 0;}
                     else if(currentD == Direction.TO_NORTH || currentD == Direction.TO_SOUTH){loc = 3;}
                     if(askExit()){return currentD;}
                     break;
             
                 case 2:
                    currentD = rooms[2].visit(v,from);
                     if(currentD == Direction.TO_EAST || currentD == Direction.TO_WEST){loc = 3;}
                     else if(currentD == Direction.TO_NORTH || currentD == Direction.TO_SOUTH){loc = 0;}
                     if(askExit()){return currentD;}
                     break;
             
                 case 3:
                    currentD = rooms[3].visit(v,from);
                    exit = true;
                    break;
             
             }
             
             
         
         
         }
         else if(choice1 == '2'){
         v.tell("You are now leaving the house the way you came in... You pot of piss!");
         currentD = Direction.opposite(d);    
             
         
         }
         

         
         
         return Direction.TO_NORTH;
     }
}
 

package OOP.ec22518.MP.contributions;

import java.util.Scanner;
class House_ec22532 implements Visitable {

    Room roomOne;
    Room roomTwo;
    Room roomThree;
    
    House_ec22532(){
    
        roomOne = new Room_ec22532();
        roomTwo = new Room_ec22842();
        roomThree = new Room_ec22929();
        
    }
    
    
    public Direction visit(Visitor v, Direction d) {

        int housePos = 0;
        Direction directionGoing;
        Direction directionFrom;


        directionFrom = d;
        directionGoing = Direction.UNDEFINED;

        while(housePos != -1){

            if (housePos == 0){
                
                if (hallwayChoice() == true){break;}
                else{
                    directionGoing = roomOne.visit(v,directionFrom);
                    directionGoing = directionGoing.opposite(directionGoing); //must be done as room ec22532 returns a "FROM" direction
                    directionFrom = directionGoing.opposite(directionGoing);
                    
                    if (directionGoing == Direction.TO_SOUTH || directionGoing == Direction.TO_EAST || directionGoing == Direction.TO_WEST){housePos = 1;}
                    else if (directionGoing == Direction.TO_NORTH){housePos = -1; System.out.println("You have left the house through the front exit"); break;}
                    
                    }} //going into room 1
            
            else if (housePos == 1){
                
                if (hallwayChoice() == true){break;}
                else{
                    directionGoing = roomTwo.visit(v,directionFrom);
                    directionFrom = directionGoing.opposite(directionGoing);
                    
                    if (directionGoing == Direction.TO_SOUTH || directionGoing == Direction.TO_EAST){housePos = 0;}
                    else if (directionGoing == Direction.TO_NORTH){housePos = 2;}
                    else if (directionGoing == Direction.TO_WEST){housePos = -1; System.out.println("You have left the house through the secret exit"); break;}
                    
                    
                    
                    }} //going into room 2
            
            else if (housePos == 2){
                
                if (hallwayChoice() == true){break;}
                else{
                    directionGoing = roomThree.visit(v,directionFrom);
                    directionFrom = directionGoing.opposite(directionGoing);
                    
                    if (directionGoing == Direction.TO_WEST || directionGoing == Direction.TO_EAST){housePos = 0;}
                    else if (directionGoing == Direction.TO_SOUTH){housePos = 1;}
                    else if (directionGoing == Direction.TO_NORTH){housePos = -1; System.out.println("You have left the house through the back exit"); break;}
                    
                    
                    }} //going into room 3
            
            
        }

            return directionFrom;
    }    

    
    
    public boolean hallwayChoice(){
        System.out.println("Do you a) leave the house or b) try to go to the next room ?");
        String userChoice = inputString();
        if (userChoice.toUpperCase().equals("A")){
            return true;
        }
        else {
            return false;
        }
        
    }
    
    public String inputString(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

}

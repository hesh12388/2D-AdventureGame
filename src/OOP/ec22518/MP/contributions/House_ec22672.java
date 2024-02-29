package OOP.ec22518.MP.contributions;

import java.util.Scanner;

class House_ec22672 implements Visitable {
    
    @Override
    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        Scanner sc = new Scanner(System.in);
        Room[] rooms = {
            new Room_ec22542(),
            new Room_ec21645(),
            new Room_ec22436(),
            new Room_ec22429()
        };
        
        int currentRoom = 0; //index of current room

        System.out.println("You enter House_ec22672.");
        System.out.println("There is only one door in front of you. Would you like to continue [y] or go back and leave [n]");
        String answer = sc.nextLine();
        if(answer.equalsIgnoreCase("n")){
            System.out.println("Bye.");
            return Direction.opposite(directionVistorArrivesFrom);
        }else if (answer.equalsIgnoreCase("y")){
            System.out.println("Moving forward");
        }
        boolean visiting = true;
        while (visiting){
            rooms[currentRoom].visit(visitor, directionVistorArrivesFrom);
            currentRoom++;
            if(currentRoom==4){
                currentRoom = 0;
                System.out.print("It seems you are back where you started. Continue? [y]");
                String exit = sc.nextLine();
                if(exit.equalsIgnoreCase("y")){
                    visiting = false;
                }
            }
        }
        return Direction.opposite(directionVistorArrivesFrom);
    }

}

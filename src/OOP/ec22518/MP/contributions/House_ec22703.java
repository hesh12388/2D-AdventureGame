package OOP.ec22518.MP.contributions;

import java.util.Random;
import java.util.Scanner;

class House_ec22703 implements Visitable {

    Room north;
    Room east;
    Room south;
    Room west;

    House_ec22703() {
        north = new Room_ec22703(); //me (person N)
        east = new Room_ec22741(); //not me (person E)
        south = new Room_ec22894(); //not me (person S)
        west = new Room_ec22566(); //not me (person W)
    }

    // list of items that can be given
    static final Item clothes = new Item("pile of clothes");

    public Direction visit(Visitor visitor, Direction d) {
    
        boolean temp = true, cross = true;
        String temp_ans = "";
        int number1 = 0, number2 = 0;
        Random num = new Random();
        String location;

        try (Scanner scan = new Scanner(System.in)) {
            visitor.tell("Hello player...you are at the front door to the mansion, where would you like to go?");
            visitor.tell("Options: north / east / south / west"); // lets players choose what room they go to

            location = scan.next();
            Visitor v = new IOVisitor(System.out,System.in);

            if (location.equals(String.valueOf(north))) { //checks input by player with name of room object to see where they go
                visitor.tell("Developer N is ecstatic that his room is choses and begins :D");
                visitor.tell("Hello, you are in room ec22703 of the north");
                visitor.tell("The room is in a mess, it is your job to TRY and clean it! (hopefully nothing BAD happens)");
                d = north.visit(v, Direction.FROM_NORTH);
                
            
                String descriptionOfChoices = "Here are your choices" + "\n a: pick up clothes" + "\n b: organise the desk"
                        + "\n c: charge phone" + "\n d: check temperature";
                char[] arrayOfPossibleChoices = { 'a', 'b', 'c', 'd' };
            
                char choice = visitor.getChoice(descriptionOfChoices, arrayOfPossibleChoices);
            
                if (choice == 'a') {
                    visitor.tell("You have vision of the clothes...");
                    visitor.tell("BOOOOM...you have teleported to the clothes");
                    visitor.tell("now they are in your hand :D");
                    visitor.giveItem(clothes);
                    number1 = num.nextInt(10);
                    number2 = num.nextInt(10);
            
                    if (number1 == number2) { // this is for the randomisation of giving a cross to the lucky player
                        cross = true;
                    }
                    if (number1 != number2) {
                        cross = false;
                    }
                }
            
                if (choice == 'b') {
                    visitor.tell("You walk towards the desk");
                    visitor.tell("You begin to start sorting and organising the desk to make it looks more clean");
                    visitor.giveGold(2);
                    visitor.tell("Heres a little something to say thank you");
                }
            
                if (choice == 'c') {
                    visitor.tell("Great...you charged it but also walked through this pile of rubbish");
                    visitor.takeGold(1);
                    visitor.tell("someone or some thing has taken away some of your gold!");
                }
            
                // this is a horror game reference from phasmaphobia for context
                if (choice == 'd') {
                    visitor.tell("You check the themometer and see...but its broken" + "\n do you feel hot or cold?");
                    temp_ans = scan.nextLine();
                } else if (temp_ans.equalsIgnoreCase("hot")) {
                    temp = true;
                } else if (temp_ans.equalsIgnoreCase("cold")) {
                    temp = false;
                }
            
                if (temp) {
                    visitor.tell("I guess there is no paranormal activity for now...");
                } else {
                    visitor.tell("maybe leave right now");
                }
            
                if (visitor.hasIdenticalItem(clothes) || (visitor.hasEqualItem(clothes))) {
                    visitor.giveGold(1);
                } else {
                    visitor.takeGold(1);
                }
            
                if (cross) {
                    visitor.tell(
                            "Somehow you managed to find a cross with holy water. You have infinite immunity to the devil.");
                } else {
                    visitor.tell(
                            "unfortunatly your actions of cleaning have not changed the devils mind. Be wary of your future path...");
                }
            }

            if (location.equals(String.valueOf(east))) { //checks input by player with name of room object to see where they go
                visitor.tell("Developer E apologises");
                visitor.tell("Sorry, as of current time in our game developement enough funding has not been put into our company");
                visitor.tell("so that other levels/rooms in our house function. Please wait a bit and in the near future our game");
                visitor.tell("will be ready for everyone to access all the rooms >.<");
                d = east.visit(v, Direction.FROM_EAST);
            }

            if (location.equals(String.valueOf(south))) { //checks input by player with name of room object to see where they go
                visitor.tell("Developer S apologises");
                visitor.tell("Sorry, as of current time in our game developement enough funding has not been put into our company");
                visitor.tell("so that other levels/rooms in our house function. Please wait a bit and in the near future our game");
                visitor.tell("will be ready for everyone to access all the rooms >.<");
                d = south.visit(v, Direction.FROM_SOUTH);
            }

            if (location.equals(String.valueOf(west))) { //checks input by player with name of room object to see where they go
                visitor.tell("Developer W apologises");
                visitor.tell("Sorry, as of current time in our game developement enough funding has not been put into our company");
                visitor.tell("so that other levels/rooms in our house function. Please wait a bit and in the near future our game");
                visitor.tell("will be ready for everyone to access all the rooms >.<");
                d = west.visit(v, Direction.FROM_WEST);
            }
        }
        return d;
    }
    
}

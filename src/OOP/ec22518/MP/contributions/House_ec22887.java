package OOP.ec22518.MP.contributions;

class House_ec22887 implements Visitable {
    //Made use of rooms by the following student id's: ec22872, ec22887 (myself), ec22746, ec22582, ec22666
    final int rooms = 5; 
    Room[] hrooms = new Room[rooms];
    
    House_ec22887(){
        Room_ec22872 r1 = new Room_ec22872();
        hrooms[0] = r1;
        Room_ec22887 r2 = new Room_ec22887();
        hrooms[1] = r2;
        Room_ec22746 r3 = new Room_ec22746();
        hrooms[2] = r3;
        Room_ec22582 r4 = new Room_ec22582();
        hrooms[3] = r4;
        Room_ec22666 r5 = new Room_ec22666();
        hrooms[4] = r5;
    }
    
    public Direction visit(Visitor visitor, Direction directionVisitorArrivesFrom){
        Direction vcurrent;
        boolean exit = false;
        Room clocation = hrooms[0];
        visitor.tell("As you walk into the entrance hallway of the house, you can automatically sense the modern but haunting atmosphere surrounding you as you walk down the hallway admiring different aspects of the interior design. It feels as if someone is constantly watching you due to the fact that there is various portraits of what seems to be rich men and women");
        char hallwaylook = visitor.getChoice("Do you want to a) inspect the paintings, b) call out to see if anyone is there or c) carry on walking forward", new char[]{'a', 'b', 'c'});
        if (hallwaylook == 'a' || hallwaylook == 'A'){
            visitor.tell("You look closer at the paintings and find free gold!");
            visitor.giveGold(5);
        }
        else if (hallwaylook == 'b' || hallwaylook == 'B'){
            visitor.tell("You call out to see if anyone is there and a ghost appears out of NOWHERE");
            visitor.tell("The ghost scares you and you end up falling to the ground but you get up quickly");
        }
        visitor.tell("You then walk down the hallway and see two doors in front of you");
        char roomc = visitor.getChoice("Do you want to pick the door on the left (L) or the door on the right (R)", new char[]{'L','R'});
        if (roomc == 'L' || roomc == 'l'){
            vcurrent = hrooms[0].visit(visitor, directionVisitorArrivesFrom);
        }
        else if (roomc == 'R' || roomc == 'r'){
            vcurrent = hrooms[3].visit(visitor, directionVisitorArrivesFrom);
        }
        else {
            visitor.tell("You didn't pick a valid door so you walk around for a bit longer and leave");
            return Direction.opposite(directionVisitorArrivesFrom);
        }
        while(!exit){
            if (vcurrent == Direction.TO_NORTH){
                if (clocation == hrooms[3]){
                    clocation = hrooms[0];
                }
                else if (clocation == hrooms[4]){ 
                    clocation = hrooms[1];
                }
                else if(clocation == hrooms[2]){
                    exit = true; 
                }
                else if (clocation == hrooms[0]){
                    clocation = hrooms[3];
                    visitor.tell("It seems you have walked into a spooky portal which takes you to the opposite room on the other side of the house!");
                }
                else if (clocation == hrooms[1]){
                    clocation = hrooms[4];
                    visitor.tell("It seems you have walked into a crazy portal which takes you to the opposite room on the other side of the house!");
                }
            }
            else if (vcurrent == Direction.TO_SOUTH){
                if (clocation == hrooms[0]){
                    clocation = hrooms[3];
                }
                else if (clocation == hrooms[1]){
                    clocation = hrooms[4];
                }
                else if (clocation == hrooms[2]){
                    exit = true; 
                }
                else if (clocation == hrooms[3]){
                    clocation = hrooms[0];
                    visitor.tell("As you walked out of the window you end up being propelled up in the air and end up in the room on the other side of the house");
                }
                else if (clocation == hrooms[4]){
                    clocation = hrooms[1];
                    visitor.tell("As it turns out, you got slid out of the house and ended up in the room on the other side of the house");
                }
            }
            else if (vcurrent == Direction.TO_EAST){
                if (clocation == hrooms[0]){
                    clocation = hrooms[1];
                }
                else if (clocation == hrooms[2]){
                    exit = true; 
                }
                else if (clocation == hrooms[3]){
                    clocation = hrooms[4];
                }
                else if (clocation == hrooms[4] || clocation == hrooms[1]){
                    clocation = hrooms[2];
                }
            }
            else if (vcurrent == Direction.TO_WEST){
                if (clocation == hrooms[1]){
                    clocation = hrooms[0];
                }
                else if (clocation == hrooms[4]){
                    clocation = hrooms[3];
                }
                else if (clocation == hrooms[0] || clocation == hrooms[3]){
                    boolean check = ehallway(visitor); 
                    if (check == false){
                        exit = true; 
                    }
                    else {
                        char roomnew = visitor.getChoice("Do you want to pick the door on the left (L) or the door on the right (R)", new char[]{'L','R'});
                        if (roomnew == 'L' || roomnew == 'l'){
                            
                            clocation = hrooms[4];
                        }
                        else if (roomnew == 'R' || roomnew == 'r'){
                            clocation = hrooms[1];
                        }
                        else {
                            visitor.tell("The door you chose was not a valid door, therefore, the house starts creaking and all of a sudden you get kicked out of the house");
                            exit = true; 
                        }
                    }
                }
                else if (clocation == hrooms[2]){
                    visitor.tell("In order for you to go backwards in the house, you must choose if you want to explore the room on the left or the right");
                    char exitr = visitor.getChoice("Do you want to pick the door on the left (L) or the door on the right (R)", new char[]{'L','R'});
                    if (exitr == 'L' || exitr == 'l'){
                        clocation = hrooms[4];
                    }
                    else if (exitr == 'R' || exitr == 'r'){
                        clocation = hrooms[1];
                    }
                    else {
                        visitor.tell("The door you chose was not a valid door, therefore, the house starts creaking and all of a sudden you get kicked out of the house");
                        exit = true; 
                    }
                }
            }
            if (exit != true){
                vcurrent = clocation.visit(visitor, vcurrent);
            }
            
        }
        visitor.tell("You have made your way to the exit of the house, hope you enjoyed exploring!!");
        return vcurrent; 
    }
    
    public Boolean ehallway(Visitor visitor){
        visitor.tell("You end up at the entrance hallway to the house again.");
        visitor.tell("You can go back through the doors or leave the house from the entrance whilst you have the chance");
        char carryon = visitor.getChoice("Would you like to leave now whilst you have the chance or continue exploring? (y/n)", new char[]{'y','n'});
        if (carryon == 'y' || carryon == 'Y'){
            return false; 
        }
        else {
            return true; 
        }
    }
}

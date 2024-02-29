package OOP.ec22518.MP.contributions;

class House_ec22724 implements Visitable {
    
    private Room room_1;
    private Room room_2;
    private Room room_3;
    private Room room_4;
    private boolean chest;

    public House_ec22724(){
        room_1 = new Room_ec22723();
        room_2 = new Room_ec22718();
        room_3 = new Room_ec22717();
        room_4 = new Room_ec22724();
    }

    public Direction visit(Visitor visitors, Direction directions){
        Room[] rooms = {room_1,room_2,room_3,room_4};
        int Gold = 7;
        char[] choices ={'a','b','c','d','e'};
        chest = false;
        char choice = visitors.getChoice("Do you wish to enter the house from the North, East, South or West entrance?:\na: North\nb: East\nc: South\nd: West\ne: leave",choices);
        while (choice!='e'){
            if(choice=='c'){
                visitors.tell("You enter the door from the South side of the house.");
                directions = Direction.TO_SOUTH;
                directions = rooms[0].visit(visitors, directions);
                visitors.tell("You notice a shine, it's 2 gold coin on the floor you pick it up.");
                visitors.giveGold(2);
                Gold = Gold+2;
                visitors.tell("You've escaped the South hall.");
                
            }
    
            else if(choice=='a'){
                visitors.tell("You enter the door from the North side of the house.");
                directions = Direction.TO_NORTH;
                directions = rooms[1].visit(visitors, directions);
                visitors.tell("You trip over and find 3 gold coins on the floor you pick it up");
                visitors.giveGold(3);
                Gold = Gold+3;
                visitors.tell("You've escaped the North hall.");
            }
    
            else if(choice=='b'){
                visitors.tell("You enter the door from the East side of the house.");
                directions = Direction.TO_EAST;
                directions = rooms[2].visit(visitors, directions);
                visitors.tell("You find a singular gold coin on the floor you pick it up.");
                visitors.giveGold(1);
                Gold = Gold+8;
                visitors.tell("You've escaped the East hall.");
            }
    
            else if(choice=='d'){
                visitors.tell("You enter the door from the West side of the house.");
                directions = Direction.TO_WEST;
                directions = rooms[3].visit(visitors, directions);
                visitors.tell("You open up a chest and find 5 coins inside");
                chest = true;
                visitors.giveGold(5);
                Gold = Gold+5;
                visitors.tell("You've escaped the North hall.");
                
                
                
            }

            choice = visitors.getChoice("Do you wish to enter the house from the North, East, South or West entrance?:\na: North\nb: East\nc: South\nd: West\ne: leave",choices);
        }
       
        visitors.tell("You've escaped the house");
        visitors.tell("You've collected "+Gold+" pieces of Gold congratulations");
        return directions;

    }
}

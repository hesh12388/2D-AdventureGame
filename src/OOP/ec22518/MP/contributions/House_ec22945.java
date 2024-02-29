package OOP.ec22518.MP.contributions;

public class House_ec22945 implements Visitable {
    private Room[] houseRooms;


public House_ec22945(){
    houseRooms = new Room[4];

    houseRooms[0] = new Room_ec22945();
    houseRooms[1] = new Room_ec21499();
    houseRooms[2] = new Room_ec22695(); 
    houseRooms[3] = new Room_ec21578();
}


public Direction visit(Visitor vis, Direction dir){
    Boolean exitHouse = false;
    vis.tell("Welcome to Mateusz's house!");
    vis.tell("You need to find the key in the mystery room, or find your way to the plushy room in order to leave!");
    vis.tell("You are currently located in the main room...");
    Room currentRoom = houseRooms[0];
    char[] choices = {'N','S','W','E'};
    char userchoice =vis.getChoice(("Select the direction you are about to follow! (N for north, S for south, W for west, E for east!"), choices);
    while (!exitHouse){
   //chooses north

        if (userchoice == 'N'){
        currentRoom = houseRooms[2];
        vis.tell("You have entered the study room..");
        vis.tell("There is nothing to do but study...");
        char[] studyRoomChoices = {'E'};
        char userchoice2 = vis.getChoice(("Select the next direction you are about to follow! (E for east!)"), studyRoomChoices);
            //next choice
            if (userchoice2 == 'E'){
                dir = Direction.TO_EAST;
                currentRoom = houseRooms[1];
                vis.tell("You found the mystery room...");
                vis.tell("There is a chest in the middle...");
                vis.tell("And the golden key is inside!");
                exitHouse = true;
                return Direction.TO_EAST;
            }

    }


    else if(userchoice == 'S'){
            currentRoom = houseRooms[3];
            vis.tell("You found the plushy room straight away, well done!!");
            exitHouse = true;
         return Direction.TO_SOUTH;
        }


     else if(userchoice == 'W'){
            char[] goBackChoices = {'E'};
            char userchoice3 = vis.getChoice(("Dead end! (E) to re-enter the main room"), goBackChoices);
            if (userchoice3 == 'E'){
                currentRoom = houseRooms[0];
                vis.tell("You are back in the main room...");
            }
            else{
                 vis.tell("There is no other direction you can follow..");
            }
        }


    else if(userchoice == 'E'){
            currentRoom = houseRooms[1];
            vis.tell("You found the mystery room...");
            vis.tell("There is a chest in the middle...");
            vis.tell("And the golden key is inside!");
            exitHouse = true;
            return Direction.TO_EAST;
       }

    else
    {
     dir = Direction.opposite(dir);
    }
         dir = currentRoom.visit(vis, dir);
}
     return dir;
    }
}

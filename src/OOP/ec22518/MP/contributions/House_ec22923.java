package OOP.ec22518.MP.contributions;

class House_ec22923 implements Visitable {

    private final Room[] groundFloor;
    private final Room[] firstFloor;
    private final Room[] listOfFirstRooms;
    private final Room basement;
    private int currentFloorNum = 0; //by default, it is the ground-floor
    private boolean visitedFirstRoom = false;
    private boolean hasCrystalAndPendant = false;
    private boolean inspectedPainting = false;
    private boolean lightsStatus = false;
    public House_ec22923(){
        groundFloor = new Room[] {new Room_ec22889(), new Room_ec22919(), new Room_ec22578()}; //find the pendant in Room_ec22431
        firstFloor = new Room[] {new Room_ec22431(), new Room_ec22937(), new Room_ec22545()};
        listOfFirstRooms = new Room [] {new Room_ec22917(), new Room_ec21504(), new Room_ec221013()};
        basement = new Room_ec22923(); //find the crystal in Room_ec22923
    }

    public Direction visit(Visitor v, Direction directionArriveFrom){
        v.tell("You step into the eerie looking house," +
                "but as you enter the door behind you slams shut." +
                "The house rumbles, and you feel the layout changing.");
        v.tell("The rooms swap in front of you endlessly, " +
                "until only one room is front of you.");
        promptEnterKey(v);
        // first room will always be randomly selected from the listOfFirstRooms array
        int firstRoom = shuffleRoom(directionArriveFrom, listOfFirstRooms);
        Direction directionExitTo = roomTrap(v, directionArriveFrom, firstRoom);


        return directionExitTo;
    }

    private Direction roomTrap(Visitor v, Direction directionArriveFrom, int roomIndex){
        Direction directionExitTo = directionArriveFrom; // default



        while(!hasCrystalAndPendant){
            v.tell("You look around you, judging from the surrounding it seems like you are on the " + currentFloor(currentFloorNum));
            if(!visitedFirstRoom){ directionExitTo = listOfFirstRooms[roomIndex].visit(v, directionArriveFrom); visitedFirstRoom = true;}
            v.tell("You have exited "+exitDirection(v, directionExitTo) + ".");
            currentFloorNum = pickFloor(v, currentFloorNum);
            directionExitTo =  roomsToDisplay(v, directionExitTo, currentFloorNum);
            v.tell("A mysterious force sends you in the opposite direction!");
            directionExitTo = Direction.opposite(directionExitTo);

            promptEnterKey(v);

            checkForConditions(v);
        }
        v.tell("\nThe crystal in the pendant suddenly glows, emitting a blinding light!");
        v.tell("You rub your eyes, only to find out you are no longer in the house but on the pavement.");
        v.tell("Strange, you just shrug it off and continue with your life - after all, A6 is 4 days from now.");

        return directionExitTo;
    }


    private int pickFloor(Visitor v, int currentFloorNum){
        v.tell("You are currently standing in the hallway.");
        if(currentFloorNum == 0){
            hallway(v, currentFloorNum);
            currentFloorNum = groundFloorOptions(v, currentFloorNum);
        } else if (currentFloorNum == 1) {
            hallway(v, currentFloorNum);
            currentFloorNum = firstFloorOptions(v, currentFloorNum);
        } else if (currentFloorNum == -1) {
            currentFloorNum = basementOptions(v, currentFloorNum);
        }
        return currentFloorNum;
    }
    private void hallway(Visitor v, int currentFloorNum){
        if(currentFloorNum == 0){
            switch(v.getChoice("There is a painting on this floor, do you want to inspect it (y/n)?",new char[]{'y', 'n'})){
                case 'y':
                    if(!inspectedPainting){
                        v.tell("You vigorously inspect the painting, even going " +
                                "so far as licking it. But your ..weird.. efforts are rewarded" +
                                "and you find 3 gold!");
                        v.giveGold(3);
                        inspectedPainting = true;
                        break;
                    }
                    else{
                        v.tell("You attempt to lick the painting again.." +
                                "but the painting fights back and consequently you lose 2 gold!" );
                        v.takeGold(2);
                        break;
                    }
                case 'n':
                    v.tell("You've decided not to, good choice, this usually how the side characters die.");
                    break;
                default:
                    v.tell("You ignore it.");
                    break;
            }
        }
        else if(currentFloorNum == 1){
            if(!lightsStatus){
                v.tell("The lights are currently off.." +
                        "you quickly turn them on.");
                lightsStatus = true;
            }
        }
    }
    private int groundFloorOptions(Visitor v, int currentFloorNum){
        v.tell("Around you are " +groundFloor.length+ " rooms, a staircase leading to the first floor" +
                " and a staircase leading to the basement.");
        switch(v.getChoice("Would you like go to the first floor (1)," +
                "basement (2), or stay on the ground floor (3)?", new char[] {'1','2','3'})) {
            case '1':
                v.tell("You climb up the stairs...");
                currentFloorNum = 1;
                break;
            case '2':
                v.tell("You climb down the stairs...");
                currentFloorNum = -1;
                break;
            case '3':
                v.tell("You've chosen to stay on the floor.");
            default: v.tell("You shake your head, that wasn't an answer." +
                    "It feels fitting that you should just stay on the current floor.");break;
        }
        return currentFloorNum;
    }

    private int firstFloorOptions(Visitor v, int currentFloorNum){
        v.tell("Around you are " +firstFloor.length+ " rooms and a staircase leading to the ground floor.");
        switch(v.getChoice("Would you like go to the ground floor (1) or stay on the first floor (2)?", new char[] {'1','2'})) {
            case '1':
                v.tell("You walk down the stairs...");
                currentFloorNum = 0;
                break;
            case '2':
                v.tell("You've chosen to stay on the floor.");
                break;
            default: v.tell("You shake your head, that wasn't an answer." +
                    "It feels fitting that you should just stay on the current floor.");
        }
        return currentFloorNum;
    }

    private int basementOptions(Visitor v, int currentFloorNum){
        v.tell("You are currently in the basement.");
        switch(v.getChoice("Would you like go to the ground floor (1) or stay in the basement (2)?", new char[] {'1','2'})) {
            case '1':
                v.tell("You walk up the stairs...");
                currentFloorNum = 0;
                break;
            case '2':
                v.tell("You've chosen to stay in the basement.");
                break;
            default: v.tell("You shake your head, that wasn't an answer." +
                    "It feels fitting that you should just stay on the current floor.");
        }
        return currentFloorNum;
    }

    private Direction displayGroundRooms(Visitor v, Direction d){
        v.tell("Since you've chosen to stay, you should probably wander" +
                " the rooms." );
        switch(v.getChoice("Would you like to enter the room on your right (r)" +
                ", your left (l) or directly north of you (n)", new char[]{'r', 'l', 'n'})){
            case 'r':
                d = Direction.TO_EAST;
                d= groundFloor[0].visit(v, d);
                break;
            case 'l':
                d = Direction.TO_WEST;
                groundFloor[1].visit(v, d);
            case 'n':
                d = Direction.TO_NORTH;
                d = groundFloor[2].visit(v, d);
            default:
                v.tell("You don't like decisions, so you just randomly select a room and run in to it!");
                d = groundFloor[shuffleRoom(d, groundFloor)].visit(v, d);
        }
        return d;
    }

    private Direction displayFirstFloorRooms(Visitor v, Direction d){
        v.tell("Since you've chosen to stay, you should probably wander" +
                " the rooms." );
        switch(v.getChoice("Would you like to enter the room on your right (r)" +
                ", your left (l) or directly north of you (n)", new char[]{'r', 'l', 'n'})){
            case 'r':
                d = Direction.TO_EAST;
                d = firstFloor[0].visit(v, d);
                break;
            case 'l':
                d = Direction.TO_WEST;
                d = firstFloor[1].visit(v, d);
            case 'n':
                d = Direction.TO_NORTH;
                d = firstFloor[2].visit(v, d);
            default:
                v.tell("You don't like decisions, so you just randomly select a room and run in to it!");
                d = firstFloor[shuffleRoom(d, firstFloor)].visit(v, d);
        }
        return d;
    }

    private Direction roomsToDisplay(Visitor v, Direction d, int currentFloorNum){
        if(currentFloorNum == 0){
            d = displayGroundRooms(v, d);
        }
        else if (currentFloorNum == 1){
            d = displayFirstFloorRooms(v, d);
        }
        else if (currentFloorNum == -1)
        {
            d = basement.visit(v, d);
        }
        return d;
    }

    private String exitDirection(Visitor v, Direction d){
        if(d == Direction.FROM_EAST){return "West";}
        else if(d == Direction.FROM_SOUTH){return "North";}
        else if(d == Direction.FROM_WEST){return "East";}
        else return "South";
    }

    private int shuffleRoom(Direction d, Room[] listOfFirstRooms){

        int randomIndex = (int) (Math.random() * listOfFirstRooms.length);

        if(d == Direction.FROM_SOUTH && randomIndex > 0){ randomIndex -= 1;}
        else if (d == Direction.FROM_EAST && randomIndex < listOfFirstRooms.length - 1){ randomIndex += 1;}

        return randomIndex;
    }

    private String currentFloor(int currentFloorNum) {
        switch (currentFloorNum) {
            case 0:
                return "ground floor.";
            case -1:
                return "basement.";
            default:
                return "first floor.";
        }
    }

    //method checks for certain items, in this case the crystal and pendant
    private void checkForConditions(Visitor v){
        if(v.hasIdenticalItem(Room_ec22431.pendant) && v.hasIdenticalItem(Room_ec22923.crystal)){
            hasCrystalAndPendant = true;
            v.tell("Upon exiting the room, you put the crystal into the pendant..");
        }
    }

    private static void promptEnterKey(Visitor v) {
        v.getChoice("[Press \"ENTER\" to continue...]", new char[] {' '});
    }



}

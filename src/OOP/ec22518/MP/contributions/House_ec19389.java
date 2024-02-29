package OOP.ec22518.MP.contributions;

import java.util.ArrayList;

class House_ec19389 implements Visitable {

    //Defines a class for a hallway
    static class Hallway extends Room{

        @Override
        public Direction visit(Visitor v, Direction d) {

            char choice = '0';

            //The visitor has the option to check thr lights or choose a direction to go towards
            while (choice!='b'){
                choice = v.getChoice("You are now in the main Hallway\n" +
                        "would you like to\n" +
                        "a) Try to turn on the lights\n" +
                        "b) Choose a direction to go",new char[]{'a','b'});

                if (choice=='a') {
                    v.tell("You flick the light switches couple of times but nothing happens\n" +
                            "I mean, what did you expect, have you seen the electricity bills recently...");
                }
                else if (choice=='b'){
                    return chooseDirection(v.getChoice("Please enter a direction\n" +
                            "(N)orth (to exit)\n" +
                            "(E)ast\n" +
                            "(S)outh\n" +
                            "(W)est",new char[]{'N','E','S','W'}),d);
                }
            }
            return d;
        }
    }

    //Defines a class for a kitchen
    class Kitchen extends Room {


        ArrayList<Item> kitchenItemList = new ArrayList<Item>();

        Kitchen() {
            kitchenItemList.add(new Item("can of pineapple"));
            kitchenItemList.add(new Item("can of soda"));
            kitchenItemList.add(new Item("cheese stick"));
        }

        @Override
        public Direction visit(Visitor v, Direction d) {

            char choice = '0';

            //The visitor has the option to check the fridge or choose a direction to go towards
            while (choice != 'b') {
                choice = v.getChoice("You are now in the kitchen\n" +
                        "would you like to\n" +
                        "a) Check the fridge\n" +
                        "b) Choose a direction to go to", new char[]{'a', 'b'});

                switch (choice) {
                    case 'a': //an item is offered to the visitor upon interacting with the fridge
//                        v.tell("You open the fridge and find a can of pineapple, doesn't look out of date\n" +
//                                "You should take it. I mean why not\n");
//                        v.giveItem(new Item("Can of Pineapple"));
                        getFood(v,kitchenItemList);
                    case 'b':
                        return chooseDirection(v.getChoice("Please enter a direction\n" +
                                "(N)orth\n" +
                                "(E)ast\n" +
                                "(S)outh (to exit)\n" +
                                "(W)est", new char[]{'N', 'E', 'S', 'W'}), d);
                    }
                }
                return d;
            }
        }

        //checks if an item exists in a list
        public boolean isItemInList(ArrayList<Item> itemList, String ItemName){
            for (int i = 0; i < itemList.size(); i++) {
                if(itemList.get(i).name.equals(ItemName)){
                    return true;
                }
            }
            return false;
        }

        //removes given Item from a given Item list
        public void removeFromItemList(ArrayList<Item> itemList, String ItemName){
            for (int i = 0; i < itemList.size(); i++) {
                if(itemList.get(i).name.equals(ItemName)){
                    itemList.remove(i);
                }
            }
        }

        //offers the passed visitor food Items options to pick up from a given list of items
        public Item getFood(Visitor v, ArrayList<Item> kitchenItemList){
            char choice = v.getChoice("You open the fridge to check whats inside\n" +
                    "Seems you have some options:\n" +
                    "a) take can of pineapple\n" +
                    "b) take can of soda\n" +
                    "c) take cheese stick",new char[]{'a','b','c'});

            switch (choice){
                case 'a':
                    if(isItemInList(kitchenItemList,"can of pineapple")){
                        removeFromItemList(kitchenItemList, "can of pineapple");
                        return new Item("can of pineapple");
                    }else {
                        v.tell("oh, seems you already picked this up.");
                        break;
                    }
                case 'b':
                    if(isItemInList(kitchenItemList,"can of soda")){
                        removeFromItemList(kitchenItemList, "can of soda");
                        return new Item("can of soda");
                    }else {
                        v.tell("oh, seems you already picked this up.");
                        break;
                    }
                case 'c':
                    if(isItemInList(kitchenItemList,"cheese stick")){
                        removeFromItemList(kitchenItemList, "cheese stick");
                        return new Item("cheese stick");
                    }else {
                        v.tell("oh, seems you already picked this up.");
                        break;
                    }
            }
            v.tell("you cant decide so you take take the first thing you see");
            return new Item("can of pineapple");

    }

    //Defines a class for an exits room. The visitor will exit via this room
    class ExitRoom extends Room{
        @Override
        public Direction visit(Visitor v, Direction d) {
            v.tell("Good bye now, hope you enjoyed your stay");
            return Direction.opposite(d);
        }
    }

    //method which returns a Direction based on the chosen char (N -> TO_NORTH)
    public static Direction chooseDirection(char chosenLetter, Direction defaultDirection){
        switch (chosenLetter){
            case 'N':
                return Direction.TO_NORTH;
            case 'E':
                return Direction.TO_EAST;
            case 'S':
                return Direction.TO_SOUTH;
            case 'W':
                return Direction.TO_WEST;
        }
        return defaultDirection;
    }


    //array of all the available rooms in the house
    Room[] rooms = new Room[9];

    //array for holding the positions of accessible rooms available in the current room
    int[] availableRoomsPos = new int[4];
    //holds the position of the current room
    int currentRoomPos = 0;


    //creates the Room objects and adds them to the rooms array
    House_ec19389(){
        rooms[0] = new Hallway();
        rooms[1] = new Room_ec22992();
        rooms[2] = new Room_ec22617();
        rooms[3] = new Room_ec22959();
        rooms[4] = new Room_ec221028();
        rooms[5] = new Room_ec22551();
        rooms[6] = new Room_ec22662();
        rooms[7] = new Kitchen();

        rooms[8] = new ExitRoom();
    }

    /*  house layout
                       exit
              ---------hall---------
             |          |           |
        --->R4----------R1----------R3--->
            |           |           |
        --->r5----------R2----------r6--->
            |           |           |
            ----------Kitchen--------
                       exit
    */

    //method for updating the availableRoomsPos array when entering a new room
    private void updateAvailableRoomPos(int rm_pos1,int rm_pos2, int rm_pos3, int rm_pos4){
        availableRoomsPos[0] = rm_pos1;
        availableRoomsPos[1] = rm_pos2;
        availableRoomsPos[2] = rm_pos3;
        availableRoomsPos[3] = rm_pos4;
    }


    //returns an array of rooms that are linked to the given room
    private Room[] getLinkedRooms(Room currentRoom, Room[] allRooms){
        if (currentRoom.equals(rooms[0])){
            updateAvailableRoomPos(-1,4,1,4);
            return new Room[]{rooms[8],rooms[3],rooms[1],rooms[4]};
        }
        else if (currentRoom.equals(rooms[1])){
            updateAvailableRoomPos(0,3,2,4);
            return new Room[]{rooms[0],rooms[3],rooms[2],rooms[4]};
        }
        else if  (currentRoom.equals(rooms[2])){
            updateAvailableRoomPos(1,6,7,5);
            return new Room[]{rooms[1],rooms[6],rooms[7],rooms[5]};
        }
        else if  (currentRoom.equals(rooms[3])){
            updateAvailableRoomPos(0,4,6,1);
            return new Room[]{rooms[0],rooms[4],rooms[6],rooms[1]};
        }
        else if  (currentRoom.equals(rooms[4])){
            updateAvailableRoomPos(0,1,5,3);
            return new Room[]{rooms[0],rooms[1],rooms[5],rooms[3]};
        }
        else if  (currentRoom.equals(rooms[5])) {
            updateAvailableRoomPos(4,2,7,6);
            return new Room[]{rooms[4], rooms[2], rooms[7], rooms[6]};
        }
        else if  (currentRoom.equals(rooms[6])) {
            updateAvailableRoomPos(3,5,7,2);
            return new Room[]{rooms[3], rooms[5], rooms[7], rooms[2]};
        }
        else if  (currentRoom.equals(rooms[7])) {
            updateAvailableRoomPos(2,6,-1,5);
            return new Room[]{rooms[2], rooms[6], rooms[8], rooms[5]};
        }
        return new Room[]{rooms[0], rooms[1]};
    }

    /*returns a room given the list of linked rooms and a direction.
    Each direction corresponds to a position on the array
    pos 0 - N | pos 1 - E | pos 2 - S | pos 3 - W
    */
    private Room getNextRoom(Room[] linkedRooms, Direction d) {
        if (d.equals(Direction.FROM_SOUTH) || d.equals(Direction.TO_NORTH)) {
            currentRoomPos = availableRoomsPos[0];
            return linkedRooms[0];
        } else if (d.equals(Direction.FROM_WEST) || d.equals(Direction.TO_EAST)) {
            currentRoomPos = availableRoomsPos[1];
            return linkedRooms[1];
        } else if (d.equals(Direction.FROM_NORTH) || d.equals(Direction.TO_SOUTH)) {
            currentRoomPos = availableRoomsPos[2];
            return linkedRooms[2];
        } else if (d.equals(Direction.FROM_EAST) || d.equals(Direction.TO_WEST)) {
            currentRoomPos = availableRoomsPos[3];
            return linkedRooms[3];
        } else {
            return linkedRooms[0];
        }
    }

    //Visit method for the House, given a visitor and a direction, allows visitor to navigate through rooms
    public Direction visit(Visitor v, Direction d) {
        boolean mainHallLeft = false;

        v.tell("Hello Stranger, I know you are just hearing me in your head.\n" +
                "That's completely normal. Think of me as your personal narrator.\n" +
                "Now, The rooms in this house belongs to unique beings, each room completely different from the other\n" +
                "So be careful as you explore!\n\n" +
                "Good luck!");

        d = rooms[0].visit(v, d);

        /*if the current position is -1, the visitor has chosen to leave.
        visitor will enter the final room (Exit room) before leaving */
        while(currentRoomPos!=-1){
            d = getNextRoom(getLinkedRooms(rooms[currentRoomPos],rooms),d).visit(v,d);
        }
        return Direction.opposite(d);
    }
}
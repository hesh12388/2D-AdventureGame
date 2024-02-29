package OOP.ec22518.MP.contributions;

class House_ec22884 implements Visitable {
    Direction[] directions = {Direction.FROM_SOUTH, Direction.FROM_WEST, Direction.FROM_NORTH, Direction.FROM_EAST};
    boolean[] visitedRoom = {false, false, false};
    Room[] roomArray = new Room[3];
    Room roomM;
    Room roomA;
    Room roomZ;
    
    
    public House_ec22884() {
        roomM = new Room_ec22884();
        roomA = new Room_ec22937();
        roomZ = new Room_ec22917();
        roomArray = new Room[] {roomM, roomZ, roomA};
    }
    
    public Direction visit(Visitor v, Direction d) {
        obtainMap(v);
        
        boolean leaveHouse = false;
        while (!leaveHouse) {
            int choice = chooseRoom(v);
            
            switch (choice) {
                case 0:
                    d = directions[chooseDir(v)];
                    d = roomArray[choice].visit(v,d);
                    visitedRoom[choice] = true;
                    break;
                case 1:
                    d = directions[chooseDir(v)];
                    d = roomArray[choice].visit(v,d);
                    visitedRoom[choice] = true;
                    break;
                case 2:
                    d = directions[chooseDir(v)];
                    d = roomArray[choice].visit(v,d);
                    visitedRoom[choice] = true;
                    break;
                default:
                    exitHousePrompt(v);
                    d = directions[chooseDir(v)];
                    leaveHouse = true;
                    break;
            }
        }
        
        return d;
    }
    
    private void exitHousePrompt(Visitor v) {
        v.tell("You left the house.");
    }
    
    private void obtainMap(Visitor v) {
        v.tell("You find a magical map that lets you pick the direction and the room of your choice.\n");
    }
    
    private int chooseRoom(Visitor v) {
        //roomPrompt(v);
        String[] choices = setChoices();
        char choice = v.getChoice("Please choose the which room to visit. (1) " + choices[0] + "  (2) " + choices[1] + "  (3) " + choices[2] + "  (4) Exit", new char[] {'1','2','3','4'});
        return Character.getNumericValue(choice) -1;
    }
    
    private int chooseDir(Visitor v) {
        //directionPrompt(v);
        char choice = v.getChoice("Please choose the direction of entry. (1) South  (2) West  (3) North  (4) East.",new char[] {'1','2','3','4'});
        return Character.getNumericValue(choice) -1;
    }
    
    private String[] setChoices() {
        String[] choices = {"???", "???", "???"};
        if (visitedRoom[0]) {
            choices[0] = "TreasureRoom";
        }
        if (visitedRoom[0]) {
            choices[0] = "Garden";
        }
        if (visitedRoom[1]) {
            choices[1] = "Alex'sTomb";
        }
        return choices;
    }
    
}

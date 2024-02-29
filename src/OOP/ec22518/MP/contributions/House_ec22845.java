package OOP.ec22518.MP.contributions;

import java.util.Random;

class Corridor extends Room
{
    static final Direction[] allDirections = new Direction[]{Direction.TO_NORTH, Direction.TO_EAST, Direction.TO_SOUTH, Direction.TO_WEST};
    static final char[] allDirectionChars = new char[]{'n','e','s','w'};
    static final String[] allDirectionStrings = new String[]{"north","east","south","west"};

    public Direction visit(Visitor visitor, Direction direction) 
    {
        visitor.tell("You are in a corridor.");

        char choice = visitor.getChoice("Do you go:\n    n) north\n    e) east\n    s) south\n    w) west?", new char[]{'n','e','s','w'});

        for (int i=0; i<allDirectionChars.length; i++) { if (choice == allDirectionChars[i]) { return allDirections[i]; } }

        return Direction.UNDEFINED;
    }

    public String toString() { return "Corridor"; }
}

class Entrance extends Corridor { public String toString() { return "Entrance"; } }

class Exit extends Corridor 
{
    Direction exitDirection;

    Exit(Direction exitDirection) { this.exitDirection = exitDirection; };

    public Direction visit(Visitor visitor, Direction direction) 
    {
        visitor.tell("You see an exit " + exitDirection.toString() + ".");

        char choice = visitor.getChoice("Do you go:\n    n) north\n    e) east\n    s) south\n    w) west?", new char[]{'n','e','s','w'});

        for (int i=0; i<allDirectionChars.length; i++) { if (choice == allDirectionChars[i]) { return allDirections[i]; } }
        return Direction.UNDEFINED;
    }

    public String toString() { return "Exit"; }
}

class Position 
{
    int x;
    int y;

    Position(int[] xy) { this.x = xy[0]; this.y = xy[1]; }

    Position(int x, int y) { this.x = x; this.y = y; }

    void moveWrap (Direction moveDirection, int xWrap, int yWrap) 
    {
        if (moveDirection == Direction.TO_NORTH) 
        {
            y--; y = (y < 0) ? yWrap-1 : y;   
        }
        else if (moveDirection == Direction.TO_EAST) 
        {
            x++; x = (x == xWrap) ? 0 : x;
        }
        else if (moveDirection == Direction.TO_SOUTH) 
        {
            y++; y = (y == yWrap) ? 0 : y;
        }
        else if (moveDirection == Direction.TO_WEST) 
        {
            x--; x = (x < 0) ? xWrap-1 : x;
        }
        else { return; }
    }

    boolean equals(Position p) { if ((this.x == p.x) && this.y == p.y) { return true; } return false; }
}

class House_ec22845 implements Visitable {
    public static <T> void pr (T m) { System.out.print(m); }
    public static <T> void prln (T m) { System.out.println(m); }

    static <T> void print2DArray (T[][] array) 
    {
        for (int y=0; y<array.length; y++) {
            for (int x=0; x<array[y].length; x++) { pr(String.format("%-30s", array[y][x].toString())); }
            prln("\n\n");
        }
    }

    static House_ec22845 generateEmptyHouse (int houseX, int houseY) { return new House_ec22845(houseX, houseY); }

    static Room[][] shuffleAndBuildHouseRooms (int houseX, int houseY, Room[] rooms) 
    {
        if (rooms.length > (houseX * houseY)) { return null; } // If there are too many rooms for the designated house, return null

        Room[][] roomLayout = new Room[houseY][houseX];
        
        Room[] flattenedHouseRooms = new Room[houseX*houseY];

        for (int i=0; i<flattenedHouseRooms.length; i++) 
        { 
            if (i < rooms.length) { flattenedHouseRooms[i] = rooms[i]; }
            else { flattenedHouseRooms[i] = new Corridor(); }
        }

        Random rnd = new Random(); //SHUFFLE ROOMS AND CORRIDORS
        for (int i=0; i<flattenedHouseRooms.length; i++) 
        {
            int swapI = rnd.nextInt(flattenedHouseRooms.length);
            Room temp = flattenedHouseRooms[swapI];
            flattenedHouseRooms[swapI] = flattenedHouseRooms[i];
            flattenedHouseRooms[i] = temp;
        }

        // Build 2D house array with 1D room array and houseX (width)
        for (int i=0; i<flattenedHouseRooms.length; i++) { roomLayout[(int)(i/houseX)][i%houseX] = flattenedHouseRooms[i]; }

        return roomLayout;
    }

    static Room randomRoomFromContributions () 
    {
        Random rnd = new Random();

        String[] fullRoomList = Contributions.getRoomUsernames();
        return Contributions.newRoomByUsername(fullRoomList[rnd.nextInt(fullRoomList.length)]);
    }


    Room[][] houseRooms;
    static Entrance roomEntrance = new Entrance();
    static Exit roomExit = new Exit(Direction.TO_NORTH);
    
    House_ec22845(int houseX, int houseY) { this.houseRooms = new Room[houseY][houseX]; }

    House_ec22845() 
    { 
        final int houseX = 6;
        final int houseY = 5;

        Room[] chosenRooms = new Room[]{new Room_ec22845(), new Room_ec22431(), 
                                        new Room_ex21626(), new Room_ec19389(), 
                                        roomEntrance, roomExit};

        Room[] rooms = new Room[10];

        for (int i=0; i<rooms.length; i++) 
        {
            if (i >= chosenRooms.length) { rooms[i] = randomRoomFromContributions(); }
            else { rooms[i] = chosenRooms[i]; }
        }

        this.houseRooms = shuffleAndBuildHouseRooms (houseX, houseY, rooms);
    }

    public Direction visit(Visitor visitor, Direction entranceDirection) 
    {
        Position entrance = new Position(getRoomCoordinates(roomEntrance));
        Position exit = new Position(getRoomCoordinates(roomExit));

        Position visitorPosition = new Position(entrance.x, entrance.y);

        boolean exitCondition = false;
        Direction leaveDirection = Direction.UNDEFINED;

        while (!exitCondition) 
        {
            //print2DArray(houseRooms);
            //prln("" + visitorPosition.x + "," + visitorPosition.y);
            leaveDirection = houseRooms[visitorPosition.y][visitorPosition.x].visit(visitor, entranceDirection);

            if (visitorPosition.equals(exit) && leaveDirection == roomExit.exitDirection) { exitCondition = true; }

            visitorPosition.moveWrap(leaveDirection, houseRooms[0].length, houseRooms.length);
        }

        return leaveDirection;
    }

    public int[] getRoomCoordinates (Room targetRoom) 
    {
        for (int y=0; y<houseRooms.length; y++) {
            for (int x=0; x<houseRooms[y].length; x++) { if (houseRooms[y][x] == targetRoom) { return new int[]{x,y}; } }
        }

        return new int[]{-1,-1};
    }
}

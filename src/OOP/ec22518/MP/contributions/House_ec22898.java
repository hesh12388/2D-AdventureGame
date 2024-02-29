package OOP.ec22518.MP.contributions;

class House_ec22898 implements Visitable {
    static class MyVisitor
    {
        Visitor v;
        int location;
        MyVisitor(Visitor v, int location)
        {
            this.v = v;
            this.location = location;
        }

        //println but makes it easier so, I don't have to pass visitor each time I need to print something to the user...
        void tell(String text) {v.tell(text);}

        void tell(int text)
        {
            v.tell("" + text);
        }

        // Returns visitor's choice.
        char getChoice(String text, char[] inputArray)
        {
            return v.getChoice(text, inputArray);
        }

        // Returns true if item is accepted.
        boolean giveItem(Item itemGivenToVisitor)
        {
            return v.giveItem(itemGivenToVisitor);
        }

        // Returns true if visitor has the identical (==) item.
        boolean hasIdenticalItem(Item itemToCheckFor)
        {
            return v.hasIdenticalItem(itemToCheckFor);
        }

        // Returns true if visitor has an equal item (same name).
        boolean hasEqualItem(Item itemToCheckFor)
        {
            return v.hasEqualItem(itemToCheckFor);
        }

        //this method gives gold to the visitor... presumably from the house pool of gold
        void giveGold(int numberOfPiecesToGive)
        {
            v.giveGold(numberOfPiecesToGive);
        }

        // Returns number of pieces actually obtained from visitor.
        int takeGold(int numberOfPiecesToTake)
        {
            return v.takeGold(numberOfPiecesToTake);
        }
    }


    //this section is to introduce a way to nav all the directions while travelling in the house...
    static class Surroundings
    {
        int north;
        int south;
        int east;
        int west;

        Surroundings(int n, int s, int e, int w)
        {
            this.north = n;
            this.south = s;
            this.east = e;
            this.west = w;
        }
    }
    //location surrounding rooms
    Surroundings prevsurrounds;//if a user wish's to go back current is made to prev.
    Surroundings surroundings;//kitchen and hall are going to be a bit unique

    //there should be a way to backtrack all the way but dont have time for that rn...

    //updates the surroundings...
    public void updateSurroundings()
    {
        int localLocInt = currentlocationInteger;
        String localLocString = currentlocationString;



    }

    private boolean exited;

    private Direction lastreturnDirection;
    boolean[] lights_on;
    boolean[] visited;
    static char yes = 'y';
    static char no = 'n';
    String[] locations;
    String currentlocationString;
    int currentlocationInteger;
    String inputError;
    char choice;
    Room[] rooms;

    House_ec22898()
    {
        rooms = new Room[5];
        rooms[0] = new Room_ec221003();
        rooms[1] = new Room_ec22898();
        rooms[2] = new Room_ec22557();
        rooms[3] = new Room_ec22923();
        rooms[4] = new Room_ec22569();

        prevsurrounds = new Surroundings(0, 11, 1, 2);
        surroundings = new Surroundings(0, 11, 1, 2);

        exited = false;

        //again not used yet but will implement...
        lights_on = new boolean[6];
        lights_on[0] = false;//hallway near entrance
        lights_on[1] = false;//right wing
        lights_on[2] = false;//left wing
        lights_on[3] = false;//bathroom
        lights_on[4] = false;//kitchen
        lights_on[5] = false;//hall
                             //Room_ec221003
                             //Room_ec22898
                             //Room_ec22557
                             //Room_ec22895
                             //Room_ec22569
        //the rooms lights are not encoded into the house as they are separate entity,
        //neither are they encoded into a room they just exist for asthetic purposes...




        // dont use it for this version...
        visited = new boolean[12];
        visited[0] = false;//outside
        visited[1] = false;//Room_ec221003
        visited[2] = false;//Room_ec22898
        visited[3] = false;//Room_ec22557
        visited[4] = false;//Room_ec22895
        visited[5] = false;//Room_ec22569
        visited[6] = false;//bathroom
        visited[7] = false;//kitchen
        visited[8] = false;//living room
        visited[9] = false;//right wing
        visited[10] = false;//left wing
        visited[11] = false;//hallway near entrance


        locations = new String[12];
        locations[0] = "outside";//only for entrance...
        locations[1] = "Room_ec221003";
        locations[2] = "Room_ec22898";
        locations[3] = "Room_ec22557";
        locations[4] = "Room_ec22923";
        locations[5] = "Room_ec22569";
        locations[6] = "bathroom";
        locations[7] = "kitchen";
        locations[8] = "living room";
        locations[9] = "hallway near entrance";
        locations[10] = "right wing";
        locations[11] = "left wing";

        inputError = "Input not recognised!! moving on";

        choice = 'l';
    }

    public Direction visit(Visitor oldvisitor, Direction d)
    {
        MyVisitor newVisitor = new MyVisitor(oldvisitor, 0);

        //mistake with it so im gonna try and not...
        //intro to the house
//        newVisitor.tell("You've just opened the door into a dark house... you see a switch to the left of you...");
//        checkLightsStatus(newVisitor);

        while(!exited)
        {
            callLocationMethod(newVisitor, oldvisitor);
        }

        if(lastreturnDirection == null)
        {
            return Direction.TO_SOUTH;
        }
        else
        {
            return lastreturnDirection;
        }
    }


    //just to make myself clear of how things are working it is assumed that once the visitor has visiter a room any particular room
    //they shall return back to the entrance so this way for this version of the code everything is referenced off of the entrance...
    public void callLocationMethod(MyVisitor newVisitor, Visitor oldVisitor)//this method must call the house that is the next location's and then save the return direction entered by each
    {
        Direction local_returnDirection = lastreturnDirection;
        newVisitor.tell("a. Room_ec221003");
        newVisitor.tell("b. Room_ec22898");
        newVisitor.tell("c. Room_ec22557");
        newVisitor.tell("d. Room_ec22923");
        newVisitor.tell("e. Room_ec22589");
        newVisitor.tell("f. Bathroom");
        newVisitor.tell("g. Kitchen");
        newVisitor.tell("h. Living room");
        newVisitor.tell("i. Exit the House_ec22898");
        choice = newVisitor.getChoice("Choose option from a - i: ", new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'});

        String entering = "you are now opening the door entering the room...";

        switch (choice)
        {
            case 'a':
                newVisitor.tell("This room was to the west of the entrance, " + entering);
                local_returnDirection = rooms[0].visit(oldVisitor, Direction.opposite(local_returnDirection));
                newVisitor.location = 1;
                newVisitor.v = oldVisitor;
                break;
            case 'b':
                newVisitor.tell("This room was to the east of the entrance, " + entering);
                local_returnDirection = rooms[1].visit(oldVisitor, Direction.opposite(local_returnDirection));
                newVisitor.location = 2;
                newVisitor.v = oldVisitor;
                break;
            case 'c':
                newVisitor.tell("You walk further down the entrance and you find this room on your left," + entering);
                local_returnDirection = rooms[2].visit(oldVisitor, Direction.opposite(local_returnDirection));
                newVisitor.location = 3;
                newVisitor.v = oldVisitor;
                break;
            case 'd':
                newVisitor.tell("You walk further down the hallway turn left and see a room on the right you also see" +
                        " a room on the left but you decide go into the room on the right " + entering);
                local_returnDirection = rooms[3].visit(oldVisitor, Direction.opposite(local_returnDirection));
                newVisitor.location = 4;
                newVisitor.v = oldVisitor;
                break;
            case 'e':
                newVisitor.tell("You walk further down the hallway turn right and see a room on the left you also see" +
                        " a room on the right but you decide to go into the room on the left " + entering);
                local_returnDirection = rooms[4].visit(oldVisitor, Direction.opposite(local_returnDirection));
                newVisitor.location = 5;
                newVisitor.v = oldVisitor;
                break;
            case 'f':
                newVisitor.tell("You walk down the hall way and after a passing a pair of rooms (on the either side) " +
                        "you encounter a kitchen and a living room you wish to enter the living room so," + entering);
                local_returnDirection = DummyRoom(newVisitor);
                newVisitor.location = 6;
                newVisitor.v = oldVisitor;
                break;
            case 'g':
                newVisitor.tell("You walk down the hall way and after a passing a pair of rooms (on the either side) " +
                        "you encounter a kitchen and a living room you wish to enter the kitchen so," + entering);
                local_returnDirection = DummyRoom(newVisitor);
                ;
                newVisitor.location = 7;
                newVisitor.v = oldVisitor;
                break;
            case 'h':
                newVisitor.tell("You walk down the hall way and as you walk to the junction and walk further straight down that hallway" +
                        " you see a toilet on the right, " + entering);
                local_returnDirection = DummyRoom(newVisitor);
                newVisitor.location = 8;
                newVisitor.v = oldVisitor;
                break;
            case 'i':
                newVisitor.tell("You have wished to exit the house by selecting this option");
                char minichoice = newVisitor.getChoice("Do you wish to continue (Y)es or (N)o", new char[]{'Y', 'N'});

                if(minichoice == 'Y')
                {
                    newVisitor.tell("You've now exited the house thanks for visiting hope to see you again ^_^");
                    exited = true;
                }
                else
                {
                    newVisitor.tell("No worries you have revoked your exit from this house (maybe forever (scary horron laugh...)) ");
                }
                break;
            default:
                newVisitor.tell(inputError);
                break;
        }

        newVisitor.tell("you now returned to the entrance of the House_ec22898 from " + locations[newVisitor.location] + " regardless of which direction you've exited the " + locations[newVisitor.location] + " from.");

        lastreturnDirection = local_returnDirection;
    }


    Direction DummyRoom(MyVisitor newVisitor)
    {
        //temp
        newVisitor.tell("this is a dummy state (maybe automata still going though my head) but this is temporary room ");
        //temp code ends

        //setting default to SOUTH so will not need an if statement for that case...
        Direction direction = Direction.TO_SOUTH;
        String dString = "SOUTH";

        //this lets the visitor choose which direction they want to go
        char choice = newVisitor.getChoice("What direction would you like to go next? ('N' or 'S' or 'W' or 'E')",
                new char[]{'N', 'S', 'E', 'W'});

        //different choices will give differnt results...
        if (choice == 'N')
        {
            dString = "NORTH";
            direction = Direction.TO_NORTH;
        }
        else if (choice == 'W')
        {
            dString = "WEST";
            direction = Direction.TO_WEST;
        }
        else if (choice == 'E')
        {
            dString = "EAST";
            direction = Direction.TO_EAST;
        }

        newVisitor.tell("Being sent through " + dString + " exi" + "t");

        //returns south as in default
        return direction;
    }



//
//    public void updateLocation()//this method is attempted when the visitor is moving from one location to another location...
//    {
//        String prevLocation = currentlocationString;
//        String nextLocation;
//
//
//
//    }
//

    //there is an error with it but we gonna ignore for now...
    //methid checks if the lights are on in the particular location and asks if they wanna switch the lights off or on depending on the state
    public void checkLightsStatus(MyVisitor newVisitor)//method is called to check the status of the lights when entering a location
    {
        String a = "The lights in the location: " + currentlocationString;
        boolean lightsStatus = lights_on[getlocationPos(currentlocationString)];
        String b = "are currently: ";

        if (!lightsStatus)
        {
            b += "on";
        }
        else
        {
            b += "off";
        }

        newVisitor.tell(a + b);
    }

    //checking which light switch to edit...
    public void lightswitch(MyVisitor newVisitor)//this method is only called if the user wishes to change the lights even tbh
    {
        //ask the visitor for the input and do appropriately
        //v.tell("Would you like to turn on the lights in the " + currentlocation);
        choice = newVisitor.getChoice("on (1) or off (2)", new char[]{'1','2'});

        if(choice == '1')
        {
            lights_on[getlocationPos(currentlocationString)] = true;
        }
        else if (choice == '2')
        {
            lights_on[getlocationPos(currentlocationString)] = false;
        }
        else
        {
            newVisitor.tell(inputError);
        }
    }

    public int getlocationPos(String l)
    {
        for(int i = 0; i < 7; i++)
        {
            if(locations[i].equals(l))
            {
                return i;
            }
        }

        return -1;
    }
}

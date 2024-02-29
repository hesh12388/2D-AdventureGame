package OOP.ec22518.MP.contributions;

class House_ec22859 implements Visitable {

    Room[][][] rooms = new Room[2][2][2];
    char[] exploreOptions = {'a', 'b', 'c'};
    char[] elevatorOptions = {'a', 'b', 'c'};
    char[] elevatorOptionsDown = {'d', 'e'};
    char[] elevatorOptionsUp = {'u', 'e'};
    Room defaultStart;
    char[] directions = {'n', 's', 'e', 'w'};
    int y = 0; // Floor tracker.
    int x = 0; // x-dimension tracker.
    int z = 0; // z-dimension tracker.

    House_ec22859(){
        rooms[0][0][0] = new Room_ec22859();
        rooms[0][0][1] = new Room_ec22771();
        rooms[0][1][0] = new Room_ec22551();
        rooms[0][1][1] = new Room_ec22553();
        rooms[1][0][0] = new Room_ec22906();
        rooms[1][0][1] = new Room_ec22761();
        rooms[1][1][0] = new Room_ec22859(); // need
        rooms[1][1][1] = new Room_ec22859(); // need 
        this.defaultStart = rooms[0][0][0];
    }
    
    public Direction visit(Visitor visitor, Direction comingFrom){
        char exploreChoice = 'a';
        char elevatorChoice = 'a';
        Direction goingTo;
        Direction curDir;
        

        visitor.tell("Welcome to Sep's House.");
        visitor.tell("Explore the rooms, interact with things, explore each floor and make money!");
        visitor.tell("You begin at Floor 1, Room 1.");

        curDir = defaultStart.visit(visitor, comingFrom);

        while(exploreChoice != 'c'){
            askExplore(visitor);
            exploreChoice = visitor.getChoice(" ", exploreOptions);
            visitor.tell("( " + y + "," + x + "," + z + " )");
            if(exploreChoice == 'a'){

                // Wrap around
                if(z == 1 && Direction.opposite(curDir) == Direction.FROM_NORTH){
                    z=0;
                }
                else if(z == 0 && Direction.opposite(curDir) == Direction.FROM_SOUTH){
                    z = 1;
                }
                else if(x==1 && Direction.opposite(curDir) == Direction.FROM_EAST){
                    x = 0;
                }
                else if(x==0 && Direction.opposite(curDir) == Direction.FROM_WEST){
                    x = 1;
                }

                // Regular
                else if(z == 0 && curDir == Direction.TO_NORTH){
                    z=1;
                }
                else if(z == 1 && curDir == Direction.TO_SOUTH){
                    z = 0;
                }
                else if(x==0 && curDir == Direction.TO_EAST){
                    x = 1;
                }
                else if(x==1 && curDir == Direction.TO_WEST){
                    x = 0;
                }

                visitor.tell("( " + y + "," + x + "," + z + " )");
                curDir = rooms[y][x][z].visit(visitor, curDir);
            }

            else if(exploreChoice == 'b'){
                askElevator(visitor, y);
                if(y == 0){
                    elevatorChoice = visitor.getChoice(" ", elevatorOptionsUp);
                }
                else if (y == 1){
                    elevatorChoice = visitor.getChoice(" ", elevatorOptionsDown);
                }
                else{
                    elevatorChoice = visitor.getChoice(" ", elevatorOptions);
                }

                if(elevatorChoice == 'a' | elevatorChoice == 'u'){
                    y++;
                }
                else if(elevatorChoice == 'b' | elevatorChoice == 'd'){
                    y--;
                }
                else{
                    // Nothing happens
                    visitor.tell("You left the elevator.");
                }
            }

            else if(exploreChoice == 'c'){
                visitor.tell("You left Sep's house!");
            }
        }
        goingTo = leaveHouse(visitor);
        return goingTo;
    }

    Direction leaveHouse(Visitor v){
        char chosenDir = v.getChoice("Where are you off to? (n)orth, (s)outh, (e)ast, (w)est?", directions);
        Direction sendTo = Direction.UNDEFINED;

        if(chosenDir == 'n'){
            v.tell("you leave from north!");
            sendTo = Direction.TO_NORTH;
        }
        else if(chosenDir == 's'){
            v.tell("you leave from south!");
            sendTo = Direction.TO_SOUTH;
        }
        else if(chosenDir == 'e'){
            v.tell("you leave from east!");
            sendTo = Direction.TO_EAST;
        }
        else if(chosenDir == 'w'){
            v.tell("you leave from west!");
            sendTo = Direction.TO_WEST;
        }

        return sendTo;
    }
    
    void askExplore(Visitor visitor){
        visitor.tell("What would you like to do?");
        visitor.tell(exploreOptions[0] + ") Enter the next room");
        visitor.tell(exploreOptions[1] + ") Enter the elevator");
        visitor.tell(exploreOptions[2] + ") Leave the house");
        return;
    }

    void askElevator(Visitor visitor, int floor){
        visitor.tell("(Elevator Floor "+ floor +") What would you like to do?");
        if(floor == 1){
            visitor.tell(elevatorOptionsDown[0] + ") Go down a floor");
            visitor.tell(elevatorOptionsDown[1] + ") Leave the elevator");
        }
        else if(floor == 0){
            visitor.tell(elevatorOptionsUp[0] + ") Go up a floor");
            visitor.tell(elevatorOptionsUp[1] + ") Leave the elevator");
        }
        else{
            visitor.tell(elevatorOptions[0] + ") Go up a floor");
            visitor.tell(elevatorOptions[1] + ") Go down a floor");
            visitor.tell(elevatorOptions[2] + ") Leave the elevator");
        }
    }
}

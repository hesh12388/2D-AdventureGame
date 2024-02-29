package OOP.ec22518.MP.contributions;
/*

    Room Layout
     _______________
    |       |       |
    |       |       |
    |   R1      R2  |
    |       |       |
    |___  __|___  __|
    |       |       |
    |           R3  |
    |   R4  |___ ___|
    |___  __| Exit  |
    |_______ _______|
 */


class House_ec22771 implements Visitable {
    Room room1;
    Room room2;
    Room room3;
    Room room4;
    int currentRoom;

    House_ec22771(){
        room1 = new Room_ec22771();
        room2 = new Room_ec22551();
        room3 = new Room_ec22859();
        room4 = new Room_ec22906();
        currentRoom = 1;
    }
    
    public Direction visit(Visitor v, Direction direction) {
        boolean exitFound = false;
        Room [] rooms = new Room []{room1, room2, room3, room4};

        v.tell("You wake up in an unfamiliar building.");
        v.tell("You remember walking an empty alleyway at night. A hooded man was walking down the opposite direction, who bumped into you with force");
        v.tell("'Watch where you are walking' you cried before the man turned around, and knocked you out with one swift punch");
        v.tell("The last thing you remember hearing is the man whisper in his dark melancholic voice 'I don't pity you for the place you're about to be'");
        
        while (!exitFound){
            if (currentRoom == 5){
                exitFound = true;
                v.tell("You found the door. You reach out to grab the slimy doorknob and get hit with an electric shock");
                v.tell("The doorknob is hard to turn, but you finally get the door to open");
                v.tell("You walk outside, and the smell of freedom hits you. You start running and never look back as you remember all the horrors inside the room");
            } else{
                direction = rooms[currentRoom-1].visit(v, direction);
                if (currentRoom == 1){
                    if (direction == Direction.TO_EAST){
                        v.tell("You open the door to East and end up in room 2");
                        currentRoom = 2;
                    } else if(direction == Direction.TO_NORTH){
                        v.tell("You open the door to the North, but you find a brick wall. Instead you decide to leave to the South");
                        v.tell("You end up in room 4");
                        currentRoom = 4;
                    } else if (direction == Direction.TO_WEST){
                        v.tell("You open the door to the West, but you find a brick wall. Instead you decide to leave to the East");
                        v.tell("You end up in room 2");
                        currentRoom = 2;
                    } else if (direction == Direction.TO_SOUTH){
                        v.tell("You open the door to South and end up in room 4");
                        currentRoom = 4;
                    }
                } else if (currentRoom == 2){
                    if (direction == Direction.TO_EAST){
                        v.tell("You open the door to the East, but you find a brick wall. Instead you decide to leave to the Weast");
                        v.tell("You end up in room 1");
                        currentRoom = 1;
                    } else if(direction == Direction.TO_NORTH){
                        v.tell("You open the door to the North, but you find a brick wall. Instead you decide to leave to the South");
                        v.tell("You end up in room 3");
                        currentRoom = 3;
                    } else if (direction == Direction.TO_WEST){
                        v.tell("You open the door to East and end up in room 1");
                        currentRoom = 1;
                    } else if (direction == Direction.TO_SOUTH){
                        v.tell("You open the door to South and end up in room 3");
                        currentRoom = 3;
                    }
                } else if (currentRoom == 3){
                    if (direction == Direction.TO_EAST){
                        v.tell("You open the door to the East, but you find a brick wall. Instead you decide to leave to the West");
                        v.tell("You end up in room 4");
                        currentRoom = 4;
                    } else if(direction == Direction.TO_NORTH){
                        v.tell("You open the door to North and end up in room 2");
                        currentRoom = 2;
                    } else if (direction == Direction.TO_WEST){
                        v.tell("You open the door to West and end up in room 4");
                        currentRoom = 4;
                    } else if (direction == Direction.TO_SOUTH){
                        v.tell("You open the door to South and end up in an unnumbered Room");
                        v.tell("WHAT IS THIS ROOM?");
                        currentRoom = 5;
                    }

                } else if (currentRoom == 4){
                    if (direction == Direction.TO_EAST){
                        v.tell("You open the door to East and end up in room 3");
                        currentRoom = 3;
                    } else if(direction == Direction.TO_NORTH){
                        v.tell("You open the door to North and end up in room 1");
                        currentRoom = 1;
                    } else if (direction == Direction.TO_WEST){
                        v.tell("You open the door to West and end up in room 3");
                        currentRoom = 3;
                    } else if (direction == Direction.TO_SOUTH){
                        v.tell("You open the door to South and end up in an unnumbered Room");
                        v.tell("WHAT IS THIS ROOM?");
                        currentRoom = 5;
                    }
                } else {
                    v.tell("Something went wrong, you ended up back in the first room");
                    direction = room1.visit(v, direction);
                    currentRoom = 1;
                }
            }
        }
        return direction;
    }
    
}

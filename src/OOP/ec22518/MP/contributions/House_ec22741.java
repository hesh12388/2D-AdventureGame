package OOP.ec22518.MP.contributions;

// Assignment 5
class House_ec22741 implements Visitable {
    private Room [] room_array;

    House_ec22741(Visitor v, Direction d)
    {
        room_array = new Room[5];
        room_array[0] = new Room_ec22828();
        room_array[1] = new Room_ec22860();
        room_array[2] = new Room_ec22660();
        room_array[3] = new Room_ec221017();
        room_array[4] = new Room_ec22741();
    }
    public Direction visit(Visitor v, Direction d)
    {
        // variables
        Room roomToVisit = room_array[0];
        boolean leaveHouse = false;
        boolean inHallway = false;
        char[] hallwayChoices = {'A', 'B', 'C', 'D'};

        // determining which direction visitor arrives from
        // then depending on direction, enters another room in a house
        // starting point

        //v.tell(d.toString);
        if (d == Direction.FROM_SOUTH)
        {
            inHallway = true;
        }
        else
        {
            if (d == Direction.FROM_WEST)
            {
                roomToVisit = room_array[1];
            }
            else if (d == Direction.FROM_NORTH)
            {
                roomToVisit = room_array[3];
            }
            else if (d == Direction.FROM_EAST)
            {
                roomToVisit = room_array[0];
            }
            d = roomToVisit.visit(v, d);
        }
        
        // while loop that makes visitor keep visiting rooms
        // break condition: must be in room_array[4] AND d must be equal to Direction.TO_NORTH
        while (leaveHouse == false)
        {
            if (roomToVisit == room_array[4] && d == Direction.TO_NORTH)
            {
                leaveHouse = true;
            }

            // visitor is in the hallway
            else if (inHallway == true)
            {
                v.tell("You are in the hallway");
                v.tell("There are four doors, labelled 'A', 'B', 'C' and 'D'");
                char choice = v.getChoice("Which door do you choose: A, B, C or D?", hallwayChoices);
                if (choice == 'A')
                {
                    roomToVisit = room_array[2];
                    d = Direction.TO_WEST;
                }
                else if (choice == 'B')
                {
                    roomToVisit = room_array[1];
                    d = Direction.TO_WEST;
                }
                else if (choice == 'C')
                {
                    roomToVisit = room_array[3];
                    d = Direction.TO_NORTH;
                }
                else if (choice == 'D')
                {
                    roomToVisit = room_array[0];
                    d = Direction.TO_EAST;
                }

                inHallway = false;
                d = roomToVisit.visit(v, d);
            }


            // visitor leaves through garden
            else if ((roomToVisit == room_array[2] && d == Direction.TO_WEST) || (roomToVisit == room_array[4] && d == Direction.TO_EAST) || (roomToVisit == room_array[3] && d == Direction.TO_WEST))
            {
                v.tell("You see a window and climb out to escape the house");
                v.tell("You venture into the deep dark woods...");
                leaveHouse = true;
            }

            // locked exits
            else if ((roomToVisit == room_array[3] && d == Direction.TO_NORTH) || (roomToVisit == room_array[1] && d == Direction.TO_WEST))
            {
                v.tell("You encounter a door but sadly it is locked");
                v.tell("You decide to go back to the hallway");
                inHallway = true;
            }

            // go to room_array[4]
            else if ((roomToVisit == room_array[0] && d == Direction.TO_NORTH) || (roomToVisit == room_array[3] && d == Direction.TO_EAST))
            {
                roomToVisit = room_array[4];
                d = roomToVisit.visit(v, d);
            }

            // go to room_array[0]
            else if (roomToVisit == room_array[4] && d == Direction.TO_SOUTH)
            {
                roomToVisit = room_array[0];
                d = roomToVisit.visit(v, d);
            }

            // go to room_array[3]
            else if (roomToVisit == room_array[4] && d == Direction.TO_WEST)
            {
                roomToVisit = room_array[3];
                d = roomToVisit.visit(v, d);
            }
        }
        return d;
    }
}

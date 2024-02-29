package OOP.ec22518.MP.contributions;

class House_ec22872 implements Visitable {
    Room nw;
    Room ne;
    Room sw;
    Room se;

    House_ec22872() {
        nw = new Room_ec22572(); //0
        ne = new Room_ec22891(); //1
        sw = new Room_ec22872(); //2
        se = new Room_ec22882();//3
    }
    public Direction visit(Visitor guest, Direction d) {
        d = Direction.FROM_SOUTH;
        guest.tell("You enter the house through the entrance found on the south face of the house");
        guest.tell("You see a long hallway with two doors in front of you.");
        guest.tell("One to your left and one to your right.");
        guest.tell("Which door do you choose?");
        char[] entry = new char[2];
        entry[0] = 'a';
        entry[1] = 'b';
        char door_choice = guest.getChoice("a) the left door or b) the right door?", entry);
        Direction left;
        int from = -1;
        if(door_choice == 'a')
        {
            guest.tell("You have entered the left door"); //sw
            left = sw.visit(guest, Direction.FROM_EAST);
            from = 2;
        }
        else
        {
            guest.tell("You have entered the right door");
            left = se.visit(guest, Direction.FROM_WEST);
            from = 3;
        }
        boolean exit = false;
        while(!exit){
            if(from == 0)
            {
                if(left.equals(Direction.TO_NORTH))
                {
                    guest.tell("You have left the house");
                    exit = true;
                }
                else if(left.equals(Direction.TO_WEST))
                {
                    guest.tell("You have left the house");
                    exit = true;
                }
                else if(left.equals(Direction.TO_EAST))
                {
                    guest.tell("You have entered a new room");
                    left = ne.visit(guest, left);
                    from = 1;
                }
                else if(left.equals(Direction.TO_SOUTH))
                {
                    guest.tell("You have entered a new room");
                    left = sw.visit(guest, left);
                    from = 2;
                }
                
            }
            else if(from == 1)
            {
                if(left.equals(Direction.TO_NORTH))
                {
                    guest.tell("You have left the house");
                    exit = true;
                }
                else if(left.equals(Direction.TO_EAST))
                {
                    guest.tell("You have left the house");
                    exit = true;
                }
                else if(left.equals(Direction.TO_SOUTH))
                {
                    guest.tell("You have entered a new room.");
                    left = se.visit(guest, left);
                    from = 3;
                }
                else if(left.equals(Direction.TO_WEST))
                {
                    guest.tell("You have entered a new room.");
                    left = nw.visit(guest, left);
                    from = 0;
                }
            }
            else if(from == 2)
            {
                if(left.equals(Direction.TO_NORTH))
                {
                    guest.tell("You have entered a new room.");
                    left = nw.visit(guest, left);
                    from = 0;
                }
                else if(left.equals(Direction.TO_EAST))
                {
                    guest.tell("You have entered the hallway at the start.");
                    char[] hallway = new char[3];
                    hallway[0] = 'a';
                    hallway[1] = 'b';
                    hallway[2] = 'c';
                    door_choice = guest.getChoice("Do yo uwish to go back to the room, go to the other room or leave", hallway);
                    if(door_choice == 'a')
                    {
                        left = sw.visit(guest, Direction.FROM_EAST);
                        from = 2;
                    }
                    else if(door_choice == 'b')
                    {
                        left = se.visit(guest, Direction.FROM_WEST);
                        from = 3;
                    }
                    else
                    {
                        exit = true;
                        left = Direction.FROM_SOUTH;
                    }
                }
                else if(left.equals(Direction.TO_SOUTH))
                {
                    guest.tell("You have left the house");
                    exit = true;
                }
                else if(left.equals(Direction.TO_WEST))
                {
                    guest.tell("You have left the house");
                    exit = true;
                }
            }
            else if(from == 3)
            {
                if(left.equals(Direction.TO_NORTH))
                {
                    guest.tell("You have entered a new room.");
                    left = ne.visit(guest, left);
                    from = 1;
                }
                else if(left.equals(Direction.TO_WEST))
                {
                    guest.tell("You have entered the hallway at the start.");
                    char[] hallway = new char[3];
                    hallway[0] = 'a';
                    hallway[1] = 'b';
                    hallway[2] = 'c';
                    door_choice = guest.getChoice("Do yo uwish to a) go back to the room, b) go to the other room or c) leave", hallway);
                    if(door_choice == 'a')
                    {
                        left = se.visit(guest, Direction.FROM_WEST);
                        from = 3;
                    }
                    else if(door_choice == 'b')
                    {
                        left = se.visit(guest, Direction.FROM_EAST);
                        from = 2;
                    }
                    else
                    {
                        exit = true;
                        left = Direction.FROM_SOUTH;
                    }
                }
                else if(left.equals(Direction.TO_SOUTH))
                {
                    guest.tell("You have left the house");
                    exit = true;
                }
                else if(left.equals(Direction.TO_EAST))
                {
                    guest.tell("You have left the house");
                    exit = true;
                }
            }
        }
        return left;   
    }
}

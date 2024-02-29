package OOP.ec22518.MP.contributions;

class House_ec22520 implements Visitable {
    Room_ec22520 R1 = new Room_ec22520();
    Room_ec22519 R2 = new Room_ec22519();
    Room_ec22772 R3 = new Room_ec22772();
    
    
    public Direction visit(Visitor v, Direction d) {
        v.tell("You have enetered my mysterous mansion, try to escape if you dare!");
        Room1(v,d);
        v.tell("You got away this time but you wont be so lucky next");         
        return d;
    }
    public void Room1(Visitor v, Direction d)
    {
        Direction Exitd = R1.visit(v,d);
        if (Exitd == Direction.TO_NORTH)
        {
                v.tell("The wall infront blocks the way");
                char choice = v.getChoice("Which way would you like to go?(S/W/E)",new char[]{'S','W','E'});
                if (choice == 'S')
                {
                    
                    v.tell("You took the easy way, you shall now pay the price");
                    v.tell("I will now take 5 gold");
                    v.takeGold(5);
                    return;
                    
                }
                else if (choice =='W')
                {
                    Exitd = Direction.TO_WEST;
                    Room3(v,Exitd);
                }
                else if (choice =='E')
                {
                    Exitd = Direction.TO_EAST;
                    Room2(v,Exitd);
                    
                }
        }
        else if(Exitd == Direction.TO_EAST)
        {
            Room2(v,Exitd);
        }
        else if (Exitd == Direction.TO_WEST)
        {
            Room3(v,Exitd);
        }
        else if (Exitd == Direction.TO_SOUTH)
        {
            v.tell("You took the easy way, you shall now pay the price");
            v.tell("I will now take 5 gold");
            v.takeGold(5);
            return;
        }
    }
      
    public void Room2(Visitor v, Direction d)
    {
        Direction Exitd = R2.visit(v,d);
        if (Exitd == Direction.TO_NORTH)
        {
            v.tell("The dark windy hallway takes you to the next room");
            Room3(v,Exitd);
        }
        if (Exitd == Direction.TO_SOUTH)
        {
            v.tell("The dark windy hallway takes you to the next room");
            Room3(v,Exitd);
        }
        if (Exitd == Direction.TO_EAST)
        {
            return;
        }
        if (Exitd == Direction.TO_WEST)
        {
            v.tell("So you decide to go back");
            Room1(v,Exitd);
        }
    
    }
    public void Room3(Visitor v, Direction d)
    {
        Direction Exitd = R3.visit(v,d);
        if (Exitd == Direction.TO_NORTH)
        {
            v.tell("The dark windy hallway takes you to the next room");
            Room2(v,Exitd);
        }
        else if (Exitd == Direction.TO_SOUTH)
        {
            v.tell("The dark windy hallway takes you to the next room");
            Room2(v,Exitd);
        }
        else if (Exitd == Direction.TO_WEST)
        {
            return;
        }
        else if (Exitd == Direction.TO_EAST)
        {
            Room1(v,Exitd);
        }
    }
}

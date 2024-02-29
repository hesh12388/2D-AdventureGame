package OOP.ec22518.MP.contributions;

class House_ec221006 implements Visitable {
    static class RoomInHouse 
    {
        Room room;
        boolean visited = false;
        
        RoomInHouse(Room r) {room = r;}
        
        Direction visit(Visitor v,Direction d) 
        {
            visited = true;
            return room.visit(v,d);   
        }
        
    }
    
    RoomInHouse[] allRooms;
    static final Item Compass = new Item("Compass");
    
    House_ec221006()
    {   
        RoomInHouse[] temp = {new RoomInHouse(new Room_ec221006()), 
                                    new RoomInHouse(new Room_ec22435()), 
                                    new RoomInHouse(new Room_ec22415()), 
                                    new RoomInHouse(new Room_ec221148())};
        allRooms = temp;                            
    }    
    
    public Direction visit(Visitor v, Direction d) 
    {
        v.tell("This is the House of The Wicked Directions .... This is a weird house....");
        v.tell("If You Head North, You enter the North Room......No matter where you are...Even the North Room itself....");
        v.tell("If You Head East, You enter the East Room......No matter where you are...Even the East Room itself....");
        v.tell("If You Head West...........Well You get the Gist........");
        v.tell("Visit each room at least once, only then do you get out.........If not, then stay forever......");
        
        do{
            d = allRooms[indexFromDir(v,d)].visit(v, d);
        }while(!visitChecker(allRooms));
        
        v.tell("Now that you have been to each room.........You enter the hall.");
        char[] choices = {'y'};
        char choice = v.getChoice("In the otherwise empty room, there lies a little leather pouch. Would you like to pick it up and see what lies within?(y / any other key)", choices);

        if(choice == 'y')
        {
            if(v.giveItem(Compass))
            {
                v.tell("May this compass help you in your future endeavours.");

                char[] options = {'n', 'e', 'w', 's'};
                char option = v.getChoice("In which direction would you like to leave the house? North? East? West? South? (n/e/w/s)", options);
                        v.tell("This House was made using the rooms of usernames - ec221006(North), ec22435(East), ec22415(West) & ec221148(South).");
        v.tell("Well then nothing left to do here......GoodBye.");

                if(option == 'n'){return Direction.TO_NORTH;}
                else if(option == 'e'){return Direction.TO_EAST;}
                else if(option == 'w'){return Direction.TO_WEST;}
                else{return Direction.TO_SOUTH;}
            }
        }
        v.tell("This House was made using the rooms of usernames - ec221006(North), ec22435(East), ec22415(West) & ec(South).");
        v.tell("Well then nothing left to do here......GoodBye.");
                    
        return d;
    }
                                                        
    private int indexFromDir(Visitor v, Direction d)
    {
        if(d == Direction.TO_NORTH)
        {
            v.tell("You entered the North Room.");
            return 0;
        }
        else if(d == Direction.TO_EAST)
        {
            v.tell("You entered the East Room.");
            return 1;
        }
        else if(d == Direction.TO_WEST)
        {
            v.tell("You entered the West Room.");
            return 2;
        }
        else if(d == Direction.TO_SOUTH)
        {
            v.tell("You entered the South Room.");
            return 3;
        }
        else
        {
            v.tell("You who comes from the way unknown! Which room to enter - The choice, your own.....");
            v.tell("Before you lie 4 rooms.");

            char[] choices = {'n', 'e', 'w', 's'};
            char choice = v.getChoice("Which direction do you go? North? East? West? South? (n/e/w/s)", choices);

            if(choice == 'n'){return 0;}
            else if(choice == 'e'){return 1;}
            else if(choice == 'w'){return 2;}
            else{return 3;}
        }
    }
    
    private boolean visitChecker(RoomInHouse[] allRooms)
    {
        for(int i = 0; i<allRooms.length; i++)
        {
            if(allRooms[i].visited == false)
            {
                return false;
            }
        }
        
        return true;
    }
}

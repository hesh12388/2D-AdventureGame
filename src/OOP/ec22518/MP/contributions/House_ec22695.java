package OOP.ec22518.MP.contributions;

import java.util.Random;
class House_ec22695 implements Visitable {

    private static boolean WindowOpened = false;
    private static boolean FoundChest = false;
    private static boolean ChestOpened = false;
    private static boolean FirstTimeInHouse = true;
    private static boolean PreviousTurnStarted_FromUpstairs = false;
    private static boolean FirstTimeUpstairs = true;

    private static Direction HouseDirection = Direction.FROM_SOUTH;
    private static Room[] House_Room = {new Room_ec22695(), new Room_ec22889(),new Room_ec221148(), new Room_ec22770(),new Room_ec221136(), new Room_ec221156()};


    //two rooms downstarts and 4 rooms upstairs.

    /* ------------------- House Layout ------------------- */ 
    /*Entrance (darkend) two rooms left right. Window with curtain closed and chest (only visisble when window curtain is open*/ 

    /* Walking ahead leads to the stairs */ 
    /* Upstairs you go to room ec 221148 */
    /* There is a single hall leads to one of the 3 rooms. Rooms it leads to are randomn per turn*/ 



    public Direction visit(Visitor visitor, Direction direction) 
    {

        visitor.tell("Welcome to the abandoned manor once apart of Queen Mary the University of London");
        visitor.tell("With QMUL relocating to a new location, the vinicty was first reconviened as a manor, but now ... the building remains lost to a time of way back when.");

        visitor.tell("You young travellor, do not see a barren building rooting endless time, but something that meets more than the eye can see.");
        visitor.tell("Late at night, proceeding with caution you enter OLD QMUL seeing what secrets you can uncover");


        DownStairsHallway(visitor);

        visitor.tell("You have left the abandoned manor. You conscience tells you that you should make this your last visit to this place");
        return Direction.opposite(direction);
    }


    public void DownStairsHallway(Visitor visitor)
    {
        
        visitor.tell("Would you like to:");
        Boolean Exit = false;
        while(Exit == false)
        {
            char choice = 'z';
            if(HouseDirection == HouseDirection.FROM_EAST)
            {
                choice = DownStairsMenu_FromEast(visitor);
            }
            else if(HouseDirection == HouseDirection.FROM_WEST)
            {
                choice = DownStairsMenu_FromWest(visitor);
            }
            else if(HouseDirection == Direction.FROM_NORTH)
            {
                choice = DownStairsMenu_FromNorth(visitor);
            }
            else
            {
                choice = DownStairsMenu(visitor);
            }
            switch (choice)
            {
                case 'A':
                    Exit = true;
                    break;

                case 'B':
                    SearchDownstairsHall(visitor);
                    HouseDirection = Direction.FROM_SOUTH;
                    break;
                case 'C':
                    House_Room[0].visit(visitor, HouseDirection);
                    HouseDirection = Direction.FROM_WEST;
                    break;
                case 'D':
                    House_Room[1].visit(visitor, HouseDirection);
                    HouseDirection = Direction.FROM_EAST;
                    break;
                case 'E':
                    visitor.tell("You make your way up the stairs and and stumble into some sort of chamber. (You wonder if the stairs really do go upstairs.)");
                    Upstairs(visitor);
                    HouseDirection = Direction.FROM_NORTH;
                    break;
                default:
                    DealWithIncorrectInput(visitor);
                    break;
            }
            
        } 
    }

    //takes gold from user (in a fun way) if they enter incorrect code
    public void DealWithIncorrectInput(Visitor visitor)
    {
        visitor.tell("BAM A GHOST APPEARS AND STARTLES YOU!!!!!!");
        visitor.tell("You drop your gold and lose some pieces");
        visitor.takeGold(1 + (new Random()).nextInt(3)); //takes gold from between 1-3
        visitor.tell("The ghosts warns you that you can only choose an option from the options listed.");
        visitor.tell("...POOOF ... The ghost is gone.");

    }

    //default menu (for Downstairs)
    public char DownStairsMenu(Visitor visitor)
    {
        if(FirstTimeInHouse)
        {
            visitor.tell("You enter the building and are confronted by a eerie hallway");
            FirstTimeInHouse = false;
        }
        else
        {
            visitor.tell("You have returned to the downstairs hall");
        }

        visitor.tell("Would you like to:");

        char[] Options = {'A', 'B', 'C', 'D'};
        return visitor.getChoice("(A) exit. (B)search the hall (C) enter room left (D) enter room right (E) proceed through the hall", Options);
    }

    //The menus bellow offer a menu description tailored to the direction they came from
    public char DownStairsMenu_FromEast(Visitor visitor)
    {
       
        visitor.tell("You have returned to the downstairs hall");
        visitor.tell("Would you like to:");

        char[] Options = {'A', 'B', 'C', 'D'};
        return visitor.getChoice("(A) exit. (B)search the hall (C) enter room infront (D) enter room behind you (room you just entered) (E) proceed left through the hall", Options);
    }
    public char DownStairsMenu_FromWest(Visitor visitor)
    {
       
        visitor.tell("You have returned to the downstairs hall");
        visitor.tell("Would you like to:");

        char[] Options = {'A', 'B', 'C', 'D'};
        return visitor.getChoice("(A) exit. (B)search the hall (C) enter room behind you (room you just entered) (D) enter room infront (E) proceed left through the hall", Options);
    }

    public char DownStairsMenu_FromNorth(Visitor visitor)
    {
        visitor.tell("You have returned to the downstairs hall");
        visitor.tell("Would you like to:");

        char[] Options = {'A', 'B', 'C', 'D'};
        return visitor.getChoice("(A) exit. (B)search the hall (C) enter room right (D) enter room left (E) proceed in the opposite direction through the hall", Options);
    }

    //this processes the menu for the upstairs section of the house
    public void Upstairs(Visitor visitor)
    {

        Boolean Exit = ProcessUpstairsEntrance(visitor);
        
        
        while(Exit == false)
        {
            char Choice = UpstairsHallway(visitor);
            switch(Choice)
            {
                case 'A':
                    Exit = true;
                    visitor.tell("Heading back Downstairs");
                    break;
                case 'B':
                    GoToRandomnUpstairsRoom(visitor);
                    break;
                default:
                    DealWithIncorrectInput(visitor);
                    break;
            }
        }
    }

    public char UpstairsHallway(Visitor visitor)
    {
        if(FirstTimeUpstairs)
        {
            FirstTimeUpstairs = false;
            visitor.tell("You have arrived to the upstairs hall. The hall seems different from downstairs. Possessed almost.");
        }
        else
        {
            visitor.tell("You have arrived back to the upstairs hall. Remember the hall has a mind of its own.");
        }
        
        char[] Options = {'A', 'B'};

        return visitor.getChoice("Would you like to (A) exit (B) let the upstairs domain take reigns on our journey", Options);
        
    }

    public void SearchDownstairsHall(Visitor visitor)
    {
        if(ChestOpened == true)
        {
            visitor.tell("The chest has been open and it seems there is nothing else in this room");
            if(visitor.hasEqualItem(new Item("Map")))
            {
                visitor.tell("With a secret map you obtained from a secret room you have found under the creaky floor board, GOLD!!");
                visitor.giveGold(3);
            }  
        }
        else if(WindowOpened == false && FoundChest == false)
        {
            char[] Options = {'Y', 'N'};
            char UserDecision =  visitor.getChoice("You find the room hard to search. You notice a closed window would you like to open it. (Y) yes or (N) no", Options);
            if(UserDecision == 'Y')
            {
                visitor.tell("The hall is flooded with light. You see a locked chest. You decide you will come back later once you have found the key");
                FoundChest = true;
                WindowOpened = true;
            }
            else{
                visitor.tell("The room remains dark");
                if((new Random().nextInt(100)) == 67) //0ne in 100 chance event (generates 0-99)
                {
                    visitor.tell("By sheer luck you have stubbled upon a locked chest. You decide you will come back later once you have found the key");
                    FoundChest = true;
                }
                else
                {
                    visitor.tell("Due to the darkness you find it hard to search the room");
                }
            }
        }
        else if(FoundChest == true)
        {
            if(visitor.hasEqualItem(new Item("Key")))
            {
                visitor.tell("You have found the key and open the chest. You have found GOLDDDDDDDDDD!!!!");
                visitor.giveGold(1 + (new Random().nextInt(9)));
                ChestOpened = true;
            }
            else
            {
                visitor.tell("You have found the chest but not the key.");
                visitor.tell("You decide you will come back later once you have found the key");
            }
        }

    }


    // public void Swap()
    // {

    //     Room Holder = House_Room[1];
    //     House_Room[1] = House_Room[0];
    //     House_Room[0] = Holder;
    // }


    public Boolean ProcessUpstairsEntrance(Visitor visitor)
    {
        HouseDirection = Direction.FROM_NORTH;
        HouseDirection = House_Room[2].visit(visitor, HouseDirection);
        if(HouseDirection == Direction.TO_SOUTH)
        {
            HouseDirection = Direction.FROM_SOUTH;
            visitor.tell("Heading back Downstairs");
            return true;
        }
        else
        {
            ProcessUpstairsDirection(visitor);
        }
        return false;
    }


    public void ProcessUpstairsDirection(Visitor visitor)
    {
        if(HouseDirection == Direction.TO_EAST)
        {
            HouseDirection = Direction.FROM_EAST;
            House_Room[3].visit(visitor, HouseDirection);
        }

        else if(HouseDirection == Direction.TO_WEST)
        {
            HouseDirection = Direction.FROM_WEST;
            House_Room[4].visit(visitor, HouseDirection);
        }

        else if(HouseDirection == Direction.TO_NORTH)
        {
            HouseDirection = Direction.FROM_NORTH;
            House_Room[5].visit(visitor, HouseDirection);
        }
    }

    public void GoToRandomnUpstairsRoom(Visitor visitor)
    {
        Direction[] DirectionOptions = {Direction.TO_EAST, Direction.TO_WEST,Direction.TO_NORTH};
        HouseDirection = DirectionOptions[(new Random()).nextInt(DirectionOptions.length)];
        ProcessUpstairsDirection(visitor);
    }
}

package OOP.ec22518.MP.contributions;/*
    Introductory message from the BIG GUY himself:

        Go BIG or go HOME but NEVER do both
        But what if you could???????
        WELCOME TO THE BIG HOUSE!! (bought to you by BIG Chocolate Bar™) 
        Here we break all the rules 
        You can go BIG and go (to a) HOME
        
        You do not even remember entering the BIG HOUSE
        All you remember is eating a BIG Chocolate Bar™ 
        And somehow waking up inside the foyer of the BIG HOUSE

        Well you're here now, so good luck BIG traveller
        All of these rooms are BIG but one is BIGGER than the others
    
    House description (the boring stuff):

        The user has eaten a BIG Chocolate Bar™ causing them to magically appear 
        in the foyer of the BIG HOUSE. 
        
        The foyer is the central area of the house
        and is on the ground floor. From the foyer the user has 3 options, either 
        going up to floor 1 using the stairs, down to the basement using the stairs,
        or taking their chances with the damaged elevator.

        Going upstairs takes the user through a staircase to floor 1, where they
        have a choice of 4 rooms which are detailed below.
        
        Going downstairs takes the user to floor -1, otherwise known as the BIG BASEMENT.
        Here, the user will find either the BIG BUSINESSMAN or the BIG BALLER.

        The BIG BUSINESSMAN is a man of many trades offering items of interest in 
        exchnge of adequate coinage.
        
        The BIG BALLER is more of a gambling man and offers the user a fun 
        minigame in exchange for adequate coinage.

        You need to bring coinage to the BIG BASEMENT.

        There is also a secret floor that can only be accessed using the damaged elevator and it
        cannot always be accessed as the elevator will randomly deny access. This is floor 816,
        the BIG floor. All of the rooms on the BIG floor have something BIG in common.
    
    Room Layout:
    
        Floor 1:
        Room 1 = Room_ec22551.java
        Room 2 = Room_ec22553.java
        Room 3 = Room_ec22771.java
        Room 4 = Room_ec22859.java

        Floor 816:
        Room 5 = Room_ec22551.java
        Room 6 = Room_ec22551.java
        Room 7 = Room_ec22551.java
        Room 8 = Room_ec22551.java

    House blueprint key:

        B -> BIG BASEMENT
        G -> Ground floor foyer
        L -> 1st floor lobby
        R -> Room
        BIG -> Floor 816 lobby

    House blueprint:

        Floor -1 (BIG BASEMENT):
        ______________
        |            |
        |     B      |
        |            |
        ______________

        Floor 0 (Ground floor):
        ______________
        |            |
        |     G      |
        |            |
        ______________
    
        Floor 1 (1st floor):
        ______________
        |    R1      |
        | R2  L  R3  |
        |    R4      |
        ______________

        Floor 816 (BIG floor)
        ______________
        |    R5      |
        | R6 BIG R7  |
        |    R8      |
        ______________
*/

import java.util.Random;

class House_ec22551 implements Visitable {
    public Direction visit (Visitor myVisitor, Direction d)
    {
        // initialising all of the rooms
        // 2 floors have rooms
        // floor 1 has rooms 1,2,3,4
        // floor 816 has rooms 5,6,7,8
        Room[][] rooms = {{new Room_ec22551(), new Room_ec22553(), new Room_ec22551(), new Room_ec22859()},
                          {new Room_ec22551(), new Room_ec22551(), new Room_ec22551(), new Room_ec22551()}};
        
        // initialise some BIG variables
        char currentFloor = '0';
        boolean leftHouse = false;

        // introduce visitor who has just entered the BIG HOUSE
        introduction(myVisitor);

        // house navigation process
        while(!leftHouse)
        {
            leftHouse = foyer(myVisitor, d, rooms, currentFloor);
        }

        //leaving process
        {
            return d;
        }
    }

    private void introduction(Visitor myVisitor)
    {
        // introduce the user to the BIG HOUSE
        // tell the user how they got here
        myVisitor.tell("************************************************************************");
        myVisitor.tell("Go BIG or go HOME but NEVER do both");
        myVisitor.tell("But what if you could???????");
        myVisitor.tell("WELCOME TO THE BIG HOUSE!! (bought to you by BIG Chocolate Bar™)");
        myVisitor.tell("Here we break all the rules");
        myVisitor.tell("You can go BIG and go (to a) HOME");
        myVisitor.tell("You do not even remember entering the BIG HOUSE");
        myVisitor.tell("All you remember is eating a BIG Chocolate Bar™");
        myVisitor.tell("And somehow waking up inside the foyer of the BIG HOUSE™");
        myVisitor.tell("Well you're here now, so good luck BIG traveller");
        myVisitor.tell("All of these rooms are BIG but one is BIGGER than the others");

        // greet every visitor to the BIG HOUSE with 6 gold pieces
        myVisitor.tell("************************************************************************");
        myVisitor.tell("Take this... you'll need it");
        myVisitor.giveGold(6);
        myVisitor.tell("*you receieved 6 gold pieces");

        return;
    }

    private Boolean foyer(Visitor myVisitor, Direction d, Room[][] rooms, char currentFloor)
    {
        char[] foyerChoices = {'1','2','3','4'};
        currentFloor = '0';

        myVisitor.tell("************************************************************************");
        myVisitor.tell("You are now in the Foyer and have 3 options");
        myVisitor.tell("1: Take the stairs up to the 1st floor");
        myVisitor.tell("2: Take the stairs down to the BIG BASEMENT");
        myVisitor.tell("3: Take your chances with the damaged elevator (costs 2 gold to try)");
        myVisitor.tell("4: Eat the BIG Chocolate Bar™ on the table next to you");
        
        char foyerChoice = myVisitor.getChoice("So which one is it, BIG traveller? (1,2,3,4)", foyerChoices);

        if (foyerChoice == '1')
        {
            stairs(myVisitor, d, rooms, currentFloor, '1');
        }
        else if (foyerChoice == '2')
        {
            stairs(myVisitor, d, rooms, currentFloor, 'B');
        }
        else if (foyerChoice == '3')
        {
            elevator(myVisitor, d, rooms, currentFloor);
        }
        else if (foyerChoice == '4')
        {
            return true;
        }
        
        return false;
    }

    private void elevator(Visitor myVisitor, Direction d, Room[][] rooms, char currentFloor)
    {
        // introduce the user to the elevator interface and tell them their current floor
        myVisitor.tell("************************************************************************");
        myVisitor.tell("WELCOME TO THE (slightly damaged) ELEVATOR");
        myVisitor.tell("*eerie elevator music starts playing*");
        myVisitor.takeGold(2);
        myVisitor.tell("*2 gold deducted*");
        myVisitor.tell("You are on floor " + currentFloor);

        // give user choice of rooms
        char[] elevatorChoices = {'B','0','1','?'};
        myVisitor.tell("B: BIG BASEMENT");
        myVisitor.tell("0: Ground floor foyer");
        myVisitor.tell("1: Floor 1 lobby");
        myVisitor.tell("?: ??????????????????????????");
        char elevatorChoice = myVisitor.getChoice("Which floor would you like to go to?:", elevatorChoices);

        // BIG BASEMENT
        if (elevatorChoice == 'B')
        {
            myVisitor.tell("*you pushed B*");

            if (elevatorChoice == currentFloor)
            {
                myVisitor.tell("*you realise you are already on B and leave the elevator 2 gold poorer*");
                basement(myVisitor, d, rooms, currentFloor);
            }
            
            else
            {
                myVisitor.tell("...............");
                myVisitor.tell("*DING*");
                myVisitor.tell("*you have arrived at floor -1*");
                basement(myVisitor, d, rooms, currentFloor);
            }
            
        }

        // Ground floor foyer
        else if (elevatorChoice == '0')
        {
            myVisitor.tell("*you pushed 0*");

            if (elevatorChoice == currentFloor)
            {
                myVisitor.tell("*you realise you are already on 0 and leave the elevator 2 gold poorer*");
                foyer(myVisitor, d, rooms, currentFloor);
            }

            else
            {
                myVisitor.tell("...............");
                myVisitor.tell("*DING*");
                myVisitor.tell("*you have arrived at floor 0*");
                foyer(myVisitor, d, rooms, currentFloor);
            }
        }

        // Floor 1
        else if (elevatorChoice == '1')
        {
            myVisitor.tell("*you pushed 1*");

            if (elevatorChoice == currentFloor)
            {
                myVisitor.tell("*you realise you are already on 1 and leave the elevator 2 gold poorer*");
                floor1(myVisitor, d, rooms, currentFloor);
            }

            else
            {
                myVisitor.tell("...............");
                myVisitor.tell("*DING*");
                myVisitor.tell("*you have arrived at floor 1*");
                floor1(myVisitor, d, rooms, currentFloor);
            }
        }

        // Floor 816
        else if (elevatorChoice == '?')
        {
            myVisitor.tell("*you pushed ?*");

            if (elevatorChoice == currentFloor)
            {
                myVisitor.tell("*you realise you are already on ????? and leave the elevator 2 gold poorer*");
                floor816(myVisitor, d, rooms, currentFloor);
            }

            else
            {
                myVisitor.tell("?????????????????????");
                myVisitor.tell("?????????????????????");
                myVisitor.tell("?????????????????????");
                myVisitor.tell("?????????????????????");
                
                // randomly grant access to floor 816
                Random random = new Random();
                int roll816 = 1 + random.nextInt(100);

                if (roll816 > 50)
                {
                    myVisitor.tell("*the elevator shakes VIOLENTLY*");
                    myVisitor.tell("...");
                    myVisitor.tell("...");
                    myVisitor.tell("...");
                    myVisitor.tell("*DING*");
                    myVisitor.tell("*you have arrived*");
                    myVisitor.tell("*you leave the elevator confused*");
                    floor816(myVisitor, d, rooms, currentFloor);
                }
                else
                {
                    myVisitor.tell("*CRACK*");
                    myVisitor.tell("*the elevator stops working*");
                    myVisitor.tell("*you leave the elevator confused*");

                    if (currentFloor == 'B')
                    {
                        basement(myVisitor, d, rooms, currentFloor);
                    }
                    if (currentFloor == 'G')
                    {
                        foyer(myVisitor, d, rooms, currentFloor);
                    }
                    if (currentFloor == '1')
                    {
                        floor1(myVisitor, d, rooms, currentFloor);
                    }
                }
            }
        }
        return;
    }

    private void stairs(Visitor myVisitor, Direction d, Room[][] rooms, char currentFloor, char desiredFloor)
    {
        // introduce the user to the stairs

        // going down to B
        if (desiredFloor == 'B')
        {
            myVisitor.tell("*you decided to take the stairs down to the BIG BASEMENT");
            myVisitor.tell("well done for taking the stairs and getting some exercise, have some gold");
            myVisitor.giveGold(3);
            basement(myVisitor, d, rooms, currentFloor);
        }

        // going up to 1
        if (desiredFloor == '1')
        {
            myVisitor.tell("*you decided to take the stairs up to floor 1");
            myVisitor.tell("well done for taking the stairs and getting some exercise, have some gold");
            myVisitor.giveGold(6);
            floor1(myVisitor, d, rooms, currentFloor);
        }

        return;
    }

    private void basement(Visitor myVisitor, Direction d, Room[][] rooms, char currentFloor)
    {
        currentFloor = 'B';

        // introduce the user to the BIG BASEMENT
        Random random = new Random();
        int rollBasement = 1 + random.nextInt(100);

        if (rollBasement > 50)
        {
            myVisitor.tell("*you feel the presence of a BALLER*");
            myVisitor.tell("I AM THE BIG BALLER");
            myVisitor.tell("IF YOU GUESS MY NUMBER FROM 1-10 I WILL GIVE YOU GOLD");
            myVisitor.tell("YOU GOTTA PAY TO PLAY AND NO YOU DO NOT HAVE A CHOICE");
            myVisitor.tell("DONT EVEN TELL ME YOUR GUESS... I READ MINDS");

            int rollBaller = 1 + random.nextInt(10);

            if (rollBaller > 5)
            {
                myVisitor.tell("HAVE MONEY");
                myVisitor.giveGold(4);
            }
            else
            {
                myVisitor.tell("NO MONEY FOR YOU, GIMME YOUR MONEY");
                myVisitor.takeGold(4);
            }
        }
        else
        {
            myVisitor.tell("*you feel the presence of a BUSINESSMAN*");
            myVisitor.tell("I AM THE BIG BUSINESSMAN");
            myVisitor.tell("I SELL A MASSIVE SWORD");
            myVisitor.tell("IT COSTS 4 GOLD");

            char[] businessChoices = {'1','2'};
            char businessChoice = myVisitor.getChoice("(1)BUY MY SWORD (2)BE A LAME", businessChoices);
            
            if (businessChoice == '1')
            {
                Item massiveSword = new Item("massiveSword");
                myVisitor.giveItem(massiveSword);
                myVisitor.tell("pleasure doing BUSINESS with you");
            }
            else if (businessChoice == '2')
            {
                myVisitor.tell("you a lame");
                myVisitor.tell("LEAVE MY STORE");
            }
        }
        return;
    }

    private void floor1(Visitor myVisitor, Direction d, Room[][] rooms, char currentFloor)
    {
        // floor 1 methods (navigate rooms)
        currentFloor = '1';
        // return using stairs down to foyer
        myVisitor.tell("************************************************************************");
        myVisitor.tell("Welcome to floor 1");
        myVisitor.tell("1: Room 1 (NORTH)");
        myVisitor.tell("2: Room 2 (WEST)");
        myVisitor.tell("3: Room 3 (EAST)");
        myVisitor.tell("4: Room 4 (SOUTH)");
        myVisitor.tell("5: Leave to where you came from");

        char[] directionChoices = {'1','2','3','4','5'};
        char directionChoice = myVisitor.getChoice("So which one is it, BIG traveller?", directionChoices);
        
        if (directionChoice == '1')
        {
            d = Direction.TO_NORTH;
        }
        else if (directionChoice == '2')
        {
            d = Direction.TO_WEST;
        }
        else if (directionChoice == '3')
        {
            d = Direction.TO_EAST;
        }
        else if (directionChoice == '4')
        {
            d = Direction.TO_SOUTH;
        }
        else if (directionChoice == '5')
        {
            return;
        }

        boolean leaveFloor = false;

        while(! leaveFloor)
        {
            char[] leaveChoices = {'1','2'};
            char leaveChoice = myVisitor.getChoice("(1) Stay on this floor (2) Leave this floor", leaveChoices);
            
            if (leaveChoice == '2')
            {
                leaveFloor = true;
                myVisitor.tell("*you chose to leave and go to the ground floor foyer*");
                return;
            }
            
            else
            {
                if(d == Direction.TO_NORTH)
                {
                    myVisitor.tell("*you chose to go NORTH to Room 1*");
                    d = rooms[0][0].visit(myVisitor,d);
                }
                else if(d == Direction.TO_WEST)
                {
                    myVisitor.tell("*you chose to go WEST to Room 2*");
                    d = rooms[0][1].visit(myVisitor,d);
                }
                else if(d == Direction.TO_EAST)
                {
                    myVisitor.tell("*you chose to go EAST to Room 3*");
                    d = rooms[0][2].visit(myVisitor,d);
                }
                else if(d == Direction.TO_SOUTH)
                {
                    myVisitor.tell("*you chose to go SOUTH to Room 4*");
                    d = rooms[0][3].visit(myVisitor,d);
                }
            }
        }
    }

    private void floor816(Visitor myVisitor, Direction d, Room[][] rooms, char currentFloor)
    {
        // floor 816 methods (navigate rooms)
        currentFloor = '?';
        // return using stairs down to foyer
        myVisitor.tell("************************************************************************");
        myVisitor.tell("Welcome to 816");
        myVisitor.tell("DA BIG ROOM");
        myVisitor.tell("1: Room 5 (NORTH)");
        myVisitor.tell("2: Room 6 (WEST)");
        myVisitor.tell("3: Room 7 (EAST)");
        myVisitor.tell("4: Room 8 (SOUTH)");
        myVisitor.tell("5: Leave to where you came from");
        myVisitor.tell("By the way every room is BIG here");
        myVisitor.tell("You'll know what that means soon");

        char[] directionChoices = {'1','2','3','4','5'};
        char directionChoice = myVisitor.getChoice("So which one is it, BIG traveller?", directionChoices);
        
        if (directionChoice == '1')
        {
            d = Direction.TO_NORTH;
        }
        else if (directionChoice == '2')
        {
            d = Direction.TO_WEST;
        }
        else if (directionChoice == '3')
        {
            d = Direction.TO_EAST;
        }
        else if (directionChoice == '4')
        {
            d = Direction.TO_SOUTH;
        }
        else if (directionChoice == '5')
        {
            return;
        }

        boolean leaveFloor = false;

        while(! leaveFloor)
        {
            char[] leaveChoices = {'1','2'};
            char leaveChoice = myVisitor.getChoice("(1) Stay on this floor (2) Leave this floor", leaveChoices);
            
            if (leaveChoice == '2')
            {
                leaveFloor = true;
                myVisitor.tell("*you chose to leave and go to the ground floor foyer*");
                return;
            }
            
            else
            {
                if(d == Direction.TO_NORTH)
                {
                    myVisitor.tell("*you chose to go NORTH to Room 5*");
                    d = rooms[1][0].visit(myVisitor,d);
                }
                else if(d == Direction.TO_WEST)
                {
                    myVisitor.tell("*you chose to go WEST to Room 6*");
                    d = rooms[1][1].visit(myVisitor,d);
                }
                else if(d == Direction.TO_EAST)
                {
                    myVisitor.tell("*you chose to go EAST to Room 7*");
                    d = rooms[1][2].visit(myVisitor,d);
                }
                else if(d == Direction.TO_SOUTH)
                {
                    myVisitor.tell("*you chose to go SOUTH to Room 8*");
                    d = rooms[1][3].visit(myVisitor,d);
                }
            }
        }
    }
}

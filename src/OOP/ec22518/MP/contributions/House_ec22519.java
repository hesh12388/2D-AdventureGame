package OOP.ec22518.MP.contributions;

import java.util.Scanner;

class House_ec22519 implements Visitable {
    Room room_A, room_B, room_C, room_D;

    public House_ec22519()
    {
        room_A = new Room_ec22772();
        room_B = new Room_ec22519();
        room_C = new Room_ec22520();
        room_D = new Room_ec22522();
    }

    public Direction visit(Visitor v, Direction arrival_Direction)
    {
        Boolean ArrivedAtDoor = false;

        if(arrival_Direction == Direction.FROM_SOUTH)
        {
            v.tell("After going through some hedges you see a small old house in front of you surrounded by a giant hedge wall. You see a red door which you presume is the entrace to the house. \n");
            ArrivedAtDoor = true;
        }
        else
        {
            v.tell("After going through some hedges you see a small old house in front of you surrounded by a giant hedge wall. You see no visible entrance to the house from this side. \n");
        }

        char goIn = v.getChoice("Do you wish to enter the house? (Y/N)", new char[]{'Y','N','y','n'});

        if(goIn == 'Y' || goIn == 'y')
        {
            if(ArrivedAtDoor)
            {
                v.tell("You decide to enter the house. The rusty red door creaks as you try to open it. \nAfter some effort you open the door but as soon as you step inside the door slams shut! \nYou try to open the door but it is locked. Looks like you have to find another way out. \nYou are in a dimly lit corridor with another door at the end. You decide to pass through that door to find another way out. \n");
            }
            else
            {
                v.tell("You decide to enter the house. \nYou circle the house looking for and entrance and eventually find a red door which you assume to be the entrance. \nThe rusty red door creaks as you try to open it. After some effort you open the door but as soon as you step inside the door slams shut! \nYou try to open the door but it is locked. Looks like you have to find another way out. \nYou are in a dimly lit corridor with another door at the end. You decide to explore further into the house to find another way out.  \n");
            }
        }
        else if(goIn == 'N' || goIn == 'n')
        {
            v.tell("You decide not to go into the house. \nYou instead have a look around the house and find a gap in the hedge wall and decide to go through. \n");
            return Direction.TO_WEST;
        }
        else
        {
            v.tell("After some time, you decide not to go into the house. \nYou instead have a look around the house and find a gap in the hedge wall and decide to go through. \n");
            return Direction.TO_WEST;
        }

        Room_A(v, Direction.FROM_SOUTH);

        movingRoomText();
        
        v.tell("As you enter the room the door slams shut behind you. You try to open the door but it is locked.");
        Item AmongUsPlushy = new Item("Among Us Plushy");
        if(v.hasEqualItem(AmongUsPlushy))
        {
            v.tell("You feel scared but at least you have an Among us plushy to keep you company! \n");
        }
        v.tell("As you look around the room you see there is a message written on the wall and a door.");
        v.tell("Message on the Wall:  'What appears once in a minute, twice in a moment but never in a thousand years?' \n");
        v.tell("There is nothing else interesting so you head toward the door and see there is a combination lock on the door. \n");

        boolean doorLocked = true;
        Scanner scan = new Scanner(System.in);

        v.tell("The combination has 3 wheels with numbers from 0-9. The correct combination probably has something to do with the message on the wall.");
        v.tell("Enter a combination! e.g 189, 621, 147, ... \n");
        
        int hintCounter = 0;

        while(doorLocked)
        { 
            try
            {
                System.out.print("Code: ");
                int combination = scan.nextInt();
                if(combination == 120)
                {
                    v.tell("You hear a click as the door becomes unlocked! You exit through the door. \n");
                    doorLocked = false;
                }
                else if(combination > 999 || combination < 0)
                {
                    v.tell("Invalid combination input. Try an actual combination.");
                }
                else
                {
                    v.tell(".... Nothing happened. Perhaps its the wrong combination. Try again.");
                    hintCounter++;
                }

                if(hintCounter == 3)
                {
                    v.tell("\n You hear a voice say 'Look at each word carefully!' \n");
                }
                else if(hintCounter == 6)
                {
                    v.tell("\n You hear a voice say 'Hmmmm... You're not very smart are you? ... Look at the words minute, moment and thousand. Do you see a pattern? (once, twice, never)' \n");
                }
                else if(hintCounter == 9)
                {
                    v.tell("\n You hear a voice say 'You still haven't got it? You should stop entering random houses. Look at the letter m in the 3 words I mentioned before!'\n");
                }
                else if(hintCounter == 12)
                {
                    v.tell("\n You hear the a sigh.");
                    v.tell("You hear a voice say : 'The code is 120 ... Please leave!' \n");
                }
                else if(hintCounter == 15)
                {
                    v.tell("\n You hear a voice say : 'I think its about time you left.'");
                    v.tell(" You hear a click as the locked door opens and then a sudden gust of wind pushes you out of the room");
                    v.tell("The door slams shut behind you. \n");
                    doorLocked = false;
                }
            }
            catch(Exception e)
            {
                System.out.println("Invalid combination input. Try an actual combination.");
                scan.next();
            }
        }

        scan.close();

        v.tell("As you exit you are blinded by the light outside. \nYou hear a thud behind you. You look behind you but there is only a wall. You must have left the house through a hidden exit.");
        v.tell("You are covered in cobwebs and dust. You decide you would rather not enter this house ever again. \nWhile thinking of what to do next you spot a gap in the hedge in front of you and decide to explore. \n");

        return Direction.TO_WEST;
    }

    public void Room_A(Visitor v, Direction arrival_Direction)
    {
        movingRoomText();
        Direction dA = room_A.visit(v, arrival_Direction);

        if(dA == Direction.TO_EAST)
        {
            Room_B(v, Direction.TO_EAST);
        }
        else if(dA == Direction.TO_NORTH)
        {
            Room_C(v, Direction.TO_NORTH);
        }
        else if(dA == Direction.TO_SOUTH)
        {
            v.tell("You think about going back the way you came but then you remember it leads to red door which is locked.");
            v.tell("You look around the room and see 2 doors.");
            char exit_A = v.getChoice("Which way do you go? (N/E)", new char[]{'E','N','e','n'});

            if(exit_A == 'E' || exit_A == 'e'){Room_B(v, Direction.TO_EAST); v.tell("");}
            else if(exit_A == 'N' || exit_A == 'N'){Room_C(v, Direction.TO_NORTH); v.tell("");}
            else{v.tell("You can't decide which door to go through so you pick a random door. \n"); Room_B(v, Direction.TO_EAST);}
        }
        else
        {
            v.tell("\nThere is no visible exit in this direction. You look around the room and see 2 doors.");
            char exit_A = v.getChoice("Which way do you go? (N/E)", new char[]{'E','N','e','n'});

            if(exit_A == 'E' || exit_A == 'e'){Room_B(v, Direction.TO_EAST);}
            else if(exit_A == 'N' || exit_A == 'N'){Room_C(v, Direction.TO_NORTH);}
            else{v.tell("You can't decide which door to go through so you pick a random door. \n"); room_B.visit(v, Direction.TO_EAST);}
        }
    }

    public void Room_B(Visitor v, Direction arrival_Direction)
    {
        movingRoomText();
        Direction dB = room_B.visit(v, arrival_Direction);
        
        if(dB == Direction.TO_WEST)
        {
            Room_A(v, Direction.TO_WEST);
        }
        else
        {
            v.tell("\nThere is no visible exit in this direction.");
            v.tell("The only exit seems to be the door you just came through. You decide to go back the way you came.");
            Room_A(v, arrival_Direction);
        }
    }

    public void Room_C(Visitor v, Direction arrival_Direction)
    {
        movingRoomText();
        Direction dC = room_C.visit(v, arrival_Direction);

        if(dC == Direction.TO_SOUTH)
        {
            Room_A(v, Direction.TO_SOUTH);
        }
        else if(dC == Direction.TO_WEST)
        {
            Room_D(v, Direction.TO_WEST);
        }
        else if(dC == Direction.TO_NORTH)
        {
            return;
        }
        else
        {
            v.tell("\nThere is no visible exit in this direction. You look around the room and see 3 doors. One of the doors(N) seems very rust and you hear ominous sounds coming from the other side of the door.");
            char exit_A = v.getChoice("Which way do you go? (S/W/N)", new char[]{'W','S','w','s','N','n'});

            if(exit_A == 'W' || exit_A == 'w'){Room_D(v, Direction.TO_WEST);}
            else if(exit_A == 'S' || exit_A == 's'){Room_A(v, Direction.TO_SOUTH);}
            else if(exit_A == 'N' || exit_A == 'n'){v.tell("After some consideration you decide to go through the suspicious door."); return;}
            else{v.tell("You can't decide which door to go through so you pick a random door. \n"); room_A.visit(v, Direction.TO_WEST);}
        }
    }

    public void Room_D(Visitor v, Direction arrival_Direction)
    {
        movingRoomText();
        Direction dD = room_D.visit(v, arrival_Direction);
        v.tell("\nYou head back the way you came.\n");

        if(dD == Direction.TO_EAST)
        {
            Room_C(v, Direction.TO_EAST);
        }
    }

    public void movingRoomText()
    {
        System.out.println("");
        System.out.println("Moving to another room ...");
        System.out.println("");   
    }
}

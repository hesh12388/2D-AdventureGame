package OOP.ec22518.MP.contributions;

class House_ec22814 implements Visitable {
    Room_ec22814 r1;
    Room_ec22630 r2;
    Room_ec22959 r3;
    Room[] rooms = new Room[3];
    
    char[] ABCChoice ={'a','b','c'};
    char[] ABChoice = {'a','b'};
    
    Item map = new Item("map");
    Item behelit = new Item("behelit");

    

    
    House_ec22814() {
        Room_ec22814 r0 = new Room_ec22814();
        Room_ec22630 r1 = new Room_ec22630(); //22630
        Room_ec22959 r2 = new Room_ec22959(); //22959
        
        rooms[0]=r0;
        rooms[1]=r1;
        rooms[2]=r2;
    }
    
    public Direction visit(Visitor visitor, Direction fromd) {
        
        Direction d;
        int currentRoom;
        char ansR;
        char ansL;
        char ansH;
        char ans;
        int hallcount=0;
        int roomsVisited=0;
        
        visitor.tell("Welcome to my house, try to get as much gold as you can. Take this map with you.");
        visitor.giveItem(map);
        
        ansR = visitor.getChoice("What room would you like to visit first? a) 1 b) 2 c) 3 ?",ABCChoice);
        
        if (ansR=='a')
        {
            currentRoom=0;
            d = rooms[0].visit(visitor,fromd);
            roomsVisited+=1;
        }
        else if (ansR=='b')
        {
            currentRoom=1;
            d = rooms[1].visit(visitor,fromd);
            roomsVisited+=1;
        }
        else
        {
            currentRoom=2;
            d = rooms[2].visit(visitor,fromd);
            roomsVisited+=1;
        }
        
        boolean loop=true;
        
        while (loop)
        {          
            ansL=visitor.getChoice("You wanna leave? a) Stay b) Leave ",ABChoice);
            if (ansL=='b')
            {
                loop=false;
                return d;
            }
            else
            {
                if (roomsVisited>2)
                {
                    if (hallcount<1)
                    {
                        visitor.tell("You know what, you mind exploring the hallway?");
                    }
                    else
                    {
                        visitor.tell("You can still check the halls, maybe you missed something, who knows...");
                    }

                    ansH=visitor.getChoice("a) explore hallway b) continue checking the rooms",ABChoice);
                    if (ansH=='a')
                    {
                        hallcount+=1;
                        if (hallcount!=2)
                        {
                            visitor.tell("Theres nothing but an endless cobblestone path here");
                            visitor.tell("I wonder why he wanted me here...");
                            visitor.tell("You give up and go into the nearest room");
                            visitor.tell("As you open the door, you hear laughter in the down the hall");
                            ans=visitor.getChoice("Check it out? a) check it out  b) forget about it",ABChoice);
                            if (ans=='a')
                            {
                                visitor.tell("You turn around but somehow you are in the room you decided not to go in...");
                            }

                        }
                        else
                        {
                            visitor.tell("As you walk down the hall you begin to hear that laughter again");
                            visitor.tell("It gets louder and louder the further you go.");
                            visitor.tell("Then, you see it, an old lady at a market stand? She says she's been waiting for you");
                            visitor.tell("She offers you some red head, what she calls the Egg of the King");
                            visitor.giveItem(behelit);
                            if (visitor.hasIdenticalItem(behelit))
                            {
                                visitor.tell("She mutters that it must be your fate...");
                            }
                            else
                            {
                                visitor.tell("She mutters that it must have not been your fate...");
                            }
                        }   
                    }
                }
                
                if (currentRoom==0 & (d==Direction.FROM_NORTH | d==Direction.FROM_SOUTH))
                {
                    d=rooms[1].visit(visitor,d);
                    roomsVisited+=1;
                }
                else if (currentRoom==0 & (d==Direction.FROM_EAST | d==Direction.FROM_WEST))
                {
                    d=rooms[2].visit(visitor,d);
                    roomsVisited+=1;
                }
                else if (currentRoom==1 & (d==Direction.FROM_NORTH | d==Direction.FROM_SOUTH))
                {
                    d=rooms[0].visit(visitor,d);
                    roomsVisited+=1;
                }
                else if (currentRoom==1 & (d==Direction.FROM_EAST | d==Direction.FROM_WEST))
                {
                    d=rooms[2].visit(visitor,d);
                    roomsVisited+=1;
                }
                else if (currentRoom==2 & (d==Direction.FROM_NORTH | d==Direction.FROM_SOUTH))
                {
                    d=rooms[0].visit(visitor,d);
                    roomsVisited+=1;
                }
                else if (currentRoom==2 & (d==Direction.FROM_EAST | d==Direction.FROM_WEST))
                {
                    d=rooms[1].visit(visitor,d);
                    roomsVisited+=1;
                }
            }
        }
  
        return d;
    }
}

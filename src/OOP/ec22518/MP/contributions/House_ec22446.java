package OOP.ec22518.MP.contributions;

import java.util.Random;

class House_ec22446 implements Visitable {
    Room room1;
    Room room2;
    Room room3;

    Random rand = new Random();

    char[] choices = {'a', 'b', 'c'};
    char choice;


    public House_ec22446()
    {
        room1 = new Room_ec22446();
        room2 = new Room_ec221247();
        room3 = new Room_ec22581();
    }


    public Direction visit(Visitor visitor, Direction arrivedFrom)
    {
        Direction cameFrom;
        int visitor_health = 100;

        // goes to room1 and comes out opposite from the way came in
        visitor.tell("You have arrived from " + arrivedFrom.toString());

        cameFrom = room1.visit(visitor, arrivedFrom);
        visitor.tell("You are coming from " + cameFrom.toString());

        if (cameFrom == Direction.opposite(arrivedFrom))
        {
            return Direction.opposite(arrivedFrom);
        }
        // if comes from East
        else
        {
            visitor.tell("Looks like you got some cash buddy, care for a trade?");
            choice = visitor.getChoice("a) Ok     b) No      c) Punch the guy", choices);

            if (choice == 'a')
            {
                visitor.tell("Oh I was just going to beat you up and take all your money, I'll just take it all and beat you up but not as bad.");

                visitor.takeGold(100);
            }
            else if (choice == 'b')
            {
                visitor.tell("Yeah well I was gonna beat you up anyway and take your money.");

                visitor.takeGold(100);
                visitor.tell("You have lost your clothes.");

            }
            else
            {
                visitor.tell("So you really wanna fight huh, then lets go...");
                
                for (int i = 0; i < 5; i++)
                {
                    choice = visitor.getChoice("a) give em a right       b) give em a left       c) bob and weave mac", choices);
                    int punch = 0;
                    int dodge = 0;

                    if (choice == 'a' || choice == 'b')
                    {
                        punch = rand.nextInt(10);

                        if (punch < 5)
                            {
                                visitor.tell("You missed dummy.");
                                visitor_health = visitor_health - 20;
                                visitor.takeGold(2);
                            }
                        else
                            {
                                visitor.tell("You actually got a hit in huh.");
                                visitor.giveGold(5);
                            }
                    }
                    else
                    {
                        dodge = rand.nextInt(10);

                        if (dodge >= 5)
                        {
                            visitor.tell("Didn't think a dummy like you would be so nimble!");
                        }
                        else
                        {
                            visitor.tell("Took that square on huh!");
                            visitor_health = visitor_health - 10;
                        }
                    }

                    if (visitor_health <= 0)
                    {
                        break;
                    }

                    visitor.tell("Come on mac you still got some fight in you!!");
                    System.out.println("Health: " + visitor_health);
                }

                visitor.tell("Yeah tell me how you thought you could win when your telling every move you dummy...");
            }

            cameFrom = room2.visit(visitor, cameFrom);
            visitor.tell("You are coming from " + cameFrom.toString());

            if (visitor_health > 20)
            {

                visitor.tell("Say it's been a rough day huh hows about you give me 10 gold and I let you out?");
                choice = visitor.getChoice("a) Pay 10 gold       b) Continue foward      c) Ugh", choices);

                if (choice == 'a')
                {
                    visitor.takeGold(10);
                    visitor.tell("You have left the house");
                    visitor.tell("You have died from fall");
                    return Direction.opposite(cameFrom);
                }
                else
                {
                    visitor.tell("You are coming from " + cameFrom.toString());
                }
            }

            cameFrom = room3.visit(visitor, cameFrom);
            visitor.tell("You are coming from " + cameFrom.toString());        
        }

        return Direction.opposite(arrivedFrom);
    }
}
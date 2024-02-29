package OOP.ec22518.MP.contributions;

import java.util.Random;

class House_ec22770 implements Visitable {
    
    //Create Room array 
    Room [] rooms = {new Room_ec22770(),new Room_ec22695(),new Room_ec22751(), new Room_ec22692()};
    static boolean HasBagbeenOpen = false;
    static boolean exit = false;
    static boolean floorbroke = false;

    public Direction visit( Visitor visitor, Direction directionVistorArrivesFrom)
    {
        visitor.giveGold(10);
        visitor.tell("Welcome to Maple Valley manor. The building has been abandend since 1987 because of the great incident that changed the lives of people within the couminity. Locals consider the building abnormal due to the beliefs of ghost taking over the premesis");
        visitor.tell("You enter the building from the main entrance. Where would you like to go");
        groundfloor(visitor, directionVistorArrivesFrom);
        return directionVistorArrivesFrom;

    }

    //Method for bottom floor
    public void groundfloor(Visitor visitor, Direction directionVistorArrivesFrom)
    {
        char[] options = {'a','b','c','d','e'};
        char[] choice ={'y','n'};
        boolean exit = false;
        while(exit == false)
        {
            char downstairs = visitor.getChoice("a) Walk down the hallway b) Visit the Library Room on your left c) Visit the door on your right labelled as THE MYSTERY ROOM d) Go upstairs e) Exit", options);

        //if 'a' user will go further down the hallway and has an option to view a cabinet which has a bag of gold. if user has already opened the bag they are sactioned with a loss of 3 gold. User has option to open bag 
            if (downstairs == 'a')
            {
                visitor.tell("You explore the main hallway and find a cabinet covered in dust. The cabinet contains a single door. You open the door and see a grey bag");
                char cabinet = visitor.getChoice("Would you like to see what is inside the bag. y/n", choice);
                if (cabinet == 'y')
                {
                    if(HasBagbeenOpen == false)
                    {
                        visitor.tell("The bag contains gold. But remember this is not the only gold lurking around the old building");
                        visitor.giveGold(7);
                        HasBagbeenOpen = true;
                    }
                    else{
                        visitor.tell("The bag has been opened already. Consider this as a warning to not be greedy");
                        visitor.takeGold(1);
                    }
                }
                else{
                    visitor.tell("I guess your not that curious or you just saved yourself from punishment");
                }
            }

            else if (downstairs=='b')
            {
                rooms[1].visit(visitor, directionVistorArrivesFrom);
            }

            else if (downstairs =='c')
            {
                rooms[3].visit(visitor, directionVistorArrivesFrom);
            }

            else if (downstairs == 'd')
            {
            topfloor(visitor, directionVistorArrivesFrom);
            }

            else if (downstairs=='e'){
                exit = true;
            }
        }  
    }

    //Method for top floor
    public void topfloor(Visitor visitor, Direction directionVistorArrivesFrom)
    {
        char[] options = {'a','b','c','d'};
        char [] choice ={'y','n'};
        boolean downstairs = false;
        visitor.tell("You have no climbed the stairs");
        while (downstairs == false)
        {
            char upstairs = visitor.getChoice("Where would you like to explore. a) the study room on your right b) the bedroom on your left c) go further down the hallway d) go back downstairs", options);

            if(upstairs == 'a')
            {
                rooms[2].visit(visitor, directionVistorArrivesFrom);
            }

            else if (upstairs =='b')
            {
                rooms[4].visit(visitor, directionVistorArrivesFrom);
            }

            else if (upstairs == 'c') 
            {
                //User might be unlucky and might fall from the broken floor causing them to be uncousnicos loose gold and go back to the bottom floor.
                Random n = new Random();
                int chance = n.nextInt(4);
                if (floorbroke==false && chance == 2 ){
                    visitor.tell("You dive deeper through the abandond hallway and notice......");
                    visitor.tell("BANGG! You lost your consious and realise you fell through the broken wood floor of the top floor. Because of your injury you have lost 3 gold");
                    visitor.takeGold(3);
                    downstairs = true;

                }
                //if they get lucky they are able to go through the passage way and view a message.
                else{
                    visitor.tell("You dive deeper through the abandond hallway and notice an envelope within the passage way");
                    char message = visitor.getChoice("Would you like to view the letter. y/n", choice);
                    if (message=='y')
                    {
                        visitor.tell("You found a chunk of gold with a letter that says : The chest lies near the reception");
                        visitor.giveGold(1);
                    }
                    else
                    {
                        visitor.tell("You leave the letter behind and carry on your journey on the top floor.");
                    }
                }

            }
            else if(upstairs == 'd')
            {
                downstairs = true;
            }
        }
    }
}
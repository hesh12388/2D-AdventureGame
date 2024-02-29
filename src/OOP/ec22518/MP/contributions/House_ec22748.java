package OOP.ec22518.MP.contributions;

class House_ec22748 implements Visitable {
    //Three rooms to visit inside the house
    Room IlaiRoom;
    Room ishitaRoom;
    Room ceciRoom;

    //Constructor for the class
    House_ec22748() {
        IlaiRoom = new Room_ec22748();
        ishitaRoom = new Room_ec22825();
        ceciRoom = new Room_ec22879();
    }

    public Direction visit(Visitor visitor, Direction direction)
    {
        //condition for the while loop
        boolean notLeaving = true;
        // the possible options in order to use the getChoice method
        char firstOptions[] = {'a', 'b', 'c'};
        char secondOptions[] = {'a' , 'b', 'c', 'd'};
        //direction of the exit which is the opposite to the entering direction
        Direction leavingDirection = direction.opposite(direction);

        //starting the while loop
        while (notLeaving){
            //first choosing options for the user
            char firstChoice = visitor.getChoice("Welcome to my humble house. " +
                    "Do you want to a)get out to the garden b)go upstairs c) leave the house",firstOptions);

            // switch and cases for the 3 possible cases 'a', 'b' and 'c'
            switch (firstChoice) {
                //the case for 'a'
                case 'a':
                    char gardenChoice = visitor.getChoice("This is my garden, " +
                            "Since i was very little gardening was my hobby. " +
                            "Do you want to a)take out the carrots b)eat the oranges c) go back inside",firstOptions);

                    if (gardenChoice == 'a'){
                        Item carrot= new Item("carrot");
                        visitor.tell("Thank you for helping me with my garden here!" +
                                " here is 10 gold pieces and the carrot!");
                        visitor.giveItem(carrot);
                        visitor.giveGold(10);
                    }
                    else if(gardenChoice == 'b'){
                        Item orange = new Item("orange");
                        visitor.giveItem(orange);
                        visitor.tell("Hey! what are you doing? Those cost 5 pieces of gold");
                        visitor.takeGold(5);
                    }
                    else if(gardenChoice == 'c'){
                        visitor.tell("alright let's go back inside");
                    }

                // the case for 'b'
                case 'b':
                    visitor.tell("here are my rooms and my two roomates' rooms. " +
                            "I could show you mine but let me check if I can show you theirs, give me a second...");
                    char roomChoice = visitor.getChoice(
                            "Alright there is no problem they said that they both cleaned their rooms this morning. " +
                                    "so which rooms do you want to see a)mine b)Ishita's my first roommate " +
                                    "c) Ceci's my second roommate d)let's go back downstairs",secondOptions);

                    if(roomChoice == 'a'){
                        IlaiRoom.visit(visitor, direction);
                    }else if(roomChoice == 'b'){
                        ishitaRoom.visit(visitor, direction);
                    }else if(roomChoice == 'c'){
                        ceciRoom.visit(visitor, direction);
                    }else if(roomChoice == 'd'){
                        visitor.tell("Alright well so let's go downstairs");
                    }

                // the case for 'c'
                case 'c':
                    visitor.tell("well it was really nice to see you hope to see you next time");
                    notLeaving = false;
            }
        }
        return leavingDirection;
    }
}

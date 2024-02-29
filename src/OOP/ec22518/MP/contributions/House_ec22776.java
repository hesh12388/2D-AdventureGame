package OOP.ec22518.MP.contributions;

class House_ec22776 implements Visitable {
    
    //rooms inside the house
    Room Room_1;
    Room Room_2;
    Room Room_3;

    
    //the constructor for the class
    House_ec22776(){
        Room_1 = new Room_ec22776();
        Room_2 = new Room_ec22748();
        Room_3 = new Room_ec22867();
    }
    
    public Direction visit(Visitor v, Direction d) {
        boolean notLeaving = true;
        Direction leavingDirection = Direction.opposite(d);
        // the possible options in order to use the getChoice method
        char firstOptions[] = {'a', 'b', 'c'};
        char secondOptions[] = {'a' , 'b', 'c', 'd'};

        while (notLeaving){
            //first choosing options for the user
            char firstChoice = v.getChoice("Welcome to my house. Would you like to a)hit the gym \nb)go upstairs \nc)leave the house",firstOptions);

            // switch and cases for the 3 possible cases 'a', 'b' and 'c'
            switch (firstChoice) {
                //the case for 'a'
                case 'a':
                    char gymChoice = v.getChoice("This is the gym, since me and my roomates workout often, we decided to make a homegym. " +
                            "Do you want to a) workout with dumbbels \nb) get some protein shake \nc) check out other places",firstOptions);

                    if (gymChoice == 'a'){
                        Item dumbbels= new Item("dumbbels");
                        v.tell("Grab some dumbbels and let's start the workout and I'll reward you with 10 pieces of gold");
                        v.giveItem(dumbbels);
                        v.giveGold(10);
                    }
                    else if(gymChoice == 'b'){
                        Item protein_shake = new Item("Protein shake");
                        v.giveItem(protein_shake);
                        v.tell("Now that you've had a protein shake, it is going to cost you 5 pieces of gold");
                        v.takeGold(5);
                    }
                    else if(gymChoice == 'c'){
                        v.tell("okay let's look at other parts of the house");
                    }

                // the case for 'b'
                case 'b':
                    v.tell("Now we are upstairs, there are three rooms, the first one is mine and the others are for my roomates");

                    char roomChoice = v.getChoice("Do you want to visit any of the rooms? a)mine \nb)the second room" + 
                                                        "\nc)the third room \nd) go back downstairs",secondOptions);

                    if(roomChoice == 'a'){
                        Room_1.visit(v, d);
                    }else if(roomChoice == 'b'){
                        Room_2.visit(v, d);
                    }else if(roomChoice == 'c'){
                        Room_3.visit(v, d);
                    }else if(roomChoice == 'd'){
                        v.tell("Alright well so let's go downstairs");
                    }

                // the case for 'c'
                case 'c':
                    v.tell("well it was really nice to see you hope to see you next time");
                    notLeaving = false;
            }
        }
        return leavingDirection;
    }
}

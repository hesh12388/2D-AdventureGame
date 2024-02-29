package OOP.ec22518.MP.contributions;

class House_ec22543 implements Visitable {

    //rooms inside the house
    Room Room_1;

    //constructor for the class
    House_ec22543(){
        Room_1 = new Room_ec22708();
    }

    public Direction visit(Visitor v, Direction d) {
        boolean notLeaving = true;
        // the possible options in order to use the getChoice method
        char firstOptions[] = {'a', 'b', 'c'};
        char secondOptions[] = {'a', 'b', 'c'};

        while (notLeaving){
            //first choosing options for the user
            char firstChoice = v.getChoice("This is private property, how did you get here!? a)I come in peace! \nb)I stumbled upon here \nc) Sorry I'll leave the house!",firstOptions);

            // switch and cases for the 3 possible cases 'a', 'b' and 'c'
            switch (firstChoice) {
                //the case for 'a' and 'b'
                case 'a':
                case 'b':
                    char offering = v.getChoice("You can have tea and leave, or if you're not thirsty then please leave" +
                            "Do you want a) tea \nb) to leave \nc) Visit my neighbour (he's friendly)",secondOptions);

                    if (offering == 'a'){
                        Item tea = new Item("tea");
                        v.tell("I'll pour you some tea and you'll be on your merry way");
                        v.giveItem(tea);
                        notLeaving = false;
                    }
                    else if(offering == 'b'){
                        v.tell("Be careful next time, not everyone will be as nice");
                        Room_1.visit(v, d);
                        notLeaving = false;
                    }
                    else if(offering == 'c'){
                      v.tell("Don't get too cozy");
                      notLeaving = false;
                    }


                // the case for 'c'
                case 'c':
                    v.tell("Ok");
                    notLeaving = false;
            }
        }
        return Direction.opposite(d);
    }
}

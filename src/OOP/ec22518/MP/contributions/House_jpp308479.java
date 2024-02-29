package OOP.ec22518.MP.contributions;

class House_jpp308479 implements Visitable {
    Room_jpp308479 r= new Room_jpp308479();
    Room_jpp317487 r1= new Room_jpp317487();
    Room_jpp324787 r2 = new Room_jpp324787();

    Visitable objectToVisit;
    Visitable objectToVisit1;
    Visitable objectToVisit2;
    String contributor ;
    String c= "Nikita Feoktystov";
    House_jpp308479() { objectToVisit = r;objectToVisit1=r1;objectToVisit2=r2; contributor = c;}
    static final Item HOUSE_KEY= new Item("Key");

    public Direction visit(Visitor v, Direction d) {

        v.tell("Welcome to jpp308479 House!: You have entered a House containing three Rooms ");

        while(true) {
            switch (v.getChoice("jpp308479 House: Do you want to " +
                            "q) leave the jpp308479 House or enter the Room from the " +
                            "s) South " +
                            "w) West " +
                            "n) North or " +
                            "e) East" +
                             "g)Go outside",

                    new char[]{'d', 's', 'w', 'n', 'e', 'q','g'})) {

                case 'g':
                    switch (v.getChoice("You would like to:" +
                            "a) visit a garden" +
                            "b) climb out the window", new char[]{'a', 'b'}))
                    {
                        case 'a':
                            v.giveItem(HOUSE_KEY);
                            switch (v.getChoice("h)You are in the garden:You would like to re-enter the front of the house " +
                                    "l)leave the house?",new char[]{'h','l'})) {

                                case 'l':
                                    v.tell("jpp308479 House: You just left the House contributed by " + contributor + " " + d + ".");
                                    return d;
                                case 'h':
                                   return visit(v,d);

                            }

                        case 'b':
                            switch (v.getChoice("You are outside: Would you like to a)Go back to the house b)Look around",new char[]{'h','l'})){
                                case 'a':return visit(v,d);
                                case 'b':v.giveItem(HOUSE_KEY);
                                return visit(v,d);
                            }
                        default:v.tell("Wrong choice");


                    }
                case 'd':
                    break; // Leave d as it is.
                case 's':
                    d = Direction.FROM_SOUTH;

                    break;
                case 'w':
                    d = Direction.FROM_WEST;

                    break;
                case 'n':
                    d = Direction.FROM_NORTH;

                    break;
                case 'e':
                    d = Direction.FROM_EAST;
                    break;

                case 'q':
                    if(v.hasIdenticalItem(HOUSE_KEY)) {
                        v.tell("jpp308479 House: You just left the House contributed by " + contributor + " " + d + ".");
                        return d;

                    }

                    else{
                        v.tell("You need the key!");
                        return visit(v,d);
                    }

                default:
                    v.tell("This cannot happen - ring the Pope!");
                    break;
            }

            if(d==Direction.FROM_SOUTH)
            {
                objectToVisit.visit(v,d);
                if(d==Direction.TO_NORTH)
                {
                    objectToVisit1.visit(v,d);
                    if(d==Direction.TO_NORTH){
                        objectToVisit2.visit(v,d);
                    }
                }

            }
            if (d==Direction.FROM_WEST) {
                objectToVisit1.visit(v, d);
                if(d==Direction.TO_WEST){
                    objectToVisit.visit(v,d);
                    if(d==Direction.TO_SOUTH){
                        objectToVisit2.visit(v,d);
                    }
                }
            }
            if (d==Direction.FROM_NORTH)
            {
                objectToVisit2.visit(v,d);
                if (d==Direction.TO_SOUTH)
                {
                    objectToVisit.visit(v,d);
                    if (d==Direction.TO_SOUTH){
                        objectToVisit1.visit(v,d);
                    }
                }
            }


            v.tell("jpp308479 House: You just left the Room contributed by " + contributor + " " + d + ".");
        }

    }
}
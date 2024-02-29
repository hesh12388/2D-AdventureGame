package OOP.ec22518.MP.contributions;

class House_ec22716 implements Visitable{
    private Room_ec22716 bedroom;
    private Room_ec22476 livingroom;
    private Room_ec22697 diningroom;

    public House_ec22716() {
        bedroom = new Room_ec22716();
        livingroom = new Room_ec22476();
        diningroom = new Room_ec22697();
    }

    public Direction visit(Visitor v, Direction d) {
        v.tell("Welcome to my enchanted home!");

        // Ask the visitor to make a choice
        char[] options = {'a', 'b', 'c', 'e'};
        String optionsdescription = "You have the option of going into the following a)the bedroom b)the livingroom c)the diningroom e)exit";
        char userchoice = v.getChoice(optionsdescription, options);            

        Direction userdirection = d;

        while (userchoice != 'e') {
            if (userchoice == 'a') {
                v.tell("You chose to go to the bedroom.");
                userdirection = bedroom.visit(v, d);
            } else if (userchoice == 'b') {
                v.tell("You chose to go to the livingroom.");
                userdirection = livingroom.visit(v, d);
            } else if (userchoice == 'c') {
                v.tell("You chose to go to the diningroom.");
                userdirection = diningroom.visit(v, d);
            }

            userchoice = v.getChoice(optionsdescription, options); 
        }

        return userdirection;
    }
}

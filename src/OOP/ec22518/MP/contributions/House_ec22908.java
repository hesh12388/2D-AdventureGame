package OOP.ec22518.MP.contributions;

class House_ec22908 implements Visitable {
    private Room room1;
    private Room room2;
    private Room room3;
    private Direction finish_direction;


    House_ec22908() {
        room1 = new Room_ec22908();
        room2 = new Room_ec22959();
        room3 = new Room_ec22909();
    }

    public Direction visit(Visitor v, Direction directionVisitorArrived) {


        char[] wanna_enter = {'y','n'};
        char choice_entry = v.getChoice("Do you want to enter Buckingham Palace? \ny \nor \nn",wanna_enter);

        if(choice_entry==('y')){

            v.tell("Welcome to Buckingham palace!");
            v.tell("There are 3 rooms you an access");
            char[] what_direction= {'a', 'b' ,'c','d'};
            char main_choice = v.getChoice("Which room do you want to enter ((a) on the left / (b) on the right / (c) forwards / (d) or you can exit.)", what_direction);

            if(main_choice=='a'){
                v.tell("You enter an extremely Large theatre within Buckingham palace, who knew they would have such royalties ;>");
                choice_entry = v.getChoice(" You see a hidden trap door would you like to enter it\ny \nor \nn",wanna_enter);

                if (choice_entry=='y'){
                    finish_direction=room1.visit(v,Direction.FROM_WEST);
                } else if (choice_entry=='n') {
                    v.tell("eh tough luck you're missing out");
                    finish_direction=Direction.FROM_WEST;
                }
            }
            else if (main_choice=='b') {
                v.tell("Lets see if you will have fun or not");
                finish_direction = room2.visit(v,Direction.FROM_EAST);

            }
            else if (main_choice=='c') {
                finish_direction = room3.visit(v,Direction.FROM_NORTH);

            }
            else if (main_choice=='d') {
                finish_direction = directionVisitorArrived;
            }
        }
        return finish_direction;
    }

}

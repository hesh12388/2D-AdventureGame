package OOP.ec22518.MP.contributions;

class House_ec22566 implements Visitable {

    private Room room1;
    private Room room2;
    private Room room3;

    public House_ec22566() {
        room1 = new Room_ec22894();
        room2 = new Room_ec22703();
        room3 = new Room_ec22566();
    }

    public Direction visit(Visitor v, Direction d) {
        v.tell("Welcome to my humble abode!");
        char[] choices = {'a', 'b', 'c'};
        v.tell("which room would you like to move towards?");
        char roomChoice = v.getChoice("a) The one on your left\n b) The one in front of you\n c) The one on your right\n", choices);

        Direction direction = d;
        if(roomChoice == ('a')) {
            v.tell("You have entered the first room. Come on in.");
            v.tell("You see a painting and a curtain on opposites sides in the corridor. Which one would you like to peak behind?");
            char peekChoice = v.getChoice("a)The painting\n b)The curtain\n c)Neither", choices);
            if(peekChoice == ('a')){
                v.tell("you have found 10 gold! Could the room ahead be related to gold?");
                v.giveGold(10);
                v.tell("You may enter the room.");
            }
            else if (peekChoice ==('b')){
                v.tell("You have found 2 gold!");
                v.giveGold(2);
                v.tell("You may enter the room.");
            }
            else{
                v.tell("You may enter the room.");
            }
            direction = room3.visit(v, d);
        }
        else if(roomChoice == ('b')) {
            v.tell("You have entered the second room. ");
            direction = room2.visit(v, d);
        }
        else if(roomChoice == ('c')) {
            v.tell("You have entered the East side room of the building");
            direction = room1.visit(v, d);
        }
        else {
            v.getChoice("That is not a valid answer. Try again.", choices);
        }

        return direction;
    }
}

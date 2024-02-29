package OOP.ec22518.MP.contributions;

class House_ec22993 implements Visitable {

    private Room RoomOne;
    private Room RoomTwo;
    private Room RoomThree;

    public House_ec22993() {
        RoomOne = new Room_ec22616();
        RoomTwo = new Room_ec22430();
        RoomThree = new Room_ec22466();
    }

    public Direction visit(Visitor v, Direction d) {

        v.tell("Welcome to the house");
        v.tell("You have been guided into the central room");
        v.tell("You have three options");


        char[] choices = {'1', '2', '3'};
        char choice = v.getChoice("Do you want to enter the door in front of you(1), to the left of you(2), to the right of you(3)", choices);

        if (choice == '1') {
            v.tell("You decide to face the challenge straight on, you enter the door in front of you");
            d = RoomOne.visit(v, d);
        }

        if (choice == '2') {
            v.tell("Will you left be lucky, you enter the room on your left");
            d = RoomTwo.visit(v, d);
        }

        if (choice == '3') {
            v.tell("You can never go wrong if you go right, you enter the room on your right");
            d = RoomThree.visit(v, d);
        }


        return d;
    }
}

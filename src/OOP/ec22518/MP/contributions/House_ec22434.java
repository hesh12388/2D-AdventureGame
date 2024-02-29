package OOP.ec22518.MP.contributions;

class House_ec22434 implements Visitable {
    private Room r1;
    private Room r2;
    private Room r3;

    House_ec22434() {
        r1 = new Room_ec22434(); // North
        r2 = new Room_ec22435(); // East
        r3 = new Room_ec22436(); // West
    }

    public Direction visit(Visitor v, Direction d) {
        v.tell("Welcome traveller, you are about to enter a (allegedly) haunted house. Do you dare continue?");
        char[] options = {'N', 'S', 'W', 'E'};
        char choice = v.getChoice("Make your decision! Go south if you want to leave.", options);

        while (choice != 'S') {
            if (choice == 'N') {
                v.tell("Welcome to the northern room!");
                d = r1.visit(v, d);
            }
            if (choice == 'E') {
                v.tell("Welcome to the eastern room!");
                d = r2.visit(v, d);
            }
            if (choice == 'W') {
                v.tell("Welcome to the western room!");
                d = r3.visit(v, d);
            }
            else {
                choice = v.getChoice("Try again!", options);
            }
            choice = v.getChoice("Time to move to the next room", options);
        }
        return d;
    }
}

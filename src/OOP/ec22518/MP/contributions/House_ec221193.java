package OOP.ec22518.MP.contributions;

public class House_ec221193 implements Visitable {
    
    private Room room1;
    private Room room2;
    private Room room3;

    
    public House_ec221193() {
        room1 = new Room_ec22749();
        room2 = new Room_ec22475();
        room3 = new Room_ec221193();
    }    
    public Direction visit (Visitor v, Direction d) {

        final char[] abcd_options = {'a','b','c', 'd'};

        System.out.println("Welcome to the ec221193 house!");
        
        while (true) {

            char currentRoom = v.getChoice("Which room would you like to visit first? options are: a, b and c. If you wish to exit, you can select d", abcd_options);

            if (currentRoom == 'a') {
               room1.visit(v, d);
            } else if (currentRoom == 'b') {
                room2.visit(v, d);
            } else if (currentRoom == 'c') {
                room3.visit(v, d);
            } else if (currentRoom == 'd') {
                return Direction.opposite(d);
            } else {
                System.out.println("This is not a valid input, try again!");
            }

        }
    }
}

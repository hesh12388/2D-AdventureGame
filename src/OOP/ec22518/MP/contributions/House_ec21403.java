package OOP.ec22518.MP.contributions;

class House_ec21403 implements Visitable {

    Room r1;
    Room r2;
    Room r3;
    Room r4;

    House_ec21403() {
        r1 = new Room_ec22743();
        r2 = new Room_ec22597();
        r3 = new Room_ec22615();
        r4 = new Room_ec22712();
    }

    public Direction visit(Visitor v, Direction d) {
        v.tell("You have entered the house");

        boolean enter = true;
        int location = 0;

        while (enter) {
            char[] options = {'N', 'E', 'S', 'W'};
            char option = v.getChoice("Enter N, S, E, or W", options);

            if (option == 'N') {
                v.tell("You have entered the North side room of the house ");
                location = 1;
                d = r1.visit(v, d);
                if (d == Direction.TO_SOUTH) {
                    v.tell("You are heading back the way you came");
                    location = 0;
                }
            }

            if (option == 'E') {
                v.tell("You have entered the East side room of the house ");
                location = 2;
                d = r2.visit(v, d);
                if (d == Direction.TO_WEST) {
                    v.tell("You are heading back the way you came");
                    location = 0;
                }
            }

            if (option == 'W') {
                v.tell("You have entered the West side room of the house ");
                location = 3;
                d = r3.visit(v, d);
                if (d == Direction.TO_EAST) {
                    v.tell("You are heading back the way you came");
                    location = 0;
                }
                if (d == Direction.TO_SOUTH) {
                    v.tell("You found a secret room");
                    location = 4;
                    v.tell("You found a key and an exit within the room.");
                    enter = false;
                }
            }
        }
        return d;
    }

    public static void main(String[] args) {
        House_ec21403 h = new House_ec21403();
        Visitor guy = new IOVisitor(System.out, System.in);
        h.visit(guy, Direction.FROM_SOUTH);
    }
}

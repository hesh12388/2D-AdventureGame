package OOP.ec22518.MP.contributions;

class House_ec22578 implements Visitable {


    public Direction visit(Visitor visitor, Direction direction) {
        Room room1 = new Room_ec22578();
        Room room2 = new Room_ec21841();
        Room room3 = new Room_ec22451();
        Room room4 = new Room_ec22480();


        char[] userOps = {'a', 'b', 'c'};
        char[] userOps2 = {'a', 'b'};
        char[] userOps3 = {'1', '2'};
        Boolean exit = false;

        while (!exit) {
            visitor.tell("This house is dark and barren, be carefull while" +
                    "you visit here.....");
            visitor.tell("To your left is Room 1 and to your right is Room 2\n" +
                    "or you can continue ahead....");
            char op = visitor.getChoice("(a)-Room 1 (b)-Room 2 (c)-Continue Ahead", userOps);
            if (op == 'a') {
                Direction directionFromRoom = room1.visit(visitor, direction);
            } else if (op == 'b') {
                Direction directionFromRoom = room2.visit(visitor, direction);
            } else if (op == 'c') {
                visitor.tell("You walk on in the house......" +
                        "you see Room 3 to your left and Room 4 to your right");


            }
            op = visitor.getChoice("(a)-Room 3 (b)-Room 4", userOps2);
            if (op == 'a') {
                Direction directionFromRoom = room3.visit(visitor, direction);
            }
            if (op == 'b') {
                Direction directionFromRoom = room4.visit(visitor, direction);
            }
            visitor.tell("Do you wish to exit of walk back to the front of the house?");
            char ch = visitor.getChoice("(1)-Back to Front (2)-Exit", userOps3);
            if (ch == '2') {
                exit = true;
            }


        }
        return direction;
    }

}

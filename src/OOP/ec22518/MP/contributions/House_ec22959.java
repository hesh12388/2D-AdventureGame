package OOP.ec22518.MP.contributions;

class House_ec22959 implements Visitable {
    Visitable objectToVisit;
    Room[] room = new Room[4];

    public House_ec22959() {
        Room Room1 = new Room_ec22908();
        Room Room2 = new Room_ec22618();
        Room Room3 = new Room_ec22814();
        Room Room4 = new Room_ec22960();

        room[0] = Room1;
        room[1] = Room2;
        room[2] = Room3;
        room[3] = Room4;
    }


    public Direction visit(Visitor v, Direction d){
        v.tell("You come across a house, do you enter?");
        char choice = v.getChoice("a) Yes b) No", new char[]{'a','b'});

        if (choice == 'b') {
            v.tell("You do not to enter the house. ");
            return d;
        }

        v.tell("You enter the house.");
        
        while (true) {
            choice = v.getChoice("You decide to\n"+
                        "a) leave the house\n"+
                        "b) enter the North room entrance\n"+
                        "c) enter the East room entrance\n"+
                        "d) enter the South room entrance\n"+
                        "e) enter the West room entrance\n", new char[] {'a','b','c','d','e'});
            
            switch (choice) {
                case 'a':
                    exithouse(v);
                    return d;
                case 'b':
                    d = Direction.FROM_NORTH;
                    this.objectToVisit = room[0];
                    break;
                case 'c':
                    d = Direction.FROM_EAST;
                    this.objectToVisit = room[1];
                    break;
                case 'd':
                    d = Direction.FROM_SOUTH;
                    this.objectToVisit = room[2];
                    break;
                case 'e':
                    d = Direction.FROM_WEST;
                    this.objectToVisit = room[3];
                    break;
                default:
                    v.tell("You struggled to decide and just walk to the South room entrance.");
                    d = Direction.FROM_SOUTH;
                    break;
            }
            objectToVisit.visit(v, d);
        }
    }

    void exithouse(Visitor v) {
        v.tell("In the end you decide to exit the house");
        v.tell("You also come across 10 gold ");
        v.giveGold(10);
        return;
    }
}

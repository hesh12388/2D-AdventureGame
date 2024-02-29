package OOP.ec22518.MP.contributions;

class House_ec22906 implements Visitable {
    Room kitchen;
    Room bedroom;
    Room bathroom;
    Room hall;

    House_ec22906() {
        bedroom = new Room_ec22906();
        kitchen = new Room_bt21057();
        bathroom = new Room_ec22414();
        hall = new Room_ec221150();
    }

    public Direction visit(Visitor v, Direction d) {
        v.tell("Welcome... you just entered the domain of Lord ec22906!");
        v.tell("");
        v.giveGold(5);
        v.tell("");
        v.tell("Lord felt generous today!");
        v.tell("");

        int x = 3, y = 1;
        while (true) {
            visitLocation(x, y, v, d);
            char direction = v.getChoice(
                "Where do you want to go now:\n" +
                "n) NORTH\n" +
                "e) EAST\n" +
                "s) SOUTH\n" +
                "w) WEST",
                new char[] {'n', 'e', 's', 'w'}
            );

            switch (direction) {
                case 'n': y++; break;
                case 'e': x++; break;
                case 's': y--; break;
                case 'w': x--; break;
            }
        }
    }

    void visitLocation(int x, int y, Visitor v, Direction d) {
        v.tell("");
        if (x == 1 || x == 5 || y == 1 || y == 5) {
            v.tell("---------- You are actually in the luxurious GARDEN of the Lord ----------");
        } else if (y == 2 && x >= 2 && x <= 4) {
            v.tell("---------- You are actually in the spectacular MAIN HALL of the house ----------");
            v.tell("");
            hall.visit(v, d);
            v.tell("");
        } else if (x == 4 && y >= 3 && y <= 4) {
            v.tell("---------- You are actually in the sumptuous KITCHEN ----------");
            v.tell("");
            kitchen.visit(v, d);
            v.tell("");
        } else if (x == 3 && y == 3) {
            v.tell("---------- You are actually in the majestic BATHROOM ----------");
            v.tell("");
            bathroom.visit(v, d);
            v.tell("");
        } else if (y == 4 && x >= 2 && x <= 3) {
            v.tell("---------- You are actually in the awesome BEDROOM ----------");
            v.tell("");
            bedroom.visit(v, d);
            v.tell("");
        } else if (y == 3 && x == 3) {
            v.tell("---------- You are actually in the tiny HALLWAY ----------");
            v.tell("");
        }
    }
}

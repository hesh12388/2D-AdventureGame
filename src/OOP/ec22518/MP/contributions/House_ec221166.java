package OOP.ec22518.MP.contributions;

class House_ec221166 implements Visitable {
    Room first;
    Room second;
    Room third;

     House_ec221166() {
        first = new Room_ec221014();
        second = new Room_ec22760();
        third = new Room_ec22893();
    }

    public Direction visit(Visitor v, Direction d) {
        char[] options = {'a', 'b', 'c'};
        v.tell("Welcome to my new home");
        String room = ("There are three rooms which are new built. Which room you would like to visit" +
                " a)Room1, b)Room2 or c)Room3");
        char answer = v.getChoice(room, options);

        if (answer == 'a') {
            first.visit(v,d);
        } else if (answer == 'b') {
             second.visit(v,d);
        } else if (answer == 'c') {
             third.visit(v,d);
        }
        else{
            v.tell("Invalid choice. Try choosing between (a), (b) or (c)");
        }

        return d;

    }
}


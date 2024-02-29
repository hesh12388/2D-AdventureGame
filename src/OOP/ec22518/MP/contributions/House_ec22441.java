package OOP.ec22518.MP.contributions;

class House_ec22441 implements Visitable {

    Room room1;
    Room room2;
    Room room3;
    boolean close;

    House_ec22441() {

        room1 = new Room_ec22441();
        room2 = new Room_ec22579();
        room3 = new Room_ec22557();

    }

    public Direction visit(Visitor visitor, Direction direction) {
        char choices_yn = '0'; // get users choice yes or no (sub choices)
        char choices_la = '0'; // get users choice the letters (main choices)
        close = false; // default

        char[] visitorChoiceLa = { 'a', 'b', 'c', }; // create char array for letters options
        char[] visitorChoiceYn = { 'Y', 'N' }; // create char array for yes and no options

        while (!close) {

            direction = room1.visit(visitor, direction); // start my room

            choices_la = visitor.getChoice(
                    " You're out of the room do you want a) go to another room b) look around c) leave the house ",
                    visitorChoiceLa); // the hallway

            if (choices_la == ('a')) {

                direction = room3.visit(visitor, direction); // visiting room 3

            }

            else if (choices_la == ('b')) {

                visitor.tell(" It's such an old house, with a lot of mysteries don't you think that ");

                choices_la = visitor.getChoice(" do you want a) go to another room or b) leave the house ? ",
                        visitorChoiceLa); // the hallway

                if (choices_la == ('a')) {

                    direction = room2.visit(visitor, direction); // visiting room 2

                }

                else {
                    close = true; // close is true, leave the house

                    break;
                }
            }

            if (choices_la == ('c')) { // close is true, leave the house
                close = true;

                break;

            }

        }
        return direction;

    }
}

package OOP.ec22518.MP.contributions;

class House_ec22545 implements Visitable {
    Room Room1;
    Room Room2;
    Room Room3;

    public House_ec22545() {
        Room1 = new Room_ec22545();
        Room2 = new Room_ec22433();
        Room3 = new Room_ec22680();

    }

    public Direction visit(Visitor visitor, Direction visitorDirection) {
        char[] options = {'1', '2', '3', '4', '5'};
        visitor.tell("Welcome to the House of everlasting.");
        visitor.tell("I am your guide Mistical and will help you navigate the House");
        char choice='0';
        while(choice!='4') {
            choice = visitor.getChoice("Select from the following(1-4)- 1. Room 1 get at North | 2. Room 2 get at East | 3. Room 3 get at West | 4. EXIT", options);
            if (choice == '1') {
                Room1.visit(visitor, visitorDirection);
                visitorDirection = Direction.TO_NORTH;
            } else if (choice == '2') {
                visitorDirection = Direction.TO_EAST;
                Room2.visit(visitor, visitorDirection);
            } else if (choice == '3') {
                visitorDirection = Direction.TO_WEST;
                Room3.visit(visitor, visitorDirection);
            } else if (choice == '4') {
                visitorDirection = Direction.TO_SOUTH;
                visitor.tell("It was nice having you. GoodBye!");
            } else {
                visitor.tell("Please insert the correct input");
            }
        }
            return visitorDirection;
        }
    }

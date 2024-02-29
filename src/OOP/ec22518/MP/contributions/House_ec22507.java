package OOP.ec22518.MP.contributions;

public class House_ec22507 implements Visitable {
    Room room1;
    Room room2;
    Room room3;
    House_ec22507(){
        room1 = new Room_ec22507();
        room2 = new Room_ec22545();
        room3 = new Room_ec22680();
    }

    public Direction visit(Visitor visitor, Direction direction) {
        char[] choices = {'a','b','c','d',};
        visitor.tell("Welcome, this house is a gateway to an alternate dimension.");
        visitor.tell("Make a choice and you will be transported to a different room to discover.");
        boolean LOOP = true;
        String routes = ("Select one of the following options a) Enter north portal b) Enter east portal c) Enter west portal d) Leave the house");
        while(LOOP){
            visitor.tell("There are 3 portals to enter, north, east and west.");
            char choice = visitor.getChoice(routes, choices);
            if(choice == 'a'){
                room1.visit(visitor,direction);
                direction = Direction.TO_NORTH;
            }
            else if(choice == 'b'){
                room2.visit(visitor,direction);
                direction = Direction.TO_EAST;
            }
            else if (choice == 'c'){
                room3.visit(visitor,direction);
                direction = Direction.TO_WEST;
            }
            else if (choice == 'd'){
                direction = Direction.TO_SOUTH;
                visitor.tell("It seems that you are leaving, salam brother.");
                LOOP = false;
            }
            else {
                visitor.tell("Please choose one of the choices.");
            }
        }
        return direction;
    }
}

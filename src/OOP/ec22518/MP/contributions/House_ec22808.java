package OOP.ec22518.MP.contributions;

class House_ec22808 implements Visitable {
  
    private boolean key;
    private Room roomForward;
    private Room roomLeft;
    private Room roomRight;

     House_ec22808() {
         roomForward = new Room_ec22808();
         roomLeft = new Room_ec22707();
         roomRight = new Room_ec22442();
     }


    public Direction visit(Visitor visitor, Direction direction) {

        key = false;

        visitor.tell("Welcome to my dungeon, find the key to leave this place. You may choose to enter one of the 3 rooms.");
        visitor.tell("Do you wish to enter the room infront of you, to the left of you or to the right of you? (F/L/R)");
        char[] choice = {'F' , 'L' , 'R'};

        while (!key) {
          
            char options = visitor.getChoice("Choice?", choice);

            if(options == ('F')) {
                visitor.tell("Welcome to the front room!");
                direction = roomForward.visit(visitor, direction);
                visitor.tell("Key is not in this room.");
            }

            if(options == ('L')) {
                visitor.tell("Welcome to the left room!");
                direction = roomLeft.visit(visitor, direction);
                key = true;
                visitor.tell("Key is in this room.");
            }

            if(options == ('R')) {
                visitor.tell("Welcome to the right room!");
                direction = roomRight.visit(visitor, direction);
                visitor.tell("Not this room");
            }   
        }

        return direction;
    }
}

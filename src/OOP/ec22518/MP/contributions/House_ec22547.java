package OOP.ec22518.MP.contributions;

class House_ec22547 implements Visitable {
    Room room1;
    Room room2;
    Room room3;
    Room room4;
    
    public House_ec22547(){
        room1 = new Room_ec22982();
        room2 = new Room_ec22426();
        room3 = new Room_ec22885();
        room4 = new Room_ec22547();
    }
    
    public Direction visit(Visitor v, Direction d) {
        boolean exit = false;
        while(!exit){
            d = room1.visit(v, d);
            d = room2.visit(v, d);
            d = room3.visit(v, d);
            d = room4.visit(v, d);
            if (d == Direction.TO_NORTH){
                exit = true;
                v.tell("You reach the back door and leave the house.");
            }
            else{
                exit = false;
                v.tell("You enter another room. It seems very familiar...");
            }
        }
        return d;
    }
}

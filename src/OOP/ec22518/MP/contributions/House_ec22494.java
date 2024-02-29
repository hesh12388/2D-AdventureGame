package OOP.ec22518.MP.contributions;

public class House_ec22494 implements Visitable {
    Room room1;
    
    public House_ec22494() {
        room1 = new Room_ec22494();
    }
    
    public Direction visit(Visitor v, Direction d) {
        return d;
    }
}

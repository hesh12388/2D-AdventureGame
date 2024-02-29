package OOP.ec22518.MP.contributions;

public class House_ec22479 implements Visitable {
    public Direction visit(Visitor v, Direction d) {
        
        // First Room - Fahim Miah
        Room_ec22717 room1 = new Room_ec22717();
        Direction direction1 = room1.visit(v,d);
        
        // Second Room - Mine :D
        Room_ec22479 room2 = new Room_ec22479();
        Direction direction2 = room2.visit(v,direction1);

        // Third Room - Tuddin
        Room_ec22944 room3 = new Room_ec22944();
        return room3.visit(v, direction2);
    }
}

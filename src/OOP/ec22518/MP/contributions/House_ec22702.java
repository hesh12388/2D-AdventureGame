package OOP.ec22518.MP.contributions;

public class House_ec22702 implements Visitable {
    Room_ec22680 roomA;
    Room_ec22568 roomB;
    Room_ec22702 roomC;

    public House_ec22702() {

        roomA = new Room_ec22680();
        roomB = new Room_ec22568();
        roomC = new Room_ec22702();
    }

    public Direction visit(Visitor visitor, Direction directionOfVisitor) {
        visitor.tell("You're are now located in the corridor of my house");
        char option = visitor.getChoice("Input the number 1 to enter Room_ec22701, the number 2 for Room_ec22703, or the number 3 for Room_ec22702", new char[] {'1', '2', '3'});

        switch(option) {
            case '1':
                roomA.visit(visitor, directionOfVisitor);
                break;
            case '2':
                roomB.visit(visitor, directionOfVisitor);
                break;
            case '3':
                roomC.visit(visitor, directionOfVisitor);
                break;
            default:
                break;
        }
        return directionOfVisitor;
    }
}

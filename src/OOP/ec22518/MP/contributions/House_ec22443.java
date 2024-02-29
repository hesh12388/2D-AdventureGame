package OOP.ec22518.MP.contributions;

class House_ec22443 implements Visitable {
    Room_ec22568 room1;
    Room_ec22443 room2;
    Room_ec221085 room3;

    public House_ec22443() {
        room1 = new Room_ec22568();
        room2 = new Room_ec22443();
        room3 = new Room_ec221085();
    }

    public Direction visit(Visitor visitor, Direction directionOfVisitor) {
        visitor.tell("you are currently in a house please choode a room you would like to enter?");
        char answer = visitor.getChoice("Type A for room 1, B for room 2, C for room 3 and D to leave the house!", new char[] {'A','B','C','D'});

        switch(answer)
        {
            case 'A':
                room1.visit(visitor, directionOfVisitor);
                break;
            case 'B':
                room2.visit(visitor, directionOfVisitor);
                break;
            case 'C':
                room3.visit(visitor, directionOfVisitor);
                break;            
        }
        
    return directionOfVisitor;
    }
}

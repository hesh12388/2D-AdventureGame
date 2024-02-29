package OOP.ec22518.MP.contributions;

class House_ec22851 implements Visitable {
    Room_ec22568 firstRoom;
    Room_ec221085 secondRoom;
    Room_ec22446 thirdRoom;
    
    public House_ec22851() 
    {
        firstRoom = new Room_ec22568();
        secondRoom = new Room_ec221085();
        thirdRoom = new Room_ec22446();
    }
    
    public Direction visit(Visitor visitor, Direction direction) 
    {
        visitor.tell("You are in the middle of my house!");
        visitor.tell("There are three rooms which surround you!");
        visitor.tell("Room 1 is Room_ec22568.");
        visitor.tell("Room 2 is Room_ec221085.");
        visitor.tell("Room 3 is Room_ec22446.");
        char answer = visitor.getChoice("Which room do you want to enter? Type A for room 1, B for room 2, C for room 3 and D to leave the house!", new char[] {'A','B','C','D'});
        
        if(answer == 'A') {
            firstRoom.visit(visitor, direction);
        }
        else if(answer == 'B') {
            secondRoom.visit(visitor, direction);
        }
        else if(answer == 'C') {
            thirdRoom.visit(visitor, direction);
        }
        
        return direction;
    }
}

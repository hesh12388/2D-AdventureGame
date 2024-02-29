package OOP.ec22518.MP.contributions;

public class House_ec22563 implements Visitable {

    // This house is linear, no matter which way you leave you will end up in the next room
    // At the last room you end up at the start, you can either leave or re-traverse the rooms

    Room roomOne;
    Room roomTwo;
    Room roomThree;

    House_ec22563() {
        roomOne = new Room_ec22519();
        roomTwo = new Room_ec22563();
        roomThree = new Room_ec22642();
    }

    @Override
    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        boolean inHouse = true;
        Room[] rooms = {roomOne, roomTwo, roomThree};
        int curRoom = 0;

        while (inHouse) {
            if (curRoom < 3) {
                directionVistorArrivesFrom = rooms[curRoom].visit(visitor, directionVistorArrivesFrom);
                curRoom++;
            } else {
                visitor.tell("You appear to be back at the entrance of the house, shall you leave or re-enter the first room?");
                char choice = visitor.getChoice("a) Leave\n b) Re-enter", new char[]{'a','b'});
                if (choice == 'a') {
                    inHouse = false;
                } else {
                    curRoom = 0;
                }
            }
        }

        visitor.tell("Goodbye!");
        return directionVistorArrivesFrom;
    }
    
}

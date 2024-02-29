package OOP.ec22518.MP.contributions;

public class House_ec22907 implements Visitable {
    // Variables of room objects

    Room roomA;
    Room roomB;
    Room roomC;
    Room roomD;

    House_ec22907() {
        this.roomA = new Room_ec22448();
        this.roomB = new Room_ec22451();
        this.roomC = new Room_ec221002();
        this.roomD = new Room_ec22786();

    }

    public Direction visit(Visitor v, Direction directionOfVisitors) {
        int currentRoom = 0;
        boolean house = true;
        Room[] rooms = { roomA, roomB, roomC, roomD };

        while (house) {
            if (currentRoom < 3) {
                directionOfVisitors = rooms[currentRoom].visit(v, directionOfVisitors);
                currentRoom += 1;
            } else {
                v.tell("Please enter the first room again.");
                char choice = v.getChoice("a) Leave\n b) Re-enter", new char[] { 'a', 'b' });
                if (choice == 'a') {
                    house = false;
                } else {
                    currentRoom = 0;
                }
            }
        }
        v.tell("Have a good day!");
        return directionOfVisitors;
    }

}

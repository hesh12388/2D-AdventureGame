package OOP.ec22518.MP.contributions;

class House_ec22723 implements Visitable {

        public House_ec22723() {

        RoomA = new Room_ec221021();
        RoomB = new Room_ec221183();
        RoomC = new Room_ec22632();
        RoomD = new Room_ec22900();
    } 

   public Direction visit(Visitor v, Direction d) {
    Room currentRoom = RoomC;  
    Direction oppositeDirection = Direction.opposite(d);
    boolean isExitFound = false;

    while (!isExitFound) {
        Direction nextDirection = currentRoom.visit(v, oppositeDirection);
        oppositeDirection = Direction.opposite(nextDirection);

        if (currentRoom == RoomA || currentRoom == RoomB || currentRoom == RoomD) {
        
            System.out.println(nextDirection.toString());
            System.out.println("You have exited the Haunted Spooky Mansion. Well done!");
            isExitFound = true;
        } else if (currentRoom == RoomC) {
         
            if (nextDirection == Direction.TO_NORTH || nextDirection == Direction.TO_EAST) {
               
                currentRoom = RoomD;
            } else if (nextDirection == Direction.TO_SOUTH) {
                
                currentRoom = (Math.random() < 0.5) ? RoomA : RoomB;
            }
            System.out.println(nextDirection.toString());
        }
    }

    return d;
}

    Room RoomA, RoomB, RoomC, RoomD;
    boolean goToRoomA, goToRoomB, goToRoomC, goToRoomD = false;
    boolean leaveHouse = false;

}             

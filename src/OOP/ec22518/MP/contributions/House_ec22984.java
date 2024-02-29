package OOP.ec22518.MP.contributions;

class House_ec22984 implements Visitable {
    // 1-dimensional array to store 4 Room instances
    private Room[] rooms;

    public House_ec22984() {
        // Initialize the rooms array with 4 elements
        rooms = new Room[4];

        // Create instances of Room classes and assign them to the array
        
        rooms[0] = new Room_ec22984();
        rooms[1] = new Room_ec22660();
        rooms[2] = new Room_ec22661();
        rooms[3] = new Room_ec22662();
    }

    public Direction visit(Visitor visitor, Direction directionVisitorArrivesFrom) {
        // The index of the current room in the rooms array
        int currentRoomIndex = 0;

        // Welcome message
        visitor.tell("Welcome to my house!");

        // Loop to navigate between rooms
        while (true) {
            // Visit the current room and get the returned direction
            directionVisitorArrivesFrom = rooms[currentRoomIndex].visit(visitor, directionVisitorArrivesFrom);

            // Navigate to the next room based on the returned direction
            if (directionVisitorArrivesFrom == Direction.FROM_NORTH) {
                // Move to the next room in the array, wrap around if necessary
                currentRoomIndex = (currentRoomIndex + 1) % rooms.length;
            } else if (directionVisitorArrivesFrom == Direction.FROM_SOUTH) {
                // Move to the previous room in the array, wrap around if necessary
                currentRoomIndex = (currentRoomIndex + rooms.length - 1) % rooms.length;
            } else {
                // End the house tour and return the opposite direction
                visitor.tell("You have completed the house tour. Goodbye");
                return directionVisitorArrivesFrom.opposite(directionVisitorArrivesFrom);
            }
        }
    }
}

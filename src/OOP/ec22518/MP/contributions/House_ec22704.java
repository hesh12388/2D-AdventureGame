package OOP.ec22518.MP.contributions;

class House_ec22704 implements Visitable {

    private final int numberRooms = 3;

    private Room[] rooms;

    // Assigns rooms to the house version 2
    House_ec22704() {
        rooms = new Room[numberRooms];
        rooms[0] = new Room_ec22897();
        rooms[1] = new Room_ec22748();
        rooms[2] = new Room_ec22704();
    }

    public Direction visit(Visitor visitor, Direction direction) {
        
        boolean escape = false;

        int currentIndex = 0;

        Room currentRoom = rooms[currentIndex];

        // Greet the visitor and welcome them to the house
        visitor.tell("Welcome to the house! You are currently in Room " + currentIndex);

        Direction wayPoint = currentRoom.visit(visitor, Direction.TO_EAST);
      

        while (!escape) {

            // Allow north way
            if (wayPoint == Direction.TO_NORTH) {
                // Inform the visitor that the door is fake and they have left the house
                visitor.tell("Oops! That door is fake...you have now left the house");
                escape = true;
            }

            // Allow west way
            else if (wayPoint == Direction.TO_WEST) {
                if (currentIndex == 0) {
                    // Inform the visitor that there is no door on this side
                    visitor.tell("Sorry, there is no door on this side");
                } else {
                    currentIndex--;
                    currentRoom = rooms[currentIndex];
                    // Inform the visitor which room they have entered
                    visitor.tell("You are now entering Room " + currentIndex);
                }
            }

            // Allow east way
            else if (wayPoint == Direction.TO_EAST) {
                if (currentIndex == numberRooms - 1) {
                    // Inform the visitor that there is no door on this side
                    visitor.tell("Sorry, there is no door on this side");
                } else {
                    currentIndex++;
                    currentRoom = rooms[currentIndex];
                    // Inform the visitor which room they have entered
                    visitor.tell("You are now entering Room " + currentIndex);
                }
            }

            // Allow south way
            else if (wayPoint == Direction.TO_SOUTH) {
                if (currentRoom == rooms[1]) {
                    // Inform the visitor that the door is stuck
                    visitor.tell("Oops! This door is stuck, sorry!");
                } else {
                    // Inform the visitor that they have discovered a secret door and lead them to room 1
                    visitor.tell("Congratulations! You have discovered a secret door to Room 1.");
                    currentRoom = rooms[1];
                    currentIndex = 1;
                }
            }

            wayPoint = currentRoom.visit(visitor, wayPoint);
        }

        // Return the opposite direction of the last wayPoint visited
        return wayPoint.opposite(direction);
    }
}

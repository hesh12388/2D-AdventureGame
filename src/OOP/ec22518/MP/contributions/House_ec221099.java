package OOP.ec22518.MP.contributions;

class House_ec221099 implements Visitable {
    Room[][] coordGrid = new Room[3][3];
    
    public House_ec221099() { 
        coordGrid[0][0] = Contributions.newRoomByUsername("ec221099");
        coordGrid[0][1] = Contributions.newRoomByUsername("ec22960");
        coordGrid[0][2] = Contributions.newRoomByUsername("ec22914");
        coordGrid[1][0] = Contributions.newRoomByUsername("ec221099");
        coordGrid[1][1] = Contributions.newRoomByUsername("ec221099");
        coordGrid[1][2] = Contributions.newRoomByUsername("ec221099");
        coordGrid[2][0] = Contributions.newRoomByUsername("ec221099");
        coordGrid[2][1] = Contributions.newRoomByUsername("ec221099");
        coordGrid[2][2] = Contributions.newRoomByUsername("ec221099");
    }

    public Direction visit(Visitor visitor, Direction direction) {
        int[] coords = {0,0};
        boolean getChoice = true;

        Direction newDirection = coordGrid[0][0].visit(visitor, direction);
        while(getChoice) {
            switch (visitor.getChoice("House: Do you want to " +
                                "q) leave the Test House or enter the Room from the " +
                                "s) South " +
                                "w) West " +
                                "n) North or " +
                                "e) East",
                                new char[] {'d', 's', 'w', 'n', 'e', 'q'})) {
                case 'd': break;
                case 's': direction = Direction.FROM_SOUTH; coords[0] += 1; break;
                case 'w': direction = Direction.FROM_WEST; coords[1] += 1; break;
                case 'n': direction = Direction.FROM_NORTH; coords[0] -= 1; break;
                case 'e': direction = Direction.FROM_EAST; coords[1] -= 1; break;
                case 'q': getChoice = false;
                default: visitor.tell("This isn't looking good brev.");
            }
            if(coords[0] < 0 || coords[1] < 0 || coords[0] > 2 || coords[1] > 2){
                getChoice = false;
            }else{
                newDirection = coordGrid[coords[0]][coords[1]].visit(visitor, direction);
            }
        } 
        return direction;
    }
}
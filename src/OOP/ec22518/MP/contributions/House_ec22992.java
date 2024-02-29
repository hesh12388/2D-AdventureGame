package OOP.ec22518.MP.contributions;

class House_ec22992 implements Visitable {
    // A reusable class for an item that can be taken, can be used in place of a regular item.
    static class TakeableItem extends Item {
        private boolean isTaken;

        public TakeableItem(String name) {
            super(name);
            isTaken = false;
        }

        public boolean tryGiveTo(Visitor visitor) {
            if (isTaken) return false;

            visitor.giveItem(this);
            return isTaken = !isTaken;
        }

        public boolean isTaken() { return isTaken; }
    }

    // A utility class that allows one to convert a character to a direction.
    static final class Utils {
        public static Direction toDirection(char character) {
            switch (character) {
                case 'N':
                    return Direction.TO_NORTH;
                case 'E':
                    return Direction.TO_EAST;
                case 'S':
                    return Direction.TO_SOUTH;
                case 'W':
                    return Direction.TO_WEST;
            }
            return null;
        }
    }

    // A private (accessible only in my house) garden associated with an instance of this class, hence non-static.
    private class Garden extends Room {
        public Direction visit(Visitor visitor, Direction startingDirection) {
            visitor.tell("> You arrive in the garden of a large house, however you do not have the house key.");
            visitor.tell("> The owner told you that the house key would be somewhere nearby, so you go looking for it.");
        
            while (!HOUSE_KEY.isTaken()) {
                char choice = visitor.getChoice("What do you do next?\na) Rummage through the foliage\nb) Inspect the peculiar dirt mound\n> ", new char[] {'a', 'b'});
                switch (choice) {
                    case 'a':
                        visitor.tell("> You put your hands in the flower bed... but find nothing.");
                        break;
                    case 'b':
                        visitor.tell("> You approach the dirt mound, it seems as if somebody has tried to hide something...?");

                        boolean doesDig = visitor.getChoice("Do you dig? (y/n): > ", new char[] {'y', 'n'}) == 'y';

                        if (doesDig) {
                            visitor.tell("> You dig rushingly until you hit a metal chest.");
                            visitor.tell("> You open the chest and there is the house key, you take it!");
                            HOUSE_KEY.tryGiveTo(visitor);
                        }
                        break;
                    default:
                        visitor.tell("> That is not a valid choice.");
                        break;
                }
            }

            return startingDirection;
        }
    }

    // A private (accessible only in my house) hallway associated with an instance of this class, hence non-static.
    private class Hallway extends Room {
        public Direction visit(Visitor visitor, Direction startingDirection) {
            visitor.tell("> You are in the hallway.");

            char direction = visitor.getChoice("Which direction do you go?\n(N)orth\n(E)ast\n(S)outh\n(W)est\n> ", new char[] {'N', 'E', 'S', 'W'});
            return Utils.toDirection(direction);
        }
    }

    // An enum for creating a readable and expressive way of managing leaving states.
    enum LeavingType {
        Room,
        House
    }

    // A coordinate record (intended to be immutable) with a generic helper method to check if it is out of bounds of a given 2D array.
    private final class Coordinate {
        private int row;
        private int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public <T> boolean outOfBounds(T[][] array) {
            return row < 0 || row >= array.length || col < 0 || col >= array[row].length;
        }
    }

    private final TakeableItem HOUSE_KEY;
    private final Room[][] FLOOR_PLAN;
    private final Garden GARDEN;
    private final Hallway HALLWAY;

    private Coordinate coordinate;

    public House_ec22992() {
        HOUSE_KEY = new TakeableItem("House Key");
        HALLWAY = new Hallway();
        GARDEN = new Garden();
        FLOOR_PLAN = new Room[][] {
            { new Room_ec22992(), HALLWAY, new Room_ec19389() },
            { new Room_ec22617(), HALLWAY, new Room_ec22912() }
        };
        coordinate = new Coordinate(1, 1);
    }

    // A method that finds the next room given a direction from the current coordinate, returning a new coordinate.
    private Coordinate findNextRoom(Direction direction) {
        for (int row = 0; row < FLOOR_PLAN.length; row++) {
            for (int col = 0; col < FLOOR_PLAN[col].length; col++) {
                if (row == coordinate.row && col == coordinate.col) {
                    if (direction == Direction.TO_NORTH) return new Coordinate(row-1, col);
                    else if (direction == Direction.TO_EAST) return new Coordinate(row, col+1);
                    else if (direction == Direction.TO_SOUTH) return new Coordinate(row+1, col);
                    else if (direction == Direction.TO_WEST) return new Coordinate(row, col-1);
                }
            }
        }
        return null;
    }

    public Direction visit(Visitor visitor, Direction startingDirection) {
        // Initially visit the garden (it is not on the floor plan as you can only visit it once).
        GARDEN.visit(visitor, startingDirection);

        LeavingType leavingType = null;

        while (leavingType == null || leavingType != LeavingType.House) {
            Room currentRoom = FLOOR_PLAN[coordinate.row][coordinate.col];
            coordinate = findNextRoom(currentRoom.visit(visitor, startingDirection));

            // If we are going out of bounds from the hallway, we are leaving the house.
            if (coordinate.outOfBounds(FLOOR_PLAN) && currentRoom == HALLWAY)
                leavingType = LeavingType.House;
            // Otherwise, if they are going out of bounds, then send them back to the hallway.
            else if (coordinate.outOfBounds(FLOOR_PLAN))
                coordinate = new Coordinate(1, 1);
        }

        return Direction.opposite(startingDirection);
    }
}
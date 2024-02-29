package OOP.ec22518.MP.contributions;

import java.util.ArrayList;
import java.util.List;

public class House_ex21566 implements Visitable {

    private final Item[] forbiddenItems = new Item[] {
            new Item("gun"),
            new Item("Anabolic Steroids"),
    };

    private final Room[][] rooms = {
            {new Room_ex21566(), new Room_ex20539()},
            {new Room_ex21299(), new Room_ex21423()},
            {new Room_ex21626(), null}
    };
    //     0     1
    //  0 Room, Room //
    //  1 Room, Room //
    //  2 Room // <--- Lobby

    //  Room_ex21495 is the first room of the house, which can be only entered from the south.
    private int row = 2;
    private int col = 0;

    @Override
    public Direction visit(Visitor visitor, Direction directionVisitorArrivesFrom) {
        boolean leaveHouse = false;

        if (directionVisitorArrivesFrom == Direction.FROM_SOUTH) {

            visitor.tell("You entered the house and are in the lobby");

            do {
                Direction direction = rooms[row][col].visit(visitor, directionVisitorArrivesFrom);

                if (row == 2 && col == 0 && direction == Direction.TO_SOUTH) {
                    visitor.tell("You are leaving the house...");
                    leaveHouse = true;
                }

                if (getValidDirections().contains(direction)) {
                    traverseCorridor(visitor, direction);
                } else if (direction == null || direction == Direction.UNDEFINED) {
                    visitor.tell("Undefined direction, you are leaving the house.");
                    leaveHouse = true;
                } else {
                    visitor.tell("Invalid direction, you are hitting a wall.\n" +
                            "You are still in the room.");
                    continue;
                }

                if (doesVisitorHaveForbiddenItem(visitor)) {
                    visitor.tell("You were found having a forbidden item.\n" +
                            "You must leave the premises.");

                    if (visitor.hasEqualItem(new Item("Bible"))) {
                        visitor.tell("You had a bible, yet you took these items");
                        visitor.takeGold(10);
                    }

                    leaveHouse = true;
                }
            } while (!leaveHouse);
        } else
            visitor.tell("House cannot be accessed from this direction.\n" +
                    "Existing house...");

        return Direction.opposite(Direction.FROM_SOUTH);
    }

    private void traverseCorridor(Visitor visitor, Direction direction) {
        int[] nextCoordinates = directionToCoordinates(direction);
        int nextRow = nextCoordinates[0], nextCol = nextCoordinates[1];
        boolean visible = false;

        if (visitor.hasEqualItem(new Item("candle")) ||
                visitor.hasEqualItem(new Item("Glow Stick"))) {
            visible = true;
        }

        if (corridorBetween(2, 0, 1, 0, nextRow, nextCol)) {
            if (direction == Direction.TO_NORTH) visitor.tell("You are leaving the lobby.");
            else visitor.tell("You are going to the lobby.");

            visitor.tell("The corridor has some old candles. You can see the old furniture.");
        } else if (corridorBetween(1, 0, 0, 0, nextRow, nextCol)) {
            if (visible)
                visitor.tell("You have a light source and can see the corridor with some ease\n" +
                        ", the corridor has some bookshelves and chair some overturned chairs, you avoid them.");
            else
                visitor.tell("The corridor is very dark and you stumble on lots of things.\n" +
                        "Somehow you managed to get to the other room safely");

        } else if (corridorBetween(1, 0, 1, 1, nextRow, nextCol)) {
            if (visible)
                visitor.tell("You have a light source and can see the corridor with some ease\n" +
                        ", the corridor is empty, some spider web and nothing else.");
            else
                visitor.tell("The corridor is very dark and you walk slowly towards the next room\n" +
                        "blindly, you managed to find the door.");
        } else if (corridorBetween(0, 0, 0, 1, nextRow, nextCol)) {
            visitor.tell("The moonlight illuminates the corridor through the open window.\n" +
                            "The corridor seems well maintained, the floor is clean and the furniture seems well ");
        } else if (corridorBetween(0, 1, 1, 1, nextRow, nextCol)) {
            visitor.tell("The corridor is very modern, you traverse it without any issues.");
        }

        row = nextRow;
        col = nextCol;
    }

    private boolean corridorBetween(int startRow, int startCol, int endRow, int endCol, int nextRow, int nextCol) {
        return (row == startRow && col == startCol && nextRow == endRow && nextCol == endCol) ||
                (row == endRow && col == endCol && nextRow == startRow && nextCol == startCol);
    }

    private int[] directionToCoordinates(Direction direction) {
        int nextRow = row, nextCol = col;

        if (direction == Direction.TO_NORTH)
            nextRow--;
        else if (direction == Direction.TO_EAST)
            nextCol++;
        else if (direction == Direction.TO_SOUTH)
            if (nextRow != 2)
                nextRow++;
        else if (direction == Direction.TO_WEST)
            nextCol--;

        return new int[]{nextRow, nextCol};
    }

    private List<Direction> getValidDirections() {
        List<Direction> validDirections = new ArrayList<>();

        for (int i = row - 1; i <= row + 1; i++)
            for (int j = col - 1; j <= col + 1; j++)
                if (0 <= i && i < rooms.length && 0 <= j && j < rooms[0].length)
                    if ((i != row || j != col) && rooms[i][j] != null)
                        if (isAdjacent(i, j))
                            validDirections.add(getDirection(i, j));

        if (row == 2 && col == 0) validDirections.add(Direction.TO_SOUTH);

        return validDirections;
    }

    private boolean doesVisitorHaveForbiddenItem(Visitor visitor) {
        for (Item item: forbiddenItems)
            if (visitor.hasEqualItem(item))
                return true;

        return false;
    }

    private boolean isAdjacent(int i, int j) {
        double xDiffSquared = Math.pow(i - row, 2);
        double yDiffSquared = Math.pow(j - col, 2);

        return Math.pow(xDiffSquared + yDiffSquared, 2) == 1;
    }

    private boolean isAdjacent(int currentRow, int currentCol, int nextRow, int nextCol) {
        double xDiffSquared = Math.pow(nextRow - currentRow, 2);
        double yDiffSquared = Math.pow(nextCol - currentCol, 2);

        return Math.pow(xDiffSquared + yDiffSquared, 2) == 1;
    }

    private Direction getDirection(int i, int j) {
        if (row - 1 == i) return Direction.TO_NORTH;
        if (row + 1 == i) return Direction.TO_SOUTH;
        if (col - 1 == j) return Direction.TO_WEST;
        if (col + 1 == j) return Direction.TO_EAST;
        return Direction.UNDEFINED;
    }
}
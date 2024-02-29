package OOP.ec22518.MP.contributions;

public class House_ec22720 implements Visitable {
    static final Room[][] rooms = {
        new Room[] {
            new Room_ec22927(),
            new Room_ec22720()
        },
        new Room[] {
            new Room_ec221183(),
            new Room_ec22720()
        }
    };

    int northSouthIndex = 0;
    int westEastIndex = 0;


    public Direction visit(Visitor visitor, Direction direction)
    {
        visitor.tell("You enter a house.");

        if (direction == Direction.FROM_SOUTH || direction == Direction.TO_NORTH)
        {
            northSouthIndex = 1;
        }

        if (direction == Direction.FROM_EAST || direction == Direction.TO_WEST)
        {
            westEastIndex = 1;
        }

        Direction currentDirection = direction;
        while (westEastIndex >= 0 && northSouthIndex >= 0 && westEastIndex < rooms[0].length && northSouthIndex < rooms.length)
        {
            String northSouth = "";
            if (northSouthIndex == 0)
            {
                northSouth = "north";
            }
            else if (northSouthIndex == 1)
            {
                northSouth = "south";
            }

            String westEast = "";
            if (westEastIndex == 0)
            {
                westEast = "western";
            }
            else if (westEastIndex == 1)
            {
                westEast = "eastern";
            }

            visitor.tell("You are entering the " + northSouth + " " + westEast + " room of the house.");

            currentDirection = rooms[northSouthIndex][westEastIndex].visit(visitor, direction);
            
            if (currentDirection == Direction.TO_NORTH || currentDirection == Direction.FROM_SOUTH)
            {
                northSouthIndex--;
            }
            else if (currentDirection == Direction.TO_SOUTH || currentDirection == Direction.FROM_NORTH)
            {
                northSouthIndex++;
            }
            else if (currentDirection == Direction.TO_EAST || currentDirection == Direction.FROM_WEST)
            {
                westEastIndex++;
            }
            else if (currentDirection == Direction.TO_WEST || currentDirection == Direction.FROM_EAST)
            {
                westEastIndex--;
            }
            else
            {
                visitor.tell("You were sent in " + currentDirection + " direction, which is not a valid direction.");
            }

            visitor.tell("northsouth: " + northSouthIndex + " westeast: " + westEastIndex);
        }

        return currentDirection; // todo
    }
}

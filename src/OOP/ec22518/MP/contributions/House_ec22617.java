package OOP.ec22518.MP.contributions;

import java.util.Random;

public class House_ec22617 implements Visitable {
    private Room[] rooms;
    private int roomSideLength;
    private Direction previousDirection;
    private int x, y;

    static class RoomChangeEvent {
        public enum Type {
            None,
            OutOfBounds,
            Leaving,
        }

        private Type event;
        private Direction leavingTo;

        public RoomChangeEvent(Type event, Direction... leavingTo) {
            if (event == null) 
                this.event = Type.None;
            else 
                this.event = event;

            if (this.isLeaving())
                this.leavingTo = leavingTo[0];
        }

        public boolean isOutOfBounds() {
            if (event == Type.OutOfBounds || event == Type.Leaving) return true;
            else return false;
        }

        public boolean isLeaving() {
            if (event == Type.Leaving) return true;
            else return false;
        }

        public Direction getLeavingDirection() {
            return this.leavingTo;
        }
    };


    public House_ec22617() {
        // by default we have a 3x3 house
        this(9);
    }

    public House_ec22617(int roomSideLength) {
        this.roomSideLength = roomSideLength;
        this.rooms = getRandomRooms();

        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return this.x;
    }

    public RoomChangeEvent setX(int x) {
        if (x >= 0 || x < this.roomSideLength) {
            this.x = x;
            return new RoomChangeEvent(null);
        }

        return this.boundaryChecks(x, this.getY());
    }

    public int getY() {
        return this.y;
    }

    public RoomChangeEvent setY(int y) {
        if (y >= 0 || y < this.roomSideLength) {
            this.y = y;
            return new RoomChangeEvent(null);
        }

        return this.boundaryChecks(this.getX(), y);
    }

    public RoomChangeEvent boundaryChecks(int x, int y) {
        // check if leaving or just out of bounds 
        if (x < 0 && y == (this.roomSideLength/2)) return new RoomChangeEvent(RoomChangeEvent.Type.Leaving, Direction.TO_WEST);
        else if (x >= this.roomSideLength && y == (Math.round((double) this.roomSideLength/2))) return new RoomChangeEvent(RoomChangeEvent.Type.Leaving, Direction.TO_EAST);
        else if (y < 0 && x == Math.round((double) this.roomSideLength/2)) return new RoomChangeEvent(RoomChangeEvent.Type.Leaving, Direction.TO_NORTH);
        else if (y > 0 && x == Math.round((double) this.roomSideLength/2)) return new RoomChangeEvent(RoomChangeEvent.Type.Leaving, Direction.TO_SOUTH);
        else return new RoomChangeEvent(RoomChangeEvent.Type.OutOfBounds);
    }

    public void changeSize(int roomSideLength) {
        this.roomSideLength = roomSideLength;
        this.rooms = getRandomRooms();
    }

    public Room[] getRandomRooms() {
        if (this.roomSideLength < 1) return new Room[0];

        final int count = (int) Math.pow(this.roomSideLength, 2);

        Room[] randomRooms = new Room[count];

        for (int i = 0; i < count; i++) {
            randomRooms[i] = getRandomRoom();
        }

        return randomRooms;
    }

    public void onRoomEnter(Direction direction) {
        if (direction == Direction.FROM_NORTH) {
            this.setX((int) Math.round((double) this.roomSideLength / 2));
            this.setY(0);
        }
        else if (direction == Direction.FROM_EAST) {
            this.setX(this.roomSideLength - 1);
            this.setY((int) Math.round((double) this.roomSideLength / 2));
        }
        else if (direction == Direction.FROM_SOUTH) {
            this.setX((int) Math.round((double) this.roomSideLength / 2));
            this.setY(this.roomSideLength - 1);
        }
        else if (direction == Direction.FROM_WEST) {
            this.setX(0);
            this.setY((int) Math.round((double) this.roomSideLength / 2));
        } else {
            System.err.println("Unknown direction: continueing from (0, 0)");
            this.setX(0);
            this.setY(0);
        }

        this.previousDirection = direction;
    }

    public RoomChangeEvent moveDirection(Direction direction) {
        RoomChangeEvent roomChangeEvent = new RoomChangeEvent(RoomChangeEvent.Type.None);

        if (direction == Direction.TO_NORTH) roomChangeEvent = this.setY(this.getY() - 1);
        else if (direction == Direction.TO_EAST) roomChangeEvent = this.setX(this.getX() + 1);
        else if (direction == Direction.TO_SOUTH) roomChangeEvent = this.setY(this.getY() + 1);
        else if (direction == Direction.TO_WEST) roomChangeEvent = this.setX(this.getX() - 1);
        else {
            System.err.println("Unknown direction to move to");
        }

        this.previousDirection = direction;
        return roomChangeEvent;
    }

    public Room getCurrentRoom() {
        return this.rooms[(this.getY() * this.roomSideLength) + this.getX()];
    }

    public Room getRandomRoom() {
        String[] allUsernames = Contributions.getRoomUsernames();
        return Contributions.newRoomByUsername(allUsernames[new Random().nextInt(allUsernames.length)]);
    }

    public Direction visit(Visitor visitor, Direction directionVisitorArrivesFrom) {
        // user enter on the midpoint of that cardinal direction, and they leave from the midpoint of any 'wall'
        // otherwise just wrap around

        this.onRoomEnter(directionVisitorArrivesFrom);
        RoomChangeEvent lastEvent;

        do {
            // visit room
            Direction exitDirection = getCurrentRoom().visit(visitor, this.previousDirection);

            // move to next room
            lastEvent = moveDirection(exitDirection);
        } while (!lastEvent.isLeaving());

        return lastEvent.getLeavingDirection();
    }
}

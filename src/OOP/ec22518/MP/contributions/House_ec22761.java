package OOP.ec22518.MP.contributions;

public class House_ec22761 implements Visitable {

    private Room[][] rooms;
    private Room center;

    private int goldInBasement = 10;
    private int goldInChest = 8;
    private int TVGold = 10;
    private int sandGold = 6;

    /**
     * Creates a new House, thanks to all the following people's classes.
     * @see Room_ec22414
     * @see Room_ec221247
     * @see Room_ah21407
     * @see Room_ec22974
     * @see Room_ec22982
     * @see Room_ec211045
     * @see Room_ec221028
     * @see Room_ec221136
     */
    public House_ec22761() {
        Room[][] rooms = new Room[4][2]; // 0N 1E 2S 3W
        this.center = new Room_ec22414();
        // North Wing
        rooms[0][0] = new Room_ec22761(); rooms[0][1] = new Room_ec221247();
        // East Wing
        rooms[1][0] = new Room_ah21407(); rooms[1][1] = new Room_ec22974();
        // South Wing
        rooms[2][0] = new Room_ec22982(); rooms[2][1] = new Room_ec211045();
        // West Wing
        rooms[3][0] = new Room_ec221028(); rooms[3][1] = new Room_ec221136();

        this.rooms = rooms;
    }

    private Direction visitCorridor(Visitor v, Direction d, Direction directionOfEntrance) {
        if (directionOfEntrance == Direction.FROM_NORTH) return visitNorthCorridor(v,d);
        if (directionOfEntrance == Direction.FROM_EAST) return visitEastCorridor(v,d);
        if (directionOfEntrance == Direction.FROM_SOUTH) return visitSouthCorridor(v,d);
        return visitWestCorridor(v, d);
    }

    private Direction visitNorthCorridor(Visitor v, Direction d) {
        v.tell("You enter a long corridor on the North Side of the building, it has a strange chest propped against a wall.");
        if(v.hasEqualItem(new Item("Key")) && goldInChest > 0) {
            v.tell("You open the chest, and inside you find " + goldInChest  + " gold!");
            v.giveGold(goldInChest);
            goldInChest = 0;
        }

        v.tell("You continue to the next room.");
        return Direction.opposite(d);
    }

    private Direction visitEastCorridor(Visitor v, Direction d) {
        v.tell("You enter a small corridor on the East Side of the building.");
        if(v.hasEqualItem(new Item("Rope"))) {
            v.tell("There is a trapdoor in the floor, leading to a basement.");
            if(v.getChoice("Would you like to use your rope to lower yourself down?\n\t(y/n)", new char[]{'y','n'}) == 'y') {
                v.tell("You lower yourself into the basement and find " + goldInBasement + " gold!");
                v.giveGold(goldInBasement);
                this.goldInBasement = 0;
            }
            v.tell("You climb out of the basement.");
        } else {
            v.tell("There is a trapdoor in the floor, leading to a basement. If only you had something to lower yourself down with...");
        }
        v.tell("You continue to the next room.");
        return Direction.opposite(d);
    }

    private Direction visitSouthCorridor(Visitor v, Direction d) {
        v.tell("You enter the south corridor, there's a TV in the corner with a PS5 plugged in, but no controller.");
        if(v.hasEqualItem(new Item("PS5 Controller")) && TVGold > 0) {
            v.tell("You plug the controller you found in the gaming room into the console, it lights up and spits out " + TVGold + " gold!");
            v.giveGold(TVGold);
            TVGold = 0;
        }
        v.tell("You continue to the next room.");
        return Direction.opposite(d);
    }

    private Direction visitWestCorridor(Visitor v, Direction d) {
        v.tell("You enter the west corridor, in the center, is a bowl full of grainy, sand like pieces of raw gold.\nThey fall through your hands when you try to pick them up. If only you had something to carry them with...");
        if(v.hasEqualItem(new Item("bag")) && sandGold >0) {
            v.tell("Thankfully, you have a bag! You use the bag to scoop up all the gold.");
            v.giveGold(sandGold);
            sandGold = 0;
        }
        return Direction.opposite(d);
    }

    /**
     * Visits the wing passed in, either from the middle of the house,
     * or from the outside
     *
     * @param v The visitor investigating the house
     * @param entering Whether they are traversing the wing from outside or inside
     * @param directionOfEntrance Which wing they are visiting
     * @return The direction they exit the wing
     */
    private Direction visitWing(Visitor v, boolean entering, Direction directionOfEntrance) {
        Room[] rooms = getWing(directionOfEntrance);
        if(entering) {
            return center.visit(v, rooms[1].visit(v, visitCorridor(v, rooms[0].visit(v,directionOfEntrance), directionOfEntrance)));
        } else {
            return rooms[0].visit(v, visitCorridor(v,rooms[1].visit(v, Direction.opposite(directionOfEntrance)), directionOfEntrance));
        }
    }

    /**
     * Handles exiting the center of the building, calling {@link #visitWing(Visitor, boolean, Direction)}.
     *
     * @param directionExitingFrom Direction the visitor exits the center
     * @param v The visitor investigating the house
     */
    private void exitCenter(Direction directionExitingFrom, Visitor v) {
        if(directionExitingFrom == Direction.TO_NORTH) { // 'TO' to account for slight error in center rooms code
            this.visitWing(v, false, Direction.FROM_NORTH);
            return;
        } else if (directionExitingFrom == Direction.TO_EAST) {
            this.visitWing(v, false, Direction.FROM_EAST);
            return;
        } else if (directionExitingFrom == Direction.TO_SOUTH) {
            this.visitWing(v, false, Direction.FROM_SOUTH);
            return;
        }
        this.visitWing(v, false, Direction.FROM_WEST);
    }

    /**
     * Gets the rooms in the passed wing
     *
     * @param direction The direction of the wing to get
     * @return The wing's rooms
     */
    private Room[] getWing(Direction direction) {
        if (direction == Direction.FROM_NORTH) { return this.rooms[0]; } //N
        else if (direction == Direction.FROM_EAST) { return this.rooms[1]; } //E
        else if (direction == Direction.FROM_SOUTH) { return this.rooms[2]; } //S
        return this.rooms[3]; //W
    }

    /**
     * Explores the wing selected by the visitor
     *
     * @param v The visitor investigating the house
     */
    private void exploreHouse(Visitor v) {
        v.tell("You are in the garden of the Winchester Mystery House.\nYou see the house takes the shape of a large cross, through the window you see it is built up of 9 rooms and 4 corridors.");
        char r = v.getChoice("Would you like to enter from:\n\tNorth (n)\n\tSouth (s)\n\tEast (e)\n\tWest (w)", new char[]{'n', 's', 'e', 'w'});
        if(r == 'n') this.exitCenter(this.visitWing(v, true, Direction.FROM_NORTH), v);
        if(r == 's') this.exitCenter(this.visitWing(v, true, Direction.FROM_SOUTH), v);
        if(r == 'e') this.exitCenter(this.visitWing(v, true, Direction.FROM_EAST), v);
        if(r == 'w') this.exitCenter(this.visitWing(v, true, Direction.FROM_WEST), v);
    }

    /**
     * Visits the Winchester Mystery House, loops until the visitor chooses to leave.
     *
     * @param v The visitor investigating the house
     * @param d The direction the visitor enters from
     * @return The direction the visitor enters from
     */
    public Direction visit(Visitor v, Direction d) {
        boolean leaving = false;
        while(!leaving) {
            this.exploreHouse(v);
            if(v.getChoice("You are back in the garden. Would you like to leave?\n\t(y/n)", new char[]{'y', 'n'}) == 'y') {
                leaving = true;
            }
        }
        return d;
    }
}

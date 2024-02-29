package OOP.ec22518.MP.contributions;

class House_ec22415 implements Visitable {
    Room room_north;
    Room room_east;
    Room room_west;
    Direction dir;

    public House_ec22415() {
        room_north = new Room_ec22415();
        room_east = new Room_ec22429();
        room_west = new Room_ec22450();
        dir = Direction.UNDEFINED;
    }

    public Direction visit(Visitor v, Direction d) {

        boolean exit = false;
        dir = start(v, d);

        while (!exit) {
            boolean exit1 = false;
            boolean exit2 = false;
            if (dir == Direction.UNDEFINED) {
                exit = true;
            }

            if (dir == Direction.TO_SOUTH) {
                dir = start(v, dir);
            }

            if (dir == Direction.TO_NORTH) {
                dir = room_north.visit(v, dir);
                if (dir == Direction.TO_NORTH) {
                    while (!exit1) {
                        dir = garden(v, dir);
                        if (dir == Direction.TO_SOUTH) {
                            dir = room_north.visit(v, d);
                        } else if (dir == Direction.UNDEFINED) {
                            exit1 = true;
                        }
                    }
                }
            }

            if (dir == Direction.TO_EAST) {
                dir = room_east.visit(v, d);
                if (dir == Direction.TO_EAST) {
                    while (!exit2) {
                        dir = window(v, dir);
                        if (dir == Direction.TO_SOUTH) {
                            exit2 = true;
                        } else if (dir == Direction.UNDEFINED) {
                            exit2 = true;
                        }
                    }
                }
                if (dir == Direction.TO_SOUTH) {
                    dir = garden(v, dir);
                }
            }

            if (dir == Direction.TO_WEST) {
                dir = room_west.visit(v, dir);
                if (dir == Direction.TO_NORTH) {
                    dir = garden(v, dir);
                }
                if (dir == Direction.TO_WEST) {
                    dir = window(v, dir);
                }
                if (dir == Direction.TO_EAST) {
                    dir = start(v, dir);
                }
                if (dir == Direction.TO_SOUTH) {
                    dir = start(v, dir);
                }
            }
        }
        return dir;
    }

    public static Direction start(Visitor v, Direction d) {
        if (d == Direction.TO_SOUTH) {
            v.tell("You are at the front door once again.");
        }

        v.tell("Welcome to the Winchester Mystery House!");
        v.tell("You are at the front door.");

        char choice = v.getChoice("Enter the house? yes(y) or no(n)", new char[]{'y', 'n'});
        if (choice == 'y') {
            v.tell("Which direction do you want to go in? a-d");
            switch (v.getChoice("a) North b) East c) west d) leave the house", new char[]{'a', 'b', 'c', 'd'})) {
                case 'a':
                    v.tell("You are going north.");
                    return Direction.TO_NORTH;
                case 'b':
                    v.tell("You are going east.");
                    return Direction.TO_EAST;
                case 'c':
                    v.tell("You are going west.");
                    return Direction.TO_WEST;
                case 'd':
                    v.tell("Thank you for visiting!");
                    break;
            }
        }

        v.tell("Good bye!");

        return Direction.UNDEFINED;
    }

    public static Direction garden(Visitor v, Direction d) {
        v.tell("You are in a beautiful garden.");
        v.tell("You spot a hole in the fence.");
        v.tell("It looks big enough to climb through.");
        v.tell("What do you do?");

        switch (v.getChoice("a) leave through the hole in the fence b) go back into the house", new char[]{'a', 'b'})) {
            case 'a':
                v.tell("You are leaving the house.");
                v.tell("Good bye!");
                return Direction.UNDEFINED;
            case 'b':
                v.tell("You go back into the room that you came from!");
                return Direction.opposite(d);
        }
        return Direction.UNDEFINED;
    }

    public static Direction window(Visitor v, Direction d) {

        v.tell("You run up to a window that is wide open.");
        v.tell("You fell the cool breeze coming through the window.");
        v.tell("Perhaps it's time to call it ");

        switch (v.getChoice("a) Jump out the window and leave the house b) Go back to the front of the house to start over", new char[]{'a', 'b'})) {
            case 'a':
                v.tell("You are leaving the house.");
                v.tell("Good bye!");
                return Direction.UNDEFINED;
            case 'b':
                v.tell("You are going back to the start of the house.");
                return Direction.TO_SOUTH;
        }

        return Direction.UNDEFINED;
    }
}

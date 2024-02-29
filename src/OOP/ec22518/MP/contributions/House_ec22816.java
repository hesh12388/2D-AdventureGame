package OOP.ec22518.MP.contributions;

class House_ec22816 implements Visitable {
    Room_ec22816 room1;
    Room_ec22585 room2;
    Room_ec221025 room3;
    Room_ec22520 room4;
    Room_ec221148 room5;
    Room[] rooms;

    final static Item key = new Item("key");

    static boolean painting = true;

    static boolean searched = false;

    public House_ec22816() {
        // init room objects & array
        room1 = new Room_ec22816();
        room2 = new Room_ec22585();
        room3 = new Room_ec221025();
        room4 = new Room_ec22520();
        room5 = new Room_ec221148();
        rooms = new Room[] {room1, room2, room3, room4, room5};
    }

    public Direction visit(Visitor v, Direction d) {
        v.tell("You have entered House_ec22816. Welcome!");
        v.tell("Your adventure begins in Room_ec22816.");

        int loc = 1;

        // loc0 = exit
        // loc1 = start
        // loc2 = east to rooms[1] eHall
        // loc3 = north to rooms[2] from rooms[0]
        // loc4 = north to rooms[4] from rooms[1]
        // loc5 = west to rooms[0] eHall
        // loc6 = east to rooms[3] from rooms[2] | west to rooms[3] from rooms[4]
        // loc7 = south to rooms[1] from rooms[4]

        while(loc != 0) {
            if(loc == 1) {
                d = rooms[0].visit(v, d);
                if(d == Direction.TO_EAST)
                    loc = 2;
                else if(d == Direction.TO_NORTH)
                    loc = 3;
                else {
                    d = exit(v, d);
                    loc = 0;
                }
            } else if(loc == 2) {
                eHall(v, rooms[1]);
                d = rooms[1].visit(v, d);
                if(d == Direction.TO_NORTH)
                    loc = 4;
                else if(d == Direction.TO_WEST)
                    loc = 5;
                else {
                    d = exit(v, d);
                    loc = 0;
                }
            } else if(loc == 3) {
                key(v, rooms[2]);
                d = rooms[2].visit(v, d);
                if(d == Direction.TO_EAST)
                    loc = 6;
                else if(d == Direction.TO_SOUTH)
                    loc = 1;
                else {
                    d = exit(v, d);
                    loc = 0;
                }
            } else if(loc == 4) {
                key(v, rooms[4]);
                d = rooms[4].visit(v, d);
                if(d == Direction.TO_SOUTH)
                    loc = 7;
                else if(d == Direction.TO_WEST)
                    loc = 6;
                else {
                    d = exit(v, d);
                    loc = 0;
                }
            } else if(loc == 5) {
                eHall(v, rooms[0]);
                loc = 1;
            } else if(loc == 6) {
                key(v, rooms[3]);
                d = rooms[3].visit(v, d);
                if(d == Direction.TO_WEST)
                    loc = 3;
                else if(d == Direction.TO_EAST)
                    loc = 4;
                else {
                    d = exit(v, d);
                    loc = 0;
                }
            }else if(loc == 7) {
                key(v, rooms[1]);
                d = rooms[1].visit(v, d);
                if(d == Direction.TO_NORTH)
                    loc = 4;
                else if(d == Direction.TO_WEST)
                    loc = 5;
                else {
                    d = exit(v, d);
                    loc = 0;
                }
            }
        }

        return d;
    }

    //
    // Visit the East hall & receive gold
    //
    void eHall(Visitor v, Room next) {
        v.tell("You have entered the East hallway.");
        v.tell("The walls are decorated with various vintage paintings.");
        if(painting) {
            v.tell("One of the paintings appears to be crooked.");
            if(v.getChoice("Do you stop to arrange it?" +
                            "\na) Yes" +
                            "\nb) No",
                    new char[] {'a', 'b'}) == 'a') {
                v.tell("As you move the painting, a small hidden safe opens beside it.");
                v.tell("It's your lucky day! The safe has 2 gold in it.");
                v.giveGold(2);
                v.tell("You continue down the hall.");
                painting = false;
            } else
                v.tell("You continue down the hall.");
        } else
            v.tell("You continue down the hall.");
        v.tell("You have reached the end of the hallway.");
        if(next == rooms[1])
            v.tell("You see a door labeled \"Room_ec22585\". You enter it.");
        else
            v.tell("You see a door labeled \"Room_ec22816\". You enter it.");
    }

    //
    // Offer option to search dresser & obtain key
    //
    void key(Visitor v, Room next) {
        v.tell("You enter a hallway.");
        if(!searched) {
            v.tell("As you go through the hallway, you notice an old wooden dresser.");
            if (v.getChoice("Do you search it?" +
                            "\na) Yes" +
                            "\nb) No",
                    new char[]{'a', 'b'}) == 'a') {
                v.tell("You find an interesting key, labeled \"USE TO EXIT\".");
                if (v.getChoice("Do you take it?" +
                                "\na) Yes" +
                                "\nb) No",
                        new char[]{'a', 'b'}) == 'a') {
                    v.tell("You take the key and continue down the hall.");
                    v.giveItem(key);
                    searched = true;
                } else {
                    v.tell("You close the drawer. This might be a mistake.");
                    if (v.getChoice("Are you sure you want to leave it?" +
                                    "\na) Yes" +
                                    "\nb) No",
                            new char[]{'a', 'b'}) == 'b') {
                        v.tell("You take the key and continue down the hall.");
                        v.giveItem(key);
                        searched = true;
                    } else
                        v.tell("Fine. Leave it then. I tried.");
                }
            } else
                v.tell("You foolishly ignore it and continue down the hall.");
        } else
            v.tell("You continue down the hall.");


        if(next == rooms[0])
            v.tell("You approach a door labeled Room_ec22816. You enter it.");
        else if(next == rooms[1])
            v.tell("You approach a door labeled Room_ec22585. You enter it.");
        else if(next == rooms[2])
            v.tell("You approach a door labeled Room_ec22660. You enter it.");
        else if(next == rooms[3])
            v.tell("You approach a door labeled Room_ec221148. You enter it.");
        else if(next == rooms[4])
            v.tell("You approach a door labeled Room_ec221025. You enter it.");
        else {
            v.tell("*As a reward for finding a glitch, here's 5 gold.*");
            v.giveGold(5);
        }
    }

    //
    // Exit method: key | window
    //
    Direction exit(Visitor v, Direction d) {
        v.tell("You approach a door labeled exit.");
        if(v.hasEqualItem(key)) {
            v.tell("Since you have the key, you are able to exit the house.");
            v.tell("You twist the key in the lock and open the door.");
            v.tell("Have a safe trip. Goodbye!");
            return Direction.TO_NORTH;
        } else {
            v.tell("No key, no exit. Unless...");
            v.tell("You notice a window. It appears to be open.");
            v.tell("Whilst climbing the window, you drop 5 gold. Oops!");
            v.tell("The window shuts before you could reach in and grab it.");
            v.takeGold(5);
            v.tell("Sad that you lost gold, happy that you left, you leave.");
            v.tell("Goodbye!");
            return Direction.TO_NORTH;
        }
    }
}

package OOP.ec22518.MP.contributions;

//house class of Ion Tugui
public class House_ec22941 implements Visitable {
    boolean gotOut;
    Room_ec22941 room1; // My room
    Room_ec221003 room2; // Chee-ho's room
    Room_ec22617 room3; // Zak's room
    Room_ec19389 room4; // Zyber's room
    Item bridge_pass;
    Item book_key;

    House_ec22941() {
        gotOut = false;
        room1 = new Room_ec22941();
        room2 = new Room_ec221003();
        room3 = new Room_ec22617();
        room4 = new Room_ec19389();
        bridge_pass = new Item("Bridge of death pass");
        book_key = new Item("Book - key to secret entrance");
    }

    // encounter with questions in hallway 1 - a way for a player to get more gold
    private void encounter1(Visitor v) {
        v.tell("As you follow down the hallway, you notice a thick fog build up the further you go.");
        v.tell("It should not make sense, but the floor breaks and you see a wooden bridge, with an old bridgekeeper on the side.");

        v.tell("");
        if (v.hasEqualItem(bridge_pass) || v.hasIdenticalItem(bridge_pass)) {
            v.tell("You safely pass the bridge of death, as the BRIDGEkeeper is no more there.");
            v.tell("You find some gold coins as you pass the bridge! You keep going until you reach a room.");
            v.giveGold(3);
            return;
        }
        char[] choices = new char[] { 'a', 'b', 'c', 'd' };
        v.tell("As you approach the BRIDGEKEEPER shouts instructions at you:");
        v.tell("STOP! Who would cross the Bridge of Death must answer me these questions three, ere the other side he see.");
        v.tell("What is the velocity of a swallow in the air?");
        v.tell("");
        v.tell("What do you choose to respond with: (HINT: you must trick the keeper)");
        char choice = v.getChoice(
                "(a) I don't know that! (b) 14 m/s (c) What kind of swallow? African or European? (d) 9 m/s", choices);
        if (choice == 'c') {
            v.tell("BRIDGEKEEPER: I DON'T KNOW THAT!");
            v.tell("As he shouts he's flung up and forced to fall under the bridge.");
            v.giveItem(bridge_pass);
            v.tell("You are now able to pass the bridge of death.");
        } else {
            v.tell("BRIDGEKEEPER: HE-HE! WRONG!");
            v.tell("You are flung into the air and fall down. You lose some of your gold.");
            v.takeGold(2);
            v.tell("You continue until you make it to the next room.");
        }
        return;
    }

    // entrance to the second hallway
    private void encounter2(Visitor v) {
        final char[] y_n = { 'y', 'n' };
        if (v.hasEqualItem(book_key) || v.hasIdenticalItem(book_key)) {
            v.tell("As you have the key you proceed down the hallway with ease");
            return;
        }
        v.tell("All the books are brown, except one which is blue.");
        v.tell("(y) Check the book");
        v.tell("(n) Don't pick the book and go back");
        // simple choice to either pick a book up or not
        char choice1 = v.getChoice("Check the book?:", y_n);
        switch (choice1) {
            case 'y': {
                v.tell("You pick the book and notice a hidden entrance open on the side.");
                v.tell("You leave the book and go through the entrance. HINT: You must keep the book so that the entrance doesn't close");
                v.giveItem(book_key);
                // if user has picked or has not picked the item
                if (v.hasEqualItem(book_key))
                    v.tell("As you have the key you proceed down the hallway with ease");
                else
                    v.tell("As you left the book you are forced to go back");
                break;
            }
            case 'n': {
                v.tell("You decide to go back at the beginning of the hallway");
                break;
            }
        }
        return;
    }

    // second split into 3 ways
    public Direction hallway2(Visitor v, Direction d, char[] turn_dir) {

        char choice;
        while (!gotOut) {
            v.tell("You notice that the hallway splits into 3 other ways");
            v.tell("(f) HW2: Go forward");
            v.tell("(r) HW2: Go right");
            v.tell("(b) HW2: Go back - go to previous hallway");
            v.tell("(l) HW2: Go left");
            choice = v.getChoice("What direction to go?:", turn_dir);
            switch (choice) {
                case 'f': {
                    v.tell("After following the hallway further forward you open the door taking you outside.");
                    gotOut = true;
                    return Direction.TO_NORTH;
                }
                case 'r': {
                    d = room3.visit(v, d);
                    v.tell("After leaving the room you come back to the hallway and face forward.");
                    break;
                }
                case 'l': {
                    d = room4.visit(v, d);
                    v.tell("After leaving the room you come back to the hallway and face forward.");
                    break;
                }
                default: {
                    v.tell("You decide to go back.");
                    return Direction.TO_SOUTH;
                }
            }
        }
        return d;
    }

    public Direction hallway1(Visitor v, Direction d) {
        final char[] turn_dir = { 'f', 'b', 'l', 'r' };
        char choice;
        v.tell("(f) HW1: Go forward");
        v.tell("(r) HW1: Go right");
        v.tell("(b) HW1: Go back - leave outside");
        v.tell("(l) HW1: Go left");
        choice = v.getChoice("What direction to go?:", turn_dir);
        // after visiting any room the user returns to the hallway facing forward
        switch (choice) {
            case 'f': {
                v.tell("You go further the hallway and notice a dead end with a large bookshelf in the end.");
                encounter2(v);
                if (v.hasEqualItem(book_key) || v.hasIdenticalItem(book_key)) {
                    d = hallway2(v, d, turn_dir);
                } else
                    v.tell("You return to the beginning.");
                break;
            }
            case 'l': {
                d = room1.visit(v, d);
                v.tell("After leaving the room you come back to the hallway and face forward.");
                break;
            }
            case 'r': {
                encounter1(v);
                d = room2.visit(v, d);
                v.tell("After leaving the room you come back to the hallway and face forward.");
                break;
            }
            default: {
                gotOut = true;
                d = Direction.TO_SOUTH;
                break;
            }
        }
        return d;
    }

    // main visit method
    public Direction visit(Visitor v, Direction d) {
        // first choice where to go
        v.tell("You enter the house and notice a hallway splitting into 3 directions.");
        while (!gotOut) {
            d = hallway1(v, d);
        }
        v.tell("You decided to leave the house.");
        return d;
    }
}

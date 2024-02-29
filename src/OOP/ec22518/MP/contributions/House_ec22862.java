package OOP.ec22518.MP.contributions;

class House_ec22862 implements Visitable {
    // Three rooms to visit inside the house
    Room merchantRoom;
    Room magiciansRoom;
    Room mysteryRoom;

House_ec22862() {
    merchantRoom = new Room_ec22862();
    magiciansRoom = new Room_ec221011();
    mysteryRoom = new Room_ec22409();
}

public Direction visit(Visitor visitor, Direction direction) {
    // condition for the while loop
    boolean stayIn = true;
    // the possible options in order to use the getChoice method
    char firstOptions[] = {'a', 'b', 'c'};
    char secondOptions[] = {'a', 'b', 'c', 'd'};
    // direction of the exit which is the opposite to the entering direction
    Direction leavingDirection = direction.opposite(direction);

    // starting the while loop
    while (stayIn) {
        // first choosing options for the user
        char firstChoice = visitor.getChoice("You have made a foolish decision to enter this devilish house. " +
                "Are you entering a)the gloomy library  b) the hallway of the deathly rooms c) escape the confines of this prison like abode", firstOptions);

        switch (firstChoice) {
            case 'a':
                char libraryChoice = visitor.getChoice("You slowly enter the gloomy library and see a poltergeist resembling a librarian patrolling and a shiny open chest. " +
                        "Do you want to a)open the chest in the corner of the room b)confront the floating librarian c) run out", firstOptions);

                switch (libraryChoice) {
                    case 'a':
                        Item diamond = new Item("diamond");
                        visitor.tell("You open the chest and find a glistening diamond");
                        visitor.giveItem(diamond);
                        break;
                    case 'b':
                        visitor.tell("You approach the ghost and suddenly it swishes through you and you hear your coins get stolen.");
                        visitor.takeGold(5);
                        break;
                    case 'c':
                        visitor.tell("Alright, let's go back.");
                        break;
                }
                break;

            case 'b':
                char roomChoice = visitor.getChoice(
                        "You slowly walk down the ominous hallway and see three rooms" +
                                "You decide to enter the a)the Merchants store b) the Magical abode c) the Mystery setting d) leave", secondOptions);

                switch (roomChoice) {
                    case 'a':
                        merchantRoom.visit(visitor, direction);
                        break;
                    case 'b':
                        magiciansRoom.visit(visitor, direction);
                        break;
                    case 'c':
                        mysteryRoom.visit(visitor, direction);
                        break;
                    case 'd':
                        visitor.tell("Leave.");
                        break;
                }
                break;

            case 'c':
                stayIn = false;
                visitor.tell("You decide to leave the house, escaping its sinister atmosphere.");
                break;
        }
    }

    return leavingDirection;
}
}

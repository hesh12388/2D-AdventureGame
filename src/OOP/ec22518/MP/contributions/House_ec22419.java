package OOP.ec22518.MP.contributions;

public class House_ec22419 implements Visitable {

    private Room example;
    private Room interfaceRoom;
    private Room moreRoom;
    private Room ec;

    public House_ec22419() {
        this.example = new Room_ec22743();
        this.interfaceRoom = new Room_ec22441();
        this.moreRoom = new Room_ec22557();
        this.ec = new Room_ec22419();

    }

    @Override
    public Direction visit(Visitor visitor, Direction directionVisitorArrivesFrom) {
        Room currentRoom = null;
        boolean exit = false;
        String userInput;
        char[] options = { 'a', 'b', 'c' };
        char[] twoChoices = { 'a', 'b' };
        char[] choices = { 'y', 'n' };

        visitor.tell("Welcome to House_ec22419!!");
        char userChoice = visitor
                .getChoice("Would you like to a) explore the rooms, b) look around, c) leave the house :( ", options);

        while ((userChoice != ('c'))) {

            if (userChoice == ('a')) {
                System.out.println("Option A!!");
                directionVisitorArrivesFrom = nextChoice(visitor, directionVisitorArrivesFrom);
                userChoice = visitor.getChoice(
                        "Would you like to a) explore the rooms, b) look around, c) leave the house :( ", options);
            } else if (userChoice == ('b')) {
                System.out.println("Option B!!");
                lookAround(visitor);
                userChoice = visitor.getChoice(
                        "Would you like to a) explore the rooms, b) look around, c) leave the house :( ", options);
            }

        }
        return directionVisitorArrivesFrom;
    }

    public Room whichRoom(Direction directionVisitorArrivesFrom) {

        if (directionVisitorArrivesFrom == Direction.FROM_NORTH) {
            directionVisitorArrivesFrom = Direction.FROM_SOUTH;
        } else if (directionVisitorArrivesFrom == Direction.FROM_SOUTH) {
            directionVisitorArrivesFrom = Direction.FROM_NORTH;
        }

        if (directionVisitorArrivesFrom == Direction.FROM_NORTH) {
            return interfaceRoom;
        } else if (directionVisitorArrivesFrom == Direction.FROM_EAST) {
            return example;
        } else if (directionVisitorArrivesFrom == Direction.FROM_SOUTH) {
            return moreRoom;
        } else {
            return ec;
        }
    }

    public Direction whichRoomNext(Direction directionVisitorArrivesFrom) {
        if (directionVisitorArrivesFrom == Direction.TO_NORTH) {
            directionVisitorArrivesFrom = Direction.FROM_SOUTH;
        } else if (directionVisitorArrivesFrom == Direction.TO_EAST) {
            directionVisitorArrivesFrom = Direction.FROM_WEST;
        } else if (directionVisitorArrivesFrom == Direction.TO_SOUTH) {
            directionVisitorArrivesFrom = Direction.FROM_NORTH;
        } else if (directionVisitorArrivesFrom == Direction.TO_WEST) {
            directionVisitorArrivesFrom = Direction.FROM_EAST;
        }
        return directionVisitorArrivesFrom;
    }

    public Direction nextChoice(Visitor visitor, Direction directionVisitorArrivesFrom) {
        Room currentRoom = whichRoom(directionVisitorArrivesFrom);
        System.out.println("Room " + currentRoom.toString());
        directionVisitorArrivesFrom = currentRoom.visit(visitor, directionVisitorArrivesFrom);
        directionVisitorArrivesFrom = whichRoomNext(directionVisitorArrivesFrom);
        System.out.println("Current Direction is " + directionVisitorArrivesFrom);
        return directionVisitorArrivesFrom;
    }

    public void lookAround(Visitor v) {
        char[] options = { 'a', 'b', 'c' };
        char[] twoChoices = { 'a', 'b' };
        char[] choices = { 'y', 'n' };

        char livingRoomChoice = v.getChoice(
                "You are in a brightly lit parlour. There is a fire burning in the fire place. \n Do you a) stoke the fire b) take the poker c) look around?",
                options);
        if (livingRoomChoice == 'a') {
            v.tell("You end up burning your fingers and just lost your sense of touch. Great Going!");
            livingRoomChoice = v.getChoice(
                    "Do you want to still a) stoke the fire or b) take the poker or c) look around?",
                    options);
        } else if (livingRoomChoice == 'b') {
            char choiceB = v.getChoice("You are being offered: Iron Poker \n Do you accept (y/n)?",
                    choices);
            livingRoomChoice = v.getChoice(
                    "Do you want to still a) stoke the fire or b) take the poker or c) look around?",
                    options);
        } else if (livingRoomChoice == 'c') {
            char userChoiceB = v.getChoice(
                    "You notice a door behind a curtain. \n Do you a) go through the door or b) go back the way you came?",
                    twoChoices);
            if (userChoiceB == 'a') {
                v.tell("You have now exited the living room.");
            } else if (userChoiceB == 'b') {
                livingRoomChoice = v.getChoice(
                        "Do you want to still a) stoke the fire or b) take the poker or c) look around?",
                        options);
            }
        }
    }
}

package OOP.ec22518.MP.contributions;/*
 *  [DOOR] [DOOR] ([DOOR]) [DOOR] [DOOR]
 *  [BEDROOM] [BEDROOM] [HALLWAY] [KITCHEN] [KITCHEN]
 *  [GARDEN] [GARDEN] [GARDEN] [GARDEN] [GARDEN]
 *
 *  () - ENTRY
 *  Where Kitchen and other rooms have their own distinct direction.
 *
 * */

class BackPorch extends Room {
    public Direction visit(Visitor vis, Direction dir) {
        return dir;
    }
}

class Kitchen extends Room {

    public Direction visit(Visitor vis, Direction dir) {
        String optionsMsg = "You are in the kitchen, you can go [N] North, [E] East, [W] West, or back [S]";
        char[] choices = {'N', 'E', 'S', 'W'};
        while (!vis.hasIdenticalItem(House_ec221023.kitchenElectrical) && (dir == dir.FROM_EAST)) {
            char choice = vis.getChoice(optionsMsg, choices);

            switch (choice) {
                case ('N'):
                    vis.tell("Oh wow! You found the kitchen electrical system...");
                    vis.giveItem(House_ec221023.kitchenElectrical);
                    vis.tell("You see a snagged wire. Maybe you should reconnect it?");
                    vis.tell("What was that noise!");
                    break;
                case ('E'):
                    vis.tell("Hmm, Oh wow some gold dabloons! Nice");
                    vis.giveGold(5);
                    break;
                case ('S'):
                    vis.tell("Thats the exit. You need to find out whats going on before you can leave.");
                    break;
                case ('W'):
                    vis.tell("Uh Oh! You have an unpaid bill overdue. You owe gold.");
                    vis.takeGold(10);
                    break;
            }
        }

        vis.tell("You have fixed the electrical system in the Kitchen, go explore outside!");

        return dir.opposite(dir);
    }
}

class Door extends Room {

    public Direction visit(Visitor vis, Direction dir) {
            if (dir == dir.TO_SOUTH) {
                vis.tell("Illegal Direction.");
                return dir.opposite(dir);
            }
            else if (dir == dir.TO_EAST || dir == dir.TO_WEST && (House_ec221023.directionHolder.EastVal <= 2 && House_ec221023.directionHolder.WestVal <= 2)) {
                vis.tell("You have ended up.... At the front door again.");
                if (dir == dir.TO_EAST) {
                    new East();
                } else {
                    new West();
                }
                return dir;
            }
            else if (dir == dir.TO_EAST || dir == dir.TO_WEST && (House_ec221023.directionHolder.EastVal >= 2 || House_ec221023.directionHolder.WestVal >= 2)) {
                vis.tell("Illegal Direction.");
                return dir.opposite(dir);
            }
            else if (dir == dir.TO_NORTH) {
                vis.tell("You are now in the hallway");
                new North();
                House_ec221023.directionHolder.WestVal = 0;
                House_ec221023.directionHolder.EastVal = 0;
                return dir;
            }

            return dir;
        }
    }


class Hallway extends Room {

    public Direction visit(Visitor vis, Direction dir) {
            if (dir == dir.TO_SOUTH) {
                vis.tell("You are back at the door.");
                if (vis.hasIdenticalItem(House_ec221023.doorKeys)) {
                    vis.giveGold(10);
                    new South();
                    return dir;
                }
                vis.tell("You cannot escape. Return back to the hallway.");
                return dir.opposite(dir);

            }
            else if (dir == dir.TO_EAST) {
                vis.tell("You enter the kitchen.");
                vis.tell("The lights are off... Better turn it on.");
                vis.tell("You can see better now. Hmm the Key must be here somewhere, You should look around.");

                new East();

                return (House_ec221023.kitchen.visit(vis, dir));

            }
            else if (dir == dir.TO_WEST) {
                vis.tell("You have entered an abandonned bedroom");
                vis.tell("What could be in here?");

                new West();

                return (House_ec221023.bedroom.visit(vis, dir));

            }

            return dir;
        }
    }

interface moveDirection {
    void directionTraverse ();
}

class CardinalDirection {

    private static CardinalDirection singletonGen = new CardinalDirection();

    static CardinalDirection instanceReference() {
        return singletonGen;
    }

    private CardinalDirection() {
    }


    int NorthVal = 0;
    int EastVal = 0;
    int SouthVal = 0;
    int WestVal = 0;


}

class North implements moveDirection {

    public void directionTraverse () {
        CardinalDirection.instanceReference().NorthVal++;
    }

    public North () {
        directionTraverse();
    }

}

class East implements moveDirection {

    public void directionTraverse () {
        CardinalDirection.instanceReference().EastVal++;
    }

    public East () {
        directionTraverse();
    }

}

class South implements moveDirection {

    public void directionTraverse () {
        CardinalDirection.instanceReference().SouthVal++;
    }

    public South () {
        directionTraverse();
    }

}

class West implements moveDirection {

    public void directionTraverse () {
        CardinalDirection.instanceReference().WestVal++;
    }

    public West () {
        directionTraverse();
    }

}

class House_ec221023 implements Visitable {

    static boolean exitKey = false;

    static Item bedroomKeys = new Item("Bedroom Keys");
    static Item doorKeys = new Item("Door Keys");

    static Item kitchenElectrical = new Item("Kitchen Electricity Bill");

    static Door door;
    static Hallway hallway;
    static Kitchen kitchen;
    static Room bathroom;
    static Room bedroom;
    static BackPorch garden;
    static CardinalDirection directionHolder;

    House_ec221023() {
        door = new Door();
        hallway = new Hallway();
        kitchen = new Kitchen();
        bathroom = new Room_ec22627();
        bedroom = new Room_ec22763();
        garden = new BackPorch();

        CardinalDirection directionHolder = CardinalDirection.instanceReference();
    }

    public Direction visit(Visitor vis, Direction dir) {
        char visitorChoice;
        char[] cardinalDirections = {'N', 'E', 'S', 'W'};
        String directionPrompt = "In which direction would you like to traverse?/n [N] North/t [E] East/t [S] South/t [W] West ?";

        vis.tell("Hello visitor! Welcome to House ec221023");
        vis.tell("You are currently unable to leave this house until you figure out where the door keys are.");
        vis.tell("It is your objective to find the door keys so that you are able to leave the house.");

        while (!vis.hasIdenticalItem(doorKeys)) {
            visitorChoice = vis.getChoice(directionPrompt, cardinalDirections);
            switch (visitorChoice) {
                case ('N'):
                    hallway.visit(vis, dir.TO_NORTH);
                    break;
                case ('E'):
                    hallway.visit(vis, dir.TO_EAST);
                    break;
                case ('S'):
                    hallway.visit(vis, dir.TO_SOUTH);
                    break;
                case ('W'):
                    hallway.visit(vis, dir.TO_WEST);
                    break;
            }
        }

        return dir;
    }


}

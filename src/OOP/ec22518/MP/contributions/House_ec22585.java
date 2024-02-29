package OOP.ec22518.MP.contributions;

class House_ec22585 implements Visitable {
    Room r1;
    Room r2;
    Room r3;
    boolean exit;
    
    House_ec22585() {
        r1 = new Room_ec22585();
        r2 = new Room_ec22409();
        r3 = new Room_ec22648();
        exit = false;
    }
    
    public Direction visit(Visitor visiting_user, Direction direction_of_visitor) {
        Direction to_go = Direction.TO_NORTH;
        Direction to_enter;

        while (exit == false) {
            if (direction_of_visitor == Direction.FROM_NORTH) {
                System.out.println("Entering the house from the North");
                enterHallway(visiting_user, "h1");
            } else if (direction_of_visitor == Direction.FROM_EAST) {
                System.out.println("Entering the house from the East");
                enterHallway(visiting_user, "h2");
            } else if (direction_of_visitor == Direction.FROM_SOUTH) {
                System.out.println("Entering the house from the South");
                enterHallway(visiting_user, "h1");
            } else if (direction_of_visitor == Direction.FROM_WEST) {
                System.out.println("Entering the house from the West");
                enterHallway(visiting_user, "h2");
            }
        }
         
        return to_go;
    }

    public void enterHallway(Visitor visiting_user, String hallway_entered) {
        visiting_user.tell("As you walk through the spooky hallway, the lights are flickering on and off");

        if (hallway_entered.equals("h1")) {
            enterHallway1(visiting_user);
        }
        else if (hallway_entered.equals("h2")) {
            enterHallway2(visiting_user);
        }
        else {
            enterHallway3(visiting_user);
        }
    }

    public void enterHallway1(Visitor visiting_user) {
        visiting_user.tell("You have entered the top hallway");
        visiting_user.tell("You are unsure what to do... ");
        visiting_user.tell("What is your final choice: ");
        String options = "1. Enter the room to your left \n" +
                        "2. Enter the room to your right \n" +
                        "3. Search around the hallway \n" +
                        "4. Go up the crooked stairs \n" +
                        "5. Leave the forsaken house";
        char[] choices = {'1', '2', '3', '4', '5'};
        char choice = visiting_user.getChoice(options, choices);

        if (choice == '1') {
            visiting_user.tell("You have chosen to enter the room to your left");
            Direction exiting_from = r1.visit(visiting_user, Direction.FROM_EAST);
            nextMove(visiting_user, exiting_from, "r1");
        }
        else if (choice == '2') {
            visiting_user.tell("You have chosen to enter the room to your right");
            Direction exiting_from = r2.visit(visiting_user, Direction.FROM_WEST);
            System.out.println("Here from r2: " + exiting_from);
            nextMove(visiting_user, exiting_from, "r2");
        }
        else if (choice == '3') {
            visiting_user.tell("You have chosen to search the mysterious hallway");
            searchHallway(visiting_user, "h1");
        }
        else if (choice == '4') {
            visiting_user.tell("You slowly climb the stairs...");
            enterHallway(visiting_user, "h3");
        }
        else if (choice == '5'){
            visiting_user.tell("You hear the ghost scream and run straight out the door");
            exit = true;
            return;
        }
        else {
            visiting_user.tell("Unsure of what you want to do, you move into the hallway");
            enterHallway(visiting_user, "h1");
        }
    }

    public void enterHallway2(Visitor visiting_user) {
        visiting_user.tell("You have entered the middle hallway");
        visiting_user.tell("You are unsure what to do... ");
        visiting_user.tell("What is your final choice: ");
        String options = "1. Enter the room to your left \n" +
                "2. Enter the room to your right \n" +
                "3. Search around the hallway \n" +
                "4. Enter the top hallway \n";
        char[] choices = {'1', '2', '3', '4'};
        char choice = visiting_user.getChoice(options, choices);

        if (choice == '1') {
            visiting_user.tell("You have chosen to enter the room to your left");
            Direction exiting_from = r1.visit(visiting_user, Direction.FROM_SOUTH);
            nextMove(visiting_user, exiting_from, "r1");
        }
        else if (choice == '2') {
            visiting_user.tell("You have chosen to enter the room to your right");
            Direction exiting_from = r2.visit(visiting_user, Direction.FROM_SOUTH);
            nextMove(visiting_user, exiting_from, "r2");
        }
        else if (choice == '3') {
            visiting_user.tell("You have chosen to search the mysterious hallway");
            searchHallway(visiting_user, "h2");
            return;
        }
        else if (choice == '4') {
            visiting_user.tell("You have chosen to enter the top hallway");
            enterHallway1(visiting_user);
            return;
        }
        else {
            visiting_user.tell("Theres no time to be thinking about random things...");
            visiting_user.tell("You enter the room to the left");
            r1.visit(visiting_user, Direction.FROM_EAST);
        }
    }

    public void enterHallway3(Visitor visiting_user) {
        visiting_user.tell("You reach the top of the stairs and enter the upper floor");
        visiting_user.tell("You have entered the upper floor hallway");
        visiting_user.tell("It seems creepier than the other parts of the house...");
        visiting_user.tell("You see a singular room at the end of the hallway that has a broken door handle");
        visiting_user.tell("Around the hallway are some mysterious objects...");
        visiting_user.tell("You feel uneasy waiting here");
        visiting_user.tell("What is your final choice: ");
        String options = "1. Walk down the hallway and enter the room at the end \n" +
                "2. Quickly turn back down the stairs in hopes of finding something more hopeful\n";
        char[] choices = {'1', '2'};
        char choice = visiting_user.getChoice(options, choices);
        if (choice == '1') {
            visiting_user.tell("The handle breaks as you try and turn it, but you manage to force the door open");
            Direction exiting_from = r3.visit(visiting_user, Direction.FROM_SOUTH);
            nextMove(visiting_user, exiting_from, "r3");
        }
        else {
            visiting_user.tell("You run back down the stairs");
            enterHallway(visiting_user, "h1");
        }
    }

    public void searchHallway(Visitor visiting_user, String from_hallway) {
        visiting_user.tell("You hear the ghosts in the house scream...");
        visiting_user.tell("The hallways feel like they are getting more and more spookier...");
        visiting_user.tell("There are a few things you see that you can investigate");
        String options = "1. Tamper with the nearby lights \n" +
                "2. Attempt to open one of the bookshelves \n" +
                "3. Search behind a nearby painting \n" +
                "4. Go back to the hallway you entered";
        char[] choices = {'1', '2' , '3', '4'};
        char choice = visiting_user.getChoice(options, choices);
        if (choice == '1') {
            visiting_user.tell("You come close to inspect the lights and see them blink rapidly");
            visiting_user.tell("A ghost nearby spooks you with his screams and you go straight back");
            return;
        } else if (choice == '2') {
            visiting_user.tell("The bookshelves are locked shut as you feel strong resistance trying to open them");
            visiting_user.tell("You finally manage to and find...");
            visiting_user.tell("A random rusty key...");
            Item rusty_key = new Item("rusty key");
            if (!(visiting_user.hasEqualItem(rusty_key))) {
                visiting_user.tell("The key seems like it could be useful and so you grab it");
                visiting_user.giveItem(rusty_key);
            }
            else {
                visiting_user.tell("You remember taking a similar key and decide to ignore it");
            }
            return;
        } else if (choice == '3') {
            visiting_user.tell("You search the nearby painting and find...");
            visiting_user.tell("5 gold coins!");
            visiting_user.giveGold(5);
            return;
        }
        else {
            visiting_user.tell("You decide what to do in the hallway");
            return;
        }
    }

    public void enterGarden(Visitor visiting_user) {
        visiting_user.tell("You have entered the garden");
        visiting_user.tell("Here it feels more peaceful...");
        visiting_user.tell("There are a few things you see that you can investigate");
        String options = "1. Prod the dying bushes and see if there is anything in there \n" +
                "2. Climb the nearby tree and get a better view \n" +
                "3. Re-enter the house to explore more... \n" +
                "4. Jump the fence and escape this creepy place ";
        char[] choices = {'1', '2' , '3', '4'};
        char choice = visiting_user.getChoice(options, choices);
        if (choice == '1') {
            visiting_user.tell("You grab a nearby branch and poke around in the bush");
            visiting_user.tell("Luckily, you find some rusty but still intact coins");
            visiting_user.tell("You find 3 gold coins");
            visiting_user.giveGold(3);
            visiting_user.tell("You see something pop out from the bush and run straight back into the house");
            enterHallway(visiting_user, "h1");
        }
        else if (choice == '2') {
            visiting_user.tell("You attempt to climb the singular mysteriously alive tree... ");
            visiting_user.tell("In a hole at the top of the tree you find... ");
            visiting_user.tell("A broken jar");
            Item broken_jar = new Item("broken jar");
            if (!(visiting_user.hasEqualItem(broken_jar))) {
                visiting_user.giveItem(broken_jar);
            }
            else {
                visiting_user.tell("You already have this and decide to ignore it");
            }
            visiting_user.tell("You feel lucky and go back in the house");
            enterHallway(visiting_user, "h1");
        }
        else if (choice == '3') {
            visiting_user.tell("In some feat of bravery or stupidity, you go back into the house");
            enterHallway(visiting_user, "h1");
        }
        else {
            visiting_user.tell("You narrowly escape the house");
            exit = true;
        }
    }

    public void nextMove(Visitor visiting_user, Direction from_direction, String from_room) {
        Direction next_direction;
        if (from_room.equals("r1")) {
            if (from_direction == Direction.FROM_NORTH) {
                enterGarden(visiting_user);
            }
            else if (from_direction == Direction.FROM_EAST) {
                enterHallway(visiting_user, "h1");
            }
            else if (from_direction == Direction.FROM_WEST) {
                r2.visit(visiting_user, Direction.FROM_EAST);
            }
            else if (from_direction == Direction.FROM_SOUTH) {
                enterHallway(visiting_user, "h2");
            }
        }
        else if (from_room.equals("r2")) {
            if (from_direction == Direction.FROM_SOUTH) {
                System.out.println("Here 1");
                enterGarden(visiting_user);
            }
            else if (from_direction == Direction.FROM_WEST) {
                System.out.println("Here 2");
                r1.visit(visiting_user, Direction.FROM_WEST);
            }
            else if (from_direction == Direction.FROM_EAST) {
                System.out.println("Here 3");
                enterHallway(visiting_user, "h1");
            }
            else if (from_direction == Direction.FROM_NORTH) {
                System.out.println("Here 4");
                enterHallway(visiting_user, "h2");
            }
        }
        if (from_room.equals("r3")) {
            if (from_direction == Direction.FROM_SOUTH) {
                visiting_user.tell("You descend to the bottom floor middle hallway");
                enterHallway(visiting_user, "h2");
            }
            else if (from_direction == Direction.FROM_WEST) {
                visiting_user.tell("You descend to the bottom floor middle hallway");
                enterHallway(visiting_user, "h2");
            }
            else if (from_direction == Direction.FROM_EAST) {
                visiting_user.tell("You descend to the bottom floor middle hallway");
                enterHallway(visiting_user, "h2");
            }
            else if (from_direction == Direction.FROM_NORTH) {
                visiting_user.tell("You descend to the bottom floor middle hallway");
                enterHallway(visiting_user, "h2");
            }
        }
    }
}

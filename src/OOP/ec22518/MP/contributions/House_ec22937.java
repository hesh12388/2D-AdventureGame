package OOP.ec22518.MP.contributions;

import java.util.*;
class House_ec22937 implements Visitable {

    private final Room[] basicRooms;
    private String[] botFloor;
    private final Room attic;
    private static final int BASEMENT_CODE = -1;
    private static final int ATTIC_CODE = -16;
    private static final int DEFAULT_FLOOR = 0;
    private static final int NORTH_MOST = 2;
    private static final int EAST_MOST = 11;
    private static final int SOUTH_MOST = 13;
    private static final int WEST_MOST = 4;
    private boolean A_isDead = false;
    private boolean A_hasGoldBag = false;
    private boolean A_catCrazy = false;
    private boolean BA_hasBook = false;
    private boolean BA_givenOP = false;
    private boolean STATE_usedBag = false;
    private boolean STATE_pandaWall = false;
    private boolean STATE_torchOn = false;
    private boolean STATE_mirror = false;
    private boolean STATE_stolenVase = false;
    private boolean STATE_squareFirst = true;
    private int coalCount = 0;


    public House_ec22937()
    {
        basicRooms = new Room[] {new Room_ec22917(), new Room_ec22884(), new Room_ec221008(), new Room_ec221013(), new Room_ec221014(),new Room_ec22462(), new Room_ec22484(), new Room_ec22545(), new Room_ec22591(), new Room_ec22664(), new Room_ec22695(), new Room_ec22751(),new Room_ec22770(),new Room_ec22894()};
        botFloor = new String[] {"Athisha","Mehmet","PANDA","OUTSIDER","MATRIX","STEAM","MUSTY","SHOP","LUCKY", "BUILDING","STUDY","MYSTERY","LIBRARY","DRAWER","Table","Bedroom"};
        attic = new Room_ec22937();
    }

    public Direction visit(Visitor v, Direction directionEnter)
    {
        v.tell("Upon entering the house you hear a bang. " +
                "Checking behind you, you see that the door you entered from  is ... gone?");
        v.tell("The walls are a gloomy blue as if storm clouds were imprinted onto the bricks. " +
                "Engulfed in cobwebs, the chandelier above you sways back and forth dancing to an imaginary beat." +
                " Affixed onto the walls, the occasional torches give a glimmer of hope in this winding blanket of darkness.");
        delayText(v);
        botFloor = shuffleRoom(botFloor);
        int roomNum = findStartingRoom(directionEnter);
        Direction directionExit = roomLoop(roomNum, v, directionEnter);
        exitHouse(v);
        return directionExit;
    }

    private int findStartingRoom(Direction d)
    {
        if (d == Direction.FROM_NORTH) {return SOUTH_MOST;}
        else if (d == Direction.FROM_EAST) {return WEST_MOST;}
        else if (d == Direction.FROM_SOUTH) {return NORTH_MOST;}
        else  {return EAST_MOST;}

    }

    private Direction roomLoop(int roomNum, Visitor v, Direction directionEnter)
    {
        Direction directionExit = directionEnter;
        while (roomNum != BASEMENT_CODE)
        {
            if (roomNum == ATTIC_CODE)
            {
                directionExit = attic.visit(v, directionEnter);
                delayText(v);
                v.tell("You just exited to the " + outputExitDir(directionExit) + ".");
                delayText(v);
                directionEnter = directionExit;
                roomNum = exitAttic(v, directionEnter);
                delayText(v);
                hallway(v);
                delayText(v);
                continue;
            }
            directionExit = enterRoom(v, directionEnter, botFloor[roomNum]);
            updateCoal(roomNum, directionExit);
            delayText(v);
            v.tell("You just exited to the " + outputExitDir(directionExit) + "." + "\n");
            directionEnter = directionExit;
            directionEnter = enterSquare(v, directionEnter);
            int pickFloor = hallway(v);
            delayText(v);
            if (pickFloor == ATTIC_CODE)
            {
                roomNum = ATTIC_CODE;
                continue;
            }
            else if (pickFloor == BASEMENT_CODE)
            {
                roomNum = BASEMENT_CODE;
                continue;
            }
            roomNum = chooseNextRoom(roomNum, directionEnter);
        }
        return directionExit;
    }

    private int exitAttic(Visitor v, Direction d)
    {
        v.tell("You find a circular staircase winding downstairs.");
        if (d == Direction.TO_SOUTH) {return SOUTH_MOST;}
        else if (d == Direction.TO_WEST) {return WEST_MOST;}
        else if (d == Direction.TO_NORTH) {return NORTH_MOST;}
        else {return EAST_MOST;}
    }

    private void exitHouse(Visitor v)
    {
        v.tell("Upon entering the basement, you are confronted by a massive steel door.");
        v.tell("Pushing with all your might, you try to force your way through.");
        v.tell("With a creak, the other side is revealed...");
    }

    private Direction enterRoom(Visitor v, Direction d, String name)
    {
        Direction exit = Direction.TO_NORTH;
        switch (name)
        {
            case "Athisha": exit = basicRooms[0].visit(v, d); break;
            case "Mehmet": exit = basicRooms[1].visit(v,d); break;
            case "PANDA": exit = basicRooms[2].visit(v,d); break;
            case "OUTSIDER": exit = basicRooms[3].visit(v,d); break;
            case "MATRIX": exit = basicRooms[4].visit(v,d); break;
            case "STEAM": exit = basicRooms[5].visit(v,d); break;
            case "MUSTY": exit = basicRooms[6].visit(v,d); break;
            case "SHOP": exit = basicRooms[7].visit(v,d); break;
            case "LUCKY": exit = basicRooms[8].visit(v,d); break;
            case "BUILDING": exit = basicRooms[9].visit(v,d); break;
            case "STUDY": exit = basicRooms[10].visit(v,d); break;
            case "MYSTERY": exit = basicRooms[11].visit(v,d); break;
            case "LIBRARY": exit = basicRooms[12].visit(v,d); break;
            case "DRAWER": exit = basicRooms[13].visit(v,d); break;
            case "Table": exit = roomA(v,d); break;
            case "Bedroom": exit = roomBA(v,d); break;
        }
        return exit;
    }

    private int chooseNextRoom(int origin, Direction d)
    {
        final int SQU_LENGTH = 4;
        final int SQUARE_MIN_X = SQU_LENGTH * (SQU_LENGTH - 1);
        if (d == Direction.TO_NORTH)
        {
            if (origin < SQU_LENGTH) { return origin + SQUARE_MIN_X; }
            return origin - SQU_LENGTH;
        }
        else if (d == Direction.TO_EAST)
        {
            if (origin % SQU_LENGTH == SQU_LENGTH - 1) { return origin - SQU_LENGTH + 1; }
            return origin + 1;
        }
        else if (d == Direction.TO_SOUTH)
        {

            if (origin >= SQUARE_MIN_X) { return origin - SQUARE_MIN_X; }
            return origin + SQU_LENGTH;
        }
        else
        {
            if (origin % SQU_LENGTH == 0) { return origin + SQU_LENGTH - 1; }
            return origin - 1;
        }
    }

    private void updateCoal(int origin, Direction d) { if (botFloor[origin].equals("SHOP") && d == Direction.TO_WEST) {coalCount++;} }

    private int hallway(Visitor v)
    {
        int outVal = DEFAULT_FLOOR;
        final int NUM_CORRIDORS = 13;
        v.tell("You notice a cellar door under you. Opening it reveals a ladder. Maybe there's a basement (hopefully to the exit)?");
        String options = "Will you go down the ladder? (y/n)";
        if (doOrNot(v, options)) { return BASEMENT_CODE; }
        v.tell("");
        switch (new Random().nextInt(NUM_CORRIDORS))
        {
            case 0: corridor0(v); break;
            case 1: corridor1(v); break;
            case 2: corridor2(v); break;
            case 3: corridor3(v); break;
            case 5: corridor5(v); break;
            case 6: corridor6(v); break;
            case 7: corridor7(v); break;
            case 8: corridor8(v); break;
            case 10: corridor10(v); break;
            default: outVal = staircase(v); break;
        }
        v.tell("");
        checkApple(v);
        checkBag(v);
        return outVal;
    }

    private void checkApple(Visitor v)
    {
        //apple found in Room_ec22545
        if (v.hasIdenticalItem(Room_ec22545.apple) && doOrNot(v, "Eat the golden apple? (y/n) (re-usable, gives 3g when used)"))
        {
            v.tell("You take a bite of the apple. Tasty!");
            v.tell("The apple regenerates back to normal. How peculiar.");
            v.giveGold(3);
        }
    }

    private void checkBag(Visitor v)
    {
        if (!STATE_usedBag && v.hasEqualItem(new Item("Gold bag")) && doOrNot(v, "Open gold bag? (y/n) (one time use, gives 16g)"))
        {
            v.tell("You open the bag and take the money.");
            v.tell("There's nothing to do with the empty bag so you leave it on a nearby table.");
            for (int i=0; i<2; i++) {v.giveGold(8);}
            STATE_usedBag = true;
        }
    }

    private int staircase(Visitor v)
    {
        v.tell("You notice a staircase on your left. Would you like to check out what is upstairs?");
        String options = "Go upstairs (y/n)";
        if (doOrNot(v, options)) { return ATTIC_CODE; }
        return DEFAULT_FLOOR;
    }

    private Direction enterSquare(Visitor v, Direction d)
    {
        if (STATE_squareFirst)
        {
            v.tell("As you exit the room, you notice something strange about these corridors, in fact all of them are like this! On either side of you, you see two doors, it wouldn't make sense for them to connect to rooms either.");
            STATE_squareFirst = false;
        }
        Direction[] exits = {Direction.TO_NORTH, Direction.TO_EAST, Direction.TO_SOUTH, Direction.TO_WEST};
        int count = 0;
        while (count < exits.length && exits[count] != d) {count++;}
        Direction left = exits[(count+3)%4];
        Direction right = exits[(count+1)%4];
        v.tell("You exited to the " + outputExitDir(d) + " so that would mean the left door would lead to the " + outputExitDir(left) + " and the right door would lead to the " + outputExitDir(right) + ".");
        switch(v.getChoice("Would you like to go left (l), right (r) or forward (f)", new char[] {'f','l','r'}))
        {
            case 'l': v.tell("Going " + outputExitDir(left) + "."); return left;
            case 'r': v.tell("Going " + outputExitDir(right) + "."); return right;
            default: v.tell("Going " + outputExitDir(d) + "."); return d;
        }
    }

    private static boolean doOrNot(Visitor v, String desc)
    {
        boolean isYes = true;
        switch (v.getChoice(desc, new char[] {'y','n'}))
        {
            case 'y': isYes = true; break;
            case 'n': isYes = false; break;
            default: break;
        }
        return isYes;
    }

    private static String[] shuffleRoom(String[] rooms)
    {
        Random r = new Random();
        for (int i=0; i<2*rooms.length; i++)
        {
            int index1 = r.nextInt(rooms.length);
            int index2 = r.nextInt(rooms.length);
            String temp = rooms[index1];
            rooms[index1] = rooms[index2];
            rooms[index2] = temp;
        }
        return rooms;
    }

    private static void delayText(Visitor v) { v.getChoice("[Press enter to continue]", new char[] {' '}); v.tell("");}


    private static String outputExitDir(Direction d)
    {
        if (d == Direction.TO_NORTH) {return "NORTH";}
        else if (d == Direction.TO_EAST) {return "EAST";}
        else if (d == Direction.TO_SOUTH) {return "SOUTH";}
        else {return "WEST";}
    }

    Direction roomA(Visitor v, Direction d)
    {
        if (A_isDead)
        {
            v.tell("You cursed and killed the resident of this room and now you want more? WHAT MORE IS THERE TO DO?");
            return Direction.opposite(d);
        }
        else if (A_catCrazy) {return rA_nightmare(v,d);}
        else {return rA_dream(v,d);}
    }

    private Direction rA_dream(Visitor v, Direction d)
    {
        v.tell("You find a cat sitting on a massive dining table.");
        v.tell("It seems as though the cat wants you to sit with it.");
        if (doOrNot(v, "Will you sit with the cat? (y/n)"))
        {
            v.tell("The cat pours a cup of hot chocolate then paws it to you.");
            v.tell("Delicious!");
            v.tell("You decide to give the cat a glass of milk. It is very much appreciated.");
            v.tell("You tell the cat about your experience in this house. It meows in agreement.");
            v.giveGold(3);
            if (!A_hasGoldBag)
            {
                v.tell("The cat also gives you a bag. Use it whenever you're feeling poor");
                v.giveItem(new Item("Gold bag"));
                A_hasGoldBag = true;
            }
        }
        //Wand found in Room_ec22484
        if (v.hasIdenticalItem(Room_ec22484.wand) && doOrNot(v, "Cast spell on the cat? (y/n)"))
        {
            v.tell("On your way out, you decide to cast a spell on the cat.");
            v.tell("Green smoke begins to engulf the cat...");
            A_catCrazy = true;
        }
        return Direction.opposite(d);
    }

    private Direction rA_nightmare(Visitor v, Direction d)
    {
        v.tell("You enter the room and find a table. Surrounded by unconscious bodies, " +
                "you find a cat sitting opposite you.");
        v.tell("As dread latches onto you, one of the bodies tries to grab you by the leg.");
        v.tell("Shivering in fear, you swat the hand away and run.");
        delayText(v);
        //Shield found in Room_ec22545
        if (v.hasIdenticalItem(Room_ec22545.shield))
        {v.tell("The cat tries to grab you but thankfully " +
                "you're able to protect yourself with your shield!");}
        else
        {
            v.tell("...");
            v.tell("You feel a paw behind you with a grip of steel.");
            v.tell("...");
            v.tell("THUD" + "\n" + "The ceiling cracks.");
            v.takeGold(4);
        }
        rA_killCat(v);
        v.tell("Slamming the door behind you, you run to the exit");
        return Direction.opposite(d);
    }

    private void rA_killCat(Visitor v)
    {
        final String CAT_DEAD = "It explodes into gold and the possessed bodies crumple to the ground.";
        if ((v.hasEqualItem(new Item("Knife")) || v.hasEqualItem(new Item("knife")))
                && doOrNot(v, "Use the knife? (y/n)"))
        {
            A_isDead = true;
            v.tell("You throw the knife at the cat." + CAT_DEAD);
            for (int i=0; i<4; i++) {v.giveGold(10);}
        }
        //Flamethrower found in Room_ec22484
        else if (v.hasIdenticalItem(Room_ec22484.flamethrower) && doOrNot(v, "Use the flamethrower? (y/n)"))
        {
            A_isDead = true;
            v.tell("You activate the flamethrower." + CAT_DEAD);
            v.tell("Unfortunately the money also melted.");
            v.giveItem(new Item("Gold goop"));
        }
        else if (v.hasIdenticalItem(Room_ec221014.gun) && doOrNot(v, "Use the gun? (y/n)"))
        {
            A_isDead = true;
            v.tell("You attempt to shoot the cat. " + CAT_DEAD);
            v.tell("The cat transforms into a ... donut");
            v.tell("Damn matrix, this thing doesn't even function like a normal gun in the real world.");
            v.giveItem(new Item("Donut"));
        }
    }

    Direction roomBA(Visitor v, Direction d)
    {
        v.tell("[You walk into a bedroom. It looks like there's someone sleeping there]");
        v.tell("[Just as you are about to leave, you find a gold fountain on the bedside table]");
        String options = "Will you check out the gold fountain (y/n)";
        boolean remain = doOrNot(v, options);
        while (remain)
        {
            BA_stealGold(v);
            delayText(v);
            if (BA_checkSuccess(v))
            {
                options = "Will you take more gold (y/n)";
                remain = doOrNot(v,options);
                continue;
            }
            BA_stealFail(v);
            BA_giveBook(v);
            BA_straw(v);
            v.tell("[Looks like she went back to sleep again but you decide to not risk taking more ... for now.]");
            remain = false;
        }
        v.tell("[There's nothing else to do here so you may as well leave]");
        return Direction.opposite(d);
    }

    private void BA_stealGold(Visitor v)
    {
        v.tell("[You tiptoe quietly into the room, being careful not to wake up the person, and reach your hand into the gold fountain.]");
        v.tell("[Upon retracting your hand back, the gold hardens into 10 gold pieces]");
        v.giveGold(10);

    }

    private boolean BA_checkSuccess(Visitor v)
    {
        final int WAKE_UP_CHANCE = 4;
        final int EXTRA_LUCK = 3;
        final String STILL_SLEEP = "[She's still fast asleep. Lucky!]";
        if ((new Random()).nextInt(WAKE_UP_CHANCE) != 0)
        {
            v.tell(STILL_SLEEP);
            return true;
        }
        //Lucky token found in Room_ec22591
        else if (v.hasEqualItem(new Item("lucky_token")) && new Random().nextInt(EXTRA_LUCK) == 0)
        {
            v.tell(STILL_SLEEP + "\n" + "You feel as if you got saved by your lucky token.");
            delayText(v);
            return true;
        }
        return false;
    }

    private void BA_stealFail(Visitor v)
    {
        final int AVG_NUM_TIMES = 5;
        v.tell("[You hear someone appear behind you]");
        v.tell("[She grabs the ... blahaj? next to her and bashes you with it]");
        delayText(v);
        int goldTaken = 10;
        //Shield found in Room_ec22545
        if (v.hasIdenticalItem(Room_ec22545.shield)) {
            v.tell("[You grab your shield on instinct.]");
            goldTaken = 7;
        }
        for (int i=0; i<AVG_NUM_TIMES; i++) {v.takeGold(goldTaken);}
    }

    private void BA_giveBook(Visitor v)
    {
        if (!BA_hasBook && v.hasIdenticalItem(Room_ec22545.book) && doOrNot(v, "Give her the book about the meaning of life (y/n)"))
        {
            delayText(v);
            v.tell("Why exactly are you giving me a book on the meaning of life? I'll take it, its been a while since I've had an existential crisis.");
            BA_hasBook = true;
            v.tell("I accept your apology but you're not getting all of your money back.");
            v.giveGold(3);
            v.tell("I'm going back to sleep now after you so rudely decide to wake me up in the middle of the night.");
            v.tell("Good night!");
            return;
        }
        if (v.hasIdenticalItem(Room_ec22545.book) && BA_hasBook)
        {
            delayText(v);
            v.tell("She seems especially angry with you.");
            for (int i=0; i<2; i++) {v.takeGold(10);}
        }
    }

    private void BA_straw(Visitor v)
    {
        if (v.hasIdenticalItem(Room_ec22894.straw_hat) && !BA_givenOP)
        {
            v.tell("Nice straw hat by the way. It reminds me of that one manga, I would highly recommend it.");
            v.giveItem(new Item("One Piece"));
            BA_givenOP = true;
        }
    }

    private void corridor0(Visitor v)
    {
        v.tell("You notice a suit of armour carrying a spear.");
        if (doOrNot(v, "Inspect the suit of armour (y/n)"))
        {
            v.tell("The spear slams down on you.");
            v.tell("Well that wasn't very nice.");
            v.takeGold(7);
            return;
        }
        v.tell("Boring");
    }

    private void corridor1(Visitor v)
    {
        if (STATE_stolenVase)
        {
            v.tell("This corridor feels so much more empty without the vase.");
            if (!doOrNot(v, "Place the vase back (y/n)"))
            {
                v.tell("That's completely understandable, the vase would look nice no matter where you put it. Please make good use of it.");
                return;
            }
            v.tell("You shouldn't have stolen it in the first place");
            STATE_stolenVase = false;
            v.takeGold(2);
            return;
        }
        v.tell("You find a vase with gold in it... Why is it on the floor?");
        String options = "a) Take money inside vase" + "\n" + "b) Break the vase" + "\n" + "c) Take the vase"
                + "\n" + "d) Leave it";
        switch (v.getChoice(options, new char[] {'a','b','c','d'}))
        {
            case 'a':
                v.tell("You find 6 gold in the vase");
                v.giveGold(6);
                break;
            case 'b':
                v.tell("You really didn't need to do that.");
                delayText(v);
                for (int i=0; i<45; i++) {v.takeGold(7);}
                break;
            case 'c':
                if (v.giveItem(new Item("Vase"))) STATE_stolenVase = true;
                break;
            default: break;
        }
    }

    private void corridor2(Visitor v)
    {
        if (STATE_mirror)
        {
            v.tell("You decide to check yourself out in the mirror.");
            boolean straw = v.hasEqualItem(Room_ec22894.straw_hat);
            boolean coat = v.hasEqualItem(new Item("Coat"));
            if (straw) {v.tell("That straw hat looks good on you.");}
            if (coat) {v.tell("This coat might be a bit too big but its comfy");}
            if (!(coat || straw)) {v.tell("If only there were more clothes here.");}
            v.giveGold(1);
            if (doOrNot(v, "Shatter the mirror? (y/n)"))
            {
                v.tell("Well, that was completely unnecessary. By the way you're paying for the reparation of that.");
                for (int i=0; i<6; i++) {v.takeGold(8);}
                STATE_mirror = false;
            }
            return;
        }
        v.tell("You find an empty table. It feels barren.");
        if ((v.hasEqualItem(new Item("Mirror")) && doOrNot(v,"Place the mirror? (y/n)")))
        {
            v.tell("You decide to place the mirror that you found on the table");
            v.tell("You find a note behind the mirror. Since when did that get there? It states that you should check your purse");
            v.tell("In the purse, you find 3 extra gold pieces. Since when did those get there?");
            STATE_mirror = true;
            v.giveGold(3);
            return;
        }
        v.tell("If only you had something like a mirror to place here.");
    }

    private void corridor3(Visitor v)
    {
        v.tell("You walk past a mouse hole and bump into a table. You should have been looking where you were going");
        v.tell("On the table is a chunk of cheese and underneath it is a wooden plank.");
        String options = "Will you lean the plank on the table (y/n)";
        if (!doOrNot(v, options)) {return;}

        v.tell("While picking up the plank, some gold pieces drop out of your pocket");
        v.takeGold(4);
        v.tell("A mouse crawls out of the mouse hole and scurries along the wooden plank");
        v.tell("The mouse grabs a knife out of its fur and begins slicing the cheese.");
        v.tell("The mouse tosses you the knife. Throwing it from the blade doesn't seem safe but it at least gives you the chance to grab it by the hilt");
        v.tell("How has it not injured itself already and why isn't it injured now?");
        v.giveItem(new Item("Knife"));
        v.tell("As you leave, you hear the screech of the plank sliding on the table followed by a thump.");

    }

    private void corridor5(Visitor v)
    {
        if (STATE_pandaWall)
        {
            v.tell("This corridor feels much better thanks to you.");
            v.tell("You notice a faint glimmer behind the painting");
            v.giveGold(2);
            v.tell("Attached to the gold was a note saying thank you. Looks like the other visitors in here appreciate cute artwork and pandas.");
            return;
        }
        v.tell("These walls in particular seem especially barren.");
        v.tell("It sure would be nice if there was a painting here or something.");
        //Cute painting found in Room_ec221008
        if (v.hasEqualItem(new Item("cute painting")) && doOrNot(v,"Hang the panda painting on the wall? (y/n)"))
        {
            v.tell("You hang the cute painting onto the wall.");
            v.tell("Awww!");
            v.giveGold(4);
            STATE_pandaWall = true;
        }
    }

    private void corridor6(Visitor v)
    {
        if (STATE_torchOn)
        {
            c6_lightOn(v);
            return;
        }
        v.tell("Nothing remains. All the light has been stolen from this room leaving a pitch black void.");
        v.tell("Struggling to find your way across, you bump into yet another table. Shocked by your sudden appearance, the table shakes, dropping two objects.");
        v.tell("You go to pick them up, it seems that they are a stick and a lighter");
        if (doOrNot(v, "Will you light the stick? It will help you see better (y/n)"))
        {
            v.tell("The nearby torches happily illuminate, as if they were waiting for a hero to rescue them from their gloomy prison.");
            v.tell("On the table, you find a third object. This one did not join its brethren on the floor.");
            v.giveItem(Room_ec22484.key);
            v.tell("This key seems kind of burnt, maybe it had an accident with a flamethrower?");
            v.tell("The treasure you seek has a peculiar association with a certain feline friend.");
            STATE_torchOn = true;
            return;
        }
        v.tell("You decide to proceed onwards in the darkness.");
        c6_steal(v);
        v.tell("Just as you're about to leave, a chunk of coal falls on your head.");
        v.giveItem(new Item("Coal"));
        coalCount++;
    }

    private void c6_lightOn(Visitor v)
    {
        v.tell("Upon returning to this corridor you find a whiteboard. It states that it is giving money to visitors in exchange for food.");
        //Food found in Room_ec22664
        if (v.hasIdenticalItem(Room_ec22664.food) && doOrNot(v, "Will you donate your food? (y/n)"))
        {
            v.tell("Your hear something scurry into your pocket.");
            v.giveGold(2);
        }
        if (doOrNot(v, "Switch off the torch? (y/n)"))
        {
            v.tell("You decide to blow out the torch. All of them go out in the process.");
            STATE_torchOn = false;
        }
    }

    private void c6_steal(Visitor v)
    {
        int stealChance = 1;
        //Shield found in Room_ec22545
        if (v.hasEqualItem(new Item("Shield"))) {stealChance = 5;}
        if (new Random().nextInt(stealChance) == 0)
        {
            v.takeGold(9);
            v.tell("Looks like someone took the opportunity while you were blinded. Unfortunate.");
        }
        else {v.tell("Someone tries to sneak up behind you and steal your gold, but you were able to block them with your shield.");}
    }

    private void corridor7(Visitor v)
    {
        v.tell("You find a fireplace off to the side. What a strange place to put it. Next to it, is a sign saying: 'Coal donations here'");
        v.tell("You have " + coalCount + " chunks of coal on you.");
        if (coalCount == 0)
        {
            v.tell("You're broke, come back with more coal.");
            return;
        }
        if (doOrNot(v, "Would you like to give in your coal? (y/n)"))
        {
            for (int i=0; i<coalCount; i++) {v.giveGold(3);}
            coalCount = 0;
            v.tell("The fireplace is nice and warm now");
        }

    }

    private void corridor8(Visitor v)
    {
        v.tell("You enter the room to find a massive pool of water. Turning your attention to the ceiling, you notice water dripping from the ceiling. That must be the cause of this mess.");
        v.tell("It seems to have come from the bathroom since this corridor isn't directly below Alex's room. It also can't have been their fault since they can't even use the bathroom for ... reasons.");
        v.tell("Whoever was in there must have been really careless for this to happen.");
        v.tell("Unfortunately, you have no cleaning supplies on you so you can't tidy this up but at least someone was nice enough to leave a pair of boots by the front.");
        v.tell("You give a donation as requested and take the boots.");
        v.takeGold(8);
        v.tell("...");
        v.tell("Reaching the end of the corridor, you put the boots in the red circle indicated and open the door to the next room.");
    }

    private void corridor10(Visitor v)
    {
        v.tell("This corridor seems extra long with one massive window on the left, stretching across the entire corridor.");
        final int SPECIAL_CHANCE = 3;
        if (new Random().nextInt(SPECIAL_CHANCE) == 0)
        {
            c10_window(v);
            return;
        }
        v.tell("Wanting to get through quickly, you dash as fast as you can!!");
        v.tell("Along the way, you find a group of turtles that want to race. Bring it on!");
        v.tell("...");
        delayText(v);
        switch (new Random().nextInt(5))
        {
            case 0:
                v.tell("You came first!!"); v.giveGold(9); break;
            case 1:
                v.tell("Second the best!"); v.giveGold(7); break;
            case 2:
                v.tell("You barely reached the podium. Well done!"); v.giveGold(5); break;
            case 3:
                v.tell("You participated. Good job."); v.giveGold(2); break;
            case 4:
                v.tell("whelp."); v.takeGold(3); break;
        }
    }

    private void c10_window(Visitor v)
    {
        v.tell("Finally a window. It feels so isolating in this house after so long.");
        v.tell("Exhausted from your long and eventful journey through this treacherous house, you decide to take a leisurely stroll through the corridor and take a look through the window.");
        delayText(v);
        v.tell("Its been a while since you first entered here hasn't it? You entered in the morning and now its already the middle of the night. Time flies." + "\n");
        v.tell("You are greeted by an array of buildings. I wonder if there's anyone working in this hour, maybe they're finishing a late night project? Each building is lit up with mini orange lights as if someone brought the starry sky down to Earth. Turning your attention to the bridge below, you see a train passing by. A collection of windows, leaving as swiftly as it came. The trains may still be packed now but by the time you leave, its likely to be quiet ride. After some more thought, you conclude that you might prefer that." + "\n");
        v.tell("You see more people walking down the streets with their families as you move further up. Melting into your thoughts, you are reminded of what the streets were like a couple of years ago. The fear of crimes that had once strangled you is almost gone now. This hour feels so much more lively." + "\n");
        v.tell("Finally you reach the roads. Dozens of cyclists pass by, rushing to get home. The occasional bus drives forward determined to serve anyone along the way. As the moonlight shines down on the city, you forge onwards to continue your journey." + "\n");
    }
}

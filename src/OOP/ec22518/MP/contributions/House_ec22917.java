package OOP.ec22518.MP.contributions;

class House_ec22917 implements Visitable {

    Room roomAS;
    Room roomZS;
    Room roomMS;

    public House_ec22917() {
        roomAS = new Room_ec22917();
        roomZS = new Room_ec22937();
        roomMS = new Room_ec22884();
    }

    public Direction visit(Visitor v, Direction d) {

        final int houseStartGold = 5;
        v.giveGold(houseStartGold);
        v.tell("[You have been given 5 pieces of gold to begin with. Wrong choices lose gold, discoveries gain gold.]");
        v.tell("");

        houseDes(v);

        v.tell("");
        chooseRoomAndDir(v);

        chooseExit(v);

        return Direction.TO_SOUTH;
    }

    public void chooseRoomAndDir(Visitor v)
    {
        v.tell("Beyond the pavilion you see 3 doors from 3 different directions.");
        String optionsRoom = "a) enter Alex's Room\nb) enter the Mystical Garden\nc) enter the room with the red X";
        char[] optRoom = {'a', 'b', 'c'};


        switch(v.getChoice(optionsRoom, optRoom))
        {
            case 'a':
                v.tell("");
                v.tell("Which direction would you like to enter Alex's Room from?");
                roomZS.visit(v, chooseDirection(v));
                break;
            case 'b':
                v.tell("");
                v.tell("Which direction would you like to enter the Mystical Room from?");
                roomAS.visit(v, chooseDirection(v));
                break;
            case 'c':
                v.tell("");
                v.tell("Which direction would you like to enter the room with the red X from?");
                roomMS.visit(v, chooseDirection(v));
                break;
            default:
                System.out.println("Room choice error.");
                break;
        }
    }

    public Direction chooseDirection(Visitor v)
    {
        Direction enterFrom = Direction.FROM_NORTH;

        String optionsDir = "a) North\nb) East\nc) South\nd) West";
        char[] optDir = {'a', 'b', 'c', 'd'};

        switch(v.getChoice(optionsDir, optDir))
        {
            case 'a':
                enterFrom = Direction.FROM_NORTH;
                break;
            case 'b':
                enterFrom = Direction.FROM_EAST;
                break;
            case 'c':
                enterFrom = Direction.FROM_SOUTH;
                break;
            case 'd':
                enterFrom = Direction.FROM_WEST;
                break;
            default:
                System.out.println("Direction choice error.");
                break;
        }

        return enterFrom;
    }

    public void chooseExit(Visitor v)
    {
        v.tell("");

        String optionsExit = "a) exit house\nb) go back to rooms";
        char[] optExit = {'a', 'b'};

        while(v.getChoice(optionsExit, optExit) != 'a')
        {
            v.tell("");
            chooseRoomAndDir(v);
            v.tell("");
            chooseExit(v);
            return;
        }

        v.tell("");
        v.tell("You have entered the cellar of the house. Everything is built with stone, rows of torch flames on either sides of the wall.");
        v.tell("There is a 3-tailed dragon asleep on the path, behind the dragon is a door, most likely the exit. The is a slight purple glow, almost drawing you in.");
        v.tell("You have to get past the dragon to exit...\n");

        if(v.hasEqualItem(new Item("Sword")))
        {
            String optionsSword = "a) use the sword\nb) run past the sleeping dragon to the exit";
            char[] optSword = {'a', 'b'};

            switch(v.getChoice(optionsSword, optSword))
            {
                case 'a':
                    v.tell("\n[cLaNgGGg!]\nYou strike the dragon right above the nose, almost able to see the birds flying in circles above its head.");
                    v.tell("You have exited the house.");
                    break;
                case 'b':
                    v.tell("\n[kata-kata-kata-kata.....]\ngrOWLLL!!\nUh oh, looks like you woke up the dragon.");
                    v.takeGold(10); v.tell("[-10 gold]");
                    v.tell("[cLaTTeR!]\nThe dragon has tossed you out of the house.");
                    break;
                default:
                    System.out.println("Sword choice error.");
                    break;
            }
        }
        else
        {
            String optionsNoSword = "a) talk to the dragon\nb) run past the sleeping dragon to the exit";
            char[] optNoSword = {'a', 'b'};

            switch(v.getChoice(optionsNoSword, optNoSword))
            {
                case 'a':
                    v.tell("\nYou wake the dragon up by yelling.\n[grOWLLL!!]");
                    v.tell("'GIMME GOLD!'\nYou try to distract the dragon.");

                    String optionsDistract = "\na) offer the dragon 7 pieces of gold\nb) offer to bring back the dragon a chest full of gold";
                    char[] optDistract = {'a', 'b'};

                    switch(v.getChoice(optionsDistract, optDistract))
                    {
                        case 'a':
                            v.tell("");
                            v.takeGold(7); v.tell("[-7 gold]");
                            v.tell("[cLaTTeR!]\nThe dragon has tossed you out of the house.");
                            break;
                        case 'b':
                            v.tell("\n'Hmmm, how can I guarantee you speak only truth...?'\n...\nThe dragon talks?! Why didn't you realise that earlier...");
                            v.tell("You give the dragon 2 pieces of dragon as a promise to return for more.");
                            v.takeGold(2); v.tell("[-2 gold]");
                            v.tell("It's mean to make promises you can't keep. On the bright side, you have exited the house.");
                            break;
                        default:
                            System.out.println("Distract choice error.");
                            break;
                    }

                    break;
                case 'b':
                    v.tell("[kata-kata-kata-kata.....]\n[grOWLLL!!]\nUh oh, looks like you woke up the dragon.");
                    v.takeGold(10); v.tell("[-10 gold]");
                    v.tell("[cLaTTeR!]\nThe dragon has tossed you out of the house.");
                    break;
                default:
                    System.out.println("Non-sword choice error.");
                    break;
             }

         }

 }

    public void houseDes(Visitor v) {
        v.tell("You're brought to your senses by the scent of smoke. You are in a courtyard where you can see a painting on the ceiling.");
        v.tell("The painting is of Persephone, Queen of the Underworld, with Artemis, Greek Goddess of Nature and Hephaestus, patron of crasftsmen sitting in the clouds behind Her.");

        v.tell("");
        v.tell("The smell of smoke is coming from the wooden flame torches that are lit on both sides of you.");
        v.tell("In front of you is a black pavilion with bronze statue of a sea serpent, engulfed in vines.");
        v.tell("");

        String optionsCourtyard = "a) approach the torch flame\nb) approach the pavilion";
        char[] optCourtyard = {'a', 'b'};

        switch(v.getChoice(optionsCourtyard, optCourtyard))
        {
            case 'a':
                v.tell("");
                v.tell("Ouch!");
                v.takeGold(2); v.tell("[-2 gold]");
                break;
            case 'b':
                v.tell("");
                v.tell("You see something on the floor of the pavilion.");
                v.giveItem(new Item("Sword"));
                v.giveGold(2); v.tell("[+2 gold]");
                v.tell("Engraved on the sheath is a 3-tailed dragon. What could that possibly mean...");
                break;
            default:
                System.out.println("Courtyard choice error.");
                break;
        }

    }

}

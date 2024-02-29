package OOP.ec22518.MP.contributions;

class House_ec22995 implements Visitable {
    private final Room[] Rooms = new Room[3];
    private boolean gardenDoorOpen = true;
    private boolean entranceKey = false;
    private Direction leavingDirection = Direction.UNDEFINED;
    static final Item Totem = new Item("Totem");
    static final Item keyOfSuccess = new Item("Key of Success");
    static final Item weirdKey = new Item("Weird Key");

    public House_ec22995() {
        Rooms[0] = new Room_ec22995();
        Rooms[1] = new Room_ec22992();
        Rooms[2] = new Room_ec22925();
    }
    
    private class MyVisitor implements Visitor {
        private Visitor visitor;
        private boolean isInjured = false;
        private int HP;
        private int purse;

        MyVisitor(Visitor visitor, Direction arriving) {
            this.visitor = visitor;
            HP = 20;
            purse = 5;
            tell("Hello Visitor, your journey in the Winchester Mystery house is about to begin. \nHere you'll see strange things, things you've never seen before. \nBe brave... \n -- You begin your adventure with " + HP + " Health Points, and " + purse + " pieces of gold. Be careful not to die... --");
        }

        public int getHP() {
            return HP;
        }

        public void getDamage(int damage) {
            if((HP - damage) > 0) {
                HP -= damage;
                isInjured = true;
            }
            else {
                visitor.tell("This blow has been fatal, you die. \n-- Your adventure ends here, bye bye --");
                System.exit(0);
            }
            tell("You have " + HP + " Health points remaining.");
        }

        public int getPurse() {
            return purse;
        }

        public void tell(String messageForVisitor){visitor.tell(messageForVisitor);}
    
        public char getChoice(String descriptioOfChoices, char[] arrayOfPossibleChoices){return visitor.getChoice(descriptioOfChoices, arrayOfPossibleChoices);}
    
        public boolean giveItem(Item itemGivenToVisitor){return visitor.giveItem(itemGivenToVisitor);}
    
        public boolean hasIdenticalItem(Item itemToCheckFor){return visitor.hasIdenticalItem(itemToCheckFor);}
        
        public boolean hasEqualItem(Item itemToCheckFor){return visitor.hasEqualItem(itemToCheckFor);}
    
        public void giveGold(int numberOfPiecesToGive){
            visitor.giveGold(numberOfPiecesToGive);
            purse += numberOfPiecesToGive;
        }
        
        public int takeGold(int numberOfPiecesToTake){
            if ((purse - numberOfPiecesToTake) >= 0)
            purse -= numberOfPiecesToTake;
            return visitor.takeGold(numberOfPiecesToTake);
        }
    }

    public Direction visit(Visitor originalVisitor, Direction arriving) {
        MyVisitor visitor = new MyVisitor(originalVisitor, arriving);
        int directionNumber = get_directionNumber(arriving);

		switch (directionNumber) {
			case 1:
				return entranceDoor(arriving, visitor);
			case 2:
				return gardenDoor(arriving, visitor);
			case 3:
				return scaryWall(arriving, visitor);
			case 4:
				return Lost(visitor);
			default: return Direction.opposite(arriving);
		}
    }

    // returns an integer depending on the arriving direction of the user
	//
	private int get_directionNumber(Direction arriving) {
		if ((arriving == Direction.FROM_SOUTH)) return 1;
		if ((arriving == Direction.FROM_NORTH) || (arriving == Direction.FROM_WEST)) return 2;
		if ((arriving == Direction.FROM_EAST)) return 3;
		if (arriving == Direction.UNDEFINED) return 4;
		else return 0;
	}

    // The user is arriving from east. He can't enter the house
    //
    private Direction scaryWall(Direction arriving, MyVisitor visitor) {
        visitor.tell("As you walk you endup at te bottom of a huge scary wall, you're so afraid that you decide to turn around and leave. \n I guess this is the end for you. \n-- You leave towards West --");
        return Direction.opposite(arriving);
    }

	// The user is lost, returns undefined direction
    //
	private Direction Lost(MyVisitor visitor) {
		visitor.tell("Sorry my brother you're lost. We can't help you here so see you soon I guess...");
		return Direction.UNDEFINED;
	}
	
	// The user is arriving either from north or west, he tries to enter through the garden's door
	//
	private Direction gardenDoor(Direction arriving, MyVisitor visitor) {
        char close;
		if(arriving == Direction.FROM_NORTH) {
            visitor.tell("As you walk, you finally reach the Winchester mystery house, You don't see the entrance door but... \nOn your left You see a little garden, you walk towards it.");
        }
        else {
            visitor.tell("As you walk, you finally reach the Winchester mystery house, You don't see the entrance door but... \nOn your right You see a little garden, you walk towards it.");
        }

        if(gardenDoorOpen) {
            visitor.tell("You approach the door, it seems open. You try to open it and... \n-- You just entered the house --");
            close = YesNoChoice("Do you wish to close the door behind you ? (Y)es, (N)o", visitor);
        }
        else {
            visitor.tell("You approach the door, You try to open it so hard but it won't budge. \nSomebody must have closed it. You have no choice but to leave. \n-- You turn around and leave --");
            return Direction.opposite(arriving);
        }

        if((close == 'Y') || (close == 'y')) {
            gardenDoorOpen = false;
        }

        enterOffice(arriving, visitor);

        return leavingDirection;
	}

    // The user chooses either yes or no
    //
    private char YesNoChoice(String message, MyVisitor visitor) {
        char[] YesNo = {'y', 'Y', 'N', 'n'};
        return visitor.getChoice(message, YesNo);
    }

    private Direction entranceDoor(Direction arriving, MyVisitor visitor) {
        char[] answers = {'a', 'b', 'c', 'd', 'e', 'A', 'B', 'C', 'D', 'E'};
        char answer;
        if (!entranceKey) {
            visitor.tell("As you walk, you finally reach the Winchester mystery house. In front of the huge entrance door a man is standing holding a key. \n\" If you wish to enter the mansion you will have to answer my riddle \"\n\nA man is found murdered on a Sunday morning. His wife calls the police, who question the wife and the staff, and are given the following alibis:");
            answer = visitor.getChoice("(A) The wife says she was sleeping \n(B) The butler said he was cleaning the closet \n(C) The gardener declared he was picking vegetables \n(D) The maid explained she was getting the mail \n(E) The cook said she was preparing breakfast \nImmediately, the police arrest the murdered. Who did it and how did the police know?", answers);
            visitor.tell("Correct answer: The maid, because no mail is delivered on Sunday.");
            if(!((answer == 'd') || (answer == 'D'))) {
                visitor.tell("You failed getting the key, the man keeps his key. \nYou are forced to leave. \n-- You turn around and leave --");
                return Direction.opposite(arriving);
            }
            else {
                entranceKey = true;
                visitor.tell("The man gives you the key and you use it to open the entrance door, You finally made it inside the Winchester Mystery house.");
            }
        }
        else {
            visitor.tell("You already have the key, you enter the house through the main entrance door.");
        }
        entryHall(arriving, visitor);

        return leavingDirection;
    }

    // The user enters a sas after the entrance door
    //
    private void entryHall(Direction arriving, MyVisitor visitor) {
        char[] choices = {'a', 'b', 'c', 'd', 'e', 'A', 'B', 'C', 'D', 'E'};
        boolean boughtSomething = false;
        visitor.tell("A man welcoms you with open arms as you enter the hall. \nAs you speak with him he offers you to buy he something.");
        char answer = visitor.getChoice("(A) 3 gold: litle lucky totem \n(B) 8 gold: The key to success \n(C) 5 gold: Encouraging hug \n(D) No thanks \n(E) You choose to attack him", choices);
        if((answer == 'a') || (answer == 'A')) {
            visitor.tell("The man gives you a lucky Totem in exchange of 3 gold pieces.");
            boughtSomething = true;
        }
        else if((answer == 'b') || (answer == 'B')) {
            visitor.tell("The man gives you the key of success in exchange of 8 gold pieces.");
            visitor.giveItem(keyOfSuccess);
            boughtSomething = true;
        }
        else if((answer == 'c') || (answer == 'C')) {
            visitor.tell("The man gives you a hug.");
            boughtSomething = true;
        }
        else if((answer == 'd') || (answer == 'D')) {
            visitor.tell("You don't buy anything and go on to the next room.");
        }
        else {
            visitor.tell("You try to kill that kind man, what a shame he fights back and hurts you. \n-- You are deeply injured --");
            visitor.getDamage(13);
        }

        if (boughtSomething) {
            visitor.tell("because you bought something the man gives you a weird key in exchange...");
            visitor.giveItem(weirdKey);
        }
        mysteryRoom(arriving, visitor);

    }

    private void mysteryRoom(Direction arriving, MyVisitor visitor) {
        leavingDirection = Rooms[0].visit(visitor, arriving);

        int directionNumber = get_directionNumber2(leavingDirection);

		switch (directionNumber) {
			case 1:
				moneyBouncer(leavingDirection, visitor);
                break;
			case 2:
				climbingWall(leavingDirection, visitor);
                break;
			default: leaveHouse(leavingDirection, visitor);
		}


    }

    // returns an integer depending on the arriving direction of the user
	//
	private int get_directionNumber2(Direction arriving) {
		if (arriving == Direction.FROM_SOUTH) return 1;
		if (arriving == Direction.FROM_EAST) return 2;
		else return 0;
	}

    private void climbingWall(Direction arriving, MyVisitor visitor) {
        char[] choices = {'r', 'R', 'l', 'L'};

        visitor.tell("You see 3 pieces of gold on the ground \n-- You take the gold -- \n You look up and see a wall, you decide to climb it. \n-- You climb the wall and fall outside of the house, you get fall damages --");
        visitor.giveGold(3);
        visitor.getDamage(6);
        char answer = visitor.getChoice("You stand up and have the choice to either go on the (L)eft or the (R)ight", choices);
        
        if((answer == 'r') || (answer == 'R')) 
            enterOffice(Direction.FROM_WEST, visitor);

        if((answer == 'l') || (answer == 'L')) 
            entranceDoor(Direction.FROM_SOUTH, visitor);
    }

    private void leaveHouse(Direction arriving, MyVisitor visitor) {
        visitor.tell("By leaving the mystery room you left the house. Bye Bye \n-- You walk away --");
        leavingDirection = Direction.TO_NORTH;
    }

    // The user enters the office
    //
    private void enterOffice(Direction arriving, MyVisitor visitor) {
        char[] choices = {'f', 'F', 'l', 'L'};
        leavingDirection = Rooms[2].visit(visitor, arriving);
        char answer = visitor.getChoice("You leave the room and have the choice to either go (L)eft or (F)orward", choices);
        
        if((answer == 'f') || (answer == 'F')) 
            moneyBouncer(arriving, visitor);

        if((answer == 'l') || (answer == 'L')) 
            mysteryRoom(Direction.FROM_SOUTH, visitor);
    }

    //
    private void moneyBouncer(Direction arriving, MyVisitor visitor) {
        visitor.tell("You walk out the room and see a bouncer. He asks you for money.");
        
        if(visitor.purse >= 5){
            visitor.takeGold(5);
            visitor.tell("You give 5 gold and the bouncer let's you in. \n-- You enter the next room --");
            if(!visitor.hasIdenticalItem(weirdKey)) {
                visitor.tell("The bouncer just saw you didn't have the key so he gives it to you.");
                visitor.giveItem(weirdKey);
            }
        }
        else {
            visitor.tell("What 5 gold ?, You don't have enough and try to enter either way, the bouncer stabs you. \n-- You manage to enter the room but you lost few HP --");
            visitor.getDamage(7);
        }
        enterDarkRoom(arriving, visitor);
    }

    private void enterDarkRoom(Direction arriving, MyVisitor visitor) {
        leavingDirection = Rooms[1].visit(visitor, arriving);
        leavingHouse(arriving, visitor);
    }

    private void leavingHouse(Direction arriving, MyVisitor visitor) {
        if(visitor.hasIdenticalItem(weirdKey)) {
            visitor.tell("As you leave the room you find a little with a weird keyhole, you try using the weird key and it works. -- You leave the house, congratulations --");
        }
        else {
            visitor.tell("As you leave the room you find a little with a weird keyhole, you don't know what to do and stay here until you die...");
            visitor.getDamage(20);
        }
        leavingDirection = Direction.TO_WEST;
    }
}

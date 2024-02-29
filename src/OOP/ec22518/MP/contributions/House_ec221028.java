package OOP.ec22518.MP.contributions;

class House_ec221028 implements Visitable {
	Room r1;
	Room r2;
	Room r3;
	final static Item woodenSword = new Item("Wooden Sword");
	final static Item smooch = new Item("Smooch");
	
	House_ec221028(){
		r1 = new Room_ec221028();
		r2 = new Room_ec22591();
		r3 = new Room_ec22617();
	}

	public Direction visit(Visitor v, Direction d){
		v.tell("Welcome to my house. Don't mind the broken window or the mold, just a temporary setback.");
		v.tell("Also don't touch anything unless I tell you it's safe, wouldn't want any mishaps hahaha.");
		v.tell("Well I told that to the last guy too, and we all know how that turned out...");
		v.tell("It's dangerous to go alone, take this.");
		v.giveItem(woodenSword);
		v.giveGold(5);
		
		v.tell("My house has 3 rooms, Merchant Orhan lives in the first room to the left (west). He's a good guy and a tricky businessman");
		v.tell("To the door in front of us (north) is someone else's creation. Something about candles and a big lever. Rent isn't free you know!");
		v.tell("The door to our right (east) is again someone else's creation. I heard the creator keeps something good behind the painting. Very dusty though");
		
		char choice = 'X';
		while (choice != 'S'){
			choice = v.getChoice("Which room would you like to visit? The Western door (W), the Eastern door(E) or the Northern door (N). If you'd like to leave the house head South (S).", new char[]{'W','E','N','S'});
			switch (choice){
				case 'W':
					r1.visit(v, Direction.FROM_EAST);
				break;
				
				case 'E':
					r3.visit(v, Direction.FROM_WEST);
				break;
				
				case 'N':
					r2.visit(v, Direction.FROM_SOUTH);
				break;
				
				case 'S':
					v.tell("See you later, tell Big Joe I said he owes me 3 gold coins.");
				break;
				
				case '?':
					v.tell("How'd you manage to get here? I'll give you a gift for your efforts.");
					v.giveGold(5);
					v.giveItem(smooch);
				break;
				
				default:
					v.tell("You gotta work with me here and tell me something, I'm busting my back over here!");
					v.takeGold(1);
			}
			v.tell("");
		}
		
		v.tell("I hope we meet again friend.");
		return Direction.TO_SOUTH;
	}
	
	// for testing
	public static void main (String[] a){
		Visitor v = new IOVisitor(System.out, System.in);
		Direction d = Direction.FROM_SOUTH;
		House_ec221028 h = new House_ec221028();
		h.visit(v,d);
		return;
	}
}

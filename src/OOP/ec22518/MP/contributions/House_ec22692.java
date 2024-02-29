package OOP.ec22518.MP.contributions;

class House_ec22692 implements Visitable {
	
	Room r1;
	Room r2;
	Room r3;
	
	House_ec22692() {
		r1 = new Room_ec22941();
		r2 = new Room_ec22692();
		r3 = new Room_ex21566();
	}
	
	
	
	
					// r3 N //
	
	
	
	// r1  W      house hall way             r2 E  //


			    // secret exit S//
	
	public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
		char choice;
		
		Direction direction = directionVistorArrivesFrom;
		visitor.tell("Welcome to my (ec22692) house! You are currently in the hall way and enter the room infront of you");
		direction = r3.visit(visitor, direction);
		
		if(direction == Direction.TO_SOUTH) {
			visitor.tell("You left the room and found your self in the back passage of the house!");
			visitor.tell("As you search you find some gold!");
			visitor.giveGold(5);
			
			choice = visitor.getChoice("You find a passage that leads to the exit. You have some choices \na)Exit \nb)Return to the house", 
									   new char[]{'a', 'b'});
			if(choice == 'a') {
				visitor.tell("You have exited the house");
				return direction;
			}
			
			if(choice == 'b') {
				visitor.tell("You have returned back to the hallway");
			}
		}
		
		if(direction == Direction.TO_NORTH) {
			visitor.tell("You cannot proceed any further and have gone back to the hallway");
			
		}
		
		if(direction == Direction.TO_EAST) {
			visitor.tell("You cannot proceed any further and have gone back to the hallway");
		}
		
		if(direction == Direction.TO_WEST) {
			visitor.tell("You cannot proceed any further and have gone back to the hallway");
		}
		
		choice = visitor.getChoice("After returning back to the hallway, you see two rooms, one on the left, and one on the right. (l)eft or (r)ight?",
						 new char[]{'l','r'});
		if(choice == 'l') {
			visitor.tell("You approach the room on the left and enter it");
			direction = r1.visit(visitor, Direction.UNDEFINED);
			visitor.tell("After exploring this room you head your way to the final room");
			direction = r2.visit(visitor,Direction.UNDEFINED);
			visitor.tell("After exploring every room a hidden pathway opens up and you exit the house");
			return Direction.TO_NORTH;
		}
		
		if(choice == 'r') {
			visitor.tell("You approach the room on the left and enter it");
			direction = r2.visit(visitor, Direction.UNDEFINED);
			visitor.tell("After exploring this room you head your way to the final room");
			direction = r1.visit(visitor,Direction.UNDEFINED);
			visitor.tell("After exploring every room a hidden pathway opens up and you exit the house");
			return Direction.TO_NORTH;
		}
		return Direction.TO_EAST;
	}
	
}

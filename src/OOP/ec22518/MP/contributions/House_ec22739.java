package OOP.ec22518.MP.contributions;

class House_ec22739 implements Visitable {
	Room ec22449, ec22425, ec22519, ec22772;
	public House_ec22739(){
		ec22449 = new Room_ec22449(); 
		ec22425 = new Room_ec22425();
		ec22519 = new Room_ec22519();
		ec22772 = new Room_ec22772();
	}
	
	public Direction visit(Visitor v, Direction da) {
		Direction d = da;
		boolean left = false;
		int roomNum = 1;
		
		while(left == false) {
			if (roomNum == 1) {
				v.tell("Currently in room 22449. Can only leave the house by using the south exit.");
				if (d == Direction.TO_EAST) {
					d = ec22519.visit(v, d);
					roomNum = 3;
				}
				else if (d == Direction.TO_NORTH) {
					d = ec22425.visit(v, d);
					roomNum = 2;
				}
				else if (d == Direction.TO_SOUTH) {
					v.tell("You have left the house.");
					left = true;
					return Direction.TO_SOUTH;
				}
				else {
					v.tell("Can't exit the room 22449 from west. You are still in the room 22449...");
				}
			}
			else if (roomNum == 2) {
				if (d == Direction.TO_SOUTH) {
					d = ec22449.visit(v, d);
					roomNum = 1;
				}
				else if (d == Direction.TO_EAST) {
					d = ec22519.visit(v, d);
					roomNum = 3;
				}
				else {
					v.tell("Can't exit the room 22425 from north or west. You are still in the room 22425...");
				}
			}
			else if (roomNum == 3) {
				if (d == Direction.TO_WEST) {
					d = ec22772.visit(v, d);
					roomNum = 4;
				}
				else if (d == Direction.TO_SOUTH) {
					d = ec22449.visit(v, d);
					roomNum = 1;
				}
				else {
					v.tell("Can't exit the room 22519 from east or north. You are still in the room 22519...");
				}
			}
			else if (roomNum == 4) {
				if (d == Direction.TO_NORTH) {
					d = ec22425.visit(v, d);
					roomNum = 2;
				}
				else if (d == Direction.TO_WEST) {
					d = ec22519.visit(v, d);
					roomNum = 3;
				}
				else {
					v.tell("Can't exit the room 22772 from south or east. You are still in the room 22772...");
				}
			}



		}
		return Direction.TO_SOUTH;

	}

}


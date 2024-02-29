package OOP.ec22518.MP.contributions;

class House_ec22421 implements Visitable {
	//Variables to store room objects
	Room ec22421;
	Room ec22425;
	Room ec22431;
	Room ec22449;

	//Constructor
	House_ec22421() {
		ec22421 = new Room_ec22421();
		ec22425 = new Room_ec22425();
		ec22431 = new Room_ec22431();
		ec22449 = new Room_ec22449();
	}

	// Returns direction the visitor leaves towards.
	public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
		Direction direction = directionVistorArrivesFrom;
		boolean leave = false; //A boolean flag for the while loop.
		int in_room = 21; //A number for each room default to number of the first room.

		direction = ec22421.visit(visitor, direction);
		in_room = 21;

		//While loop set with condition leave.
		while(leave == false) {
			if (in_room == 21) {
				visitor.tell("You are in room 22421, you can exit the house via this room by leaving the room from south.");
				if (direction == Direction.TO_EAST) {
					direction = ec22449.visit(visitor, direction);
					in_room = 49;
				}
				else if (direction == Direction.TO_NORTH) {
					direction = ec22425.visit(visitor, direction);
					in_room = 25;
				}
				else if (direction == Direction.TO_SOUTH) {
					visitor.tell("You chose to leave the house.");
					leave = true;
					return Direction.TO_SOUTH;
				}
				else {
					visitor.tell("Can not exit the room 22421 from west. You are still in the room 22421...");
				}
			}
			else if (in_room == 25) {
				if (direction == Direction.TO_SOUTH) {
					direction = ec22421.visit(visitor, direction);
					in_room = 21;
				}
				else if (direction == Direction.TO_EAST) {
					direction = ec22431.visit(visitor, direction);
					in_room = 31;
				}
				else {
					visitor.tell("Can not exit the room 22425 from north or west. You are still in the room 22425...");
				}
			}
			else if (in_room == 31) {
				if (direction == Direction.TO_WEST) {
					direction = ec22425.visit(visitor, direction);
					in_room = 25;
				}
				else if (direction == Direction.TO_SOUTH) {
					direction = ec22449.visit(visitor, direction);
					in_room = 49;
				}
				else {
					visitor.tell("Can not exit the room 22431 from east or north. You are still in the room 22431...");
				}
			}
			else if (in_room == 49) {
				if (direction == Direction.TO_NORTH) {
					direction = ec22431.visit(visitor, direction);
					in_room = 31;
				}
				else if (direction == Direction.TO_WEST) {
					direction = ec22421.visit(visitor, direction);
					in_room = 21;
				}
				else {
					visitor.tell("Can not exit the room 22449 from south or east. You are still in the room 22449...");
				}
			}



		}
		return Direction.TO_SOUTH;

	}

}

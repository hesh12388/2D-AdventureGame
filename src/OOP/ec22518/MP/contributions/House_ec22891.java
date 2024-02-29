package OOP.ec22518.MP.contributions;

class House_ec22891 implements Visitable {

	Room room1;
	Room room2;
	Room room3;
	Room room4;
	Room[][] rooms;

    public House_ec22891(){
		room1 = new Room_ec22891();
		room2 = new Room_ec22459();
		room3 = new Room_ec22708();
		room4 = new Room_ec22738();
		rooms = new Room[][]{{room2, room3}, {room4, room1}};
    }

    public Direction visit(Visitor v, Direction d){
		int i=0, j=0, roomsVisited=0;
		Direction newDirection = Direction.FROM_SOUTH;

		while(roomsVisited < 5 && i>=0 && j>=0 && i<rooms.length && j<rooms[0].length){
			newDirection = rooms[i][j].visit(v, newDirection);

			if(newDirection == Direction.TO_NORTH){ i--; newDirection = Direction.FROM_SOUTH; }
			else if(newDirection == Direction.TO_SOUTH){ i++; newDirection = Direction.FROM_NORTH; }
			else if(newDirection == Direction.TO_EAST){ j++; newDirection = Direction.FROM_WEST; }
			else{ j--; newDirection = Direction.FROM_EAST; }

			roomsVisited++;

			v.tell("You open the door and walk through it\n");
		}

		v.tell("This door leads you outside the house.");
		if(roomsVisited == 5){
			v.tell("Fortunately, you were getting quite tired wandering the rooms, so you can now have some rest");
		} else {
			v.tell("You tell yourself that you may revisit this house another time");
		}

		return newDirection;
    }
}

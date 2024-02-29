package OOP.ec22518.MP.contributions;

class House_ec22627 implements Visitable {
	Room[][] rooms;
	private int i = 0 , j = 0 ;
	private final int exitI = 2 , exitJ = 2;
	private final Direction exitD = Direction.TO_EAST ;
	
	House_ec22627()
	{
		rooms = new Room[3][3] ;
		//assign exit room
		rooms[exitI][exitJ] = new Room_ec22627();
		
		//assign other, borrowed rooms
		rooms[0][0] = new Room_ec221028();
		rooms[1][0] = new Room_ec19389();
		rooms[2][0] = new Room_ec22459();
		rooms[2][1] = new Room_ec22413();
		rooms[0][2] = new Room_ec221247();
	}
	
	void traverse(Direction d)
	{
		if(d == Direction.TO_EAST) {j++;}
		if(d == Direction.TO_WEST) {j--;}
		if(d == Direction.TO_NORTH) {i++;}
		if(d == Direction.TO_SOUTH) {i--;}
		
	}
	
	void validate(Visitor v)
	{
		if(i >= rooms.length) {i = 0; v.tell("You found a portal warping you to the opposite end of the house");}
		if(j >= rooms[0].length) {j = 0; v.tell("You found a portal warping you to the opposite end of the house");}
		if(i < 0) {i = rooms.length-1; v.tell("You found a portal warping you to the opposite end of the house");}
		if(j < 0) {j = rooms[0].length-1; v.tell("You found a portal warping you to the opposite end of the house");}
	}
	
	public Direction visit(Visitor v , Direction d)
	{
		Direction move = d ;
		v.tell("Welcome to the house.");
		
		do
		{
			
			//handle empty space within array
			if(rooms[i][j] == null)
			{
				
				v.tell("You have stumbled into the void. Your vision begins to fade. When you awake, you find yourself in the gift shop\n");
				i = exitI ;
				j = exitJ;
				move = exitD ;
			}
			
			//What is a hall if not a small, narrow room
			
			move = rooms[i][j].visit(v, move) ;
			
			//handle movement through the house
			if(i != exitI || j != exitJ || move != exitD)
			{
				traverse(move);
			}
			
			validate(v);
			
		}while(i != exitI || j != exitJ || move != exitD);
		
		v.tell("You have successfully left the house. Congratulations");
		return exitD;
	}
	
}

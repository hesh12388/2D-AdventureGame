package OOP.ec22518.MP.contributions;

public class House_ec22480 implements Visitable {
	Room southRoom;
	Room centreRoom;
	Room eastRoom;
	Room westRoom;
	String currentRoom;
	boolean started;
	
	House_ec22480() 
	{
		southRoom = new Room_ec22479();
		centreRoom = new Room_ec22944();
		eastRoom = new Room_ec22471();
		westRoom = new Room_ec22480();
		started = false;
	}

	public Direction visit(Visitor v, Direction d) 
	{		
		if (!started)
		{
			start(v);
			currentRoom = "southRoom";
			visit(v, southRoom.visit(v, Direction.TO_SOUTH));
		}
		
		if (currentRoom.equals("southRoom") && d.equals(Direction.TO_NORTH)) 
		{
			currentRoom = "centreRoom";
			visit(v, centreRoom.visit(v, d));
		}
		
		if (currentRoom.equals("centreRoom"))
		{
			
			if (d.equals(Direction.TO_EAST)) 
			{
				currentRoom = "eastRoom";
				visit(v, eastRoom.visit(v, d));
			}
			else if (d.equals(Direction.TO_WEST))
			{
				currentRoom = "westRoom";
				visit(v, westRoom.visit(v, d));
			}
			else if (d.equals(Direction.TO_SOUTH)) 
			{
				currentRoom = "southRoom";
				visit(v, southRoom.visit(v, d));
			}
			else if (d.equals(Direction.TO_NORTH))
			{
				garden(v);
				visit(v, centreRoom.visit(v, d));
			}
		}
		
		if (currentRoom.equals("westRoom") && d.equals(Direction.TO_EAST)) 
		{
			currentRoom = "centreRoom";
			visit(v, centreRoom.visit(v, d));
		}
		
		if (currentRoom.equals("eastRoom") && d.equals(Direction.TO_WEST)) 
		{
			currentRoom = "centreRoom";
			visit(v, centreRoom.visit(v, d));
		}
		
		return null;
	}
	
	public void start(Visitor v) 
	{
        v.tell("\nWelcome to Zubair's Dungeon!\n");
        started = true;
	}
	
	public void garden(Visitor v)
	{
        v.tell("You are in a beautiful garden.");
        v.tell("You spot a hole in the fence.");
        v.tell("It looks big enough to climb through.");
        v.tell("What do you do?");
        
        char choice = v.getChoice("a) leave through the hole in the fence\nb) go back into the house", new char[] {'a', 'b'});
        if (choice == 'a') 
        {
        	System.exit(0);
        }
        
        v.tell("Your indecisiveness has angered the winds.");
        v.tell("You were dragged out of the house");
	}
	
	void blocked(Visitor v) 
	{
		v.tell("\nAn invisible wall blocks your path.");
	}
	
}

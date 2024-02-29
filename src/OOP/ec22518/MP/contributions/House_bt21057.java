package OOP.ec22518.MP.contributions;

public class House_bt21057 implements Visitable {

	Room r1;
	Room r2;
	Room r3;
	Room r4;

	House_bt21057 () {
		r1 = new Room_bt21057();
		r2 = new Room_ec22586();
		r3 = new Room_ec22702();
		r4 = new Room_ec22707();
	}
	
	public Direction visit (Visitor visitor, Direction visitorDirection) {
		
		 char [] option =  {'N', 'E','S', 'W', 'L'};
		 char [] didyoulike = {'y', 'n'};
		 
		 
		visitor.tell("Hello there welcome to my house!");
		visitor.tell("You are currnently in the hallway of my House \n You can take either North, South, East or West to reach the respective rooms!");
		visitor.tell("Press L to leave the house.");

		char choice = 'g';
		char like;
		while (choice != 'L') {

			choice = visitor.getChoice("So please enter 'N' for North 'E' for East 'S' for South 'W' for West 'L' to LEAVE", option);
			
		if (choice == 'N') {
			visitor.tell("You have chosen to go The North Room...");
			
			r1.visit(visitor, visitorDirection);
			
			visitor.tell("Now you are done with this room, I would like to ask");
			
			 like = visitor.getChoice("Did you like the Room y/n?", didyoulike);
			
			if (like == 'y') {
				System.out.println(r1);
			}
			else {System.out.println("Sorry to hear about that, hope you find a better room :)");}
				
				
				
			}
			
			else if (choice == 'E') {
				visitorDirection = Direction.TO_EAST;
				
				visitor.tell("You have chosen to go The East Room...");
				
				r2.visit(visitor, visitorDirection);
				
				visitor.tell("Now you are done with this room, I would like to ask");
				
				like = visitor.getChoice("Did you like the Room y/n?", didyoulike);
				
				if (like == 'y') {
					System.out.println(r1);
				}
				else {System.out.println("Sorry to hear about that, hope you find a better room :)");}
				
				
				
			}
			else if (choice == 'S') {
				visitorDirection = Direction.TO_SOUTH;
			
				visitor.tell("You have chosen to go The South Room...");
				
				r3.visit(visitor, visitorDirection);
				
				visitor.tell("Now you are done with this room, I would like to ask");
				
				like = visitor.getChoice("Did you like the Room y/n?", didyoulike);
				
				if (like == 'y') {
					System.out.println(r1);
				}
				else {System.out.println("Sorry to hear about that, hope you find a better room :)");}
				
				
				
				
			}
			else if (choice == 'W') {
				visitorDirection = Direction.TO_WEST;
				
				visitor.tell("You have chosen to go The West Room...");
				
				r4.visit(visitor, visitorDirection);
				
				visitor.tell("Now you are done with this room, I would like to ask");
				
				like = visitor.getChoice("Did you like the Room y/n?", didyoulike);
				
				if (like == 'y') {
					System.out.println(r1);
				}
				else {System.out.println("Sorry to hear about that, hope you find a better room :)");}
				
			
			}
			else if (choice == 'L') {
				visitor.tell("Thank you for coming to my house ;)");
				return visitorDirection;
			}
			
			
		}
	
		return visitorDirection;
		
	}
}
		

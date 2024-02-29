package OOP.ec22518.MP.contributions;

public class House_ec22537 implements Visitable {
    private Room room1;
    private Room room2;
    private Room room3;

    public House_ec22537 () {
        room1 = new Room_ec22537();
        room2 = new Room_ec22475();
        room3 = new Room_ec22480();
    }
    
    @Override
    public Direction visit (Visitor visitor, Direction visitorDirection) {
        char [] option =  {'N', 'E','S', 'L'};
        char [] didyoulike = {'y', 'n'};
     
     
        visitor.tell("Hello, welcome to my house.");
        visitor.tell("Currently, you're in the corridor of my House \n You can take either North, South or West to reach the desired rooms.");
        visitor.tell("Press L to leave the house.");

        char choice = 'z';
        char like;
        while (choice != 'L') {
            choice = visitor.getChoice("So please enter 'N' for North 'E' for East 'S' for South 'W' for West 'L' to LEAVE", option);
            if (choice == 'N') {
                visitor.tell("You are entering the North Room.");
                room1.visit(visitor, visitorDirection);
                like = visitor.getChoice("Did you like the Room (y/n)?", didyoulike);
            if (like == 'y') {
                System.out.println(room1);
            } 
                else {
                    System.out.println("I'm sorry to hear about that, I hope to find a better room.");
                }
            }
            else if (choice == 'E') {
              visitorDirection = Direction.TO_EAST;
              visitor.tell("You are entering the East Room.");
              room2.visit(visitor, visitorDirection);


            like = visitor.getChoice("Did you like the Room (y/n)?", didyoulike);

            if (like == 'y') {
              System.out.println(room2);
            }
            else {
                System.out.println("I'm sorry to hear about that, I hope to find a better room.");
            };
          }
        else if (choice == 'S') {
            visitorDirection = Direction.TO_SOUTH;

            visitor.tell("You are entering the South Room.");

            room3.visit(visitor, visitorDirection);


            like = visitor.getChoice("Did you like the Room (y/n)?", didyoulike);

            if (like == 'y') {
              System.out.println(room3);
            }
            else {
                System.out.println("I'm sorry to hear about that, I hope to find a better room.");
            }
          }
            
          else if (choice == 'L') {
            visitor.tell("Thank you for visiting my house!");
            return visitorDirection;
          }
    }
    return visitorDirection;
  }
}

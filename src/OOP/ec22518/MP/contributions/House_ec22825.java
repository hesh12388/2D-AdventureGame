package OOP.ec22518.MP.contributions;

import java.util.Scanner;

public class House_ec22825 implements Visitable {

    Room rooms [];
    
    public House_ec22825 ()
    {
        rooms = new Room[5];
        rooms[0] = new Room_ec22754();
        rooms[1] = new Room_ec221021();
        rooms[2] = new Room_ec22975();
        rooms[3] = new Room_ec22638();
        rooms[4] = new Room_ec22815();
    }
    
    public Direction visit( Visitor visitor, Direction directionVistorArrivesFrom)
    {
        System.out.println("You have just walked through a gigantic iron door to find yourself within an ancient egyptian palace");
        System.out.println("What do you decide to do? a) Go inside \n b) Leave ");
        
        Scanner sc = new Scanner(System.in);
        char choice = sc.next().charAt(0);
        
        if(choice == 'b')
        {
            return Direction.TO_SOUTH;
        }
        
        System.out.println("As you further head towards the North, you find yourself in a dark hall. You see many old paintings of Pharaoes and their families along with all their belongings. You see many doors that are closed and a brown casket lying next to the old piano. \n You have 3 options: a) Go back South out of the house \n b) Explore the house \n c) Open the brown casket");
        choice = sc.next().charAt(0);
        
        if (choice == 'a')
        {
            return Direction.TO_SOUTH;
        }
        
        else if (choice == 'c')
        {
            System.out.println("You have unlocked a room full of gold coins. Collect as many coins as you can on your way out");
            return Direction.TO_EAST;
        }
        
        while (choice == 'b')
        {
            System.out.println(" A door opens towards your West. A gloomy light iluminates your path. You walk towards the door and exit out of the hallway. You see the path leading to a haunted room. You decide to enter the room.");
            
            Direction d = rooms[0].visit(visitor, Direction.FROM_WEST);
            System.out.println(" You come out from the direction " + d);
            
            System.out.println("You are now again in the dark corridor, follwing the iluminating light that leads you to another room located towards your North. There is a sign on the door that warns you about entering the room. However, you decide to enter the room  ");
            
            Direction n = rooms[1].visit(visitor, Direction.FROM_NORTH);
            System.out.println(" You come out from the direction " + n);
            
            System.out.println("You are now again in the dark corridor, follwing the iluminating light that leads you towards an dark kitchen. You see so many kitchen supplies but you have to collect the bag of gold coins that is stored near the stove. Otherwise you are are unable to continue your journey.  ");
            
            Direction a = rooms[2].visit(visitor, Direction.FROM_EAST);
            System.out.println(" You come out from the direction " + a);
            
            
            System.out.println("You are now again in the dark corridor, with no light that leads you to another room located towards your West. On the way, you find a lamp, with a warning letter saying 'carry at your own risk'. \n What do you decide to do? a) Pick the lamp b) Continue your journey in the dark ");
            
            char answer = sc.next().charAt(0);
            
            if( answer == 'a')
            {
                System.out.println("You see 10 gold coins slipping away from your pocket into a treasure box that is located beside the lamp.");
                }
            
            System.out.println(" As your journey continues, you see a a key hanging from the ceiling. This key will lead you to your next room");
            
            d = rooms[3].visit(visitor, a);
            System.out.println(" You come out from the direction " + d);
            
            
            System.out.println("To your surprise, you find yourself in a bright passage with a modernized passage way, with decorated walls either side. As you move along, you can hear birds chirping and waterfall nearby. You decide to follow the sound, which leads you to the next room.");
            
            Direction c = rooms[4].visit(visitor, d);
            System.out.println(" You come out from the direction " + n);
            
            
            System.out.println("To finish your journey, you will have to pass through this last door, which is a room full of surprises. We suggest you move ahead carefully and be aware of your surroundings. You decide to enter the room ");
            
            c = rooms[4].visit(visitor, Direction.FROM_SOUTH);
            System.out.println(" You come out from the direction " + c);
            
            
            System.out.println("You have now reached the main dark hall of this house. This time you are infront of two doors. You don't know where each of them will lead you so you are advised to choose your next move wisely. What do you wish to do ? a) Choose the black door \n b) Choose the white door");
             choice = sc.next().charAt(0);
        
        if (choice == 'a')
        {
            return Direction.TO_SOUTH;
        }
            else if (choice == 'b')
            {
                return Direction.TO_EAST;
            }
        }
        
        return directionVistorArrivesFrom;
            
    }
    

}

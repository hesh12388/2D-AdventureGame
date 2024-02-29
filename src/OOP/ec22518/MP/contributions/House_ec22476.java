package OOP.ec22518.MP.contributions;

import java.util.Scanner;

class House_ec22476 implements Visitable {
    Room a;
    Room b;
    Room c;
    Room e;
    Room currentRoom;
    static final Item i = new Item("AC");
    
    House_ec22476()
    {
        // Creating room instances
        a = new Room_ec22476();
        b = new Room_ec22716();
        c = new Room_ec22697();
        e = new Room_ec221183();
    }
    
    public static void main(String[] args)
    {
        House_ec22476 house = new House_ec22476();
        Visitor v = new IOVisitor(System.out,System.in);
        house.visit(v, Direction.FROM_SOUTH);
    }
    
    public String input(String statement)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(statement);
        return scanner.nextLine();
    }
    
    public Direction visit(Visitor v, Direction d) 
    {
        String choice;
        // Introductory messages
        v.tell("Welcome to ec22476's house");
        v.tell("You first enter ec22476's room...");
        currentRoom = a;
        Direction dir = currentRoom.visit(v, d);
        
        String cont = input("Would you like to: "+
        "\n1) continue to the next room or "+
        "\n2) leave the house?");
        
        while (!(cont.equals("2")))
        {
            // Using direction to determine next room
            if ((dir.toString()).equals("heading North"))
            {
                // Small activity 
                v.tell("As you leave the room you notice an ajar door.");
                choice = input("Do you: \na) open the door or \nb) continue walking?");
                if (choice.equals("a"))
                {
                    v.tell("As you place you hand on the handle, a gust of wind slams the door shut.");
                    v.tell("I wonder what was behind that door...");
                }
                v.tell("You encounter another door.");
                v.tell("This takes you to Room ec22716");
                currentRoom = b;
                d = Direction.FROM_SOUTH;
            }
            else if ((dir.toString()).equals("heading East"))
            {
                // Small activity
                v.tell("As you leave the room you notice a window.");
                choice = input("Do you: \na) jump out the window or \nb) continue walking?");
                if (choice.equals("a"))
                {
                    v.tell("You are out in the garden.");
                    choice = input("Do you: \na) climb back into the window or \nb) run out the house?");
                    if (choice.equals("a"))
                    {
                        v.tell("You are back in the hallway.");
                    }
                    else
                    {
                        // Leaves house
                        break;
                    }
                }
                v.tell("You encounter another door.");
                v.tell("This takes you to Room ec22697");
                currentRoom = c;
                d = Direction.FROM_WEST;
            }
            else if ((dir.toString()).equals("heading South"))
            {
                v.tell("As you enter the hallway you are surrounded by fog.");
                
                if (v.hasEqualItem(i))
                {
                    v.tell("You posses AC so can fan out the fog.");
                    v.tell("Your path is now clear.");
                    v.tell("You encounter another door.");
                }
                else
                {
                    v.tell("You do not have AC so the fog persists.");
                    v.tell("Your hands are your only guide.");
                    v.tell("You feel another door");
                }
                v.tell("This takes you to Room ec221183");
                currentRoom = e;
                d = Direction.FROM_NORTH;
            }
            else if ((dir.toString()).equals("heading West"))
            {
                v.tell("You found the front door, which leaves the house.");
                d = Direction.FROM_EAST;
                break;
            }
            else
            {
                v.tell("You didn't seem to pick a direction...");
                v.tell("A gust of wind blows you out of the house");
                break;
            }
            
            // Sets direction to direction returned by House
            dir = currentRoom.visit(v, d);
            
            // Allows user to determine if they want to continue looking around the house
            cont = input("Would you like to:\n1) continue to the next room or \n2) leave the house?");
            
        }
        
        // Leaving house
        v.tell("Thanks for visiting house ec22476!");
        return d;
    }
}

package OOP.ec22518.MP.contributions;

public class House_ec22885 implements Visitable {
    //room instance variables
    Room room1;
    Room room2;
    Room room3;
    
    //constructor creating instances of multiple Room and assigning to room instance variables 
    House_ec22885()
    {
        room1 = new Room_ec22885();
        room2 = new Room_ec22426();
        room3 = new Room_ec22982();
    }
    
    //visit method that sends the visitor to different rooms
    public Direction visit(Visitor person, Direction direction) 
    {
        person.tell("");
        person.tell("Welcome to The Manor...");
        person.tell("");
        //set boolean as default false, until changed inside the house
        boolean exit = false;
        
        //deny entry into the house if direction is not TO_NORTH
        if(direction!=Direction.TO_NORTH)
        {
            person.tell("You find that the door is locked, unable to enter the house.");
            person.tell("This is not so good...Or is it?");
            person.tell("Take 1 gold for your troubles...");
            person.tell("");
            person.giveGold(1);
            person.tell("");
            person.tell("Safe travels, Visitor...");
            return direction;
        }
        else
        {
            //while the person hasn't yet left the house
            while(exit!=true)
            {
                //enter room 1
                person.tell("Approaching Room 1.....");
                person.tell("");
                direction = room1.visit(person,direction);
                person.tell("");
                person.tell("Departing Room 1.....");
                person.tell("");
                
                if(direction==Direction.TO_SOUTH)
                {
                    //enter room 2 as normal if room 1 returns the direction of TO_SOUTH
                    person.tell("Approaching Room 2 contributed by ec22426.....");
                    person.tell("");
                    direction = room2.visit(person,direction);
                    person.tell("");
                    person.tell("Departing Room 2.....");
                    person.tell("");
                }
                else
                {
                    //send the visitor to a dead end if room 1 doesn't lead straight to room 2
                    person.tell("After walking for a bit, you stumble across a dead end after leaving the room.");
                    person.tell("There are 2 buttons, green and red.");
                    char[] choiceButton = {'R', 'G', 'r', 'g'};
                    char optionButton = person.getChoice("Do you choose the red button or green button? R/G?", choiceButton);
                    //chance to get gold before heading into room 2
                    if (optionButton=='R'|optionButton=='r')
                    {
                        person.tell("");
                        person.tell("Upon choosing the red button, a gap in the wall opens up and you walk through it.");
                        person.tell("You took a risk...Here, have 2 gold coins before you head into room 2.");
                        person.giveGold(2);
                        person.tell("");
                        person.tell("Approaching Room 2 contributed by ec22426.....");
                        person.tell("");
                        direction = room2.visit(person,direction);
                        person.tell("Departing Room 2.....");
                        person.tell("");
                    }
                    //or chance to lose gold and bypass room 2
                    else
                    {
                        person.tell("");
                        person.tell("Upon choosing the red button, a gap in the wall opens up and you walk through it.");
                        person.tell("You must have thought green was safe, fool's choice.");
                        person.tell("");
                        person.tell("I shall take 2 gold for my hospitality so far. Maybe you will get it back in the next room...");
                        person.takeGold(2);
                        person.tell("");
                    }
                }
                //enter room3 and then offer visitor chance to exit, or send back to room 1 via while loop
                person.tell("Approaching Room 3 contributed by ec22982.....");
                person.tell("");
                direction = room3.visit(person,direction);
                person.tell("");
                person.tell("Departing Room 3.....");
                person.tell("");
                
                //opportunity to leave after room 3
                person.tell("You have left room 3. To your left, you see an exit and to your right you see an unmarked door.");
                char[] choiceDoor = {'L', 'R', 'l', 'r'};
                char optionDoor = person.getChoice("Do you choose to go left or right? L/R?", choiceDoor);
                //if visitor chose to leave, then while loop breaks
                if (optionDoor=='L'|optionDoor=='l')
                {
                    person.tell("");
                    person.tell("Upon choosing the door on your left, you walk through it.");
                    person.tell("Hope you enjoyed your time here.");
                    exit = true;
                }
                //if visitor did not choose to leave, then the while loop continues and goes back to room 1
                else
                {
                    person.tell("Walking through the unmarked door, you find yourself walking down a loooooooooooooong hallway.");
                    person.tell("I wonder where you end up...");
                }
            }
        }
        //send the visitor on their way, and return direction
        person.tell("Now you have left the driveway of the Manor, safe travels Visitor...");
        person.tell("");
        person.tell("");

        return direction;
    }
}

package OOP.ec22518.MP.contributions;

class House_ec22764 implements Visitable {
    Room_ec22764 Room1;
    Room_ec221022 Room2;
    Room_ec22770 Room3;
    
    boolean Room1entered = false;
    boolean Room2entered = false;
    boolean Room3entered = false;
    
    public House_ec22764() {
        Room1 = new Room_ec22764();
        Room2 = new Room_ec221022();
        Room3 = new Room_ec22770();
    }
    
    public Direction visit(Visitor v, Direction d) 
    {
        boolean exit = false;
        
        char [] option = {'a', 'b', 'c'};
        char NextD;
        
        v.tell("The rain was pouring and thunder was rumbling so you run inside an old mysterious house. You scan the hallway for any activities ahead, but instead you see three doors around you.");
        char choice = v.getChoice("Do you a) explore the house b) exit the house?", option);
        
        
        while((choice == 'a' || choice =='b') && (exit=false))
        {
            if(String.valueOf(choice).contains("a"))
            {
                v.tell("You decide to explore the house, not knowing what's in store for you. Good luck.");
                    
                NextD = v.getChoice("You can either a) Go North b) Go East c) Go West", option);
                    
                if (String.valueOf(NextD).contains("a"))
                {
                    v.tell("You decide to go into the room ahead.");
                    d = Room2.visit(v, d);
                 }
                    
                else if (String.valueOf(NextD).contains("b"))
                {
                    v.tell("You decide to go into the room to the right.");
                    d = Room3.visit(v, d);
                    
                    v.tell("You see a door ahead of you and a door to your left.");
                    NextD = v.getChoice("You can either a) Go North b) Go West", option);
                    
                    if (String.valueOf(NextD).contains("a"))
                    {
                        v.tell("You open the door and you manage to exit the house!");
                        exit = true;
                        break;
                    }
                    
                    else if (String.valueOf(NextD).contains("b"))
                    {
                        v.tell("You enter a hallway and find 20 pieces of gold!");
                        v.giveGold(20);
                    }
                }
        
                else if (String.valueOf(NextD).contains("c"))
                {
                    v.tell("You decide to go into the room to the left.");
                    d = Room1.visit(v, d);
                    return Direction.TO_NORTH;
                }
                else
                {
                    v.tell("Select either a, b or c");
                    choice = choice;
                }
            }
            
            else if(String.valueOf(choice).contains("b"))
            {           
                v.tell("You leave the house and decide to explore another day.");
            }
            
            else
            {
                v.tell("Select either a or b");
                choice = choice;
            }
        
        }
    
        return d;
}
                    }

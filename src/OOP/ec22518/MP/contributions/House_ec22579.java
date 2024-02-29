package OOP.ec22518.MP.contributions;

class House_ec22579 implements Visitable {
    
    Room_ec22579 room1;
    Room_ec22557 room2;
    Room_ec22743 room3;
    Room_ec22766 room4;
    Room_ec22419 room5;
    Room_ec22441 room6;
    char [] choices1;
    char [] choices2;
    
    private int hallway_passed = 0;
    
    
    House_ec22579(){
        room1 = new Room_ec22579();
        room2 = new Room_ec22557();
        room3 = new Room_ec22743();
        room4 = new Room_ec22766();
        room5 = new Room_ec22419();
        room6 = new Room_ec22441();
        char [] choices1 = { '1', '2', '3' };
        char [] choices2 = { '1', '2',};
    }
    
    public void ground_floor (Visitor visitor,Direction direction)
    {
        boolean move_floor = false;
        Direction exit = direction;
        
        while(!move_floor)
        {
            visitor.tell("You are currently on the ground floor");
            char door = visitor.getChoice("Which door would you like to enter 1, 2 or 3?", choices1); 

            switch(door)
            {
                case '1':
                    exit = room1.visit(visitor, direction);
                    break;
                case '2':
                    exit = room2.visit(visitor, direction);
                    break;
                case '3':
                    exit = room3.visit(visitor, direction);
                    break;
            }
            
            if (exit == Direction.FROM_NORTH)
            {
                visitor.tell("As you exit, you see that you have arrived back at the same place you started.");
            }
            
            else
            {
                visitor.tell("As you exit, you can see a set of stairs and another hallway.");
                Character hall = visitor.getChoice("Are you going to go 1.Up the stairs 2.Through the hallway?", choices2);
        
                if (hall.equals('2'))
                {
                    visitor.tell("You walk through the hallway and find 5 pieces of gold on the table");
                    visitor.giveGold(5);
                    visitor.tell("The hallway eventually leads back to the three door you saw at the start.");
                    hallway_passed = hallway_passed +1;
                    
                    if (hallway_passed ==0)
                    {
                        visitor.tell("You walk through the hallway and find 5 pieces of gold on the table");
                        visitor.giveGold(5);
                        visitor.tell("The hallway eventually leads back to the three door you saw at the start.");
                        hallway_passed = hallway_passed +1;
                        break;
                    }
                    else if ((hallway_passed>0)&&(hallway_passed<5))
                    {
                        visitor.tell("You walk through the hallway and find 5 pieces of gold again in the same place as last time");
                        visitor.giveGold(5);
                        visitor.tell("The hallway eventually leads back to the three door you saw at the start.");
                        hallway_passed = hallway_passed +1;
                    }
                    
                    else
                    {
                        visitor.tell("You walk through the hallway but there is no gold this time");
                        visitor.tell("As you walk past the table, a hand jumps out and grabs "+visitor.takeGold(2)+" pieces of gold.");
                        visitor.tell("The hallway eventually leads back to the three door you saw at the start.");
                    }
                    
                }

                else
                {
                    visitor.tell("You walk up the stairs to find a similar hallway, with three doors");
                     first_floor(visitor,direction);
                    move_floor = true;
                }
            }
        }
        return;
        
    }
    
    public void first_floor(Visitor visitor, Direction direction)
    {
        boolean leave = false;
        Direction exit = direction;
        
        while (!leave)
        {
            visitor.tell("You are currently on the first floor");
            char door = visitor.getChoice("Which door would you like to enter 1,2 or 3?", choices1);
                    
            switch(door)
            {
                case '1':
                    exit = room3.visit(visitor, direction);
                    break;
                case '2':
                    exit = room4.visit(visitor, direction);
                    break;
                case '3':
                    exit = room5.visit(visitor, direction);
                    break;
            }
                
            if (exit == Direction.FROM_WEST)
            {
                visitor.tell("You arrive at a hallway with a pitch black hole in the wall and a sign above saying enter.");
                visitor.tell("To your left there is also a set of stair leading you back to the ground floor.");
                char option = visitor.getChoice ("What would you like to do 1.Go through the hole 2.Go back down.", choices2);
                        
                switch(option)
                {
                    case '1':
                        visitor.tell("You blindly enter the hole, not knowing where to go");
                        visitor.tell("When you suddenly drop and start sliding down");
                        visitor.tell("You see a light appearing at your feet and then land in a mud pit");
                        visitor.tell("Turning around, you see that you went through a slide that has led you out the house");
                        break;
                                
                    case '2':
                        ground_floor(visitor, direction);
                        break;
                }
                leave = true;
            }
                
            else if (exit == Direction.FROM_EAST) 
            {
                visitor.tell("As you exit, you see that you have somehow arrived back to the ground floor. ");
                ground_floor(visitor, direction);
                leave = true;
            }
                    
            else
            {
                visitor.tell("You leave the room and find yourself back at the same hallway as before");
            }
        }
        return;
    }
                                  
    
    public Direction visit(Visitor visitor, Direction direction) {
            
        visitor.tell("You have entered house ec22579");
        visitor.tell("As you enter the door slams behind you and the handle falls off");
        visitor.tell("You walk further in and see a hallway with three doors.");
        ground_floor(visitor,direction);
        visitor.tell("Hope you had a nice time!");
        visitor.tell("Goodbye.");
        
        return Direction.opposite(direction);
    }
    
}

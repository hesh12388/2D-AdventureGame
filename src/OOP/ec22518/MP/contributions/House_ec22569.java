package OOP.ec22518.MP.contributions;

public class House_ec22569 implements Visitable {
    Room first_room;
    Room second_room; 
    Room third_room; 
    Room fourth_room;
    Room fifth_room;    
    char[] two_options = {'y', 'n'};
    char[] three_options = {'a', 'b', 'c'};
    static final Item PLANT = new Item("Plant");
    static final Item SLEDGEHAMMER = new Item("SledgeHammer");
    static final Item BRICK = new Item("Brick");
    static final Item CHAINSAW = new Item("Chainsaw");
    boolean chose_hammer = false;
    boolean chose_book = false;
    boolean chose_plant = false;
    boolean chose_brick = false;
    boolean chose_chainsaw = false;
    boolean met_ghost = false;
    boolean escaped = false;

    // create array to store rooms
    Room[] house_rooms = new Room[4];

    
    public House_ec22569()
    {
        // assign each room to a variable
        first_room = new Room_ec22569(); // my room
        second_room = new Room_ec22476(); //farida's room
        third_room = new Room_ec22562(); //taylor's room
        fourth_room = new Room_ec22503(); // norah's room
        //assign variable to array
        house_rooms[0] = first_room;
        house_rooms[1] = second_room;
        house_rooms[2] = third_room;
        house_rooms[3] = fourth_room;
        
    }
        
    public Direction visit(Visitor visitor, Direction direction)
    {
        // introductory statements if this is the first time visiting the house
        visitor.tell("");
        visitor.tell("You have entered into the house through the front door.");
        visitor.tell("The large towering door slams behind you and locks.");
        visitor.tell("You need to visit a series of rooms to retrieve gold pieces and items.");
        visitor.tell("You also need to find a particular special item that will help you escape.");
        visitor.tell("But be careful! Depending on your choices, you can also lose any gold pieces you might have.");
        visitor.tell("Good luck...");
        visitor.tell("");
                     
        // while loop executed until escaped variable becomes true
        while (escaped == false)
        {
            visitor.tell("You walk into a room.");
            // call first room and assign direction returned to variable
            Direction first_direction = house_rooms[1].visit(visitor, direction);
            // if first room returns SOUTH or WEST direction then if statement executes
            if (first_direction == Direction.TO_SOUTH || first_direction == Direction.TO_WEST)         
            {
                // use direction returned to provide info about which way user is heading
                visitor.tell("You walk out of the room " + first_direction + ".");
                visitor.tell("You have entered into a narrow passageway that is stacked full of books.");
                visitor.tell("Curious to read one?");
                // give user choice to choose between two options
                char book_choice = visitor.getChoice("Enter only either (y) or (n)", two_options);
                // if user chooses yes
                if (book_choice == ('y'))
                {
                    // set variable to true
                    chose_book = true;
                    visitor.tell("You have chosen to look further at one of the books.");
                    visitor.tell("You decide to pick up one of the books to see what it is about.");
                    visitor.tell("As you attempt to open it, it turns to dust.");
                    visitor.tell("You leave a gold piece (if you have any) to compensate for your actions.");
                    // take gold from user
                    visitor.takeGold(1);
                    visitor.tell("You hurriedly walk down the passageway.");
                }
                else
                {
                    visitor.tell("You decide to continue walking down the passageway.");
                }
                
                visitor.tell("");
                visitor.tell("You walk into another room.");  
                visitor.tell("");
                // call another room and assign returned direction to variable
                Direction third_direction = house_rooms[3].visit(visitor, first_direction);
                visitor.tell("You walk out of the room " + third_direction + ".");
                visitor.tell("");
            } 
            //if first room does not return SOUTH or WEST direction 
            else
            {
                visitor.tell("");
                visitor.tell("You walk into another room.");
                visitor.tell("");
                // call another room and assign direction returned to variable
                Direction second_direction = house_rooms[2].visit(visitor, first_direction);
                // use direction returned to provide info about which way user is heading
                visitor.tell("You walk out of the room " + second_direction + ".");
                visitor.tell("");
                visitor.tell("You stumble into a large dining room that seems to be cluttered with a variety of dead plants that you have never seen before.");
                visitor.tell("Do you wish to collect a plant?");
                // give user choice between yes or no
                char plant_choice = visitor.getChoice("Enter only either (y) or (n)", two_options);
                if (plant_choice == ('y'))
                {
                    // if user has not chosen this option on a previous visit
                    if (chose_plant == false)
                    {
                        // give user plant and gold
                        visitor.giveItem(PLANT);
                        visitor.giveGold(1);
                        chose_plant = true;
                        visitor.tell("You have collected one of the plants and some gold.");
                    }
                    // user has chosen this option on a previous visit
                    else
                    {
                        visitor.tell("You seem to have collected the plant on a previous visit.");
                    }
                }
                // user chose not to collect item
                else
                {
                    visitor.tell("You have chosen to not collect one of the plants.");
                }
                visitor.tell("Whilst becoming encapsulated by the plants, a ghost approaches you and you notice at the last minute.");
                // give user choice to hear ghost out or not
                visitor.tell("Do you want to run away? (y) yes or (n) no");
                char ghost_choice = visitor.getChoice("Enter only either (y) or (n)", two_options);
                // if user chooses yes
                if (ghost_choice == ('y'))
                {
                    visitor.tell("You have chosen to run away.");
                    visitor.tell("You sprint towards one of the four doors mindlessly and continue running.");
                    visitor.tell("Whilst doing so, you drop some gold pieces, that you might have had, which the ghost quickly takes and flies away.");
                    // take gold from user
                    visitor.takeGold(2);
                    visitor.tell("As you ran, you did not pay attention to which direction you were in.");
                }
                // assume user has chosen no
                else 
                {
                    // if user has not met ghost before
                    if (met_ghost == false)
                    {
                        // set met_ghost to true
                        met_ghost = true;
                        visitor.tell("You watch nervously as the ghost approaches you.");
                        visitor.tell("The ghost looks upset and you begin to feel sympathy towards it.");
                        visitor.tell("You try to ask what's wrong but then the ghost suddenly transforms into an evil red colour and robs you of some of your gold, that you might have had.");
                        // take gold from user
                        visitor.takeGold(3);
                        visitor.tell("You try to chase after it but the scent of the plants seems to have confused you.");
                        visitor.tell("You struggle to remember which direction you were heading towards.");
                    }
                    // if user has met ghost before
                    else
                    {
                        visitor.tell("You anixously wait to see what the ghost might do.");
                        visitor.tell("The ghost glides past you dropping some of his own gold.");
                        visitor.tell("You pick up his dropped gold.");
                        // give user some gold
                        visitor.giveGold(2);
                    }
                }
                
                visitor.tell("");
                visitor.tell("You decide to enter the next room at a random direction.");
                
                // based on what the variable stores, assign a different direction
                if (second_direction == Direction.TO_NORTH)
                {
                    second_direction = Direction.TO_WEST;
                }
                if (second_direction == Direction.TO_SOUTH)
                {
                    second_direction = Direction.TO_EAST;
                }
                if (second_direction == Direction.TO_WEST)
                {
                    second_direction = Direction.TO_SOUTH;
                }
                if (second_direction == Direction.TO_EAST)
                {
                    second_direction = Direction.TO_NORTH;
                }
                
                // call next room and assign returned direction to a variable
                Direction fourth_direction = house_rooms[0].visit(visitor, second_direction);
                // use direction returned to provide info about which way user is heading
                visitor.tell("You walk out of the room " + fourth_direction + ".");
                visitor.tell("");                
            }
            visitor.tell("");                        
            visitor.tell("Having exited the previous room, the magical house leads you into a small room.");
            visitor.tell("Some items stand out to you, such as: a brick, sledgehammer and chainsaw.");
            visitor.tell("You feel lethargic and so, only wish to pick one of them up as they're all heavy.");
            visitor.tell("");
            // give user choice out of three options
            visitor.tell("Which one do you choose? (a) brick (b) sledgehammer (c) chainsaw");
            char final_item = visitor.getChoice("Enter only either (a), (b) or (c)", three_options);
            // if user chooses first option
            if (final_item == ('a'))
            {
                // if user has not chosen this item before
                if (chose_brick == false)
                {
                    visitor.giveItem(BRICK);
                    chose_brick = true;
                    visitor.tell("You have collected a brick.");
                }
                // user has chosen this item before
                else
                {
                    visitor.tell("You have already chosen this item on a previous visit so you decide not to collect it again.");
                }
            }
            // if user chooses second option
            else if (final_item == ('b'))
            {
                // if user has not chosen this item before
                if (chose_hammer == false)
                {
                    visitor.giveItem(SLEDGEHAMMER);
                    chose_brick = true;
                    visitor.tell("You have collected a sledge hammer.");
                }
                // user has chosen this item before
                else
                {
                    visitor.tell("You have already chosen this item on a previous visit so you decide not to collect it again.");
                }
            }
            // assume user chose third option
            else
            {
                // if user has not chosen this item before
                if (chose_chainsaw == false)
                {
                    visitor.giveItem(CHAINSAW);
                    chose_brick = true;
                    visitor.tell("You have collected a chainsaw.");
                }
                // // user has chosen this item before
                else
                {
                    visitor.tell("You have already chosen this item on a previous visit so you decide not to collect it again.");
                } 
            }
            
            visitor.tell("You walk out of the small room and find yourself at the entrance point again.");
            // if user has previously met the ghost
            if (met_ghost == true)
            {
                visitor.tell("The same ghost from earlier approaches you.");
                visitor.tell("You hold on tightly to your gold pieces.");
            }
            // if user has not previously met the ghost
            else
            {
                visitor.tell("You notice a ghost approach you.");
            }
            visitor.tell("");
            visitor.tell("The ghost explains that you only have one chance to attempt to escape otherwise you will need to go back to visiting the rooms.");
            visitor.tell("The ghost gives a hint that only one of the items you have just collected will assist you.");
            visitor.tell("You check your pockets for any items you might have that could help you break the door.");
            
            // if user has both a PLANT and BRICK item
            if (visitor.hasEqualItem(PLANT) && visitor.hasEqualItem(BRICK))
            {
                visitor.tell("The items that stand out most to the ghost which you have are the single BRICK and PLANT.");
                visitor.tell("The ghost uses a form of magic to entangle these together to create some form of an explosive.");
                visitor.tell("You hurl it at the door and it breaks - allowing you to escape!");
                // set escaped to true - breaking while loop
                escaped = true;
            }
            // if user has a CHAINSAW item
            else if (visitor.hasEqualItem(CHAINSAW))
            {
                visitor.tell("The ghost explains that you can use your CHAINSAW item to cut the door down.");
                visitor.tell("You begin to do so but the metal pieces injure you and whilst you stumble, you drop some gold pieces you might have had.");
                // take gold from user
                visitor.takeGold(2);
                visitor.tell("However, the door breaks and you are able to escape!");
                // set escaped to true - breaking while loop
                escaped = true;
            }
            // assume user has none of these items
            else
            {
                visitor.tell("You don't seem to have any particularly useful items that could help you escape.");
                visitor.tell("You are forced to visit the house again.");
                visitor.tell("");
            }
        }
        
        visitor.tell("Congratulations - you have finally escaped!");
        
        return direction; // END
    }
}
package OOP.ec22518.MP.contributions;

class House_ec221013 implements Visitable {
  
    Room ec221013;
    Room ec221014;
    Room ec221012;
    // At least two rooms that I did not write myself
    House_ec221013()
    {
    ec221013 = new Room_ec221013();
    // Entrance and exit is the same door.

    // Picking username neighbours
    ec221012 = new Room_ec221012();
    // Entrance and exit is same door

    ec221014 = new Room_ec221014();
    // Sometimes TO.NORTH, TO.SOUTH, or direction arrived from
    /* LAYOUT
            ec221013
            |
    <--------->ec221014<---------->
            |
            ec221012
        
    */
    }

    public Direction visit(Visitor visitor, Direction ArrivedFrom)
    {
        visitor.tell("Welcome to the House of the Outsider. There are two entrances, on the west and on the east");
        Direction d = ArrivedFrom;
        char[] we = {'w', 'e'};
        char[] ls = {'l','s'};
        char enterFrom = visitor.getChoice("Do you want to enter the house from the west or east? w/e", we);

        if (enterFrom == 'w') { d = Direction.FROM_WEST; }
        else if (enterFrom == 'e') { d = Direction.FROM_EAST; }

        Boolean leave = false;
        while(leave == false)
        {
            // Start at ec221014, get returned direction
            Direction ec221014d = ec221014.visit(visitor, d);
            if (ec221014d == Direction.TO_NORTH) { ec221013.visit(visitor, ec221014d); }
            else if (ec221014d == Direction.TO_SOUTH) { ec221012.visit(visitor, ec221014d); }
            else
            {
                visitor.tell("You are back outside.");
                char leaveChoice = visitor.getChoice("Do you want to leave or stay? l/s", ls);
                if (leaveChoice == 'l') { leave = true; }
                else if (leaveChoice == 's') { continue; }
            }
        }
      
        visitor.tell("You chose to leave the house the way you came");
        return ArrivedFrom;
    }
}

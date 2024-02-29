package OOP.ec22518.MP.contributions;

class House_ec22798 implements Visitable {
    private Room roomNorth;
    private Room roomEast;
    private Room roomWest;

    House_ec22798() 
    {
        roomNorth = new Room_ec22798();
        roomEast = new Room_ec22943();
        roomWest = new Room_ec22943();
    }

    public Direction visit(Visitor visitor, Direction direction)
    {
        boolean present = true;
        visitor.tell("Welcome to House EC22798");
        while (present)
        {
            visitor.tell("You are in the hallway. There are three doors to the north, east and west. You also see a piece of paper with a messahe pinned to the wall.");
            char[] hallwayChoices = {'N', 'E', 'W', 'P', 'L'};
            char hallway = visitor.getChoice("Would you like to enter the (N)orth, (E)ast or (W)est room or inspect the (P)aper or (L)eave", hallwayChoices);
            Boolean paperInspected = false;
            Boolean eastVisited = false;
            if (hallway == 'P')
            {
                visitor.tell("You inspect the piece of paper and find it reads the following: To survive this house you must follow the sun.");
                paperInspected = true;
            }
            if (hallway == 'E')
            {
                eastVisited = true;
                direction = roomEast.visit(visitor, direction);
            }
            else if (hallway == 'W')
            {
                if (eastVisited)
                {
                    direction = roomWest.visit(visitor, direction);
                }
                else if (!eastVisited)
                {
                    visitor.tell("You opened the door but was greeted by a devious monster called Eshan. He threw you out the house.");
                    present = false;
                }
                else
                {
                    visitor.tell("An error occurred");
                }
            }
            else if (hallway == 'N')
            {
                direction = roomNorth.visit(visitor, direction);
            }
            else if (hallway == 'L')
            {
                visitor.tell("You ran away from the house.");
                present = false;
            }
            else
            {
                visitor.tell("An error occurred. Try Again");
            } 
        }
        return direction;
    }
}

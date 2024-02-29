package OOP.ec22518.MP.contributions;

public class House_ec22422 implements Visitable {
    
    Room leftRoom;
    Room rightRoom;
    boolean doneVisit;

    House_ec22422()
    {
        leftRoom = new Room_ec22446();
        rightRoom = new Room_ec22569();
        doneVisit =  false;
    }

    static void garden(Visitor theVisitor, Direction left)
    {
        boolean inGarden = true;
        theVisitor.tell("You have entered the gardern from the " + left);

        while (inGarden == true)
        {
            theVisitor.tell("You are in the garden");
            theVisitor.tell("1. Go back into the room");
            theVisitor.tell("2. Look around");
            theVisitor.tell("3. Enter the house from behind");

            char[] whatToDo = {'1','2','3'};
            char toDo = theVisitor.getChoice("What will you do?", whatToDo);

            if (toDo == '1')
            {
                theVisitor.tell("The room is locked from the outside");
            }
            else if (toDo == '2')
            {
                theVisitor.tell("You find nothing");                
            }
            else if (toDo == '3')
            {
                theVisitor.tell("You enter the house from the back"); 
                inGarden = false;               
            }
            else
            {
                theVisitor.tell("What are you thinking");
            }
        }   
    }

    public Direction visit(Visitor theVisitor, Direction theDirection)
    {
        theVisitor.tell("It seems you've had a fall");
        theVisitor.tell("You have just woken up and you find yourself in a house mysterious house");
        
        //when the visitor is in the house they will always find a way back to the middle

        while (doneVisit == false)
        {
            theVisitor.tell("You are in the middle of the house");
            theVisitor.tell("1. There is a room on the left");
            theVisitor.tell("2. There is an exit ahead");
            theVisitor.tell("3. There is a room on the right");

            char[] whereToGo = {'1','2','3'};
            char toGo = theVisitor.getChoice("Where will you go", whereToGo);

            if (toGo == '1')
            {
                Direction enter = Direction.FROM_WEST;
                Direction left = leftRoom.visit(theVisitor, enter);
                if (left != (Direction.TO_WEST))
                {
                    garden(theVisitor, left);
                }
            }
            else if (toGo == '2')
            {
                doneVisit = true;
            }
            else if (toGo == '3')
            {
                Direction enter = Direction.FROM_EAST;
                Direction left = rightRoom.visit(theVisitor, enter);
                if (left != (Direction.TO_EAST))
                {
                    garden(theVisitor, left);
                }
            }
            else
            {
                theVisitor.tell("What are you thinking");
            }
        }

        theVisitor.tell("You leave the house in the direction you came from");
        return theDirection;
    }
}

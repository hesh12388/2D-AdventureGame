package OOP.ec22518.MP.contributions;

class House_ec22899 implements Visitable {

    Room room1;
    Room room2;
    Room room3;
    Boolean Stoked = false;


    House_ec22899()
    {
      room1 = new Room_ec22535();
      room2 = new Room_ec221085();
      room3 = new Room_ec22899();
    }

    @Override

    public Direction visit(Visitor visitor, Direction directionArrived)
    {   
        visitor.tell("You entered the house");
        visitor.tell("Do you a)Explore b)Stoke the fire c)Jump");
        char choice1 = visitor.getChoice("Do you a)Explore b)Stoke the fire c)Jump", new char[]{'a', 'b', 'c'});

        while(choice1 != 'a')
        {
            if (choice1 == 'b' && Stoked == false)
            {
                visitor.tell("The fire is now stoked");
                Stoked = true;
            }

            else if (choice1 == 'b' && Stoked == true)
            {
                visitor.tell("The fire is already stoked");
            }
            else if (choice1 == 'c')
            {
                visitor.tell("You have jumped");
            }
            choice1 = visitor.getChoice("Do you a)Explore b)Stoke the fire c)Jump", new char[]{'a', 'b', 'c'});
        }
        
        
        
        
        visitor.tell("You find a hallway behind a cupboard");
        visitor.tell("There are three doors in front of you");
        Direction newDirection = directionArrived;

        char choice = visitor.getChoice("Which would you like to enter? a)Room 1 (left side), b)Room 2 (right side), c)Room 3 (in front),  d)Exit", new char[]{'a', 'b', 'c'});

        while(choice != 'd')
        {

            if (choice == 'a')
            {
                visitor.tell("You have entered Room 1 on your left");
                newDirection = room1.visit(visitor,directionArrived.turnLeft(directionArrived));
                visitor.tell("You have exited Room1 and are now in the hallway");
            }

            else if (choice == 'b')
            {
                visitor.tell("You have entered Room 2 on you right");
                newDirection = room2.visit(visitor,directionArrived.turnRight(directionArrived));
                visitor.tell("You have exited Room2 and are now in the hallway");

            }

            else if (choice == 'c')
            {
                visitor.tell("You have entered Room 3 in front of you");
                newDirection = room3.visit(visitor,directionArrived.turnRight(directionArrived));
                visitor.tell("You have exited Room3 and are now in the hallway");

            }

            choice = visitor.getChoice("Which would you like to enter? a)Room 1 (left side), b)Room 2 (right side), c)Room 3 (in front),  d)Exit", new char[]{'a', 'b', 'c'});


            
        }

        return newDirection;

    }
}
//

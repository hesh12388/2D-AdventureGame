package OOP.ec22518.MP.contributions;

class House_ec22779 implements Visitable {
    Room_ec22779 RoomA;
    Room_ec22900 RoomB;
    Room_ec22632 RoomC;
    
    public House_ec22779()
    {
        RoomA = new Room_ec22779();
        RoomB = new Room_ec22900();
        RoomC = new Room_ec22632();
    }
    
    
    public Direction visit(Visitor v, Direction d)
    {
        v.tell("Welcome to House ec22779!");
        boolean Cont = true;
        Direction dir;
        while(Cont)
        {
            v.tell("You have 3 options: go left, go right, or go forwards?");
            char[] Choices = {'L', 'R', 'F'};
            char RandChoice = v.getChoice("Select one of the choices available. ", Choices);
            if(RandChoice == 'L')
            {
                v.tell("You have entered the room on the left.");
                dir = RoomA.visit(v, d);
            }
            else if(RandChoice == 'R')
            {
                v.tell("You have entered the room on the right");
                dir = RoomB.visit(v, d);
            }
            else if(RandChoice == 'F')
            {
                v.tell("You have gone forward and are about to leave the house.");
                dir = RoomC.visit(v, d);
                Cont = false;
            }
            else
            {
                v.tell("Sorry, there has been an error, please try again.");
            }
        }
        return d;
    }
}
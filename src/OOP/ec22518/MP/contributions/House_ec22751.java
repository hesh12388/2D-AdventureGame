package OOP.ec22518.MP.contributions;

class House_ec22751 implements Visitable {
    private Room[] rooms;

    House_ec22751(){
        rooms = new Room[3];
        rooms[0]= new Room_ec22984();
        rooms[1] = new Room_ec22982();
        rooms[2] = new Room_ec22418();
        
    }
    //rooms layout

    //             
    //               
    //        E          
    // rooom1 - -  room2 
    //  | N         |S
    // entrance    room3

public Direction visit(Visitor v,Direction d)
{
    char[] choices = new char[]{'N','E','S','W'};
    boolean Loop = true;
    int now = 1;
    v.tell("Welcome to THE best haunted house in town!");
    char choice2 = 'h';
    
    v.tell(" There is a room with one entry point choose wisely or you will be kicked out.");
    char choice = v.getChoice("options (N) (E) (S) (W)", choices);
    // if (choice == N)
    while(Loop)
    {
        if (choice == 'N')  // if the entrance to 
        {
            
            v.tell("you entered room 1 nice");
            Direction next = rooms[0].visit(v,d); //holds direction after leaving room 1 which is opposite
                if (next == Direction.TO_EAST)
            {
                v.tell("Welcome to the second Room");
                next = rooms[1].visit(v,d); //returns opposite directiom
                choice2 = v.getChoice("Which direction do you want to go now? options (N) (E) (S) (W)", choices);
                now = 0;
            }
            else if(now == 1)
            {
                v.tell("You made it out from room 1 but you are not welcome to room 2 because it's the wrong way!");
                Loop = false;
            }
                    if (choice2 == 'S' && now == 0)
                {
                v.tell("Welcome to the third room");
                next = rooms[2].visit(v,d);
                Loop = false;
                }
                else if ( choice2 == 'E' || choice2 == 'W' )
                {
                v.tell("You exited the room. Sorry to let you go. BYE!");
                Loop = false;
                }
                else if (choice2 == 'N')
                {
                v.tell("You left room 3 got lost and went back to room 1( You have to start over again ughh )");
                }
        }
        else
        {
            v.tell("I'm sorry but thats the worng choice bye bye now.");
            Loop = false;
        }
        
    }

    v.tell("You made it out of the house! Congratulations?");
    return d;
}
}

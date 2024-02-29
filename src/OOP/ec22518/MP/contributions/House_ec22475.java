package OOP.ec22518.MP.contributions;

class House_ec22475 implements Visitable {
    
    Room A;
    Room B;
    Room C;
    
    House_ec22475(){
        A = new Room_ec22475();
        B = new Room_ec22468();
        C = new Room_ec22467();
    }
    
    public Direction visit(Visitor v, Direction d){
        char Q;
        char Q1;
        char [] choices = {'a','b'};
        char [] choices2 = {'a','b','c','d'};
        String question;
        String question1;
        Direction exit = Direction.TO_WEST;
        
        v.tell("You are currently in a dark room with 4 different directions to go....");
        v.tell("Would you like to continue to explore the house or leave?");
        question = "Enter a. To continue exploring... b. To leave the house";
        question1 = "Enter a. To go North b. To go South c. To go East d. To go West";
        
        Q = v.getChoice(question,choices);
        
        while (Q != 'b')
        {
            Q1 = v.getChoice(question1,choices2);
            
            if (Q1 == 'a')
            {
                exit = A.visit(v,d);
                
                v.tell("Was the room interesting?");
                v.tell("Returning to original room........");
            }
            else if (Q1 == 'b')
            {
                exit = B.visit(v,d);
                
                v.tell("Was the room interesting?");
                v.tell("Returning to original room.......");
            }
            else if (Q1 == 'c')
            {
                exit = C.visit(v,d);
                
                v.tell("Was the room interesting?");
                v.tell("Returning to original room.......");
            }
            else if (Q1 == 'd')
            {
                exit = Direction.TO_SOUTH;
                
                v.tell("Looks like this is a deadend");
                v.tell("Returning to original room......");
            }
            
            v.tell("Would you like to continue to explore the house or leave?");
            Q = v.getChoice(question,choices);
        }
        
        v.tell("Hope the house was interesting! See you next time!");
        
        return exit;
    }
}

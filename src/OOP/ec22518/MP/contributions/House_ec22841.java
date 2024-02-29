package OOP.ec22518.MP.contributions;

class House_ec22841 implements Visitable {
    Room r1;
    Room r2;
    
    House_ec22841()
    {
        this.r1 = new Room_ec22841();
        this.r2 = new Room_ec22890();
    }
    
    public Direction visit(Visitor visitor, Direction direction)
    {

        visitor.tell("Welcome to my house.");
        visitor.tell("There are 2 rooms in my house.");
        visitor.tell("Please choose either Room 1 (1) or Room 2 (2):");
        char options[] = {'a', 'b', 'c'};
        boolean room1 = false;
        boolean room2 = false;
        while(!room1 || !room2)
        {
            char choice = visitor.getChoice("(a) Go to Room 1, (b) Go to Room 2, or (c) to leave the house", options);
            
            if(choice == 'a' && !room1)
            {
                direction = r1.visit(visitor, direction);
                room1 = true;
            }
            else if(choice == 'b' && !room2)
            {
                direction = r2.visit(visitor, direction);
                room2 = true;
            }
            else if(choice == 'c')
            {
                break;
            }
            
            
        }
        visitor.tell("Thank you for visiting my house. Goodbye.");
        return direction;
    }
    
}
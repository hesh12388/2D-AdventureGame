package OOP.ec22518.MP.contributions;// import libraries

// My House Class
class House_ec22557 implements Visitable
{
    private boolean foundGold;
    // Rooms in house belong to Me, Aarti, Nikitha and Rucha
    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    
    // constructor for my house creates instances of other room classes and assigns variables
    public House_ec22557 ()
    {
        foundGold = false;
        room1 = new Room_ec22557();
        room2 = new Room_ec22419();
        room3 = new Room_ec22766();
        room4 = new Room_ec22743();
    }
    
    // visitor method overrides A4 visitor
    public Direction visit(Visitor v, Direction d) 
    {
        v.tell("Welcome to my House. Hope you breeze through my house without a scare in the world!");
        
        char[] options = {'1', '2'};
        char[] decision = {'a', 'b'};
        
        char nextDirection;
        
        int choice = v.getChoice("You can either (1) Explore my house or (2) Exit my house", options);
 
        // there are 2 ways to exit the House, either if you chose to exit by selecting 2 or if you have found all the gold and foundGold = true in the South.
        while (choice != 2 || foundGold == false) 
        {  
            if (d == Direction.FROM_NORTH) 
            {
                d = room1.visit(v, d);
                choice = v.getChoice("You can either (1) Explore my house or (2) Exit my house", options);
            } 

            else if (d == Direction.FROM_EAST) 
            {
                d = room2.visit(v, d);
                nextDirection = v.getChoice("You can either (a) Go North (b) Go South", decision);
                
                if (nextDirection == 'a')
                {
                    v.tell("You chose to go North");
                    d = room1.visit(v, d);
                    choice = v.getChoice("You can either (1) Continue exploring my house or (2) Exit my house", options);
                }
                else if (nextDirection == 'b')
                {
                    v.tell("You chose to go South");
                    d = room3.visit(v, d);
                    foundGold = true;
                }
            } 

            else if (d == Direction.FROM_SOUTH) 
            {
                d = room3.visit(v, d);
                foundGold = true;
            } 

            else if (d == Direction.FROM_WEST) 
            {
                d = room4.visit(v, d);
                nextDirection = v.getChoice("You can either (1) Go North (2) Go South", decision);
                
                if (nextDirection == 'a')
                {
                    v.tell("You chose to go North");
                    d = room1.visit(v, d);
                    choice = v.getChoice("You can either (1) Continue exploring my house or (2) Exit my house", options);
                }
                else if (nextDirection == 'b')
                {
                    v.tell("You chose to go South");
                    d = room3.visit(v, d);
                    foundGold = true;
                }
            }
        }
        
        v.tell("Thank you for visiting my house. Do come again ;)");
        
        return d;
    }
}

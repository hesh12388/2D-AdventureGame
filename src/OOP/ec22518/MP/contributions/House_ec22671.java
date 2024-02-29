package OOP.ec22518.MP.contributions;

class House_ec22671 implements Visitable {
    
    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    
    
    public House_ec22671()
    {
        room1 = new Room_ec22671(); // north
        room2 = new Room_ec211030(); //glass shop - east
        room3 = new Room_ec21403(); // mysterious room - south
        room4 = new Room_ec221006(); // Shakespeare room - west
    }
    
    public Direction visit(Visitor v, Direction d)
    {
        v.tell("Welcome to my penthouse!");
        v.tell("This is the main hall of my house. Would you like a tour?");
        String des = "Which room would you like to see? (you can also exit my house if you want to) choose: \n n) north \n e)east \n s) south \n w) west n x) exit";
        char [] opt = {'n', 'e', 's', 'w', 'x'};
        
        
        char answer = v.getChoice(des, opt);
        
        while(answer != 'x')
        {
            
            if (answer == 'n')
            {
                v.tell("you have entered my luxurious room. My favourite!");
                d = room1.visit(v, d);
                
                answer = v.getChoice(des, opt);
            }
            
            else if (answer == 'e')
            {
                v.tell("This part of the pent hous is actually a glass shop");
                v.tell("A bit strange but who wants to be ordinary, right?");
                v.tell("Want to buy something? I'll give you a special discount.");
                
                d = room2.visit(v, d);
                
                answer = v.getChoice(des, opt);
            }
            
            else if (answer == 's')
            {
                v.tell("This is a mysterious room. Try to survive and good luck.");
                
                d = room3.visit(v, d);
                
                answer = v.getChoice(des, opt);
            }
            
            
            else if (answer == 'w')
            {
                v.tell("This room is probably my least favourite but I liked enough to have it in my house.");
                v.tell("Anyway, this is a Shakespeare inspired room. Hope you are interested in poetry unlike me.");
                
                d = room4.visit(v, d);
                
                answer = v.getChoice(des, opt);
            }
            
            else
            {
                v.tell("There is no such a room in my house. Want to try again?");
                
                answer = v.getChoice(des, opt);
            }
        }
        
         return d; 
    }
}

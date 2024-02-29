package OOP.ec22518.MP.contributions;

class House_ec22840 implements Visitable {
    
    Room r1;
    Room r2;
    Room r3;
    boolean fvisit;
    
    public House_ec22840(){
        r1 = new Room_ec22840();
        r2 = new Room_ec22519();
        r3 = new Room_ec22449();
        fvisit = false;
        
    }
    
    static void park(Visitor avisitor, Direction adirection)
    {
        boolean inpark = true;
        
        while (inpark == true) {
            avisitor.tell("You are in the park");
            avisitor.tell("Where do you want to go?");
            avisitor.tell("1. Go to another room");
            avisitor.tell("2. Go back the the room you come from");
            avisitor.tell("3. Look around");
            
            char[] choice ={'1','2','3'};
            char choose = avisitor.getChoice("What's your choice?",choice);
            if (choose == 1)
            {
                avisitor.tell("Haha it is not allowed, you can only go back to the room you come from");
            }
            else if (choose == 2) {
                
                avisitor.tell("You go back to the room la, is the Park fun?");
            }
            else if (choose == 3) {
                avisitor.tell("Have fun, but you can't escape yet");
            }
            else {
                avisitor.tell("You must make a choice...");
                avisitor.tell("You don't want a penalty right?");
            }
            
        }
    }
    
    public Direction visit (Visitor avisitor,Direction d){
        avisitor.tell("You have enter My mysterious House, Welcome");
        avisitor.tell("Please choose where do you want to go.");

        
        while(fvisit==false){
            
            avisitor.tell("1. Go to a room");
            avisitor.tell("2. Go to the garden");
            avisitor.tell("3. walk around");
            avisitor.tell("4. exit :)");
            
            char[] choice2 ={'1','2','3','4'};
            char choose2 = avisitor.getChoice("What's your choice?",choice2);
            
            if(choose2 == 1)
            {
                avisitor.tell("You are visiting Room 1");
                Direction e = Direction.FROM_SOUTH;
                Direction l = r1.visit(avisitor,d);
                avisitor.tell("Do you want to visit another room? (1/2)");
                
                char[] choice3 ={1,2};
                char ans = avisitor.getChoice("1 = yes, 2 = no ",choice3);
                if (ans == 1) {
                    l = r2.visit(avisitor,d);
                }
                else
                {
                    fvisit = true;
                }
            }
            else if (choose2 == 2) {
                park(avisitor,d);
            }
            else if (choose2 == 3) {
                
                avisitor.tell("You accidentally run into room3");
                Direction e = Direction.FROM_WEST;
                Direction l = r3.visit(avisitor,d);
            }
            else if (choose2 ==4) {
                fvisit = true;
            }
            else
            {
                avisitor.tell("You will be trapped in the house forever hahahahahaha!");
            }
        }
        
        avisitor.tell("You are leaving the House");
        return d;
    }
}

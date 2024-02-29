package OOP.ec22518.MP.contributions;

class House_ec22413 implements Visitable {
    
    
    public static void main(String[]args) {
        House_ec22413 h = new House_ec22413();
        Visitor v = new IOVisitor(System.out,System.in);
    
        Direction d = h.visit(v, Direction.FROM_SOUTH);
    }
    
    private Room Nr;
    private Room Wr;
    private Room Er;

    
    
    public House_ec22413()
    {
        Nr=new Room_ec22413(); // Casino
        Wr=new Room_ec221014();// Matrix room
        Er=new Room_ec22880(); // treasure room
    }
    
    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom){
        
        boolean stop=false;
        
        char[] choices= {'n','e','s','w'};  //array of choices
        
        char choice= ' ';
        
        visitor.tell("Welcome to the Crusty Crab Condo.\n You just to the elevator to the top floor and are in the center of the room.\n Around you are 4 rooms north,east,south and west.\n Please choose one to go into and take your time to explore everything.");
        
        choice= visitor.getChoice("Which room would you like to explore?\n North room (n)\nWest room(w)\nEast room (e)\nSouth Basement (s)",choices); // gets choice from user
        
        
        while (stop==false){
            
            if (choice =='n'){
                
                visitor.tell("Aah my casino, just remember the house always wins");
                
                choice=converter(choices,Nr.visit(visitor, directionVistorArrivesFrom));
            }
            
            if (choice =='w'){
                
                choice=converter(choices,Wr.visit(visitor, directionVistorArrivesFrom));
                
            }
            
            if (choice =='e'){
                
                choice=converter(choices,Er.visit(visitor, directionVistorArrivesFrom));
            }
            
            if (choice=='s'){
                
                visitor.tell("You find yourself in the basement");
                
                visitor.giveGold(5);
                
                visitor.tell("You find an open window, with a broken ladder underneath.");
                
                    char [] answers= {'y','n'};
                    visitor.tell("If only you had a rope,");
                    visitor.tell("Ill sell you one for 5.99 Gold");
                    char answer= visitor.getChoice("Do you accept? (y/n)",answers);
                    
                    if (answer=='y'){
                        
                        visitor.takeGold(6);
                        visitor.tell("You can finally leave, hope you enjoyed your stay.");
                        stop=true;
                    }
                    
                    else { 
                        
                        visitor.tell("Come back when you have some more gold");
                        choice=converter(choices,Direction.TO_NORTH);
                    }
                }
            }
        return directionVistorArrivesFrom; 
    }
    
    public char converter (char[] C, Direction D){
        
        String[] Directions ={"heading North","heading East","heading South","heading West"};
        int value=-1;
        for (int i=0; i<4;i++){
            
            if (D.toString().equals(Directions[i])){
                value=i;
                break;
            }
        }
        
        return C[value];
    }
}

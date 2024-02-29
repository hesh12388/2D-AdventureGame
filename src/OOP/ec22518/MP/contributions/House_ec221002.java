package OOP.ec22518.MP.contributions;

class House_ec221002 implements Visitable {
    
    Room Room_ec221002;
    Room Room_ec22940;
    Room Room_ec22906;
    
    
    House_ec221002(){
        Room_ec221002 = new Room_ec221002();
        Room_ec22940 = new Room_ec22940();
        Room_ec22906 = new Room_ec22906();
       
        
        
    }
    
    int gold;
    String key = "axe";
    
      public Direction visit(Visitor visitor, Direction d){
        Direction retdir = d;
          
        visitor.tell("Welcome to the walter house");
        visitor.tell("you will encounter danger in all rooms beacuse i am the danger");
        visitor.tell("You only have a chemical mixture to burn down the lock or you can use your gold items");
        char[] options = { '1', '2', '3', '4' };
        char choice = visitor.getChoice("what would you do to survive , go to room 1 or room 2 or room 3 or die right here", options);
        
        if(choice == 1){
            visitor.tell("you are in room 22940 of the house ");
            
             retdir = Room_ec22940.visit(visitor, d);
        
        }
        else if(choice == 2){
            visitor.tell("you are in room 221002 of the house ");
            retdir = Room_ec221002.visit(visitor, d);
            
        }
        else if(choice == 3){
            visitor.tell("you are in room 22906 of the house ");
            retdir = Room_ec22906.visit(visitor, d);
            
        }
           else if(choice == 4){
            visitor.tell("JESSE HAS GONE TO MARKET TO GET SOME STUFF FOR YOU ");
            
        }
        
          
          
          
          
          
          return retdir;
      }
    
}

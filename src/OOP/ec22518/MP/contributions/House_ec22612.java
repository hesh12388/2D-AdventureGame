package OOP.ec22518.MP.contributions;

import java.util.Random;
class House_ec22612 implements Visitable {
    
    
    private Room [][] rooms;
    
    
    House_ec22612(){
        
        Room Room1 = new Room_ec22612();
        Room Room2 = new Room_ec22986();
        Room Room3 = new Room_ec22518();
        Room Room4 = new Room_ec22981();
        
     
        rooms = new Room [] [] {{Room1, Room2}, {Room3, Room4}};
        
  
    }
    
    public Direction visit(Visitor v, Direction d) {
        
        boolean inhouse = true;
        int x = 0;
        int y = 0;
        
        
        while(inhouse){
            
            
            d = rooms[x][y].visit(v,d);
            
            runhallway(v);
            
            
            
            if(y == 0 && d == Direction.TO_SOUTH){
                inhouse = false;
            } 
            
            else if(x == 0 && d == Direction.TO_WEST){
                inhouse = false;
            }
            
            else if(y == 1 && d == Direction.TO_NORTH){
                inhouse = false;
            }
            
            else if(x == 1 && d == Direction.TO_EAST){
                
                inhouse = false;
            }
            
            else if (d == Direction.TO_NORTH){
                y += 1;
            }
            
            else if (d == Direction.TO_SOUTH){
                y -= 1;
            }
            
            else if (d == Direction.TO_EAST){
                x+= 1;
            }
            
            else{
                x-= 1;
            }
            
            
        }
        
        
        return d;
    }
    
    
    private void runhallway(Visitor v){
        int rdm = randomInt(4);
        
        if (rdm == 1){
            
            hallway1(v);
            
        }
        
        else if (rdm == 2){
            hallway2(v);
        }

        else if (rdm == 3){
            hallway3(v);
        }
        
    }
    
    private void hallway1(Visitor v){
    
        v.tell("As you walk through the hallway, you see a treasure chest!!");
        
        if(v.hasEqualItem(new Item("Key"))){
            char choice = v.getChoice("Do you want to use the key you have to open the chest(y/n)?",new char []{'y','n'});
            if (choice == 'y'){
                v.giveGold(3);
            }
        }
        
        else{
            v.tell("Hmm, looks like you need a key. Lets see where we can find you one.");
        }
    }
    
    
    private void hallway2(Visitor v){
    
        v.tell("As you walk through the hallway, you see a treasure chest!!");
        
        if(v.hasEqualItem(new Item("Key"))){
            char choice = v.getChoice("Do you want to use the key you have to open the chest(y/n)?",new char []{'y','n'});
            if (choice == 'y'){
                v.tell("It was empty, better luck next time");
            }
        }
        
        else{
            v.tell("Hmm, looks like you need a key. Lets see where we can find you one.");
        }
    }

    private void hallway3(Visitor v){
    
        v.tell("As you walk through a wizard come up to you an ask about if you had found his Wand, he cannot find it anywhere");
        
        if(v.hasEqualItem(new Item("Wand"))){
            char choice = v.getChoice("Do you want to give the wizard the Wand You found (y/n)?",new char []{'y','n'});
            if (choice == 'y'){
                v.tell("Wizard: Thank you so much, been looking for this throughout the house. Here is some gold for helping me!");
                v.giveGold(4);
            }
        }
        
        else{
            v.tell("You haven't seen a wand yet, I wonder were you could find one");
        }
    }


    

    // This method is used to return a random int given bound
    private static int randomInt(int bound) {
    Random r = new Random();
    return r.nextInt(bound);
    }
    
    
}
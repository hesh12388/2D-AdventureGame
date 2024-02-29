package OOP.ec22518.MP.contributions;

class House_ec22902 implements Visitable {
    static boolean key_status = true; 
    static boolean shovel_status = true;
    static boolean torch_status = false; 
    Item torch = new Item("Torch"); 
    Item golden_shovel = new Item("Golden shovel");
    
    //This impletation is inspired by the code of ec22842
    class Basement extends Room{
        Room right; 
        Room left; 
        
        Basement(){
            right = new Room_ex21423(); 
            left = new Room_ec22761();
        }
        
        public Direction visit(Visitor v, Direction d){
            v.tell("Welcome to this mysterious basement..."); 
            
            if (!v.hasEqualItem(torch) || !v.hasIdenticalItem(torch)){
                v.tell("Oh I think you will need one of these");
                v.giveItem(torch); 
            }
            
            v.tell(""); 
            v.tell("Your torch is off, you should turn it on."); 
            
            char[] torch= {'y', 'n'};
            char torch_choice; 
            String stringtorch = ("turn it on? (y/n)");
            
            torch_choice = v.getChoice(stringtorch, torch);
            
            if (torch_choice == 'y'){
                torch_status = true; 
                v.tell("There is light");
            }
            
            
            if(location_x==2){
                left.visit(v, d);
            }
            
            else if(location_x==4){
                right.visit(v, d); 
            }
            
            return d;
        }
    }
    
    class Garden extends Room{
        int gardencounter = 0;
        
        public Direction visit(Visitor v, Direction d){
            v.giveGold(1);
            gardencounter++;
            
            if(gardencounter == 10 && shovel_status){
                v.tell("You have enough to buy a gold shovel!"); 
                v.giveItem(golden_shovel); 
                v.takeGold(10);
                shovel_status = false;
            }
            
            return d;
        }
    }
    
    Item basement_key = new Item("Basement Key"); 
    Room kitchen;
    Room bedroom; 
    Room bathroom; 
    Room hall;
    Basement basement; 
    Garden garden; 
    House_ec221021 doll;
    
    static int location_x = 5;
    static int location_y = 2; 
    
    House_ec22902(){
        bedroom = new Room_ec22902();
        kitchen = new Room_bt21057(); 
        bathroom = new Room_ec22414(); 
        hall = new Room_ec221150();
        
        basement = new Basement(); 
        garden = new Garden(); 
        
        doll = new House_ec221021();
    }
    
    public Direction visit(Visitor v, Direction d){
   
        //This will help to choose the direction we want to take
        char directions_choice; 
        char[] direction_options = {'n', 'e', 's', 'w'};
        String direction_string = "Where do you want to go now:\n"+
                          "n) North\n"+
                          "e) East\n"+
                          "s) South\n"+
                          "w) West"; 
        
        char first_choice; 
        char[] first_options = {'k', 'b', 'm'};
        
        
        v.tell("Welcome!");
        v.tell(" "); 
        v.giveGold(5); 
        v.tell(" "); 
        v.tell("Lord felt generous today!");
        v.tell(" ");
        ActualPosition(location_x, location_y, v, d); 
        v.tell(" "); 
        first_choice = v.getChoice("Where do you want to go now:\n"+
                          "k) Enter by the window kitchen \n"+
                          "b) Enter by the back-door... y\n"+
                          "m) Enter via the main", first_options);
        
        switch(first_choice){
            case 'k': 
                location_x = 4;
                location_y = 3; 
                
                v.tell(" ");
                v.tell(" I think you dropped you're money on the way here");
                v.takeGold(4); 
                v.tell(" "); 
                v.tell("this House is full of surprises I'm sure you'll find something valuable!"); 
                v.tell(" ");
                
                ActualPosition(location_x, location_y, v, d); 
                
                break; 
                
            case 'b': 
                location_x = 3;
                location_y = 4;
                
                ActualPosition(location_x, location_y, v, d);
                
                break;
                
            case 'm': 
                location_x = 3;
                location_y = 2; 
                
                ActualPosition(location_x, location_y, v, d); 
                
                break;
        }
        
        directions_choice = v.getChoice(direction_string, direction_options);
        
        switch(directions_choice){
                case 'n': 
                    location_y = next_NORTH(location_y);
                    break;
                case 's': 
                    location_y = next_SOUTH(location_y);
                    break;
                case 'e': 
                    location_x = next_EAST(location_x);
                    break;
                case 'w': 
                    location_x = next_WEST(location_x);
                    break;
            }
        
        while (1<=location_x && location_x<=5 && 2<=location_y && location_y<=5 || location_y==1 && 2<=location_x && location_x<=4){
            ActualPosition(location_x, location_y, v, d);
            directions_choice = v.getChoice(direction_string, direction_options);
            
            switch(directions_choice){
                case 'n': 
                    location_y = next_NORTH(location_y);
                    break;
                case 's': 
                    location_y = next_SOUTH(location_y);
                    break;
                case 'e': 
                    location_x = next_EAST(location_x);
                    break;
                case 'w': 
                    location_x = next_WEST(location_x);
                    break;
            }
        }
        
        v.tell(" ");
        ActualPosition(location_x, location_y, v, d);
    
        return d;
    }
    
    void ActualPosition(int x, int y, Visitor v, Direction d){
        String word = " ";
        if ((x==1 && 2<=y && y<=5)||(x==5 && 2<=y && y<=5)||(y==5 && 1<=x && x<=5)){
            v.tell("Actual position: "+x+", "+y); 
            v.tell(" You are actually in the  GARDEN "); 
            garden.visit(v, d); 
        }
        
        else if(y==2 && 3<=x && x<=4){
            v.tell("Actual position: "+x+", "+y); 
            v.tell(" You are actually in the MAIN HALL of the house ");
            v.tell(" "); 
            hall.visit(v, d);
            v.tell(" "); 
        }
        
        else if(x==4 && 3<=y && y<=4){
            v.tell("Actual position: "+x+", "+y); 
            v.tell(" You are actually in the sumptous KITCHEN ");
            v.tell(" "); 
            kitchen.visit(v, d); 
            v.tell(" "); 
        }
        
        else if(x==2 && y==3){
            v.tell("Actual position: "+x+", "+y); 
            v.tell("You are actually in the BATHROOM ");
            v.tell(" "); 
            bathroom.visit(v, d); 
            v.tell(" "); 
        }
        
        else if(y==4 && 2<=x && x<=3){
            v.tell("Actual position: "+x+", "+y); 
            v.tell(" You are actually in the  BEDROOM ");
            v.tell(" ");
            char [] reality_choice = {'y', 'n'}; 
            char rchoice = v.getChoice("Would you like to visit it? (y/n)", reality_choice);
            v.tell(" ");
            
            switch (rchoice){
                case 'y': 
                    doll.visit(v, d); 
                    v.tell(" ");
                    v.tell("Back to reality!");
                    break; 
                case 'n': 
                    bedroom.visit(v, d); 
                    break; 
            }
            v.tell(" "); 
        }
        
        else if(y==3 && x==3){
            v.tell("Actual position: "+x+", "+y);  
            v.tell(" You are in theHALLWAY");
            
            if (key_status){
                v.tell(" there is a key on the floor");
                v.giveItem(basement_key);
                key_status = false; 
            }
            
        }
        
        else if(y==1 && 2<=x && x<=4){
            
            if (v.hasIdenticalItem(basement_key)){
                v.tell("Actual position: "+x+", "+y); 
                v.tell(" You are actually in the  BASEMENT ");
                v.tell(" ");
                basement.visit(v, d); 
                v.tell(" ");
            }
            
            else{
                v.tell("Sorry you don't have the key to enter here!");
            }
        }
        
        else if(y==2 && x==2){
            v.tell("Actual position: "+x+", "+y); 
            v.tell(" You are in the STORAGE ");
        }
    
        else{
            v.tell("You're out of the domain! ");  
        }      
    }
    
    int next_SOUTH(int y){
        return y-1;
    }
    
    int next_NORTH(int y){
        return y+1;
    }
    
    int next_EAST(int x){
        return x+1;
    }
    
    int next_WEST(int x){
        return x-1;
    }
    
}

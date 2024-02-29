package OOP.ec22518.MP.contributions;

class House_ec22830 implements Visitable {
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
            v.tell("I see that your torch is off, you should tunr it on."); 
            
            char[] torch= {'y', 'n'};
            char torch_choice; 
            String stringtorch = ("Do you want to turn it on? (y/n)");
            
            torch_choice = v.getChoice(stringtorch, torch);
            
            if (torch_choice == 'y'){
                torch_status = true; 
                v.tell("And there was light!");
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
                v.tell("You now have enough coin to buy this gold shovel!"); 
                v.giveItem(golden_shovel); 
                v.takeGold(10);
                shovel_status = false;
            }
            
            return d;
        }
    }
    
    class Shop extends Room{
        char shop_choice = 'a';
        char[] shop_array = {'a', 'b', 'c', 'd', 'e'}; 
        String shop_string = "What would you like to buy? :\n"+
                          "a) Bag of Chips\n"+
                          "b) Map of the house\n"+
                          "c) Bottle of Water\n"+
                          "d) Pair of shoes\n"+
                          "e) Exit the shop..."; 
        
        
        public Direction visit(Visitor v, Direction d){
            v.tell(" ");
            v.tell("Welcome to the shop!");
            v.tell(" ");
            
            shop_choice = v.getChoice(shop_string, shop_array);
            
            while(shop_choice!='e'){
                
                switch (shop_choice){
                    case 'a':
                        v.giveItem(new Item("Bag of Chips")); 
                        v.takeGold(3);
                        break; 
                        
                    case 'b':
                        v.giveItem(new Item("Map of the House")); 
                        v.takeGold(5);
                        break;
                        
                    case 'c': 
                        v.giveItem(new Item("Bottle of Water")); 
                        v.takeGold(3);
                        break; 
                        
                    case 'd': 
                        v.giveItem(new Item("Pair of shoes"));
                        v.takeGold(2);
                        break;
                }
                
                shop_choice = v.getChoice(shop_string, shop_array);
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
    Shop shop; 
    
    static int location_x = 5;
    static int location_y = 2; 
    
    House_ec22830(){
        bedroom = new Room_ec22830();
        kitchen = new Room_bt21057(); 
        bathroom = new Room_ec22414(); 
        hall = new Room_ec221150();
        
        basement = new Basement(); 
        garden = new Garden(); 
        shop = new Shop();
        
        doll = new House_ec221021(); 
    }
    
    public Direction visit(Visitor v, Direction d){
    /* This is the layout of my house
    
       y=5 [GARDEN] [GARDEN] [GARDEN] [GARDEN] [GARDEN]
       y=4 [GARDEN] [bedroom] [bedroom] [kitchen] [GARDEN]
       y=3 [GARDEN] [bathroom] [HALLWAY] [kitchen] [GARDEN]
       y=2 [GARDEN] [shop] [main hall] [main hall] [GARDEN]
       y=1         [basement] [basement] [basement] 
             x=1       x=2        x=3       x=4       x=5
    */
        //This will help us later in the program to choose the direction we want to take
        char directions_choice; 
        char[] direction_options = {'n', 'e', 's', 'w'};
        String direction_string = "Where do you want to go now:\n"+
                          "n) North\n"+
                          "e) East\n"+
                          "s) South\n"+
                          "w) West"; 
        
        char first_choice; 
        char[] first_options = {'k', 'b', 'm'};
        
        
        v.tell("Welcome... you just entered the domain of Lord ec22830!");
        v.tell(" "); 
        v.giveGold(5); 
        v.tell(" "); 
        v.tell("Lord felt generous today!");
        v.tell(" ");
        ActualPosition(location_x, location_y, v, d); 
        v.tell(" "); 
        first_choice = v.getChoice("Where do you want to go now:\n"+
                          "k) Enter by the window kitchen...beware of brambles! \n"+
                          "b) Enter by the back-door... nobody uses this one it is too scary\n"+
                          "m) Enter via the main...the only choice if you are a boring person", first_options);
        
        switch(first_choice){
            case 'k': 
                location_x = 4;
                location_y = 3; 
                
                v.tell(" ");
                v.tell("Oh! What happened to you! These brambles are no jokes... I think you dropped you're money on the way here");
                v.takeGold(4); 
                v.tell(" "); 
                v.tell("But don't worry this House is full of surprises I'm sure you'll find something valuable!"); 
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
            v.tell("---------- You are actually in the luxurious GARDEN of the Lord ----------"); 
            garden.visit(v, d); 
        }
        
        else if(y==2 && 3<=x && x<=4){
            v.tell("Actual position: "+x+", "+y); 
            v.tell("---------- You are actually in the spectacular MAIN HALL of the house ----------");
            v.tell(" "); 
            hall.visit(v, d);
            v.tell(" "); 
        }
        
        else if(x==4 && 3<=y && y<=4){
            v.tell("Actual position: "+x+", "+y); 
            v.tell("---------- You are actually in the sumptous KITCHEN ----------");
            v.tell(" "); 
            kitchen.visit(v, d); 
            v.tell(" "); 
        }
        
        else if(x==2 && y==3){
            v.tell("Actual position: "+x+", "+y); 
            v.tell("---------- You are actually in the majestic BATHROOM ----------");
            v.tell(" "); 
            bathroom.visit(v, d); 
            v.tell(" "); 
        }
        
        else if(y==4 && 2<=x && x<=3){
            v.tell("Actual position: "+x+", "+y); 
            v.tell("---------- You are actually in the awsome BEDROOM ----------");
            v.tell(" ");
            char [] reality_choice = {'y', 'n'}; 
            char rchoice = v.getChoice("Look at this stunning doll house in the corner of the room! Would you like to visit it? (y/n)", reality_choice);
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
            v.tell("---------- You are actually in the tiny HALLWAY ----------");
            
            if (key_status){
                v.tell("It seems like there is a key on the floor...");
                v.giveItem(basement_key);
                key_status = false; 
            }
            
        }
        
        else if(y==1 && 2<=x && x<=4){
            
            if (v.hasIdenticalItem(basement_key)){
                v.tell("Actual position: "+x+", "+y); 
                v.tell("---------- You are actually in the mysterious BASEMENT ----------");
                v.tell(" ");
                basement.visit(v, d); 
                v.tell(" ");
            }
            
            else{
                v.tell("Sorry you don't have th eky to come here take another look around the house!");
            }
        }
        
        else if(y==2 && x==2){
            v.tell("Actual position: "+x+", "+y); 
            v.tell("---------- You are actually in the SHOP ----------");
            v.tell(" ");
            shop.visit(v, d);
            v.tell(" ");
        }
    
        else{
            v.tell("---------- You're out of the domain! ----------");  
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


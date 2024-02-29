package OOP.ec22518.MP.contributions;

class House_ex21405 implements Visitable {
    
    Room[] list_rooms = new Room[4];
    private Item a = new Item("KFC");
    private Item b = new Item("Key");
    private Item c = new Item("Sniper rifle");
     
    char current_room;
    char choice;
    
    House_ex21405() { 
        list_rooms[0] = new Room_ex21405(); //D
        list_rooms[1] = new Room_ec22883(); //C
        list_rooms[2] = new Room_ex21247(); //B
        list_rooms[3] = new Room_ec22881(); //A
    }
        
    public Direction visit(Visitor v, Direction d) {
        v.tell("You are at the entrance of the house, you see some food, is it a good idea to eat the food, you never know when you will find food again.");
        v.tell("Try the food");
        v.giveItem(a);
        
        v.tell("You are going into the first room");
        Direction dir = list_rooms[0].visit(v, Direction.FROM_SOUTH);
        current_room = 'D';
        while(true) {

            switch (current_room) {
                case 'A':
                    
                    if(dir == Direction.TO_NORTH) {
                        v.tell("You are leaving the house.ahhahahah bye :)");
                        current_room = ' ';
                        return Direction.TO_NORTH;
                    }
                    else if(dir == Direction.TO_SOUTH) {
                        v.tell("You entering room D. Be careful of the floorboards");
                        current_room = 'D';
                        dir = list_rooms[0].visit(v, Direction.FROM_NORTH);
                        
                    }
                    
                    else if(dir == Direction.TO_WEST) {
                        v.tell("You entering room B. There is a key on the floor. Do you want to take the key?");
                        current_room = 'B';
                        choice = v.getChoice("a)yes b)no.", new char[]{'a', 'b'});
                        
                        if(choice == 'a') {
                            v.tell("Gold reward added!");
                            v.giveGold(3);
                        }
                            
                        dir = list_rooms[2].visit(v, Direction.FROM_EAST);
                        
                    }
                    
                    else  {
                        v.tell("You are travelling through the entrance of room B. In the passage you see a treasure chest.");
                        choice = v.getChoice("Do you want to open it? a) yes b) no.", new char[]{'a', 'b'});
                        if(choice == 'a') {
                            v.tell("Inside the chest, there is mysterious key?");
                            v.giveItem(b);
                        }
                        v.tell("You enter the room"); 
                        current_room = 'B';
                        dir = list_rooms[2].visit(v, Direction.FROM_WEST);
                        
                    }
                    
                    break;
                    
                case 'B':
                    
                    if(dir == Direction.TO_NORTH) {
                        v.tell("You are entering room C.");
                        current_room = 'C';
                        dir = list_rooms[1].visit(v, Direction.FROM_SOUTH);
                        
                    }
                    else if(dir == Direction.TO_SOUTH) {
                        v.tell("You are entering room C. You see a small tunnel on the way.");
                        choice = v.getChoice("Do you enter it? a) yes b) no.", new char[] {'a', 'b'});
                        if(choice == 'a') {
                            
                            v.tell("Rats start rushing you from the other end of the tunnel");
                            v.tell("You run out the tunnel as fast as flash.");
                            
                        }
                        v.tell("You make it into room C");
                        current_room = 'C';
                        dir = list_rooms[1].visit(v, Direction.FROM_NORTH);
                        
                    }
                    else if(dir == Direction.TO_WEST) {
                        
                        v.tell("You are travelling through the entrance of room A. In the passage you see a treasure chest.");
                        choice = v.getChoice("Do you want to open it? a) yes b) no.", new char[]{'a', 'b'});
                        if(choice == 'a') {
                            v.tell("Inside the chest, there is mysterious key?");
                            v.giveItem(b);
                        }
                        v.tell("You enter the room"); 
                        current_room = 'A';
                        dir = list_rooms[3].visit(v, Direction.FROM_EAST);
                        
                    }
                    
                    else {
                        
                      v.tell("You entering room A. There is a key on the floor. Do you want to take the key?");
                        current_room = 'A';
                        choice = v.getChoice("a)yes b)no.", new char[]{'a', 'b'});
                        
                        if(choice == 'a') {
                            v.tell("Gold reward added!");
                            v.giveGold(3);
                        }
                        dir = list_rooms[3].visit(v, Direction.FROM_WEST);
                        
                    }
                    break;
                    
                case 'C': 
                    
                    if(dir == Direction.TO_NORTH) {
                        v.tell("You are entering room B. You see a small tunnel on the way.");
                        choice = v.getChoice("Do you enter it? a) yes b) no.", new char[] {'a', 'b'});
                        if(choice == 'a') {
                            
                            v.tell("Rats start rushing you from the other end of the tunnel");
                            v.tell("You run out the tunnel as fast as flash.");
                            
                        }
                        v.tell("You make it into room B");
                        current_room = 'B';
                        dir = list_rooms[2].visit(v, Direction.FROM_SOUTH);
                    }
                    
                    else if( dir == Direction.TO_SOUTH) {
                        v.tell("You are entering room B");
                        current_room = 'B';
                        dir = list_rooms[2].visit(v, Direction.FROM_NORTH);
                    }
                    else if(dir == Direction.TO_WEST) {
                        v.tell("You are entering room D");
                        current_room = 'D';
                        dir = list_rooms[0].visit(v, Direction.FROM_EAST);
                    }
                    
                    else {
                        v.tell("You are entering the abandonned arena, in the arena there is a giant hole.");
                        choice = v.getChoice("Do you want to go down it? a) yes b) no", new char[] {'a', 'b'});
                        
                        if(choice == 'a') {
                            v.tell("You have entered the hole. You see there's barely anything inside.");
                            v.tell("You see a sniper rifle. Do you want to take it?");
                            v.giveItem(c);
                            v.tell("You now return to the arena");
                        }
                        
                        v.tell("Do you want to leave the house or go back inside?");
                        choice = v.getChoice("Choose a) re enter, b) leave", new char[] {'a', 'b'});

                        if(choice == 'b') {
                            v.tell("You are now leaving the house through the arena exit.\nBYE");
                            return Direction.TO_WEST;
                        }
                        else {
                            v.tell("You are back in room C. Do you want to go in room D, B or outside again?");
                            choice = v.getChoice("Choose a) room B, b) room D, c) Outside", new char[] {'a', 'b', 'c'});
                            if(choice ==  'a') dir = Direction.TO_NORTH;
                            else if(choice == 'b') dir = Direction.TO_WEST;
                            else dir = Direction.TO_EAST;
                        }
                    }
                    break;
                
                case 'D': 
                    if(dir == Direction.TO_NORTH) {
                        v.tell("You are entering room A");
                        current_room = 'A';
                        dir = list_rooms[3].visit(v, Direction.FROM_SOUTH);
                    }
                    else if(dir == Direction.TO_SOUTH) { 
                        v.tell("You are trying to exit the way you entered the house.");
                        choice = v.getChoice("Are you sure you want to exit? choose a)Yes b)no.", new char[] {'a', 'b'});
                        if(choice == 'a') return Direction.TO_SOUTH;
                        
                        v.tell("Do you want to go in room A, room C or in the Forest?");
                        choice = v.getChoice("Choose a) room A, b) room B, c) Forest.", new char[] {'a', 'b', 'c'});
                        if(choice == 'a') dir = Direction.TO_NORTH;
                        else if(choice == 'b') dir = Direction.TO_EAST;
                        else dir = Direction.TO_WEST;
                    }
                    else if(dir == Direction.TO_WEST) {
                        v.tell("You are entering the forest. It has loads of different types of birds.");
                        v.tell("You see birds moving around.");
                        
                        if(v.hasEqualItem(c) == true){
                            v.tell("Do you want use the sniper rifle to kill some birds?");
                            choice = v.getChoice("Choose a) yes b) no.", new char[] {'a', 'b'});
                            if(choice == 'a') {
                                v.tell("You are sniping birds, when suddenly a swarm of birds fly over you!");
                                v.tell("You run to the side and are covered in poo");
                            }
                        }
                        v.tell("Do you want to go back inside or exit?");
                        choice = v.getChoice("Choose a) Inside or b) Exit.", new char[] {'a', 'b'});
                        if(choice == 'b') {
                                v.tell("You are now leaving the house through the forest exit.\nGoodbye.");
                                return Direction.TO_WEST;
                            }
                            v.tell("Now, do you want to go in room A, room C, or back in the garden?");
                            choice = v.getChoice("Choose a) room A, b) room C, c) forest.", new char[] {'a', 'b', 'c'});
                            if(choice == 'a') dir = Direction.TO_NORTH;
                            else if(choice == 'b') dir = Direction.TO_EAST;
                            else dir = Direction.TO_WEST;
                    }
                    //(dir == Direction.TO_EAST)
                    else {
                        v.tell("You are entering room C");
                        current_room = 'C';
                        dir = list_rooms[1].visit(v, Direction.FROM_SOUTH);
                    }
                                               
                    
            }
             
        }
    }
 
 }

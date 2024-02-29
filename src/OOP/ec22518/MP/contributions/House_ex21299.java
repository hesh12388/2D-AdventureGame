package OOP.ec22518.MP.contributions;

class House_ex21299 implements Visitable {
    
    Room[] list_rooms = new Room[4];
    private Item a = new Item("Nike");
    private Item b = new Item("Key");
    private Item c = new Item("Fishing pole");
     
    char current_room;
    char choice;
    
    House_ex21299() { 
        list_rooms[0] = new Room_ex21299(); // Room D
        list_rooms[1] = new Room_ex21566(); // ROOM C
        list_rooms[2] = new Room_ex21247(); // ROOM B
        list_rooms[3] = new Room_ex20539(); // ROOM A
    }
        
    public Direction visit(Visitor v, Direction d) {
        v.tell("You are entering the house. You see a shoerack at the entrance. It is best to wear some shoes as you never know what you may find in the house");
        v.tell("Here, take these shoes.");
        v.giveItem(a);
        
        v.tell("You are entering the first room");
        Direction dir = list_rooms[0].visit(v, Direction.FROM_SOUTH);
        current_room = 'D';
        while(true) {

            switch (current_room) {
                case 'A':
                    
                    if(dir == Direction.TO_NORTH) {
                        v.tell("You are leaving the house. Goodbye! Also live the shoes!!!");
                        current_room = ' ';
                        return Direction.TO_NORTH;
                    }
                    else if(dir == Direction.TO_SOUTH) {
                        v.tell("You entering room D. Watch out the handle is a bit rusty");
                        current_room = 'D';
                        dir = list_rooms[0].visit(v, Direction.FROM_NORTH);
                        
                    }
                    
                    else if(dir == Direction.TO_WEST) {
                        v.tell("You entering room B. There is a cat in the corridor on the way there. Do you want to give it a treat?");
                        current_room = 'B';
                        choice = v.getChoice("a) for yes b) for no.", new char[]{'a', 'b'});
                        
                        if(choice == 'a') {
                            v.tell("Good work here is some gold");
                            v.giveGold(3);
                        }
                            
                        dir = list_rooms[2].visit(v, Direction.FROM_EAST);
                        
                    }
                    //(dir == Direction.TO_EAST)
                    else  {
                        v.tell("You are on your way to enter room B. In the corridor you see a small drawer.");
                        choice = v.getChoice("Do you want to open it? Choose a) yes or b) no.", new char[]{'a', 'b'});
                        if(choice == 'a') {
                            v.tell("You open it and find inside a lot of useless junk, but under it all you also see a key.");
                            v.tell("Do you take the key?");
                            v.giveItem(b);
                        }
                        v.tell("You move forward and enter the room."); 
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
                        v.tell("You are entering room C. On the way there you notice that there is something under the carpet and realize its a trapdoor.");
                        choice = v.getChoice("Do you enter it? Choose a) yes or b) no.", new char[] {'a', 'b'});
                        if(choice == 'a') {
                            
                            v.tell("You start descending down but midway through, you hear a spine-tingling raor coming from below.");
                            v.tell("Immediately you run upstaris and close the trapdor.");
                            
                        }
                        v.tell("You proceed into the room");
                        current_room = 'C';
                        dir = list_rooms[1].visit(v, Direction.FROM_NORTH);
                        
                    }
                    else if(dir == Direction.TO_WEST) {
                        
                         v.tell("You are on your way to enter room A. In the corridor you see a small drawer.");
                        choice = v.getChoice("Do you want to open it? Choose a) yes or b) no.", new char[]{'a', 'b'});
                        if(choice == 'a') {
                            v.tell("You open it and find inside a lot of useless junk, but under it all you also see a key.");
                            v.tell("Do you take the key?");
                            v.giveItem(b);
                        }
                        v.tell("You move forward and enter the room."); 
                        current_room = 'A';
                        dir = list_rooms[3].visit(v, Direction.FROM_EAST);
                        
                    }
                    //( dir == Direction.TO_EAST)
                    else {
                        
                        v.tell("You entering room A. There is a cat in the corridor on the way there. Do you want to give it a treat?");
                        current_room = 'A';
                        choice = v.getChoice("a) for yes b) for no.", new char[]{'a', 'b'});
                        
                        if(choice == 'a') {
                            v.tell("Good work here is some gold");
                            v.giveGold(3);
                        }
                        dir = list_rooms[3].visit(v, Direction.FROM_WEST);
                        
                    }
                    break;
                    
                case 'C': 
                    
                    if(dir == Direction.TO_NORTH) {
                        v.tell("You are entering room B. On the way there you notice that there is something under the carpet and realize its a trapdoor.");
                        choice = v.getChoice("Do you enter it? Choose a) yes or b) no.", new char[] {'a', 'b'});
                        if(choice == 'a') {
                            
                            v.tell("You start descending down but midway through, you hear a spine-tingling raor coming from below.");
                            v.tell("Immediately you run upstaris and close the trapdor.");
                            
                        }
                        v.tell("You proceed into the room");
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
                    //(dir == Direction.TO_EAST)
                    else {
                        v.tell("You are entering the children's playground outside. You see a 'SPECIAL STAIRS' leading to a treehouse");
                        choice = v.getChoice("Do you want to climb up? a) yes and b) no", new char[] {'a', 'b'});
                        
                        if(choice == 'a') {
                            v.tell("You have entered the small tree house. You see its woody and old with not much inside.");
                            v.tell("Yet you manage to see a fishing pole. Do you want to take it?");
                            v.giveItem(c);
                            v.tell("You now return to the playground");
                        }
                        
                        v.tell("Do you want to leave the house or go back inside?");
                        choice = v.getChoice("Choose a) enter again, b) leave", new char[] {'a', 'b'});

                        if(choice == 'b') {
                            v.tell("You are now leving the house through the playground exit.\nBYE");
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
                        choice = v.getChoice("Are you sure you want to exit? choose a)Yes and b)no.", new char[] {'a', 'b'});
                        if(choice == 'a') return Direction.TO_SOUTH;
                        
                        v.tell("Well, do you want to go in room A, room C or in the garden?");
                        choice = v.getChoice("Choose a) room A, b) room B, c) Garden.", new char[] {'a', 'b', 'c'});
                        if(choice == 'a') dir = Direction.TO_NORTH;
                        else if(choice == 'b') dir = Direction.TO_EAST;
                        else dir = Direction.TO_WEST;
                    }
                    else if(dir == Direction.TO_WEST) {
                        v.tell("You are entering the garden. It has a pond in the center with fish swimming inside.\n There are flowers of different varieties.");
                        v.tell("You see frogs, squirrels and birds moving around.");
                        
                        if(v.hasEqualItem(c) == true){
                            v.tell("Do you want use the fishing pole to catch some fish?");
                            choice = v.getChoice("Choose a) yes or b) no.", new char[] {'a', 'b'});
                            if(choice == 'a') {
                                v.tell("You are fishing serenly when suddenly you feel a strong pull on your fising pole and you slip into the water!");
                                v.tell("You get out of the pond but you are totally wet!");
                            }
                        }
                        v.tell("Do you want to go back inside or exit?");
                        choice = v.getChoice("Choose a) Inside or b) Exit.", new char[] {'a', 'b'});
                        if(choice == 'b') {
                                v.tell("You are now leaving the house through the garden exit.\nGoodbye.");
                                return Direction.TO_WEST;
                            }
                            v.tell("Now, do you want to go in room A, room C, or back in the garden?");
                            choice = v.getChoice("Choose a) room A, b) room C, c) garden.", new char[] {'a', 'b', 'c'});
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

package OOP.ec22518.MP.contributions;

class House_ec22743 implements Visitable {
    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    private Room room5;
    private boolean keyClaimed;
    
    public House_ec22743() {
        room1 = new Room_ec22743();
        room2 = new Room_ec22441();
        room3 = new Room_ec22419();
        room4 = new Room_ec22766();
        room5 = new Room_ec22579();
        keyClaimed = false;
    }
    
    public Direction visit(Visitor v, Direction d){
        v.tell("Welcome to my house. In your journey you will encounter many different and unique rooms...have fun!(unless you want to leave");
        
        char[] options = {'a', 'b'};
        char direct;
        char picked = v.getChoice("Would you like to \n (a) explore the house \n (b) exit ?", options);
        
        if (picked == 'b') { keyClaimed = true; }
        int how_many_times = 0;
        boolean eastroom = false;
        boolean westroom = false;
        boolean northroom = false;
        boolean southroom = false;
        
        
        
        while (keyClaimed == false) {
            v.tell("You enter the first room through the front door. You suddenly hear the door shut behind you. You try to open the door but it doesn't move an inch. Looks like you are stuck here until unless you find the special gemstone key hidden in this house.");
            
            d = room3.visit(v, d); //first room exploration
            southroom = true;
            
            v.tell("Leaving the room, you notice the hallway is quite compact but through the window on your left, you can see a garden. There is no way to get to the garden yet.");
            v.tell("You go to the next door present towards you.");
            
            d = room4.visit(v, d); //second room exlporation
            
            if (d==Direction.TO_NORTH) {
                v.tell("You enter the room up north...");
                d = room1.visit(v, d); // top room exploration
                v.tell("After exploring the room, you go back to the previous room you came from. No need to go North anymore.");
                northroom = true;
                direct = v.getChoice("Do you wish to go (a) East or (b) West?", options);
                if (direct=='a') {
                    v.tell("You head EAST"); //exploration eastroom
                    d = room2.visit(v, d);
                    eastroom = true;
                }
                else {
                    v.tell("You head WEST");
                    d = room5.visit(v, d); //exploration westroom
                    westroom = true;
                }
            }
            else if (d==Direction.TO_SOUTH) { 
                v.tell("You are back to the first room you came from. You've explored this room enough. You go back to the other room.");
                direct = v.getChoice("Do you wish to go (a) East or (b) West?", options);
                if (direct=='a') {
                    v.tell("You head EAST");
                    d = room2.visit(v, d); //exploration eastroom
                    eastroom = true;
                }
                else {
                    v.tell("You head WEST");
                    d = room5.visit(v, d); //exploration westroom
                    westroom = true;
                }                
            }
            
            else if (d==Direction.TO_EAST) {
                d = room2.visit(v, d); //eastroom
                eastroom = true;
            }
            else {
                d = room5.visit(v, d); // westroom
                westroom = true;
            }
            
            if (eastroom) {
                v.tell("after exploring the room, you end up going back from then same door, leading you back to the room you can from. No need to go EAST anymore.");

                if (northroom == true) {
                    v.tell("You head to the only room you haven't explored yet. You head WEST.");
                    d = room5.visit(v, d);
                    westroom = true;
                }
            }
            
            if (westroom) {
                v.tell("You have a feeling the entrance to garden is here.");
                if (d==Direction.TO_WEST  || d==Direction.TO_NORTH) {
                    v.tell("The doors seem to be blocked off. There is no way to get past them.");
                    v.tell("Maybe try again from the beginning.");
                }
                if (d==Direction.TO_EAST) {
                    v.tell("You have already explored this room.");
                    v.tell("Maybe try again from the beginning.");
                }
                if (d==Direction.TO_SOUTH) {
                    v.tell("You go SOUTH to find the entrance to the garden.");
                    v.tell("YOU FOUND THE SPECIAL GEMSTONE KEY WHILST SEARCHING!");
                    keyClaimed = true;
                }
            }
        }
        
        v.tell("You manage to exit.");
        
        d = Direction.TO_SOUTH;
        return d;
    }
    
}

package OOP.ec22518.MP.contributions;

class House_ec22791 implements Visitable {
    Room room1;
    Room room2; 
    
    House_ec22791(){
        room1= new Room_ec22791(); 
        room2= new Room_ec221016();
    }
    
    public Direction visit(Visitor v, Direction d) { 
        
        v.tell("Welcome to Aurora's house, you will have the choice to enter 1 of 2 roooms. The rooms face each other and are on the two separate ends of a hallway. ");
        String whichRoom= (" You can either enter a)The room to your left or b) The room to your right");
        String LeaveStay= (" Do you wish to l)leave the house or s) to stay?"); 
        char[] optionsRoom= {'a', 'b'};
        char[] optionsLS= {'l', 's'};
        
        Direction newDirection= d; 
        char answer= v.getChoice(whichRoom, optionsRoom); 
        
        if (answer== 'a'){
            v.tell("As you walk towards the room to your left, you hear footsteps behind you and suddenly the power goes off and the room goes pitch black."); 
            char LS= v.getChoice(LeaveStay, optionsLS);
            if (LS=='l'){
                d= Direction.TO_SOUTH;
            }
            else{
                v.tell("You will now enter the room to your left"); 
                d=room1.visit(v,d);
            }
        }
        if (answer=='b'){
            v.tell("This is the room to your right but... a ghost appears and tells you: 'Before you can enter this room, you must solve this riddle...if you get it wrong you will enter the opposite room.' "); 
            String Question= (" If POST is 1234 and FLIRT is 56784, what is FROST? Choices: a) 58234 b) 58243 c) 52384 d) 43285"); 
            char[] riddleOptions= {'a', 'b', 'c', 'd'};
            char Riddle= v.getChoice(Question, riddleOptions); 
            if (Riddle== 'a'){
                v.tell("This is the correct answer, you may enter the room."); 
                d= room2.visit(v,d); 
            }
            else{
                d=room1.visit(v,d);
            }
        }
        return d;
    }
}

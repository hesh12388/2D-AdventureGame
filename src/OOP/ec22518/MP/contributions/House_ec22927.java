package OOP.ec22518.MP.contributions;

class House_ec22927 implements Visitable {
    Room r1; 
    Room r2; 
    Room r3;
    
    House_ec22927(){
        r1 = new Room_ec22927(); //me 
        r2 = new Room_ec221183(); //khadija
        r3 = new Room_ec22720(); //james
    }
    
    public Direction visit(Visitor visitor, Direction direction){
        
        char [] choices = {'a', 'b', 'c', 'd'};
        char [] shoes = {'y', 'n'};
        String options = "Welcome to my house! Would you like to explore a) Room 1 b) Room 2 c) Room 3 or d) put your shoes back on?";
        
        visitor.tell("This is an asian household, please take off your shoes.");
        char shoe_choice = visitor.getChoice("Are you going to take off your shoes? (y/n)", shoes);
   
        while(shoe_choice == 'y'){
        
        char user_decision = visitor.getChoice(options, choices);
        
            if (user_decision == 'a'){
            visitor.tell("Welcome to the first room! Word of advice, do not underestimate the cat.");
            direction = r1.visit(visitor, direction);
        }
            else if (user_decision == 'b'){
            visitor.tell("Welcome to the second room! Will you repair broken bonds with your father or die? You decide!");
            direction = r2.visit(visitor, direction);
        }
            else if (user_decision == 'c'){
                visitor.tell("Welcome to the third room, the only room where death isn't an option!");
                direction = r3.visit(visitor, direction);
            }
            else if(user_decision == 'd'){
            visitor.tell("You may leave.");
            break;
            }
            else{
            user_decision = visitor.getChoice("Please choose one of the options.", choices);
            }
        }
            return direction;
    }
    }

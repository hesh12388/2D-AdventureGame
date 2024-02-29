package OOP.ec22518.MP.contributions;

class House_ec22760 implements Visitable {
    Room Room1;
    Room Room2;
    Room Room3;
    
    House_ec22760(){
        Room1 = new Room_ec22434();
        Room2 = new Room_ec21413();
        Room3 = new Room_ec22760();
    }
    
    public Direction visit(Visitor visitor, Direction direction){
        visitor.tell("Welcome to my house!");
        String askRoom = ("There are three rooms, would you like to go to a) Room 1, b) Room 2, or c) Room 3?");
        char[] options = {'a', 'b', 'c'};
        boolean exit = false;
        Room roomChosen = Room1;
        boolean chosen = false;
        String hallwayChoices = ("Would you like to a) go to the next room or b) leave the house?");
        char[] choice = {'a','b'};
        
        char answer = visitor.getChoice(askRoom, options);
        
        while(!chosen){
            if(answer == 'a'){
                roomChosen = Room1;
                chosen = true;
            }
            else if(answer == 'b'){
                roomChosen = Room2;
                chosen = true;
            }
            else if(answer == 'c'){
                roomChosen = Room3;
                chosen = true;
            }
            else{
                visitor.tell("invalid choice. choose either (a), (b) or (c)");
            }
        }
        
        
        while(!exit){
            if(roomChosen == Room1){
                direction = Room1.visit(visitor, direction);
                if(direction == Direction.TO_SOUTH){
                    roomChosen = Room2;
                    visitor.tell("You are going to Room 2");
                }
                else if(direction == Direction.TO_EAST){
                    visitor.tell("You have entered the hallway. You have receivd 3 gold peices!");
                    visitor.giveGold(3);
                    char ans = visitor.getChoice(hallwayChoices, choice);
                    
                    if(ans == 'a'){
                        visitor.tell("You are going to Room 3 now");
                        roomChosen = Room3;
                    }
                    else if(ans == 'b'){
                        exit = true;
                    }
                }
            }
            else if(roomChosen == Room2){
                direction = Room2.visit(visitor, direction);
                if(direction == Direction.TO_NORTH){
                    roomChosen = Room1;
                    visitor.tell("You are going to Room 1");
                }
                else if(direction == Direction.TO_EAST){
                    roomChosen = Room3;
                    visitor.tell("You are going to Room 3");
                }
            }
            else if(roomChosen == Room3){
                direction = Room3.visit(visitor, direction);
                if(direction == Direction.TO_NORTH){
                    roomChosen = Room1;
                    visitor.tell("You are going to Room 1 now");
                }
                else if(direction == Direction.TO_EAST){
                    visitor.tell("You are now leaving the house, GoodBye!");
                    exit = true;
                }
            }
        }
        return direction;
    }
}
        

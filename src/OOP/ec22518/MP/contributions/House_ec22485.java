package OOP.ec22518.MP.contributions;

class House_ec22485 implements Visitable {

    Room room1;
    Room room2;
    
    House_ec22485(){
        room1= new Room_ec22599();
        room2= new Room_ec22466();
     }
        
    public Direction visit (Visitor visitor, Direction move){
        
        visitor.tell ("Welcome to room ec22485, enjoy your visit!");
        
        char [] optionAvailable = {'a', 'b', 'c'};
        String description = ("Choose your weapon to escape the room: a)ladder b)torch c)key");
        char chosenOption = visitor.getChoice (description, optionAvailable);
        
        if (chosenOption == optionAvailable[0]){
            visitor.giveItem (new Item("ladder"));
            visitor.tell ("Noooo! you dropped the gold while climbing");
            visitor.tell ("You got no new golds");
            
        }
        else if (chosenOption == optionAvailable [1]){
            visitor.giveItem (new Item("torch"));
            visitor.tell ("Oh no! A zombie spotted you, you lost the gold!!");
            visitor.takeGold (1);
            move= room1.visit (visitor, move);
        }
        else if (chosenOption == optionAvailable [2]){
            visitor.giveItem (new Item("key"));
            visitor.tell ("Great, take the gold and run!");
            visitor.giveGold (1);
            move=Direction.TO_NORTH;
        }
        else {
            visitor.tell ("you did not choose the right answer, you did not gain nor lost any gold");
            move= room1.visit (visitor, move);
        }
        return Direction.opposite (move);
        }
}

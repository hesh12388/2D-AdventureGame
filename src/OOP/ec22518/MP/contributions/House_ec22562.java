package OOP.ec22518.MP.contributions;

import java.util.Random;

class House_ec22562 implements Visitable {
    
    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    int currentRoom = 0;
    Random rand = new Random();
    
    House_ec22562(){
        room1 = new Room_ec22562();
        room2 = new Room_ec22503();
        room3 = new Room_ec22569();
        room4 = new Room_ec22843();  
        
    }
    
    public Direction visit(Visitor v, Direction d) {
        d = garden(v,d);
        return d;
    }
    
    public Direction garden(Visitor v, Direction d){
        char[] options = { 'a', 'b' };
        char choice = 'c';
        
        if (!(d.toString().equals("heading South"))){
            choice = v.getChoice("You are in the garden, would you like to a) enter the house through the North entrance or b) leave the house?", options);
        }
        switch(choice){
            case 'a':
                room1(v,d);
                break;
            case 'b':
                v.tell("You have left the house");
                break;
            default:
                v.tell("Invalid option. You have now left the house");
                break;
        }
        
        return d;
            
    }
    
    
    public void room1(Visitor v, Direction d){
        currentRoom = 1;
        v.tell("You are in room 1");
        d = room1.visit(v, d);
        d = changeDirection(v,d);
        
        if (d.toString().equals("heading South")) {
            room3(v,d);
        }
        else if (d.toString().equals("heading East")) {
            room2(v,d);
        }
        else {
            garden(v,d);
        }
    }
    
    public void room2(Visitor v, Direction d){
        currentRoom = 2;
        v.tell("You are in room 2");
        d = room2.visit(v, d);
        d = changeDirection(v,d);
        
        if (d.toString().equals("heading South")) {
            room4(v,d);
        }
        else if (d.toString().equals("heading West")) {
            room1(v,d);
        }
        else {
            garden(v,d);
        }
    }
    
    public void room3(Visitor v, Direction d){
        currentRoom = 3;
        v.tell("You are in room 3");
        d = room3.visit(v, d);
        d = changeDirection(v,d);
        
        if (d.toString().equals("heading North")){
            room1(v,d);
        }
        else if (d.toString().equals("heading East")) {
            room4(v,d);
        }
        else {
            garden(v,d);
        }
    }
    
    public void room4(Visitor v, Direction d){
        currentRoom = 4;
        v.tell("You are in room 4");
        d = room4.visit(v, d);
        d = changeDirection(v,d);
        
        if (d.toString().equals("heading North")) {
            room2(v,d);
        }
        else if (d.toString().equals("heading West")) {
            room3(v,d);
        }
        else {
            garden(v,d);
        }
    }
    
    public Direction changeDirection(Visitor v, Direction d){
        Direction[] directions = {Direction.FROM_SOUTH, Direction.FROM_NORTH, Direction.FROM_WEST, Direction.FROM_EAST};
        char[] options = { 'a', 'b' };
        int randInt = rand.nextInt(4);
        Direction newDirection = d;
        char choice = 'c';
        
        v.tell("You are currently " + d.toString());
        choice = v.getChoice("Would you like to a) continue in the direction you're heading or b) change direction at random", options);
        
        switch(choice){     
            case'a':
                newDirection = d;
                break;
            case'b':
                newDirection = directions[randInt];
                break;
            default:
                v.tell("Invalid option");
                 newDirection = directions[randInt];
                break;
        }
        
        v.tell("You are now " + newDirection.toString());
                 
        return newDirection;          
        
    }
    
}

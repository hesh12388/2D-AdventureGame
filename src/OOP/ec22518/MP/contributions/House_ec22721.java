package OOP.ec22518.MP.contributions;

import java.util.Scanner;

public class House_ec22721 implements Visitable {
    //constructor- special method used to initialise objects.
    //instance variable- variable which's declared in a class but out of constructors, methods or blocks
    
    
    
    
    public static void main(String[] a) {
        Visitor v = new IOVisitor(System.out, System.in);
        House_ec22721 H = new House_ec22721();
        
        H.visit(v, Direction.UNDEFINED);
    }
    
    
    //input class
    public String input(String statement)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(statement);
        return scanner.nextLine();
    }
    
    public char verify(String statement)
    {
        String temp = input(statement);
        char x = 'd';
        
        if(temp.equals("a") || temp.equals("b") || temp.equals("c") || temp.equals("d")){
            x = temp.charAt(0);
        }else{
            System.out.println("Invalid input traveller.");
            x = verify(statement);
        }
        return x;
    }
    
    
    
    
    
    
    //clone repo of other rooms
    //Those rooms will be read into here
    public char R = 'd';
    Room R1 = new Room_ec221247(); //num1, a
    Room R2 = new Room_ec221183(); //num2, b
    Room R3 = new Room_ec22431(); //num3, c
    Room R4 = new Room_ec22770(); //hallway value
    
    Room currentRoom = R4;
    
    
    
    public Direction visit (Visitor V, Direction d) {
         
        boolean leave = false;
        boolean key = false;
        Direction direct = currentRoom.visit(V, d);
        
        //intro
        V.tell("Welcome to the house of A. You can leave anytime through the corridor if you wish. Explore as you want weary traveller.");
        V.tell("Do not open the closet though.");
        
        //stored in loop
        //inbetween rooms is corridor which can let player leave house
        
        while(leave == false) {
            //1st room
            if (R == 'a') {
                char answer = V.getChoice("It is an empty room, nothing is here but a key by the bed (a) and an old bag (b):", new char[] {'a', 'b'});
                if(answer == 'a' ){
                    key = true;
                    V.tell("You hear a strange noise as a chill runs down your spine as you pick up the coin..");
                }else if (answer == 'b') {
                    V.tell("It had some antique coins inside.");
                    V.giveGold(10);
                }
                direct = R1.visit(V, direct);
                
                //results
                
                if (direct == Direction.TO_NORTH) {
                    V.tell("Error... There's a noise above you as you look up.. Best not to go that way.");
                }else if(direct == Direction.TO_EAST){
                    V.tell("There is nothing that way...");
                    
                }else if(direct == Direction.TO_SOUTH){
                    V.tell("You head Down to the hallway.");
                    R = 'd';
                }else if(direct == Direction.TO_WEST){
                    V.tell("There is nothing that way...");
                }
            }
            //2nd room
            else if (R == 'b') {
                direct = R2.visit(V, direct);
                
                //results
                if (direct == Direction.TO_NORTH) {
                    V.tell("You head to the attic through a dusty ladder");
                    R = 'a';
                }else if(direct == Direction.TO_EAST){
                    V.tell("You head east and somehow end back at the hallway room");
                    R = 'd';
                }else{
                    V.tell("There is nothing that way...");
                }
            }
            //3rd room
            else if (R == 'c') {
                char answer1 = V.getChoice("You see the locked closet I warned you about, what will you do. (a) Open it, (b) leave it alone", new char[] {'a', 'b'});
                if(answer1 == 'a') {
                    if(key == true){
                     V.tell(".. I warned you, to the player reading, I'll spare the details and the fate that awaited the traveller you led.");
                     System.exit(0);
                    }else{
                     V.tell("A blood curdling cry emits from the cupboard what coulod only be described as a nail sounds scratched the door.");   
                    }
                }else{
                    V.tell("Good, it is better that way.");
                }
                
                direct = R3.visit(V, direct);
                
                //results
                if (direct == Direction.TO_NORTH) {
                    V.tell("There is nothing that way...");
                }else if(direct == Direction.TO_EAST){
                    V.tell("You head to the hallway room");
                    R = 'd';
                }else if(direct == Direction.TO_SOUTH){
                    V.tell("You head South and strangely end up at the east room.. Something isn't right here.");
                    R = 'a';
                }else if(direct == Direction.TO_WEST){
                    V.tell("There is nothing that way...");
                }
            }
            //4th room, Hallway
            else if (R == 'd') {
                R = verify("Would you like to leave the house(a) or stay (b)?");
                if(R == 'a'){
                 leave = true;   
                }else{
                 direct = R4.visit(V, direct);   
                }
                
                //results
                if (direct == Direction.TO_NORTH) {
                    V.tell("You head to the attic");
                    R = 'a';
                }else if(direct == Direction.TO_EAST){
                    V.tell("You head to the east room");
                    R = 'b';
                }else if(direct == Direction.TO_SOUTH){
                    V.tell("There is nothing that way...");
                }else if(direct == Direction.TO_WEST){
                    V.tell("You head to the older looking room to the west.");
                    R = 'c';
                }
                
            }



        }
        // Leaving house
        V.tell("Thanks for visiting the house, hope you return soon. Everyone comes back one day.");
        return direct;
    }
        
 
}        
        
    

    
    
    
     
   

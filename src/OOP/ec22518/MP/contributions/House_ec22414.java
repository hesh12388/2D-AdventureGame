package OOP.ec22518.MP.contributions;

import java.util.Random;
class House_ec22414 implements Visitable {
    
    
    final int number_of_rooms = 4; 
    private Room[] rooms = new Room[number_of_rooms];
    Item Football = new Item("football");
    
    House_ec22414(){
        rooms[0] = new Room_ec22414(); // returns any direction
        rooms[1] = new Room_ec22559(); // returns any direction
        rooms[2] = new Room_ec22434(); // returns opposite direction
        rooms[3] = new Room_ec22589(); // returns opposite direction
    }
    
    public Direction visit(Visitor v, Direction d) {
        Direction new_d;
        Room current_room = rooms[0];
        
        boolean leave = false;
        new_d = rooms[0].visit(v,d);
        
        while(!leave){
            
            if (new_d == Direction.TO_SOUTH){
                if (current_room == rooms[0]){
                    leave = true;
                }
                else if (current_room == rooms[1]){
                    current_room = rooms[0];
                }
            }
            else if (new_d == Direction.TO_EAST){
                if (current_room == rooms[0]){
                    current_room = rooms[2];
                }
                else if (current_room == rooms[2]){
                    current_room = rooms[0];
                }
                else if (current_room == rooms[1]){
                    leave = true;
                }
                else{
                    current_room = rooms[1];
                }
            }
            else if (new_d == Direction.TO_NORTH){
                if(current_room == rooms[0]){
                    current_room = rooms[1];
                }
                else if (current_room == rooms[1]){
                    leave = true;
                }
            }
            else{
                if (current_room == rooms[0]){
                    leave = true;
                }
                else if (current_room == rooms[2]){
                    current_room = rooms[0];
                }
                else if (current_room == rooms[1]){
                    current_room = rooms[3];
                }
            }
            if (leave != true){
                v = Hallway(v);
                new_d = current_room.visit(v,new_d);
            }
        }
        v.tell("You have exited the house.");
        return new_d;
    }
    
    public Visitor Hallway(Visitor v){
        Random random = new Random();
        v.tell("You are now in the hallway. Before you enter your next room, what would you like to do?" );
        char[] choices = {'a', 'b', 'c', 'd'};
        char choice = v.getChoice("Enter 'a' to play football in the hallway, 'b' to play snooker or 'c' to just enter the next room.", choices);
        
        if (choice == 'a'){
            int goals_scored = random.nextInt(10);
            int goals_conceded = random.nextInt(10);
            String w_l = "win";
            if (goals_scored < goals_conceded){
                w_l = "lose";
            }
            else if (goals_scored == goals_conceded){
                w_l = "drew";
            }
                
            v.tell("You play football in the hallway with your friend and " + w_l + " " + goals_scored + " - " + goals_conceded + " .");
            
            if (w_l.equals("win")){
                v.tell("You have received 5 pieces of gold and offered to keep the football");
                v.giveItem(Football);
                v.giveGold(5);
            }
            else{
                v.tell("You have lost 5 pieces of gold.");
                v.takeGold(5);
            }
        }
        else if (choice == 'b'){
            String w_l = "win";
            int random_number = random.nextInt(1);
            if (random_number == 1){
                w_l = "loss"; 
            }
            v.tell("You play snooker with you friend and " + w_l + ". You " + w_l + " some gold.");
            if (w_l == "win"){
                v.giveGold(7);
            }
            else{
                v.takeGold(5);
            }
        }  
                 
        v.tell("You have entered another room.");
        return v;
    }
    
}

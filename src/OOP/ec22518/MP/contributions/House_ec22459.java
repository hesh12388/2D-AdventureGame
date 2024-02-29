package OOP.ec22518.MP.contributions;

class House_ec22459 implements Visitable {
    Room room1;
    Room room2;
    Room room3;
    Room room4;
    
    House_ec22459(){
        room1 = new Room_ec22459();
        room2 = new Room_ec22891();
        room3 = new Room_ec22707(); 
        room4 = new Room_ec22442(); 
    }
    
    
    public Direction visit(Visitor v, Direction d) {
        boolean left = false;
        Direction leftd = room1.visit(v,d);
        Room pos = room1;
        
        v.tell("You start in Room 1 and need to find the exit out.");
        
        while (!left){
            if(pos == room1){
                char[] choices = {'S','E'};
                v.tell("You are in the first room");
                char directionMove = v.getChoice("Where would you like to go South or East", choices);
               
                if(directionMove == ('S')){
                    leftd = room3.visit(v,d);
                    pos = room3;
                }
                
                else if (directionMove == ('E')){
                    leftd = room2.visit(v,d);
                    pos = room2;
                }
                else{
                    v.tell("Neither accepted option was chose, stay in same room");
                }
            }
            else if(pos == room2){
                char[] choices = {'S','W'};
                v.tell("You are in the second room");
                char directionMove = v.getChoice("Where would you like to go South or West", choices);
               
                if(directionMove == ('S')){
                    leftd = room4.visit(v,d);
                    pos = room4;
                }
                
                else if (directionMove == ('W')){
                    leftd = room1.visit(v,d);
                    pos = room1;
                }
                else{
                    v.tell("Neither accepted option was chose, stay in same room");
                }
            }
            else if(pos == room3){
                char[] choices = {'N','E'};
                v.tell("You are in the third room");
                char directionMove = v.getChoice("Where would you like to go North or East", choices);
                
                if(directionMove == ('N')){
                    leftd = room1.visit(v,d);
                    pos = room1;
                }
                
                else if (directionMove == ('E')){
                    leftd = room4.visit(v,d);
                    pos = room4;
                }
                else{
                    v.tell("Neither accepted option was chose, stay in same room");
                }
            }
            else if(pos == room4){
                char[] choices = {'N','W','S'};
                v.tell("You are in the forth room");
                char directionMove = v.getChoice("Where would you like to go North,West or South", choices);
                
                if(directionMove == ('N')){
                    leftd = room2.visit(v,d);
                    pos = room2;
                }
                
                else if (directionMove == ('W')){
                    leftd = room3.visit(v,d);
                    pos = room3;
                }
                
                else if(directionMove == ('S')){
                    v.tell("You found the exit!");
                    v.tell("Goodbye");
                    left = true;
                }
                else{
                    v.tell("Neither accepted option was chose, stay in same room");
                }
            }
        }
        return d;
    }
}

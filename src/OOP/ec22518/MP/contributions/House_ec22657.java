package OOP.ec22518.MP.contributions;

class House_ec22657 implements Visitable {
    private Room gamesRoom;
    private Room diamondRoom;
    private Room lounge;
    private Room abandonedBuilding;
    
    Room[] r;
    int index;
    boolean Hallway;
    
    public House_ec22657(){
        this.gamesRoom = new Room_ec22657();
        this.diamondRoom = new Room_ec22586();
        this.lounge = new Room_ec22861();
        this.abandonedBuilding = new Room_ec22664();
        
        r = new Room[] {gamesRoom, diamondRoom, lounge, abandonedBuilding};
        index = 0;
        Hallway = false;
    }
    
    public Direction visit(Visitor visitor, Direction direction){
        visitor.tell("Hello! Here is the Games Room in house ec22657.");
        visitor.tell("You are aiming to exit this house.");
        Direction next = r[index].visit(visitor, direction);
        
        while(Hallway == false){
            if(next == Direction.FROM_EAST){
                if(index ==1){
                    index=2;
                }
                else{
                    index=3;
                }
            }
            else if(next == Direction.FROM_SOUTH){
                if(index == 2){
                    Hallway=true;
                }
                else if(index ==3){
                    index=2;
                }
                else{
                    index=1;
                }
            }
            else{
                index=0;
            }
        }
        
        if(Hallway==true){
            visitor.tell("Congratulation! You are in Hallway now. There are three doors and ONLY one of the door can exit this house.");
            char[] choices = {'a', 'b', 'c'};
            char choice = visitor.getChoice("Which door do you want to go? a) Fire Door b) Water Door c) Thunder Door", choices);
            
            if(choice == 'a'){
                visitor.tell("Congratulations! You now exit the house. Goodbye!");
                Hallway=false;
                index =3;
            }
            
            if(choice == 'b'){
                visitor.tell("You are in the graden now. You don't have any tools to climb out the walls.");
                visitor.tell("Sorry! Your mission has been fail. Try next time.");
                index =0;
            }
            
            if(choice == 'c'){
                visitor.tell("You are in car park now. The gate was locked and you don't have any keys to open it.");
                visitor.tell("Sorry! Your mission has been fail. Try next time.");
                index =1;
            }
        }
        
        return next;
    }
        
        
}

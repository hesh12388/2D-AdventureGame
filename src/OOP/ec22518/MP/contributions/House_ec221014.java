package OOP.ec22518.MP.contributions;

class House_ec221014 implements Visitable {
    private Room Matrix;
    private Room Casino;
    private Room DarkRoom;
    
    public House_ec221014(){
        Matrix = new Room_ec221014();
        Casino = new Room_ec22413();
        DarkRoom = new Room_ec22880();
        
    }
    
    public Direction visit(Visitor v, Direction d) {
        boolean exit = false;
        boolean escapeStoneUsed = false;
        boolean outside = true;
        char[] RoomPosition = new char[]{'O','M','C','D'};
        char RoomIn = RoomPosition[0];
        char RoomFrom = RoomPosition[0];
        Item EscapeStone = new Item("Escape Stone");
        
        while(!exit){
            
            if (outside){
                
                v.tell("You are at the dungeon entrance and you see a small cottage in front of you");
                char choiceSelected  = v.getChoice("Press (a) to enter the cottage or Press (b) to explore the surroundings",new char[]{'a','b'});
                if (choiceSelected == 'a'){
                    RoomIn = RoomPosition[1];
                    RoomFrom = RoomPosition[0];
                    outside = false;
                        
                }
                if ( choiceSelected == 'b'){
                    v.giveItem(EscapeStone);
                    exit = true;
                }
            }
            if (RoomIn == RoomPosition[1]){
                if (RoomFrom == RoomPosition[0]){
                    d = Matrix.visit(v , Direction.TO_NORTH);
                     
                }
                if (RoomFrom == RoomPosition[2]){
                    d = Matrix.visit(v, Direction.TO_SOUTH);
                }  
                RoomFrom = RoomPosition[1];
                    if (d == Direction.TO_NORTH){
                        RoomIn = RoomPosition[2];
                        outside = false;
                    }
                    else if (d == Direction.TO_SOUTH){
                        outside = true;
                    }
            }
            
            if (RoomIn == RoomPosition[2]){
                d = Casino.visit(v,Direction.TO_NORTH);
                if (d == Direction.TO_SOUTH){
                        RoomIn = RoomPosition[1];
                        
                    }
                else if (d == Direction.TO_EAST){
                        RoomIn = RoomPosition[3];
                }
            }
            if (RoomIn == RoomPosition[3]){
                d = DarkRoom.visit(v,d);
                if (d == Direction.TO_WEST){
                        RoomIn = RoomPosition[2];
                }
            }          
        }
        
               
        
        return d;
    }
}

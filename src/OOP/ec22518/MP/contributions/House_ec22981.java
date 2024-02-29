package OOP.ec22518.MP.contributions;

class House_ec22981 implements Visitable {
    private Room[][] rooms;
    private Direction d;
    
    public House_ec22981() {
        Room r1 = new Room_ec22981();
        Room r2 = new Room_ec22518();
        Room r3 = new Room_ec22612();
        Room r4 = new Room_ec22986();
        Room r5 = new Room_ec22882();
        Room r6 = new Room_ec22598();
        
        rooms = new Room[][]{{r1, r2}, {r3, r4}, {r5, r6}};
    }
    
    public Direction visit(Visitor v, Direction directionVistorArrivesFrom) {
        int row, col;
        
        v.tell("You are currently located in the garden.");
        char choice = v.getChoice("Do you want to enter the house from west (a) or from the east (b)? If you do not want to enter the house type N.", new char[] {'a', 'b', 'N'});
        
        while(choice != 'N'){
            
            if(choice == 'a'){
                col = 0;
                row = 1;
                d = Direction.FROM_WEST;
            }
            else{
                col = 1;
                row = 1;
                d = Direction.FROM_EAST;
            }
            
            while(row<rooms.length && row<0 && col<rooms[0].length && col<0){
                d = rooms[row][col].visit(v, d);
                
                if(d.toString().equals("heading North"))
                    row++;
                else if(d.toString().equals("heading South"))
                    row--;
                else if(d.toString().equals("heading West"))
                    col--;
                else
                    col++;
            }
            
            v.tell("You are in the garden again!");
            choice = v.getChoice("Do you want to enter the house from west (a) or from the east (b)? If you do not want to enter the house type N.", new char[] {'a', 'b', 'N'});
        }

        return d;
    }
}
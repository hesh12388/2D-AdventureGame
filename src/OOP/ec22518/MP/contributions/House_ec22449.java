package OOP.ec22518.MP.contributions;

class House_ec22449 implements Visitable {
    Room[] myRooms;
    
    
    House_ec22449(){
        myRooms = new Room[3];
        myRooms[0] = new Room_ec221247();
        myRooms[1]= new Room_ec22413();;
        myRooms[2] = new Room_ec22449();
    }
    public Direction visit(Visitor visitor1, Direction direction1){
        int currentRoomNumber = 0;
        Room roomInside = myRooms[currentRoomNumber];
        Direction finalDirectionGo = myRooms[0].visit(visitor1,direction1);
        boolean hasExited = false;
        
        while (!hasExited){
            if (currentRoomNumber == 0){
                if (direction1 == Direction.TO_WEST){
                    finalDirectionGo = myRooms[1].visit(visitor1,direction1);
                    currentRoomNumber = 1;
                }
                else if (direction1 == Direction.TO_EAST){
                    finalDirectionGo = myRooms[2].visit(visitor1,direction1);
                    currentRoomNumber = 2;
                }
                else{
                    visitor1.tell("Don't you dare, you can't go this way");
                }
            }
            
            else if (currentRoomNumber == 1){
                if (direction1 == Direction.TO_WEST){
                    finalDirectionGo = myRooms[0].visit(visitor1,direction1);
                    currentRoomNumber = 0;
                    
                }
                else if (direction1 == Direction.TO_EAST){
                    hasExited = true;
                    return finalDirectionGo.TO_EAST;
                }
                else{
                    visitor1.tell("Don't you dare, you can't go this way");
                }
            }
                
            else if (currentRoomNumber == 3){
                if (direction1 == Direction.TO_WEST){
                    hasExited = true;
                    return finalDirectionGo.TO_WEST; 
                }
                else if (direction1 == Direction.TO_EAST){
                    finalDirectionGo = myRooms[0].visit(visitor1,direction1);
                    currentRoomNumber = 0;
                    
                }
                else{
                    visitor1.tell("Don't you dare, you can't go this way");
                }
            }
            }
            return finalDirectionGo;  
            
        }
}



package OOP.ec22518.MP.contributions;

class House_ec22711 implements Visitable {
    int x = 1;
    int y = 0;
    
    int maxX = 2;
    int maxY = 1;
    
    int minX = 0;
    int minY = 0;
    
    Room[][] rooms;
    boolean hasLeftHouse;
    
    Direction nextDir = Direction.TO_NORTH;
    
    House_ec22711 ()
    {
        rooms = new Room[][]{ {new Room_ec22711(), new Room_ex20181(), new Room_ec22447()}, {new Room_ec22432(), new Room_ec22435(), new Room_ec22836()}};
        hasLeftHouse = false;
    }
    
    public Direction visit(Visitor v, Direction d) 
    {           
        nextDir = rooms[y][x].visit(v, nextDir);
        evaluateDirection(nextDir);
        
        while(!hasLeftHouse) 
        {            
            //Determine whether player has left the house
            hasLeftHouse = coordinateEval(v);
            
            if(!hasLeftHouse) 
            {
                nextDir = rooms[y][x].visit(v, nextDir);
                evaluateDirection(nextDir);
            }
        }
        
        System.out.println("You found an exit to the house!");
        return nextDir;
    }
    
    public boolean coordinateEval (Visitor v) 
    {
        //Coordinate Conditions: What happens if the user reaches certain areas of the house.
        if(x==1 && y==-1) 
        {
            return true;
        }
        else if(x < minX) 
        {
            System.out.println("You have found a magic door that leads to the room at the other end of this house!");
            v.giveItem(new Item("Key"));
            v.giveGold(1);
            x = maxX;
            return false;
        }
        else if(x > maxX) 
        {
            System.out.println("You have found a magic door that leads to the room at the other end of this house!");
            v.giveItem(new Item("Key"));
            x = minX;
            return false;
        }
        else if(y<minY) 
        {
            System.out.println("You have found a magic door that leads to the room at the other end of this house!");
            y = maxY;
            return false;
        }
        else if(y>maxY)
        {
            System.out.println("You have found a magic door that leads to the room at the other end of this house!");
            y = minY;
            return false;
        }
        else {
            System.out.println("You proceed to the next room.");
            return false;
        }
    }
    
    public void evaluateDirection (Direction newDir) 
    {
        if(newDir == Direction.TO_NORTH) {y++;}
        else if(newDir == Direction.TO_SOUTH) {y--;}
        else if(newDir == Direction.TO_EAST) {x++;}
        else {x--;}
    }
       
}

package OOP.ec22518.MP.contributions;

public class House_ec22752 implements Visitable {

    Room[][] rooms = new Room[2][2];
    public House_ec22752(){
        rooms[0][0] = new Room_ec22825();
        rooms[0][1] = new Room_ec22406();
        rooms[1][0] = new Room_ec19389();
        rooms[1][1] = new Room_ec22752();
    }

    public Direction visit(Visitor visitor, Direction direction){
        int indexX = 0;
        int indexY = 0;
        while(true){
            direction = rooms[indexX][indexY].visit(visitor,direction);
            if(indexX == 0 && indexY == 0){
                if(direction == Direction.TO_EAST || direction == Direction.TO_WEST){
                    indexY = 1;
                }
                else if(direction == Direction.TO_NORTH || direction == Direction.TO_SOUTH){
                    indexX = 1;
                }
            }
            else if(indexX == 0 && indexY == 1){
                if(direction == Direction.TO_EAST || direction == Direction.TO_WEST){
                    indexY = 0;
                }
                else if(direction == Direction.TO_NORTH || direction == Direction.TO_SOUTH){
                    indexX = 1;
                }
            }
            else if(indexX == 1 && indexY == 0){
                if(direction == Direction.TO_EAST || direction == Direction.TO_WEST){
                    indexY = 1;
                }
                else if(direction == Direction.TO_NORTH || direction == Direction.TO_SOUTH){
                    indexX = 0;
                }
            }
            else if(indexX == 1 && indexY == 1){
                if(direction == Direction.TO_WEST){
                    indexY = 0;
                }
                else if(direction == Direction.TO_NORTH || direction == Direction.TO_SOUTH){
                    indexX = 0;
                }
                else if(direction == Direction.TO_EAST){
                    break;
                }
            }
        }
        return direction;
    }
}

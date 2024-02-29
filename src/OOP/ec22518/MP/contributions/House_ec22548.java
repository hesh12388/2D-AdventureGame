package OOP.ec22518.MP.contributions;

import java.util.Random;

class House_ec22548 implements Visitable {
    Room[][] myRooms = { {new Room_ec22548()}, {new Room_ec22955(), new Room_ec22413()}, {new Room_ec22430(), new Room_ec22720()}};
    
    public Direction visit(Visitor visitor, Direction direction) {
        int xAxis = 0;
        int yAxis = 0;
        
        int count = 0;
        while (true) {
            count += 1;
            
            if (count==1) {
                direction = myRooms[xAxis][yAxis].visit(visitor, direction);
            }
            
            if (direction==Direction.FROM_NORTH) {
                xAxis-=1;
                try {
                    direction = myRooms[xAxis][yAxis].visit(visitor, direction);
                }
                catch (ArrayIndexOutOfBoundsException exception) {
                    xAxis=0;
                    direction = myRooms[xAxis][yAxis].visit(visitor, direction);
                }
            }
            else if (direction==Direction.FROM_SOUTH) {
                xAxis+=1;
                try {
                    direction = myRooms[xAxis][yAxis].visit(visitor, direction);
                }
                catch (ArrayIndexOutOfBoundsException exception) {
                    xAxis=myRooms.length-1;
                    direction = myRooms[xAxis][yAxis].visit(visitor, direction);
                }
            }
            else if (direction==Direction.FROM_EAST) {
                yAxis+=1;
                try {
                    direction = myRooms[xAxis][yAxis].visit(visitor, direction);
                }
                catch (ArrayIndexOutOfBoundsException exception) {
                    yAxis=myRooms[xAxis].length-1;
                    direction = myRooms[xAxis][yAxis].visit(visitor, direction);
                }
            }
            else if (direction==Direction.FROM_WEST) {
                yAxis-=1;
                try {
                    direction = myRooms[xAxis][yAxis].visit(visitor, direction);
                }
                catch (ArrayIndexOutOfBoundsException exception) {
                    yAxis=0;
                    direction = myRooms[xAxis][yAxis].visit(visitor, direction);
                }
            }
            
            Random random = new Random();
            if (random.nextInt(50)==49 || count == 50) {
                visitor.tell("Congrats you managed to survive ^^");
                break;
            } else {
                visitor.tell("You continue deeper into the maze...");
            }
        }

        return direction;
    }
}

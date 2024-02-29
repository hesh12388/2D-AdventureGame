package OOP.ec22518.MP.contributions;

import java.util.Random;

class House_ec22549 implements Visitable {
    
    private int row = 1;
    private int column = 1;
    
    public Direction visit(Visitor v, Direction d) {
        
        // Random 9 rooms.
        // You leave the house by ending up outside the 2D array (when the row/column variable is bigger than 2 or smaller than 0).
        
        Direction newDirection = d;
        
        Room[][] roomsArray = {
            {new Room_ec22549(), new Room_ec22852(), new Room_ec22617()},
            {new Room_ec22621(), new Room_ec22675(), new Room_ec22726()},
            {new Room_ec22752(), new Room_ec22597(), new Room_ec22995()}
        };
        
        // All direction returns seem to be going north or south so to make this interesting I am going to randomise direction returns.
        
        boolean insideHouse = true;
        
        while (insideHouse) {
            roomsArray[row][column].visit(v, d);
            newDirection = calculateNewDirection();
            
            if ("heading North".equals(newDirection.toString())) {
                row--;
            }
            else if ("heading East".equals(newDirection.toString())) {
                column++;
            }
            else if ("heading South".equals(newDirection.toString())) {
                row++;
            }
            else {
                column--;
            }
            
            // Check if outside the "house" (array) index range.
            if (row < 0 || row >= roomsArray.length || column < 0 || column >= roomsArray.length) {
                insideHouse = false;
            }
            
        }
        
        return newDirection;
    }
    
    Direction calculateNewDirection() {
        Direction[] directionsList = {Direction.TO_NORTH, Direction.TO_EAST, Direction.TO_SOUTH, Direction.TO_WEST};
        return directionsList[(new Random()).nextInt(4)];
    }
    
}
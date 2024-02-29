package OOP.ec22518.MP.contributions;

public class House_ec22517 implements Visitable {
    Room[][] house = {{new Room_ec21403(), new Room_ec22406()},
                      {new Room_ec22409(), new Room_ec21645()},
                      {new Room_ec21413(), new Room_ec22517()}};
    
    int currentX;
    int currentY;
    boolean leaving;
    Direction direction;

    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        leaving = false;

        if (directionVistorArrivesFrom.toString().equals(Direction.FROM_NORTH.toString())) {
            currentX = 1;
            currentY = 0;
        } else if (directionVistorArrivesFrom.toString().equals(Direction.FROM_EAST.toString())) {
            currentX = house[0].length - 1;
            currentY = 0;
        } else if (directionVistorArrivesFrom.toString().equals(Direction.FROM_SOUTH.toString())) {
            currentX = 0;
            currentY = house.length - 1;
        } else {
            currentX = 0;
            currentY = 1;
        }

        while (!leaving) {
            direction = house[currentY][currentX].visit(visitor, directionVistorArrivesFrom);
            
            if (direction.toString().equals(Direction.FROM_NORTH.toString())) {
                currentY += 1;
            } else if (direction.toString().equals(Direction.FROM_EAST.toString())) {
                currentX -= 1;
            } else if (direction.toString().equals(Direction.FROM_SOUTH.toString())) {
                currentY -= 1;
            } else {
                currentX += 1;
            }

            if (currentX >= house[0].length || currentY >= house.length || currentX < 0 || currentY < 0) {
                leaving = true;
            }
        }

        return direction;
    }

    public static void main(String[] a) {
        House_ec22517 h = new House_ec22517();
        Visitor v = new IOVisitor(System.out, System.in);
        h.visit(v, Direction.FROM_WEST);        
    }
}

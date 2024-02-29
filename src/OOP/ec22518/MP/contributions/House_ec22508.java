package OOP.ec22518.MP.contributions;

import java.util.Random;

class House_ec22508 implements Visitable {

    Room[][] house;
    int[] start;
    int[] end;
    int[] current;

    class Hallway extends Room {
        @Override
        public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
            return Direction.TO_NORTH;
        }
    }


    public House_ec22508() {
        Room_ec22508 roomEc22508 = new Room_ec22508();
        Room_ec22507 roomEc22507 = new Room_ec22507();
        Room_ec22510 roomEc22510 = new Room_ec22510();

        Hallway hallway = new Hallway();

        house = new Room[][]{
                {roomEc22507, hallway, roomEc22507},
                {roomEc22508, hallway, roomEc22508},
                {roomEc22510, hallway, roomEc22510}
        };

        start = new int[]{1, 2};
        end = new int[]{1, 0};
        current = start;
    }

    @Override
    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        Direction direction = Direction.TO_NORTH;
        Random random = new Random();

        /*
            current[0][0]     current[0][1]     current[0][2]
            current[1][0]     current[1][1]     current[1][2]
            current[2][0]     current[2][1]     current[2][2]
         */



        if(current[1] == 1) {
            int n = random.nextInt(2);
            if(n == 1) {
                direction = Direction.TO_NORTH;
            }
            if(n == 0){
                direction = Direction.TO_WEST;
            } else {
                direction = Direction.TO_EAST;
            }
        }

        if(current[1] == 0) {
            int n = random.nextInt(1);
            if(n == 1) {
                direction = Direction.TO_NORTH;
            } else {
                direction = Direction.TO_WEST;
            }
        }
        if (current[1] == 2) {
            int n = random.nextInt(1);
            if(n == 1) {
                direction = Direction.TO_NORTH;
            } else {
                direction = Direction.TO_EAST;
            }
        }

        return direction;
    }
}
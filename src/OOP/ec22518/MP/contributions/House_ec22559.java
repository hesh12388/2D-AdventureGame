package OOP.ec22518.MP.contributions;

class House_ec22559 implements Visitable {
    Room room1;
    Room room2;
    Room room3;
    
    House_ec22559(){
        room1 = new Room_ec22559();
        room2 = new Room_ec22414();
        room3 = new Room_ec22558();
    }
    
    public Direction visit(Visitor v, Direction d) {
        v.tell("Welcome to my house!");

        
        Direction ffDirection = room1.visit(v, d);
        while(!ffDirection.equals(Direction.TO_SOUTH)){
            if(ffDirection.equals(Direction.TO_WEST)){
                ffDirection = room2.visit(v, Direction.FROM_EAST);
            }

            if(ffDirection.equals(Direction.TO_WEST)){
                ffDirection = room3.visit(v, Direction.FROM_WEST);
            }

            if(ffDirection.equals(Direction.TO_NORTH)){
                ffDirection = room1.visit(v, Direction.FROM_SOUTH);
            }
        }

        return Direction.TO_SOUTH;
        
    }
        
}
package OOP.ec22518.MP.contributions;

class House_ec211044 implements Visitable {
    private Room[] rooms;
    private final int NUM = 3;

    House_ec211044(Visitor V, Direction D){
        rooms = new Room[NUM];
        rooms[0] = new Room_ec211044();
        rooms[1] = new Room_jpp314171();
        rooms[2] = new Room_ec211030();
    }

    public Direction visit(Visitor V, Direction D){
        int index = 0;
        Room current = rooms[index];
        Direction direction = current.visit(V,Direction.TO_EAST);
        boolean leave = false;

        while(!leave){
            if(direction == Direction.TO_NORTH){
                V.tell("No room on the north side");
            }

            else if(direction == Direction.TO_SOUTH){
                V.tell("You left the house");
                leave = true;
            }

            else if(direction == Direction.TO_WEST){
                if(current == rooms[0]){
                    V.tell("You can't go west");
                }
                else{
                    index = index - 1;
                    current = rooms[index];
                }
            }

            else if(direction == Direction.TO_EAST){
                if(current == rooms[2]){
                    V.tell("You can't go east");
                }
                else{
                    index = index + 1;
                    current = rooms[index];
                } 
            }

            direction = current.visit(V,direction);
        }
        return Direction.opposite(D);
    }
}

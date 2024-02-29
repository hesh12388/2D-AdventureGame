package OOP.ec22518.MP.contributions;

class House_ec22758 implements Visitable {

    Room[] rooms = {new Room_ec22761(),new Room_ec22634(),new Room_ec22763()};

    public Direction visit(Visitor v, Direction d) 
    {

        char[] option = {'1', '2'};
        int choice = v.getChoice("Type 1 to explore or 2 to leave:", option);

        while (choice != 2) {  

            if (d == Direction.FROM_SOUTH) {
                d = rooms[0].visit(v, d);
                choice = v.getChoice("Type 1 to explore or 2 to leave:", option);
            } 

            else if (d == Direction.FROM_WEST) {
                d = rooms[1].visit(v, d);
                choice = v.getChoice("Type 1 to explore or 2 to leave:", option);
            } 

            else if (d == Direction.FROM_NORTH) {
                d = rooms[2].visit(v, d);
                choice = v.getChoice("Type 1 to explore or 2 to leave:", option);
            } 

            else if (d == Direction.FROM_EAST) {
                d = rooms[3].visit(v, d);
                choice = v.getChoice("Type 1 to explore or 2 to leave:", option);
            }
        }
        return d;
    }
}

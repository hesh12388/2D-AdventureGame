package OOP.ec22518.MP.contributions;

class House_ec22860 implements Visitable {
    //Array of rooms from Alicia, Maks, Gabrielle and Mine's Room_
    Room[] rooms = {new Room_ec22860(),new Room_ec22828(),new Room_ec22741(),new Room_ec22660()}; 
    public Direction visit(Visitor v, Direction d) {
      
        char[] options = {'1', '2'};
        int choice = v.getChoice("Type 1 to explore or 2 to leave:", options);
    
        //while user doesnt want to leave, rooms will be visited
        while (choice != 2) {  

            if (d == Direction.FROM_SOUTH) {
                d = rooms[0].visit(v, d);
                choice = v.getChoice("Type 1 to explore or 2 to leave:", options);
            } 

            else if (d == Direction.FROM_WEST) {
                d = rooms[1].visit(v, d);
                choice = v.getChoice("Type 1 to explore or 2 to leave:", options);
            } 

            else if (d == Direction.FROM_NORTH) {
                d = rooms[2].visit(v, d);
                choice = v.getChoice("Type 1 to explore or 2 to leave:", options);
            } 

            else if (d == Direction.FROM_EAST) {
                d = rooms[3].visit(v, d);
                choice = v.getChoice("Type 1 to explore or 2 to leave:", options);
            }
        }
        return d;
    }
}

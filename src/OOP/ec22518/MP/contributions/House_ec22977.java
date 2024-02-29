package OOP.ec22518.MP.contributions;

class House_ec22977 implements Visitable {
     boolean room1Visited = false;
     boolean room2Visited = false;
     boolean room3Visited = false;
     boolean room0Visited = false;
     Room[] rooms = {new Room_ec22899(),new Room_ec22977(),new Room_ec221148(),new Room_ec221024()};
     public Direction visit(Visitor v, Direction d) {

         char[] options = {'1', '2'};
         int choice = v.getChoice("Enter 1 to explore room. Enter 2 to exit house", options);

         while (choice != 2) {

             if (d == Direction.FROM_SOUTH) {
                 d = rooms[0].visit(v, d);
                 room0Visited = true;
                 
             }

             else if (d == Direction.FROM_WEST) {
                 d = rooms[1].visit(v, d);
                 room1Visited = true;

             }

             else if (d == Direction.FROM_NORTH) {
                 d = rooms[2].visit(v, d);
                 room2Visited = true;

             }

             else if (d == Direction.FROM_EAST) {
                 d = rooms[3].visit(v, d);
                 room3Visited = true;

             }
             choice = v.getChoice("Enter 1 to explore room. Enter 2 to exit house", options);
         }
         return d;
     }
}

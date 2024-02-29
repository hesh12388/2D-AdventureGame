package OOP.ec22518.MP.contributions;

import java.util.Random;

class House_ec22852 implements Visitable {

  private int r = 1;
  private int c = 1;
  Room [][] rooms;

  public House_ec22852(){
    this.rooms = new Room[][]{
            {new Room_ec22861(), new Room_ec22760(), new Room_ec22741()},
            {new Room_ec22825(), new Room_ec22852(), new Room_ec22433()},
            {new Room_ec22862(), new Room_ec22828(), new Room_ec22836()}
    };
  }

  public Direction visit (Visitor vis, Direction dir){
    Direction new_dir = dir;
    boolean end_visit = false;

    while (!end_visit){
      //new_dir =
      this.rooms[r][c].visit(vis, dir);

      // Rooms return opposite directions so visitor just moves back and forth between same rooms, so I am choosing random directions instead
      new_dir = getRandomDirection();

      // Allow visitor to leave house between each room
      if (vis.getChoice("Would you like to (a) carry on exploring or (b) leave the house", new char[] {'a', 'b'}) == ('b')) end_visit=true;
      
      
      // Switch Case causes error in jenkins...
      if (new_dir.toString().equals("heading North")) {
          r--;
      } else if (new_dir.toString().equals("heading East")) {
          c++;
      } else if (new_dir.toString().equals("heading South")) {
          r++;
      } else {
          c--;
      }

      // If the new room position is out of bounds, wrap around
      wrapRC();

    }

    return new_dir;
  }

  private void wrapRC(){
    // Assuming a nxn matrix
    if (r == -1) r = this.rooms.length;
    if (r == 3) r = 0;
    if (c == -1) c = this.rooms.length;
    if (c == 3) c = 0;
  }

  private Direction getRandomDirection(){
    Direction[] directionsList = {Direction.TO_NORTH, Direction.TO_EAST, Direction.TO_SOUTH, Direction.TO_WEST};
    return directionsList[(new Random()).nextInt(4)];
  }

}

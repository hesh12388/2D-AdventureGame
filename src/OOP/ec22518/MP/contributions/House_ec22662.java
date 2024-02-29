package OOP.ec22518.MP.contributions;

class House_ec22662 implements Visitable {
  
  public static void main(String[] args) {
    House_ec22662 ec22662= new House_ec22662();
    Visitor visitor = new IOVisitor(System.out,System.in);
    
    ec22662.visit(visitor,Direction.FROM_SOUTH);
  }
  
  //inspired by other pull requests
  private Room[] allRooms;
  private Room current;
  private final int NUMBER_OF_ROOMS = 3;

  House_ec22662() {
    allRooms = new Room[NUMBER_OF_ROOMS];
    allRooms[0] = new Room_ec221006();
    allRooms[1] = new Room_ec22662();
    allRooms[2] = new Room_ah21407();
  }
  
  public Direction visit(Visitor V, Direction D) {
    current =  allRooms[0]; 
    D = current.visit(V, D);

    V.tell("Trick Question! There is only one way out of this room.");
    
    current =  allRooms[1];
    D = current.visit(V, D);

    current =  allRooms[2]; 
    D = current.visit(V, D);
    
    V.tell("You have visited all the rooms. The exit is in front of you.");
    V.tell("You have left the house.");
    
    return D;
  }  
}

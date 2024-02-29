package OOP.ec22518.MP.contributions;

class House_ec22439 implements Visitable {
    Room roomN;
    Room roomE;
    Room roomS;
    
  public House_ec22439() {
    Room roomN = new Room_ec22439();
    Room roomE = new Room_ec221002();
    Room roomS = new Room_ec22704();
  }
  
  public Direction visit(Visitor x, Direction d) {
    
    x.tell("Welcome to Krish's House!");
    
    Direction opposite = null;
    
    if (d == Direction.FROM_NORTH) {
      
      opposite = roomN.visit(x,d);
      return opposite;
      
    } else if (d == Direction.FROM_EAST) {
      
      opposite = roomE.visit(x,d);
      return opposite;
      
    } else {
      
      opposite = roomS.visit(x,d);
      return opposite;
      
    }
  }
}

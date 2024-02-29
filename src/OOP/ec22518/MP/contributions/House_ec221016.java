package OOP.ec22518.MP.contributions;

class House_ec221016 implements Visitable {
    Room roomN;
    Room roomE;
    Room roomS;
    
  public House_ec221016() {
    Room roomN = new Room_ec221016();
    Room roomE = new Room_ec22791();
    Room roomS = new Room_ec22548();
  }
  
  public Direction visit(Visitor x, Direction d) {
    
    x.tell("Welcome to Hassan's House!");
    
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

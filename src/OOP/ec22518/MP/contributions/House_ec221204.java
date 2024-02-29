package OOP.ec22518.MP.contributions;

class House_ec221204 implements Visitable {

    Room_ec22962 e = new Room_ec22962();
    Room_ec22948 s = new Room_ec22948();
    Room_ec221204 b = new Room_ec221204();
    Room_ec22883 a = new Room_ec22883();
    Room_ec22990 m = new Room_ec22990();





    public House_ec221204() {
        a = new Room_ec22883();
        m = new Room_ec22990();
        s = new Room_ec22948();
        b = new Room_ec221204();
        e = new Room_ec22962();
    }



    public static void main(String[] args) {
        House_ec221204 hou = new House_ec221204();
        Visitor visitor = new IOVisitor(System.out,System.in);
        
        Direction d = hou.visit(visitor, Direction.FROM_SOUTH);
    }







    public Direction visit(Visitor visitor, Direction arrivedFrom) {
        if(arrivedFrom == Direction.FROM_EAST) { 
            return Direction.TO_NORTH; 
            }

        

        Direction visitorComing = m.visit(visitor, arrivedFrom);
  

        
        if(visitorComing == Direction.FROM_EAST || visitorComing == Direction.FROM_NORTH || visitorComing == Direction.FROM_SOUTH) {
            System.out.println("no roooms left, you are exiting the house");
            return Direction.TO_NORTH;
        }

        
        visitorComing = e.visit(visitor, visitorComing);
        visitorComing = s.visit(visitor, visitorComing);








        if(visitorComing == Direction.FROM_SOUTH) {
            visitorComing = a.visit(visitor, visitorComing);
            visitorComing = s.visit(visitor, visitorComing);
   

        } else if(visitorComing == Direction.FROM_NORTH) {
            visitorComing = b.visit(visitor, visitorComing);
            visitorComing = s.visit(visitor, visitorComing);

        } else if(visitorComing == Direction.FROM_WEST) {
            return Direction.TO_EAST;

        } else if(visitorComing== Direction.FROM_EAST) {
            visitorComing = e.visit(visitor, visitorComing);
            visitorComing = s.visit(visitor, visitorComing);

        }

        return Direction.TO_EAST;
    }







}
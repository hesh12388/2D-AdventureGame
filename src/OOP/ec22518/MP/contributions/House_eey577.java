package OOP.ec22518.MP.contributions;

class House_eey577 implements Visitable {
    
    Room r1;
    Room r2;
    
    House_eey577(){
        r1 = new Room_ec22425();
        r2 = new Room_ec22425();
    }
    
    public Direction visit( // Returns direction the visitor leaves towards.
        Visitor v,
        Direction d) {
        v.tell("You have enter the House! the actiona choices are A B C and the leaving choices are A=N B=E C=S D=W for the diffetent directions");
        
        int loc = 1;
        
        while(loc != 0) {
            if (loc == 1) {
                 d = r1.visit(v,d);
                 if (d == Direction.TO_NORTH) {
                     v.tell("You are going to Room 2");
                     loc = 2;
                 }
            }
            else if (loc == 2) {
                 d = r2.visit(v,d);
                 if (d == Direction.TO_SOUTH) {
                     v.tell("You are going to Room 1");
                     loc = 1;
                 }
                 else if (d == Direction.TO_EAST) {
                     
                     loc = 0;
                 }
            }
            
        }
        
         v.tell("You have are leaving the House!");
         return d;
    }
    
    public static void main(String[] args) {
        House_eey577 h = new House_eey577();
        Visitor guy = new IOVisitor(System.out,System.in);
        h.visit(guy,Direction.FROM_SOUTH);
    }
}
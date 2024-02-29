package OOP.ec22518.MP.contributions;

class House_ec22712 implements Visitable {
    Room r1;
    Room r2;
    
    House_ec22712() 
    {
        r1 = new Room_ex21247();
        r2 = new Room_ec22425();
    }
    
    public Direction visit(Visitor v, Direction d) 
    {
        v.tell("Welcome to the House!");
        int loc = 1;
        
        while(loc != 0){
        
            if (loc == 1){
                d = r1.visit(v,d);}
            if(v == Direction.TO_EAST){
            loc = 2;
            }
            
            if (loc == 2){
                d = r2.visit(v,d);}
                if(v == Direction.TO_WEST){
                loc = 1;
                }
                
                
            else if (v == Direction.TO_SOUTH) {
            loc = 1;
            }
        }
        
        v.tell("You are leaving the House, BYE!");
        return d;
    }
    
    public static void main(String[] args)
    {
        House_ec22712 h = new House_ec22712();
        Visitor person = new IOVisitor(System.out,System.in);
        h.visit(person,Direction.FROM_NORTH);
    
    }
}
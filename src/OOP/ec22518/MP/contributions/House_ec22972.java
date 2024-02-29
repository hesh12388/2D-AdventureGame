package OOP.ec22518.MP.contributions;

class House_ec22972 implements Visitable {
    Room r1;
    Room r2;
    
    House_ec22972(){
        r1 = new Room_ex21247();
        r2 = new Room_ec22632();
    }
    
    public Direction visit(Visitor v, Direction d){
        v.tell("You have entered the house!");
        
        int loc = 1;
        
        while(loc != 0){
            if(loc == 1){
                d = r1.visit(v, d);
                if(d == Direction.TO_NORTH){
                    v.tell("You are going to room 2");
                    loc = 2;
                }
            }
            else if(loc == 2){
                d = r2.visit(v, d);
                if(d == Direction.TO_SOUTH){
                    v.tell("You are going to room 1");
                    loc = 1;
                }
                else if(d == Direction.TO_EAST){
                    loc = 0;
                }
            }
        }
        v.tell("You are leaving the House!");
        return d;
    }
    
    public static void main(String[] args) {
        House_ec22972 h = new House_ec22972();
        Visitor guy = new IOVisitor(System.out,System.in);
        h.visit(guy,Direction.FROM_SOUTH);
    }
}
package OOP.ec22518.MP.contributions;

class House_ec22824 implements Visitable {
    
//     static class MyVisitor implements Visitor{
//         int loc;
//         Visitor V;
        
        // MyVisitor(Visitor mv, int l){
        //     v = mv;
        //     loc = l;
        // }
        
//     }
    
    Room r1 = new Room_ec22824();
    Room r2 = new Room_ec22859();//from r1 west out to r2 east, ending at west
    Room r3 = new Room_ec22890();//from r1 north out to r3 south, ending at north

    public Direction visit(Visitor v, Direction dir) {
        v.tell("You have enter the House! ");
        int loc = 0;
        while(loc != 0){
            if(loc == 0){
                dir = r1.visit(v,dir);
                if(dir == Direction.TO_EAST){
                    loc = 1;
                    v.tell("You are going to Room 2");
                }
                else if(dir == Direction.TO_NORTH){
                    loc = 2;
                    v.tell("You are going to Room 3");
                }
            }
            if(loc == 1){
                dir = r2.visit(v,dir);
                if(dir == Direction.TO_WEST){
                    loc = 0;
                    v.tell("Here is the end of house");
                }
            }
            if(loc == 2){
                dir = r3.visit(v,dir);
                if(dir == Direction.TO_NORTH){
                    loc = 0;
                    v.tell("Here is the end of house");
                }
            }
        }
        v.tell("You have are leaving the House!");
        return dir;
    }
    

}

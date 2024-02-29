package OOP.ec22518.MP.contributions;

class House_ec22602 implements Visitable {
    
    Room x;
    Room y;
    Room z;
    
    public Direction visit(Visitor v, Direction d) {
        
        Direction finalDirection = x.visit(v,d);
        
        if (d == Direction.TO_NORTH){

            finalDirection = x.visit(v,d);
        }
        else if(d == Direction.TO_EAST){
            finalDirection = y.visit(v,d);
        }
        else if (d == Direction.TO_WEST){
            finalDirection = z.visit(v,d);
        }
        else{
            v.tell("Go South");
        }

        return finalDirection;
        
    }
    
    House_ec22602(){
        this.x = new Room_ec22602();
        this.y = new Room_ec22450();
        this.z = new Room_ec22586();
    }
    
}

package OOP.ec22518.MP.contributions;

class House_ec22889 implements Visitable {
    Room_ec22889 room1;
    Room_ec22858 room2;
    Room_ec22578 room3;
    Room_ec22919 room4;
    final String jason = "JASON";
    final String harris = "HARRIS";
    final String miguel = "MIGUEL";
    final String on = "ON";
    String current;
    boolean locked;
    
    public House_ec22889(){
            room1 = new Room_ec22889(); //myself
            room2 = new Room_ec22858();  //Miguel
            room3 = new Room_ec22578(); // Harris
            room4 = new Room_ec22919(); // Jason
            locked = room1.locked;
            current = jason; // always start out at Jason's room
    }
    
    public Direction visit(Visitor v, Direction d){
        while(locked){
            d = visitRoom(v,d);
            decideRoom(d);
            locked=isLocked();
        }
        
        System.out.println("You finally made it out of the house!");
        return d;
    }
    
    public Direction visitRoom(Visitor v, Direction d){
        if(current.equals(on)){
                d = room1.visit(v,d);
            }
               
        else if(current.equals(miguel)){
            d = room2.visit(v,d);
        }
            
        else if(current.equals(harris)){
            d = room3.visit(v,d);
        }
            
        else{
            d = room4.visit(v,d);
        }
        
        return d;
    }
    
    public boolean isLocked(){
        return room1.locked;
    }
        
    public void decideRoom(Direction d){
        if(current.equals(jason)){
            if(d == Direction.TO_NORTH){
                current = on;
            }
            
            else if(d == Direction.TO_WEST){
                current = harris;
            }
            
            else if(d == Direction.TO_EAST){
                current = miguel;
            }
            
            else{
                System.out.println("You ended up in the same room");
            }
        }
        
        else if(current.equals(harris)){
            if(d == Direction.TO_WEST){
                System.out.println("You somehow travelled to the opposite side of the house");
                current = miguel;
            }
            
            else if(d == Direction.TO_EAST){
                current = jason;
            }
            
            else{
                System.out.println("You ended up in the same room");
            }
            
        }
        
        else if(current.equals(miguel)){
            if(d == Direction.TO_EAST){
                System.out.println("You somehow travelled to the opposite side of the house");
                current = harris;
            }
            
            else if(d == Direction.TO_WEST){
                current = jason;
            }
            
            else{
                System.out.println("You ended up in the same room");
            }
        }
        
        else{
            if(d == Direction.TO_NORTH){
                current = on;
            }
            
            else{
                current = jason;
            }
        }
    }
}
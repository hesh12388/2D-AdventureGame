package OOP.ec22518.MP.contributions;

class House_ec22472 implements Visitable {
    
    public Direction visit(Visitor v, Direction d){ // ran when house is visited
        outHouse(v);
        return d;
    }

    private void outHouse(Visitor v){
        v.tell("You are now in the garden of the house, the only viable way in to the house is through the front entrance.");
        v.tell("You approach the front door, it doesn't seem to be locked, so you enter");
        ec22472(v, Direction.FROM_NORTH);
    }
    
    private void ec22472(Visitor v, Direction dIn){
        Room_ec22472 room1 = new Room_ec22472();
        Direction dOut = room1.visit(v, dIn);
        if(dOut == Direction.TO_SOUTH) 
        {
            ec22902(v, dOut);
        }
        else 
        {
            outHouse(v);
        }
     }
    
    private void ec22902(Visitor v, Direction dIn){
        Room_ec22902 room2 = new Room_ec22902();
        Direction dOut = room2.visit(v, dIn);
        ec22678(v, dOut);
     }
    
    private void ec22678(Visitor v, Direction dIn){
        Room_ec22678 room3 = new Room_ec22678();
        Direction dOut = room3.visit(v, dIn);
        v.tell("You have completed the house");
        return;
    }
    
}
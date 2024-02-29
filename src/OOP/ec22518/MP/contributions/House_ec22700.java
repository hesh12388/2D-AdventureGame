package OOP.ec22518.MP.contributions;

class House_ec22700 implements Visitable {
    private Room room1;
    private Room room2;
    private Room room3;
    
    public House_ec22700(){
        this.room1 = new Room_ec221185();
        this.room2 = new Room_ec221166(){};
        this.room3 = new Room_ec221023(){};
    
    }
    
    public Direction visit(Visitor v, Direction d) {
        char[] direc = {1, 2, 3};
        
        v.tell("Pick a number between (1 ,2 ,3).");
        char go = v.getChoice("Pick wisely", direc);
        
        if(go == 1){
            v.tell("Terrible choice.");
            d = room1.visit(v,d);
        } else if (go == 2){
            v.tell("Good Choice.");
            d = room2.visit(v,d);
        } else if (go == 3){
            v.tell("Theres better options.");
            d = room3.visit(v,d);
        }
        
        return d;
    }
}
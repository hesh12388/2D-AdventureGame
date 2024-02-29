package OOP.ec22518.MP.contributions;

class House_ec20315 implements Visitable {
    private Room roomOne; private Room roomTwo; private Room roomThree;
    
    public Direction visit(Visitor v, Direction d) {
        boolean flag = false;
        char choiceSelected  = v.getChoice("Press (N) to go North, (E) East, (S) South, (W) West",new char[]{'N','E','S','W'});
        while(flag!=true){
            if(choiceSelected=='N'){
                d = roomTwo.visit(v, d);//Visit room 21413
                this.visit(v, d);
                flag=true;
            }else if(choiceSelected=='E'){
                d = roomThree.visit(v, d); //Visit room 221014
                this.visit(v, d);
                flag=true;
            }else if(choiceSelected=='S'){
                d = roomOne.visit(v, d); // Visit room 221012
                this.visit(v,d);
                flag=true;
            }else if(choiceSelected=='W'){
                System.out.println("Exit");
                flag=true;
            }else{
                System.out.println("Invalid input");
            }
        }
        return d;
    }
    public House_ec20315(){
        this.roomOne= new Room_ec22917(); 
        this.roomTwo= new Room_ec22937(); 
        this.roomThree= new Room_ec22884();
    }
}

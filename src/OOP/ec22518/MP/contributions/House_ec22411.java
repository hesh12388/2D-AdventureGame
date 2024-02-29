package OOP.ec22518.MP.contributions;

class House_ec22411 implements Visitable {
    Room r1, r2, r3;
    
    House_ec22411() {
        r1 = new Room_ec22411();
        r2 = new Room_ec22467();
        r3 = new Room_ec22429();
    }

    public Direction visit(Visitor v, Direction d) {
        Direction dir = d;
        char opt;
        char[] optArray = {'a','b'} ;
        boolean inr1,inr2,inr3,exit;
        inr1 =false;inr2=false;inr3=false;exit=false;
        inr1 = true; //starting room is room 1 so starts as true

        while (exit == false){
                    //room 1 - starting room
            if (inr1) {
                dir = r1.visit(v,dir);
        
                if (dir == Direction.TO_NORTH){ //north from room 1 leads outside
                    v.tell("The door leads you outside! You can run away! You could also stay in the house... but why would you do that?");
                    opt = v.getChoice("\t[a] Run away! TO FREEDOM!!!!\n\t[b] Stay in the house.", optArray);
                        
                    if(opt=='a'){
                        v.tell("You run as fast as you can until the house disappears from your view. You are safe. Right?");
                        exit = true; inr1=false;
                        dir = Direction.UNDEFINED;
                    }
                    else if(opt=='b'){
                        v.tell("You turned around and step back into the house. It seems you enjoy fearing for your life.");
                        dir = Direction.FROM_NORTH;
                    }
                }
                else if(dir == Direction.TO_EAST){ //to ec22467 room2 gift - West entrance
                    inr1 = false; inr2 = true;
                    dir = Direction.FROM_WEST;
                }
                else if(dir== Direction.TO_SOUTH){//to ec22429 room3 cristina - North entrance
                    inr1 = false; inr3 = true;
                    dir = Direction.FROM_NORTH;
                }
                else if(dir == Direction.TO_WEST){//to ec22429 room3 cristina - East entrance
                    inr1 = false; inr3 = true;
                    dir = Direction.FROM_EAST;
                }
            }      

            //room 2 ec22467
            else if(inr2){
                dir = r2.visit(v, dir);

                if (dir == Direction.TO_NORTH) {//to ec22429 room3 cristina - South entrance
                    inr3=true;
                    dir = Direction.FROM_SOUTH;
                }
                else if(dir == Direction.TO_EAST){//to ec22429 room3 cristina - West entrance 
                    inr3=true;
                    dir = Direction.FROM_WEST;
                }
                else if(dir == Direction.TO_SOUTH){//to ec22411 room1 me - North entrance
                    inr1=true;
                    dir = Direction.FROM_NORTH;
                }
                else if(dir == Direction.TO_WEST){//to ec22411 room1 me - East entrance
                    inr1=true;
                    dir = Direction.FROM_EAST;
                }
                inr2=false; // leaving room 2
            }                    

            else if(inr3){
                if (dir == Direction.TO_NORTH) {//to ec22411 room1 me - South entrance
                    inr1=true;
                    dir = Direction.FROM_SOUTH;
                }
                else if(dir == Direction.TO_EAST){//to ec22411 room1 me - West entrance
                    inr1=true;
                    dir = Direction.FROM_WEST;
                }
                else if(dir == Direction.TO_SOUTH){//to ec22467 room2 gift - North entrance
                    inr2=true;
                    dir = Direction.FROM_NORTH;
                }
                else if(dir == Direction.TO_WEST){//to ec22467 room2 gift - East entrance
                    inr2=true;
                    dir = Direction.FROM_EAST;
                }
                inr3=false;//leaving room 3
            }
        }
        return dir; 
    }
}

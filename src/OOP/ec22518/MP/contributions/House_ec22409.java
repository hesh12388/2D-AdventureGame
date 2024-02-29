package OOP.ec22518.MP.contributions;

class House_ec22409 implements Visitable {
    Room[] rooms = new Room[3];
    int currentRoom;
    
    boolean escape;
    
    House_ec22409(){
        rooms[0] = new Room_ec221008(); //ROOM 0
        rooms[1] = new Room_ec22409(); //ROOM 1
        rooms[2] = new Room_ec21499(); //ROOM 2
        this.escape = false;
    }
    public Direction visit(Visitor v, Direction d) {
        d = StartChoice(v,d);
        while(!this.escape){
            pathChoice(v,d);
        }
        return d;
    }
    
    public Direction StartChoice(Visitor v,Direction d)
    {
        //This is to choose where you start
        if(d == Direction.FROM_NORTH){
            this.currentRoom = 0;
            d = rooms[0].visit(v,d);
        }
        else if(d == Direction.FROM_EAST){
            this.currentRoom = 1;
            d = rooms[1].visit(v,d);
        }
        else if(d == Direction.FROM_WEST){
            this.currentRoom = 2;
            d = rooms[2].visit(v,d);
        }
        else if(d == Direction.FROM_SOUTH){
            System.out.println("You go through a mysterious portal");
            this.currentRoom = 2;
            d = rooms[2].visit(v,d);
        }
        return d;
    }
    public Direction pathChoice(Visitor v,Direction d){
        if(this.currentRoom == 0){
            //This is if we are in room 0
            if (d == Direction.TO_EAST){
                d = hallway(v,d);
            }
            else if (d == Direction.TO_WEST){
                d = garden(v,d);
            }
            else if (d == Direction.TO_SOUTH){
                d = garden(v,d);
            }
        }
        else if(this.currentRoom == 1){
            //This is if we are in room 1
            if (d == Direction.FROM_NORTH){
                d = hallway(v,d);
            }
            else if (d == Direction.TO_WEST){
                 d = garden(v,d);
            }
        }
        //else == Room 2
        else{
            if (d == Direction.FROM_EAST){
                d = garden(v,d);
            }
            else if (d == Direction.FROM_NORTH){
                d = hallway(v,d);
            }
        }
        return d;
    }
    public Direction hallway(Visitor v,Direction d){
        v.tell("You enter a hallway with plently of cobwebs and an eary creaking");
        v.tell("You enter the next room");
        if(this.currentRoom == 1){
            d = garden(v,d);
        }
        else if(this.currentRoom == 2){
            d = garden(v,d);
        }
        return d;
    }
    public Direction garden(Visitor v, Direction d){
        v.tell("You enter the garden, the flowers are beautiful and the grass is well maintained");
        String decisions = ("Would you like to a) Continue to the next room or b) Jump the fence to escape!");
        char[] room_decisions = {'a', 'b'};
        if(v.getChoice(decisions, room_decisions) == 'a'){
            v.tell("You choose to continue to the next room");
            if (this.currentRoom == 0){
                decisions = ("Would you like to go to a) Room 1 b) Room 2");
                if(v.getChoice(decisions, room_decisions) == 'a'){
                    this.currentRoom = 1;
                    d = Direction.TO_EAST;
                    d = rooms[1].visit(v,d);
                }
                else{
                    this.currentRoom = 2;
                    d = Direction.FROM_WEST;
                    d = rooms[2].visit(v,d);
                }
            }
            else if (this.currentRoom == 1){
                 decisions = ("Would you like to go to a) Room 0 b) Room 2");
                if(v.getChoice(decisions, room_decisions) == 'a'){
                    this.currentRoom = 0;
                    d = Direction.TO_NORTH;
                    d = rooms[0].visit(v,d);
                }
                else{
                    this.currentRoom = 2;
                    d = Direction.FROM_WEST;
                    d = rooms[2].visit(v,d);
                }
            }
            else if (this.currentRoom == 2){
                decisions = ("Would you like to go to a) Room 0 b) Room 1");
                if(v.getChoice(decisions, room_decisions) == 'a'){
                    this.currentRoom = 0;
                    d = Direction.TO_NORTH;
                    d = rooms[0].visit(v,d);
                }
                else{
                    this.currentRoom = 1;
                    d = Direction.TO_EAST;
                    d = rooms[1].visit(v,d);
                }
            }
            
        }
        else{
            v.tell("You choose to escape!");
            this.escape = true;
        }
        return d;
    }
}

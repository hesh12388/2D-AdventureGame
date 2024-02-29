package OOP.ec22518.MP.contributions;

class House_ec22952 implements Visitable {
    
    Room r1; //ec22952 room, enters room to the West, leaves West of the room, ends up in room to the East of the corridor. 
    //Exits if they've visited room to the East already
    Room r2; //ec22510 room, enters to the East, leaves East of the room, ends up in room to the West of the corridor
    //Exits if they've visited room to the West already.
    Room r3;//ec22801 room, Enters room to the North, leaves from the North of the room and exits the house
    Room r4; //ec22475 room, Enters room to the South, Leaves from the South of the room and ends up to the room in the North of the house.
    
    boolean escaped = false;
    boolean visit_west = false;
    boolean visit_east = false;
    boolean visit_south = false;
    boolean peek = false;
    
    final String msg ="As you go to enter the door there is a message on the front of it. Before you can leave my house, you must explore at least one room. This is for your benefit. You may leave my house with something valuable. This house is magical, so expect the unexpected." ;
    
    House_ec22952(){
        r1 = new Room_ec22952();
        r2 = new Room_ec22510();
        r3 = new Room_ec22801();
        r4 = new Room_ec22475();
    }
    
    public Direction visit(Visitor v,Direction d){
        v.tell("As you enter the house, you are surrounded by four doors. The interior of the house looks neglected despite the well kept appearance of the exterior. ");
        v.tell("This intimidates you and you try to leave to the door behind you that you entered through. You open it and pause.");
        v.tell("As you opened the door you noticed that it no longer lead to the outside where you came from. It leads to a room.");
        v.tell("This can't be real can it? How could the outside world suddenly transform into a room?");
        v.tell("You close the door swiftly and are left in the corridor, with a door to your North, East, South and West.");
        
        char [] peek_choice = {'Y','N'};
        char peeking = v.getChoice("Before you proceed you may choose to peek into the remaining three rooms to wiegh your options. Will you? (Y)es, (N)o",peek_choice);
        
        switch(peeking){
            case 'Y':
                v.tell("You get a good look of the North, East and West rooms.");
                peek = true;
                break;
            case'N':
                v.tell("You decide not to take a look at any of the remaining rooms. You continue.");
        }
        
        char [] choices = {'N', 'S', 'E', 'W'};
        char option = v.getChoice("You musn't stay in this house. Choose a direction to go in order to escape: (N)orth, (S)outh, (E)ast, (W)est",choices);
        
        while(!escaped){
            switch(option){
                case 'N':
                    if ((peek == true) && (visit_south == true)){
                        v.tell("This is odd. If you went South and exited to the South, how could you end up in the room to the North?");
                    }
                    v.tell(msg);
                    d = r3.visit(v,d);
                    escaped = true;
                    break;
                case 'E':
                    v.tell(msg);
                    d = r2.visit(v,d);
                    visit_east = true;
                    if (visit_west == true){
                        escaped = true;
                    }
                    else{
                        v.tell(msg);
                        if (peek == true){
                            v.tell("This is odd. If you went East and exited to the East, how could you end up in the room to the West?");
                        }
                        d = r1.visit(v,d);
                        escaped = true;
                    }
                    break;
                case 'S':
                    v.tell(msg);
                    d = r4.visit(v,d);
                    visit_south = true;
                    break;
                case 'W':
                    v.tell(msg);
                    d = r1.visit(v,d);
                    visit_west = true;
                    if (visit_east == true){
                        escaped = true;
                    }
                    else{
                        v.tell(msg);
                        if (peek == true){
                            v.tell("This is odd. If you went West and exited to the West, how could you end up in the room to the East?");
                        }
                        d = r2.visit(v,d);
                        escaped = true;
                    }
                    break;
            }
        }
        
        v.tell("You go through the door to be met by the outside world. You're free!");
        
        return d;
    }
}
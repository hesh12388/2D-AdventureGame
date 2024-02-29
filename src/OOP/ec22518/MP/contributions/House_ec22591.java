package OOP.ec22518.MP.contributions;

class House_ec22591 implements Visitable {

    Room[][] rooms = {{new Room_ec22591(), new Room_ec21582(), new Room_ec22561()},
                     {new Room_ec211044(), new Room_ec21578(), new Room_ec22419()},
                     {new Room_ec22616(),new Room_ec22825(),new Room_ec22591()}};

    public Direction visit(Visitor v, Direction d) {
        v.tell("Welcome to my house");
        int xAxis = 0;
        int yAxis = 0;
        Direction dir = d;

        for (int i = 0 ; i < 100 ; i++){
            
            //System.out.println("xAxis: " + xAxis + "    yAxis: " + yAxis);
            //System.out.println("xAxis: " + (((xAxis %3)+3)%3) + "   yAxis: " + (((yAxis %3)+3)%3));
            // this is so that incase xaxis turns into negative it is corrected to the correct position on the map
            dir = rooms[(((xAxis %3)+3)%3)][(((yAxis %3)+3)%3)].visit(v, d);
            if (dir == Direction.TO_NORTH){xAxis+= -1;} 
            else if (dir == Direction.TO_SOUTH){xAxis += 1;}
            else if (dir == Direction.TO_EAST){yAxis += 1;}
            else if (dir == Direction.TO_WEST){yAxis += -1;}
        }

        return dir;
    }
}
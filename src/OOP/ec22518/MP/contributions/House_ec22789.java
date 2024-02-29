package OOP.ec22518.MP.contributions;

class House_ec22789 implements Visitable {
    static final Item Pen = new Item("Pen");
    private Room[][] houseRooms = new Room[2][2] ;
    private boolean leave = false;
    private int x=0,y=0;
    private char optionChoosen;

    //special room
      private class specialRoom extends Room{
       public Direction visit(Visitor visitor, Direction arrives) {
                visitor.tell("You are in the special room of this house");
                
                 if(visitor.hasEqualItem(Pen)){
                         visitor.tell("You have pen. Nice. Here is your reward.");
                         visitor.giveGold(3);
                 }
                  else{
                      visitor.tell("You dont have pen");
                      visitor.takeGold(-2);
                  }
                  
            return Direction.opposite(arrives);
        }
    }


    House_ec22789(){


        houseRooms[0][0] = new Room_ec221247();
        houseRooms[0][1] = new Room_ec221136();
        houseRooms[1][0] = new specialRoom();
        houseRooms[1][1] = new Room_ec221150();


    }


    public void houseLayout(Visitor v){
        for(int i=0;i<2;i++){
            for(int j=0; j<2; j++){
                if(i==1 && j==0){
                    System.out.print("    SPECIAL_ROOM        ");
                }
                else{
                    System.out.print(houseRooms[i][j] + "  ");
                }

            }
            v.tell("");
            v.tell("");
        }
    }


    public boolean leavingRoom(Visitor v, Direction d){
        v.tell("There is no room at "+ d + ". So, you are leaving the house ...");
        return true;
    }


    public void printCurrentRoom(Visitor v, Room room){
        v.tell("You are in " + room);
    }


    public Direction visit(Visitor v, Direction d) {
        v.tell( "This is a 4 room house with a special room. \n\n All rooms are connected. Every room has two door to exit from the house. The house layout >>> \n" );

        houseLayout(v);
        while(!leave){
            System.out.print(d.toString() + " you entered the house. And ");
            if(d == d.FROM_NORTH || d == d.FROM_WEST){
                printCurrentRoom(v, houseRooms[x][y]);
                d =houseRooms[x][y].visit(v,d);

                if(d == d.TO_WEST || d == d.TO_NORTH){
                    leave = leavingRoom(v,d);
                }
                else{
                    optionChoosen= v.getChoice("1.Go South \n" +
                            "2.Go East", new char[]{'1','2'});
                    if(optionChoosen == '1'){
                        x++;
                        printCurrentRoom(v, houseRooms[x][y]);
                        d =houseRooms[x][y].visit(v,d);
                    }
                    else{
                        y++;
                        printCurrentRoom(v, houseRooms[x][y]);
                        d =houseRooms[x][y].visit(v,d);
                    }

                }

            }


            else{
                x=1;
                y=1;
                printCurrentRoom(v, houseRooms[x][y]);
                d = houseRooms[x][y].visit(v,d);
                if(d == d.TO_EAST || d == d.TO_SOUTH){
                    leave = leavingRoom(v,d);
                }
                else{
                    optionChoosen= v.getChoice("1.Go North \n" +
                            "2.Go West", new char[]{'1','2'});
                    if(optionChoosen == '1'){
                        x--;
                        printCurrentRoom(v, houseRooms[x][y]);
                        d =houseRooms[x][y].visit(v,d);
                    }
                    else{
                        y--;
                        d =houseRooms[x][y].visit(v,d);
                    }
                }

            }

        }


        return d;
}
}



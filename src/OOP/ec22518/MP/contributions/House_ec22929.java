package OOP.ec22518.MP.contributions;

class House_ec22929 implements Visitable {
    Room r [] = new Room[3];
    int index = 0;        
    char[] options = {'a', 'b','y', 'n'};
    
    House_ec22929(){
        r[0] = new Room_ec22929();
        r[1] = new Room_ec22532();
        r[2] = new Room_ec22476();
    }
    
    public void Hallway(Visitor v, Direction d) {        
        char choice = v.getChoice("Do you wish to a) Go West b) Go East", options);
        char leave = ' ';

        if (choice == 'a') {
            if (index > 0) {
                d = Direction.FROM_WEST;
                index -= 1;
                r[index].visit(v,d);
                Hallway(v,d);
            }
            
            if (index == 0) {
                leave = v.getChoice("Do you wish to leave the house? (y/n)", options);
                if (leave == 'y') {
                    System.exit(0);
                }
                else if (leave == 'n') {
                    r[index].visit(v, d);
                    Hallway(v,d);
                }
            }
        }

        else if (choice == 'b') {
            char gotoroom = v.getChoice("You are in the well decorated hallway, do to next room? (y/n)", options);
            if (gotoroom =='y') {
                if (index <= 2) {
                    d = Direction.FROM_EAST;
                    index += 1;
                    r[index].visit(v,d);
                    Hallway(v,d);
                }
                else {
                    v.tell("This is the last room");
                }
                    
            }

            else if (gotoroom == 'n') {
                d = Direction.FROM_WEST;
                r[index].visit(v,d);
                Hallway(v,d);
            }
        }
    }
    
    public Direction visit(Visitor v, Direction d) {
        r[index].visit(v,d);
        
        Hallway(v,d);

        return d;
    }
}

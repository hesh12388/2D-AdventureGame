package OOP.ec22518.MP.contributions;

class House_ec22515 implements Visitable {

    Room[] rooms;
    int location; //location in rooms array
    
    //floor plan --> centre hallway with rooms on every side.
    //1 exit to all rooms leads back to Hallway where visitor will choose again out of N.S.E.W.
    
    public House_ec22515 () {
        rooms = new Room[4];
        rooms[0] = new Room_ec22515(); //my room
        
        rooms[1] = new Room_ec22475(); //other random rooms
        rooms[2] = new Room_ec221013();
        rooms[3] = new Room_ec22890();
    }
    
    
    public Direction visit(Visitor v, Direction d) {
        
        boolean leaving = false;
        
        char[] choices = {'N','S','E','W'};
        
        
        v.tell("You entred ec22515's House");
        v.tell("You emerged from spiral staircase into the centre of a large hallway");
        v.tell("You");
        v.tell("You have 4 directions to choose from: (N)orth, (S)outh, (E)ast and (W)est ");
        
        
        
        while(!leaving) {
            
            char choice = 'H';
            
            boolean valid = false;
            while (!valid) {
                choice = v.getChoice("Choose a direction", choices);
                if (choice == 'N' || choice == 'S' || choice == 'E' || choice == 'W') {
                    valid = true;
                } else {
                    v.tell("Invalid Input, try again");
                    valid = false;
                }
            }
            
            
            switch(choice){
            case('N'):
                    location = 0;
                    d = Direction.TO_NORTH;
                    System.out.println("Went North");
                    break;
            case('S'):
                    location = 1;
                    d = Direction.TO_SOUTH;
                    System.out.println("Went South");
                    break;
            case('E'):
                    location = 2;
                    d = Direction.TO_EAST;
                    System.out.println("Went East");
                    break;
            case('W'):
                    location = 3;
                    d = Direction.TO_EAST;
                    System.out.println("Went West");
                    break;
            }
            
            d = rooms[location].visit(v, d);
            System.out.println("return : " + d);
            
            System.out.println("");
            System.out.print("You arrive back in the Hallway");
            if (d == Direction.FROM_SOUTH){
                System.out.print(" from the South \n ");
            } else if (d == Direction.FROM_NORTH){
                System.out.print(" from the North \n ");
            } else if (d == Direction.FROM_WEST) {
                    System.out.print(" from the West \n ");
            }else {
                    System.out.println(" from the East \n ");
            }
            
            leaving = isLeaving(v);
            
        }
        
        return d;
        
    }
    
    //breaking out of main loop method
    public static boolean isLeaving(Visitor v) {
        char[] yes_no = {'y', 'n'};
        
        
        boolean leaving = false;
        boolean valid = false;
        
        while (!valid) {
            
            char bool = v.getChoice("Would you like to leave (y/n)", yes_no);
            if (bool == 'y'){
                leaving = true;
                valid = true;
            } else if (bool == 'n') {
                leaving = false;
                valid = true;
            } else {
                v.tell("Invalid Input");
                valid = false;
            }
        }
        
        return leaving;
    }
    
    
}

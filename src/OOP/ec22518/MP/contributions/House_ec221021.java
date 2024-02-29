package OOP.ec22518.MP.contributions;

class House_ec221021 implements Visitable {
    
    private Room R_One;
    private Room R_Two;
    private Room R_Three;
    private Room R_Four;
    
    House_ec221021() {
  
        R_Two = new Room_ec22587 ();
        R_Three = new Room_ec221021();
        R_Four = new Room_ec221006();

        }
    
    public Direction visit(Visitor vis, Direction dir) {
            char[] options = {'a','b','c','d'};

            vis.tell("You enter a an alley. Now, you can decide either you can go North or South or East or West.");
        char choice = vis.getChoice("North (a), or South (b), East(c), West(d)?", options);

       
        
         if(choice == 'b') {
                dir = R_Two.visit(vis, Direction.FROM_SOUTH);
            }

        else if (choice == 'c'){
            dir = R_Three.visit(vis, Direction.FROM_EAST);
        }

        else if (choice == 'd'){
            dir = R_Four.visit(vis, Direction.FROM_WEST);
        }

            
        return Direction.opposite(dir); //new
    }
}

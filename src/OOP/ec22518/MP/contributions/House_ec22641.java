package OOP.ec22518.MP.contributions;

class House_ec22641 implements Visitable {

    Room room1;
    Room room2;
    Room room3;

    House_ec22641()
    {
        room1 =new Room_ec22641();
        room2= new Room_ec22777(); 
        room3 =new Room_ec22666(); 
    }


    public Direction visit( Visitor v, Direction d)
    {

        v.tell("You just entered the House, which is made up of three rooms.");
        v.tell("So choose to go somewhere between, South, West and East.");
        
        
        
        
        int loc = 1;


        while(loc!=0)
        {
            if(loc == 1)
            {
                 d = room1.visit(v,d);
                 if (d == Direction.TO_NORTH) {
                 loc = 2;
                 }
            }

            else if (loc == 2)
            {
               d = room2.visit(v,d);
               if ( d == Direction.TO_SOUTH) {
               loc = 1;
               }
               
               else if ( d == Direction.TO_EAST) {
                    loc = 0;
                }
               
            }

            if(d == Direction.TO_NORTH) break;
            
            

        }

             v.tell("You are leaving the house!");

           return d;
    }
    
    public static void main(String[] args) {
        
        House_ec22641 h = new House_ec22641();
        Visitor guy = new IOVisitor(System.out,System.in);
        h.visit(guy,Direction.FROM_SOUTH);
        }
 }

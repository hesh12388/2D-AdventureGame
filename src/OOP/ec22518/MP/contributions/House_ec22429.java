package OOP.ec22518.MP.contributions;// Credit to ec22765(Maksymilian Jan Matusiak) and ec22462(Justin Adrian Abaloyan Ama) for helping me sort out the room positions.
// Credit to ec22462(Justin Adrian Abaloyan Ama) helping me figure out how the fixed rooms should work.

// Layout for house
///////////   ///////////   ///////////
//       //   //       //   //       //
//  n    //   //   g   //   //   c   //
//       //   //       //   //       //
///////////   ///////////   ///////////

             ///////////
             //       // 
             // foyer // 
             //       // 
             ///////////


class House_ec22429 implements Visitable {

    Room r1; //c
    Room r2; //n
    Room r3; //g

    
    House_ec22429() {
        r1 = new Room_ec22429();
        r2 = new Room_ec22411();
        r3 = new Room_ec22467();
    }

    public Direction visit(Visitor v, Direction d) {

        boolean inside = true;
        boolean foyer = true;
        Room current_room = r3;
        boolean in_r3 = false;

        v.tell("Hello visitor! You have entered the house.");
        char [] rooms = {'n', 'c', 'l', 'k'};
        char [] foyer_access = {'g', 'l'};

        while (inside) {

            if(foyer){
                while(foyer){
                    v.tell("You are in the foyer.");
                    v.tell("Do you want to:");
                    char foyer_dir = v.getChoice("go to (g)ift's room or (l)eave the house.", foyer_access);

                    if(foyer_dir == 'g'){
                        v.tell("Welcome to Gift's room");
                        current_room = r3;
                        foyer = false;
                        in_r3 = true;
                    }

                    else if(foyer_dir == 'l') {
                        v.tell("You have decided to leave the house. Goodbye!");
                        foyer = false;
                        inside = false;
                    }
                }
            }

            char visit_r = v.getChoice("Do you want to visit (n)atalie's or (c)ristina's room,  or (l)eave the house", rooms);

            if(visit_r == 'n'){
                if(current_room.equals(r3))
                {
                    d = r2.visit(v,d);
                    v.tell("You are now in Natalie's room.");
                    current_room = r2;
                }
                else if(!in_r3){
                    v.tell("You cannot go to that room. I will now ask you to leave. Bye bye!");
                    inside = false;
                }
                else{
                    v.tell("Something seems to have gone wrong...");
                }
            }

            else if(visit_r == 'c'){
                if(current_room.equals(r3)){
                    d = r1.visit(v,d);
                    v.tell("You are now in Cristina's room.");
                    current_room = r1;
                }
                else if(!in_r3){
                    v.tell("You cannot go to that room. I will now ask you to leave. Bye bye!");
                    inside = false;
                }
                else{
                    v.tell("Something seems to have gone wrong...");
                }
            }

            else if(visit_r == 'l'){
                inside = false;
                v.tell("You have decided to leave the house. Goodbye!");
            }
        }

        return d;

    }
}
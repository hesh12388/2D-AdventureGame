package OOP.ec22518.MP.contributions;

class House_jpp319457 implements Visitable {
    public Direction visit(Visitor v, Direction d) {
        
        char[] runOptions = {'a', 'b'};
        char[] roomOptions = {'w', 'a', 's', 'c', 'f'};

        v.tell("You're in london and can go North, West, South, East. Do you want to keep going on the adventure or no.");

        char option = v.getChoice("Enter a) To continue and explore b) To leave the house", runOptions);
        
        while (option == 'a')
        {
            v.tell("Which direction would you like to choose to go on?");
            char ro = v.getChoice("w) North a) West s) South d) East f) Leave", roomOptions); // ro = roomOptions

            if (ro == 'w')
            {
                RoomA.visit(v, d);
                v.tell("Is North nice?");
            }
            else if (ro == 'a')
            {
                RoomB.visit(v, d);
                v.tell("Is West nice?");
            }
            else if (ro == 's')
            {
                RoomC.visit(v, d);
                v.tell("Is South nice?");
            }
            else if(ro == 'd')
            {
                RoomD.visit(v, d);
                v.tell("Is East nice?");
            }
            else if (ro == 'f')
            {
                v.tell("YOU DIED!!!");
                RoomF.visit(v, d);
            }

            option = v.getChoice("Enter a) To continue the adventure b) To leave the adventure", runOptions);
        }

        return d;

    }



    Room RoomA, RoomB, RoomC, RoomD, RoomF;

    public House_jpp319457() {
        RoomA = new Room_jpp319457();
        RoomB = new Room_ec22519();
        RoomC = new Room_ec22520();
        RoomD = new Room_ec22772();
        RoomF = new Room_ec22519();
    }
}

    
    

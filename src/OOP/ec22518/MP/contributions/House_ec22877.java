package OOP.ec22518.MP.contributions;

class House_ec22877 implements Visitable {
        private Room place1;
        private Room place2;
        private Room place3;
        private boolean leave;

        House_ec22877()
        {
            place1 = new Room_ec22877();
            place2 = new Room_ec22712();
            place3 = new Room_ec22935();
        }

        public Direction visit(Visitor visitor, Direction direct)
        {
            visitor.tell("Welcome to my house");
            leave = false;

            char[] choose  = {'a' , 'b' , 'c'};

            while (leave != true) {
                char choice = visitor.getChoice("Choose a random letter, it might just be your way out...", choose);

                if (choice == ('c'))
                {
                    visitor.tell("Alright then...");
                    direct = place3.visit(visitor, direct);
                    visitor.tell("Go back");
                }

                if (choice == ('a'))
                {
                    visitor.tell("Ah, good choice");
                    direct = place1.visit(visitor, direct);
                    visitor.tell("I'll let you leave now");
                    leave = true;
                }

                if (choice == ('b'))
                {
                    visitor.tell("Interesting choice");
                    direct = place2.visit(visitor, direct);
                    visitor.tell("Look again");
                }
            }
            return direct;
        }
    }

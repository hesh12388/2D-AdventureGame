package OOP.ec22518.MP.contributions;

class House_ec22678 implements Visitable {
    private Room room1;
    private Room room2;
    private Room room3;

    House_ec22678() {
        room1 = new Room_ec22678();
        room2 = new Room_ec22680();
        room3 = new Room_ec22692();
    }
    
    
        public Direction visit(Visitor v, Direction d) {
            int counter = 0;
            boolean leave = true;
            
            v.tell("You stumble upon an innocent looking cottage");
            v.tell("Infact it looks so innocent, you decide to walk in");
            v.tell("As you walk in, the door shuts behind you and an ominous presence is felt");
            v.tell("You start to walk deeper into this cottage which now looks a lot bigger inside");
            v.tell("You see an 3 doorways.");
            v.tell("One door North, East and West which all seem to lead deeper into this cottage.");
            v.tell("No exit is available.");
            
            while(counter != 3){
                char direction = v.getChoice( "Do as you please:\n n) North\n e) East\n w) West", new char[] {'n', 'e', 'w'});
                    if (direction == 'n') {
                        v.tell("You have entered a mysterious room");
                        d = room1.visit(v, d);
                    }
                    else if (direction == 'e') {
                        v.tell("You have entered a spooky room");
                        d = room2.visit(v, d);
                    }
                    else if (direction == 'w') {
                        v.tell("You have entered a plain room");
                        d = room3.visit(v, d);
                    }
                counter++;
            }
            v.tell("Suddenly an exit appears South. Do you wish to continue or leave?");
            while(leave){
                char direction = v.getChoice( "Do as you please:\n n) North\n e) East\n w) West\n s) South", new char[] {'n', 'e', 'w', 's'});
            
                        if (direction == 'n') {
                            v.tell("You have entered a mysterious room");
                            d = room1.visit(v, d);
                        }
                        else if (direction == 'e') {
                            v.tell("You have entered a spooky room");
                            d = room2.visit(v, d);
                        }
                        else if (direction == 'w') {
                            v.tell("You have entered a plain room");
                            d = room3.visit(v, d);
                        }
                        else if (direction == 's'){
                            break;
                        }
            }
            return d;
        }
    }

package OOP.ec22518.MP.contributions;

class House_ec22738 implements Visitable {
    Room_ec22738 room1 = new Room_ec22738();
    Room_ec221011 room2 = new Room_ec221011();
    Room_ec22520 room3 = new Room_ec22520();
    
    public Direction visit(Visitor v, Direction d) {
        Direction d1 = room1.visit(v,d);
        
        int current = 0;
        if (d1 == Direction.FROM_NORTH){
            v.tell("You have left the house through the main entrance");
            return d1;
        }
        else if (d1 == Direction.FROM_SOUTH){
            v.tell("You have left the house through the back door");
            return d1;
        }
        else if (d1 == Direction.FROM_WEST){
            current = 3;
        }
        else if (d1 == Direction.FROM_EAST){
            current = 2;
        }
            
        boolean inside = true;
        while (inside){
            if (current == 1){
                d1 = room1.visit(v,d1);
                if (d1 == Direction.FROM_NORTH){
                    v.tell("You have left the house through the main entrance.");
                    inside=false;
                }
                else if (d1 == Direction.FROM_SOUTH){
                    v.tell("You have left the house through the back door.");
                    inside=false;
                }
                else if (d1 == Direction.FROM_WEST){
                    current = 3;
                }
                else if (d1 == Direction.FROM_EAST){
                    current = 2;
                }
            }
            
            else if (current == 2){
                d1 = room2.visit(v,d1);
                if (d1 == Direction.FROM_NORTH){
                    v.tell("You go into a white room, and as soon you step in, the floor opens up and you fall to your death.");
                    inside=false;
                }
                else if (d1 == Direction.FROM_SOUTH){
                    v.tell("You have left the house through a window to find out that there is a pack of wolves waiting for you. Good luck escaping them!");
                    inside=false;
                }
                else if (d1 == Direction.FROM_WEST){
                    current = 1;
                }
                else if (d1 == Direction.FROM_EAST){
                    v.tell("You have left the house through the garden.");
                    inside=false;
                }
            }
            
            else if (current == 3){
                d1 = room3.visit(v,d1);
                if (d1 == Direction.FROM_NORTH){
                    v.tell("You have left the house through a small gap on the wall.");
                    inside=false;
                }
                else if (d1 == Direction.FROM_SOUTH){
                    v.tell("You leave the house through a mysterious door, and you teleport to a lovely beach in Tenerife.");
                    inside=false;
                }
                else if (d1 == Direction.FROM_WEST){
                    v.tell("You have left the house.");
                }
                else if (d1 == Direction.FROM_EAST){
                    current = 1;
                }
            }
        }
        return d1;
    }
}

    

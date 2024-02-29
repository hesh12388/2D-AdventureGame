package OOP.ec22518.MP.contributions;

class House_ec22746 implements Visitable {
    Room room1;
    Room room2;
    Room room3;
    Room room4;
    
    House_ec22746(){
        room1 = new Room_ec22746();
        room2 = new Room_ec22519();
        room3 = new Room_ec22415();
        room4 = new Room_ec22738();
    }
    
    public Direction visit(Visitor visitor, Direction direction){
        visitor.tell("You have entered House_ec22746! \n");
        visitor.tell("                     Brief plan of house");
        visitor.tell("            #exit#          #exit#         #exit#");
        visitor.tell("       |---------------|--------------|---------------|");
        visitor.tell("#exit# |----#exit#-----|-Room_ec22519-|----#exit#-----| #exit#");
        visitor.tell("       |---------------|--------------|---------------|");
        visitor.tell("#exit# |-Room_ec22738--|-Room_ec22746-|-Room_ec22415--| #exit#");
        visitor.tell("       |---------------|--------------|---------------|");
        visitor.tell("                           #enter# \n");
        int location = 1;
        while (location != 0){
            if (location == 1){
                visitor.tell("You are entering Room_ec22746");
                direction = room1.visit(visitor, direction);
                if (direction == Direction.TO_NORTH){
                    visitor.tell("You are leaving north");
                    location = 2;
                }
                else if (direction == Direction.TO_SOUTH){
                    visitor.tell("You are leaving south");
                    location = 0;
                }
                else if (direction == Direction.TO_EAST){
                    visitor.tell("You are leaving east");
                    location = 3;
                }
                else if (direction == Direction.TO_WEST){
                    visitor.tell("You are leaving west");
                    location = 4;
                }
            }
            else if (location == 2){
                visitor.tell("You are entering Room_ec22519");
                direction = room2.visit(visitor, direction);
                if (direction == Direction.TO_NORTH){
                    visitor.tell("You are leaving north");
                    location = 0;
                }
                else if (direction == Direction.TO_SOUTH){
                    visitor.tell("You are leaving south");
                    location = 1;
                }
                else if (direction == Direction.TO_EAST){
                    visitor.tell("You are leaving east");
                    location = 0;
                }
                else if (direction == Direction.TO_WEST){
                    visitor.tell("You are leaving west");
                    location = 0;
                }
            }
            else if (location == 3){
                visitor.tell("You are entering Room_ec22415");
                direction = room3.visit(visitor, direction);
                if (direction == Direction.TO_NORTH){
                    visitor.tell("You are leaving north");
                    location = 0;
                }
                else if (direction == Direction.TO_SOUTH){
                    visitor.tell("You are leaving south");
                    location = 0;
                }
                else if (direction == Direction.TO_EAST){
                    visitor.tell("You are leaving east");
                    location = 0;
                }
                else if (direction == Direction.TO_WEST){
                    visitor.tell("You are leaving west");
                    location = 1;
                }
            }
            else if (location == 4){
                visitor.tell("You are entering Room_ec22738");
                direction = room4.visit(visitor, direction);
                if (direction == Direction.TO_NORTH){
                    visitor.tell("You are leaving north");
                    location = 0;
                }
                else if (direction == Direction.TO_SOUTH){
                    visitor.tell("You are leaving south");
                    location = 0;
                }
                else if (direction == Direction.TO_EAST){
                    visitor.tell("You are leaving east");
                    location = 1;
                }
                else if (direction == Direction.TO_WEST){
                    visitor.tell("You are leaving west");
                    location = 0;
                }
            }
        }
        visitor.tell("You are now leaving House_ec22746");
        return direction;
    }
}

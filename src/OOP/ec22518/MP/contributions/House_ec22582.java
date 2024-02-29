package OOP.ec22518.MP.contributions;

public class House_ec22582 implements Visitable {
    /*
            Room Layout
     _________________________
    |                         |
    |        exit             |
    |_________________________|
    |                         |
    |        top              |
    |_________________________|
    |                         |
    |        mid              |
    |_________________________|
    |                         |
    |        bot              |
    |_________________________|
    |                         |
    |        exit             |
    |_________________________|

    */

    Room top;
    Room mid;
    Room bot;
    
    
    public House_ec22582() {
        // Create rooms
        this.top = new Room_ec22583();
        this.mid = new Room_ec22585();
        this.bot = new Room_ec22586();
    }
    
    public Direction visit(Visitor visitor, Direction direction) {
        visitor.tell("You enter the house");

        Room currentRoom = this.mid;
        boolean inHouse = true;

        while(inHouse){

            Direction directionLeavingIn = currentRoom.visit(visitor, direction);

            if(directionLeavingIn == Direction.TO_WEST ||  directionLeavingIn == Direction.TO_EAST){
                visitor.tell("The doors are barred off");
            } else if(directionLeavingIn == Direction.TO_NORTH){
                if(currentRoom == this.bot){
                    currentRoom = this.mid;
                    direction = Direction.FROM_SOUTH;
                }else if(currentRoom == this.mid){
                    currentRoom = this.top;
                    direction = Direction.FROM_SOUTH;
                } else if(currentRoom == this.top){
                    inHouse = false;
                }
            } else if(directionLeavingIn == Direction.TO_SOUTH){
                if(currentRoom == this.bot){
                    inHouse = false;
                }else if(currentRoom == this.mid){
                    currentRoom = this.bot;
                    direction = Direction.FROM_NORTH;
                } else if(currentRoom == this.top){
                    currentRoom = this.mid;
                    direction = Direction.FROM_NORTH;
                }
            }
        }

        visitor.tell("You leave the house " + direction.toString());
        return direction;
    }
}

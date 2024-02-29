package OOP.ec22518.MP.contributions;

public class House_ec22784 implements Visitable {

    Room room1;
    Room room2;
    Room room3;


    public House_ec22784() {
        this.room1 = new Room_ec22583();
        this.room2 = new Room_ec22585();
        this.room3 = new Room_ec22586();
    }

    public Direction visit(Visitor visitor, Direction direction) {
        visitor.tell("You enter the house");

        Room current_room = this.room2;
        boolean inside_house = true;

        while(inside_house){

            Direction direction_left = current_room.visit(visitor, direction);

            if(direction_left == Direction.TO_WEST ||  direction_left == Direction.TO_EAST){
                visitor.tell("You can't leave through here, the doors are locked.");
            } else if(direction_left == Direction.TO_NORTH){
                if(current_room == this.room3){
                    current_room = this.room2;
                    direction = Direction.FROM_SOUTH;
                }else if(current_room == this.room2){
                    current_room = this.room1;
                    direction = Direction.FROM_SOUTH;
                } else if(current_room == this.room1){
                    inside_house = false;
                }
            } else if(direction_left == Direction.TO_SOUTH){
                if(current_room == this.room3){
                    inside_house = false;
                }else if(current_room == this.room2){
                    current_room = this.room3;
                    direction = Direction.FROM_NORTH;
                } else if(current_room == this.room1){
                    current_room = this.room2;
                    direction = Direction.FROM_NORTH;
                }
            }
        }

        visitor.tell("You leave the house " + direction.toString());
        return direction;
    }    
}
package OOP.ec22518.MP.contributions;/*Room layout
      |            |
--- Room 1------ Room 2 ---
      |            |
      |            |
      |            |
--- Room 3 ----- Room 4 ---> Exit
      |            |
*/

class House_ec22912 implements Visitable {

    class Exit extends Room{
        public Direction visit(Visitor v,Direction d){
            return null;
        }

    }
    
    Room[] rooms;
    House_ec22912(){
        rooms = new Room[5];
        rooms[0] = new Room_ec22912();
        rooms[1] = new Room_ec22992();
        rooms[2] = new Room_ec19389();
        rooms[3] = new Room_ec22617();
        rooms[4] = new Exit();
    }

    int[] RoomDirection(Room current_room){

        if(current_room.equals(rooms[0])){
            return new int[]{2,1,2,1};
        }

        else if(current_room.equals(rooms[1])){
            return new int[]{3,0,3,0};
        }

        else if(current_room.equals(rooms[2])){
            return new int[]{0,3,0,-1};
        }

        else if(current_room.equals(rooms[3])){
            return new int[]{1,-1,1,2};
        }
        return new int[]{-1,-1,-1,-1};
    }

    int WhereTo(Direction destination){
        int where_to;
        if (destination == Direction.TO_NORTH || destination == Direction.FROM_SOUTH){
            where_to = 0;
        }

        else if(destination == Direction.TO_EAST || destination == Direction.FROM_WEST){
            where_to = 1;
        }

        else if(destination == Direction.TO_SOUTH || destination == Direction.FROM_NORTH){
            where_to = 2;
        }

        else if(destination == Direction.TO_WEST || destination == Direction.FROM_EAST){
            where_to = 3;
        }

        else{
            where_to = -1;
        }

        return where_to;
    }

    Room NextRoom(int where_to, int[] to_go){
        int next_room;

        next_room = to_go[where_to];

        if(next_room == -1){
            return rooms[4];
        }



        return rooms[next_room];
    }


    public Direction visit(Visitor v, Direction d) {
        int room_num = 0;
        int[] to_go = {2,1,2,1};
        int where_to;
        Room current_room = rooms[0];
        Direction destination;

        v.tell("You have now entered the house of Mine, welcome you are now locked inside the house, find the exit");
        while (!current_room.equals(rooms[4])) {
            destination = current_room.visit(v, d);
            where_to = WhereTo(destination);
            to_go = RoomDirection(current_room);
            current_room = NextRoom(where_to,to_go);

        }

        v.tell("GoodBye you have left the house.");

        return Direction.TO_SOUTH;
    }
}

package OOP.ec22518.MP.contributions;

public class House_ec22919 implements Visitable {
    //instance variables
    Room[] rooms;
    int roomNumber;
    boolean hasEscaped;

    //constructor to create rooms and set starting room
    public House_ec22919() {
        //create instances of all rooms
        Room_ec22858 room0 = new Room_ec22858(); // Egg Room
        Room_ec22578 room1 = new Room_ec22578(); // Ghost Game Room
        Room_ec22919 room2 = new Room_ec22919(); // My Room
        Room_ec22889 room3 = new Room_ec22889();// Exit Room
        Room_ec221247 room4 = new Room_ec221247();// Item Room
        Room_ah21407 room5 = new Room_ah21407();// Seal Room

        //set rooms into array
        rooms = new Room[] {room0,room1,room2,room3,room4,room5};
        roomNumber = 0;
        hasEscaped = false;

    }




     public Direction visit(Visitor v, Direction d) {

         v.tell("You have entered a mysterious house");

         while(!hasEscaped){
             d = rooms[roomNumber].visit(v, d);
             
             switch(roomNumber){
                 case 0://Egg Room
                     if(d == Direction.TO_NORTH){
                         roomNumber = 3;
                     }
                     else if(d == Direction.TO_WEST){
                         roomNumber = 1;
                     }
                     else if(d == Direction.TO_EAST){
                         roomNumber = 2;
                     }
                     else{
                         v.tell("The gap leads you back into the room");
                     }
                     break;

                 case 1://Ghost Game Room
                     if(d == Direction.TO_NORTH){
                         roomNumber = 2;
                     }
                     else if(d == Direction.TO_EAST){
                         roomNumber = 0;
                     } 
                     else{
                         roomNumber = 5;
                     }
                     break;

                 case 2://My Room 
                     if(d == Direction.TO_NORTH){
                         roomNumber = 4;
                     }
                     else if(d == Direction.TO_WEST){
                         roomNumber = 0;
                     }
                     else{
                         roomNumber = 1;
                     }
                     break;
                     
                 case 3://Exit Room
                     if (d == Direction.TO_NORTH){
                         v.tell("You have escaped the house!");
                         hasEscaped = true;
                     }
                     else{
                         roomNumber = 0;
                     }
                     break;

                case 4://Item Room
                     roomNumber = 2;
                     break;

                case 5://Seal Room
                     roomNumber = 1;
                     break;
             }
        }
          return d;
     }
}

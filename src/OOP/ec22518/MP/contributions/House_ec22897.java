package OOP.ec22518.MP.contributions;

/*
    
     ROOM LAYOUT
      ___________
     |           |
     |     2     | 
     |___________|
     |  0   | 1  |
     |______|____| 
     |   hallway |
     |___________|
    
    It wraps around the edges when going beyound the point from the illustration
    
    */
public class House_ec22897 implements Visitable {

    Room room0;
    Room room1;
    Room room2;
    private Room[] rooms;
    Visitable objectToVisit;
    String contributor;

    
    House_ec22897(){
        
        room0 = new Room_ec22897();
        room1 = new Room_ec22881();
        room2 = new Room_ec22891();
        
        rooms = new Room[4];
        
        rooms[0] = room0; 
        rooms[1] = room1; 
        rooms[2] = room2;
        //4 is the hallway
        
        // objectToVisit = r; 
        // contributor = c;
    }

    @Override
    public Direction visit(Visitor visitor, Direction d) {
        
        System.out.println(rooms[0]);
        System.out.println(rooms[1]);
        System.out.println(rooms[2]);
        
        
        int roomIndex = 0; 
        Item key = new Item("key");
        boolean noKey = true;
        
        visitor.tell("You enter into my house.");
        if (d == Direction.FROM_NORTH) {
            visitor.tell("You arrived from north.");
            roomIndex = 2;
        } else if (d == Direction.FROM_EAST) {
            visitor.tell("You arrived from east");
            roomIndex = 1;
        } else if (d == Direction.FROM_SOUTH) {
            visitor.tell("You arrived from south.");
            roomIndex = 0;
        } else if (d == Direction.FROM_WEST) {
            visitor.tell("You arrived from west.");
             roomIndex = 0;
        }        
        
        whoseRoom(roomIndex, visitor);
        
        Room currentRoom = rooms[roomIndex];
        Direction direction = currentRoom.visit(visitor, d);
        
        //visitor can only leave my house when they are in the hallway and have the key.
        while (noKey) {
            if(direction == Direction.TO_NORTH){
                 if (currentRoom == rooms[2]) {
                    visitor.tell("You cannot go North, this is the top of the house");
                    visitor.tell("you will be looped back to the bottom of the house..");
                    visitor.tell("HOUSE SPEAKING: going SOUTH you found a hallway!");
                    visitor.tell("There are 2 different rooms and one exit");
                    char[] choices = {'1', '2', 'l'};
                    char choice = visitor.getChoice("Which room would you like to explore? (1/2) or press 'L' to leave:", choices);
                     
                     switch (choice){ 
                         case '1':
                             visitor.tell("You decide to explore Room 1.");
                             roomIndex = 0;
                             currentRoom = rooms[roomIndex]; 
                             break;
                         case '2':
                             visitor.tell("You decide to explore Room 2.");
                             roomIndex = 1;
                             currentRoom = rooms[roomIndex]; 
                             break;
                         case 'l':
                             visitor.tell("You can only leave the house if you have the key, it is somewhere in the rooms.");
                             if (visitor.hasEqualItem(key)) {
                                 visitor.tell("luckily you got the key");
                                 visitor.tell("You leave my house using the key.");
                                 return direction;
                             } else {
                                 visitor.tell("You can't leave without the key. Try finding it from one of the rooms.");
                                 visitor.tell("You will be redirected to room number 1");
                                 roomIndex = 0;
                                 currentRoom = rooms[roomIndex]; 
                                 break;
                             }                        
                         }
                     
                 }else if( (currentRoom == rooms[0]) || (currentRoom == rooms[1])){
                     roomIndex = 2;
                     currentRoom = rooms[roomIndex];
                 }
            }else if(direction == Direction.TO_EAST){
                if((roomIndex == 1) || (roomIndex == 2)){
                    visitor.tell("you are going EAST from room number " + ( roomIndex+1));
                    visitor.tell("So you will be wrapped around back to room 1");
                    roomIndex = 0;
                    currentRoom = rooms[roomIndex];
                }else if(roomIndex == 0){
                    roomIndex = 1;
                    currentRoom = rooms[roomIndex];
                }  
            }else if(direction == Direction.TO_WEST){
                if((roomIndex == 0) || (roomIndex == 2)){
                    visitor.tell("you are going WEST from room number " + ( roomIndex+1));
                    visitor.tell("So you will be wrapped around back to room 1");
                    roomIndex = 1;
                    currentRoom = rooms[roomIndex];
                }else if(roomIndex == 1){
                    roomIndex = 0;
                    currentRoom = rooms[roomIndex];
                } 
            
            
             }else if(direction == Direction.TO_SOUTH){
                 if((roomIndex == 2)){
                     visitor.tell("HOUSE SPEAKING: going SOUTH from room number " + ( roomIndex+1));
                     whoseRoom(roomIndex, visitor);
                     roomIndex = 1;
                     currentRoom = rooms[roomIndex];                    
                 }else if( (roomIndex == 0) || (roomIndex == 1)){
                     
                     visitor.tell("HOUSE SPEAKING: going SOUTH you found a hallway!");
                     visitor.tell("There are 2 different rooms and one exit");
                     char[] choices = {'1', '2', 'l'};
                     char choice = visitor.getChoice("Which room would you like to explore? (1/2) or press 'L' to leave:", choices);
                     
                     switch (choice){ 
                             case '1':
                                 visitor.tell("You decide to explore Room 1.");
                                 roomIndex = 0;
                                 currentRoom = rooms[roomIndex]; 
                                 break;
                             case '2':
                                 visitor.tell("You decide to explore Room 2.");
                                 roomIndex = 1;
                                 currentRoom = rooms[roomIndex]; 
                                 break;
                             case 'l':
                                 visitor.tell("You can only leave the house if you have the key, it is somewhere in the rooms.");
                                 if (visitor.hasEqualItem(key)) {
                                     visitor.tell("luckily you GOT the key");
                                     visitor.tell("You leave my house using the key.");
                                     return direction;
                                 } else {
                                     visitor.tell("You can't leave without the key. Try finding it from one of the rooms.");
                                     visitor.tell("You will be redirected to room number 1");
                                     roomIndex = 0;
                                     currentRoom = rooms[roomIndex]; 
                                      break;
                                 }                        
                     }
                   
                 }
                
                
            }
            noKey = true;
            
            direction = currentRoom.visit(visitor, direction);
            
            if (d == Direction.TO_NORTH) {
                visitor.tell("You left the previous room HEADING SOUTH.");
            } else if (d == Direction.TO_EAST) {
                visitor.tell("You left the previous room HEADING WEST");
            } else if (d == Direction.TO_SOUTH) {
                visitor.tell("You left the previous room HEADING NORTH.");
            } else if (d == Direction.TO_WEST) {
                visitor.tell("You left the previous room HEADING EAST.");
            }
            
            
      
            }
    
        
           return d;
        }
    
    private void whoseRoom(int roomIndex, Visitor visitor){
        
        // Finding room name
        String roomName = "";
        if(roomIndex == 0){
            roomName = "Room_ec22897";
        }else if (roomIndex == 1){
            roomName = "Room_ec22881";
        }else if (roomIndex == 2){
            roomName = "Room_ec22891";
        }
        
        visitor.tell("you are in going to room number " + (roomIndex+1) + " which belongs to "+ roomName + "\n");
        
        
    }
    }

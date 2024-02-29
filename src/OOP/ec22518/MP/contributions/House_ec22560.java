package OOP.ec22518.MP.contributions;

import java.util.ArrayList;

public class House_ec22560 implements Visitable{

    Room room3;
    Room room2;
    Room room1;
    Room temproom;
    char[] options = {'1', '2', '3', '4'};
    char choice = 'a';
    boolean leave = false;

    House_ec22560(){
        room3= new Room_ec22842();
        room2 = new Room_ec22412();
        room1= new Room_ec22830();
    }
    
    ArrayList<Room> rooms = new ArrayList<Room>();
    
    public Direction visit(Visitor visitor, Direction dFrom) {
            
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
     
        while (!leave){
        
        choice = visitor.getChoice("I have 3 rooms, in my House ! Which one of them do you want to visit first?\n a)Bank Account Management System Room\n b)Club Room\n c)Masjid\n d)Leave the house",options);
        
        if(choice == 'a'){
            temproom = rooms.get(0);
            temproom.visit(visitor, dFrom);
        }else if (choice == 'b'){
            temproom = rooms.get(1);
            temproom.visit(visitor, dFrom);
        }else if(choice == 'c'){
            temproom = rooms.get(2);
            temproom.visit(visitor, dFrom); 
        }else if(choice == 'd'){
            visitor.tell("It was nice to have you!");
            leave = true;
        }            
    }

        return dFrom;
    }
}

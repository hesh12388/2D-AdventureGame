package OOP.ec22518.MP.contributions;

import java.util.Scanner;
class House_ec22561 implements Visitable {
    Room[] side1;
    Room[] side2;
    // char[] choices = {q,n,e,s,w}
    int room = 0;
    int side_im_on = 1;


    House_ec22561(){
        side1= new Room[]{new Room_ec221023(),new Room_ec22561(), new Room_ec211030(), new Room_ec211045()};
        side2= new Room[]{new Room_ec21841(), new Room_ec221085(), new Room_ec21499(), new Room_ec211044()};
    }
    public Direction visit(Visitor v, Direction d){
        System.out.println("Hello visitor.You have entered the haunted Winchester House. Which room would you like to go first?");
        int count = 0;
        char answer = 'a';
        d = side1[room].visit(v,d);
        while(answer != 'q'){
            if(((room == 3) && (d == d.TO_EAST)) || ((room == 0) && (d == d.TO_WEST)) || ((room == 4) && (d == d.TO_WEST)) || ((room == 7) && (d == d.TO_EAST))) {
                answer = 'q';
                break;
            }
            answer = inputChar("Would you like to leave thw haunted house? If yes type q.");
            if(answer == 'q'){
                break;
            }
            else{
                d = MovingToRoom(v,side_im_on,room,d);
            }
        }
        return d;
    }
//     Direction GivingDirection(char direction, Direction d){
//         if(direction == 'w'){
//             return d.TO_WEST;
//         }
//         else if(direction == 'n'){
//             return d.TO_NORTH;
//         }
//         else if(direction == 'e'){
//             return d.TO_EAST;
//         }
//         else if(direction == 's'){
//             return d.TO_SOUTH;
//         }
//         return d;
//     }
    Direction MovingToRoom(Visitor v,int side,int room,Direction d){
        if((d == d.TO_EAST) && (room != 3) && (side_im_on == 1)){
            try{
                room++;
                return side1[room - 1].visit(v,d);
            }
            catch(Exception e){
                return d;
            }
        }
        else if((d == d.TO_EAST) && (room != 7) && (side_im_on == 2)){
            try{
                room++;
                return side2[room-1].visit(v,d);
            }
            catch(Exception e){
                return d;
            }
        }
        else if((d == d.TO_WEST) && (room != 0) && (side_im_on == 1)){
            try{
                room--;
                return side1[room+1].visit(v,d);
            }
            catch(Exception e){
                return d;
            }
        }
        else if((d == d.TO_WEST) && (room != 4) && (side_im_on == 2)){
            try{
                room--;
                return side2[room+1].visit(v,d);
            }
            catch(Exception e){
                return d;
            }
        }
        else if((d == d.TO_NORTH) && (side_im_on == 2)){
            try{
                return side1[room].visit(v,d);
            }
            catch(Exception e){
                return d;
            }
        }
        else if((d == d.TO_SOUTH) && (side_im_on == 1)){
            try{
                return side2[room].visit(v,d);
            }
            catch(Exception e){
                return d;
            }
        }
        return d;
    }
    public static char inputChar(String message){
        Scanner scan = new Scanner(System.in);
        System.out.println(message);
        return scan.next().charAt(0);
    }
}

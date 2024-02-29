package OOP.ec22518.MP.contributions;

import java.util.Scanner;

public class House_ec221085 implements Visitable {
    Room_ec221085 room1;
    Room_ec22787 room2;
    Room_ec221003 room3;

    public House_ec221085(){
        room1 = new Room_ec221085();
        room2 = new Room_ec22787();
        room3 = new Room_ec221003();
    }

    public Direction visit(Visitor visitor, Direction d){
        System.out.println("Welcome, which room do you wish to visit 1)Fireplace 2)dorm room 3)fountain 4)exit");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        while(a < 1 || a>5 ){
            System.out.println("Please try again");
            a = sc.nextInt();
        }
        if(a==1){room1.visit(visitor, d);whereToGo(visitor,d);}
        if(a==2){room2.visit(visitor, d);whereToGo(visitor,d);}
        if(a==3){room3.visit(visitor, d);whereToGo(visitor,d);}

        return d;
    }
    public void whereToGo(Visitor visitor, Direction d){
        Scanner sc = new Scanner(System.in);
        int a=0;
        while(a<4){
        System.out.println("Where to you want to go next? 1)Fireplace 2)dorm room 3)something 4)exit");
        a = sc.nextInt();
        if(a==1){room1.visit(visitor, d);}
        if(a==2){room2.visit(visitor, d);}
        if(a==3){room3.visit(visitor, d);}
        }
    }
}

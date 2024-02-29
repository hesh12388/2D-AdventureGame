package OOP.ec22518.MP.contributions;

import java.util.Scanner;

class House_ec22819 implements Visitable {
    Room_ec22819 adam;
    Room_ec22420 room2;
    Room_ec22434 room3;

    Scanner scanner = new Scanner(System.in);
    String name;
    int choice = 0;
    
    public static void main(String[] args) {
        House_ec22819 h = new House_ec22819();
        Visitor v = new IOVisitor(System.out,System.in);
        Direction d = h.visit(v, Direction.FROM_NORTH);
    }
    
    public Direction visit(Visitor v, Direction d) {
        v.tell("Hi! What is your name?");
        name = scanner.nextLine();
        v.tell("");
        v.tell("Welcome " + name + "This is my house, feel free to enter my first room, well the others are not prepared yet.");
        v.tell("Remember, collect as many golds as you want!");
        v.tell("");
        d = adam.visit(v, d);

        v.tell("");
        v.tell("How was my first room? The next one will cheer you up!");
        Direction room2_direction = whereTo();
        d = room2.visit(v, room2_direction);

        v.tell("");
        v.tell("How as it " + name + "anyways come on in to our last room!");
        d = room3.visit(v, d);

        return d;
    }
    
    House_ec22819() {
        this.adam = new Room_ec22819();
        this.room2 = new Room_ec22420();
        this.room3 = new Room_ec22434();
    }

    /* inputString */
    public String inputString(String m) {
        Scanner sc = new Scanner(System.in);
        String answer;

        System.out.println(m);
        answer = sc.nextLine();
        return answer;
    }

    /* Engages User in Room 2 To where to go */
    public Direction whereTo() {
        String ans = inputString("Where would you like to go?");
        if (ans.equals("N")) {return Direction.FROM_NORTH;}
        else if (ans.equals("S") ){return Direction.FROM_SOUTH;}
        else if (ans.equals("W") ){return Direction.FROM_WEST;}
        else if (ans.equals("E") ){return Direction.FROM_EAST;}
        else {return Direction.FROM_NORTH;}
    }
}

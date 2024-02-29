package OOP.ec22518.MP.contributions;

public class House_ec21413 implements Visitable {
  Room room1;
  Room room2;
  Room room3;

  House_ec21413() {
    room1 = new Room_ec21413();
    room2 = new Room_ec221008();
    room3 = new Room_ec221218();
  }



  public Direction visit (Visitor v, Direction d) {

    v.tell("Welcome to my House, which room would you like to visit:");
    char choice = 'x';

    while (choice != 'a' && choice != 'b' && choice != 'c' && choice != 'd') {                                        // input validation

      choice = v.getChoice("a- Living room  b- Kitchen  c- Bedroom  d- exit House", new char[] {'a','b','c','d'} );

      if (choice != 'a' && choice != 'b' && choice != 'c' && choice != 'd') {
        v.tell("wrong input. try again");
      }
    }

    if (choice == 'a') {
      v.tell("you are now entering the Living room");
      room1.visit(v,d);
    }

    if (choice == 'b') {
      v.tell("you are now entering the Kitchen");
      room2.visit(v,d);
    }

    if (choice == 'c') {
      v.tell("you are now entering the Bedroom");
      room3.visit(v,d);
    }

    if (choice == 'd') {
      v.tell("too bad. you lose some gold. better luck next time");
      v.takeGold(4);

      return Direction.opposite(d);
    }

    return Direction.opposite(d);

  }

  public static void main(String[] args){}

}

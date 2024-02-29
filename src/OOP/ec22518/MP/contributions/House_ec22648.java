package OOP.ec22518.MP.contributions;

import java.util.Random;

class House_ec22648 implements Visitable {
  private Room room1;
  private Room room2;
  private Room room3;
  private Room room4;

  // Constructor defining rooms in my house which I know will work in my program
  public House_ec22648()
  {
    room1 = new Room_ec22648(); // My own room
    room2 = new Room_ec22504(); // A friend's room
    room3 = new Room_ec22430();
    room4 = new Room_ec22434();
  }

  public Direction visit(Visitor visitor, Direction direction)
  {
    Direction oppositeDirection = direction.opposite(direction); // Used when running away from the house

    Random random = new Random();

    visitor.tell("Welcome to the Winchester Mystery Mansion! We hope you enjoy your visit.");
    char choice = visitor.getChoice("Would you like to: "
                                    + "\na) Visit the living room"
                                    + "\nb) Venture into the various rooms" 
                                    + "\nc) Leave the house", new char[]{'a', 'b', 'c'});
    if (choice == 'a')
    {
      visitor.tell("You walk down the grand hallway onwards to the living room, "
                    + "in complete awe of the glass chandelier you trip over a bump in the rug.");
      choice = visitor.getChoice("You notice the eyes of a painting staring at you as you get up, do you: "
                                 + "\na) Inspect the painting"
                                 + "\nb) Carry onwards to the living room", new char[]{'a', 'b'});
      if (choice == 'a')
      {
        visitor.tell("You notice a thick layer of dust building up on the painting, "
                     + "you slide your finger over the border of the painting.");
        visitor.tell("The slight pressure exerted on the border of the painting seems to have triggered a mechanism, "
                     + "you hear a whirring of gears and the painting swings open, revealing a hole in the wall "
                     + "containing a locket of hair.");
        choice = visitor.getChoice("Do you: \na) Take the locket of hair \nb) Shut the painting", new char[]{'a', 'b'});
        if (choice == 'a')
        {
          final Item locketOfHair = new Item("Locket of hair");
          visitor.giveItem(locketOfHair);
          if (random.nextInt(2) != 0) // 50% chance of falling into a trapdoor
          {
            visitor.tell("A trapdoor under the rug sets off and you fall several metres down into a pit");
            visitor.tell("*several hours later*");
            visitor.tell("You are rescued by a group of people after your continuous pleads for help. "
                        + "You scramble out of the house, but you are too far gone to realise " 
                        + "that you lost many of your gold coins in the process.");
            visitor.takeGold(7);
            return oppositeDirection;
          }
        }
        else if (choice == 'b')
        {
          visitor.tell("As you shut the painting you notice a few gold coins on the side of the hole in the wall. "
                       + "You decide to take them before shutting the painting.");
          visitor.giveGold(4);
          visitor.tell("Right as the painting is about to latch back on an arm races out and grips around your neck, "
                       + "you just about manage to escape its grasp... Petrified, you sprint out of the house.");
          return oppositeDirection;
        }
      }
      else if (choice == 'b')
      {
        visitor.tell("You turn the knob of the living room door and enter within... "
                     + "You are pleasently surprised by the sweet aroma and decadent interior.");
        visitor.tell("You notice a misplaced book on one of the lower bookshelves, you walk forward to inspect. "
                     + "You dust off the book to reveal the title 'To Kill a Mockingbird' by Harper Lee. "
                     + "You place the book back in its correct position and walk away...");
        visitor.tell("A fellow visitor asks you to watch their belongings for a few minutes, gifting you a few gold coins.");
        visitor.giveGold(5);
        visitor.tell("The other visitor has collected their belongings and you decide it's time to leave this room.");
        choice = visitor.getChoice("Would you like to: "
                                   + "\na) Visit the other rooms"
                                   + "\nb) Leave the house", new char[]{'a', 'b'});
        visitor.tell("As you leave the living room you notice a small combination safe on the "
                     + "entrance hall table that was not previously there. You have a try at the combination once "
                     + "before moving on...");
        char safeChoice = visitor.getChoice("Input combination: "
                                            + "\na) KAMT"
                                            + "\nb) TAKM"
                                            + "\nc) TKAM"
                                            + "\nd) MKAT", new char[]{'a', 'b', 'c', 'd'});
        if (safeChoice == 'c')
        {
          final Item pearlNecklace = new Item("Pearl Necklace");
          visitor.tell("You hear a click, a twist of the combination lock opens the safe door.");
          visitor.tell("Laying within, a necklace made of iridescent pearls, believed to belong to Sarah Winchester herself");
          visitor.tell("You decide to take the necklace believing it is a symbol of good luck!");
          visitor.giveItem(pearlNecklace);
        }
        else
        {
          visitor.tell("You input the combination and... no luck... you continue onwards");
        }
        if (choice == 'a')
        {
          // Visiting rooms
          visitor.tell("Welcome to the first room!");
          direction = room1.visit(visitor, direction);
          visitor.tell("You walk across the hallway to the second room, admiring the interior of the house");
          direction = room2.visit(visitor, direction);
          visitor.tell("Excited after the previous room, you pace to the next room to see what is in store");
          direction = room3.visit(visitor, direction);
          visitor.tell("You visit the last room of the Winchester Mystery House.");
          direction = room4.visit(visitor, direction);          
          // End of visiting rooms
          visitor.tell("After visiting all the rooms you feel tired, you decide it is time to leave.");
          visitor.tell("We hope you enjoyed your time here!");
          return direction;
        }
        else if (choice == 'b')
        {
          visitor.tell("We hope you enjoyed your time here!");
          return direction;
        }
      }
    }
    else if (choice == 'b')
    {
      // Visiting rooms
      visitor.tell("Welcome to the first room!");
      direction = room1.visit(visitor, direction);
      visitor.tell("You walk across the hallway to the second room, admiring the interior of the house");
      direction = room2.visit(visitor, direction);
      visitor.tell("Excited after the previous room, you pace to the next room to see what is in store");
      direction = room3.visit(visitor, direction);
      visitor.tell("You visit the last room of the Winchester Mystery House.");
      direction = room4.visit(visitor, direction);
      // End of visiting rooms
      visitor.tell("After visiting all the rooms you feel tired, you decide it is time to leave.");
      visitor.tell("We hope you enjoyed your time here!");
      return direction;
    }
    else if (choice == 'c')
    {
      visitor.tell("We hope you enjoyed your time here!");
      return direction;
    }
    return direction;
  }
}

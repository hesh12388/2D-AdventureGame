package OOP.ec22518.MP.contributions;

class House_ec22504 implements Visitable {
  // Private instance variables
  private Room room1;
  private Room room2;
  private Room room3;
  private Room room4;
  private Direction nextDirection;

  // Constructor to assign each room their room from other rooms and my own room
  public House_ec22504() {
    room1 = new Room_ec22504();
    room2 = new Room_ec22430();
    room3 = new Room_ec22615();
    room4 = new Room_ec22434();
  }
  // Visit method
  public Direction visit(Visitor v, Direction d) {
    // possible choices for the user when in the house
    char[] choice1 = { 'a', 'b' };
    char[] choice2 = { 'a', 'b', 'c' };
    char[] choice3 = { 'a', 'b', 'c', 'd' };
    // Assigning the leaving direction 
    Direction leavingDirection = d.opposite(d);
    /* Every entry will take them to the hallway first.When they leave they always leave in the opposing direction. */
    char user_choice = v.getChoice(
        "Welcome to the Main Magic Mansion. You can a) continue into the living room, b) explore the Magic hallway, c) go upstairs into the spooky bedrooms or d) leave the house",
        choice2);
    while (user_choice != 'd') {
      // switch case for choices a b and c.
      switch (user_choice) {
        // case 1
        case 'a':
          room2.visit(v, d);
          // case 2
        case 'b':
          // Items for this choice
          final Item MagicWand = new Item("MagicWand");
          final Item key = new Item("key");
          v.tell("Here in the Hallway, you notice there is a closet closed off by a wooden door.");
          char ChoiceH = v.getChoice(
              "You have two options, a) find the key to the door or b) take a risk and push the door in. Choose wisely",
              choice1);
          // Should be getting the user choice and take them to the necessary room.
          // What should happen If the playe chooses option 1 -->
          if (ChoiceH == 'a') {
            v.tell(
                "Guess the password correctly out of the following 3 options and earn the key to open the mysterious closet.");
            char PassChoice = v.getChoice("a) 1234, b)0414, c) 1788", choice2);
            if (PassChoice == 'a' || PassChoice == 'c') {
              v.tell("Incorrect choice, you loose 3 gold pieces");
              v.takeGold(3);
            } else if (PassChoice == 'b') {
              v.tell(
                  "Luck is on your side, password is correct. YOu have unlocked the key and earned your self 10 gold pieces.");
              v.giveItem(key);
              v.giveGold(10);
              v.tell(
                  "Opening the closet, you have found the Magic Wand. Take it and bring out your inner Harry Potter.");
              v.giveItem(MagicWand);

            }
            // What should happen ifplayer choses option 2 -->
          } else if (ChoiceH == 'b') {
            v.tell("You have successfully knocked down the door, but dropped 8 gold pieces.");
            v.takeGold(8);
            v.tell("You have found the Magic Wand. Take it and bring out your inner Harry Potter.");
            v.giveItem(MagicWand);
          }
        case 'c':
          Direction next;
          v.tell("You will now visit each room once.");
          next = room1.visit(v, d);
          next = room3.visit(v, d);
          next = room4.visit(v, d);
          leavingDirection = next;
        case 'd':
          v.tell("It was nice knowing you...");
      }
    }
    return leavingDirection;
  }
}

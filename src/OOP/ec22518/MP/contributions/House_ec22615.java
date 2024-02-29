package OOP.ec22518.MP.contributions;

// House_ec22615 class
class House_ec22615 implements Visitable {
  // Private instance variables
  private Room room1;
  private Room room2;
  private Room room3;
  private Room room4;
  private Room room5;
  private Room room6;
  private Direction nextDirection;

  // Constructor to assign each room their room from other rooms and my own room
  public House_ec22615() {
    room1 = new Room_ec22615();
    room2 = new Room_ec22430();
    room3 = new Room_ec22615();
    room4 = new Room_ec22434();
    // r5 = new Room_ec22617();
    // r6 = new Room_ec22515();
  }
  // Visit method
  public Direction visit(Visitor v, Direction d) {
    // possible choices for the user when in the house
    char[] choices2 = { 'a', 'b' };
    char[] choices3 = { 'a', 'b', 'c' };
    char[] choices4 = { 'a', 'b', 'c', 'd' };
    // Assigning the leaving direction 
    Direction leavingDirection = d.opposite(d);
    /* Greeting the user when they enter the house. 
    Every entry will take them to the hallway first.When they leave they always leave in the opposing direction. 
    i.e they came into the house in one direction then they lave in the opposite,like a normal house. */
    char user_choice = v.getChoice(
        "Welcome to the main mammoth Hallway. You can a) continue into the living room, b) explore the mammoth of a hallway, c) go upstairs into the bedrooms or d) leave the house",
        choices3);
    while (user_choice != 'd') {
      // switch case for choices a b and c.
      switch (user_choice) {
        // case 1
        case 'a':
          room2.visit(v, d);
          // case 2
        case 'b':
          // Items fo this choice
          final Item invisibilityCloak = new Item("invisibilityCloak");
          final Item key = new Item("key");
          v.tell("Here in the Hallway, you notice there is a closet closed off by a wooden door.");
          char hallwayChoice = v.getChoice(
              "You have two options, a) find the key to the door or b) take a risk and push the door in. Choose wisely",
              choices2);
          // getting the user choice and taking them to the necessary room.
          // If the playe chooses option 1 -->
          if (hallwayChoice == 'a') {
            v.tell(
                "Guess the password correctly out of the following 3 options and earn the key to open the mysterious closet.");
            char playerPasswordChoice = v.getChoice("a) 1234, b)0414, c) 1788", choices3);
            if (playerPasswordChoice == 'a' || playerPasswordChoice == 'c') {
              v.tell("Incorrect choice, you loose 3 gold pieces");
              v.takeGold(3);
            } else if (playerPasswordChoice == 'b') {
              v.tell(
                  "Luck is on your side, password is correct. YOu have unlocked the key and earned your self 5 gold pieces.");
              v.giveItem(key);
              v.giveGold(5);
              v.tell(
                  "Opening the closet, you have found the Invisibility Cloak. Take it and bring out your inner HArry Potter.");
              v.giveItem(invisibilityCloak);

            }
            // If the player choses option 2 -->
          } else if (hallwayChoice == 'b') {
            v.tell("You have successfully knocked down the door, but at the cost of 5 gold pieces.");
            v.takeGold(5);
            v.tell("You have found the Invisibility Cloak. Take it and bring out your inner HArry Potter.");
            v.giveItem(invisibilityCloak);
          }
        case 'c':
          Direction next;
          v.tell("You will now visit each room one.");
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

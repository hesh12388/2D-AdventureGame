package OOP.ec22518.MP.contributions;

class House_ec22858 implements Visitable {
  Room foyer = new Foyer();
  Room kitchen = new Room_ec22858();
  Room homeOffice = new Room_ec22941();
  Room diningRoom = new Room_ec22889();
  Room familyRoom = new Room_ec22919();
  Room guestRoom = new Room_ec22559();

  public Direction visit(Visitor visitor, Direction visitorDirection) {
    visitor.tell(
        "As the sun sets on a small town, an abandoned house sits alone, rumoured to be haunted. You decide to explore it. Those tales can't be true, can they?");
    visitor.tell("Taking a deep breath, you push open the creaky door and step inside.");

    Room whichRoomYoureIn;
    Direction directionYoureGoingNext;

    whichRoomYoureIn = foyer;
    directionYoureGoingNext = foyer.visit(visitor, visitorDirection);

    boolean isInside = true;
    while (isInside) {
      if (whichRoomYoureIn == foyer) {
        if (directionYoureGoingNext == Direction.TO_SOUTH) {
          isInside = false;
        }
      }
      if (isInside) {
        whichRoomYoureIn = whichRoomCanIGoTo(visitor, directionYoureGoingNext, whichRoomYoureIn);
        directionYoureGoingNext = whichRoomYoureIn.visit(visitor, directionYoureGoingNext);
      }
    }

    return Direction.opposite(visitorDirection);
  }

  Room whichRoomCanIGoTo(Visitor visitor, Direction direction, Room roomCurrentlyIn) {
    Room roomGoingTo = foyer;

    if (roomCurrentlyIn == foyer) {
      if (direction == Direction.TO_WEST) {
        roomGoingTo = kitchen;
      }
      if (direction == Direction.TO_NORTH) {
        roomGoingTo = diningRoom;
      }
      if (direction == Direction.TO_EAST) {
        roomGoingTo = familyRoom;
      }
      if (direction == Direction.TO_SOUTH) {
        roomGoingTo = foyer;
      }
    }

    if (roomCurrentlyIn == foyer) {
      if (direction == Direction.TO_WEST) {
        roomGoingTo = kitchen;
      }
      if (direction == Direction.TO_NORTH) {
        roomGoingTo = diningRoom;
      }
      if (direction == Direction.TO_EAST) {
        roomGoingTo = familyRoom;
      }
      if (direction == Direction.TO_SOUTH) {
        roomGoingTo = foyer;
      }
    }

    if (roomCurrentlyIn == kitchen) {
      if (direction == Direction.TO_WEST) {
        visitor.tell("Turns out you can't go that direction. So you just go to the foyer.");
        roomGoingTo = foyer;
      }
      if (direction == Direction.TO_NORTH) {
        roomGoingTo = guestRoom;
      }
      if (direction == Direction.TO_EAST) {
        roomGoingTo = foyer;
      }
      if (direction == Direction.TO_SOUTH) {
        visitor.tell("Turns out you can't go that direction. So you just go to the foyer.");
        roomGoingTo = foyer;
      }
    }

    if (roomCurrentlyIn == guestRoom) {
      if (direction == Direction.TO_WEST) {
        visitor.tell("Turns out you can't go that direction. So you just go to the kitchen.");
        roomGoingTo = kitchen;
      }
      if (direction == Direction.TO_NORTH) {
        visitor.tell("Turns out you can't go that direction. So you just go to the kitchen.");
        roomGoingTo = kitchen;
      }
      if (direction == Direction.TO_EAST) {
        roomGoingTo = diningRoom;
      }
      if (direction == Direction.TO_SOUTH) {
        roomGoingTo = kitchen;
      }
    }

    if (roomCurrentlyIn == diningRoom) {
      if (direction == Direction.TO_WEST) {
        roomGoingTo = guestRoom;
      }
      if (direction == Direction.TO_NORTH) {
        roomGoingTo = homeOffice;
      }
      if (direction == Direction.TO_EAST) {
        visitor.tell("Turns out you can't go that direction. So you just go to the foyer.");
        roomGoingTo = foyer;
      }
      if (direction == Direction.TO_SOUTH) {
        roomGoingTo = foyer;
      }
    }

    if (roomCurrentlyIn == familyRoom) {
      if (direction == Direction.TO_WEST) {
        roomGoingTo = foyer;
      }
      if (direction == Direction.TO_NORTH) {
        visitor.tell("Turns out you can't go that direction. So you just go to the foyer.");
        roomGoingTo = foyer;
      }
      if (direction == Direction.TO_EAST) {
        visitor.tell("Turns out you can't go that direction. So you just go to the foyer.");
        roomGoingTo = foyer;
      }
      if (direction == Direction.TO_SOUTH) {
        visitor.tell("Turns out you can't go that direction. So you just go to the foyer.");
        roomGoingTo = foyer;
      }
    }

    if (roomCurrentlyIn == homeOffice) {
      if (direction == Direction.TO_WEST) {
        visitor.tell("Turns out you can't go that direction. So you just go to the dining room.");
        roomGoingTo = diningRoom;
      }
      if (direction == Direction.TO_NORTH) {
        visitor.tell("Turns out you can't go that direction. So you just go to the dining room.");
        roomGoingTo = diningRoom;
      }
      if (direction == Direction.TO_EAST) {
        visitor.tell("Turns out you can't go that direction. So you just go to the dining room.");
        roomGoingTo = diningRoom;
      }
      if (direction == Direction.TO_SOUTH) {
        roomGoingTo = diningRoom;
      }
    }

    return roomGoingTo;
  }
}

class Foyer extends Room {
  public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
    visitor.tell(
        "You see a kitchen to your left, the dining room straight ahead, and the family room to your right.");

    char[] foyerOptions = { 'a', 'b', 'c', 'd' };
    char foyerChoice = visitor.getChoice(
        "Do you go a) towards the kitchen, b) to the dining room, c) to the family room, or d) back outside.",
        foyerOptions);

    Direction foyerChoiceDirection;
    if (foyerChoice == 'a') {
      foyerChoiceDirection = Direction.TO_WEST;
    } else if (foyerChoice == 'b') {
      foyerChoiceDirection = Direction.TO_NORTH;
    } else if (foyerChoice == 'c') {
      foyerChoiceDirection = Direction.TO_EAST;
    } else if (foyerChoice == 'd') {
      visitor.tell("This is enough haunted house for you today. So you decide to head back outside.");
      foyerChoiceDirection = Direction.TO_SOUTH;
    } else {
      visitor.tell("Unsure what to do, you head back outside.");
      foyerChoiceDirection = Direction.TO_SOUTH;
    }

    return foyerChoiceDirection;
  }
}

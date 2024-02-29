package OOP.ec22518.MP.contributions;

public class House_ec22894 implements Visitable {

    Room room1;
    Room room2;
    Room room3;
    private Room[] rooms;
    private boolean hallwayLight = false;
    private boolean isPainting = true;

    House_ec22894() {
        room1 = new Room_ec22894();
        room2 = new Room_ec22566();
        room3 = new Room_ec22703();
        rooms = new Room[4];
        rooms[0] = room1;
        rooms[1] = room2;
        rooms[2] = room3;

    }
    @Override
    public Direction visit(Visitor visitor, Direction d) {
        int index = 0;
        Item ExitKey = new Item("Exit Key");
        boolean hasnoKey = true;

        visitor.tell("The door slowly creeks open as you enter into a house...");
        visitor.tell("The wind blows violently, slamming the door shut behind you.");
        if (d == Direction.FROM_NORTH) {
            visitor.tell("You arrived into the house from the north.");
            index = 0;
        }
        else if(d == Direction.FROM_SOUTH) {
            visitor.tell("You arrived into the house from the south.");
            index = 2;
        }
        else if(d == Direction.FROM_EAST) {
            visitor.tell("You arrived into the house from the east.");
            index = 2;
        }
        else if(d == Direction.FROM_WEST) {
            visitor.tell("You arrived into the house from the west.");
            index = 1;
        }

        roomName(index, visitor);
        Room current = rooms[index];
        Direction direction = current.visit(visitor, d);

        //inspired by ec22897

        while(hasnoKey) {
            if(direction == Direction.TO_NORTH) {
                if (current == rooms[0]) {
                    visitor.tell("You are at the top, you will be transported back to the bottom of the house.");
                    visitor.tell("You enter the main hallway of the house...");
                    visitor.tell("The lights are dimly lit and an eerie atmosphere takes over you");
                    char choiceMade = visitor.getChoice("You have the option to enter one of 2 rooms, continue exploring the hallway, or leave the house... (1/2/e/l)", new char[] {'1', '2', 'e', 'l'});

                    if(choiceMade == 1) {
                        visitor.tell("You decided to enter the first room...");
                        index = 1;
                        current = rooms[index];
                    }
                    else if(choiceMade == 2) {
                        visitor.tell("You decided to enter the second room...");
                        index = 2;
                        current = rooms[index];
                    }
                    else if (choiceMade == 'e') {
                        visitor.tell("You decided to explore the hallway...");
                        char lightOnorOff = visitor.getChoice("Would you like to turn the light on or off [o/f]", new char[] {'o', 'f'});

                        if(lightOnorOff == 'o') {
                            if(hallwayLight) {
                                visitor.tell("The light is already on");
                            }
                            else {
                                visitor.tell("You turned the light on");
                                hallwayLight = true;
                            }
                        }
                        else {
                            if(hallwayLight) {
                                visitor.tell("The light is now off");
                                hallwayLight= false;
                            }
                            else {
                                visitor.tell("The hallway remains to be in the darkness");
                            }
                        }

                        if(hallwayLight) {
                            if(isPainting) {
                                visitor.tell("After looking around you find a painting on the wall...");
                                char ripPainting = visitor.getChoice("Do you wish to rip the painting off the wall to see what lies behind it [y/n]", new char[] {'y', 'n'});
                                if(ripPainting == 'y') {
                                    visitor.tell("You found a key.. it looks to be a key to exit the house");
                                    visitor.giveItem(ExitKey);
                                    isPainting = false;
                                }
                                else if(ripPainting == 'n') {
                                    visitor.tell("You admire the painting and move on to explore...");
                                }
                            }

                            visitor.tell("After a while of exploring the hallway, you find nothing else...");
                            char secondChoice = visitor.getChoice("Youre back at where the 2 rooms are... do you wish to enter either room or leave [1/2/l]", new char[] {'1', '2', 'l'});
                            if(secondChoice == 1) {
                                visitor.tell("You decided to enter the first room...");
                                index = 1;
                                current = rooms[index];
                            }
                            else if (secondChoice == 2) {
                                visitor.tell("You decided to enter the second room...");
                                index = 2;
                                current = rooms[index];
                            }
                            else if(secondChoice == 'l') {
                                visitor.tell("You may only leave if you have the exit key.");
                                if(visitor.hasEqualItem(ExitKey)) {
                                    visitor.tell("Fortunately, you have the exit key.\n" + "You leave the house ");
                                    return direction;
                                }
                                else {
                                    while(!(visitor.hasEqualItem(ExitKey))) {
                                        visitor.tell("You do not have the exit key, you can find it behind a painting somewhere in the house.");
                                        visitor.tell("You are transported back to the start of the hallway");
                                        char exploreChoice = visitor.getChoice("do you wish to enter either of the rooms or go find the painting [1/2/p]", new char[] {'1', '2', 'p'});
                                        if (exploreChoice == 1) {
                                            visitor.tell("You decided to enter the first room...");
                                            index = 1;
                                            current = rooms[index];
                                        }
                                        else if(exploreChoice == 2) {
                                            visitor.tell("You decided to enter the second room...");
                                            index = 2;
                                            current = rooms[index];
                                        }
                                        else if(exploreChoice == 'p') {
                                            visitor.tell("You went back to the painting and ripped it off");
                                            visitor.tell("You found the exit key laying behind the painting");
                                            visitor.giveItem(ExitKey);
                                            char thirdChoice = visitor.getChoice("do you wish to enter either of the rooms or leave", new char[] {'1', '2', 'l'});
                                            if (thirdChoice == 1) {
                                                visitor.tell("You decided to enter the first room...");
                                                index = 1;
                                                current = rooms[index];
                                            }
                                            else if(thirdChoice == 2) {
                                                visitor.tell("You decided to enter the second room...");
                                                index = 2;
                                                current = rooms[index];
                                            }
                                            else if(thirdChoice == 'l') {
                                                visitor.tell("You have the key, you may leave the house. ");
                                                return direction;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            while(!hallwayLight) {
                                visitor.tell("You explore the hallway in the darkness and trip over a chair...");
                                visitor.tell("Some gold has spilled out of your pockets. You might want to turn the light on so you can see where your stepping next time!");
                                visitor.takeGold(3);
                                char secondLightChoice = visitor.getChoice("Do you wish to turn on the light to the hallway [y/n]", new char[] {'y', 'n'});
                                if(secondLightChoice == 'y') {
                                    visitor.tell("You can now see!");
                                    hallwayLight = true;
                                    visitor.tell("You see a painting in the distance and some rooms nearby");
                                    char getChoice = visitor.getChoice("Do you want to explore either room [1/2] or rip the painting off the wall and see whats behind [p] ", new char[] {'1', '2', 'p'});
                                    if(getChoice == 1) {
                                        visitor.tell("You decided to enter the first room...");
                                        index = 1;
                                        current = rooms[index];
                                    }
                                    else if (getChoice == 2) {
                                        visitor.tell("You decided to enter the second room...");
                                        index = 2;
                                        current = rooms[index];
                                    }
                                    else if (getChoice == 'p') {
                                        visitor.tell("You rip the painting off the wall to unveil whats behind");
                                        visitor.tell("You found a key... seems like it is the exit key for the house.");
                                        visitor.giveItem(ExitKey);
                                        char nextChoice = visitor.getChoice("You can leave now [l] or explore the rooms [1/2]", new char[] {'l', '1', '2'});
                                        if (nextChoice == 'l') {
                                            visitor.tell("You decide to leave.");
                                            return direction;
                                        }
                                        else if (nextChoice == 1) {
                                            visitor.tell("You decided to enter the first room...");
                                            index = 1;
                                            current = rooms[index];
                                        }
                                        else if (nextChoice == 2) {
                                            visitor.tell("You decided to enter the second room...");
                                            index = 2;
                                            current = rooms[index];
                                        }
                                    }
                                }
                                else if (secondLightChoice == 'n') {
                                    hallwayLight = false;
                                }
                            }
                        }

                    }
                    else if(choiceMade == 'l') {
                        visitor.tell("You may only leave if you have the exit key.");
                        if(visitor.hasEqualItem(ExitKey)) {
                            visitor.tell("Fortunately, you have the exit key.\n" + "You leave the house ");
                            return direction;
                        }
                        else {
                            while(!(visitor.hasEqualItem(ExitKey))) {
                                visitor.tell("You do not have the exit key, you can find it behind a painting somewhere in the house.");
                                visitor.tell("You are transported back to the start of the hallway");
                                char exploreChoice2 = visitor.getChoice("do you wish to enter either of the rooms or go find the painting [1/2/p]", new char[] {'1', '2', 'p'});
                                if (exploreChoice2 == 1) {
                                    visitor.tell("You decided to enter the first room...");
                                    index = 1;
                                    current = rooms[index];
                                }
                                else if(exploreChoice2 == 2) {
                                    visitor.tell("You decided to enter the second room...");
                                    index = 2;
                                    current = rooms[index];
                                }
                                else if(exploreChoice2 == 'p') {
                                    visitor.tell("You went back to the painting and ripped it off");
                                    visitor.tell("You found the exit key laying behind the painting");
                                    visitor.giveItem(ExitKey);
                                    char fourthChoice = visitor.getChoice("do you wish to enter either of the rooms or leave", new char[] {'1', '2', 'l'});
                                    if (fourthChoice == 1) {
                                        visitor.tell("You decided to enter the first room...");
                                        index = 1;
                                        current = rooms[index];
                                    }
                                    else if(fourthChoice == 2) {
                                        visitor.tell("You decided to enter the second room...");
                                        index = 2;
                                        current = rooms[index];
                                    }
                                    else if(fourthChoice == 'l') {
                                        visitor.tell("You have the key, you may leave the house. ");
                                        return direction;
                                    }
                                }
                            }
                        }
                    }
                }
                else if ( (current == rooms[1]) || (current== rooms[2])) {
                    index = 0;
                    current = rooms[index];
                }
            }
            else if (direction == Direction.TO_WEST) {
                if((index == 0) || index == 1) {
                    visitor.tell("You will be wrapped around back to room 3");
                    index = 2;
                    current = rooms[index];
                } else if (index == 2) {
                    index = 1;
                }
            } else if (direction == Direction.TO_EAST) {
                if((index == 0) || index == 2) {
                    visitor.tell("You will be wrapped around back to room 2");
                    index = 1;
                    current = rooms[index];
                } else if (index == 1) {
                    index = 2;
                    current = rooms[index];
                }
            }
            else if(direction == Direction.TO_SOUTH) {
                if((index == 0)) {
                    visitor.tell("You are going to room 2");
                    roomName(index, visitor);
                    index = 1;
                    current = rooms[index];
                }
                else if ((index == 1) || (index == 2)) {
                    visitor.tell("You entered the hallway...");
                    char choices = visitor.getChoice("You can choose to enter either rooms [1/2] or leave", new char[] {'1', '2', 'l'});
                    if(choices == 1) {
                        visitor.tell("You decided to enter the first room...");
                        index = 1;
                        current = rooms[index];
                    }
                    else if (choices==2) {
                        visitor.tell("You decided to enter the second room...");
                        index = 2;
                        current = rooms[index];
                    }
                    else if (choices == 'l') {
                        if(visitor.hasEqualItem(ExitKey)) {
                            visitor.tell("You may leave since you have the key");
                            return direction;
                        }
                        else {
                            visitor.tell("You do not have the key. Try finding it somewhere else in the house. You will be redirected to room 2");
                            index = 1;
                            current = rooms[1];
                            break;
                        }
                    }
                }
            }
            hasnoKey = true;
        }

        if (d == Direction.TO_NORTH) {
            visitor.tell("You left the previous room HEADING SOUTH.");
        } else if (d == Direction.TO_EAST) {
            visitor.tell("You left the previous room HEADING WEST");
        } else if (d == Direction.TO_SOUTH) {
            visitor.tell("You left the previous room HEADING NORTH.");
        } else if (d == Direction.TO_WEST) {
            visitor.tell("You left the previous room HEADING EAST.");
        }

        return d;
    }

    private void roomName(int index, Visitor visitor) {
        String roomName = "";
        if(index == 0) {
            roomName = "Room_ec22894";
        }
        else if(index == 1) {
            roomName = "Room_ec22566";
        }
        else if (index == 2) {
            roomName = "Room_ec22703";
        }

        visitor.tell("You are entering room " + (index+1) + " owned by " + roomName);
    }
}

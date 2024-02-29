package OOP.ec22518.MP.contributions;

class House_ec22447 implements Visitable {

    private Room firstRoom;
    private Room secondRoom;
    private Room thirdRoom;

    public House_ec22447() {
        firstRoom = new Room_ec22475();
        secondRoom = new Room_ec22466();
        thirdRoom = new Room_ec22927();
    }
        public Direction visit (Visitor visitor, Direction directionVistorArrivesFrom){
            char[] choices = {'a', 'b', 'y', 'n'};

            visitor.tell("Welcome to my house!");

            while (true) {

                char roomChoice = visitor.getChoice("You are in the corridor with two rooms : one to your left (a) the other to your right(b). Which room would you like to enter?", choices);
                switch (roomChoice) {
                    case 'a':
                        visitor.tell("Congratulations you have entered the largest room in this house");
                        directionVistorArrivesFrom = firstRoom.visit(visitor, Direction.FROM_SOUTH);
                        break;
                    case 'b':
                        visitor.tell("You have entered the second room of the house");
                        directionVistorArrivesFrom = secondRoom.visit(visitor, Direction.FROM_NORTH);
                        break;
                    default:
                        visitor.tell("Invalid choice. Please choose a valid option.");
                        continue;
                }


                char torchChoice = visitor.getChoice("You now find yourself in the attic where darkness has emerged. You are offered a torch. Do you accept (y/n)", choices);
                switch (torchChoice) {
                    case 'y':
                        visitor.giveItem(new Item("Torch"));
                        while (true) {
                            char atticChoice = visitor.getChoice("You find a third door hidden behind a cupboard. Would you like to (a) enter the room (b) leave the attic", choices);
                            switch (atticChoice) {
                                case 'a':
                                    visitor.tell("You have found the final hidden room in the house!!");
                                    directionVistorArrivesFrom = thirdRoom.visit(visitor, Direction.FROM_EAST);
                                    break;
                                case 'b':
                                    visitor.tell("You have left the attic");
                                    break;
                                default:
                                    visitor.tell("Invalid choice. Please choose a valid option.");
                                    continue;
                            }
                            break;
                        }
                        break;
                    case 'n':
                        visitor.tell("Good luck exploring in the dark :)");
                        break;
                    default:
                        visitor.tell("You have completed the house tour. Goodbye");
                        return Direction.FROM_WEST;
                }
                break;
            }

            return Direction.opposite(directionVistorArrivesFrom);
        }
    }

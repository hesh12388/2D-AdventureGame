package OOP.ec22518.MP.contributions;

import java.lang.Exception;

class House_ec22466 implements Visitable {
    
    static class InvalidInputException extends Exception {
        public InvalidInputException(String message){
            super(message);
        }
    }
    
    private Room[] rooms;
    private int number_of_rooms = 2;

    House_ec22466() {
        rooms = new Room[number_of_rooms];
        rooms[0] = new Room_ec221247();
        rooms[1] = new Room_ec22415();
    }

public Direction visit(Visitor visitor, Direction direction) {
    
    boolean present = true;
    char[] options = {'a', 'b'};
    boolean valid = false;

    visitor.tell("Welcome to my house, lets play a survival game, I will present you with 2 challenges, if you manage to succesfully pass the challenges given to you I will give you freedom, but if you dont you will be trapped here forever!");

    while (!valid) {
        try {
            char decision = visitor.getChoice("Okay pick between these 2 rooms, a) The room to the west covered in cob webs or, b)The room to the east which has a red door.", options);

            if (decision == 'a') {
                direction = Direction.TO_WEST;
                visitor.tell("You have entered the room to the west, here is your first challenge, the rooms is filled with posinous snakes.");
                char c1 = visitor.getChoice("will you a) sing a song to the snakes to make them fall asleep and escape out the room, or b) try launch yourself out of the window.", options);

                if (c1 == 'b') {
                    visitor.tell("Dont be silly, singing a song wont make snakes fall asleep");
                    present = false;
                } else {
                    visitor.tell("Well done lets move on to the next challenge");
                    present = true;
                }
                valid = true;
            } else if (decision == 'b') {
                direction = Direction.TO_EAST;
                visitor.tell("If you picked the room to the east, then you automatically lose the challenge as a red door signals death.");
                present = false;
                valid = true;
            } else {
                throw new InvalidInputException("Invalid choice. Please enter 'a' or 'b'.");
            }
        } catch (InvalidInputException e) {
            visitor.tell("Error: " + e.getMessage());
        }
    }
    return direction;
}

    }

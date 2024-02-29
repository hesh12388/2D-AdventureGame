package OOP.ec22518.MP.contributions;

class House_ec22572 implements Visitable {
    Room room_1;
    Room room_2;
    Room room_3;
    Room room_4;

    House_ec22572() {

        // setting the rooms
        Room room_1 = new Room_ec22572(); // south of room_2 and west of room 3
        Room room_2 = new Room_ec22872(); // north of room_1 and west of room_3
        Room room_3 = new Room_ec20258(); // east of room_2 and north of room_3
        Room room_4 = new Room_ec20315(); // south of room_3 and east of room_1
    }

    public Direction visit(Visitor v, Direction d) {
        char[] choices = {'a','b'};
        String position = "room_1";
        String leave = "false";
        // starting the user in the first room
        v.tell("Hello visitor, you have stumbled upon my home lets goto the first room!");
        d = room_1.visit(v, Direction.TO_NORTH);

        while (leave.equals("false")){
            if (position.equals("room_1")) {
                v.tell("You have just exited the first room you are now in a hallway");
                v.tell("Would you a)go north(room 2) b)go south (leave the house");
                char room_1_choice = v.getChoice("Pick carefully!", choices);
                if (room_1_choice == 'a') {
                    position = "room_2";
                    room_2.visit(v, Direction.TO_NORTH);
                } else if (room_1_choice == 'b') {
                    position = "leave_house";
                    leave = "true";
                }
            }else if (position.equals("room_2")) {
                v.tell("You have just exited the second room you are now in a hallway");
                v.tell("Would you a)go east (room 3) b)go south (room 1)");
                char room_2_choice = v.getChoice("Pick Carefully!", choices);
                if (room_2_choice == 'a') {
                    position = "room_3";
                    room_3.visit(v, Direction.TO_EAST);
                } else if (room_2_choice == 'b') {
                    position = "room_1";
                    room_1.visit(v, Direction.TO_SOUTH);
                }
            }else if (position.equals("room_3")) {
                v.tell("You have just exited the third room you are now in a hallway");
                v.tell("Would you a)south (room 4) b)go west (room 2)");
                char room_3_choice = v.getChoice("Pick Carefully!", choices);
                if (room_3_choice == 'a') {
                    position = "room_4";
                    room_4.visit(v, Direction.TO_SOUTH);
                } else if (room_3_choice == 'b') {
                    position = "room_2";
                    room_2.visit(v, Direction.TO_WEST);
                }
            }else if (position.equals("room_4")) {
                v.tell("You have just exited the fourth room you are now in a hallway");
                v.tell("Would you a)go west (room 1) b)go north (room 3)");
                char room_4_choice = v.getChoice("Pick Carefully!", choices);
                if (room_4_choice == 'a') {
                    position = "room_1";
                    room_1.visit(v, Direction.TO_WEST);
                } else if (room_4_choice == 'b') {
                    position = "room_3";
                    room_3.visit(v, Direction.TO_NORTH);
                }
            }

        }
        v.tell("You are now exiting my house bye!!");
        return Direction.opposite(d);
    }
}

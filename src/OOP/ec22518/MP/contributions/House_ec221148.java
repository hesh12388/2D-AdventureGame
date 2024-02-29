package OOP.ec22518.MP.contributions;

class House_ec221148 implements Visitable {
    Room[] rooms = {new Room_ec221148(), new Room_ec22436(), new Room_ec22917(), new Room_ec221136(), new Room_ec22589(), new Room_ec22623()};
    boolean visited[] = {false, false, false, false, false, false};
    int latestChoice;
    public Direction visit(Visitor v, Direction d) {
        v.tell("As you approach the wrought-iron gates of Shadow Manor, a feeling of unease creeps over you. The sky above is heavy with dark clouds, and the wind howls through the branches of the gnarled trees that surround the sprawling estate. The Manor itself looms before you, a foreboding presence that seems to crouch on the edge of the world, waiting to pull you into its shadows. Despite the overgrown garden and the sense of abandonment, the house still exudes a strange magnetism that draws you closer. You can't help but wonder what secrets and horrors lie within its walls. As you step through the creaking gate, you realize that the only way to satisfy your curiosity is to venture inside and face whatever darkness awaits you.");
        v.tell("As you step inside the entrance hall of Shadow Manor, the heavy wooden door creaks shut behind you, sealing you in. You find yourself standing in a dimly lit corridor, shrouded in shadow and dust. The walls are adorned with ancient tapestries that hang limply, the threadbare fabric revealing only faint traces of what were once grand designs. The air is thick with the musty scent of age, and every step you take sends plumes of dust dancing into the air.");
        v.tell("To your left and right, six doors line the hallway, each adorned with tarnished brass handles and locks. The doors seem to loom ominously, like silent sentinels guarding their secrets. As you approach them, you notice that each door is slightly ajar, as if beckoning you to enter.");
        v.tell("As you stand there, unsure of which way to turn, you can't shake the feeling that you are being watched. You glance over your shoulder, but the hallway behind you is empty. You listen closely, and in the stillness, you swear you hear whispers emanating from behind one of the doors. But as you strain to hear more, the whispers fade away, leaving you with an uneasy feeling. You realize that you must choose which door to open, and with a deep breath, you take a step forward, steeling yourself for whatever lies ahead.");
        v.tell("The rooms are numbered from 1 to 6, with 1 being the furthest left, 2 the furthest right, 3 the middle left, 4 the middle right, 5 the closest left and 6 the closest right");
        char firstChoice = v.getChoice("Which room number do you choose?[1-6]", new char[] {'1', '2', '3', '4', '5', '6'});
        if (Integer.parseInt(String.valueOf(firstChoice)) == 1 || Integer.parseInt(String.valueOf(firstChoice)) == 3 || Integer.parseInt(String.valueOf(firstChoice)) == 5) {
            d = rooms[Integer.parseInt(String.valueOf(firstChoice)) - 1].visit(v, Direction.TO_WEST);
        } else {
            d = rooms[Integer.parseInt(String.valueOf(firstChoice)) - 1].visit(v, Direction.TO_EAST);
        }
        latestChoice = Integer.parseInt(String.valueOf(firstChoice)) - 1;
        while (!checkVisited() && !(latestChoice == '6')) {
            if (((latestChoice == 0 || latestChoice == 2 || latestChoice == 4) && d.equals(Direction.TO_EAST)) || ((latestChoice == 1 || latestChoice == 3 || latestChoice == 4) && d.equals(Direction.TO_WEST)) ) {
                visited[latestChoice] = true;
                if (checkVisited()) {
                    latestChoice = Integer.parseInt(String.valueOf(v.getChoice("You are back in the hallway, but now notice a new door at the end of the hallway.[1-7]", new char[] {'1', '2', '3', '4', '5', '6', '7'}) - 49));
                    d = rooms[latestChoice].visit(v, Direction.TO_WEST);
                } else {
                    latestChoice = Integer.parseInt(String.valueOf(v.getChoice("You are back in the hallway, pick a door to go through.[1-6]", new char[] {'1', '2', '3', '4', '5', '6'}) - 49));
                    d = rooms[latestChoice].visit(v, Direction.TO_EAST);
                }
            } else if (latestChoice == 0) {
                visited[latestChoice] = true;
                if (d.equals(Direction.TO_NORTH)) {
                    latestChoice = 4;
                    d = rooms[latestChoice].visit(v, Direction.TO_NORTH);
                } else if (d.equals(Direction.TO_SOUTH)) {
                    latestChoice = 2;
                    d = rooms[latestChoice].visit(v, Direction.TO_SOUTH);
                } else if (d.equals(Direction.TO_WEST)) {
                    latestChoice = 1;
                    d = rooms[latestChoice].visit(v, Direction.TO_WEST);
                }
            } else if (latestChoice == 2) {
                visited[latestChoice] = true;
                if (d.equals(Direction.TO_NORTH)) {
                    latestChoice = 0;
                    d = rooms[latestChoice].visit(v, Direction.TO_NORTH);
                } else if (d.equals(Direction.TO_SOUTH)) {
                    latestChoice = 4;
                    d = rooms[latestChoice].visit(v, Direction.TO_SOUTH);
                } else if (d.equals(Direction.TO_WEST)) {
                    latestChoice = 3;
                    d = rooms[latestChoice].visit(v, Direction.TO_WEST);
                }
            } else if (latestChoice == 4) {
                visited[latestChoice] = true;
                if (d.equals(Direction.TO_NORTH)) {
                    latestChoice = 2;
                    d = rooms[latestChoice].visit(v, Direction.TO_NORTH);
                } else if (d.equals(Direction.TO_SOUTH)) {
                    latestChoice = 0;
                    d = rooms[latestChoice].visit(v, Direction.TO_SOUTH);
                } else if (d.equals(Direction.TO_WEST)) {
                    latestChoice = 5;
                    d = rooms[latestChoice].visit(v, Direction.TO_WEST);
                }
            } else if (latestChoice == 1) {
                visited[latestChoice] = true;
                if (d.equals(Direction.TO_NORTH)) {
                    latestChoice = 5;
                    d = rooms[latestChoice].visit(v, Direction.TO_NORTH);
                } else if (d.equals(Direction.TO_SOUTH)) {
                    latestChoice = 3;
                    d = rooms[latestChoice].visit(v, Direction.TO_SOUTH);
                } else if (d.equals(Direction.TO_EAST)) {
                    latestChoice = 0;
                    d = rooms[latestChoice].visit(v, Direction.TO_EAST);
                }
            } else if (latestChoice == 3) {
                visited[latestChoice] = true;
                if (d.equals(Direction.TO_NORTH)) {
                    latestChoice = 1;
                    d = rooms[latestChoice].visit(v, Direction.TO_NORTH);
                } else if (d.equals(Direction.TO_SOUTH)) {
                    latestChoice = 5;
                    d = rooms[latestChoice].visit(v, Direction.TO_SOUTH);
                } else if (d.equals(Direction.TO_EAST)) {
                    latestChoice = 2;
                    d = rooms[latestChoice].visit(v, Direction.TO_EAST);
                }
            } else if (latestChoice == 5) {
                visited[latestChoice] = true;
                if (d.equals(Direction.TO_NORTH)) {
                    latestChoice = 3;
                    d = rooms[latestChoice].visit(v, Direction.TO_NORTH);
                } else if (d.equals(Direction.TO_SOUTH)) {
                    latestChoice = 1;
                    d = rooms[latestChoice].visit(v, Direction.TO_SOUTH);
                } else if (d.equals(Direction.TO_EAST)) {
                    latestChoice = 4;
                    d = rooms[latestChoice].visit(v, Direction.TO_EAST);
                }
            }
        }
        v.tell("You have managed to escape Shadow Manor, congratulations.");
        return Direction.TO_NORTH;
    }
    public boolean checkVisited() {
        return visited[0] && visited[1] && visited[2] && visited[3] && visited[4] && visited[5];
    }
  }

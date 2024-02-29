package OOP.ec22518.MP.contributions;

class House_ec22820 implements Visitable {

    Room[] rooms = getRandomRooms();
    static Item rustyKnife = new Item("Rusty Knife");
    static Item key1 = new Item("Key");
    static Item key2 = new Item("key");
    static Item key3 = new Item("KEY");



    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        boolean leave = false;
        for(int i = 0; i < rooms.length; i++) {
            Direction dir = rooms[0].visit(visitor, directionVistorArrivesFrom);

            leave = doRandomHallway(visitor);

            if(leave) {
                return Direction.opposite(dir);
            }
        }
        return Direction.opposite(directionVistorArrivesFrom);
    }

    public Room[] getRandomRooms() {
        Room[] out = new Room[4];
        /* for(int i = 0; i < 4; i++) {
            int random = (int) (Math.random() * Contributions.getRoomUsernames().length);
            out[i] = Contributions.newRoomByUsername(Contributions.getRoomUsernames()[random]);
        } */ //Doesnt work because half the rooms dont work
        out[0] = new Room_ec22486();
        out[1] = new Room_ec22646();
        out[2] = new Room_ec22519();
        out[3] = new Room_ec22871();
        return out;
    }

    public boolean doRandomHallway(Visitor visitor) {
        char choice;
        int random = (int) (Math.random() * 4);

        if(random == 0) {
            visitor.tell("As you exit the room, the door smashes behind you, you find yourself in a gloomy corridor with cobwebs and dust everywhere.");
            visitor.tell("You see an old rusty knife on the floor to your left, do you go to pick it up?");
            choice = visitor.getChoice("a: pick it up, b: ignore it and keep going", new char[]{'a', 'b'});
            if(choice == 'a') {
                visitor.giveItem(rustyKnife);
                visitor.tell("You pick up the knife and put it in your pocket, might be useful later");
            } else {
                visitor.tell("You keep moving forward toward the next room");
            }
            return false;
        }
        else if(random == 1) {
            visitor.tell("You see a mysterious figure around the corner, he talks to you");
            visitor.tell("???: Do you happen to have any .. gold on you?");
            choice = visitor.getChoice("a: yes, b: no", new char[]{'a', 'b'});
            if(choice == 'a') {
                visitor.takeGold('5');
                visitor.tell("???: Thank you, kind traveler");
                visitor.tell("You see the figure walk back around the corner and dissapear, you keep moving forward");
                return false;
            } else {
                visitor.tell("???: Oh, well, I guess I'll just have to take it from you then");
                visitor.tell("You start running and eventually find yourself out of the house as the man stops chasing you");
                return true;
            }
        }
        else if(random == 2) {
            visitor.tell("You end up in a corridor covered completely with overgrown spiky plants, you can't see anything.");
            if(visitor.hasEqualItem(rustyKnife)) {
                visitor.tell("You find the old knife from earlier, in your pocket, you use it to cut the plants away");
                return false;
            } else {
                visitor.tell("You check if you have anything to cut the plants with but end up backtracking out of the house");
                return true;
            }
        }
        else if(random == 3) {
            visitor.tell("You see a weird looking crack on the brick wall, looks like it has been moved");
            choice = visitor.getChoice("a: Push the brick b: continue moving", new char[]{'a', 'b'});
            if(choice == 'a') {
                visitor.tell("You push the brick back and it revelas a hidden section behind the wall, you push the rest of the bricks and see a locked chest.");
                if(visitor.hasEqualItem(key1) || visitor.hasEqualItem(key2) || visitor.hasEqualItem(key3)) {
                    visitor.tell("You use the key from one of the houses and open the chest, you find 8 gold inside.");
                    visitor.giveGold(8);
                } else {
                    visitor.tell("You don't have a key to open the chest, you keep moving");
                }
            } else {
                visitor.tell("You keep moving");
            }
            return false;
        }
        return false;
    }
    
}

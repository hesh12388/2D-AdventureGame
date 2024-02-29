package OOP.ec22518.MP.contributions;

class House_ec22632 implements Visitable {
    private Room room1;
    private Room room2;
    private Room room3;

    House_ec22632() {
        room1 = new Room_ec22632();
        room2 = new Room_ec22972();
        room3 = new Room_ec22630();
    }
    @Override
    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        System.out.println("Welcome to ec_22632's house! ");
        char[] lobbyChoice = {'a','b','c','d'};
        boolean present = true;
        String currentRoom = "Lobby";
        Direction room1Return = directionVistorArrivesFrom;
        Direction room2Return = directionVistorArrivesFrom;
        Direction room3Return = directionVistorArrivesFrom;
        while (present){
            visitor.tell("You are currently in the lobby of the house.");
            currentRoom = "Lobby";
            char lobbyChoice2 = visitor.getChoice("Would you like to: a) Go to the room on your left, b) Go to the room in front of you, c) Go to the room on your right, or d) Leave the house", lobbyChoice);
            if (lobbyChoice2 == 'a'){
                room2Return = room2.visit(visitor, Direction.FROM_EAST);
                currentRoom = "Room2";
                if (room2Return == Direction.TO_EAST){
                    visitor.tell("You return to the lobby room");
                }
                else {
                    visitor.tell("You leave the house through the left room... I didn't think that was possible...");
                    present = false;
                }
            }
            if (lobbyChoice2 == 'b'){
                room1Return = room1.visit(visitor, Direction.FROM_SOUTH);
                currentRoom = "Room1";
                if (room1Return == Direction.TO_SOUTH){
                    visitor.tell("You somehow find your way back to the lobby room from those scary woods.");
                }
                else {
                    visitor.tell("You don't know where you're heading, but you know you've left the house.");
                    present = false;
                }
            }
            if (lobbyChoice2 == 'c'){
                room3Return = room3.visit(visitor, Direction.FROM_WEST);
                currentRoom = "Room3";
                if (room3Return == Direction.TO_WEST){
                    visitor.tell("You return to the lobby room");
                }
                else {
                    visitor.tell("You leave the house through the right room... I didn't think that was possible...");
                    present = false;
                }
            }
            if (lobbyChoice2 == 'd'){
                visitor.tell("You exit through the main doors, which are facing south");
                present = false;
            }
        }
        if(currentRoom=="Room1"){return room1Return;}
        if(currentRoom=="Room2"){return room2Return;}
        if(currentRoom=="Room3"){return room3Return;}
        else{return Direction.TO_SOUTH;}
    }
}

package OOP.ec22518.MP.contributions;

class House_ec22707 implements Visitable {
    private Room roomOne;
    private Room roomTwo;
    private Room roomThree;
    private Boolean[] visited = {false, false, false};
    House_ec22707(){
        roomOne = new Room_ec22707();
        roomTwo = new Room_ec22702();
        roomThree = new Room_ec22703();
    }
    public Direction visit(Visitor v, Direction d) {
        final char[] possibleChoices = {'l', 'r', 'c'};
        final char[] roomDecision = {'a', 'b'};
        final char[] yOrN = {'y', 'n'};
        char leave = 'b';
        char decision;
        v.tell("Welcome to the Winchester House.");
        v.tell("You welcome yourself through the large door.");
        v.tell("You find yourself standing in a long corridor with a door at the end and one on either side of you.");
        while(leave!='a'){
            decision = v.getChoice("Which path will you take? (l/r/c)", possibleChoices);
            if (decision=='l'){
                if(!hasVisited(v, decision, visited, yOrN)) {
                    d = roomOne.visit(v, d);
                    visited[0] = !visited[0];
                    leave = leaveHouse(v, roomDecision);
                }
            } else if (decision=='r'){
                if(!hasVisited(v, decision, visited, yOrN)){
                    d = roomThree.visit(v, d);
                    visited[1]=!visited[1];
                    leave = leaveHouse(v, roomDecision);
                }
            } else if (decision=='c'){
                if(!hasVisited(v, decision, visited, yOrN)) {
                    d = roomOne.visit(v, d);
                    visited[2] = !visited[2];
                    leave = leaveHouse(v, roomDecision);
                }
            }
        }
        return d;
    }

    private char leaveHouse(Visitor v, char[] roomDecision){
        char choice = v.getChoice("Would you like to a) leave the house or b) visit another room?", roomDecision);
        if (choice=='a'){
            v.tell("You have made your escape from the house... or have you");
            return 'a';
        }
        v.tell("You find yourself back in the corridor");
        return 'b';
    }

    private boolean hasVisited(Visitor v, char decision, Boolean[] visited, char[] yOrN){
        boolean visitedAlready = false;
        if (decision=='l'){
            if(visited[0]){
                char choice = v.getChoice("You have already visited this room, are you sure you want to visit it again? (y/n)", yOrN);
                if (choice=='y'){
                    visitedAlready = false;
                } else {
                    visitedAlready = true;
                }
            }
        } else if (decision=='r'){
            if(visited[1]){
                char choice = v.getChoice("You have already visited this room, are you sure you want to visit it again? (y/n)", yOrN);
                if (choice=='y'){
                    visitedAlready = false;
                } else {
                    visitedAlready = true;
                }
            }
        } else if (decision=='c'){
            if(visited[2]){
                char choice = v.getChoice("You have already visited this room, are you sure you want to visit it again? (y/n)", yOrN);
                if (choice=='y'){
                    visitedAlready = false;
                } else {
                    visitedAlready = true;
                }
            }
        }
       return visitedAlready;
    }
}

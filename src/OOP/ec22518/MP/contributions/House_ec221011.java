package OOP.ec22518.MP.contributions;

class House_ec221011 implements Visitable {
    Room_ec221011 R1 = new Room_ec221011();
    Room_ec22520 R2 = new Room_ec22520();
    Room_ec22738 R3 = new Room_ec22738();
    
    public Direction visit(Visitor vis, Direction dir){
        char [] choices = {'a', 'b', 'c', 'd'};
        
        Direction D1 = R1.visit(vis, dir);
        vis.tell("Well Done for the money.");
        vis.tell("You exit the room through a door.");
        
        vis.tell("You're in the hallway. In order to enter the next room you must answer this question.");
        vis.tell("How many planets are in our Solar System?");
        char choice = vis.getChoice("Is it: a) 9 or b) 6 or c) 8 or d) 21.", choices);
        if(choice == 'c'){
            vis.tell("Correct!! Enter the roooom.");
        }
        else{
            vis.tell("Wrooooooong!!!");
            vis.takeGold(2);
            vis.tell("Ha Ha. You Jokeman.");
        }
        
        Direction D2 = R2.visit(vis, dir);
        vis.tell("You exit the room through a door.");
        
        Direction D3 = R3.visit(vis, dir);
        vis.tell("You exit the room through a door. It leads to outside the house.");
        
        return Direction.TO_SOUTH;
    }
}

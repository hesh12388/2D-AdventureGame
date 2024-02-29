package OOP.ec22518.MP.contributions;

class House_ec221008 implements Visitable {
    
    private Room north;
    private Room east;
    private Room south;
    private Room west;
    Item extra_life = new Item("extra_life");
    boolean cat_gift = false;
    boolean exit = false;
        
    House_ec221008() {
        north = new Room_ec221008();
        east = new Room_ec22494();
        south = new Room_ec22741();
        west = new Room_ec22627();
    }
    
    public Direction visit (Visitor v, Direction d) {
        v.tell("You have ended up in a dark hallway. It is dark, yet enough light creaks in for you to notice 4 doors surrounding you on every direction.");
        v.tell("Strangely, you also notice a cat staring at you from across the hallway. It would be too rude to ignore the cat right?");
        
        
        char[] choices = {'N', 'E', 'S', 'W', 'P'};
        
        v.tell("Will you head to the (N)orth, (E)ast, (S)outh, (W)est, or will you decide to (P)et the cat?");
        char option = v.getChoice("Choose an option: ", choices);
        
        while(!exit){
            if(option=='P') {
                if(!cat_gift){
                    v.tell("You decide to pet the cat, to relieve yourself of your anxiety.");
                    v.tell("Luckily for you, the cat has a present for you! It has decided to donate one of its lives to you as an act of kindness!");
                    v.tell("How sweet!");
                    v.giveItem(extra_life);
                    cat_gift = true;
                    v.tell("You now decide to explore the rest of the house with your newfound courage.");
                }
                else {
                    v.tell("You pet the cat, and whilst the cat is still pleased with your kind gesture, it does not have any gifts for you this time.");
                    v.tell("Unlucky!");
                    v.tell("You decide to explore the rest of the house now.");
                }
                v.tell("Will you head to the (N)orth, (E)ast, (S)outh, (W)est, or will you decide to (P)et the cat?");
                option = v.getChoice("Choose an option: ", choices);
            }
            else if(option=='N') {
                d = north.visit(v, d);
                if(!cat_gift) {
                    v.tell("As you are leaving, the cat from earlier gives you a sad look as it is abandoned. While you feel bad, you have no choice but to leave it alone.");
                }
                else {
                    v.tell("As you are leaving, the cat from earlier gives you a look of hope for your next adventures!");
                }
                exit = true;
            }
            else if(option=='E') {
                d = east.visit(v, d);
                v.tell("You decide that you no longer want to explore this house.");
                exit = true;
            }
            else if(option=='S') {
                d = south.visit(v, d);
                v.tell("As you are leaving, you wave goodbye to the cat.");
                v.tell("This makes you wonder how that cat even got here...");
                exit = true;
            }
            else if(option=='W') {
                d = west.visit(v, d);
                v.tell("You physically cannot handle any more of this house, and decide to flee.");
                exit = true;
            }
            else {
                v.tell("You have not picked a valid option.");
                v.tell("Will you head to the (N)orth, (E)ast, (S)outh, (W)est, or will you decide to (P)et the cat?");
                option = v.getChoice("Choose an option: ", choices);
            }
        }
        return d;
    }
}

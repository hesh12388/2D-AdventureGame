package OOP.ec22518.MP.contributions;

class House_ec221024 implements Visitable {
    private Room grand_hall;
    private Room reception;
    private Room living_room;
    char[] multipleChoice = {'A','B','C','D'};
    Direction leaving = Direction.UNDEFINED;
    
    public House_ec221024(){
        grand_hall = new Room_ec221015();
        reception = new Room_ec221024();
        living_room = new Room_ec22413();
    }
    
    public Direction visit(Visitor person, Direction direction){
        
        boolean leave = true;
        
        person.tell("You are standing at the gates of an austere looking mansion.");
        person.tell("Walking through the gates and into the house you go, in front");
        person.tell("of you is a three-way split.");
        person.tell("How would you like to proceed?");
        
        
        while(!leave){
            char choice = person.getChoice("A)Head into the Grand Hall  B)Head into the Reception  C)Head into the Living Room  D)Leave the house", multipleChoice);
            if (choice == 'a'){
                direction = grand_hall.visit(person, direction);
                person.tell("You just visited the Grand Hall.");
                Minigame(person, "Grand Hall");
                leave = false;
            }else if(choice == 'b'){
                direction = reception.visit(person, direction);
                person.tell("You just visited the Reception.");
                Minigame(person, "Reception");
                leave = false;
            }else if(choice == 'c'){
                direction = living_room.visit(person, direction);
                person.tell("You just visited the Living Room.");
                Minigame(person, "Living Room");
                leave = false;
            }else if(choice == 'd'){
                leave = true;
                leaving = Direction.TO_SOUTH;
                direction = leaving;
            }else{
                person.tell("That wasn't an option");
            }
        }
    
        return direction;
    }
    public static void Minigame(Visitor person, String name) {
        person.tell("After coming back from the " + name + ", the house owner would like to propose a minigame to gain some coins!");
        person.tell("There are currently two games available to play.");

        char[] multipleChoice = {'A', 'B'};
        char choice = person.getChoice("A)Roll the die   or   B)Heads or Tails", multipleChoice);

        if (choice == 'A') {
            int roll = (int) (Math.random() * 6) + 1;
            person.tell("You rolled a " + roll + "!");
            if (roll >= 4) {
                person.giveGold(10);
                person.tell("Congratulations! You won 10 coins!");
            } else {
                person.takeGold(5);
                person.tell("Better luck next time, you lost 5 coins.");
            }
        } else if (choice == 'B') {
            char[] game_choices = {'H', 'T'};
            char game_choice = person.getChoice("Heads or tails? H) Heads  T) Tails", game_choices);
            boolean isHeads = Math.random() < 0.5; //if the random generated number is less than 0.5 then that is Heads
            if ((isHeads && game_choice == 'H') || (!isHeads && game_choice == 'T')) {
                person.giveGold(10);
                person.tell("Congratulations! You won 10 coins!");
            } else {
                person.takeGold(5);
                person.tell("Sorry, you lost 5 coins.");
            }
        } else {
            person.tell("Invalid choice. Please try again.");
        }
    }
}

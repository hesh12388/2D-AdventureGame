package OOP.ec22518.MP.contributions;

class House_ec22664 implements Visitable {
    private Room room1;
    private Room room2;
    private Room room3;
    
    public House_ec22664(){
        this.room1 = new Room_ec22662();
        this.room2 = new Room_ec22664();
        this.room3 = new Room_ec22666();
    }

    public Direction visit(Visitor visitor, Direction direction){
        Direction d = direction;
        visitor.tell("You have entered House_ec22664, the Central Hub.");
        visitor.tell("There are three different rooms connected to this hub:");
        visitor.tell("1. Room 1 - The Artifacts Room");
        visitor.tell("2. Room 2 - The Abandoned Building 22664");
        visitor.tell("3. Room 3 - The Chest Room");
        
        char[] choices = {'1', '2', '3', 'L'};
        
        char choice = visitor.getChoice("Which room would you like to explore? (1/2/3) or press 'L' to leave:", choices);
        
            switch (choice){ 
                case '1':
                    visitor.tell("You decide to explore Room 1 - The Artifacts Room.");
                    d = room1.visit(visitor, direction);
                    break;
                case '2':
                    visitor.tell("You decide to explore Room 2 - The Abandoned Building 22664.");
                    d = room2.visit(visitor, direction);
                    break;
                case '3':
                    visitor.tell("You decide to explore Room 3 - The Chest Room.");
                    d = room3.visit(visitor, direction);
                    break;
                case 'L':
                    visitor.tell("You decide to leave House_ec22664.");
                    break;
                default:
                    visitor.tell("Invalid choice. You leave House_ec22664.");
               break;
            }
            
            if (d == Direction.FROM_SOUTH || d == Direction.FROM_NORTH){
                d = Direction.opposite(d);
            }
        
        return d;
    }
    
}

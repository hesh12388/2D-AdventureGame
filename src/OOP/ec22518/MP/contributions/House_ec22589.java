package OOP.ec22518.MP.contributions;

class House_ec22589 implements Visitable {
    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    
    public House_ec22589(){
        this.room1 = new Room_ec22589();
        this.room2 = new Room_ec221148();
        this.room3 = new Room_ec221136();
        this.room4 = new Room_ec22917();
    }
    
    public Direction visit(Visitor visitor, Direction direction){
        char[] choices = {'E', 'L'};
        char[] room_choice = {'N', 'E', 'S', 'W'};
        
        visitor.tell("Welcome! You have entered the house and are currently standing in the hallway.");
        
        visitor.tell("Would you like to (E)nter a room or (L)eave the house? ");
        char choice = visitor.getChoice("Choose an option: ", choices);
        
        if (choice == 'L'){
            visitor.tell("The owner of the house is unhappy that you have not entered any rooms.");
            visitor.tell("5 gold will be taken from you and you are kicked out of the house.");
            int gold_taken = visitor.takeGold(5);
        }
               
        while (choice == 'E'){
            if (choice == 'E'){
                visitor.tell("You may enter the room in the (N)orth, (E)ast, (S)outh or (W)est. ");
                choice = visitor.getChoice("Choose an option: ", room_choice);

                if (choice == 'N'){
                    direction = room1.visit(visitor, direction);
                }
                else if (choice == 'E'){
                    direction = room2.visit(visitor, direction);
                }

                else if (choice == 'S'){
                    direction = room3.visit(visitor, direction);
                }

                else if (choice == 'W'){
                    direction = room4.visit(visitor, direction);
                }
            }
            
            else if (choice == 'L'){
                visitor.tell("You are now exiting the house. ");
                break;
            }
            
            visitor.tell("Would you like to (E)nter a room or (L)eave the house? ");
            choice = visitor.getChoice("Choose an option: ", choices);
        }
        
        return Direction.opposite(direction);
    }
}

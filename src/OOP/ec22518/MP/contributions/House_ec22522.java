package OOP.ec22518.MP.contributions;

class House_ec22522 implements Visitable {
    
    private Room roomOne;
    private Room roomTwo;
    private Room roomThree;
    private Direction dir;
    

    
    public House_ec22522() {
        roomOne = new Room_ec22510();
        roomTwo = new Room_ec22513();
        roomThree = new Room_ec22522();
        
    }
    
    public Direction visit(Visitor vis, Direction dir) {
        
        Item Theo = new Item("Theo");
        char[] options = {'a','b'};

        vis.tell("You enter a hallway with 2 doors: one facing west and one facing east. Which do you choose?");
        
        if (vis.getChoice("West (a), or East (b)?", options) == 'a') {
            dir = roomOne.visit(vis, Direction.FROM_EAST);
        }
        else {
            dir = roomTwo.visit(vis, Direction.FROM_WEST);
        }
        
        vis.tell("You reach a hallway and hear a sound ahead but it's too dark to see. There is also a room to your right.");
        
        if (vis.getChoice("Check out the sound (a) or enter the room (b)?", options) == 'a') {
            vis.tell("As you get closer to the sound, it turns out to be a cat! Its collar says 'Theo'.");
            if (vis.getChoice("Take the cat (a) or leave him (b)?", options) == 'a') {
                vis.tell("You take Theo with you. He seems happy about it.");
            }
            else {
                vis.tell("As you walk past him he starts following you anyway. You don't get a choice here.");
            }
            vis.giveItem(Theo);
        }
        else {
            dir = roomThree.visit(vis, Direction.FROM_WEST);
        }
        
        vis.tell("You've reached the end of the house.");
        return Direction.FROM_SOUTH;
    }
    
}

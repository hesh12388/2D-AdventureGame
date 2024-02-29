package OOP.ec22518.MP.contributions;

class House_ec22418 implements Visitable {
    
    private Room room1;
    private Room room2;
    private Room room3;
    private boolean keyFound;

     House_ec22418() {
         //North Room
         room1 = new Room_ec22418(); 
         //East Room
         room2 = new Room_ec22442(); 
         //West Room
         room3 = new Room_ec22707(); 
     }
    
    
    public Direction visit(Visitor v, Direction d) {
        
        keyFound = false;
        
        v.tell("You enter the haunted Windsor House, which is made up of three rooms. You must explore and find the key to escape.");
        v.tell("You are standing in the entrance hallway, and can either go North, East or West.");
        char[] choices  = {'N' , 'E' , 'W'};
        
        while (keyFound != true) {
            char options = v.getChoice("CHOOSE.", choices);
        
            if(options == ('N')) {
                v.tell("You have entered the West side room of the building ");
                d = room1.visit(v, d);
                v.tell("Unfortunately, no key in that one.");
            }
        
            if(options == ('E')) {
                v.tell("You have entered the North side room of the building");
                d = room2.visit(v, d);
                v.tell("Unfortunately, no key in that one.");
                keyFound = true;
            }
        
            if(options == ('W')) {
                v.tell("You have entered the East side room of the building");
                d = room3.visit(v, d);
                v.tell("Ah, yes it was in that one. Goodbye forever.");
            }   
        }
        
        return d;
    }
}

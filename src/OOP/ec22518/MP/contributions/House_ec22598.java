package OOP.ec22518.MP.contributions;

class House_ec22598 implements Visitable {
    private Room [] rooms = new Room [4];
    // Constructor
    House_ec22598() 
    {
        rooms[0] = new Room_ec22598();
        rooms[1] = new Room_ec22521();
        rooms[2] = new Room_ec22935();
        rooms[3] = new Room_ec22597();  
    }
    
   // Visit method
    public Direction visit(Visitor v, Direction d) 
    {
        v.tell("Welcome to ec22598's house ");
        v.tell("Here you have " + rooms.length + " rooms that lead to the North Pole. ?");

        char[] choices = {'N','S','E','W'};
        char vChoice = v.getChoice("Which direction do you want to enter??", choices);

        for (int i = 0; i < rooms.length && vChoice == 'N'; i++) {
            d = rooms[i].visit(v, d);
             vChoice = v.getChoice("Which direction do you want to enter??", choices);
              if (rooms.length == i+1)
              {
                  v.tell("you have reached the north pole following the north star");
              }
        }
        return d;
    }
} 

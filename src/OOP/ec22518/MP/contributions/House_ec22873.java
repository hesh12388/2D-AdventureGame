package OOP.ec22518.MP.contributions;

class House_ec22873 implements Visitable {
    private Room [] rooms = new Room [4];
    // Constructor
    House_ec22873()
    {
        rooms[0] = new Room_ec22421();
        rooms[1] = new Room_ec22521();
        rooms[2] = new Room_ec22935();
        rooms[3] = new Room_ec22597();  
    }

   // Visit method
    public Direction visit(Visitor v, Direction d) 
    {
        v.tell("Welcome to the house ");
        v.tell("There are " + rooms.length + " rooms that lead to the North...");

        char[] choices = {'N','S','E','W'};
        char vChoice = v.getChoice("Which direction do you want to go? ", choices);

        for (int i = 0; i < rooms.length && vChoice == 'N'; i++) {
            d = rooms[i].visit(v, d);
             vChoice = v.getChoice("Which direction do you want to go?", choices);
              if (rooms.length == i+1)
              {
                  v.tell("You've reached north...");
                  v.tell("Well done...");
              }
        }
        return d;
    }
}

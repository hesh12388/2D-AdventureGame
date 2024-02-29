package OOP.ec22518.MP.contributions;

class House_ec22642 implements Visitable {

     private Room room1;
     private Room room2;
     private Room room3;
     private boolean lantern;

      public House_ec22642() {
          room1 = new Room_ec22887();
          room2 = new Room_ec22707();
          room3 = new Room_ec22442();
      }
    
    public Direction visit(Visitor v, Direction d) {

    lantern = false;

    v.tell("3 rooms spawn in front of you in an instant. Find the lantern ");
    char[] choice = {'a' , 'b' , 'c'};

    while (!lantern) {

        char options = v.getChoice("Which room will you pick, the first (a), second (b) or the third (c) ?", choice);

        switch (options) {
            case 'a':
                room1.visit(v, d);
                v.tell("The door creaks open.... room1.");
                v.tell("the dust clears...nothing.");
                break;

            case 'b':
                room2.visit(v, d);
                v.tell("The door creaks open.... room2.");
                v.tell("The dust clears... THE LANTERN!!");
                lantern = true;
                break;

            case 'c':
                room3.visit(v, d);
                v.tell("The door creaks open.... room3.");
                v.tell("the dust clears...nothing.");
                break;

            default:
                v.tell("choose from 'a', 'b', or 'c'.");
                break;
        }
    }

    return d;
}
}

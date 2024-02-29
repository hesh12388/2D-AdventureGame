package OOP.ec22518.MP.contributions;

public class House_ec22955 implements Visitable {
    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    
    
    public House_ec22955() {
        room1 = new Room_ec22955();
        room2 = new Room_ec22548();
        room3 = new Room_ec22551();
        room4 = new Room_ec22559();
    }
    
    public static void main(String[] a) {
        House_ec22955 h = new House_ec22955();
        IOVisitor v = new IOVisitor(System.out, System.in);
        h.visit(v, Direction.TO_EAST);
    }
    
    public Direction visit(Visitor v, Direction d) {
        // Direction d1 = room1.visit(v, Direction.TO_EAST);
        boolean exit = false;
        Direction newd = d;
        Room current;
        String room1Slogan = "entering a room with a suspicious creature......";
        String room2Slogan = "entering a room with the faint sounds of growling......";
        String room3Slogan = "entering a big room.......";
        String room4Slogan = "entering a gaming room.......";
        String returnRoom = "there is no way to go, you re-enter from whence you came......";
        v.tell("you approach a house with 4 rooms");
        char choice = v.getChoice("do you want to enter the house (Y/N)?  ", new char[] {'Y', 'N'});
        if (choice=='Y') {
                v.tell("on entering the house you find yourself in a hallway. there are two doors, one on the left (L) and one on the right (R).");
                choice = v.getChoice("which door do you choose (L/R)? ", new char[] {'L', 'R'});
                if (choice=='L') {
                    v.tell(room1Slogan);
                    newd = room1.visit(v, newd);
                    current = room1;
                }
                else {
                    v.tell(room2Slogan);
                    newd = room2.visit(v, newd);
                    current = room2;
                }
            
            while (exit==false) {

                if (current==room1) {
                    if (newd==Direction.TO_WEST) {
                        v.tell(room3Slogan);
                        newd = room3.visit(v, newd);
                    }
                    else if (newd==Direction.TO_SOUTH) {
                        v.tell(room2Slogan);
                        newd = room2.visit(v, newd);
                    }
                    else {
                        v.tell(returnRoom);
                        newd = room1.visit(v, newd);
                    }
                }
                else if (current==room2) {
                    if (newd==Direction.TO_WEST) {
                        v.tell(room4Slogan);
                        newd = room4.visit(v, newd);
                    }
                    else if (newd==Direction.TO_NORTH) {
                        v.tell(room1Slogan);
                        newd = room1.visit(v, newd);
                    }
                    else {
                        v.tell(returnRoom);
                        newd = room2.visit(v, newd);
                    }
                }
                else if (current==room3) {
                    if (newd==Direction.TO_EAST) {
                        v.tell(room1Slogan);
                        newd = room1.visit(v, newd);
                    }
                    else if (newd==Direction.TO_SOUTH) {
                        v.tell(room4Slogan);
                        newd = room4.visit(v, newd);
                    }
                    else {
                        v.tell(returnRoom);
                        newd = room3.visit(v, newd);
                    }
                }
                else {
                    if (newd==Direction.TO_NORTH) {
                        v.tell(room3Slogan);
                        newd = room3.visit(v, newd);
                    }
                    else if (newd==Direction.TO_EAST) {
                        v.tell(room2Slogan);
                        newd = room2.visit(v, newd);
                    }
                    else {
                        v.tell(returnRoom);
                        newd = room4.visit(v, newd);
                    }
                }
                choice = v.getChoice("do you want to leave the house (Y) or continue exploring (N)", new char[] {'Y', 'N'});
                if (choice=='Y') {
                    exit=true;
                    v.tell("come again soon.... bye bye");
                }
            }
        }
        else {
            v.tell("you walk away in the direction you came.... bye bye");
        }
        return d;
    }
}
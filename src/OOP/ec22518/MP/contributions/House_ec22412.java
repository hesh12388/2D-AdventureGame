package OOP.ec22518.MP.contributions;

class House_ec22412 implements Visitable {
    Room[] areas = new Room[4];
    
    House_ec22412() {
        areas[0] = new Room_ec22412();
        areas[1] = new Room_ec22887();
        areas[2] = new Room_ec22582();
        areas[3] = new Room_ec22666();
    }
    
    public Direction visit(Visitor v, Direction d) {
        boolean end = false;
        char[] arrayOfPossibleChoices = {'a', 'b', 'c', 'd'};
        
        v.tell("Welcome, what is your choice?");
        
        char option = v.getChoice("a, b, c or d", arrayOfPossibleChoices);
        
        while (end == false) {
            if (option == 'a') {
                d = areas[0].visit(v,d);
            }
            else if (option == 'b') {
                d = areas[1].visit(v,d);
            }
            else if (option == 'c') {
                d = areas[2].visit(v,d);
            }
            else if (option == 'd') {
                d = areas[3].visit(v,d);
                end = true;
            }
            else {
                v.getChoice("a, b, c or d", arrayOfPossibleChoices);
            }
        }
        
        return d;
    }
}

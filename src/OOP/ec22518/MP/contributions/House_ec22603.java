package OOP.ec22518.MP.contributions;

class House_ec22603 implements Visitable {
    
    
    Room room1;
    Room room2;
    Room room3;
    Room room4;
        
    House_ec22603(){
        room1 = new Room_ec22578();
        room2 = new Room_ec21841();
        room3 = new Room_ec22451();
        room4 = new Room_ec22480();
    }
    
    public Direction visit(Visitor v,Direction d){
        v.tell("You have entered the house");
        char[] userOps = {'a', 'b', 'c','d'};
        char[] userOps2 = {'a', 'b'};
        char[] userOps3 = {'1', '2'};
        Boolean exit = false;
        
        while(!exit){
            v.tell("To your left is Room 1 and to your right is Room 2" +
                    "or you can proceed to the others...");
            char options = v.getChoice("(a)-Room 1 (b)-Room 2 (c)-proceed in exploring other rooms (d)jump through a window and run away like a coward", userOps);
            
            if (options == 'a') {
                d = room1.visit(v,d);
            } else if (options == 'b') {
                d = room2.visit(v,d);
            } else if (options == 'c') {
                v.tell("You walk on in the house......" +
                        "you see Room 3 to your left and Room 4 to your right");
            } else if(options =='d'){
                exit=true;
            }
            
            if(options != 'd'){
                options = v.getChoice("(a)-Room 3 (b)-Room 4", userOps2);
                if (options == 'a') {
                    d = room3.visit(v,d);
                }
                if (options == 'b') {
                    d = room4.visit(v,d);
                }
                v.tell("Do you wish to exit of walk back to the front of the house?");
                char ch = v.getChoice("(1)-Back to Front (2)-Exit", userOps3);
                if (ch == '2') {
                    exit = true;
                }
            }
        }
        v.tell("You are running away from the house");
        return d;
    }
    
    public static void main(String[] args) {
        House_ec22603 h= new House_ec22603();
        Visitor guy= new IOVisitor(System.out,System.in);
        h.visit(guy,Direction.FROM_SOUTH);
    }
}


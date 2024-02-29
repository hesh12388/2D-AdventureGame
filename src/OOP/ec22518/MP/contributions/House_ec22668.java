package OOP.ec22518.MP.contributions;

class House_ec22668 implements Visitable {

    Room room1,room2,room3,room4;

    House_ec22668(){
        room1 = new Room_ec22473();
        room2 = new Room_ec22429();
        room3 = new Room_ec22466();
        room4 = new Room_ec22486();
    }

    public Direction visit(Visitor v, Direction d){
        char[] roomChoice = {'N','E','S','W','X'};
        v.tell("Welcome To Munaibs House!");
        v.tell("Looks like there are 4 rooms");
        v.tell("N Will take you to the North room");
        v.tell("E Will take you to the East room");
        v.tell("S Will take you to the South room");
        v.tell("W Will take you to the West room");
        v.tell("X Will make you leave the house");
        char Selection = v.getChoice("Choose a option", roomChoice);

        while (Selection != 'X'){
            if (Selection == 'N'){
                v.tell("Looks like your going to the North room!");
                d = room1.visit(v,d);
            }
            else if (Selection == 'E'){
                v.tell("Looks like your going to the East room!");
                d = room2.visit(v,d);
                
            }
            else if (Selection == 'S'){
                v.tell("Looks like your going to the South room!");
                d = room3.visit(v,d);
            }
            else if (Selection == 'W'){
                v.tell("Looks like your going to the West room!");
                d = room4.visit(v,d);
            }
            else{
                v.tell("That Choice doesnt make sense, try again!");
            }
            v.tell("You have left the room...");
            Selection = v.getChoice("Where would you like to go now?", roomChoice);
        
        
        }
        v.tell("That was fun, have a safe journey, Bye!");
        return d;
        

    }
}

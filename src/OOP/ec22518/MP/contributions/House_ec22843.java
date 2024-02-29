package OOP.ec22518.MP.contributions;

public class House_ec22843 implements Visitable {


    private Room room1;
    private Room room2;
    private Room room3;

    House_ec22843(){
        room1 = new Room_ec22843();
        room2 = new Room_ec22562();
        room3 = new Room_ec22569();
    }

    public Direction visit(Visitor v, Direction d) {

        v.tell("Welcome to the most haunted house in Windsor...");
        v.tell("Here you have three rooms. And which room do you want to visit?");
        v.tell("Only one of them will let you out again..");

        char[] choices = {'A','B','C','D'};
        char vChoice = v.getChoice("Which room do you want to enter? A) The Orange Bedroom? B) Up the staircase? C) To the Pipe Organ? D) To the library?", choices);

        while(vChoice!= ('D')){

            if(vChoice == 'A'){
                v.tell("Intresting Choice... Not where I wanted you but there is a gold coin in here");
                d = room2.visit(v,d);
            }
            if (vChoice == 'B') {
                v.tell("WRONG!!.. Never Choose the stairs");

            }
            if (vChoice == 'C') {
                v.tell("Exactly where I wanted you!");
            }

            else {
                v.getChoice("Wrong..Try again if you want to go out!",choices);
            }
        }

        v.tell("Byeee for now...");
        return d;

    }

}

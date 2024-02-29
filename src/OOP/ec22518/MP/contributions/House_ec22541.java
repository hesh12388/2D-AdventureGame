package OOP.ec22518.MP.contributions;

class House_ec22541 implements Visitable {

    private Room room1;
    private Room room2;
    private boolean poke_cards;

     House_ec22541() {
         //North Room
         room1 = new Room_ec22541(); 
         //West Room
         room2 = new Room_ec22418(); 
         //East Room contains nothing


     }


    public Direction visit(Visitor v, Direction d) {

        poke_cards = false;


        v.tell("You enter the haunted Windsor House, though mildy creepy, you choose to head inside anyways");
        v.tell("When walking towards the entrance, you peep inside to look around the mansion (north)");
        v.tell("You look to your left (west) and notice a singled out room?");
        v.tell("You look to your right (east) and notice ... nothing ...");
        v.tell("Nervous to walk the path ahead, you turn around and notice a strange ... card like hole in a tree???");
        char[] choices  = {'n' , 'w' , 'e'};

        while (poke_cards != true) {
            char options = v.getChoice("which direction do you choose to go?", choices);

            if(options == ('n')) {
                v.tell("You choose to walk straight (north), though more creepy, it seems to have the only content");
                d = room1.visit(v, d);
                v.tell("It seems you have found the card ... maybe its time to head back");
                poke_cards = true;
            }

            if(options == ('w')) {
                v.tell("You choose to walk west, into the singled out room");
                d = room2.visit(v, d);
                v.tell("Doesn't seem as though anything special was found, maybe look around more?");
            }

            if(options == ('e')) {
                v.tell("I already told you there was nothing here, what did you expect??");

            }   

        }

        return d;
    }
}

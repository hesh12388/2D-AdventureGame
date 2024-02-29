package OOP.ec22518.MP.contributions;

class House_ec22614 implements Visitable {
    private Room Rone;
    private Room Rtwo;
    private Room Rthree;
    private Room Rfour;
    
    House_ec22614() {
        Rone = new Room_ec22614();
        Rtwo = new Room_ec22965();
        Rthree = new Room_ec22615();
        Rfour = new Room_ec221148();
    }
    public Direction visit(Visitor v, Direction d) {
        char[] options = {'a','b','c','d'};
        char[] options2 = {'a','b'};
        char choice = v.getChoice("Welcome to the Winchester Mystery House. You find yourself standing in the middle of a dark and eerie hallway, would you like to a)explore the hallway b)enter the smallest room in the house c)explore rooms nearby d)leave the house", options);
        
        if (choice == 'a'){
            final Item flashlight = new Item("flashlight");
            final Item diary = new Item("diary");
            char choice2 = v.getChoice("So you want to explore but don't want to explore too far, smart choice. You come across a diary that is labelled 'Sarahs diary' and a flashlight. Do you want to a)sit and read the diary or b)use the flashlight to explore ", options2);
            if (choice2== 'a'){
                v.tell("happy reading, maybe you can solve some mysterys of this house");
                v.giveItem(diary);
            }
            else if (choice2 == 'b'){
                v.giveItem(flashlight);
                v.tell("wise choice because you've just found yourself some gold");
                v.giveGold(5);
            }
        }
        
        else if (choice == 'b'){
            v.tell("you have decided to exlpore the smallest room in the house, it may be small but I assure you theres loads to find...");
            d = Rone.visit(v, d);
        }
        
        else if (choice == 'c'){
            v.tell("so you would like to exlpore all the rooms around? be my guest...");
            d = Rtwo.visit(v, d);
            d = Rthree.visit(v, d);
            d = Rfour.visit(v, d);
        }
        
        else if (choice == 'd'){
            v.tell("thank you for visiting, until next time...");
        }
        return d;
    }
}

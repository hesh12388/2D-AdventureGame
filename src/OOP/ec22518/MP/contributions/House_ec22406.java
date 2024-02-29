package OOP.ec22518.MP.contributions;

public class House_ec22406 implements Visitable {

    Room r1;
    Room r2;
    Room r3;
    boolean close;

    House_ec22406() {
        r1 = new Room_ec22480();
        r2 = new Room_ec22484();
        r3 = new Room_ec22485();
    }

    public Direction visit(Visitor visitor, Direction direction) {
        char choicesYesNo = '0';
        char choicesLetters = '0';
        close = false;

        char[] visitorChoiceLetters = { 'a', 'b', 'c' };
        char[] visitorChoiceYesNo = { 'Y', 'N' };

        while (!close) {
            direction = r1.visit(visitor, direction);

            choicesLetters = visitor.getChoice(
                "You're out of the room. What do you want to do next? a) explore another room b) investigate the surroundings c) leave the house",
                visitorChoiceLetters);

            if (choicesLetters == ('a')) {
                direction = r3.visit(visitor, direction);
            } else if (choicesLetters == ('b')) {
                visitor.tell("This old house calls out to you. Do you wish to keep exploring?");
                choicesLetters = visitor.getChoice("a) explore another room b) leave the house",
                    visitorChoiceLetters);
                if (choicesLetters == ('a')) {
                    direction = r2.visit(visitor, direction);
                } else {
                    close = true;
                    break;
                }
            }
            if (choicesLetters == ('c')) {
                close = true;
                break;
            }
        }
        return direction;
    }
}

package OOP.ec22518.MP.contributions;

import java.util.Random;

class House_ec22925 implements Visitable {
    public Room[] rooms;
    
    public House_ec22925(){
        rooms = new Room[]{new Room_ec22995(), new Room_ec22925(), new Room_ec22943()};
    }

    public Direction visit(Visitor vs, Direction d){
        char[] quizOptions = {'a', 'b', 'c', 'd'};
        char choice = 'a';
        Direction dir = null;

        for(int i = 0; i < rooms.length; i++){
            dir = rooms[i].visit(vs, d);
            vs.tell("Now that you exited the room do you want to:");
            if((rooms.length - i) > 0)
            {
                quizOptions = new char[]{'a', 'b', 'c'};
                choice = vs.getChoice("| a) Go to the next room | b) Skip the next room | c) Leave the house |", quizOptions);
                if(choice == 'b'){
                    i++;
                }
                else if(choice == 'c'){
                    break;
                }
            }
            else
            {
                quizOptions = new char[]{'a', 'b'};
                choice = vs.getChoice("| a) Go to the next room | b) Leave the house |", quizOptions);
                if(choice == 'b'){
                    break;
                }
            }

            vs.tell("To enter the next room you must answer correctly to the following question, or you will lose 4 coins!");

            if(askQuiz(vs)){
                vs.tell("Your answer was correct! Well done!");
            }
            else{
                vs.tell("Unfortunately your answer was wrong! You just lost 4 coins!");
                vs.takeGold(4);
            }
        }

        vs.tell("This is all the rooms! You have reached the end!");

        return dir;
    }

    public boolean askQuiz(Visitor vs){
        Random rand = new Random();
        int randomNum = rand.nextInt(4);
        String[] quizes = {"How many colours are in a rainbow? \n | a) 9 | b) 6 | c) 8 | d) 7 |", "Are there more doors or wheels in the world? \n | a) Doors | b) Wheels | c) Equal |", "How many registered dog breeds are there worldwide? \n | a) 340 | b) 300 | c) 360 |", "At what temperature does the antifreeze reach its bouling point? \n | a) 225-275C | b) 200-245C | c) Antifreeze doesn't boil |"};
        boolean correct = false;
        char choice = 'a';

        switch (randomNum) {
            case 0:
            choice = vs.getChoice(quizes[randomNum], new char[]{'a', 'b', 'c', 'd'});
            if(choice =='d'){
                correct = true;
            }
            break;

            case 1:
            vs.getChoice(quizes[randomNum], new char[] {'a', 'b', 'c'});
            if(choice == 'b'){
                correct = true;
            }
            break; 

            case 2:
            vs.getChoice(quizes[randomNum], new char[]{'a', 'b', 'c'});
            if(choice == 'c'){
                correct = true;
            }
            break;

            case 3:
            vs.getChoice(quizes[randomNum], new char[]{'a', 'b', 'c'});
            if(choice == 'a'){
                correct = true;
            }
            break;

            default:
                break;
        }

        return correct;
    }
}

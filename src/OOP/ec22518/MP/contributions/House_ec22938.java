package OOP.ec22518.MP.contributions;

import java.util.Random;

class House_ec22938 implements Visitable {
    Room r1;
    Room r2;
    Room r3;
    
    House_ec22938(){
        r1 = new Room_ec22938();
        r2 = new Room_ec22546();
        r3 = new Room_ec22420();
    }
    
    public Direction visit(Visitor visitor, Direction direction) {
        visitor.tell("Welcome to the Sarah Winchester Mystery House.");
        
        char[] options = {'N', 'S', 'W', 'E'};
        char choice = visitor.getChoice("Which way would you like to go? Choose S to go back!", options);
        
        int houseCounter = 0;
        int r1Counter = 0; 
        int r2Counter = 0;
        int r3Counter = 0;
        
        Random randNum = new Random();
        
        int randBox;
        int randJourney;
        
        char[] boxes = {'1', '2', '3'};
        char boxChoice;
        
        while (choice != 'S') {
            
          if (choice == 'N') {
              visitor.tell("Welcome to the north wing!");
              direction = r1.visit(visitor, direction);
              r1Counter ++;
          }
          else if (choice == 'E') {
              visitor.tell("Welcome to the east wing!");
              direction = r2.visit(visitor, direction);
              r2Counter ++;
          }
          else if (choice == 'W') {
              visitor.tell("Welcome to the west wing!");
              direction = r3.visit(visitor, direction);
              r3Counter ++;
          }
          else {
              choice = visitor.getChoice("Invalid input, choose again! N, S, W or E", options);
          }
          
          houseCounter ++;
          randJourney = randNum.nextInt(houseCounter + 3);
          randBox = randNum.nextInt(2);
            
          if (houseCounter == randJourney) {
              visitor.tell("\nTake a look at the ground! What have we found?");
              visitor.tell("There are three unknown boxes, where did they come from?");

              boxChoice = visitor.getChoice("Which one would you like to open? 1, 2 or 3?", boxes);

              if (boxChoice == boxes[randBox]) {
                  visitor.giveGold(5);
                  visitor.tell("You found 5 pieces of gold!");
              }
              else {
                  visitor.tell("The box is empty. Better luck next time.");
              }
          }

          if (r1Counter == 2) {
              visitor.giveGold(2);
              visitor.tell("Look! There was some gold outside your room!");
          }
          if (r2Counter == 2) {
              visitor.takeGold(2);
              visitor.tell("The rats have nibbled through your gold pouch! You have lost 2 gold pieces, oh no!");
          }
          if (r3Counter == 2) {
              visitor.giveGold(2);
              visitor.tell("Look! There was some gold outside your room!");
          }
     
          choice = visitor.getChoice("Where to now?", options);
          
        }
        
      return direction;
    }
}

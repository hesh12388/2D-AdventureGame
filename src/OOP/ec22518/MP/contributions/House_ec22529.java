package OOP.ec22518.MP.contributions;

public class House_ec22529 implements Visitable {
    
    /* House layout has 4 rooms which can only be accessed in an anti clock wise orientation. 
    Room zero will only give access to room one and so on. Room three will connect in a loop to room zero 
    = signs represent doors to the outside world and && represent inner room doors
     
     |----=---------------|
     |          |         |
     |     2   &&     1   =
     |          |         |
     |----&&---------&&---|
     |          |         |
     =     3   &&     0   |
     |          |         |
     |---------------=----|
     */
        Room roomOne;
        Room roomTwo;
        Room roomThree;
       public House_ec22529(){
      
          roomOne = new Room_ec22529();
          roomTwo = new Room_ec22897();
          roomThree = new Room_ec22704();
        }
      
        public Direction visit(Visitor visitor, Direction arrivedFrom){
            boolean hasExited = false;
          
        visitor.tell("You have entered my house from room " + "There are 4 rooms and you can only choose to enter 2 rooms from any given room. Make your choice now.");
          char[] options = {'a','b','c','d'};
          char optionChosen = visitor.getChoice("Which door do you want to choose, left(A), forwards (B), right(C) or backwards (D)", options);
      
          while(!hasExited){
            if(optionChosen =='a'){
              hasExited = true;
              visitor.tell("Correct door chosen! you can now leave my house well done!");
      
            }
            else if(optionChosen =='b'){
              visitor.tell("You are now in room 2");
              arrivedFrom = roomOne.visit(visitor, arrivedFrom);
      
            }
            else if(optionChosen =='c'){
              visitor.tell("You are now in room 3");
              arrivedFrom = roomTwo.visit(visitor, arrivedFrom);
            }
            else if(optionChosen =='d'){
              visitor.tell("You are now in room 4");
              arrivedFrom = roomThree.visit(visitor, arrivedFrom);
            }
            else{
              visitor.tell("INVALID SELECTION");
            }
      
      
      
        }
          return arrivedFrom;
        }
      
      
      }

package OOP.ec22518.MP.contributions;

class House_ec22478 implements Visitable {
  Room r1;
  Room r2;
  Room r3;
  
  House_ec22478() {
        r1 = new Room_ec22562();
        r2 = new Room_ec21582();
        r3 = new Room_ec221156();
  }
  
  public Direction visit(Visitor v, Direction d){
    v.tell("You have entered the house");
    char[] choices = { 'a', 'b', 'c' };
    String roomChoice = ("We have a range of rooms, choose wisely which one you would like to enter. Room a, b or c");
    char userChoice = v.getChoice(roomChoice, choices);
    
    if (userChoice == 1){
      v.tell("You have chosen Room1, Good Luck");
      r1.visit(v,d);
      }
             
    else if (userChoice == 2){
      v.tell("You have chosen Room2, Good Luck");
      r2.visit(v,d);
      }
             
    else if (userChoice == 3){
      v.tell("You have chosen Room3, Good Luck");
      r3.visit(v,d);
      }
    
    else{
      v.tell("There are three rooms, pick one of them");
    }
    
    return d;
  }
  
}

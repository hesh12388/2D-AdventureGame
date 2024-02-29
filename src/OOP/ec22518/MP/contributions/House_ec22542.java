package OOP.ec22518.MP.contributions;

class House_ec22542 implements Visitable {
  Room roomA;
  Room roomB;
  Room roomC;
  
  
  House_ec22542(){
    
    roomA = new Room_ec22542();
    roomB = new Room_ec22434();
    roomC = new Room_ec22672();
    
    
  }
  
  public Direction visit(Visitor ovisitor, Direction directionVisitorArrivesFrom){
    ovisitor.tell("Welcome visitor, this house  has 4 rooms. Choose wisely which door you want to choose as 1 door may lead to a consequence");
    char[] doorChoice = {'N','E','S','W'};
    char userDecision = ovisitor.getChoice("Which door do you want to choose, {N,E,S,W}", doorChoice);
    
    
    boolean leaveHouse = false;
    
    while(!leaveHouse){
      if(userDecision =='N'){
        leaveHouse = true;
        ovisitor.tell("You have chosen the wrong door and now you will leave this house");
        
      }
      else if(userDecision =='E'){
        ovisitor.tell("Welcome traveller to room 1");
        directionVisitorArrivesFrom = roomA.visit(ovisitor, directionVisitorArrivesFrom);
        
      }
      else if(userDecision =='S'){
        ovisitor.tell("Welcome traveller to room 2");
        directionVisitorArrivesFrom = roomB.visit(ovisitor, directionVisitorArrivesFrom);
      }
      else if(userDecision =='W'){
        ovisitor.tell("Welcome traveller to room 3");
        directionVisitorArrivesFrom = roomC.visit(ovisitor, directionVisitorArrivesFrom);
      }
      else{
        ovisitor.tell("Sorry, you did not pick a room.");
      }
        
  
  
  }
    return directionVisitorArrivesFrom;
  }
  

}

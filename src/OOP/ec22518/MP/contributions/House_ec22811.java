package OOP.ec22518.MP.contributions;

class House_ec22811 implements Visitable{

  private Room eastside_room;
  private Room westside_room;
  private Room northside_room;
  private Room southside_room;

 House_ec22811(){
     
     eastside_room = new Room_ec22716();
     westside_room = new Room_ec22798();
     northside_room = new Room_ec22893();
     southside_room = new Room_ec22819();

    }

  public Direction visit(Visitor visitor, Direction direction){

      visitor.tell("You see a MYSTERY HOUSE in the distance...");
      char[] select = {'E', 'W', 'N', 'S'};
      char choice = visitor.getChoice("Which direction would you like to enter from? East (E) / West (W) /  North (N) / South (S)", select);

      if(choice == ('E')){
           direction = eastside_room.visit(visitor, direction);
          visitor.tell("You now enter the passage connecting to all rooms");
          choice = visitor.getChoice("Which side room would you like to enter? East (E) / West (W) /  North (N) / South (S)", select);

      }
      else if(choice == ('W')){
           direction = westside_room.visit(visitor, direction);
          visitor.tell("You now enter the passage connecting to all rooms");
          choice = visitor.getChoice("Which side room would you like to enter? East (E) / West (W) /  North (N) / South (S)", select);
      }
      else if(choice == ('N')){
           direction = northside_room.visit(visitor, direction);
          visitor.tell("You now enter the passage connecting to all rooms");
          char[] select_exit = {'E', 'W', 'N', 'S', 'O'};
          choice = visitor.getChoice("Which side room would you like to enter or would you like to exit the house? East (E) / West (W) /  North (N) / South (S) / Exit (O)", select_exit);
      }
      else if(choice == ('S')){
           direction = southside_room.visit(visitor, direction);
          visitor.tell("As you exit the room, you see the reflection of the door you can exit the house from. You are still in the passage connecting the rooms though");
          choice = visitor.getChoice("Which side room would you like to enter? East (E) / West (W) /  North (N) / South (S)", select);
      }
      
       return direction;


    }
 }

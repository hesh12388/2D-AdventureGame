package OOP.ec22518.MP.contributions;

class House_ec22804 implements Visitable {
private Room room1;
private Room room2;
private Room room3;
private Room room4;
House_ec22804() {
     room1= new Room_ec22754();//tushar
     room2= new Room_ec22638();
     room3= new Room_ec22975();//aarav
     room4= new Room_ec22587();
   }
public Direction visit(Visitor v, Direction d) {

        char option[]={'1','2'};
        Room currentRoom = room1;
        boolean loop=true;
       while (loop){
        
        v.tell("You have come from this direction: "+ d);
        v.tell("You see a bonfire!");
        if (d==Direction.TO_WEST){
          char choice =v.getChoice("Do you want to 1) Put it out with water! 2)Put it out with a cloth",option);
          if (choice=='1'){
                v.tell("The fire is put out with water!");
                v.tell("You found a secret passage to another room!");
                currentRoom=room1;
            }
          else if (choice=='2'){
                v.tell("The fire is not put out from the cloth. The fire spreads!"); 
                v.tell("You exit the house!");
                loop=false;
            }
          }
         else if (d== Direction.TO_NORTH){
            v.tell("You have come from this direction: "+ d);
            v.tell("Your entering the next room!");
            currentRoom=room2;
        }
        else if (d== Direction.TO_EAST){
            v.tell("You have come from this direction: "+ d);
            v.tell("Your entering the next room!");
            currentRoom=room3;
        }
        else if (d== Direction.TO_SOUTH){
            v.tell("You have come from this direction: "+ d);
            v.tell("Your entering the next room!");
            currentRoom=room4;
        }
        
        
        d= currentRoom.visit(v, Direction.TO_NORTH);
        }
      
    
        return Direction.opposite(d);
  }
  
 }


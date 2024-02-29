package OOP.ec22518.MP.contributions;

class House_ec221218 implements Visitable {

   private Room r1;
   private Room r2;
   private Room r3;

   House_ec221218() {
         r1 = new Room_ec221218();
         r2 = new Room_ec221011();
         r3 = new Room_ec22436();

     }

   public Direction visit(Visitor v, Direction d){
         boolean Exit = false;
         v.tell("Welcome! Which direction do you want to go? a) North, b) South, c) East, d) West");
         char[] options = {'a','b','c','d'};

         while(!Exit){
             if (v.getChoice("a) North, b) South, c) East, d) West",options) == 'a'){
                     d = r1.visit(v,d);

             }

             else if (v.getChoice("a) North, b) South, c) East, d) West",options) == 'b'){
                     d = r2.visit(v,d);                
             }

             else if (v.getChoice("a) North, b) South, c) East, d) West",options) == 'c'){
                     d = r3.visit(v,d);

             }

             else if (v.getChoice("a) North, b) South, c) East, d) West",options) == 'd'){
                     Exit = true;

             }

             else {
               v.tell("This is restricted");
             }
             }

             return d;  

         }


 }

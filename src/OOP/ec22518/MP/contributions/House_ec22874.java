package OOP.ec22518.MP.contributions;


class House_ec22874 implements Visitable{

	 private Room n;
	 private Room w;
	 private Room s;
	 private Room e;

	House_ec22874(){

   	 n = new Room_ec22579();
   	 w = new Room_ec221017();
   	 s = new Room_ec22912();
   	 e = new Room_ec22419();

  	}

 		public Direction visit(Visitor visitor, Direction direction){

 			visitor.tell("There is a vampire infront of you!");
 			char[] answer = {'n', 's', 'e', 'w'};
 			char[] answer2 = {'a' , 'b' , 'c' };
 			char choice = visitor.getChoice("Which direction you wanna move in (n, s, e, w) ", answer);

 			if(choice==('n')){
    
     			 direction = n.visit(visitor, direction);
                  
 				visitor.tell("you see a shining toy");
                 
 				choice = visitor.getChoice("would you - a) pick up  b) check  c) Ignore", answer2);
 				if(choice==('a')){
 					visitor.tell("you picked up the toy and kept it , leave as soon as you can before someone sees it");
 				}
                 
 				else if(choice==('b')){
 					visitor.tell("While checking you broke the toy !! , now run");
 				}
                 
				else{
					
					visitor.tell("you kept moving ");
				} 
				
 			 }

 			else if(choice==('e')){
    
       			direction = e.visit(visitor, direction);
                   
 				visitor.tell("You come across a TV");
                 
 				choice = visitor.getChoice("which channel would you like to watch - a) Movie  b) Music  c) Sports  ", answer2);
 				if(choice==('a')){
 					visitor.tell("Somehow there isnt any good movie running on any channel so you leave");
 				}
                 
 				else if(choice==('b')){
    
 					visitor.tell("The music is too loud so you turn the volume down");
 				}
                 
				else{
					visitor.tell("Its a champions league match so you just watch the whole match ");
				}

 			}

 			else if(choice==('s')){
    
       			direction = s.visit(visitor, direction);
                   
 				visitor.tell("This is a play area");
                 
 				choice = visitor.getChoice("what do you wanna do - a) explore around  b) sit and relax  c) Leave ", answer2);
 				if(choice==('a')){
    
 					visitor.tell("You explored around the room and ended up playing on an old arcade");
 				}
 				else if(choice==('b')){
    
 					visitor.tell("You sit down on a chair and scroll through your mobile");
 				}

				else{

					visitor.tell("you leave the room to explore outside");
				}
            }
 			else if(choice==('w')){
    
       			direction = w.visit(visitor, direction);
                   
 				visitor.tell("You came out of the house");
 			 }

      		return direction;


     	  }
}

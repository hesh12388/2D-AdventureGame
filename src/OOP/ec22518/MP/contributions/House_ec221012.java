package OOP.ec22518.MP.contributions;

class House_ec221012 implements Visitable {

    Room room1 = new Room_ec22662();
    Room room2 = new Room_ec22406();
    Room room3 = new Room_ec22420();


  public Direction visit(Visitor v, Direction d) 
  {
    char[] options = {'a', 'b', 'c', 'd'}; 

    v.tell("Now, look in the corner at that dire little plant. Even the poltergeists can't keep this little plant alive. How are you going to stay alive?");
    v.tell("Obviously, I am joking... but let's do something about this poor guy");
    char userChoice = v.getChoice("Do you want to: \n a)Water the plant with this beautiful watering can I will give you \n b)Take a bite for some nutrition \n c)Leave it to die", options);

    if(userChoice == 'a')
    {
        v.tell("She's alive! You can keep these 5 pieces of gold!");
        v.giveGold(5);
    }
    else if(userChoice == 'b')
    {
        v.tell("Full now fatty?");
    }
    else
    {
        v.tell("Someone should leave you to die.");
    }

    v.tell("You find yourself in the hallway. There are three doors to the left, right and in front of you. You can enter any of these three rooms at your own will. Which will you pick");
    char userChoice1 = v.getChoice("Which room do you dare to enter? a) Left, b) Front, c) Right? or d) leave house", options);

    if(userChoice1 == ('a'))
    {
        v.tell("The creepy room with the librarian. I've heard she is NOT friendly but you do you.");
        d = room1.visit(v,d);    
    }
    else if(userChoice1 == ('b'))
    {
        v.tell("The riddle room eesh. Put the tiny brain that got you in this mess to good use");
        d = room2.visit(v,d);    
    }
    else if(userChoice1 == ('c'))
    {        
          v.tell("Of course. You pick the room with mr poltergeist. Your problem now, not mine");
          d = room3.visit(v,d);    
    }
    else
    {      
          v.tell("Too traumatised? Course you are. See you soon... or not");
    }

       return d;
    }
  }

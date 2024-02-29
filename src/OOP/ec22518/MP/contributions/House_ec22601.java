package OOP.ec22518.MP.contributions;

class House_ec22601 implements Visitable {

    Room Room1;
    Room Room2;
    Room Room3;
    Room Room4;
    Room Room5;
    Room Room6;

    // Direction currentDirection;
    int currentRoom;

    House_ec22601(){

        Room1 = new Room_ec22601();
        Room2 = new Room_ec221008();
        Room3 = new Room_ec221218();
        Room4 = new Room_ec22962();
        Room5 = new Room_ec221025();
        Room6 = new Room_ec221085();

    }

    public Direction visit(Visitor visitor, Direction direction){

        char[] escapeChoices = {'a', 'b', 'c'};
        char choice = ' ';
        boolean keepExploring = true;

        visitor.tell("Welcome to ec22601's house!");
        visitor.tell("You have entered the main hallway. The lights are off, the windows are locked and the door locked behind you...");

        if((direction.toString().equals("heading North"))){

            currentRoom = 1;
        }

        else if((direction.toString().equals("heading East"))){
            currentRoom = 2;
        }

        else if((direction.toString().equals("heading South"))){
            currentRoom = 3;
        }

        else{
            currentRoom = 4;
        }


        while(keepExploring == true){

            if(currentRoom == 1){

                direction = Room1.visit(visitor, direction);

                if((direction.toString().equals("heading North"))){

                    currentRoom = 3;
                }

                else if((direction.toString().equals("heading East"))){
                    currentRoom = 6;
                }
        
                else if((direction.toString().equals("heading South"))){

                    //hallway
                    visitor.tell("You have returned to the hallway...");
                    visitor.tell("In order to escape this house, you have a few options...");
                    choice = visitor.getChoice("Choices: a. You break down the door, b. You break the stained glass window, c. Open the drawer in the cupboard and hope for a key", escapeChoices);

                    if(choice == 'a'){
                        visitor.tell("After a few attempts of kicking the door, the locks finally gives way...");
                        visitor.tell("The door swings open and you're free!");
                    }

                    else if(choice == 'b'){
                        visitor.tell("After one strike to the window, it fractures and breaks.");
                        visitor.tell("You can now escape through the window.");
                    }

                    else {
                        visitor.tell("You open the drawer and find a single key.");
                        visitor.tell("You try the key in the door lock and it turns, unlocking the door.");
                        visitor.tell("The door swings open to freedom!");
                    }

                    keepExploring = false;
                }
        
                else{
                    currentRoom = 5;;
                }

            }

            else if(currentRoom == 2){

                direction = Room2.visit(visitor, direction);

                if((direction.toString().equals("heading North"))){

                    currentRoom = 6;
                }

                else if((direction.toString().equals("heading East"))){
                    currentRoom = 4;
                }
        
                else if((direction.toString().equals("heading South"))){
                    currentRoom = 3;
                }
        
                else{
                    
                    //hallway
                    visitor.tell("You have returned to the hallway...");
                    visitor.tell("In order to escape this house, you have a few options...");
                    choice = visitor.getChoice("Choices: a. You break down the door, b. You break the stained glass window, c. Open the drawer in the cupboard and hope for a key", escapeChoices);

                    if(choice == 'a'){
                        visitor.tell("After a few attempts of kicking the door, the locks finally gives way...");
                        visitor.tell("The door swings open and you're free!");
                    }

                    else if(choice == 'b'){
                        visitor.tell("After one strike to the window, it fractures and breaks.");
                        visitor.tell("You can now escape through the window.");
                    }

                    else {
                        visitor.tell("You open the drawer and find a single key.");
                        visitor.tell("You try the key in the door lock and it turns, unlocking the door.");
                        visitor.tell("The door swings open to freedom!");
                    }

                    keepExploring = false;
                }
            }

            else if(currentRoom == 3){

                direction = Room3.visit(visitor, direction);

                if((direction.toString().equals("heading North"))){

                   //hallway
                   visitor.tell("You have returned to the hallway...");
                   visitor.tell("In order to escape this house, you have a few options...");
                   choice = visitor.getChoice("Choices: a. You break down the door, b. You break the stained glass window, c. Open the drawer in the cupboard and hope for a key", escapeChoices);

                   if(choice == 'a'){
                       visitor.tell("After a few attempts of kicking the door, the locks finally gives way...");
                       visitor.tell("The door swings open and you're free!");
                   }

                   else if(choice == 'b'){
                       visitor.tell("After one strike to the window, it fractures and breaks.");
                       visitor.tell("You can now escape through the window.");
                   }

                   else {
                       visitor.tell("You open the drawer and find a single key.");
                       visitor.tell("You try the key in the door lock and it turns, unlocking the door.");
                       visitor.tell("The door swings open to freedom!");
                   }

                   keepExploring = false;
                }

                else if((direction.toString().equals("heading East"))){
                    currentRoom = 2;
                }
        
                else if((direction.toString().equals("heading South"))){
                    currentRoom = 1;
                }
        
                else{
                    currentRoom = 4;
                }
            }

            else if(currentRoom == 4){

                direction = Room4.visit(visitor, direction);

                if((direction.toString().equals("heading North"))){

                    currentRoom = 5;
                }

                else if((direction.toString().equals("heading East"))){
                    //hallway
                    visitor.tell("You have returned to the hallway...");
                    visitor.tell("In order to escape this house, you have a few options...");
                    choice = visitor.getChoice("Choices: a. You break down the door, b. You break the stained glass window, c. Open the drawer in the cupboard and hope for a key", escapeChoices);

                    if(choice == 'a'){
                        visitor.tell("After a few attempts of kicking the door, the locks finally gives way...");
                        visitor.tell("The door swings open and you're free!");
                    }

                    else if(choice == 'b'){
                        visitor.tell("After one strike to the window, it fractures and breaks.");
                        visitor.tell("You can now escape through the window.");
                    }

                    else {
                        visitor.tell("You open the drawer and find a single key.");
                        visitor.tell("You try the key in the door lock and it turns, unlocking the door.");
                        visitor.tell("The door swings open to freedom!");
                    }

                    keepExploring = false;
                }
        
                else if((direction.toString().equals("heading South"))){
                    currentRoom = 3;
                }
        
                else{
                    currentRoom = 2;
                }
            }

            else if(currentRoom == 5){

                direction = Room5.visit(visitor, direction);

                if((direction.toString().equals("heading North"))){

                    currentRoom = 2;
                }

                else if((direction.toString().equals("heading East"))){
                    currentRoom = 1;
                }
        
                else if((direction.toString().equals("heading South"))){
                    currentRoom = 4;
                }
        
                else{
                    currentRoom = 6;
                }
            }

            else if(currentRoom == 6){

                direction = Room6.visit(visitor, direction);

                if((direction.toString().equals("heading North"))){

                    currentRoom = 4;
                }

                else if((direction.toString().equals("heading East"))){
                    currentRoom = 5;
                }
        
                else if((direction.toString().equals("heading South"))){
                    currentRoom = 2;
                }
        
                else{
                    currentRoom = 1;
                }
            }

            else{
                
                //hallway
                visitor.tell("You have returned to the hallway...");
                visitor.tell("In order to escape this house, you have a few options...");
                choice = visitor.getChoice("Choices: a. You break down the door, b. You break the stained glass window, c. Open the drawer in the cupboard and hope for a key", escapeChoices);

                if(choice == 'a'){
                    visitor.tell("After a few attempts of kicking the door, the locks finally gives way...");
                    visitor.tell("The door swings open and you're free!");
                }

                else if(choice == 'b'){
                    visitor.tell("After one strike to the window, it fractures and breaks.");
                    visitor.tell("You can now escape through the window.");
                }

                else {
                    visitor.tell("You open the drawer and find a single key.");
                    visitor.tell("You try the key in the door lock and it turns, unlocking the door.");
                    visitor.tell("The door swings open to freedom!");
                }

                keepExploring = false;

            }
        }
        return direction;

    }


    

    
}


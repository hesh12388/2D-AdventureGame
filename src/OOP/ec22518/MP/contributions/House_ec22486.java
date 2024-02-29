package OOP.ec22518.MP.contributions;

class House_ec22486 implements Visitable {
    static Room[] rooms = {new Room_ec22486(), new Room_ec22646(), new Room_ec22820()};

    public Direction visit(Visitor visitor, Direction direction){
        visitor.tell("Welcome to my house. Each room is located in a different way, you can go in different directions. Go in one direction to explore and collect items, as well as gold! Best of luck.");
        char[] options = {'N', 'S', 'W', 'E'};
        char[] yesNo = {'y', 'n'};
        char[] pick = {'1', '2' ,'3'};
        char[] leaveSearch = {'l', 's'};
        char[] gunOption = {'a', 'b'};

        char choice = visitor.getChoice("Go make a decision! Every room has something different to offer to you. Go east if you want to leave.", options);
       
        while (choice != 'E') {

            if (choice == 'N') {
                char yesorNo = visitor.getChoice("You have entered the master bedroom. Would you like to search it? (y/n)", yesNo);
                if(yesorNo == 'y'){
                    char search = visitor.getChoice("In front of you you have a little dinasour toy, a new lego set, and a new croissant jellycat. What would you like to pick?", pick);
                    if(search == '2'){
                        visitor.tell("Yay you found 7 gold pieces inside the new lego. Glad you picked this option. Now you have a brand new lego set and extra 7 golds, how lucky you are!");
                        visitor.giveGold(7);
                    } else{
                        visitor.tell("Sorry that you picked out this one :( Even though you didn't win any gold you won a new toy ");
                    }
                } else{
                    visitor.tell("That's okay. There's plenty of rooms inside this house. Feel free to explore!");
                }
                visitor.tell("You are at the place where you started");
                rooms[1].visit(visitor, direction);
            }

            if (choice == 'S') {
                char yesorNo = visitor.getChoice("You have entered the kitchen of the house. Would you like to search it? (y/n)", yesNo);
                if(yesorNo == 'y'){
                    char search = visitor.getChoice("In front of you you have three boxes. Which one would you like to pick?", pick);
                    if(search == '1'){
                        char leaveorSearch = visitor.getChoice("Unfortunately in this box there was nothing but a piece of paper. The paper asks for you to leave the room now and go to the first room or give 5 golds and continue searching. Which one are you going to pick? (l/s)", leaveSearch);
                        if(leaveorSearch == 'l'){
                            visitor.tell("Sorry that you are leaving, you missed you big chance of winning more golds");
                            direction = rooms[1].visit(visitor, direction);;
                        } else if (leaveorSearch == 's'){
                            visitor.takeGold(5);
                            search = visitor.getChoice("Yay! So glad you chose to stay. Now feel free to chose one of the other two boxes", pick);
                        }
                    } else if(search == '2'){
                        visitor.tell("This box just filled with dust, and now you are covered with it.");
                    } else if(search == '3'){
                        visitor.tell("You find the right box!! There was a Haribo packet and next to it there were 15 golds. Yes 15, count them if you want to.");
                        visitor.giveGold(15);
                    }
                } else{
                    visitor.tell("That's okay. There's plenty of rooms inside this house. Feel free to explore!");
                }
                visitor.tell("You are at the place where you started");
                rooms[2].visit(visitor, direction);
            }

            if (choice == 'W') {
                char yesorNo = visitor.getChoice("You have entered the old creepy basement. Would you like to search it? (y/n)", yesNo);
                if(yesorNo == 'y'){
                    char gun = visitor.getChoice("Someone came from the behind and held a gun at you head. What do you do? (a: Turn back and attack him, b: Say you'll do anything he wants if he doesn't hurt you", gunOption);
                    if(gun=='a'){
                        visitor.tell("You attacked the man that was holding a gun at you. Turns out it was just a water gun and you got played");
                    } else if(gun=='b'){
                        visitor.tell("It was not even a real gun and you got played. Now give the man 8 golds because you said you'll do anything he wants");
                        visitor.takeGold(8);
                    }
                } else{
                    visitor.tell("That's okay if you got scared. There's plenty of rooms inside this house. Feel free to explore!");
                }
                visitor.tell("You are at the place where you started");
                rooms[3].visit(visitor, direction);
            }
            visitor.tell("I hope you liked the House!");
        }
        return direction;
    }
}

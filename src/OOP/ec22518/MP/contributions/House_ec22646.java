package OOP.ec22518.MP.contributions;

class House_ec22646 implements Visitable {
    
    static Room[] House_Room = {new Room_ec22646(), new Room_ec221156(), new Room_ec22486()};

    public Direction visit (Visitor visitor, Direction d) {

        visitor.tell("Welcome to the House of Random Rooms. The rooms connect you to different places. There is one room in the basement, one on the ground floor, and the last one is the playhouse in the backyard . Pick one of the rooms. When you have finished exploring that room, you will be sent to the main entrance. Best of Luck.");

        boolean roam = true;
        char[] yesNo = {'y', 'n'};
        char[] options = {'a', 'b', 'c'};


        while (roam) {

            visitor.tell("Pick one of the rooms.");
            char place = visitor.getChoice("Choose room (a) 1 in the attick, choose room (b) 2 next to the kitchen, or choose room (c) 3 the room in the playhouse. Pick a, b or c. ", options);

            if (place == 'a') {
                char answer = visitor.getChoice("This attic is kind of creepy... Would you like to search it? (y/n)", yesNo);

                if (answer == 'y') {
                    visitor.tell("Pick one of the three options you would want to search during your journey to the attic");
                    char search = visitor.getChoice("Choose (a) the console table in the hallway , (b) the hidden drawer under the console table, (c) the box labelled February 1st 1994", options);

                    if (search == 'c') {
                        visitor.tell("The box had 10 gold coins inside. Good Job :). P.s. You might want to remember that date.");
                        visitor.giveGold(10);
                    } else {
                        visitor.tell("There was nothing here :(");
                    }
                } else {
                    visitor.tell("That's okay. The House of random rooms awaits your next visit.");
                }

                visitor.tell("You are now where you first started.");
                House_Room[1].visit(visitor, d);
            } else if (place == 'b') {
                char answer = visitor.getChoice("This kitchen is filled with different boxes.. Would you like to search it? (y/n)", yesNo);

                if (answer == 'y') {
                    visitor.tell("Pick one of the three boxes.");
                    char search = visitor.getChoice("Search (a) box A  (b) box B (c) box C", options);

                    if (search == 'a') {
                        visitor.tell("You founs a picture of a baby with 5 gold coins next to it.");
                        visitor.giveGold(5);
                    } else if (search == 'b') {
                        visitor.tell("The box was filled with dust and now it's in your eyes.");
                    } else {
                        visitor.tell("The box was empty. What a shame.");
                    }
                } else {
                    visitor.tell("That's okay. The House of random rooms awaits your next visit.");
                }

                visitor.tell("You are now where you first started.");
                House_Room[2].visit(visitor, d);
            } else {
                char answer = visitor.getChoice("The backyard is really cool... Would you like to search it? (y/n)", yesNo);

                if (answer == 'y') {
                    visitor.tell("We don't have much time.. quickly pick one thing in the staircase you want to search.");
                    char search = visitor.getChoice("Search (a) The unfinished puzzle in the grass (b) The lego box  (c) The elephant jellycat and it's blanket", options);

                    if (search == 'c') {
                        visitor.tell("Under the blanket you found 2 gold coins.");
                        visitor.giveGold(2);
                    } else if (search == 'b') {
                        visitor.tell("There was nothing in the lego box. Not even legos.");
                    } else{
                        visitor.tell("The only thing interesting in the unfinished puzzle was the picture of the jellycat they were trying to put together.");
                    }
                } else {
                    visitor.tell("That's okay. The House of random rooms awaits your next visit.");
                }

            visitor.tell("I hope you liked the House of randoms rooms. Were they too random?");
            char decision = visitor.getChoice("Do you want to explore the house some more? (y/n)", yesNo);

            if (decision == 'n') {
                roam = false;
            }
            
        }

        
    }
    return d;
  }
}












package OOP.ec22518.MP.contributions;/* This is a magical house where each door is a portal that connects to a 'room' in random locations. There are 3 floors.
   Each floor has one room apart from the to floor which had two. Leading to these rooms are hallways and staricases that the
   visitor has the option of searching (to find some gold). 

   The staricase leading the the top floow requires an item obtained in one of the rooms to be able to unlock a cabinet to 
   obtain the gold. Another staircase has decorations hinting at what an answer is in the quiz taken in Room_e221156 (the 
   quiz can only be taken on the second (or more) visit).

   Each staricase/hallway has a different number of gold to be found. Each staricase/hallway has one search location that 
   contains gold coins. Due to the "We don't have much time" story element, the visitor can only search one place each time 
   they are in a staircase/hallway.

   Once the visitor is deposited back at the main enterance, they have the option of continuing to explore the house if 
   they wish to, allowing them to enter different rooms or the same room however many times they want.
 */


class House_ec221156 implements Visitable {
    
    Room[] rooms = {new Room_ec221156(), new Room_ec22770(), new Room_ec22695(), new Room_ec22646()};

    public Direction visit (Visitor visitor, Direction d) {

        visitor.tell("Welcome to The Magical House. This house has a connection to many locations. Each door is a portal to a different domain located somewhere in the multiverse. The rooms are located on differnt floors. There is a room in the basement; one on the ground floor; two on the first floor. Choose a room to explore; once your task in the room is finished, you will be transported back to the main enterance via a portal. Be very careful with which room you choose to go into. Lives may be at risk...");

        boolean move = true;
        char[] yesNo = {'y', 'n'};
        char[] options = {'a', 'b', 'c'};
        char[] upstairs = {'a', 'b'};
       

        while (move) {

            visitor.tell("Now choose where you want to go..");
            char place = visitor.getChoice("Go to (a) the staricase leading to the basement (b) the hallway leading to the room on the ground floor (c) the staircase leading to the first floor", options);
            
            if (place == 'a') {
                char answer = visitor.getChoice("This is an interesting staricase... Would you like to search it? (y/n)", yesNo);

                if (answer == 'y') {
                    visitor.tell("We don't have much time.. quickly pick one thing in the staricase you want to search.");
                    char search = visitor.getChoice("Search (a) The chest on the window sill (b) The framed picture of Zahid Rahman and Harvinder Dhillon (c) The chipped floorboard of the first step", options);

                    if (search == 'b') {
                        visitor.tell("The frame covered a hole in the wall! You found 5 gold coins! Let's continue to the basement.");
                        visitor.giveGold(5);
                    } else {
                        visitor.tell("Oof... Looks like there was nothing to be suspicious about here. Let's continue to the basement.");
                    }
                } else {
                    visitor.tell("That's a shame. Oh well, you seem to be in a rush. You can search the next time you visit.");
                }

                visitor.tell("Brace yourself; travelling through portals can be taxing.");
                rooms[0].visit(visitor, d);
            } else if (place == 'b') {
                char answer = visitor.getChoice("This is an interesting hallway... Would you like to search it? (y/n)", yesNo);

                if (answer == 'y') {
                    visitor.tell("We don't have much time.. quickly pick one thing in the hallway you want to search.");
                    char search = visitor.getChoice("Search (a) A cabinet painted in blue and yellow (b) The chandelier (c) A poster of Zayn Malik", options);

                    if (search == 'c') {
                        visitor.tell("The cabinet's key was hidden behind the poster! You unlock it and find 3 gold coins! Let's continue to the room.");
                        visitor.giveGold(3);
                    } else if (search == 'a') {
                        visitor.tell("Hmmm.. the cabinet seems to be locked. I wonder if the key is somewhere here too. I guess you'll have to find out in your next visit. Let's continue to the room.");
                    } else {
                        visitor.tell("Oof... Looks like there was nothing to be suspicious about here. Let's continue to the room.");
                    }
                } else {
                    visitor.tell("That's a shame. Oh well, you seem to be in a rush. You can search the next time you visit.");
                }

                visitor.tell("Brace yourself; travelling through portals can be taxing.");
                rooms[3].visit(visitor, d);
            } else {
                char answer = visitor.getChoice("This is an interesting staircase... Would you like to search it? (y/n)", yesNo);

                if (answer == 'y') {
                    visitor.tell("We don't have much time.. quickly pick one thing in the staircase you want to search.");
                    char search = visitor.getChoice("Search (a) The forgotten book on the first step (b) The chest on the window sill (c) The stained glass window", options);

                    if (search == 'a') {
                        visitor.tell("This is just a regular book... Nothing interesting about it. Let's continue towards the rooms.");
                    } else if (search == 'b') {
                        visitor.tell("Oop this is an empty chest. Let's continue towards the rooms");
                    } else {
                        if (visitor.hasEqualItem(new Item("Lever_ec22695"))) {
                            visitor.tell("You have a lever from Room ec22770.");
                            char lever = visitor.getChoice("Do you want to use the lever on the stained glass? (y/n)", yesNo);
                            if (lever == 'y'){
                                visitor.tell("There were 10 gold coins hidden between the stained glass!");
                                visitor.giveGold(10);
                            } else {
                                visitor.tell("That's a shame. Maybe in your next visit then.");
                            }
                        } else {
                            visitor.tell("This is an interesting window... I wonder if you can find an item in one of the rooms to take apart the stained glass. Let's continue towards the rooms.");
                        }
                    }
                } else {
                    visitor.tell("That's a shame. Oh well, you seem to be in a rush. You can search the next time you visit.");
                }

                char upstairsRooms = visitor.getChoice("There are two doors at the end of this staircase. Each leads to a room. Do you go through (a) The door on the left (b) The door on the right?", upstairs);

                visitor.tell("Brace yourself; travelling through portals can be taxing.");
                if (upstairsRooms == 'a') {
                    rooms[1].visit(visitor, d);
                } else {
                    rooms[2].visit(visitor, d);
                }
            }

            visitor.tell("You're back! I hope you had a pleasant visit.");
            char decision = visitor.getChoice("Would you like to continue exploring the house? (y/n)", yesNo);

            if (decision == 'n') {
                move = false;
            }

        }

        return d;
    }
}

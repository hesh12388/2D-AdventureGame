package OOP.ec22518.MP.contributions;

import java.util.Random;
class House_ec22450 implements Visitable {
        Room room1;
        Room room2;
        Room room3;
        House_ec22450()
        {
            this.room1=new Room_ec22450();
            this.room2=new Room_ec22602();
            this.room3=new Room_ec22657();
        }
        public Direction visit(Visitor visitor, Direction from)
        {
            visitor.tell("Welcome to ec22450's house!");
            visitor.tell("You emerge into the hallway.");
            visitor.tell("You find an old painting on the floor.");
            char[] choiceArray={'A','B'};
            if(visitor.getChoice("Do you (A)pick it up and put it back on the wall or (B) ask to keep it for yourself",choiceArray)=='A')
            {
                Random randomNumbers=new Random();
                int randomNum=randomNumbers.nextInt(1);
                if(randomNum==0)
                {
                    visitor.tell("Your request to keep the painting has been rejected.");
                }
                else
                {
                    visitor.tell("Your request to keep the painting has been granted.");
                    Item painting=new Item("Painting");
                    boolean itemAccepted=visitor.giveItem(painting);
                }
             }
             visitor.tell("You now have a choice of two rooms: Room 1 or Room 2. Room 3 is unavailable at the moment.");
             char[]choiceArray2={'1','2'};
             char choice=visitor.getChoice("Press 1 to go to room 1, and 2 to go to room 2.",choiceArray2);
             if(choice=='1')
             {
                 Direction newDirection=room1.visit(visitor,Direction.FROM_NORTH);
             }
             else if(choice=='2')
             {
                 Direction newDirection=room2.visit(visitor,Direction.FROM_NORTH);
             }
             else
             {
                 visitor.tell("You didn't specify a room. You will have to leave the house");
                 return Direction.TO_WEST;
             }
             visitor.tell("You emerge into the hallway again.");
             visitor.tell("This time you find a note that reads:'You have been granted exclusive access to Room 3. Please take this key to enter the room.'");
             Item room3Key=new Item("Key");
             boolean itemAccepted=visitor.giveItem(room3Key);
             if(visitor.hasEqualItem(room3Key)==true)
             {
                Direction newDirection=room3.visit(visitor,Direction.FROM_SOUTH);
             }
             else
             {
                visitor.tell("You didn't accept the key.");
                visitor.tell("Therefore, you will leave the house.");
                return Direction.TO_EAST;
             }
             visitor.tell("You now enter the garden.");
             visitor.tell("There are trees all around you, and a lonely shed at the back of the garden.");
             char[] newChoiceArray={'R','W','L'};
             choice=visitor.getChoice("Press R to go to rake leaves, W to water the neglected plants, or L to leave the house.",newChoiceArray);
             if(choice=='R')
             {
                 visitor.tell("Thank you for volunteering to help out.");
                 visitor.tell("Here are six gold pieces in return.");
                 visitor.giveGold(6);
                 visitor.tell("You now exit the garden.");
             }
             else if(choice=='W')
             {
                 visitor.tell("Thank you for offering to water the plants!");
                 visitor.tell("Here are five gold pieces in return.");
                 visitor.giveGold(5);
                 visitor.tell("You now exit the garden.");
             }
             else
             {
                 visitor.tell("You didn't specify a particular option.");
                 visitor.tell("You now exit the garden. It's a shame you didn't put your gardening skills to use.");
             }
             char[] newChoiceArray2={'1','2','L'};
             choice=visitor.getChoice("Press 1 to go to room 1, and 2 to go to room 2, or L to leave the house.",newChoiceArray2);
             if(choice=='1')
             {
                 Direction newDirection=room1.visit(visitor,Direction.FROM_WEST);
             }
             else if(choice=='2')
             {
                 Direction newDirection=room2.visit(visitor,Direction.FROM_WEST);
             }
             else if(choice=='L')
             {
                 visitor.tell("Bye!");
             }
             else
             {
                 visitor.tell("You didn't specify a room. You will have to leave the house");
                 return Direction.TO_NORTH;
             }
             visitor.tell("Thanks for visiting! Bye.");
             return Direction.TO_EAST;
        }
}
        

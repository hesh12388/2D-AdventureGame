package OOP.ec22518.MP.contributions;//import java.util.Scanner;

class House_ec21645 implements Visitable {

    Room Hallway;
    Room LivingRoom;
    Room Kitchen;
    Room Bedroom;
    

    Room[] RoomsinHouse;

    // Direction direction = new Direction();

    



    public House_ec21645(){

        // Room room1 = new Room_ec/*Username*/();

        // Room room2 = new Room_ec/*Username*/();

        this.Hallway = new Room_ec21645();

        this.LivingRoom = new Room_ec22630();

        this.Kitchen = new Room_ec22695();

        this.Bedroom = new Room_ec22518();

        
        this.RoomsinHouse = new Room[] {new Room_ec21645() ,new Room_ec22630(), new Room_ec22695(), new Room_ec22913()};

        


        // this.Kitchen = room1;
        // this.LivingRoom = room2;



    }

    //@Override
    public Direction visit(Visitor v, Direction d)
    {
        Direction InitialDirection = Hallway.visit(v,d);
        //Scanner scanner = new Scanner(System.in);
        String dialogue = "Author : ";

        //String ans;
        char choices[] = {'a', 'b', 'c'};

        v.tell("Welcome Friend, you awaken in my domain. I will do everything I can to make you stay, Just try and Leave!!" + "   ;)");
        v.giveGold(10);
        v.tell("Try to escape my domain, if you dare");

        v.tell(dialogue + "You are currently at the doorway and this unkown entity is after you, you must think quickly!!");
        v.tell(dialogue + "Here are your choices: ");

        char choice = v.getChoice("[ (a) Go to the Kitchen, (b) Try to unlock the door, (c) Go to the Bedroom]", choices);

        //ans = scanner.nextLine();

        
            
            if (choice == 'a')
            {
                InitialDirection = Kitchen.visit(v, Direction.TO_NORTH);


                v.tell("The entity did not see you and now you hide behind the kitchen counter with nothing but a rolling pin to protect you");
                v.giveGold(10);
                v.tell("What will you do now?");

                char choice2 = v.getChoice("[a] Get out of the Kitchen and hope it doesn't catch you, [b] Fight it with your rolling oin, [c] Go to Living Room", choices);

                if (choice2 == 'a')
                {
                    v.tell("It found you and your dead");
                    System.exit(0);
                }

                else if (choice2 == 'b')
                {
                    v.tell("You thought you could actually kill it, you couldn't");
                    System.exit(0);
                }

                else if (choice2 == 'c')
                {
                    InitialDirection = LivingRoom.visit(v, Direction.TO_WEST);

                    v.tell("You have made it successfully into the Living Room and summon the Ender Dragon, then you escape");
                    v.giveGold(25);
                    return Direction.FROM_SOUTH;
                }

            }
            else if (choice == 'b')
            {
                v.tell("The unknown entity heard the doorknob rattle and has killed you before you could even step outside");
                v.takeGold(10);
                System.exit(0);
            }
            else if (choice == 'c')
            {
                InitialDirection = Bedroom.visit(v, Direction.TO_SOUTH);
                v.giveGold(10);
                v.tell("You have safely made it into the sleepy dungeon of the entity");
                v.tell("");
            }
            
        
        

        return Hallway.visit(v,d);
    }

    
    
}
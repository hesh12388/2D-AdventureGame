package OOP.ec22518.MP.contributions;

class House_ec22618 implements Visitable {

    Room room1;
    Room room2;
    Room room3;
    Room room4;

    House_ec22618(){
        room1 = new Room_ec22419();
        room2 = new Room_ec22634();
        room3 = new Room_ec22893();
        room4 = new Room_ec22959();
    }

    public Direction visit(Visitor person, Direction from){
        person.tell("Entering the house...");
        int x = 1;
        int y = 1;

        boolean leaving = false;
        Direction exit = Direction.TO_EAST;
        boolean moving = false;

        while(!leaving){
            person.tell("You are currently in one of the room in the house");
            person.tell("Now pick a direction.");

            char dir = person.getChoice("N)north S)south W)west E)east", new char[]{'N', 'S' ,'W','E'});
        
            switch(dir){
                case 'N':
                y-=1;
                moving = true;
                break;

                case 'S':
                y+=1;
                moving = true;
                break;

                case 'W':
                x-=1;
                moving = true;
                break;

                case 'E':
                x+=1;
                moving = true;
                break;

                default:
                person.tell("heading nowhere in particular");
                moving = false;
                break;
            }
            
            if((x<1||x>3||y<1||y>3)&(moving==true)){
                person.tell("You jumped out the window into the garden and can't climb back in...");
                dir = person.getChoice("Would you like to re-enter the house? Y/N", new char[]{'Y', 'N'});
                switch(dir) {
                    case 'Y':
                         x = 1;
                         y = 1;
                        break;
                    case 'N':
                        leaving = true;
                        break;
                }
            }

            else if((x==3&y==1)&(moving==true)){
                person.tell("You found a room with something in it...");
                exit = room1.visit(person, from);
            }
            else if((x==2&y==1)&(moving==true)){
                person.tell("You found a room with something in it...");
                exit = room2.visit(person, from);
            }
            else if((x==2&y==2)&(moving==true)){
                person.tell("You found a room with something in it...");
                exit = room3.visit(person, from);
            }
            else if((x==3&y==3)&(moving==true)){
                person.tell("You found a room with something in it...");
                exit = room4.visit(person, from);
            }
            else if(moving==true){
                person.tell("Nothing is here...go to the next room.");
            }
        }
        return exit;
    }
}

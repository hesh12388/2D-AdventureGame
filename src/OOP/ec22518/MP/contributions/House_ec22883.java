package OOP.ec22518.MP.contributions;

class House_ec22883 implements Visitable {
    Room_ec22962 egor;
    Room_ec22990 max;
    Room_ec22883 arsen;
    Room_ec221204 bohdan;

    public House_ec22883(){
        max = new Room_ec22990();
        egor = new Room_ec22962();
        bohdan = new Room_ec221204();
        arsen = new Room_ec22883();
    }

    public Direction visit(Visitor v, Direction d)
    {
        v.tell("Welcome to the House!");
        Direction dir=goToEgor(v, d);
        v.tell("Thanks for visiting our house! Come back again!");
        return dir;
    }

    public Direction goToEgor(Visitor v, Direction d)
    {
        v.tell("You are entering the room of arm dealer named Egor");
        Direction dir=egor.visit(v, d);
        if (dir==Direction.TO_EAST)
        {
            dir=goToBohdan(v, dir);
        }
        return dir;
    }

    public Direction goToBohdan(Visitor v, Direction d)
    {
        v.tell("You are entering the gym!");
        Direction dir=bohdan.visit(v, d);
        if (dir==Direction.TO_NORTH)
        {
            dir=goToMax(v, dir);
        }
        return dir;
    }

    public Direction goToMax(Visitor v, Direction d)
    {
        v.tell("You go to the room, where you fight the boss!");
        Direction dir=max.visit(v,d);
        if (dir==Direction.TO_SOUTH)
        {
            dir=goToBohdan(v, dir);
        }
        else if (dir==Direction.TO_NORTH || dir==Direction.TO_EAST)
        {
            dir=goToArsen(v, dir);
        }
        else
        {
            dir=goToEgor(v, dir);
        }
        return dir;
    }


    public Direction goToArsen(Visitor v, Direction d)
    {
        v.tell("You came to arms dealer named Arsen! You can go to the next room only if you make him angry, otherwise you will go back to the previous room.");
        Direction dir=arsen.visit(v,d);
        if (dir==Direction.TO_NORTH || dir==Direction.TO_EAST)
        {
            return dir;
        }
        else if (dir==Direction.TO_SOUTH || dir==Direction.TO_WEST)
        {
            dir=goToMax(v, dir);
        }
        return dir;
    }
}
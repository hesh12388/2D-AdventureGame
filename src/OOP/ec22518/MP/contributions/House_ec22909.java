package OOP.ec22518.MP.contributions;

public class House_ec22909 implements Visitable{

    private Room [] rooms;
    private final int NUMBER_OF_ROOMS = 3;

    House_ec22909(Visitor V, Direction D){
        rooms[0] = new Room_ec221085();
        rooms[1] = new Room_ec20258();
        rooms[2] = new Room_ec22909();
    }

    @Override
    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        
        
        int index = 0;
        Room current = rooms[index];
        Direction direction = current.visit(visitor,Direction.TO_NORTH);
        
        
        
        System.out.println("You enter the house, which is made up of three rooms. ");
        System.out.println("You are standing in a hallway, and you can either go West, East, or North. ");
        
        char[] options = {'1', '2', '3','4'};
        char choice = visitor.getChoice("1. Go North \n2. Go West \n3. Go East\n4. Leave",options);

        if (options.equals('1'))
        {
            visitor.tell("you head north and find yourself in a dark room ");
            current = rooms[0];
            direction = current.visit(visitor,Direction.TO_NORTH);
            
        }
        else if (options.equals('2'))
        {
            visitor.tell("you head north and find yourself in a bright room ");
            current = rooms[1];
            direction = current.visit(visitor,Direction.TO_WEST);
            
        }
        else if (options.equals('3'))
        {
            visitor.tell("you head north and find yourself in a dusty room ");
            current = rooms[2];
            direction = current.visit(visitor,Direction.TO_EAST);
            
        }
        else if (options.equals('4'))
        {
            visitor.tell("you leave this abondoned house ");
            direction = directionVistorArrivesFrom;
            
        }
        
        return direction;






    }


}

package OOP.ec22518.MP.contributions;

public class House_ec22881 implements Visitable{

    Room_ec221003 room3;
    Room_ec22551 room2;
    Room_ec22881 room1;

    public House_ec22881(){
        room3= new Room_ec221003();
        room2 = new Room_ec22551();
        room1= new Room_ec22881();
    }

    @Override
    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        Direction direction1;
        Direction direction2;
        Direction direction3 = Direction.TO_SOUTH;

        System.out.println("You enter the house, which is made up of three rooms. ");
        System.out.println("You are standing in a hallway, and you can either go West, East, or North. ");

        char[] options = {'1', '2', '3', '4'};
        int choice = visitor.getChoice("1. Go North \n2. Go West \n3. Go East \n 4. Go South(leave)",options);

        while (choice!=4 || direction3!=Direction.TO_NORTH) {
            direction1 = room1.visit(visitor, directionVistorArrivesFrom);
            direction2 = room2.visit(visitor, direction1);
            direction3 = room3.visit(visitor, direction2);
        }

        return direction3;






    }


}

package OOP.ec22518.MP.contributions;

public class House_ec221017 implements Visitable {
    //heavily inspired by other pr-s, mostly ec22660
    private Room[] rooms;
    private Room currentR;

    public House_ec221017() 
    {
        rooms = new Room[4];

        //all the rooms in the house loaded into the array

        rooms[0] = new Room_ec22741(); // Alicia
        rooms[1] = new Room_ec22860(); // Mariam
        rooms[2] = new Room_ec22660(); // Gabby
        rooms[3] = new Room_ec22828(); // Maks
    }

    public Direction visit(Visitor visitor, Direction d) 
    {
        // my house is a magical house, it doesn't matter which way you enter or leave a room, the order of the rooms will always be the same :3

        currentR = rooms[0]; // you will first enter Alicia's room
        d = currentR.visit(visitor, d);

        currentR = rooms[1]; //then, no matter where you left, you will enter Mariam's
        d = currentR.visit(visitor, d);

        currentR = rooms[2]; //then Gabby's
        d = currentR.visit(visitor, d);
        
        currentR = rooms[3]; //finally Maks'
        d = currentR.visit(visitor, d);

        visitor.tell("For some magical reason, the last door pulls you in by force and leads you outside. You have left the house."); 

        return d;
    }
}

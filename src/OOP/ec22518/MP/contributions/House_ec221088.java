package OOP.ec22518.MP.contributions;

class House_ec221088 implements Visitable {
    Room_ec22443 room1;
    Room_ec22887 room2;
    Room_ec221085 room3;


    public House_ec221088() {
        room1 = new Room_ec22443(); //dorm room(North)
        room2 = new Room_ec22887(); //East
        room3 = new Room_ec221085(); //West

 // checking to see if it passes.
    }

    public Direction visit(Visitor visitor, Direction directionVisitorArrivesFrom) {
        // Visit the rooms

        room1.visit(visitor, directionVisitorArrivesFrom);
        room2.visit(visitor, directionVisitorArrivesFrom);
        room3.visit(visitor, directionVisitorArrivesFrom);

        return directionVisitorArrivesFrom;
    }

}

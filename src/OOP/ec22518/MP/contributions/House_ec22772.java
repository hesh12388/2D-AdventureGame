package OOP.ec22518.MP.contributions;

class House_ec22772 implements Visitable {

    public Direction visit(Visitor v, Direction d) {

        // Starting room (entering house from the south)
        RoomCvisit = true;
        Direction d1 = RoomC.visit(v, d);

        while(!exitHouse) {

            if(RoomAvisit) {

                if(Direction.opposite(d1) == Direction.TO_NORTH) {
                    System.out.println(d1.toString());
                    System.out.println("Congratulations, you successfully escaped the House of Mystery.");
                    RoomAvisit = false;
                    exitHouse = true;
                }

                else if(Direction.opposite(d1) == Direction.TO_EAST) {
                    System.out.println(d1.toString());
                    System.out.println("Congratulations, you successfully escaped the House of Mystery.");
                    RoomAvisit = false;
                    exitHouse = true;
                }

                else if(Direction.opposite(d1) == Direction.TO_SOUTH) {
                    RoomAvisit = false;
                    RoomCvisit = true;
                    System.out.println(d1.toString());
                    RoomC.visit(v, Direction.opposite(d1));
                }

                else {
                    System.out.println(d1.toString());
                    System.out.println("Congratulations, you successfully escaped the House of Mystery.");
                    RoomAvisit = false;
                    exitHouse = true;
                }

            }

            else if(RoomBvisit) {

                if(d1 == Direction.TO_NORTH) {
                    System.out.println(d1.toString());
                    System.out.println("Congratulations, you successfully escaped the House of Mystery.");
                    RoomBvisit = false;
                    exitHouse = true;
                }

                else if(d1 == Direction.TO_EAST) {
                    System.out.println(d1.toString());
                    System.out.println("Congratulations, you successfully escaped the House of Mystery.");
                    RoomBvisit = false;
                    exitHouse = true;
                }

                else if(d1 == Direction.TO_SOUTH) {
                    System.out.println(d1.toString());
                    System.out.println("Congratulations, you successfully escaped the House of Mystery.");
                    RoomBvisit = false;
                    exitHouse = true;
                }

                else {
                    RoomBvisit = false;
                    RoomCvisit = true;
                    System.out.println(d1.toString());
                    RoomC.visit(v, Direction.opposite(d1));
                }

            }

            
            else if(RoomCvisit) {

                if(d1 == Direction.TO_NORTH) {
                    RoomCvisit = false;
                    RoomAvisit = true;
                    System.out.println(d1.toString());
                    d1 = RoomA.visit(v, Direction.opposite(d1));
                }

                else if(d1 == Direction.TO_EAST) {
                    RoomCvisit = false;
                    RoomBvisit = true;
                    System.out.println(d1.toString());
                    d1 = RoomB.visit(v, Direction.opposite(d1));
                }

                else if(d1 == Direction.TO_SOUTH) {
                    System.out.println("You cannot exit Shrek's swamp.");
                    System.out.println("Sending you east...");
                    RoomCvisit = false;
                    RoomBvisit = true;
                    System.out.println(d1.toString());
                    d1 = RoomB.visit(v, Direction.opposite(d1));
                }

                else {
                    RoomCvisit = false;
                    RoomDvisit = true;
                    System.out.println(d1.toString());
                    d1 = RoomD.visit(v, Direction.opposite(d1));
                }
                
            }

            else if(RoomDvisit) {

                if(d1 == Direction.TO_NORTH) {
                    System.out.println(d1.toString());
                    System.out.println("Congratulations, you successfully escaped the House of Mystery.");
                    RoomDvisit = false;
                    exitHouse = true;
                }

                else if(d1 == Direction.TO_EAST) {
                    RoomDvisit = false;
                    RoomCvisit = true;
                    System.out.println(d1.toString());
                    RoomC.visit(v, Direction.opposite(d1));
                }

                else if(d1 == Direction.TO_SOUTH) {
                    System.out.println(d1.toString());
                    System.out.println("Congratulations, you successfully escaped the House of Mystery.");
                    RoomDvisit = false;
                    exitHouse = true;
                }

                else {
                    System.out.println(d1.toString());
                    System.out.println("Congratulations, you successfully escaped the House of Mystery.");
                    RoomDvisit = false;
                    exitHouse = true;
                }

            }
        }


        return d;
    }

    Room RoomA, RoomB, RoomC, RoomD;
    boolean RoomAvisit, RoomBvisit, RoomCvisit, RoomDvisit = false;
    boolean exitHouse = false;

    public House_ec22772() {

        RoomA = new Room_ec22519();
        RoomB = new Room_ec22520();
        RoomC = new Room_ec22772();
        RoomD = new Room_jpp319457();

    }
}

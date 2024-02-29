package OOP.ec22518.MP.contributions;

class House_ec22990 implements Visitable {

    Room_ec22962 egg;
    Room_ec22990 max;
    Room_ec22948 sasha;
    Room_ec22883 arsen;
    Room_ec221204 boh;

    public House_ec22990() {
        this.egg = new Room_ec22962();
        this.max = new Room_ec22990();
        this.sasha = new Room_ec22948();
        this.arsen = new Room_ec22883();
        this.boh = new Room_ec221204();
    }
    
    public static void main(String[] args) {
        House_ec22990 h = new House_ec22990();
        Visitor v = new IOVisitor(System.out,System.in);
        
        Direction d = h.visit(v, Direction.FROM_SOUTH);
    }

    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        if(directionVistorArrivesFrom == Direction.FROM_EAST) { return Direction.TO_NORTH; }
        
        boolean in_house = true;
        int old_room = 1;
        Direction directionVistorArrives = directionVistorArrivesFrom;
        
        while(in_house) {
            //walk around all rooms
            if (old_room == 1) {
                directionVistorArrives = sasha.visit(visitor, directionVistorArrives);   
            }
            
            if (old_room == 2) {
                directionVistorArrives = egg.visit(visitor, directionVistorArrives);   
            }            
            
            if (old_room == 3) {
                directionVistorArrives = max.visit(visitor, directionVistorArrives);
            }
            
            if (old_room == 4) {
                directionVistorArrives = arsen.visit(visitor, directionVistorArrives);
                // directionVistorArrives = Direction.TO_SOUTH;
            }
            
            if (old_room == 5) {
                directionVistorArrives = boh.visit(visitor, directionVistorArrives);   
            }
            
            old_room = next_room(old_room, directionVistorArrives);
            if (old_room == 0) {
                in_house = false;
            }
                        
        }

        return Direction.TO_EAST;
    }

    public static int next_room(int old_room, Direction dir) {
        int room;
        if (old_room == 1) {
            room = sashko(dir);
            return room;
        }
        if (old_room == 2) {
            room = egor(dir);
            return room;
        }
        if (old_room == 3) {
            room = max(dir);
            return room;
        }
        if (old_room == 4) {
            room = arsen(dir);
            return room;
        }
        if (old_room == 5) {
            room = bohdan(dir);
            return room;
        }
        return 0;
    }
    
    public static int max(Direction dir) {
        if (dir == Direction.TO_EAST) {
            return 2;
        } else if (dir == Direction.TO_NORTH) {
            return 4;
        } else if (dir == Direction.TO_SOUTH) {
            return 5;
        } else if (dir == Direction.TO_WEST) {
            return 0;
        }
        return 0;
    }
    
    public static int egor(Direction dir) {
        if (dir == Direction.TO_EAST) {
            return 3;
        }
        return 0;
    }

    public static int sashko(Direction dir) {
        return 2;
    }
    
    public static int arsen(Direction dir) {
        if (dir == Direction.TO_NORTH) {
            return 3;
        } else {
            return 0;
        }
    }
    
    public static int bohdan(Direction dir) {
        if (dir == Direction.TO_NORTH) {
            return 3;
        }
        return 0;
    }
}

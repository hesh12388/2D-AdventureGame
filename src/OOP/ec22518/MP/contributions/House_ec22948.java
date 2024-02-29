package OOP.ec22518.MP.contributions;

class House_ec22948 implements Visitable {

    
    Room_ec22883 arsen;
    Room_ec22948 sasha;
    Room_ec22962 egg;
    Room_ec221204 boh;
    Room_ec22990 max;
    

    public House_ec22948() {
        
        this.arsen = new Room_ec22883();
        this.sasha = new Room_ec22948();
        this.egg = new Room_ec22962();
        this.boh = new Room_ec221204();
        this.max = new Room_ec22990();
    }
    
    public static void main(String[] args) {
        House_ec22948 h = new House_ec22948();
        Visitor v = new IOVisitor(System.out,System.in);
        
        Direction d = h.visit(v, Direction.FROM_SOUTH);
    }

    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        if(directionVistorArrivesFrom == Direction.FROM_EAST) { return Direction.TO_NORTH; }
        
        boolean in = true;
        int old = 1;
        Direction directionVistorArrives = directionVistorArrivesFrom;
        
        while(in) {
            //walk around all rooms
            if (old == 1) {
                directionVistorArrives = sasha.visit(visitor, directionVistorArrives);   
            }
            
            if (old == 2) {
                directionVistorArrives = egg.visit(visitor, directionVistorArrives);   
            }            
            
            if (old == 3) {
                directionVistorArrives = max.visit(visitor, directionVistorArrives);
            }
            
            if (old == 4) {
                directionVistorArrives = arsen.visit(visitor, directionVistorArrives);
                // directionVistorArrives = Direction.TO_SOUTH;
            }
            
            if (old == 5) {
                directionVistorArrives = boh.visit(visitor, directionVistorArrives);   
            }
            
            old = next(old, directionVistorArrives);
            if (old == 0) {
                in = false;
            }
                        
        }

        return Direction.TO_EAST;
    }

    public static int next(int old, Direction dir) {
        int room;
        if (old == 1) {
            room = sasha(dir);
            return room;
        }
        if (old == 2) {
            room = egor(dir);
            return room;
        }
        if (old == 3) {
            room = max(dir);
            return room;
        }
        if (old == 4) {
            room = arsen(dir);
            return room;
        }
        if (old == 5) {
            room = bohdan(dir);
            return room;
        }
        return 0;
    }

    public static int arsen(Direction dir) {
        if (dir == Direction.TO_NORTH) {
            return 3;
        } else {
            return 0;
        }
    }

    public static int sasha(Direction dir) {
        return 2;
    }

    public static int egor(Direction dir) {
        if (dir == Direction.TO_EAST) {
            return 3;
        }
        return 0;
    }

    public static int bohdan(Direction dir) {
        if (dir == Direction.TO_NORTH) {
            return 3;
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
    
    
    
    public static void transition(Visitor visitor, Direction directionUserIsGoingTo) {
        if(directionUserIsGoingTo == Direction.TO_NORTH) {
            System.out.println("You are in a hallway walking north.");
        } else if(directionUserIsGoingTo == Direction.TO_SOUTH) {
            System.out.println("You are in a hallway walking south.");
        } else if(directionUserIsGoingTo == Direction.TO_EAST) {
            System.out.println("You are in a hallway walking east.");
        }else if(directionUserIsGoingTo == Direction.TO_WEST) {
            System.out.println("You are in a hallway walking west.");
        }
    }
}

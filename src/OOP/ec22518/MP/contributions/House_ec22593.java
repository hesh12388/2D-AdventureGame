package OOP.ec22518.MP.contributions;

class House_ec22593 implements Visitable {
    Room r1;
    Room r2;
    Room r3;
    Direction d1;
    Direction d2;
    Direction d3;
    final char[] yesNoArray={'y','n'};
    final String yesNoDescription="\n y for yes n for no";
    final Direction[] allDirections = new Direction[]{Direction.TO_NORTH, Direction.TO_EAST, Direction.TO_SOUTH, Direction.TO_WEST};
    public Direction visit(Visitor v, Direction d) {
        r1= new Room_ec22593();
        r2= new Room_ec22887();
        r3= new Room_ec22666();
        v.tell("You have entered my house which has a cyclic collection of portals. Have fun exploring");
        d1=r1.visit(v,d);
        d2=r2.visit(v,d1);
        d3=r3.visit(v,d1);
        char choice=v.getChoice("Would you like to change your direction before leaving my house?"+yesNoDescription,yesNoArray);
        if(choice=='n'||choice=='N')
            return d3;
        char[] neswLower=new char[]{'n','e','s','w'};
        char[] neswUpper=new char[]{'N','E','S','W'};
        choice=v.getChoice("Which direction do you want to be on your new direction?",neswLower);
        for(int i=0;i<4;i++)
        {
            if(choice==neswUpper[i]||choice==neswLower[i])
                return allDirections[i];
        }
        return d;
    }
}

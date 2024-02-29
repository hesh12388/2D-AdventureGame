package OOP.ec22518.MP.contributions;

public class House_ec22456 implements Visitable {
    Room r1;
    Room r2;
    Room r3;

    House_ec22456() {
        r1 = new Room_ec22456();
        r2 = new Room_ec22718();
        r3 = new Room_ec22881();
    }

    public Direction visit(Visitor v, Direction d) {
        v.tell("PLEASE LEAVE IMMEDIATELY POST EXPLORING MY PROPERTY YOU HEATHEN!");

        d = r1.visit(v, d);
        d = r2.visit(v, d);
        d = r3.visit(v, d);

        return d;
    }
}

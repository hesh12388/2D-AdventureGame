package OOP.ec22518.MP;

import javax.swing.*;
import java.awt.*;

public class Room1Panel extends JPanel {

    MyVisitor v;
    KeyHandler keyH;
    Container con;
    public static MainScreen m;

    public Inventory i;
    public Room1Panel(KeyHandler keyH, MyVisitor v, Inventory i)
    {

        this.i=i;
        this.v=v;
        this.keyH = keyH;
        this.addKeyListener(keyH);
        this.setPreferredSize(new Dimension(2000, 2000));
        JPanel base = new JPanel();


        base.setPreferredSize(new Dimension(2000, 2000));
        CardLayout cl = new CardLayout();

        base.setLayout(cl);




         m = new MainScreen(keyH, this, v, i);

        base.add(m, "1");

        cl.show(base, "1");











        this.add(base);
    }
}
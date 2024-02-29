package OOP.ec22518.MP;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


class MyFrame extends JFrame{

    public static Inventory i = new Inventory();

   public static MyVisitor v= new MyVisitor(i);
    public static Hallway panelTest;
    Room1Panel r1;
    public static ArrayList<JPanel> panels;
    ArrayList<JPanel> mainPanels;
    public static KeyHandler keyH = new KeyHandler();
    public static CardLayout cl;

    public static JPanel base;
    Room3Panel r3;
    Room2Panel r2;
    public MyFrame()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(new Dimension(500, 500));
        this.addKeyListener(keyH);
        this.setFocusable(true);
            base = new JPanel();
             cl = new CardLayout();
            base.setLayout(cl);

            JPanel enterPanel = new JPanel();
            enterPanel.setBackground(Color.black);
            enterPanel.setLayout(null);
            enterPanel.setSize(new Dimension(500, 500));
            JButton enter = new JButton("Enter House");
            enter.setBounds(700, 500, 200, 50);
            enterPanel.add(enter);

            JPanel house = new JPanel();

            house.setSize(new Dimension(2000, 2000));
            house.setLayout(null);
            JPanel p1 = new JPanel();
            JPanel p2 = new JPanel();
            JPanel p3 = new JPanel();
            mainPanels = new ArrayList<JPanel>(4);
         r1= new Room1Panel(keyH, v, i);
         r2 = new Room2Panel(keyH, v, i);
         r3 = new Room3Panel(keyH, v, i);

        ArrayList<JPanel> panels = new ArrayList<JPanel>();

        panels.add(r1);
        panels.add(r2);
        panels.add(r3);
        panelTest = new Hallway(keyH, cl, panels, base, v, i);
        mainPanels.add(p1);
        mainPanels.add(p2);
        mainPanels.add(p3);

        Border b = BorderFactory.createLineBorder(Color.white);

        Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 40);
            int count=0;
            for(JPanel p : mainPanels){
                JLabel l = new JLabel("Room " + (count+1));
                l.setFont(f);
                l.setForeground(Color.white);
                p.setLayout(null);
                p.setBorder(b);
                l.setBounds(350, 150, 200, 100);
                p.add(l);
                p.setBackground(new Color(0x123456));
                count++;
            }
            for(JPanel p: mainPanels)
            {
                house.add(p);
            }
            house.add(panelTest);

            mainPanels.get(0).setBounds(0, 0, 850, 450);
            mainPanels.get(1).setBounds(850, 0, 1000, 450);
            panelTest.setBounds(0, 450, 2000, 200);
            mainPanels.get(2).setBounds(400, 650, 850, 450);
            Map<JPanel, String> rooms = new HashMap<JPanel, String>();

            rooms.put(house, "2");
            rooms.put(r1, "3");
            rooms.put(r2, "4");
            rooms.put(r3, "5");


            base.add(enterPanel, "1");
            for(JPanel p : rooms.keySet())
            {
                base.add(p, rooms.get(p));
            }


            cl.show(base, "1");


            enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cl.show(base, rooms.get(house));
                    panelTest.startGameThread();
                }
            });




            this.add(base);



    }




}









package OOP.ec22518.MP;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import OOP.ec22518.MP.contributions.*;
import OOP.ec22518.MP.contributions.Visitor;
import OOP.ec22518.MP.contributions.Direction;

public class Hallway extends JPanel implements Runnable, Visitable{

    int FPS =60;


   public int count=0;
    public ArrayList<JButton> buttons;
    public  Thread gameThread;
    KeyHandler keyH;
    public static Player player;
    char choice;
    ArrayList<JPanel> panels;

    public MyVisitor v;
    public Direction d;
    public static boolean exit= true;
    JPanel base;
    public Inventory i;

    public boolean allowed =true;

    CardLayout cl;
    public Hallway(KeyHandler keyH, CardLayout cl, ArrayList<JPanel> panels, JPanel base, MyVisitor v, Inventory i)
    {
        this.v=v;
        this.keyH=keyH;
        this.panels=panels;
        this.base=base;
        this.cl = cl;
        this.i=i;
        player= new Player( keyH, 500, 100, 70);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(2000, 2000));

        this.setLayout(null);




    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run()
    {
         double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread!=null) {


            update();

            repaint();



         try {
         double remainingDrawTime = nextDrawTime - System.nanoTime();
         remainingDrawTime =remainingDrawTime/1000000;
        //
            if(remainingDrawTime<0)
            {
             remainingDrawTime=0;
            }
            Thread.sleep((long) remainingDrawTime);
            //
            nextDrawTime +=drawInterval;
             }

        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        }
    }

    public void update() {


        if (exit == true) {

            if (Hallway.getPlayerX() < 0) {
                player.setPlayerX(1629);
            }


            if (Hallway.getPlayerX() > 1630) {
                player.setPlayerX(1);
            }


            if (Hallway.getPlayerX() < 850 && Hallway.getPlayerX() > 0) {
                if (Hallway.getPlayerY() < 0) {
                    choice = v.getChoice("You are about to enter room 1, do you comply or would you like to be redirected to the hallway again", new char[]{'y', 'n'});


                    if (choice == 'y') {
                        keyH.reset();
                        player.setPlayerY(5);
                        Room1Panel.m.startGameThread();
                        Room1Panel.m.subWindow=true;

                        Room1Panel.m.v.tell("Infront of you lays an archway, /n to your east a forgotten treasure, /n and to your west a cavern. Procede at your own will");

                        Room1Panel.m.repaint();
                        d = visit(v, Direction.TO_NORTH);
                        exit = false;
                        this.gameThread.stop();
                    } else if (choice == 'n') {

                        keyH.reset();
                        player.setPlayerY(50);
                    }


                }

            } else if (Hallway.getPlayerX() > 850 && Hallway.getPlayerX() < 2000) {
                if (Hallway.getPlayerY() < 0) {
                    choice = v.getChoice("You are about to enter room 2, do you comply or would you like to be redirected to the hallway again", new char[]{'y', 'n'});

                    if (choice == 'y') {
                        keyH.reset();
                        player.setPlayerY(50);
                        Room2Panel.m.startGameThread();
                        Room2Panel.m.subWindow=true;

                        Room2Panel.m.v.tell("Infront of you lays a waterfall, /n to your east a princess trapped in a dungeon, /n and to your west an old wizards liar./n Procede at your own will");
                        Room2Panel.m.repaint();

                        d = visit(v, Direction.TO_EAST);
                        exit = false;
                        this.gameThread.stop();
                    } else if (choice == 'n') {
                        keyH.reset();
                        player.setPlayerY(50);
                    }
                }

            }


            if (Hallway.getPlayerX() > 400 && Hallway.getPlayerX() < 1250) {
                if (Hallway.getPlayerY() > 170) {

                    choice = v.getChoice("You are about to enter room 3, do you comply or would you like to be redirected to the hallway again", new char[]{'y', 'n'});
                    if (choice == 'y') {
                        keyH.reset();
                        player.setPlayerY(50);

                        Room3Panel.m.startGameThread();
                        Room3Panel.m.subWindow=true;

                        Room3Panel.m.v.tell("Choose doors at your own will");

                        Room3Panel.m.repaint();

                        d = visit(v, Direction.TO_WEST);
                        exit = false;
                        this.gameThread.stop();
                    } else if(choice=='n'){
                        keyH.reset();
                        player.setPlayerY(50);
                    }
                }
            }
        }

        if(Hallway.getPlayerX()>0 && Hallway.getPlayerX()<400)
        {
            if(Hallway.getPlayerY()>160)
            {
                player.setPlayerY(159);
            }
        }

        if(Hallway.getPlayerX()>1250 && Hallway.getPlayerX()<2000)
        {
            if(Hallway.getPlayerY()>160)
            {
                player.setPlayerY(159);
            }
        }


        if(allowed) {
            if (i.inventory.containsKey(Room3Panel.m.keyItem)) {
                Room3Panel.m.keyH.exitPressed=false;
                char choice = v.getChoice("You have the key, would you like to leave the house?", new char[]{'y', 'n'});

                if (choice == 'y') {
                    System.exit(0);
                }

                else if(choice=='n'){
                    allowed=false;
                }
            }

            else{
                count=100;
            }


        }

        player.update();
    }



    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }

    public static int getPlayerX()
    {
        return player.playerX;
    }

    public static int getPlayerY()
    {
        return player.playerY;
    }

    public  void setPanels(int index)
    {
        keyH.reset();
        cl.show(base, Integer.toString((index+3)));
    }

    @Override
    public Direction visit(Visitor visitor, Direction directionVistorArrivesFrom) {
        if(directionVistorArrivesFrom.equals(Direction.TO_NORTH))
        {

            setPanels(0);
        }

        else if(directionVistorArrivesFrom.equals(Direction.TO_WEST))
        {
            setPanels(2);
        }

        else if(directionVistorArrivesFrom.equals(Direction.TO_EAST))
        {
            setPanels(1);
        }


        return directionVistorArrivesFrom;
    }


}


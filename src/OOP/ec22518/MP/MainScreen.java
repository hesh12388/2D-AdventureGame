package OOP.ec22518.MP;

import OOP.ec22518.MP.contributions.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class MainScreen extends JPanel implements Runnable{

        int FPS =60;

    public String commandDialogue="Commands: /n Move through the room using the arrow keys /n To remove the dialogue after it has appeared press enter /n To see your inventory press R /n If allowed, you can return to the hallway with E /n To view the commands, press C anytime /n Good luck";
        char choice;
        TileManage tm;

        public boolean command=true;
    public Item sheepItem = new Item("sheep");

        Thread gameThread;
        public static Player player;
        boolean subWindow = false;
        boolean text=false;

        public Inventory i;
        MyVisitor v;
        Room1Panel r1;
        boolean allowed=false;

        public int count=0;
        boolean inventory=false;


    Graphics2D g2;
        JButton run;
        JButton attack;
        KeyHandler keyH;

        JFrame treasure = new JFrame();

        public Item sword = new Item("sword");
        public MainScreen(KeyHandler keyH, Room1Panel r1, MyVisitor v, Inventory i)
        {
            Tile [] tiles = getTileImage();

            tm = new TileManage(this, tiles);
            this.i=i;
            this.v=v;
            this.r1=r1;
            this.keyH = keyH;
            player= new Player(keyH, 800, 10, 70);
            this.setDoubleBuffered(true);
            this.setFocusable(true);
            this.setVisible(true);

            this.setSize(new Dimension(2000, 2000));
            this.setBackground(new Color(0x97452));
            this.v=v;







        }



        public void startGameThread()
        {
            gameThread = new Thread(this);
            gameThread.start();
        }

    public Tile [] getTileImage()
    {
        Tile [] tiles = new Tile [4];
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("treasure.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("cavern.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("graveyard.png"));


            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("room.png"));

        }

        catch(IOException e)
        {
            e.printStackTrace();
        }

        return tiles;
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

        public void update()
        {
            if(this.getPlayerY()>960)
            {
                player.setPlayerY(0);
            }



            if(this.getPlayerY()<0)
            {
                player.setPlayerY(960);
            }

            if(this.getPlayerX()>1800)
            {
                player.setPlayerX(150);
            }

            if(this.getPlayerX()<150)
            {
                player.setPlayerX(1800);
            }

            if(this.getPlayerX()>250 && this.getPlayerX()<400)
            {


                if(this.getPlayerY()>300 && this.getPlayerY()<500) {

                    player.setPlayerX(800);
                    player.setPlayerY(5);


                    subWindow=true;
                    v.tell("You have been greedy and guillable and as a result /n I am taking five peices of your gold");
                    repaint();

                    keyH.reset();
                    int n= v.takeGold(5);
                    repaint();

                }
            }

            if(this.getPlayerX()>1400 && this.getPlayerX()<1700)
            {
                if(this.getPlayerY()>300 && this.getPlayerY()<500) {


                    player.setPlayerX(800);
                    player.setPlayerY(5);

                    v.tell("You have stumbled across an ancient monk's cave, /n He has given you the chance to leave the room /n Simply press e to access this power");
                    subWindow=true;
                    allowed=true;
                    repaint();

                }
            }

            if(this.getPlayerX()>850 && this.getPlayerX()<1150)
            {
                if(this.getPlayerY()>700 && this.getPlayerY()<900) {

                    player.setPlayerX(800);
                    player.setPlayerY(5);
                    v.tell("You have encountered flesh eating zombies");
                   subWindow=true;
                   repaint();


                    keyH.reset();

                    choice= v.getChoice("What do you do? (Attack/a or Run/r", new char [] {'a', 'r'});
                    keyH.reset();

                    switch(choice)
                    {
                        case 'a':
                            boolean accepted= v.giveItem(sword);

                            if(accepted)
                            {
                                v.tell("I have given you a sword to defend yourself /n You have narrowly defeated them, and for your bravery, /n I am awarding you 5 peices of gold");
                                v.tell("You have lost 20 health in the proccess");
                                v.takeHealth(20);
                                repaint();

                                v.giveGold(5);
                            }

                            else{
                                v.tell("You have nothing to defend yourself with /n and get bitten.You only have 1 health bar left, /nlose it and you will die");
                                v.takeHealth(i.inventory.get(i.health)-1);
                            }


                            break;

                        case 'r':
                            v.tell("There is nothing worse than a coward /n I have stripped you of all your gold");
                            repaint();
                            v.takeGold(i.inventory.get(i.gold));
                            break;


                    }




                }
            }


            if(keyH.enterPressed)
            {

                keyH.reset();
                    command=false;
                    subWindow = false;
                    inventory = false;

                repaint();
            }

            if(keyH.commandPressed)
            {

                command=true;

            }



            if(keyH.rPressed)
            {
                inventory=true;
            }

            if(keyH.Spressed)
            {
                if(i.inventory.containsKey("sheep"))
                {
                    keyH.reset();
                    choice=v.getChoice("Would you like eat the meat?", new char[] {'y', 'n'});

                    if(choice=='y')
                    {
                        i.inventory.remove(sheepItem);
                        v.giveHealth(30);
                        subWindow=true;
                        v.tell("You can now view your updated health /n in your inventory");
                    }
                }

                else{
                    subWindow=true;
                    v.tell("You do not have this item at the moment");
                }
            }


            if(allowed) {
                if (keyH.exitPressed) {
                    MyFrame.cl.show(MyFrame.base, "2");
                    MyFrame.panelTest.player.setPlayerX(50);
                    MyFrame.panelTest.player.setPlayerY(5);
                    MyFrame.panelTest.exit=true;
                    allowed=false;
                    MyFrame.panelTest.allowed=true;
                    MyFrame.panelTest.startGameThread();
                    this.gameThread.stop();



                }
            }






            player.update();
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g2 = (Graphics2D) g;
            tm.draw(g2);
            player.draw(g2);


            if(subWindow)
            {
                drawSubWindow(800, 300, 400, 300);
            }

            if(inventory)
            {
                i.draw(g2);
            }


            if(command)
            {
                drawCommandWindow(300, 100, 400, 300);
            }




        }

        public void drawSubWindow(int x, int y, int width, int height)
        {
            Color c = new Color(0,0,0, 150);
            g2.setColor(c);
            g2.fillRoundRect(x, y, width, height, 35, 35);

            c= new Color(255, 255, 255);

            g2.setColor(c);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

            x+=45;
            y+=50;
            for(String s : v.dialogue.split("/n")) {
                g2.drawString(s, x, y);
                y+=40;
            }
        }

    public void drawCommandWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 150);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);

        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

        x += 45;
        y += 50;
        for (String s : commandDialogue.split("/n")) {
            g2.drawString(s, x, y);
            y += 40;
        }
    }


        public int getPlayerX()
        {
            return this.player.playerX;
        }

        public int getPlayerY()
        {
            return this.player.playerY;
        }

    }




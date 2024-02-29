package OOP.ec22518.MP;


import OOP.ec22518.MP.contributions.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class MainScreen3 extends JPanel implements Runnable{

    int FPS =60;

    public String commandDialogue="Commands: /n Move through the room using the arrow keys /n To remove the dialogue after it has appeared press enter /n To see your inventory press R /n If allowed, you can return to the hallway with E /n To view the commands, press C anytime /n Good luck";
    char choice;
    TileManage tm;

    public boolean command=true;

    Thread gameThread;
    public static Player player;
    boolean subWindow = false;
    boolean text=false;

    public Inventory i;
    MyVisitor v;
    Room3Panel r3;
    boolean allowed=false;

    boolean door2= true;

    boolean door3 = true;

    public int count=0;
    boolean inventory=false;


    Graphics2D g2;
    JButton run;
    JButton attack;
    KeyHandler keyH;
    public boolean accepted;

    public boolean sheep;

    public Item sheepItem = new Item("sheep");

    public boolean key;

    public boolean gold=false;

    public Item keyItem =  new Item("key");

    JFrame treasure = new JFrame();

    public boolean door1= true;

    public Item sword = new Item("sword");
    public MainScreen3(KeyHandler keyH, Room3Panel r3, MyVisitor v, Inventory i)
    {
        Tile [] tiles = getTileImage();

        tm = new TileManage(this, tiles);
        this.i=i;
        this.v=v;
        this.r3=r3;
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
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("door_closed.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("door_closed.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("door_closed.png"));


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

                if(door1)
                {
                    player.setPlayerX(this.getPlayerX()+70);
                    choice = v.getChoice("Would you like to open the door?" , new char[] { 'y', 'n'});

                    if(choice=='y')
                    {
                        try {
                            tm.tiles[0].image = ImageIO.read(getClass().getResourceAsStream("door_open.png"));
                            gold=true;
                            door1=false;
                            keyH.reset();
                            subWindow=true;
                            v.tell("You have a bag of gold /n Press T to retrieve your reward");

                            repaint();
                        }

                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }

        if(this.getPlayerX()>1400 && this.getPlayerX()<1700)
        {
            if(this.getPlayerY()>300 && this.getPlayerY()<500) {



                if(door3)
                {
                    player.setPlayerX(this.getPlayerX()-70);
                    choice = v.getChoice("Would you like to open the door?" , new char[] { 'y', 'n'});

                    if(choice=='y')
                    {
                        try {
                            tm.tiles[1].image = ImageIO.read(getClass().getResourceAsStream("door_open.png"));
                            key=true;
                            door3=false;
                            keyH.reset();
                            subWindow=true;
                            v.tell("You have found the key to rule all keys /n It grants you the power to leave the house completely /n Press T to take the key");

                            repaint();
                        }

                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        if(this.getPlayerX()>850 && this.getPlayerX()<1150)
        {
            if(this.getPlayerY()>700 && this.getPlayerY()<900) {

                if(door2)
                {
                    player.setPlayerY(this.getPlayerY()-70);
                    choice = v.getChoice("Would you like to open the door?" , new char[] { 'y', 'n'});

                    if(choice=='y')
                    {
                        try {
                            tm.tiles[2].image = ImageIO.read(getClass().getResourceAsStream("door_open.png"));
                            sheep = true;
                            door2=false;
                            keyH.reset();
                            subWindow=true;
                            v.tell("You have long since eaten. /n You have found a sheep /n take its meat and retrieve 30 of your health /n Press T to take the sheep /n Press S to use the sheep");

                            repaint();
                        }

                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
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

        if(keyH.Spressed)
        {
            if(i.inventory.containsKey(sheepItem))
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






        if(key) {
            if (keyH.Tpressed) {
                keyH.reset();
                accepted = v.giveItem(keyItem);
                if (accepted) {
                    key = false;
                    repaint();
                }
            }
        }

        if(sheep) {
            if (keyH.Tpressed) {
                keyH.reset();
                accepted = v.giveItem(sheepItem);
                if (accepted) {
                    sheep= false;
                    repaint();
                }
            }
        }

        if(gold) {
            if (keyH.Tpressed) {
                v.tell("Greedy again? Look at your gold");
                keyH.reset();
                    gold= false;
                    v.takeGold(i.inventory.get(i.gold));
                    repaint();
            }
        }



        if(keyH.rPressed)
        {
            inventory=true;
        }


            if (keyH.exitPressed) {
                keyH.reset();
                MyFrame.cl.show(MyFrame.base, "2");
                MyFrame.panelTest.player.setPlayerX(50);
                MyFrame.panelTest.player.setPlayerY(5);
                player.update();
                MyFrame.panelTest.exit=true;
                MyFrame.panelTest.allowed=true;
                MyFrame.panelTest.startGameThread();
                this.gameThread.stop();

            }







        player.update();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        tm.draw(g2);
        player.draw(g2);

        if(key)
        {
            Tile key = new Tile();
            try {
                key.image = ImageIO.read(getClass().getResourceAsStream("key.png"));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            g2.drawImage(key.image, 1450, 350, 150, 150, null);
        }

        if(gold)
        {
            Tile gold = new Tile();
            try {
                gold.image = ImageIO.read(getClass().getResourceAsStream("goldbag.png"));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            g2.drawImage(gold.image, 300, 350, 150, 150, null);
        }
        if(sheep)
        {
            Tile sheep = new Tile();
            try {
                sheep.image = ImageIO.read(getClass().getResourceAsStream("sheep.png"));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            g2.drawImage(sheep.image, 900, 750, 150, 150, null);
        }
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




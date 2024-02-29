package OOP.ec22518.MP;

import OOP.ec22518.MP.contributions.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainScreen2 extends JPanel implements Runnable {

    int FPS = 60;

    public String commandDialogue="Commands: /n Move through the room using the arrow keys /n To remove the dialogue after it has appeared press enter /n To see your inventory press R /n If allowed, you can return to the hallway with E /n To view the commands, press C anytime /n Good luck";

    char choice;

    public Item sheepItem = new Item("sheep");
    TileManage tm;

    Thread gameThread;
    public static Player player2;
    boolean subWindow = false;
    boolean text = false;

    public Inventory i;
    MyVisitor v;
    Room2Panel r1;

    boolean inventory = false;

    public int count=0;

    Graphics2D g2;

    KeyHandler keyH;

    JFrame treasure = new JFrame();

    public boolean command=true;

    public Item sword = new Item("sword");

    public MainScreen2(KeyHandler keyH, Room2Panel r1, MyVisitor v, Inventory i) {

        Tile [] t = getTileImage();

        tm = new TileManage(this, t);
        this.i = i;
        this.v = v;
        this.r1 = r1;
        this.keyH = keyH;
        player2 = new Player(keyH, 800, 10, 70);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setVisible(true);

        this.setSize(new Dimension(2000, 2000));
        this.setBackground(new Color(0x123456));
        this.v = v;






    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public Tile [] getTileImage()
    {
        Tile [] tiles = new Tile [4];
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("dungeon.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("wizard.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("dam.png"));


            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("room.png"));

        }

        catch(IOException e)
        {
            e.printStackTrace();
        }

        return tiles;
    }

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {


            update();

            repaint();


            try {
                double remainingDrawTime = nextDrawTime - System.nanoTime();
                remainingDrawTime = remainingDrawTime / 1000000;
                //
                if (remainingDrawTime < 0) {
                    remainingDrawTime = 0;
                }
                Thread.sleep((long) remainingDrawTime);
                //
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void update() {
        if (this.getPlayerY()>960) {
            player2.setPlayerY(0);
        }

        if (this.getPlayerY() <0) {
            player2.setPlayerY(960);
        }

        if (this.getPlayerX() > 1800) {
            player2.setPlayerX(155);
        }

        if (this.getPlayerX() <150) {
            player2.setPlayerX(1750);
        }

        if (this.getPlayerX()>250 && this.getPlayerX()<400) {
            if (this.getPlayerY() > 300 && this.getPlayerY() <500) {


                player2.setPlayerX(800);
                player2.setPlayerY(5);


                subWindow = true;

                if(v.hasEqualItem(sword)) {
                    v.tell("You have a sword and slay the guards,/n saving the princess /n and releasing her from the dungeon");
                }

                else if(i.inventory.get(i.gold)>=10)
                {
                    v.tell("You have enough gold to bribe the guards and free the princess");

                    choice = v.getChoice("Would you like to use 10 of your gold? (y/n", new char [] {'y', 'n'});

                    if(choice=='y'){
                        v.takeGold(10);
                        v.tell("The princess thanks you for your bravery and /n invites you to her castle when you /n have completed your journey");
                    }

                    else{
                        v.tell("The princess wishes a curse upon you /n which takes 90 health" );
                        v.takeHealth(90);
                    }
                }
                else{
                    v.tell("You have no money or weapons /n to free the princess, come back when you do ");
                }
                repaint();

                keyH.reset();
                repaint();

            }
        }

        if (this.getPlayerX() > 1400 && this.getPlayerX() < 1700) {
            if (this.getPlayerY() > 300 && this.getPlayerY() < 500) {


                this.player2.setPlayerX(800);
                this.player2.setPlayerY(5);


                subWindow = true;
                v.tell("You have stumbled across an old wizards liar, /n He has given you a wand and advises you /n to use it only in times of danger");
                repaint();
                v.giveItem(new Item("wand"));
                keyH.reset();
                repaint();


            }
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
                    v.tell("You can now view your updated health /n  in your inventory");
                }
            }

            else{
                subWindow=true;
                v.tell("You do not have this item at the moment");
            }
        }

        if (this.getPlayerX() > 850 &&  this.getPlayerX() < 1150) {
            if (this.getPlayerY() > 700 && this.getPlayerY() < 900) {

                player2.setPlayerX(800);
                player2.setPlayerY(5);
                keyH.reset();
                v.tell("You have found the waters of youth /n You drink from the water /n and regain your full health");

                v.giveHealth(i.maxHealth-i.inventory.get(i.health));
                subWindow = true;
                repaint();



            }
        }


        if (keyH.enterPressed) {
            keyH.reset();
            command=false;
                subWindow = false;
                inventory = false;
            repaint();
        }


        if (keyH.rPressed) {
            inventory = true;
        }



            if (keyH.exitPressed) {
                MyFrame.cl.show(MyFrame.base, "2");
                MyFrame.panelTest.player.setPlayerX(50);
                MyFrame.panelTest.player.setPlayerY(5);
                MyFrame.panelTest.exit = true;
                MyFrame.panelTest.allowed=true;
                MyFrame.panelTest.startGameThread();
                this.gameThread.stop();



            }

        if(keyH.commandPressed)
        {

            command=true;

        }


        player2.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        tm.draw(g2);
        player2.draw(g2);
        if (subWindow) {
            drawSubWindow(800, 300, 400, 300);
        }

        if (inventory) {
            i.draw(g2);
        }

        if(command)
        {
            drawCommandWindow(300, 100, 400, 300);
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 150);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);

        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

        x += 45;
        y += 50;
        for (String s : v.dialogue.split("/n")) {
            g2.drawString(s, x, y);
            y += 40;
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


    public int getPlayerX() {
        return this.player2.playerX;
    }

    public int getPlayerY() {
        return this.player2.playerY;
    }
}

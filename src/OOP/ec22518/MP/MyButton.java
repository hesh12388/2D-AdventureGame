//package OOP.ec22518.A8;
//
//import javax.swing.*;
//import java.awt.*;
//
//
//public class MyButton extends JButton implements Runnable{
//
//    private int room_number;
//    private boolean active;
//    MyButton(String s, int room_number, boolean active)
//    {
//        this.setText(s);
//        this.active = active;
//        this.room_number=room_number;
//        this.setPreferredSize(new Dimension(500, 500));
//    }
//
//    public int getRoom_number()
//    {
//        return this.room_number;
//    }
//
//    public void setActive()
//    {
//        this.active=!active;
//    }
//
//
//    final int originalTileSize =16;
//    final int scale=3;
//
//    public final int tileSize = originalTileSize* scale;
//
//    final int maxCol =16;
//    final int maxRow =12;
//
//    int FPS =60;
//    final int screenWidth=tileSize*maxCol;
//
//    final int screenHeight = tileSize*maxRow;
//
//    KeyHandler keyH = new KeyHandler();
//    Thread gameThread;
//
//    Player player = new Player(this, keyH);
//
//
//    public MyButton()
//    {
//        this.setDoubleBuffered(true);
//        this.addKeyListener(keyH);
//        this.setFocusable(true);
//        this.setVisible(true);
//        this.setPreferredSize(new Dimension(500, 500));
//        this.setBackground(new Color(0xb3cde0));
//    }
//
//    public void startGameThread()
//    {
//        gameThread = new Thread(this);
//        gameThread.start();
//    }
//    public void run()
//    {
//        double drawInterval = 1000000000/FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        while(gameThread!=null) {
//
//
//            update();
//
//            repaint();
//
//
//
//            try {
//                double remainingDrawTime = nextDrawTime - System.nanoTime();
//                remainingDrawTime =remainingDrawTime/1000000;
//                //
//                if(remainingDrawTime<0)
//                {
//                    remainingDrawTime=0;
//                }
//                Thread.sleep((long) remainingDrawTime);
//                //
//                nextDrawTime +=drawInterval;
//            }
//            catch(InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void update()
//    {
//
//        player.update();
//    }
//
//    public void paintComponent(Graphics g)
//    {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        player.draw(g2);
//        g2.dispose();
//    }
//
//
//
//}

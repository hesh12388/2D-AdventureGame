package OOP.ec22518.MP;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
    public int playerX, playerY;

    public int playerSpeed;


    public int W;
    public BufferedImage up1,up2, down1, down2, left1, left2, right1, right2;

    public String direction;

    public int spriteCounter=0;
    public int spriteNum=1;
    KeyHandler  keyH;


    public Player(KeyHandler keyH, int X, int Y, int W)
    {
        this.W=W;
        this.keyH=keyH;
        setDefaultValues(X, Y);
        getPlayerImage();
    }

    public void getPlayerImage()
    {
        try{
            up1 =ImageIO.read(getClass().getResourceAsStream("boy_up_1.png"));
            up2 =ImageIO.read(getClass().getResourceAsStream("boy_up_2.png"));
            down1 =ImageIO.read(getClass().getResourceAsStream("boy_down_1.png"));
            down2 =ImageIO.read(getClass().getResourceAsStream("boy_down_2.png"));
            right1 =ImageIO.read(getClass().getResourceAsStream("boy_right_1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("boy_right_2.png"));
            left1 =ImageIO.read(getClass().getResourceAsStream("boy_left_1.png"));
            left2 =ImageIO.read(getClass().getResourceAsStream("boy_left_2.png"));

        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setDefaultValues(int X, int Y)
    {
        this.playerX=X;
        this.playerY=Y;
        playerSpeed=6;
        direction="down";
    }

    public void update()
    {
        if(keyH.upPressed==true)
        {
            direction="up";
            this.playerY-=playerSpeed;
        }

         else if(keyH.downPressed==true)
        {
            direction="down";
            this.playerY+=playerSpeed;
        }

         else if(keyH.leftPressed==true)
        {
            direction="left";
            this.playerX-=playerSpeed;
        }

         else if(keyH.rightPressed==true){
            direction="right";
           this. playerX+=playerSpeed;
        }

        spriteCounter++;

        if(spriteCounter>10)
        {
            if(spriteNum==1)
            {
                spriteNum=2;
            }

            else if(spriteNum==2)
            {
                spriteNum=1;
            }
            spriteCounter=0;
        }

    }

    public void draw(Graphics2D g2)
    {
//        g2.setColor(Color.white);
//
//        g2.fillRect(playerX, playerY, p.tileSize, p. tileSize);

        BufferedImage image= null;

        switch(direction)
        {
            case "up":
                if(spriteNum==1) {
                    image = up1;
                }

                if(spriteNum==2)
                {
                    image = up2;
                }
                break;

            case "down":
                if(spriteNum==1) {
                    image = down1;
                }

                if(spriteNum==2)
                {
                    image = down2;
                }
                break;

            case "right":
                if(spriteNum==1) {
                    image = right1;
                }

                if(spriteNum==2)
                {
                    image = right2;
                }
                break;
            case "left":
                if(spriteNum==1) {
                    image = left1;
                }

                if(spriteNum==2)
                {
                    image = left2;
                }
                break;
        }



        g2.drawImage(image, playerX, playerY, W, W, null);
    }


    public void setPlayerX(int x)
    {
        this.playerX=x;
    }

    public void setPlayerY(int x)
    {
        this.playerY=x;
    }


}

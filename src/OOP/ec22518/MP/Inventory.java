package OOP.ec22518.MP;

import OOP.ec22518.MP.contributions.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class Inventory {


    public HashMap<Item, Integer> inventory;

    public Tile [] images;

    public int x;

    public int y;

    public Item gold = new Item("gold");

    public Item health = new Item("health");

    public int maxHealth=100;

    public Inventory()
    {
        inventory = new HashMap<>(7);
        inventory.put(gold, 10);
        inventory.put(health, 100);
        images = new Tile[6];
        setDefaultValues();
        getItemImages();
    }

    public void setDefaultValues()
    {
        x=840;
        y=340;
    }

    public HashMap<Item, Integer> getInventory()
    {
        return this.inventory;
    }

    public void addItem(Item X)
    {
            inventory.put(X, 1);
    }

    public void updateHealth(int n)
    {
        if(n<0)
        {
            n=0;
        }

        if(n>100)
        {
            n=100;
        }
        inventory.replace(health, n);
    }

    public void addGold(int n)
    {
        inventory.replace(gold,inventory.get(gold)+ n);
    }

    public void removeGold(int n)
    {
        inventory.replace(gold,inventory.get(gold)- n);
    }

    public void updateItem(Item X)
    {
        for(Item i: inventory.keySet())
        {
            if(X.name==i.name)
            {
                inventory.replace(i, inventory.get(i)+1);
            }
        }
    }

    public void getItemImages()
    {

        try{
            images[2] = new Tile(2, "sword");
            images[2].image = ImageIO.read(getClass().getResourceAsStream("sword.png"));


            images[3] = new Tile(3, "key");
            images[3].image = ImageIO.read(getClass().getResourceAsStream("key.png"));


            images[0] = new Tile(0, "gold");
            images[0].image = ImageIO.read(getClass().getResourceAsStream("gold.png"));


            images[5] = new Tile(5, "wand");
            images[5].image = ImageIO.read(getClass().getResourceAsStream("wand.png"));


            images[1] = new Tile(1, "health");
            images[1].image = ImageIO.read(getClass().getResourceAsStream("health.png"));


            images[4] = new Tile(4, "sheep");
            images[4].image = ImageIO.read(getClass().getResourceAsStream("meat.png"));

        }

        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2)
    {
        Color c = new Color(0,0,0, 150);
        g2.setColor(c);
        g2.fillRoundRect(800, 300, 400, 200, 35, 35);

        c= new Color(255, 255, 255);

        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(805, 305, 390, 190, 25, 25);



        setDefaultValues();
        for(Item i : inventory.keySet())
        {
            determineHealthImage();


            for(Tile t: images) {

                if(i.name==t.name) {
                    g2.drawImage(images[t.index].image, x, y-20, 40, 40, null);
                    g2.drawString(i.name + ": " + inventory.get(i), x + 60, y);
                    y+=45;
                }
            }


            if(y>500)
            {
                y = 340;
                x = 970;
            }




        }

    }

    public void determineHealthImage()
    {
        if(inventory.get(health)<75)
        {
            try {
                images[1].image = ImageIO.read(getClass().getResourceAsStream("halfHeart.png"));
            }
            catch(IOException e){
                e.printStackTrace();
             }
        }

        if(inventory.get(health)==100)
        {
            try {
                images[1].image = ImageIO.read(getClass().getResourceAsStream("health.png"));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

        if(inventory.get(health)<50)
        {
            try {
                images[1].image = ImageIO.read(getClass().getResourceAsStream("quarterHeart.png"));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

        if(inventory.get(health)==0)
        {
            try {
                images[1].image = ImageIO.read(getClass().getResourceAsStream("emptyHeart.png"));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public int determineY(int n)
    {

        int num=y;
        for(int i=0; i<n; i++)
        {
            num+=40;
        }

        System.out.println(num);

        if(num>470)
        {
            x=900;
            return y;
        }

        return num;
    }
}

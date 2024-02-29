package OOP.ec22518.MP;

import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage image;
    public int index;
    public String name;

    public Tile(int i, String n)
    {
        this.index=i;
        this.name=n;
    }

    public Tile()
    {
        this.index=-1;
        this.name="";
    }

}

package OOP.ec22518.MP;

import javax.swing.*;
import java.awt.*;


public class TileManage {

    JPanel r1;
    public Tile [] tiles;



    public TileManage(JPanel r1, Tile [] t){
        this.r1=r1;

        tiles = t;


    }




    public void draw(Graphics2D g2)
    {
        g2.drawImage(tiles[3].image, 0, 0, 2000, 1200, null );
        g2.drawImage(tiles[0].image, 250, 300, 300, 300, null );
        g2.drawImage(tiles[2].image, 850, 700, 300, 300, null );
        g2.drawImage(tiles[1].image, 1400, 300, 300, 300, null );

    }



}

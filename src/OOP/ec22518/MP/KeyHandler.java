package OOP.ec22518.MP;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;

    public boolean enterPressed;
    public boolean Spressed;

    public boolean exitPressed;

    public boolean Tpressed;

    public boolean rPressed;

    public boolean commandPressed;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code==KeyEvent.VK_UP)
        {
            upPressed=true;
        }

        if(code==KeyEvent.VK_DOWN)
        {
            downPressed=true;
        }

        if(code==KeyEvent.VK_RIGHT)
        {
            rightPressed=true;
        }

         if(code==KeyEvent.VK_LEFT)
        {
            leftPressed=true;
        }

         if(code==KeyEvent.VK_ENTER)
         {
                enterPressed=true;
         }

        if(code==KeyEvent.VK_E)
        {
            exitPressed=true;
        }

        if(code==KeyEvent.VK_R)
        {
            rPressed=true;
        }

        if(code==KeyEvent.VK_C)
        {
            commandPressed=true;
        }

        if(code==KeyEvent.VK_T)
        {
            Tpressed=true;
        }

        if(code==KeyEvent.VK_S)
        {
            Spressed=true;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_UP)
        {
            upPressed=false;
        }

        if(code==KeyEvent.VK_DOWN)
        {
            downPressed=false;
        }

         if(code==KeyEvent.VK_LEFT)
        {
            leftPressed=false;
        }

         if(code==KeyEvent.VK_RIGHT)
        {
            rightPressed=false;
        }
        if(code==KeyEvent.VK_ENTER)
        {
            enterPressed=false;
        }

        if(code==KeyEvent.VK_C)
        {
            commandPressed=false;
        }

        if(code==KeyEvent.VK_E)
        {
            exitPressed=false;
        }

        if(code==KeyEvent.VK_R)
        {
            rPressed=false;
        }

        if(code==KeyEvent.VK_T)
        {
            Tpressed=false;
        }

        if(code==KeyEvent.VK_S)
        {
            Spressed=false;
        }


    }

    public void reset()
    {
        upPressed=false;
        downPressed=false;
        leftPressed=false;
        rightPressed=false;
        Tpressed=false;
        Spressed=false;
    }
}

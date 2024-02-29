package OOP.ec22518.MP.contributions;

class House_ec22813 implements Visitable {
    Room r1;
    Room r2;
    Room r3;
    Room now;

    public House_ec22813() 
    {
        r1 = new Room_ec22813();
        r2 = new Room_ec22802();
        r3 = new Room_ec221017();
    }

    public Direction visit(Visitor x, Direction y) 
    {
        Boolean loop = true;
        Item Key = new Item("Key");
        char[] options = { 'a', 'b', 'c' };

        x.tell("Welcome to my house, there are 2 doors you can choose. Which one do you want to choose?");
        if (x.getChoice("a.) West or b.) East", options) == 'a') 
        {
            now = r1;
        }
        else{
            now = r2;
        }

        while (loop) 
        {
            if (now == r1) 
            {
                y = r1.visit(x, y);
                x.tell("You go into room 1");
                x.tell("there are 2 doors you can choose. Which one do you want to choose?");
                if (x.getChoice("a.) West or b.) East", options) == 'a')
                {
                    now = r3;
                }

                else{
                    now = r2;
                }
            }

            if(now == r2)
            {
                x.tell("You go into room 2");
                y = r2.visit(x, y);
                if (x.getChoice("a.) West or b.) East", options) == 'a')
                {
                    now = r1;
                }

                else{
                    now = r3;
                }

            }

            if(now == r3)
            {
                x.tell("You go into room 3");
                y = r3.visit(x, y);
                if (x.getChoice("a.) West or b.) East c.) North", options) == 'a') 
                {
                    now = r2;
                }

                else if(x.getChoice("a.) West or b.) East c.) North", options) == 'b') 
                {
                    now = r1;
                }

                else
                {
                    x.tell("Now you exit the house.");
                    loop = false;
                }
            }
        }
    return y;
    }
}
